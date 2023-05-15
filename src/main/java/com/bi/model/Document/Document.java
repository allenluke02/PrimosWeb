 package com.bi.model.Document;

 import com.bi.model.Auditable;
 import com.bi.model.AuthUser;
 import com.bi.views.DocumentViews;
 import com.bi.views.DocumentViews.*;
 import com.bi.views.PersonViews;
 import com.bi.views.PersonViews.*;
 import com.fasterxml.jackson.annotation.JsonView;
 import io.swagger.annotations.Api;
 import io.swagger.annotations.ApiModelProperty;
 import lombok.Getter;
 import lombok.Setter;
 import org.hibernate.annotations.Where;
 import org.hibernate.envers.Audited;

 import javax.persistence.*;
 import java.io.Serializable;

 @Getter
 @Setter
 @Entity
 @Audited
 @Where(clause = "is_deleted = false")
 @Api(value="Document Details")
 @Inheritance(strategy=InheritanceType.JOINED)
 public class Document extends Auditable<AuthUser> implements Serializable{

     private static final long serialVersionUID = -6344796521016110619L;

     @Id
     @GeneratedValue(strategy = GenerationType.IDENTITY)
     @JsonView({DocumentViews.DocumentBasicView.class, PersonViews.DocumentList.class})
     private long id;

     @Column(unique = true)
     @JsonView({DocumentBasicView.class, DocumentList.class})
     private String guid;

     @JsonView({DocumentBasicView.class, DocumentList.class})
     private String documentType;

     @JsonView({DocumentBasicView.class, DocumentList.class})
     private String documentFormat;

     @JsonView({DocumentBasicView.class, DocumentList.class})
     private String documentName;

     @JsonView({DocumentBasicView.class})
     private String documentDescription;

     @JsonView({DocumentBasicView.class})
     private String fileStorageURI;

     @JsonView({DocumentBasicView.class})
     private String fileURI;

     @JsonView({DocumentBasicView.class,DocumentList.class})
     private String fileName;

     @ApiModelProperty(notes="Returns boolean value for deleted Document")
     @JsonView(DocumentBasicView.class)
     @Column(columnDefinition="boolean default false" )
     private Boolean isDeleted = false;

     @JsonView(DocumentBasicView.class)
     private String contentType;
 }
