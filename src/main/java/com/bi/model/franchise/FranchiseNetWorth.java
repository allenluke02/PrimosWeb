package com.bi.model.franchise;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import com.bi.model.Searchable;
import com.bi.views.FranchiseView;
import com.fasterxml.jackson.annotation.JsonView;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Entity
@Table(name = "franchise_net_worth", uniqueConstraints = { @UniqueConstraint(columnNames = {"title"},
name="franchise_net_worth_assets_title_unique")})
@Getter
@Setter
@Accessors(chain = true)
public class FranchiseNetWorth {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@JsonView(FranchiseView.BasicView.class)
	private Long id;
	
	@JsonView(FranchiseView.BasicView.class)
	@Searchable
	String title;

}
