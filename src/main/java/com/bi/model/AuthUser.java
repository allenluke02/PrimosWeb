package com.bi.model;

import java.io.Serializable;
import java.util.Collection;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.bi.views.UserViews.UserPasswordResetView;
import com.bi.views.UserViews.UserRegisterView;
import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Getter;
import lombok.Setter;

@Getter@Setter
@Scope(value="session", proxyMode=ScopedProxyMode.TARGET_CLASS)
public class AuthUser implements UserDetails, Serializable {

	private static final long serialVersionUID = 7823628616613823732L;
    private Long id;
	
	@NotEmpty (message = "{err.user.phone.empty}",groups= {UserPasswordResetView.class})
	private String loginName;
	
	@Getter(onMethod = @__( @JsonIgnore ))
	@NotEmpty (message = "{err.user.password.empty}", groups= {UserRegisterView.class,UserPasswordResetView.class})
    private String password; 

	@Getter(onMethod = @__( @JsonIgnore ))
    private boolean accountExpired;

	@Getter(onMethod = @__( @JsonIgnore ))
    private boolean accountLocked;

	@Getter(onMethod = @__( @JsonIgnore ))
    private boolean credentialsExpired;
	
    private boolean enabled= true;
    
    @Getter(onMethod =@__(@JsonIgnore))
    @Setter(onMethod = @__( @JsonIgnore ))
    private boolean isDeleted;
    
    @Getter(onMethod =@__(@JsonIgnore))
    @NotBlank(message="{err.otp.otp.empty}",groups= {UserRegisterView.class,UserPasswordResetView.class})
    private String otp;    
    
    private long otpGeneratedTime;
   
    private Collection<Role> roles;

    @Getter(onMethod = @__(@JsonIgnore))
    private Collection<? extends GrantedAuthority> authorities;
    
	@NotNull
	private boolean isTermsPolicyAgreed;

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return String.valueOf(id);
	}

	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return !isAccountExpired();
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return !isAccountLocked();      
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return !isCredentialsExpired();
	}
}
