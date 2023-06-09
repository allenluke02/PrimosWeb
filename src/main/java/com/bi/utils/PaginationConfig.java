package com.bi.utils;

import org.springframework.data.domain.Sort.Direction;

import lombok.Getter;

@Getter
public class PaginationConfig {
	private Integer pageSize; 
	private Integer pageNumber; 
	private String sortDirection;
	private String sortOn;
	
	public PaginationConfig(Integer pageSize, Integer pageNumber, String sortDirection, String sortOn) {
		super();
		this.pageSize = pageSize == null  ? 10 : pageSize;
		this.pageNumber = pageNumber == null ? 0 : pageNumber;
		if(sortDirection == null || sortOn == null || sortDirection.isEmpty() || sortOn.isEmpty()) {
			this.sortDirection = null;
			this.sortOn = null;
		}else {
			this.sortDirection=sortDirection;
			this.sortOn=sortOn;
		}
	}
	
	public Direction getSpringDataSortDirection(){
		if(sortDirection.equalsIgnoreCase(BIConstant.ASC)){
			return Direction.ASC;
		}
		else if(sortDirection.equalsIgnoreCase(BIConstant.DESC)){
			return Direction.DESC;
		}
		else{
			return Direction.ASC;
		}
	}
}
