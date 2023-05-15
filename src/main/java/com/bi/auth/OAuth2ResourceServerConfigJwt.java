package com.bi.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;

import com.bi.config.ConfigurationService;
import com.bi.model.BlobConfigurationKeyImpl;

@Configuration
@EnableResourceServer
public class OAuth2ResourceServerConfigJwt extends ResourceServerConfigurerAdapter {

	@Autowired
	private CustomAccessTokenConverter customAccessTokenConverter;

	@Autowired
	private ConfigurationService configService;

	@Override
	public void configure(final HttpSecurity http) throws Exception {
		// @formatter:off
		http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED).and().authorizeRequests()
				.antMatchers("/login").permitAll().antMatchers("/oauth/token/revokeById/**").permitAll()
				.antMatchers("/tokens/**").permitAll().antMatchers("/api/**").permitAll()
				.antMatchers("/configuration/ui", "/webjars/**", "/swagger-ui/**", "/swagger-resources/**",
						"/configuration/security", "/v2/api-docs","/fundRaising/**").permitAll()
				.antMatchers("/careers/**","/feedbacks/**","/contactUs/**").permitAll()
				.antMatchers(HttpMethod.GET,"/pickupPoints/**","/counties/**","/healthConcerns/**","/jobs/**",
						"/jobTypes/**","/pickupHours/**","/productConcerns/**","/serviceConcerns/**","/serviceProviders/**","/DocumentType/**",
						"/states/**","/countries/**","/franchiseInfos/**","/franchiseLiquidAssets/**",
						"/franchiseNetWorths/**","/restaurantCapacities/**","/downloadFile/**"
						,"/cateringPrice/**","/cateringType/**","/beansType/**","/choice/**","/tortillaType/**").permitAll()
				.antMatchers(HttpMethod.POST,"/uploadFile/**","/franchises/**","/siteSubmitters/**","/catering/**").permitAll()
				.anyRequest().authenticated();
		// @formatter:on
	}

	@Override
	public void configure(final ResourceServerSecurityConfigurer config) {
		config.tokenServices(tokenServices());
		config.resourceId("api");
	}

	@Bean
	public TokenStore tokenStore() {
		return new JwtTokenStore(accessTokenConverter());
	}

	@Bean
	public JwtAccessTokenConverter accessTokenConverter() {
		final JwtAccessTokenConverter converter = new JwtAccessTokenConverter();
		converter.setAccessTokenConverter(customAccessTokenConverter);
		converter.setVerifierKey(
				new String(configService.getBlobConfiguration(BlobConfigurationKeyImpl.AUTH_PUBLIC_KEY.getKeyName())));
		//converter.setSigningKey("123");
		return converter;
	}

	@Bean
	@Primary
	public DefaultTokenServices tokenServices() {
		final DefaultTokenServices defaultTokenServices = new DefaultTokenServices();
		defaultTokenServices.setTokenStore(tokenStore());
		return defaultTokenServices;
	}

}