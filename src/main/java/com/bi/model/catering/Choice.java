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

import com.bi.views.CateringView;
import com.bi.views.PickupPointView;
import com.fasterxml.jackson.annotation.JsonView;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Entity
@Table(name = "choices")
@Getter
@Setter
@Accessors(chain = true)
public class Choice {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@JsonView(CateringView.BasicView.class)
	private Long id;
		
	@JsonView(CateringView.BasicView.class)
	String name;
	
	String description;
	
	@ManyToMany(fetch = FetchType.LAZY,cascade = CascadeType.REFRESH)
	@JoinTable(name = "choice_catering_types",
			uniqueConstraints = {
		            @UniqueConstraint(columnNames = {"catering_type_id", "choice_id"}, name = "catering_type_id_choice_id")},
			joinColumns = { 
					@JoinColumn(name = "choice_id",nullable=false,
							foreignKey = @ForeignKey(name = "fk_t_choice_catering_type_t_choiceId", value = ConstraintMode.CONSTRAINT)) }, 
			inverseJoinColumns = {@JoinColumn(name = "catering_type_id" ,nullable=false,
					foreignKey = @ForeignKey(name = "fk_t_catering_type_choice_t_cateringTypeId", value = ConstraintMode.CONSTRAINT))})
	@JsonView(PickupPointView.BasicView.class)
	private Set<CateringType> cateringTypes;

}
