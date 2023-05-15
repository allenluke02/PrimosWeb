package com.bi.services;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.provider.authentication.OAuth2AuthenticationDetails;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.bi.config.ConfigurationService;
import com.bi.model.AuthUser;
import com.bi.model.ConfigurationKeyImpl;
import com.bi.vo.UserVo;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class AuthServiceImpl implements AuthService {
    private static final Logger LOGGER = LoggerFactory.getLogger(AuthServiceImpl.class);

    private final static String AUTH_USER_ADD_URI = "/oauth/users/add";
    //private final static String AUTH_USER_UPDATE_URI = "/oauth/user/edit";
    private final static String AUTH_USERS_DELETE_URI = "/oauth/users/delete";
    
    private final static String AUTH_USER_DOMAIN_UPDATE_URI = "/oauth/users/{userId}/domains";

    private final static String GET_AUTH_USER = "/oauth/users/{userId}";
    
    private final static String GET_AUTH_USER_NAME = "/oauth/users/{userName}";
    
    private final static String UPDATE_AUTH_USER_ROLE = "/oauth/users/{userId}/roles";
    		
    private final static String DELETE_AUTH_USER_ROLE = "/oauth/users/{userId}/roles";
   
    @Autowired
    private ConfigurationService configurationService;


    @Override
   	public AuthUser getUser(String username){
       	RestTemplate restTemplate = new RestTemplate();
           HttpHeaders headers = new HttpHeaders();
           String uri = prepareAuthUri(GET_AUTH_USER_NAME.replace("{userName}", String.valueOf(username)));
           headers.setContentType(MediaType.APPLICATION_JSON);
           Authentication security = SecurityContextHolder.getContext().getAuthentication();
           if (null != security) {
               Object auth = security.getDetails();
               if (auth instanceof OAuth2AuthenticationDetails) {
                   LOGGER.info("============ bearer token for get user by name === {}",((OAuth2AuthenticationDetails) auth).getTokenValue());
                   headers.add("Authorization","Bearer "+((OAuth2AuthenticationDetails) auth).getTokenValue());
               }
           }
           HttpEntity<String> requestEntity = new HttpEntity<>(username,headers);
           ResponseEntity<AuthUser> responseEntity = restTemplate.exchange(uri, HttpMethod.GET, requestEntity, AuthUser.class);
   		return responseEntity.getBody();
   		
   		
   	}
    
    @Override
   	public AuthUser getUser(Long userId){
    	//TODO: implement userid end point that would be exposed in auth.
       	RestTemplate restTemplate = new RestTemplate();
           HttpHeaders headers = new HttpHeaders();
           String uri = prepareAuthUri(GET_AUTH_USER_NAME.replace("{userName}", String.valueOf(userId)));
           headers.setContentType(MediaType.APPLICATION_JSON);
           Authentication security = SecurityContextHolder.getContext().getAuthentication();
           if (null != security) {
               Object auth = security.getDetails();
               if (auth instanceof OAuth2AuthenticationDetails) {
                   LOGGER.info("============ bearer token for get user by name === {}",((OAuth2AuthenticationDetails) auth).getTokenValue());
                   headers.add("Authorization","Bearer "+((OAuth2AuthenticationDetails) auth).getTokenValue());
               }
           }
           HttpEntity<Long> requestEntity = new HttpEntity<>(userId,headers);
           ResponseEntity<AuthUser> responseEntity = restTemplate.exchange(uri, HttpMethod.GET, requestEntity, AuthUser.class);
   		return responseEntity.getBody();
   		
   		
   	}
    
    public AuthUser updateAuthUserRole(Long id , List<String> roles) {
    	
    	RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        String uri = prepareAuthUri(UPDATE_AUTH_USER_ROLE.replace("{userId}", String.valueOf(id)));
        Authentication security = SecurityContextHolder.getContext().getAuthentication();
        if (null != security) {
            Object auth = security.getDetails();
            if (auth instanceof OAuth2AuthenticationDetails) {
                LOGGER.info("============ bearer token for update user role === {}",((OAuth2AuthenticationDetails) auth).getTokenValue());
                headers.add("Authorization","Bearer "+((OAuth2AuthenticationDetails) auth).getTokenValue());
            }
        }
        HttpEntity<List<String>> requestEntity = new HttpEntity<>(roles,headers);
        ResponseEntity<AuthUser> responseEntity = restTemplate.exchange(uri, HttpMethod.POST, requestEntity, AuthUser.class);
		return responseEntity.getBody();

		
	}
    public AuthUser deleteAuthUserRole(Long id , List<String> roles) {
    	
		RestTemplate restTemplate = new RestTemplate();
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		String uri = prepareAuthUri(DELETE_AUTH_USER_ROLE.replace("{userId}", String.valueOf(id)));

		Map<String, String> params = new HashMap<>();
		StringBuilder builder = new StringBuilder();

		for (int i = 0; i < roles.size(); i++) {

			builder.append(roles.get(i));
			if (i != roles.size() - 1) {
				builder.append("&roles=");
			}

		}
		params.put("roles", builder.toString());
		Authentication security = SecurityContextHolder.getContext().getAuthentication();
		if (null != security) {
			Object auth = security.getDetails();
			if (auth instanceof OAuth2AuthenticationDetails) {
				LOGGER.info("============ bearer token for delete user role === {}",
						((OAuth2AuthenticationDetails) auth).getTokenValue());
				headers.add("Authorization", "Bearer " + ((OAuth2AuthenticationDetails) auth).getTokenValue());
			}
		}

		HttpEntity<String> requestEntity = new HttpEntity<>(headers);
		ResponseEntity<AuthUser> responseEntity = restTemplate.exchange(uri + "?roles=" + builder.toString(),
				HttpMethod.DELETE, requestEntity, AuthUser.class);
		return responseEntity.getBody();
		
	}

    /*@Override
    public Optional<UserVo> updateAuthUser(UserVo userVo) {
        UserVo vo = null;
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        String uri = prepareAuthUri(AUTH_USER_UPDATE_URI);
        Authentication security = SecurityContextHolder.getContext().getAuthentication();
        if (null != security) {
            Object auth = security.getDetails();
            if (auth instanceof OAuth2AuthenticationDetails) {
                LOGGER.info("============ bearer token for update user === {}",((OAuth2AuthenticationDetails) auth).getTokenValue());
                headers.add("Authorization","Bearer "+((OAuth2AuthenticationDetails) auth).getTokenValue());
            }
        }
        HttpEntity<UserVo> requestEntity = new HttpEntity<>(userVo,headers);
        ResponseEntity<String> responseEntity = restTemplate.exchange(uri, HttpMethod.POST, requestEntity, String.class);
        LOGGER.info("updateUser to Auth responseEntity {}",responseEntity.getBody());
        ObjectMapper mapper = new ObjectMapper();
        try {
            vo = mapper.readerWithView(BusViews.BusBasicView.class)
                    .forType(UserVo.class).readValue(responseEntity.getBody());
        } catch (IOException e) {
            LOGGER.error(e.getMessage());
            return Optional.ofNullable(null);
        }
        return Optional.ofNullable(vo);
    }
*/
    @Override
    public boolean deleteAuthUser(List<Long> users) {
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        String uri = prepareAuthUri(AUTH_USERS_DELETE_URI);
        Authentication security = SecurityContextHolder.getContext().getAuthentication();
        if (null != security) {
            Object auth = security.getDetails();
            if (auth instanceof OAuth2AuthenticationDetails) {
                headers.add("Authorization","Bearer "+((OAuth2AuthenticationDetails) auth).getTokenValue());
            }
        }
        headers.add("Content-Type", "application/json");
        ObjectMapper mapper = new ObjectMapper();
        String jsonUsers = null;
        try {
            jsonUsers = mapper.writeValueAsString(users);
        } catch (JsonProcessingException e) {
            LOGGER.error(e.getMessage());
            return false;
        }
        HttpEntity<String> requestEntity = new HttpEntity<>(jsonUsers,headers);
        ResponseEntity<String> responseEntity = restTemplate.exchange(uri, HttpMethod.POST, requestEntity, String.class);
        return responseEntity.getBody().equals("true");
    }


    private String prepareAuthUri(final String uri) {
        final StringBuilder urlBuilder = new StringBuilder();
        urlBuilder.append(configurationService.getConfigValue(String.class, ConfigurationKeyImpl.AUTH_BASE_URL)).append(uri);
        LOGGER.debug("Auth Service Uri : {}", urlBuilder.toString());
        return urlBuilder.toString();
    }

	@Override
	public void updateUserDomain(String userId, String domainName) {
		RestTemplate restTemplate = new RestTemplate();
        String uri = prepareAuthUri(AUTH_USER_DOMAIN_UPDATE_URI.replace("{userId}", userId));
//		String uri ="http://localhost:9999/oauth/users/"+userId+"/domains";
		UserVo userVo = new UserVo();
		userVo.setDomain(domainName);
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        headers.setContentType(MediaType.APPLICATION_JSON);
        Authentication security = SecurityContextHolder.getContext().getAuthentication();
        if (null != security) {
            Object auth = security.getDetails();
            if (auth instanceof OAuth2AuthenticationDetails) {
            	LOGGER.info("============ bearer token for update user domain === {}",((OAuth2AuthenticationDetails) auth).getTokenValue());
                headers.add("Authorization","Bearer "+((OAuth2AuthenticationDetails) auth).getTokenValue());
            }
        }
        HttpEntity<UserVo> requestEntity = new HttpEntity<>(userVo, headers);
        LOGGER.info(" ================= Going to update user domain at GMAuth side for URI = " +uri);
		restTemplate.exchange(uri, HttpMethod.PUT, requestEntity, String.class);
		LOGGER.info("=========== SUCCESS----------");
	}

	
}
