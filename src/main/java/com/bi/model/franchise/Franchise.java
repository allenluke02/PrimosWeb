package com.bi.model.franchise;

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
import javax.validation.constraints.Size;
import javax.validation.groups.Default;

import com.bi.model.Country;
import com.bi.model.Searchable;
import com.bi.model.State;
import com.bi.views.FranchiseView;
import com.bi.views.FundRaisingView;
import com.fasterxml.jackson.annotation.JsonView;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Entity
@Table(name = "franchises")
@Getter
@Setter
@Accessors(chain = true)
public class Franchise {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@JsonView(FranchiseView.BasicView.class)
	private Long id;
	
	@Searchable
	@NotBlank(message = "{err.first.name.blank}", groups = { Default.class })
	@JsonView(FranchiseView.BasicView.class)
	String firstName;

	@NotBlank(message = "{err.last.name.blank}", groups = { Default.class })
	@JsonView(FranchiseView.BasicView.class)
	String lastName;
	
	@Searchable
	@JsonView(FranchiseView.BasicView.class)
	String company;
	
	@JsonView(FranchiseView.BasicView.class)
	@NotBlank(message = "{err.mailing.address.line1.blank}", groups = { Default.class })
	String mailingAddressLine1;
	
	@JsonView(FranchiseView.BasicView.class)
	String mailingAddressLine2;
	
	@JsonView(FranchiseView.BasicView.class)
	@NotBlank(message = "{err.city.blank}", groups = { Default.class })
	String city;
	
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "state_id", foreignKey = @ForeignKey(name = "fk_t_franchise_stateId_t_state_id", value = ConstraintMode.CONSTRAINT))
	@JsonView(FranchiseView.BasicView.class)
	//@NotNull(message = "{err.state.not.exists}", groups = { Default.class })
	private State state;
	
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "franchise_info_id", foreignKey = @ForeignKey(name = "fk_t_franchise_infoId_t_info_id", value = ConstraintMode.CONSTRAINT))
	@JsonView(FranchiseView.BasicView.class)
	@NotNull(message = "{err.franchiseInfo.not.exists}", groups = { Default.class })
	private FranchiseInfo franchiseInfo;
	
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "franchise_liquid_assets_id", foreignKey = @ForeignKey(name = "fk_t_franchise_liquidAssetsId_t_liquid_assets_id", value = ConstraintMode.CONSTRAINT))
	@JsonView(FranchiseView.BasicView.class)
	@NotNull(message = "{err.franchiseLiquidAsset.not.exists}", groups = { Default.class })
	@Searchable
	private FranchiseLiquidAsset franchiseLiquidAsset;
	
	
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "franchise_net_worth_id", foreignKey = @ForeignKey(name = "fk_t_franchise_netWorthId_t_net_worth_id", value = ConstraintMode.CONSTRAINT))
	@JsonView(FranchiseView.BasicView.class)
	@NotNull(message = "{err.franchiseNetWorth.not.exists}", groups = { Default.class })
	@Searchable
	private FranchiseNetWorth franchiseNetWorth;
	
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "restaurants_capacity_id", foreignKey = @ForeignKey(name = "fk_t_franchise_restaurantsCapacityId_t_restaurants_capacity_id", value = ConstraintMode.CONSTRAINT))
	@JsonView(FranchiseView.BasicView.class)
	@NotNull(message = "{err.restaurantsCapacity.not.exists}", groups = { Default.class })
	private RestaurantsCapacity restaurantsCapacity;
	
	/**
	 * although seems same Country but differs in Pollo loco ex: US , Outside US
	 */
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "country_to_develop_id", foreignKey = @ForeignKey(name = "fk_t_franchise_countryToDevelopId_t_country_to_develop_id", value = ConstraintMode.CONSTRAINT))
	@JsonView(FranchiseView.BasicView.class)
	@NotNull(message = "{err.countryToDevelop.not.exists}", groups = { Default.class })
	private Country countryToDevelop;
	
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "state_to_develop_id", foreignKey = @ForeignKey(name = "fk_t_franchise_stateToDevelopId_t_state_to_develop_id", value = ConstraintMode.CONSTRAINT))
	@JsonView(FranchiseView.BasicView.class)
	@NotNull(message = "{err.stateToDevelop.not.exists}", groups = { Default.class })
	private State stateToDevelop;
	
	@NotBlank(message = "{err.zip.blank}", groups = { Default.class })
	@JsonView(FranchiseView.BasicView.class)
	String zip;
	
	@Searchable
	@NotNull(message = "{err.email.empty}", groups = { Default.class })
	@JsonView(FranchiseView.BasicView.class)
	String email;
	
	@Searchable
	@NotNull(message = "{err.phone.empty}", groups = { Default.class })
	@JsonView(FranchiseView.BasicView.class)
	String officePhone;
	
	@JsonView(FranchiseView.BasicView.class)
	boolean hasEquityPartner;
	
	@JsonView(FranchiseView.BasicView.class)
	boolean franchisseWithOtherBrand;
	
	@JsonView(FranchiseView.BasicView.class)
	boolean hasOperationalPartner;
	
	@JsonView(FranchiseView.BasicView.class)
	boolean hasOperationalPartnerInArea;
	
	@JsonView(FranchiseView.BasicView.class)
	boolean hasSecuredFinance;
	
	@JsonView(FranchiseView.BasicView.class)
	@Size(max=255,message="{err.franchise.additionalcomments.length}")
	String additionalComments;
	
	@Transient
	String reCaptcha;
	
	@JsonView(FranchiseView.BasicView.class)
	@Temporal(TemporalType.TIMESTAMP)
	private Date createdDate = new Date();
	
}
