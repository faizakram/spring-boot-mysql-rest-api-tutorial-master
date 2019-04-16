package com.example.easynotes.utils;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
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

	public List<String> uploadOnS3All(List<MultipartFile> files) {
		Map<String, MultipartFile> map = new HashMap<>();
		List<String> names = new ArrayList<>();
		files.forEach(e -> {
			String fileName = UUID.randomUUID() + e.getOriginalFilename();
			map.put(fileName, e);
			names.add(fileName);
		});
		uploadOnS3All(map);
		return names;
	}

	@Async
	public void uploadOnS3All(Map<String, MultipartFile> map) {
		
		for (Map.Entry<String, MultipartFile> entry : map.entrySet()) {
			try {
				getAmazonS3().putObject(privateBucketName, entry.getKey(), entry.getValue().getInputStream(), null);
			} catch (Exception e) {
				e.printStackTrace();
			}
	    }
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

	private void copyObjectsFromPrivateToPublic(List<String> resources, Long min) {
		writeObjectsInDB(resources, min);
		resources
				.forEach(fileName -> getAmazonS3().copyObject(privateBucketName, fileName, publicBucketName, fileName));

	}

	private void writeObjectsInDB(List<String> resources, Long min) {
		ObjectListing objectListing = getAllObjects();
		Map<String, S3ObjectSummary> map = objectListing.getObjectSummaries().stream()
				.collect(Collectors.toMap(S3ObjectSummary::getKey, s -> s));
		resources.forEach(fileNmae -> {
			S3ObjectSummary os = map.get(fileNmae);
			System.out.println("added");
			CopyObjects copyObjects = new CopyObjects();
			copyObjects.setFileName(os.getKey());
			copyObjects.setFileSize(os.getSize());
			copyObjects.setValidMin(min);
			copyObjects.setIsDeleted(false);
			copyObjects.setExpirationTime(getExpireDateTime(min));
			copyObjectRepository.save(copyObjects);
		});
	}

	/**
	 * getExpireDateTime
	 * 
	 * @param min
	 * @return
	 */
	public static Date getExpireDateTime(Long min) {
		Calendar now = Calendar.getInstance();
		now.add(Calendar.MINUTE, min.intValue());
		return now.getTime();
	}

	public void deleteObjectsFromPublic(List<String> objects) {
		objects.forEach(fileName -> getAmazonS3().deleteObject(publicBucketName, fileName));

	}

	/**
	 * 
	 * @param resources
	 * @param min
	 * @throws InterruptedException
	 */
	@Async
	public void copyObjectsFromS3BucketToBuket(List<String> resources, Long min) {
		copyObjectsFromPrivateToPublic(resources, min);
	}
}
