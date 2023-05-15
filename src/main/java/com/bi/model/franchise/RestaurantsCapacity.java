package com.bi.model.franchise;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import com.bi.views.FranchiseView;
import com.fasterxml.jackson.annotation.JsonView;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Entity
@Table(name = "restaurants_capacity", uniqueConstraints = { @UniqueConstraint(columnNames = {"title"},
name="restaurants_capacity_title_unique")})
@Getter
@Setter
@Accessors(chain = true)
public class RestaurantsCapacity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@JsonView(FranchiseView.BasicView.class)
	private Long id;
	
	//ex: 3 to 6, 7 to 11
	@JsonView(FranchiseView.BasicView.class)
	String title;
	
	@JsonView(FranchiseView.BasicView.class)
	String description;

}
