package com.bi.utils;

import com.amazonaws.services.s3.model.S3ObjectInputStream;

import java.io.InputStream;
import java.net.URI;

public interface StorageUtil {
	
	public void createClient();
	
	public URI uploadFile(String bucketName, String fileName, InputStream is, String contentType);
	
	public S3ObjectInputStream downloadFile(String bucketName, String fileObjKeyName);
	
	public Boolean delete(String bucketName, String fileName);
}
