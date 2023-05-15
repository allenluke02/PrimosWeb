package com.bi.model;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

import com.bi.config.ServiceKey;

@Component
@Primary
public class ServiceKeyImpl implements ServiceKey {

	@Override
	public String getServiceKey() {
		return "PRIMOS_FO";
	}
	

}
