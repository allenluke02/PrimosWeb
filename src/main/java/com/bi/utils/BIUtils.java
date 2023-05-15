package com.bi.utils;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.bi.config.ConfigurationService;
import com.bi.exception.BIValidationException;
import com.bi.model.Address;
import com.bi.model.ConfigurationKeyImpl;
import com.bi.model.Country;
import com.bi.model.QCountry;
import com.bi.model.QState;
import com.bi.model.State;
import com.bi.model.franchise.BICaptchaResponse;
import com.bi.repositories.CountryRepository;
import com.bi.repositories.StateRepository;
import com.bi.services.MessageByLocaleService;

@Component
public class BIUtils {
	private static final Logger LOGGER = LoggerFactory.getLogger(BIUtils.class);

	@Autowired
	private MessageByLocaleService messageByLocaleService;
	
	@Autowired
	StateRepository stateRepository;
	
	@Autowired
	CountryRepository countryRepository;
	
    @Autowired
    private ConfigurationService configurationService;
	
	private final static String GET_COUNTRY_BY_ID = "/api/auth/countries/{countryid}";
	private final static String GET_STATE_BY_ID = "/api/auth/countries/{countryid}/states/{stateid}";
	
	public boolean isValidCaptcha(String reCaptcha) {
		String uri = configurationService.getConfigValue(String.class, ConfigurationKeyImpl.GOOGLE_CAPTCHA_VARIFY_URL);
		String secret = configurationService.getConfigValue(String.class, ConfigurationKeyImpl.GOOGLE_CAPTCHA_SECRET);
		RestTemplate restTemplate = new RestTemplate();
		UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(uri).queryParam("secret", secret).queryParam("response", reCaptcha);
		BICaptchaResponse status = restTemplate.getForObject(builder.toUriString(), BICaptchaResponse.class);
		return status.isSuccess();
}

	public boolean isValidState(Address adds) {
		Long countryId=adds.getCountry().getId();
		boolean isValid=true;
		Optional<State> dbSt=stateRepository.findOne(QState.state.id.eq(adds.getState().getId()).and(QState.state.country.id.eq(countryId)));
		if(dbSt.isEmpty()) {
			State state=fetchState(adds.getState().getId(),countryId);
			if(state == null ) {
				isValid=false;
				throw new BIValidationException(
						messageByLocaleService.getMessage("err.state.invalid"));
			}else {
				stateRepository.save(state);
				adds.setState(state);
			}
		}else {
			adds.setState(dbSt.get());
		}
		return isValid;
	}
	
	public boolean isValidCountry(Address adds) {
		Long countryId=adds.getCountry().getId();
		boolean isValid=true;
		Optional<Country> country=countryRepository.findOne(QCountry.country.id.eq(countryId));
		if(country.isEmpty()) {
			Country newCountry=fetchCountry(countryId);
			if(newCountry == null ) {
				isValid=false;
				throw new BIValidationException(messageByLocaleService.getMessage("err.country.invalid"));
			}else {
				countryRepository.save(newCountry);
				adds.setCountry(newCountry);
			}
		}else {
			adds.setCountry(country.get());
		}
		return isValid;
	}
	
	
	private Country fetchCountry( Long countryId) {
		Country country = null;
		RestTemplate restTemplate = new RestTemplate();
		final StringBuilder urlBuilder = new StringBuilder();
        urlBuilder.append(configurationService.getConfigValue(String.class, ConfigurationKeyImpl.AUTH_BASE_URL)).append(GET_COUNTRY_BY_ID);
		UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(urlBuilder.toString().replace("{countryid}", String.valueOf(countryId)));
		try {
			LOGGER.trace("Country Api url " + builder.toUriString());
			country = restTemplate.exchange(builder.toUriString(), HttpMethod.GET, null, Country.class).getBody();
		} catch (Exception e) {
			LOGGER.error("Error fetching Country data", e);
		}
		return country;
	}

	private State fetchState(Long stateId, Long countryId) {
		State state = null;
		RestTemplate restTemplate = new RestTemplate();
		final StringBuilder urlBuilder = new StringBuilder();
        urlBuilder.append(configurationService.getConfigValue(String.class, ConfigurationKeyImpl.AUTH_BASE_URL)).append(GET_STATE_BY_ID);
		UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(
				urlBuilder.toString().replace("{countryid}", String.valueOf(countryId)).replace("{stateid}", String.valueOf(stateId)));
		try {
			LOGGER.trace("State Api url " + builder.toUriString());
			state = restTemplate.exchange(builder.toUriString(), HttpMethod.GET, null, State.class).getBody();
		} catch (Exception e) {
			LOGGER.error("Error fetching state data", e);
		}
		return state;
	}
	
	

}
