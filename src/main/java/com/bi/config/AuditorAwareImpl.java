package com.bi.config;

import java.util.Optional;

import org.springframework.data.domain.AuditorAware;
import org.springframework.security.core.context.SecurityContextHolder;

import com.bi.model.User;


public class AuditorAwareImpl implements AuditorAware<User> {

    @Override
    public Optional<User> getCurrentAuditor() {
    	User u = null;
    	String userId = SecurityContextHolder.getContext().getAuthentication().getName();
    	if(userId != null) {
    		try {
    			long userIdL = Long.parseLong(userId);
    			u = new User();
    			u.setId(userIdL);
    		} catch (NumberFormatException e) {
    			
    		}
    	}
    	return Optional.ofNullable(u);
    }
}