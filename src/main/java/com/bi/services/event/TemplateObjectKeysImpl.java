package com.bi.services.event;

import com.bi.enums.TemplateObjectKeys;
import lombok.Getter;

@Getter
public class TemplateObjectKeysImpl implements TemplateObjectKeys {
	
	public static final TemplateObjectKeys dataPayload = new TemplateObjectKeysImpl("dataPayload");
	public static final TemplateObjectKeys ADDRESS = new TemplateObjectKeysImpl("address");
	public static final TemplateObjectKeys USER = new TemplateObjectKeysImpl("USER");
	public static final TemplateObjectKeys LFS_UTILS = new TemplateObjectKeysImpl("lfs_utils");
	public static final TemplateObjectKeys TOKEN_VALUE = new TemplateObjectKeysImpl("token_value");
	public static final TemplateObjectKeys DOCUMENT_DATA = new TemplateObjectKeysImpl("document_data");
	public static final TemplateObjectKeys FEEDBACK = new TemplateObjectKeysImpl("FEEDBACK");
	public static final TemplateObjectKeys CAREER = new TemplateObjectKeysImpl("CAREER");
	public static final TemplateObjectKeys CONTACT = new TemplateObjectKeysImpl("CONTACTUS");
	public static final TemplateObjectKeys FRANCHISE = new TemplateObjectKeysImpl("FRANCHISE");
	public static final TemplateObjectKeys SITESUBMITTER = new TemplateObjectKeysImpl("SITESUBMITTER");
	public static final TemplateObjectKeys FUND_RAISING = new TemplateObjectKeysImpl("FUNDRAISING");
	public static final TemplateObjectKeys CATERING = new TemplateObjectKeysImpl("CATERING");
	public static final TemplateObjectKeys IMAGE_BASE_URL = new TemplateObjectKeysImpl("IMAGE_BASE_URL");

	private String key;
	
	private TemplateObjectKeysImpl(String key) {
		this.key = key;
	}

}
