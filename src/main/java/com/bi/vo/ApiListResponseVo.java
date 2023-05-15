package com.bi.vo;

import java.util.List;

import com.bi.views.ApiListResponseView;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonView;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@JsonIgnoreProperties(ignoreUnknown = true)
@Accessors(chain = true)
public class ApiListResponseVo<T> {
	
	@JsonView(ApiListResponseView.BasicView.class)
	List<T> data;
	
	@JsonView(ApiListResponseView.BasicView.class)
	Boolean success;
	
	@JsonView(ApiListResponseView.BasicView.class)
	Long total;
	

}
