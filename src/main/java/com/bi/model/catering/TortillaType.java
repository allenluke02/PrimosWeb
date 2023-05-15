package com.bi.model.catering;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.ConstraintMode;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;
import javax.validation.groups.Default;

import com.bi.model.Searchable;
import com.bi.views.CateringView;
import com.bi.views.JobView;
import com.fasterxml.jackson.annotation.JsonView;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Entity
@Table(name = "tortilla_types")
@Getter
@Setter
@Accessors(chain = true)

public class TortillaType {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@JsonView(CateringView.BasicView.class)
	private Long id;
	
	@JsonView(CateringView.BasicView.class)
	String name;
	
	@JsonView(CateringView.BasicView.class)
	String description;
	
	@ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.REFRESH)
	@JoinTable(name = "tortilla_type_catering_types", 
			uniqueConstraints = {
		            @UniqueConstraint(columnNames = {"tortilla_type_id", "catering_type_id"}, name = "tortilla_type_id_catering_type_id")},
			joinColumns = {	@JoinColumn(name = "tortilla_type_id",nullable=false,foreignKey = @ForeignKey(name = "fk_t_tortilla_type_tortilla_type_id_t_tortillaTypeId", value = ConstraintMode.CONSTRAINT)) }, 
			inverseJoinColumns = {@JoinColumn(name = "catering_type_id",nullable=false,foreignKey = @ForeignKey(name = "fk_t_tortilla_type_catering_type_id_t_cateringTypeId", value = ConstraintMode.CONSTRAINT))  })
	@JsonView(JobView.BasicView.class)
	@NotNull(message = "{err.catering.type.not.exists}", groups = { Default.class })
	@Searchable
	private Set<CateringType> cateringTypes;


}
