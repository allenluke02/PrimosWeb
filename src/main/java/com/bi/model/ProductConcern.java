package com.bi.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotBlank;
import javax.validation.groups.Default;

import com.bi.views.FeedbackView;
import com.bi.views.ProductConcernView;
import com.fasterxml.jackson.annotation.JsonView;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Entity
@Table(name = "product_concern", uniqueConstraints = { @UniqueConstraint(columnNames = {"name"},name="fp_product_concern_name_unique")})
@Getter
@Setter
@Accessors(chain = true)
public class ProductConcern {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	@JsonView({ProductConcernView.BasicView.class,FeedbackView.BasicView.class})
    private Long id;
	
	@JsonView({ProductConcernView.BasicView.class,FeedbackView.BasicView.class})
	@NotBlank(message = "{err.not.blank}", groups = { Default.class })
	String name;
}
