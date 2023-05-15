package com.bi.model.payment;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.ConstraintMode;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.bi.model.User;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * DTO only to store reference might not be needed later ,will be handled from BI Payment Module 
 */
@Entity
@Table(name = "payments")
@Getter
@Setter
@Accessors(chain = true)
public class Payment {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@Enumerated(EnumType.STRING)
	PaymentType paymentType;
	
	private BigDecimal amount;

	private String currency;
	
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id", foreignKey = @ForeignKey(name = "fk_t_payment_userId_t_user_id", value = ConstraintMode.CONSTRAINT))
	User user;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "created_on")
	Date createdOn;
	
}
