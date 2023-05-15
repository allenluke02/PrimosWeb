package com.bi.model;

import javax.validation.constraints.NotBlank;

import org.springframework.security.core.GrantedAuthority;

import io.swagger.annotations.Api;
import lombok.Getter;
import lombok.Setter;

@Api(value = "")
@Getter
@Setter
public class Authority implements GrantedAuthority {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5739733446690737647L;

	private long id;

	@NotBlank(message = "{err.authority.name.empty}")
	private String name;

	@Override
	public String getAuthority() {
		// TODO Auto-generated method stub
		return getName();
	}
}
