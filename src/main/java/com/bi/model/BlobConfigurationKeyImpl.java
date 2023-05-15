package com.bi.model;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.bi.config.BlobConfigurationKey;
import com.bi.config.ConfigurationKey;

@Component
public class BlobConfigurationKeyImpl implements BlobConfigurationKey {
	
	private static final List<ConfigurationKey> values = new ArrayList<>();

	public static final ConfigurationKey AUTH_PUBLIC_KEY = new BlobConfigurationKeyImpl("AUTH_PUBLIC_KEY",Byte[].class);
	
	private String keyName;
	private Class<?> dataType;
	private String replaceKey;
	
	public BlobConfigurationKeyImpl() {
		
	}
	
	private BlobConfigurationKeyImpl(String name, Class<?> dataType) {
		this.keyName = name;
		this.dataType = dataType;
		BlobConfigurationKeyImpl.values.add(this);
	}
	
	private BlobConfigurationKeyImpl(String name, String replaceKey, Class<?> dataType) {
		this.keyName = name;
		this.dataType = dataType;
		this.replaceKey = replaceKey;
		BlobConfigurationKeyImpl.values.add(this);
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
	public List<com.bi.config.ConfigurationKey> getValues() {
		return BlobConfigurationKeyImpl.values;
	}
	
	public String getReplaceKey() {
		return this.replaceKey;
	}
	
}
