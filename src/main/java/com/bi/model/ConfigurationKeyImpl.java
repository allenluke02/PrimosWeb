package com.bi.model;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

import com.bi.config.ConfigurationKey;

@Component
@Primary
public class ConfigurationKeyImpl implements ConfigurationKey{
	
	private static final List<ConfigurationKey> values = new ArrayList<>();
	public static final ConfigurationKey AUTH_BASE_URL = new ConfigurationKeyImpl("AUTH_BASE_URL", String.class);;
	public static final ConfigurationKey AWS_ACCESS_KEY = new ConfigurationKeyImpl("AWS_ACCESS_KEY", "aws.access.key", String.class);
	public static final ConfigurationKey AWS_SECRET_ACCESS_KEY = new ConfigurationKeyImpl("AWS_SECRET_ACCESS_KEY", "aws.access-s.key", String.class);
	public static final ConfigurationKey AWS_UPLOAD_DOCUMENT_BUCKET = new ConfigurationKeyImpl("AWS_UPLOAD_DOCUMENT_BUCKET", String.class);
	public static final ConfigurationKey AWS_REGION = new ConfigurationKeyImpl("AWS_REGION", String.class);
	public static final ConfigurationKey GOOGLE_CAPTCHA_VARIFY_URL = new ConfigurationKeyImpl("GOOGLE_CAPTCHA_VARIFY_URL", String.class);
	public static final ConfigurationKey GOOGLE_CAPTCHA_SECRET = new ConfigurationKeyImpl("GOOGLE_CAPTCHA_SECRET", String.class);
	public static final ConfigurationKey PRIMOS_INTERNAL_MAIL = new ConfigurationKeyImpl("PRIMOS_INTERNAL_MAIL", String.class);
	public static final ConfigurationKey GOOGLE_DISTANCE_MATRIX_API_KEY = new ConfigurationKeyImpl("GOOGLE_DISTANCE_MATRIX_API_KEY", String.class);
	public static final ConfigurationKey GOOGLE_DISTANCE_MATRIX_URL = new ConfigurationKeyImpl("GOOGLE_DISTANCE_MATRIX_URL", String.class);
	public static final ConfigurationKey PRIMOS_IMAGE_BASE_URL= new ConfigurationKeyImpl("PRIMOS_IMAGE_BASE_URL", String.class);

	public static final ConfigurationKey PRIMOS_INTERNAL_CATERING_MAIL = new ConfigurationKeyImpl("PRIMOS_INTERNAL_CATERING_MAIL", String.class);
	public static final ConfigurationKey PRIMOS_INTERNAL_FUNDRAISING_MAIL = new ConfigurationKeyImpl("PRIMOS_INTERNAL_FUNDRAISING_MAIL", String.class);
	public static final ConfigurationKey PRIMOS_INTERNAL_FEEDBACK_MAIL = new ConfigurationKeyImpl("PRIMOS_INTERNAL_FEEDBACK_MAIL", String.class);
	public static final ConfigurationKey PRIMOS_INTERNAL_CONTACT_US_MAIL = new ConfigurationKeyImpl("PRIMOS_INTERNAL_CONTACT_US_MAIL", String.class);
	public static final ConfigurationKey PRIMOS_INTERNAL_FRANCHISE_MAIL = new ConfigurationKeyImpl("PRIMOS_INTERNAL_FRANCHISE_MAIL", String.class);
	public static final ConfigurationKey PRIMOS_INTERNAL_CAREER_MAIL = new ConfigurationKeyImpl("PRIMOS_INTERNAL_CAREER_MAIL", String.class);

	private String keyName;
	private Class<?> dataType;
	private String replaceKey;
	
	public ConfigurationKeyImpl() {
		
	}
	
	private ConfigurationKeyImpl(String description, Class<?> dataType) {
		this.keyName = description;
		this.dataType = dataType;
		ConfigurationKeyImpl.values.add(this);
	}

	private ConfigurationKeyImpl(String name, String replaceKey, Class<?> dataType) {
		this.keyName = name;
		this.dataType = dataType;
		this.replaceKey = replaceKey;
		ConfigurationKeyImpl.values.add(this);
	}
	
	public boolean isInt() {
		return dataType.isAssignableFrom(Integer.class);
	}
	
	public boolean isLong() {
		return dataType.isAssignableFrom(Long.class);
	}

	@Override
	public String getKeyName() {
		return keyName;
	}
	
	@Override
	public Class<?> getDataType() {
		return dataType;
	}

	@Override
	public List<ConfigurationKey> getValues() {
		return ConfigurationKeyImpl.values;
	}

	public String getReplaceKey() {
		return this.replaceKey;
	}
	
}
