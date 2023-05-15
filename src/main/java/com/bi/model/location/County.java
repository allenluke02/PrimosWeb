package com.bi.model.location;

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
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.groups.Default;

import com.bi.model.State;
import com.bi.views.CareerView;
import com.bi.views.CountyView;
import com.bi.views.PickupPointView;
import com.fasterxml.jackson.annotation.JsonView;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Entity
@Table(name = "county", uniqueConstraints = { @UniqueConstraint(columnNames = {"name","state_id"},name="fp_county_name_state_id_unique")})
@Getter
@Setter
@Accessors(chain = true)
public class County {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@JsonView({PickupPointView.BasicView.class,CareerView.BasicView.class,CountyView.BasicView.class})
	private Long id;
	
	//ex: SAN DIEGO COUNTY, INLAND EMPIRE
	@JsonView({PickupPointView.BasicView.class,CountyView.BasicView.class,CareerView.BasicView.class})
	@NotBlank(message = "{err.not.blank}", groups = { Default.class })
	String name;
	
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "state_id", foreignKey = @ForeignKey(name = "fk_t_county_stateId_t_state_id", value = ConstraintMode.CONSTRAINT))
	@NotNull(message = "{err.state.not.exists}", groups = { Default.class })
	//@Valid
	private State state;
}
