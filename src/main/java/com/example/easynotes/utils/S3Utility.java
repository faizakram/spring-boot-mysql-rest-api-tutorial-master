package com.example.easynotes.utils;

import java.io.InputStream;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.DeleteObjectsRequest;
import com.amazonaws.services.s3.model.ObjectListing;
import com.amazonaws.services.s3.model.PutObjectResult;
import com.amazonaws.services.s3.model.S3Object;
import com.amazonaws.services.s3.model.S3ObjectSummary;
import com.example.easynotes.model.CopyObjects;
import com.example.easynotes.repository.CopyObjectRepository;

@Component
public class S3Utility {

	@Value("${cloud.aws.credentials.accessKey}")
	private String accessKey;

	@Value("${cloud.aws.credentials.secretKey}")
	private String secretkey;

	@Value("${app.awsServices.private.bucketName}")
	private String privateBucketName;

	@Value("${app.awsServices.public.bucketName}")
	private String publicBucketName;

	@Value("${cloud.aws.region.static}")
	private String regions;

	@Autowired
	private CopyObjectRepository copyObjectRepository;
	/**
	 * Get Basic AWS Credentials
	 * 
	 * @param accesskey
	 * @param secretkey
	 * @return
	 */
	private AWSCredentials getBasicAWSCredentials(String accesskey, String secretkey) {
		return new BasicAWSCredentials(accesskey, secretkey);
	}

	/**
	 * Get AmazonS3
	 * 
	 * @return
	 */
	private AmazonS3 getAmazonS3() {
		return AmazonS3ClientBuilder.standard()
				.withCredentials(new AWSStaticCredentialsProvider(getBasicAWSCredentials(accessKey, secretkey)))
				.withRegion(regions).build();
	}

	/**
	 * Upload On S3
	 * 
	 * @param bucketName
	 * @param fileName
	 * @param file
	 * @return
	 */
	public PutObjectResult uploadOnS3(String fileName, InputStream file) {
		return getAmazonS3().putObject(privateBucketName, fileName, file, null);
	}

	/**
	 * Download Object
	 * 
	 * @param bucketName
	 * @param fileName
	 * @return
	 */
	public S3Object downloadObject(String fileName) {
		return getAmazonS3().getObject(privateBucketName, fileName);
	}

	/**
	 * Get All Objects
	 * 
	 * @return
	 */
	public ObjectListing getAllObjects() {
		return getAmazonS3().listObjects(privateBucketName);
	}

	private void copyObjectsFromPrivateToPublic(List<String> resources) {
		writeObjectsInDB(resources);
		resources
				.forEach(fileName -> getAmazonS3().copyObject(privateBucketName, fileName, publicBucketName, fileName));

	}

	private void writeObjectsInDB(List<String> resources) {
		for (S3ObjectSummary os : getAllObjects().getObjectSummaries()) {
			if (resources.contains(os.getKey())) {
				CopyObjects copyObjects = new CopyObjects();
				copyObjects.setFileName(os.getKey());
				copyObjects.setCreatedOn(DateUtil.getCurrentTimestampInUTC());
				copyObjects.setFileSize(os.getSize());
				copyObjectRepository.save(copyObjects);
			}
		}
	}

	private void deleteObjectsFromPublic(List<String> objects) {
		objects.forEach(fileName -> {
			DeleteObjectsRequest delObjReq = new DeleteObjectsRequest(publicBucketName).withKeys(fileName);
			getAmazonS3().deleteObjects(delObjReq);
		});

	}

	/**
	 * 
	 * @param resources
	 * @param min
	 * @throws InterruptedException
	 */
	@Async("asyncExecutor")
	public void copyObjectsFromS3BucketToBuket(List<String> resources, Long min) throws InterruptedException {
		// Copy Object From Private Bucket From Public Bucket
		copyObjectsFromPrivateToPublic(resources);
		Thread.sleep(min * 60000);
		// Delete All Objects From Public
		deleteObjectsFromPublic(resources);
	}
}
