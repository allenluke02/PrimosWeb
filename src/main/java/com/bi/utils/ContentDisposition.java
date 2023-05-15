package com.bi.utils;

import lombok.Getter;

@Getter
public enum ContentDisposition {
	ATTACHMENT("attachment"),
	INLINE("inline");
	
	private String type;

	private ContentDisposition(String type) {
		this.type = type;
	}
}
