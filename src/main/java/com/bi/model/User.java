package com.bi.model;

import java.io.Serializable;
import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.ConstraintMode;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.annotations.Where;
import org.hibernate.envers.Audited;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.bi.views.UserViews.UserPasswordResetView;
import com.bi.views.UserViews.UserRegisterView;
import com.fasterxml.jackson.annotation.JsonIgnore;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Entity
//@Table(name = "users")
@Getter
@Setter
@EqualsAndHashCode(of = "id")
@Scope(value = "session", proxyMode = ScopedProxyMode.TARGET_CLASS)
@Api(value = "User Details")
@ApiModel(value = "name", description = "desc", reference = "ref")
@Table(name = "users", uniqueConstraints = {
		@UniqueConstraint(columnNames = "loginName", name = "user_loginName_unique") })

@Audited
@Where(clause = "is_deleted = false")
public class User implements Serializable {

	private static final long serialVersionUID = 7823628616613823732L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;

	@NotEmpty(message = "{err.user.phone.empty}", groups = { UserPasswordResetView.class })
	private String loginName;

	@NotEmpty(message = "{err.user.phone.empty}", groups = { UserRegisterView.class })
	@Size(min = 10, max = 10, message = "{err.user.phone.length}", groups = { UserRegisterView.class })
	// TODO digit validation
	private String phone;

	@NotNull
	private boolean isTermsPolicyAgreed;

	private String firstName;

	private String middleName;

	private String lastName;

	private String email;
}
