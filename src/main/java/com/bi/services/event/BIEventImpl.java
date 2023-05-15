package com.bi.services.event;

import com.bi.enums.BIEvent;
import com.bi.enums.TemplateObjectKeys;
import lombok.Getter;
import lombok.Setter;

import java.util.AbstractMap;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Getter
@Setter
public class BIEventImpl implements BIEvent {

    public static final BIEvent FEEDBACK_MAIL = new BIEventImpl("FEEDBACK_MAIL", Stream.of(
            new AbstractMap.SimpleEntry<>(TemplateObjectKeysImpl.FEEDBACK,TemplateObjectKeysImpl.FEEDBACK.getKey()),
            new AbstractMap.SimpleEntry<>(TemplateObjectKeysImpl.IMAGE_BASE_URL,TemplateObjectKeysImpl.IMAGE_BASE_URL.getKey()),
            new AbstractMap.SimpleEntry<>(TemplateObjectKeysImpl.USER,TemplateObjectKeysImpl.USER.getKey()))
            .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue)));

    public static final BIEvent CAREER_MAIL = new BIEventImpl("CAREER_MAIL", Stream.of(
            new AbstractMap.SimpleEntry<>(TemplateObjectKeysImpl.CAREER,TemplateObjectKeysImpl.CAREER.getKey()),
            new AbstractMap.SimpleEntry<>(TemplateObjectKeysImpl.IMAGE_BASE_URL,TemplateObjectKeysImpl.IMAGE_BASE_URL.getKey()),
            new AbstractMap.SimpleEntry<>(TemplateObjectKeysImpl.USER,TemplateObjectKeysImpl.USER.getKey()))
            .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue)));

    public static final BIEvent CONTACT_MAIL = new BIEventImpl("CONTACT_MAIL", Stream.of(
            new AbstractMap.SimpleEntry<>(TemplateObjectKeysImpl.CONTACT,TemplateObjectKeysImpl.CONTACT.getKey()),
            new AbstractMap.SimpleEntry<>(TemplateObjectKeysImpl.IMAGE_BASE_URL,TemplateObjectKeysImpl.IMAGE_BASE_URL.getKey()),
            new AbstractMap.SimpleEntry<>(TemplateObjectKeysImpl.USER,TemplateObjectKeysImpl.USER.getKey()))
            .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue)));

    public static final BIEvent FRANCHISE_MAIL = new BIEventImpl("FRANCHISE_MAIL", Stream.of(
            new AbstractMap.SimpleEntry<>(TemplateObjectKeysImpl.FRANCHISE,TemplateObjectKeysImpl.FRANCHISE.getKey()),
            new AbstractMap.SimpleEntry<>(TemplateObjectKeysImpl.IMAGE_BASE_URL,TemplateObjectKeysImpl.IMAGE_BASE_URL.getKey()),
            new AbstractMap.SimpleEntry<>(TemplateObjectKeysImpl.USER,TemplateObjectKeysImpl.USER.getKey()))
            .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue)));

    public static final BIEvent SITESUBMITTER_MAIL = new BIEventImpl("SITESUBMITTER_MAIL", Stream.of(
            new AbstractMap.SimpleEntry<>(TemplateObjectKeysImpl.SITESUBMITTER,TemplateObjectKeysImpl.SITESUBMITTER.getKey()),
            new AbstractMap.SimpleEntry<>(TemplateObjectKeysImpl.IMAGE_BASE_URL,TemplateObjectKeysImpl.IMAGE_BASE_URL.getKey()),
            new AbstractMap.SimpleEntry<>(TemplateObjectKeysImpl.USER,TemplateObjectKeysImpl.USER.getKey()))
            .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue)));

    public static final BIEvent FUNDRAISING_MAIL = new BIEventImpl("FUNDRAISING_MAIL", Stream.of(
            new AbstractMap.SimpleEntry<>(TemplateObjectKeysImpl.FUND_RAISING,TemplateObjectKeysImpl.FUND_RAISING.getKey()),
            new AbstractMap.SimpleEntry<>(TemplateObjectKeysImpl.IMAGE_BASE_URL,TemplateObjectKeysImpl.IMAGE_BASE_URL.getKey()),
            new AbstractMap.SimpleEntry<>(TemplateObjectKeysImpl.USER,TemplateObjectKeysImpl.USER.getKey()))
            .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue)));

	public static final BIEvent CATERING_MAIL = new BIEventImpl("CATERING_MAIL", Stream.of(
            new AbstractMap.SimpleEntry<>(TemplateObjectKeysImpl.CATERING,TemplateObjectKeysImpl.CATERING.getKey()),
            new AbstractMap.SimpleEntry<>(TemplateObjectKeysImpl.IMAGE_BASE_URL,TemplateObjectKeysImpl.IMAGE_BASE_URL.getKey()),
            new AbstractMap.SimpleEntry<>(TemplateObjectKeysImpl.USER,TemplateObjectKeysImpl.USER.getKey()))
            .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue)));
	
	 public static final BIEvent FUNDRAISING_APPROVED_MAIL = new BIEventImpl("FUNDRAISING_APPROVED_MAIL", Stream.of(
	            new AbstractMap.SimpleEntry<>(TemplateObjectKeysImpl.FUND_RAISING,TemplateObjectKeysImpl.FUND_RAISING.getKey()),
	            new AbstractMap.SimpleEntry<>(TemplateObjectKeysImpl.IMAGE_BASE_URL,TemplateObjectKeysImpl.IMAGE_BASE_URL.getKey()),
	            new AbstractMap.SimpleEntry<>(TemplateObjectKeysImpl.USER,TemplateObjectKeysImpl.USER.getKey()))
	            .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue)));
	 
	 public static final BIEvent FUNDRAISING_DISAPPROVED_MAIL = new BIEventImpl("FUNDRAISING_DISAPPROVED_MAIL", Stream.of(
	            new AbstractMap.SimpleEntry<>(TemplateObjectKeysImpl.FUND_RAISING,TemplateObjectKeysImpl.FUND_RAISING.getKey()),
	            new AbstractMap.SimpleEntry<>(TemplateObjectKeysImpl.IMAGE_BASE_URL,TemplateObjectKeysImpl.IMAGE_BASE_URL.getKey()),
	            new AbstractMap.SimpleEntry<>(TemplateObjectKeysImpl.USER,TemplateObjectKeysImpl.USER.getKey()))
	            .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue)));

    private String name;
    private Map<TemplateObjectKeys, String> templateKeys;

    private BIEventImpl(String name, Map<TemplateObjectKeys, String> map) {
        this.name = name;
        this.templateKeys = map;
    }

}
