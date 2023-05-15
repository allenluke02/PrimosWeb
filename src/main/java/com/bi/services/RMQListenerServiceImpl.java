package com.bi.services;

import java.util.Map;

import org.springframework.stereotype.Component;

import com.bi.service.RabbitMQListener;
@Component
public class RMQListenerServiceImpl implements RabbitMQListener {

	@Override
	public void rabbitMQListener(Map<String, Object> header, String event, String body) throws Exception {

		
	}
}
