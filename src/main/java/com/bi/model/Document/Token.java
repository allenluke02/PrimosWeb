package com.bi.model.Document;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Getter
@Setter
public class Token implements Serializable{
	
	private static final long serialVersionUID = 4474422767022392646L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
//	@JsonView({SharePrescriptionDocView.class,OrderConfirmationOTPView.class})
	private String token;
	@Enumerated(EnumType.STRING)
	private TokenType tokenType;
	@Temporal(TemporalType.TIMESTAMP)
	private Date issuedDate;
	@Column(name="expiryms")
//	@JsonView(SharePrescriptionDocView.class)
	private long expiryMS;
	private String tokenFor;
}
