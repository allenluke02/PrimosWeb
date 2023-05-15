package com.bi.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import javax.validation.groups.Default;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Entity
@Table(name = "contact_us")
@Getter
@Setter
@Accessors(chain = true)
public class ContactUs {
	
	@Searchable
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Searchable
	@NotBlank(message = "{err.not.blank}", groups = { Default.class })
	String name;
	
	@Searchable
	@Email(message = "{err.invalid.email}", groups = { Default.class })
	@NotBlank(message = "{err.not.blank}", groups = { Default.class })
	String email;
	
	@Searchable
	String phone;
	
	@Searchable
	@Size(max=255,message="{err.contactus.message.length}")
	String message;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date createdDate  = new Date();

}
