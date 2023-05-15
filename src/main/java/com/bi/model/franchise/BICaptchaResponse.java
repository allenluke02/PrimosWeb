package com.bi.model.franchise;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class BICaptchaResponse {
	
    private boolean success;
     
    @JsonProperty("error-codes")
    private List<String> errorCodes;

}
