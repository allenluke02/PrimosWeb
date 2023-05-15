package com.bi.controllers;

import com.bi.model.Document.Document;
import com.bi.model.Document.DocumentType;
import com.bi.model.Document.UploadFileResponse;
import com.bi.repositories.TokenRepository;
import com.bi.services.MessageByLocaleService;
import com.bi.services.StorageServiceImpl;
import com.bi.utils.ContentDisposition;
import com.bi.views.DocumentViews;
import com.fasterxml.jackson.annotation.JsonView;
import io.swagger.annotations.Api;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.method.annotation.StreamingResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@Api(value ="File Controller for uploading  and downloading documents")
public class FileController {

    private static final Logger LFS_LOGGER = LoggerFactory.getLogger(FileController.class);

    @Autowired
    private StorageServiceImpl fileStorageService;
    @Autowired	
	private MessageByLocaleService messageByLocaleService;
    @Autowired
    private TokenRepository tokenRepository;
    
	@PostMapping("/uploadFile")
	public UploadFileResponse uploadFile(@RequestParam(value = "documentType", required = false) String documentType,
										 @RequestPart("file") MultipartFile file) {
		documentType = documentType != null ? documentType : DocumentType.OTHERS.toString();
		
		return fileStorageService.storeFile(documentType, file);
	}	
	
	@PostMapping("/uploadMultipleFiles")
    public List<UploadFileResponse> uploadMultipleFiles(@RequestParam("documentType")String documentType,
    		@RequestParam("files") MultipartFile[] files) {
		return Arrays.asList(files)
                .stream()
                .map(file -> uploadFile(documentType,file))
                .collect(Collectors.toList());
    }
	
    @GetMapping("/downloadFile/{documentId}")
    @JsonView(DocumentViews.DocumentBasicView.class)
    public ResponseEntity<StreamingResponseBody> downloadFile(@PathVariable String documentId,
															  @RequestParam(name="responseContentDisposition", required = false) ContentDisposition responseContentDisposition,
															  HttpServletRequest request,
															  final HttpServletResponse response) throws IOException {
		Document document = fileStorageService.getDocumentByDocumentId(documentId);
		String contentType=document.getContentType();
    	if(responseContentDisposition == null) {
    		responseContentDisposition = ContentDisposition.ATTACHMENT;
    	}
    	response.setContentType(MediaType.parseMediaType(contentType).toString());
        response.setHeader(HttpHeaders.CONTENT_DISPOSITION, responseContentDisposition + "; filename=\"" + document.getFileName() + "\"");
//        S3ObjectInputStream  is = fileStorageService.loadFileAsResource(document)
        StreamingResponseBody stream = out -> {
        	try (InputStream is = fileStorageService.loadFileAsResource(document) ) {
        		int numberOfBytesToWrite = 0;
                byte[] data = new byte[1024];
                while ((numberOfBytesToWrite = is.read(data, 0, data.length)) != -1) {
                    out.write(data, 0, numberOfBytesToWrite);
                }
        	}
        };
        
        return new ResponseEntity<>(stream, HttpStatus.OK);
    }
  
    @PutMapping("/deleteFile/{documentId}")
    public Boolean deleteDocument(@PathVariable long documentId){
    	
    	return fileStorageService.deleteDocument(documentId);
    }
    
	@GetMapping("/DocumentType")
	public DocumentType[] getDocumentTypeList(){

		return DocumentType.values();
	}
}
