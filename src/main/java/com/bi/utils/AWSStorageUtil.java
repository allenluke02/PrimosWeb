package com.bi.utils;

import com.amazonaws.SdkClientException;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.services.s3.model.S3Object;
import com.amazonaws.services.s3.model.S3ObjectInputStream;
import com.amazonaws.util.IOUtils;
import com.bi.config.ConfigurationService;
import com.bi.model.ConfigurationKeyImpl;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.StringTokenizer;

@Component("AWS")
public class AWSStorageUtil implements StorageUtil{
	
	@Autowired
	private ConfigurationService configurationService;
	
	private AmazonS3 s3Client;
	private static final Logger LOGGER = LoggerFactory.getLogger(AWSStorageUtil.class);
	

	@Override
	@PostConstruct
	public void createClient() {
		String accessKey = configurationService.getConfigValue(String.class, ConfigurationKeyImpl.AWS_ACCESS_KEY);
		String secretKey = configurationService.getConfigValue(String.class, ConfigurationKeyImpl.AWS_SECRET_ACCESS_KEY);
		String regionKey = configurationService.getConfigValue(String.class, ConfigurationKeyImpl.AWS_REGION);
		
		this.s3Client = AmazonS3ClientBuilder.standard()
				.withCredentials(new AWSStaticCredentialsProvider(new BasicAWSCredentials(accessKey, secretKey)))
				.withRegion(Regions.fromName(regionKey)).build();
		
	}

	/**
	 * 
	 * @param bucket           - Bucket where file will be uploaded
	 * @param stringObjKeyName - String object key name
	 * @param fileObjKeyName   - File object key name
	 * @param fileName
	 * @param metadata
	 * @return
	 * @throws URISyntaxException 
	 */
	@Override
	public URI uploadFile(String bucketName, String fileName, InputStream is, String contentType) {

		try {
			// This code expects that you have AWS credentials set up per:
			// https://docs.aws.amazon.com/sdk-for-java/v1/developer-guide/setup-credentials.html
			// Upload a text string as a new object. s3Client.putObject(bucketName, stringObjKeyName, "Uploaded String Object");
			 
			ObjectMetadata metadata = new ObjectMetadata();
			metadata.setContentType(contentType);
			// Upload a file as a new object with ContentType and title specified.
			if(StringUtils.isBlank(contentType)) {
				metadata.setContentType("plain/text");
			}
			metadata.addUserMetadata("x-amz-meta-title", fileName);
			PutObjectRequest request = new PutObjectRequest(bucketName, fileName, is, metadata);
			s3Client.putObject(request);
			return new URI(s3Client.getUrl(bucketName, fileName).toExternalForm());
		} catch (SdkClientException e) {
			LOGGER.error("error storing file {} to s3", fileName, e);
		} catch (URISyntaxException e) {
			LOGGER.error("Storage exception for file {}", fileName, e);
			throw new RuntimeException("Storage exception for file " + fileName, e);			
		}
		return null;
		
	}
	@Override
	public S3ObjectInputStream downloadFile(String bucketName, String fileObjKeyName) {
		try {
			// This code expects that you have AWS credentials set up per:
			// https://docs.aws.amazon.com/sdk-for-java/v1/developer-guide/setup-credentials.html

			S3Object s3object = s3Client.getObject(bucketName, fileObjKeyName);
			return s3object.getObjectContent();
//			S3ObjectInputStream inputStream = s3object.getObjectContent();
//			return new InputStreamResource( new ByteArrayInputStream(inputStream.getDelegateStream().readAllBytes()));
		} catch (Exception e) {
			LOGGER.error("error downloading bucket {} file {}", bucketName, fileObjKeyName, e);
		}
		return null;
	}
	@Override
	public Boolean delete(String bucketName, String fileName) {
		s3Client.deleteObject(bucketName, fileName);
		return Boolean.TRUE;
	}
	public File writeInputStreamToFile(InputStream inputStream, String fileName) throws IOException {
    	StringTokenizer fileToken = new StringTokenizer(fileName, ".");
		File targetFile = File.createTempFile(fileToken.nextToken(), "." + fileToken.nextToken());
		Files.copy(	inputStream, 
					targetFile.toPath(), 
					StandardCopyOption.REPLACE_EXISTING);
			 
		IOUtils.closeQuietly(inputStream,null);
		return targetFile;
    }
}
