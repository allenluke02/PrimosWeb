package com.bi.services;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bi.model.AuthUser;
import com.bi.model.Authority;
import com.bi.model.Role;

import io.swagger.annotations.Api;

@Service
@Api(value="CRUD Operation for UserService")
@Qualifier("UserDetailsServiceImpl")
public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	private AuthService authService;
	
    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    	AuthUser user = authService.getUser(username);
        if (user == null) {
        	throw new UsernameNotFoundException(username);
        }
        user.setAuthorities(getGrantedAuthorities(user.getRoles()));
        return user;
        
    }
    
    private List<? extends GrantedAuthority> getGrantedAuthorities(Collection<Role> roles) {
    	  
        List<Authority> authorities = new ArrayList<>();
        for (Role role : roles) {
            authorities.addAll(role.getAuthorities());
        }
        return authorities;
    }
    
}