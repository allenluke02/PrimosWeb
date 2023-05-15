package com.bi.vo;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class GoogleMapResponseVo {

	@JsonProperty("destination_addresses")
    private List<String> destinationAddresses;
	
    @JsonProperty("origin_addresses")
    private List<String> originAddresses ;
    
    @JsonProperty("rows")
    private List<Row> rows ;
    
    @JsonProperty("status")
    private String status;
	
	@Getter
	@Setter
	public static class Row {
		 @JsonProperty("elements")
		    private List<Element> elements;
	}
	
	@Getter
	@Setter
	public static class Element {
		 @JsonProperty("distance")
		    private Distance distance;
		    @JsonProperty("duration")
		    private Duration duration;
		    @JsonProperty("status")
		    private String status;
	}
	
	@Getter
	@Setter
	public static class Duration {
		@JsonProperty("text")
	    private String text;
	    @JsonProperty("value")
	    private Integer value;
	}
	
	@Getter
	@Setter
	public static class Distance {
		@JsonProperty("text")
	    private String text;
	    @JsonProperty("value")
	    private Integer value;
	}
	
}
