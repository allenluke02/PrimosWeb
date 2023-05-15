package com.bi.model.catering;

import java.util.Date;

import javax.persistence.ConstraintMode;
import javax.persistence.Entity;
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
import javax.persistence.Transient;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.groups.Default;

import com.bi.model.Searchable;
import com.bi.model.State;
import com.bi.views.CateringView;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonView;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Entity
@Table(name = "caterings")
@Getter
@Setter
@Accessors(chain = true)
public class Catering {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@JsonView(CateringView.BasicView.class)
	private Long id;

	@Searchable
	@NotBlank(message = "{err.first.name.blank}", groups = { Default.class })
	@JsonView(CateringView.BasicView.class)
	String firstName;

	@NotBlank(message = "{err.last.name.blank}", groups = { Default.class })
	@JsonView(CateringView.BasicView.class)
	String lastName;

	@NotNull(message = "{err.email.empty}", groups = { Default.class })
	@JsonView(CateringView.BasicView.class)
	String email;

	@NotNull(message = "{err.phone.empty}", groups = { Default.class })
	@JsonView(CateringView.BasicView.class)
	String phone;

	@Temporal(TemporalType.TIMESTAMP)
	@JsonView({ CateringView.BasicView.class })
	private Date eventDate;

	@JsonView(CateringView.BasicView.class)
	String note;

	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "catering_type_id", foreignKey = @ForeignKey(name = "fk_t_caterings_cateringTypeId_t_catering_type_id", value = ConstraintMode.CONSTRAINT))
	@JsonView(CateringView.BasicView.class)
	CateringType cateringType;

	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "tortilla_type_id", foreignKey = @ForeignKey(name = "fk_t_caterings_tortillaTypeId_t_tortilla_type_id", value = ConstraintMode.CONSTRAINT))
	@JsonView(CateringView.BasicView.class)
	TortillaType tortillaType;

	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "beans_type_id", foreignKey = @ForeignKey(name = "fk_t_caterings_beansTypeId_t_beans_type_id", value = ConstraintMode.CONSTRAINT))
	@JsonView(CateringView.BasicView.class)
	BeansType beansType;

	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "catering_price_id", foreignKey = @ForeignKey(name = "fk_t_caterings_cateringPriceId_t_catering_price_id", value = ConstraintMode.CONSTRAINT))
	@JsonView(CateringView.BasicView.class)
	CateringPrice cateringPrice;

	@JsonView(CateringView.BasicView.class)
	@NotBlank(message = "{err.mailing.address.line1.blank}", groups = { Default.class })
	String mailingAddressLine1;

	@JsonView(CateringView.BasicView.class)
	String mailingAddressLine2;

	@JsonView(CateringView.BasicView.class)
	@NotBlank(message = "{err.city.blank}", groups = { Default.class })
	String city;

	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "state_id", foreignKey = @ForeignKey(name = "fk_t_catering_stateId_t_state_id", value = ConstraintMode.CONSTRAINT))
	@JsonView(CateringView.BasicView.class)
	// @NotNull(message = "{err.state.not.exists}", groups = { Default.class })
	private State state;

	@NotBlank(message = "{err.zip.blank}", groups = { Default.class })
	@JsonView(CateringView.BasicView.class)
	String zip;

	@JsonView(CateringView.BasicView.class)
	int noOfPeople;

	@Transient
	String reCaptcha;
	
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "choice_first_id", foreignKey = @ForeignKey(name = "fk_t_caterings_choiceFirstId_t_choice_first_id", value = ConstraintMode.CONSTRAINT))
	@JsonView(CateringView.BasicView.class)
	Choice firstChoice;
	

	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "choice_second_id", foreignKey = @ForeignKey(name = "fk_t_caterings_choiceSecondId_t_choice_second_id", value = ConstraintMode.CONSTRAINT))
	@JsonView(CateringView.BasicView.class)
	Choice secondChoice;
	
	@JsonProperty("totalPrice")
	@JsonView(CateringView.BasicView.class)
	public Double getTotalPrice() {
		Double price=noOfPeople * cateringPrice.getPrice();
		return cateringPrice != null
				? (double) Math.round(price+ price * (cateringPrice.getServiceFee() / 100))
				: 0.0;
	}

}
