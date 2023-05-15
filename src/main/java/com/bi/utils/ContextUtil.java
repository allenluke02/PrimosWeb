package com.bi.utils;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import com.bi.exception.EntityNotFoundException;
import com.bi.exception.ForbiddenException;
import com.bi.model.QUser;
import com.bi.model.User;
import com.bi.repositories.UserRepository;
import com.bi.services.MessageByLocaleService;

@Component
public class ContextUtil {
	
	@Autowired
	private MessageByLocaleService messageByLocaleService;

	@Autowired
	private UserRepository userRepository;

	public Optional<Long> getUserId(){
		Long id = null;
		String userId = SecurityContextHolder.getContext().getAuthentication().getName();
		if (userId != null) {
			try {
				id=Long.parseLong(userId);

			} catch (NumberFormatException e) {

			}
		}
		return Optional.ofNullable(id);
	}
	

	public Long getLoggedInUserId() throws ForbiddenException {
		return getUserId().orElseThrow(() -> new ForbiddenException(messageByLocaleService.getMessage("err.user.not.loggedin")));
	}
	
	
	

	public User getLoggedInUser() throws ForbiddenException {
		Long id = getLoggedInUserId();
		Optional<User>  user=userRepository.findOne(QUser.user.id.eq(id));
		if(!user.isPresent()) {
			throw new ForbiddenException(messageByLocaleService.getMessage("err.user.not.loggedin"));
		}
		return user.get();
	
	}


	public boolean isValidLoggedInUser() {
		// If Auth Token present then user must be valid and present in DB
		Optional<Long> userId = getUserId();
		if (userId.isPresent()) {
			if (!userRepository.exists(QUser.user.id.eq(userId.get()))) {
				return false;
			}
		}
		return true;
	}


}