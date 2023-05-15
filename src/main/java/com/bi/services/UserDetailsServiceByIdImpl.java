package com.bi.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bi.model.AuthUser;

import io.swagger.annotations.Api;

@Service
@Api(value="CRUD Operation for UserService")
@Qualifier("UserDetailsServiceByIdImpl")
public class UserDetailsServiceByIdImpl implements UserDetailsService {

	 @Autowired
	    private AuthService authService;

	    @Override
	    @Transactional(readOnly = true)
	    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
	    AuthUser user = authService.getUser(Long.parseLong(username));

	        if (user == null) {
	        	throw new UsernameNotFoundException(username);
	        }
	        return user;
	        
	    }
}