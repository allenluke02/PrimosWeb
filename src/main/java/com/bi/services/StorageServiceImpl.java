package com.bi.services;

import java.io.IOException;
import java.net.URI;
import java.util.Optional;
import java.util.UUID;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.amazonaws.services.s3.model.S3ObjectInputStream;
import com.bi.config.ConfigurationService;
import com.bi.exception.EntityNotFoundException;
import com.bi.exception.FileStorageException;
import com.bi.model.ConfigurationKeyImpl;
import com.bi.model.QUser;
import com.bi.model.User;
import com.bi.model.Document.Document;
import com.bi.model.Document.QDocument;
import com.bi.model.Document.UploadFileResponse;
import com.bi.repositories.DocumentRepository;
import com.bi.repositories.UserRepository;
import com.bi.utils.BIConstant;
import com.bi.utils.ContextUtil;
import com.bi.utils.StorageUtil;


@Service
public class StorageServiceImpl {

    @Autowired
    private DocumentRepository documentRepository;
    @Autowired
    @Qualifier("AWS")
    private StorageUtil storageUtil;
	
    @Autowired
	private MessageByLocaleService messageByLocaleService;
   
    @Autowired
	private ConfigurationService configurationService;
    
    @Autowired
    ContextUtil contextUtil;
    
	@Autowired
	private UserRepository userRepository;
	
	private static final Logger LFS_LOGGER = LoggerFactory.getLogger(StorageServiceImpl.class);

    public UploadFileResponse storeFile(String documentType, MultipartFile file) {
       
		String fileName = StringUtils.cleanPath(file.getOriginalFilename());
		String contentType = file.getContentType() == null ? "application/octet-stream" : file.getContentType();
		try {
			// Check if the file's name contains invalid characters
			if (fileName.contains("..")) {
				throw new FileStorageException("Sorry! Filename contains invalid path sequence " + fileName);
			}
			if(file.getSize() > BIConstant.UPLOAD_FILE_MAX_SIZE) {
				throw new FileStorageException("Maximum file size is 10 MB");
				
			}
			
			
			if(!contextUtil.isValidLoggedInUser()) {
				throw new EntityNotFoundException(messageByLocaleService.getMessage("err.user.not.found"));
			}
			
			
			Document doc = saveDocument(documentType, fileName);
			String bucketName = configurationService.getConfigValue(String.class, ConfigurationKeyImpl.AWS_UPLOAD_DOCUMENT_BUCKET);
			
			URI fileStorageUri = storageUtil.uploadFile(bucketName, doc.getGuid(),file.getInputStream(),contentType);
			String fileURI = BIConstant.BIURI_FOR_DOWNLOAD + doc.getGuid();
			doc.setFileStorageURI(fileStorageUri.toString());
			doc.setFileURI(fileURI);
			doc.setFileName(fileName);
			doc.setDocumentName(fileName);
			doc.setContentType(contentType);
			doc = documentRepository.save(doc);
			return new UploadFileResponse(doc.getFileName(), doc.getGuid(), file.getContentType(), fileURI);

		} catch (IOException ex) {
			throw new FileStorageException("Could not store file " + fileName + ". Please try again!", ex);
		}
	}
    
    private String getExtension(String fileName) {
    	int dotIdx = fileName.lastIndexOf(".");
    	if(dotIdx > 0) {
    		return fileName.substring(dotIdx, fileName.length());
    	}
    	return null;
    }
    
    @Transactional
    public S3ObjectInputStream loadFileAsResource(long documentId) throws IOException {
    	String bucketName = configurationService.getConfigValue(String.class, ConfigurationKeyImpl.AWS_UPLOAD_DOCUMENT_BUCKET);
    	return storageUtil.downloadFile(bucketName,documentRepository.getOne(documentId).getGuid());
    }  
    public S3ObjectInputStream loadFileAsResource(Document document) throws IOException {
    	String bucketName = configurationService.getConfigValue(String.class, ConfigurationKeyImpl.AWS_UPLOAD_DOCUMENT_BUCKET);
    	return storageUtil.downloadFile(bucketName,document.getGuid());
    }    
    @Transactional
    public Document getDocumentByDocumentId(String documentId) {
    	return documentRepository.findOne(QDocument.document.guid.eq(documentId)).orElseThrow(() -> new EntityNotFoundException("document not found"));
    }
    
    @Transactional
    public Boolean deleteDocument(Long documentId){
    	 Document doc = documentRepository.getOne(documentId);
    	 String bucketName = configurationService.getConfigValue(String.class, ConfigurationKeyImpl.AWS_UPLOAD_DOCUMENT_BUCKET);
    	 storageUtil.delete(bucketName, doc.getFileName());
    	 doc.setIsDeleted(Boolean.TRUE);
    	 documentRepository.save(doc);
    	return Boolean.TRUE;
    }
    @Transactional
    public Boolean doSoftDeleteDocument(Long documentId){
   	 	Document doc = documentRepository.getOne(documentId);
   	 	doc.setIsDeleted(Boolean.TRUE);
   	 	documentRepository.save(doc);
   	 	return Boolean.TRUE;
   }
  
	private Document saveDocument(String documentFormat, String fileName) {

		Document doc = new Document();
		doc.setFileName(fileName);
		doc.setDocumentFormat(documentFormat);
		doc.setGuid(UUID.randomUUID().toString());
		return documentRepository.save(doc);
	}
}