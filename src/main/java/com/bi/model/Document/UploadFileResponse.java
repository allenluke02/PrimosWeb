package com.bi.model.Document;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UploadFileResponse {
    private String fileName;
    private String fileUploadId;
    private String fileURI;
    private String fileType;
   

    public UploadFileResponse(String fileName, String fileUploadId, String fileType, String fileURI) {
        this.fileName = fileName;
        this.fileUploadId = fileUploadId; 
        this.fileType = fileType;
        this.fileURI = fileURI;
       
    }

	
}
