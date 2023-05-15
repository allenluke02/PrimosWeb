package com.bi.services;

import java.util.List;

import com.bi.exception.BIAPIException;
import com.bi.exception.BIServiceException;
import com.bi.model.Role;
import com.bi.model.User;



public interface UserService {
	
	public Iterable<User> getUsers() throws BIAPIException;
	
	public User getUser(Long userId);

	public User updateUser(Long userId, User user);

	public User deleteUser(Long userId);

	User createUser(User user, List<Role> role, boolean validateOTP) throws BIServiceException;
	
	User createUser(User user, boolean validateOTP,String... roles) throws BIServiceException;

	User createUser(User user, String... roles) throws BIServiceException;

//	User signUpUser(User user, User userInSession) throws GMServiceException;

//	User createUser(int otp, User userInSession) throws GMServiceException;

}
