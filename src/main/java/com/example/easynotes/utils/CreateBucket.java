package com.example.project.dms.utility;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.CreateBucketRequest;
import com.amazonaws.services.s3.model.GetBucketLocationRequest;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;

@Component
public class CreateBucket {

	@Autowired
	private S3Utility s3Utility;

	public String createBucket(String bucketName) {
		AmazonS3 s3Client = s3Utility.getAmazonS3();
		String str = UUID.randomUUID().toString();
		if (!s3Client.doesBucketExistV2(str)) {
			// Because the CreateBucketRequest object doesn't specify a region, the
			// bucket is created in the region specified in the client.
			s3Client.createBucket(new CreateBucketRequest(str));
			// Verify that the bucket was created by retrieving it and checking its
			// location.
			return s3Client.getBucketLocation(new GetBucketLocationRequest(str));
		}
		return null;
	}
	
	
	public void createFolder(String folderName) {
		String bucketName ="common1234";
		folderName = folderName.replace("common1234/", "");
		AmazonS3 client = s3Utility.getAmazonS3();
	    // create meta-data for your folder and set content-length to 0
	    ObjectMetadata metadata = new ObjectMetadata();
	    metadata.setContentLength(0);
	    // create empty content
	    InputStream emptyContent = new ByteArrayInputStream(new byte[0]);
	    // create a PutObjectRequest passing the folder name suffixed by /
	    PutObjectRequest putObjectRequest = new PutObjectRequest(bucketName, folderName + "/", emptyContent, metadata);

	    // send request to S3 to create folder
	    client.putObject(putObjectRequest);
	}

}
