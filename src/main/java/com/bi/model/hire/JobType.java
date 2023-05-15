package com.bi.model.hire;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.groups.Default;

import com.bi.model.Searchable;
import com.bi.views.CareerView;
import com.bi.views.JobView;
import com.fasterxml.jackson.annotation.JsonView;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Entity
@Table(name = "job_types")
@Getter
@Setter
@Accessors(chain = true)
public class JobType {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@JsonView({JobView.BasicView.class,CareerView.BasicView.class})
	private Long id;

	@JsonView({JobView.BasicView.class,CareerView.BasicView.class})
	@NotBlank(message = "{err.not.blank}", groups = { Default.class })
	@Searchable
	String name;

}
