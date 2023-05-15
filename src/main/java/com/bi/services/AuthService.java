package com.bi.services;

import java.util.List;

import com.bi.model.AuthUser;

public interface AuthService {
    boolean deleteAuthUser(List<Long> users);

	public void updateUserDomain(String userId, String domainName);

	public AuthUser updateAuthUserRole(Long id , List<String> roles);

	public AuthUser deleteAuthUserRole(Long id , List<String> roles);
	
	public AuthUser getUser(String username);
	
	public AuthUser getUser(Long userId);

}
