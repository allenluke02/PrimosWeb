package com.bi.model.catering;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.bi.views.CateringView;
import com.fasterxml.jackson.annotation.JsonView;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Entity
@Table(name = "catering_types")
@Getter
@Setter
@Accessors(chain = true)
public class CateringType {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@JsonView(CateringView.BasicView.class)
	private Long id;
	
	@JsonView(CateringView.BasicView.class)
	String name;
	
	String description;
}
