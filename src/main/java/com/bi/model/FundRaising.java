package com.bi.model;

import java.util.Date;

import javax.persistence.ConstraintMode;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.validation.groups.Default;

import com.bi.model.location.PickupPoint;
import com.bi.views.FundRaisingView;
import com.fasterxml.jackson.annotation.JsonView;
import com.querydsl.core.annotations.QueryInit;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Entity
@Table(name = "fund_raising")
@Getter
@Setter
@Accessors(chain = true)
public class FundRaising {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonView(FundRaisingView.BasicView.class)
    private Long id;

    @Searchable
    @JsonView(FundRaisingView.BasicView.class)
    @NotNull(message = "{err.first.name.empty}", groups = { Default.class })
    String firstName;

    @JsonView(FundRaisingView.BasicView.class)
    @NotNull(message = "{err.last.name.empty}", groups = { Default.class })
    String lastName;

    @Searchable
    @JsonView(FundRaisingView.BasicView.class)
    @NotNull(message = "{err.phone.empty}", groups = { Default.class })
    String phone;

    @Searchable
    @JsonView(FundRaisingView.BasicView.class)
    @NotNull(message = "{err.email.empty}", groups = { Default.class })
    String email;

    @Searchable
    @NotNull(message = "{err.fundRaising.date.empty}", groups = { Default.class })
    @Temporal(TemporalType.TIMESTAMP)
    @JsonView(FundRaisingView.BasicView.class)
    private Date date;
    
    @Searchable
    @NotNull(message = "{err.fundRaising.date.empty}", groups = { Default.class })
    @Temporal(TemporalType.TIMESTAMP)
    @JsonView(FundRaisingView.BasicView.class)
    private Date endDate;

    @Searchable
    @JsonView(FundRaisingView.BasicView.class)
    @NotNull(message = "{err.organization.cause.empty}", groups = { Default.class })
    String organizationCause;

    @JsonView(FundRaisingView.BasicView.class)
    String teamName;

    @ManyToOne(optional = false)
    @JoinColumn(name = "store_location_id", foreignKey = @ForeignKey(name = "fk_t_fundRaising_fundRaisingId_t_pickup_point_id", value = ConstraintMode.CONSTRAINT))
    @JsonView(FundRaisingView.BasicView.class)
    @NotNull(message = "{err.store.location.empty}", groups = { Default.class })
    @QueryInit("address.state")
    private PickupPoint storeLocation;

    @JsonView(FundRaisingView.BasicView.class)
    @Size(max=255,message="{err.comments.length}")
    String comment;

    @Searchable
    @JsonView(FundRaisingView.BasicView.class)
    String checkPayableTo;

    @JsonView(FundRaisingView.BasicView.class)
    String mailingAddressLine1;

    @JsonView(FundRaisingView.BasicView.class)
    String mailingAddressLine2;

    @JsonView(FundRaisingView.BasicView.class)
    private String city;

    @ManyToOne
    @JoinColumn(name = "state_id", foreignKey = @ForeignKey(name = "fk_t_foundRaising_stateId_t_state_id", value = ConstraintMode.CONSTRAINT))
    @JsonView(FundRaisingView.BasicView.class)
    private State state;

    @JsonView(FundRaisingView.BasicView.class)
    String zip;
    
    @JsonView(FundRaisingView.BasicView.class)
	@Temporal(TemporalType.TIMESTAMP)
	private Date createdDate  = new Date();
    
    @JsonView(FundRaisingView.BasicView.class)
    private Boolean isApproved=Boolean.FALSE;

}
