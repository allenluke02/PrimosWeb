package com.bi.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.ConstraintMode;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.groups.Default;

import com.bi.views.CareerView;
import com.bi.views.FeedbackView;
import com.bi.views.JobView;
import com.bi.views.PickupPointView;
import com.fasterxml.jackson.annotation.JsonView;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Entity
@Table(name = "address")
@Getter
@Setter
@Accessors(chain = true)
public class Address implements Serializable {

    private static final long serialVersionUID = 7823628616613823732L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonView({PickupPointView.BasicView.class,JobView.BasicView.class,FeedbackView.BasicView.class,CareerView.BasicView.class})
    private Long id;

    @NotNull(message = "{err.address1.empty}", groups = { Default.class })
    @JsonView({PickupPointView.BasicView.class,JobView.BasicView.class,FeedbackView.BasicView.class,CareerView.BasicView.class})
    @Searchable
    private String address1;
    
    @JsonView({PickupPointView.BasicView.class,JobView.BasicView.class,FeedbackView.BasicView.class,CareerView.BasicView.class})
    @Searchable
    private String address2;

    @NotNull(message = "{err.city.empty}", groups = { Default.class })
    @JsonView({PickupPointView.BasicView.class,JobView.BasicView.class,FeedbackView.BasicView.class,CareerView.BasicView.class})
    @Searchable
    private String city;

    @OneToOne
	@JoinColumn(name = "state_id", foreignKey = @ForeignKey(name = "fk_t_address_stateId_t_state_id", value = ConstraintMode.CONSTRAINT))
    @NotNull(message = "{err.state.empty}", groups = { Default.class })
    @JsonView({PickupPointView.BasicView.class,JobView.BasicView.class,FeedbackView.BasicView.class,CareerView.BasicView.class})
    @Searchable
	private State state;

    @NotNull(message = "{err.zip.empty}", groups = { Default.class })
    @JsonView({PickupPointView.BasicView.class,JobView.BasicView.class,FeedbackView.BasicView.class,CareerView.BasicView.class})
    @Searchable
    private String zip;
    
    @OneToOne
	@JoinColumn(name = "country_id", foreignKey = @ForeignKey(name = "fk_t_address_countryId_t_country_id", value = ConstraintMode.CONSTRAINT))
	@NotNull(message = "{err.country.empty}", groups = { Default.class })
    @JsonView({PickupPointView.BasicView.class,JobView.BasicView.class,FeedbackView.BasicView.class})
	private Country country;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "created_on")
    @JsonView({PickupPointView.BasicView.class,JobView.BasicView.class})
    Date createdOn;
}
