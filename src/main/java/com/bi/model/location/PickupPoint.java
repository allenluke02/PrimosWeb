package com.bi.model.location;

import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ConstraintMode;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.persistence.UniqueConstraint;
import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.groups.Default;

import com.bi.model.Address;
import com.bi.model.Searchable;
import com.bi.views.CareerView;
import com.bi.views.FeedbackView;
import com.bi.views.FundRaisingView;
import com.bi.views.JobView;
import com.bi.views.PickupPointView;
import com.fasterxml.jackson.annotation.JsonView;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Entity
@Table(name = "pickup_points")
@Getter
@Setter
@Accessors(chain = true)
public class PickupPoint {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@JsonView({PickupPointView.BasicView.class,FeedbackView.BasicView.class,JobView.BasicView.class,
		CareerView.BasicView.class})
	private Long id;
	
	//ex: San Marcos, Point Loma
	@Searchable
	@JsonView({PickupPointView.BasicView.class,JobView.BasicView.class,CareerView.BasicView.class,FeedbackView.BasicView.class})
	@NotBlank(message = "{err.not.blank}", groups = { Default.class })
	String name;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "address_id", foreignKey = @ForeignKey(name = "fk_t_pickup_points_addressId_t_address_id", value = ConstraintMode.CONSTRAINT))
	@JsonView({PickupPointView.BasicView.class,JobView.BasicView.class,FeedbackView.BasicView.class,CareerView.BasicView.class})
	@NotNull(message = "{err.address.not.exists}", groups = { Default.class })
	@Valid
	@Searchable
	private Address address;
	
	@JsonView(PickupPointView.BasicView.class)
	String phone;
	
	@JsonView(PickupPointView.BasicView.class)
	@OneToMany(mappedBy = "pickupPoint",cascade = CascadeType.REFRESH,orphanRemoval=true)
    private List<PickupPointServiceProvider> pickupPtServiceProviders;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "county_id", foreignKey = @ForeignKey(name = "fk_t_pickup_points_countyIdId_t_county_id", value = ConstraintMode.CONSTRAINT))
	@JsonView(PickupPointView.BasicView.class)
	@NotNull(message = "{err.county.not.exists}", groups = { Default.class })
	//@Valid
	private County county;
	
	
	/**
	 * Can be many to many
	 * Mon-Sat 7am-9pm
	 * Sun 8am-9pm
	 */
	@ManyToMany(fetch = FetchType.LAZY,cascade = CascadeType.REFRESH)
	@JoinTable(name = "pickup_points_pickup_hours",
			uniqueConstraints = {
		            @UniqueConstraint(columnNames = {"pickup_hour_id", "pickup_points_id"}, name = "pickup_hour_id_pickup_points_id")},
			joinColumns = { 
					@JoinColumn(name = "pickup_points_id",nullable=false,
							foreignKey = @ForeignKey(name = "fk_t_pickup_hours_pickup_point_id_t_pickupPointId", value = ConstraintMode.CONSTRAINT)) }, 
			inverseJoinColumns = {@JoinColumn(name = "pickup_hour_id" ,nullable=false,
					foreignKey = @ForeignKey(name = "fk_t_pickup_hours_hour_id_t_hourId", value = ConstraintMode.CONSTRAINT))})
	@JsonView(PickupPointView.BasicView.class)
	private Set<PickupHours> pickupHours;
	
	/**
	 * seems lat lng not needed as they are calling url
	 * https://maps.google.com/maps?q=801 South Twin Oaks Rd.,San Marcos,CA 92078
	 * addressline1+City+zip
	 * adding it might be needed in marker 
	 */
	@JsonView(PickupPointView.BasicView.class)
	String lat;
	
	@JsonView(PickupPointView.BasicView.class)
	String lng;
	
	@JsonView(PickupPointView.BasicView.class)
	boolean isCantinaHrsAvlb;
	
	@JsonView(PickupPointView.BasicView.class)
	boolean isBeerWineAvlb;
	
	@JsonView(PickupPointView.BasicView.class)
	boolean outdoorDiningAvlb;
	
	@JsonView(PickupPointView.BasicView.class)
	boolean driveThruAvlb;
	
	@ElementCollection
	@CollectionTable(name="pickup_point_notes", joinColumns=@JoinColumn(name="pickup_point_id"), 
			uniqueConstraints = {@UniqueConstraint(columnNames = { "pickup_point_id", "special_notes" }, name = "pickup_point_id_special_notes") })
	@JsonView(PickupPointView.BasicView.class)
	@Column(name="special_notes", unique = true,nullable=false)
	public List<@NotBlank(message = "{err.not.blank}", groups = { Default.class }) String> specialNotes;
	
	@JsonView(PickupPointView.BasicView.class)
	String cakeUrl;
	
	@JsonView(PickupPointView.BasicView.class)
	boolean active;

	@Transient
	@JsonView(PickupPointView.BasicView.class)
	Double distance;
	
	
	@JsonView(PickupPointView.BasicView.class) 
	boolean indoorDiningAvlb;
	 
	@Searchable
	@NotNull(message = "{err.email.empty}", groups = { Default.class })
	@NotBlank(message = "{err.not.blank}", groups = { Default.class })
	@JsonView({PickupPointView.BasicView.class,JobView.BasicView.class})
	String email;
	
}
