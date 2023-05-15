package com.bi.model;

import java.util.Collection;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Api(value="Role of Person in Organization")
public class Role {

	private Long id;

	@ApiModelProperty(notes = "Name of Role")
	@NotBlank(message = "{err.role.name.empty}")
	@Size(min = 2, max = 15, message = "{err.role.name.length}")
	private String name;

	@ApiModelProperty(notes = "Number of Users in Role")
	@NotEmpty(message = "{err.role.users.empty}")
	private Collection<User> users;

	@ApiModelProperty(notes = "Authorities of Role")
	@NotEmpty(message = "{err.role.authorities.empty}")
	private Collection<Authority> authorities;
}
