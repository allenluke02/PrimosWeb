package com.bi.services.event;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.EnumMap;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bi.config.ConfigurationKey;
import com.bi.config.ConfigurationKeyDefaultImpl;
import com.bi.config.ConfigurationService;
import com.bi.enums.BIEvent;
import com.bi.enums.TemplatePartyEnum;
import com.bi.model.ConfigurationKeyImpl;
import com.bi.model.ContactUs;
import com.bi.model.Feedback;
import com.bi.model.FundRaising;
import com.bi.model.catering.Catering;
import com.bi.model.franchise.Franchise;
import com.bi.model.franchise.SiteSubmitter;
import com.bi.model.hire.Career;
import com.bi.views.CareerView;
import com.bi.views.CateringView;
import com.bi.views.ContactUsView;
import com.bi.views.FeedbackMailView;
import com.bi.views.FranchiseView;
import com.bi.views.FundRaisingView;
import com.bi.views.SiteSubmitterView;
import com.bi.vo.UserVo;

@Service
public class NotificationServiceImpl implements NotificationService {

	@Autowired
	private com.bi.service.NotificationService notificationService;

	@Autowired
	private ConfigurationService configurationService;

	@Override
	public void submitFeedbackNotification(Feedback feedback) {
		String format = "DEFAULT";
		BIEvent event = BIEventImpl.FEEDBACK_MAIL;
		Map<String, Object> prepareNotifDataForSendFeedback = prepareNotifDataForSendFeedback(event, feedback);
		Map<TemplatePartyEnum, List<String>> prepareEmailToSendMail = prepareEmailToSendMail(feedback.getEmail(),ConfigurationKeyImpl.PRIMOS_INTERNAL_FEEDBACK_MAIL);
		notificationService.sendNotification(event, format, prepareEmailToSendMail, Collections.emptyMap(),
				prepareNotifDataForSendFeedback, Optional.of(FeedbackMailView.BasicView.class));
	}

	@Override
	public void createContactUs(ContactUs contactUs) {
		String format = "DEFAULT";
		BIEvent event = BIEventImpl.CONTACT_MAIL;
		Map<String, Object> prepareNotifDataForSendContactUs = prepareNotifDataForSendContactUs(event, contactUs);
		Map<TemplatePartyEnum, List<String>> prepareEmailToSendMail = prepareEmailToSendMail(contactUs.getEmail(),ConfigurationKeyImpl.PRIMOS_INTERNAL_CONTACT_US_MAIL);
		notificationService.sendNotification(event, format, prepareEmailToSendMail, Collections.emptyMap(),
				prepareNotifDataForSendContactUs, Optional.of(ContactUsView.BasicView.class));
	}

	@Override
	public void submitCareer(Career career) {
		String format = "DEFAULT";
		BIEvent event = BIEventImpl.CAREER_MAIL;
		Map<String, Object> prepareNotifDataForSendCareer = prepareNotifDataForSendCareer(event, career);
		Map<TemplatePartyEnum, List<String>> prepareEmailToSendMail = prepareEmailToSendMailToCareer(
				career.getMailingAddress(),ConfigurationKeyImpl.PRIMOS_INTERNAL_CAREER_MAIL,career);
		notificationService.sendNotification(event, format, prepareEmailToSendMail, Collections.emptyMap(),
				prepareNotifDataForSendCareer, Optional.of(CareerView.BasicView.class));
	}

	@Override
	public void franchiseRequest(Franchise franchise) {
		String format = "DEFAULT";
		BIEvent event = BIEventImpl.FRANCHISE_MAIL;
		Map<String, Object> prepareNotifDataForFranchiseRequest = prepareNotifDataForFranchiseRequest(event, franchise);
		Map<TemplatePartyEnum, List<String>> prepareEmailToSendMail = prepareEmailToSendMail(franchise.getEmail(),ConfigurationKeyImpl.PRIMOS_INTERNAL_FRANCHISE_MAIL);
		notificationService.sendNotification(event, format, prepareEmailToSendMail, Collections.emptyMap(),
				prepareNotifDataForFranchiseRequest, Optional.of(FranchiseView.BasicView.class));
	}

	@Override
	public void siteSubmitterRequest(SiteSubmitter siteSubmitter) {
		String format = "DEFAULT";
		BIEvent event = BIEventImpl.SITESUBMITTER_MAIL;
		Map<String, Object> prepareNotifDataForSiteSubmitterRequest = prepareNotifDataForSiteSubmitterRequest(event,
				siteSubmitter);
		Map<TemplatePartyEnum, List<String>> prepareEmailToSendMail = prepareEmailToSendMail(siteSubmitter.getEmail(),ConfigurationKeyImpl.PRIMOS_INTERNAL_FRANCHISE_MAIL);
		notificationService.sendNotification(event, format, prepareEmailToSendMail, Collections.emptyMap(),
				prepareNotifDataForSiteSubmitterRequest, Optional.of(SiteSubmitterView.BasicView.class));
	}

	@Override
	public void fundRaisingRequest(FundRaising fundRaising) {
		String format = "DEFAULT";
		BIEvent event = BIEventImpl.FUNDRAISING_MAIL;
		Map<String, Object> prepareNotifDataForFundRaisingRequest = prepareNotifDataForFundRaisingRequest(event,
				fundRaising);
		Map<TemplatePartyEnum, List<String>> prepareEmailToSendMail = prepareEmailToSendMail(fundRaising.getEmail(),ConfigurationKeyImpl.PRIMOS_INTERNAL_FUNDRAISING_MAIL);
		notificationService.sendNotification(event, format, prepareEmailToSendMail, Collections.emptyMap(),
				prepareNotifDataForFundRaisingRequest, Optional.of(FundRaisingView.BasicView.class));
	}

	@Override
	public void approveFundRaisingRequest(FundRaising fundRaising) {
		String format = "DEFAULT";
		BIEvent event = BIEventImpl.FUNDRAISING_APPROVED_MAIL;
		Map<String, Object> prepareNotifDataForFundRaisingRequest = prepareNotifDataForFundRaisingRequest(event,
				fundRaising);
		Map<TemplatePartyEnum, List<String>> prepareEmailToSendMail = prepareEmailToSendMail(fundRaising.getEmail(),ConfigurationKeyImpl.PRIMOS_INTERNAL_FUNDRAISING_MAIL);
		notificationService.sendNotification(event, format, prepareEmailToSendMail, Collections.emptyMap(),
				prepareNotifDataForFundRaisingRequest, Optional.of(FundRaisingView.BasicView.class));
	}

	@Override
	public void disapproveFundRaisingRequest(FundRaising fundRaising) {
		String format = "DEFAULT";
		BIEvent event = BIEventImpl.FUNDRAISING_DISAPPROVED_MAIL;
		Map<String, Object> prepareNotifDataForFundRaisingRequest = prepareNotifDataForFundRaisingRequest(event,
				fundRaising);
		Map<TemplatePartyEnum, List<String>> prepareEmailToSendMail = prepareEmailToSendMail(fundRaising.getEmail(),ConfigurationKeyImpl.PRIMOS_INTERNAL_FUNDRAISING_MAIL);
		notificationService.sendNotification(event, format, prepareEmailToSendMail, Collections.emptyMap(),
				prepareNotifDataForFundRaisingRequest, Optional.of(FundRaisingView.BasicView.class));
	}

	private Map<String, Object> prepareNotifDataForSendFeedback(BIEvent event, Feedback feedback) {

		Map<String, Object> notificationData = new HashMap<>();
		notificationData.put(event.getTemplateKeys().get(TemplateObjectKeysImpl.FEEDBACK), feedback);
		UserVo user = new UserVo();
		user.setEmail(feedback.getEmail());
		notificationData.put(event.getTemplateKeys().get(TemplateObjectKeysImpl.USER), user);
		notificationData.put(event.getTemplateKeys().get(TemplateObjectKeysImpl.IMAGE_BASE_URL),
				configurationService.getConfigValue(String.class, ConfigurationKeyImpl.PRIMOS_IMAGE_BASE_URL));
		return notificationData;
	}

	private Map<String, Object> prepareNotifDataForSendContactUs(BIEvent event, ContactUs contactUs) {
		Map<String, Object> notificationData = new HashMap<>();
		notificationData.put(event.getTemplateKeys().get(TemplateObjectKeysImpl.CONTACT), contactUs);
		UserVo user = new UserVo();
		user.setEmail(contactUs.getEmail());
		notificationData.put(event.getTemplateKeys().get(TemplateObjectKeysImpl.USER), user);
		notificationData.put(event.getTemplateKeys().get(TemplateObjectKeysImpl.IMAGE_BASE_URL),
				configurationService.getConfigValue(String.class, ConfigurationKeyImpl.PRIMOS_IMAGE_BASE_URL));
		return notificationData;
	}

	private Map<String, Object> prepareNotifDataForSendCareer(BIEvent event, Career career) {

		Map<String, Object> notificationData = new HashMap<>();
		notificationData.put(event.getTemplateKeys().get(TemplateObjectKeysImpl.CAREER), career);
		UserVo user = new UserVo();
		user.setEmail(career.getMailingAddress());
		notificationData.put(event.getTemplateKeys().get(TemplateObjectKeysImpl.USER), user);
		notificationData.put(event.getTemplateKeys().get(TemplateObjectKeysImpl.IMAGE_BASE_URL),
				configurationService.getConfigValue(String.class, ConfigurationKeyImpl.PRIMOS_IMAGE_BASE_URL));
		return notificationData;

	}

	private Map<String, Object> prepareNotifDataForFranchiseRequest(BIEvent event, Franchise franchise) {

		Map<String, Object> notificationData = new HashMap<>();
		notificationData.put(event.getTemplateKeys().get(TemplateObjectKeysImpl.FRANCHISE), franchise);
		UserVo user = new UserVo();
		user.setEmail(franchise.getEmail());
		notificationData.put(event.getTemplateKeys().get(TemplateObjectKeysImpl.USER), user);
		notificationData.put(event.getTemplateKeys().get(TemplateObjectKeysImpl.IMAGE_BASE_URL),
				configurationService.getConfigValue(String.class, ConfigurationKeyImpl.PRIMOS_IMAGE_BASE_URL));
		return notificationData;

	}

	private Map<String, Object> prepareNotifDataForSiteSubmitterRequest(BIEvent event, SiteSubmitter siteSubmitter) {

		Map<String, Object> notificationData = new HashMap<>();
		notificationData.put(event.getTemplateKeys().get(TemplateObjectKeysImpl.SITESUBMITTER), siteSubmitter);
		UserVo user = new UserVo();
		user.setEmail(siteSubmitter.getEmail());
		notificationData.put(event.getTemplateKeys().get(TemplateObjectKeysImpl.USER), user);
		notificationData.put(event.getTemplateKeys().get(TemplateObjectKeysImpl.IMAGE_BASE_URL),
				configurationService.getConfigValue(String.class, ConfigurationKeyImpl.PRIMOS_IMAGE_BASE_URL));
		return notificationData;

	}

	private Map<String, Object> prepareNotifDataForFundRaisingRequest(BIEvent event, FundRaising fundRaising) {

		Map<String, Object> notificationData = new HashMap<>();
		notificationData.put(event.getTemplateKeys().get(TemplateObjectKeysImpl.FUND_RAISING), fundRaising);
		UserVo user = new UserVo();
		user.setEmail(fundRaising.getEmail());
		notificationData.put(event.getTemplateKeys().get(TemplateObjectKeysImpl.USER), user);
		notificationData.put(event.getTemplateKeys().get(TemplateObjectKeysImpl.IMAGE_BASE_URL),
				configurationService.getConfigValue(String.class, ConfigurationKeyImpl.PRIMOS_IMAGE_BASE_URL));
		return notificationData;

	}

	private Map<TemplatePartyEnum, List<String>> prepareEmailToSendMail(String email) {

		EnumMap<TemplatePartyEnum, List<String>> emailData = new EnumMap<>(TemplatePartyEnum.class);
        ArrayList<String> emails = new ArrayList<>();
        emails.add(email);
        emailData.put(TemplatePartyEnum.USER, emails);
		ArrayList<String> internalEmails = new ArrayList<>();
		String cc = configurationService.getConfigValue(String.class, ConfigurationKeyImpl.PRIMOS_INTERNAL_MAIL);
		String ccArray[] = null;
		if (StringUtils.isNotBlank(cc)) {
			ccArray = cc.split(",");
			internalEmails.addAll(Arrays.asList(ccArray));
		}

		emailData.put(TemplatePartyEnum.PRIMOS_INTERNAL, internalEmails);
		return emailData;
	}
	private Map<TemplatePartyEnum, List<String>> prepareEmailToSendMail(String email,ConfigurationKey configurationKey) {

		EnumMap<TemplatePartyEnum, List<String>> emailData = new EnumMap<>(TemplatePartyEnum.class);
        ArrayList<String> emails = new ArrayList<>();
        emails.add(email);
        emailData.put(TemplatePartyEnum.USER, emails);
		ArrayList<String> internalEmails = new ArrayList<>();
		String cc = configurationService.getConfigValue(String.class, configurationKey);
		String ccArray[] = null;
		if (StringUtils.isNotBlank(cc)) {
			ccArray = cc.split(",");
			internalEmails.addAll(Arrays.asList(ccArray));
		}

		emailData.put(TemplatePartyEnum.PRIMOS_INTERNAL, internalEmails);
		return emailData;
	}
	private Map<TemplatePartyEnum, List<String>> prepareEmailToSendMailToCareer(String email,ConfigurationKey configurationKey,Career career) {
		String careerEmailId= career.getPickupPoint().getEmail();
		EnumMap<TemplatePartyEnum, List<String>> emailData = new EnumMap<>(TemplatePartyEnum.class);
        ArrayList<String> emails = new ArrayList<>();
        emails.add(email);
        emailData.put(TemplatePartyEnum.USER, emails);
		ArrayList<String> internalEmails = new ArrayList<>();
		internalEmails.add(careerEmailId);
		String cc = configurationService.getConfigValue(String.class, configurationKey);
		String ccArray[] = null;
		if (StringUtils.isNotBlank(cc)) {
			ccArray = cc.split(",");
			internalEmails.addAll(Arrays.asList(ccArray));
		}

		emailData.put(TemplatePartyEnum.PRIMOS_INTERNAL, internalEmails);
		return emailData;
	}

	@Override
	public void cateringRequest(Catering catering) {
		String format = "DEFAULT";
		BIEvent event = BIEventImpl.CATERING_MAIL;
		Map<String, Object> prepareNotifDataForCateringRequest = prepareNotifDataForCateringRequest(event, catering);
		Map<TemplatePartyEnum, List<String>> prepareEmailToSendMail = prepareEmailToSendMail(catering.getEmail(),ConfigurationKeyImpl.PRIMOS_INTERNAL_CATERING_MAIL);
		notificationService.sendNotification(event, format, prepareEmailToSendMail, Collections.emptyMap(),
				prepareNotifDataForCateringRequest, Optional.of(CateringView.BasicView.class));

	}

	private Map<String, Object> prepareNotifDataForCateringRequest(BIEvent event, Catering catering) {

		Map<String, Object> notificationData = new HashMap<>();
		notificationData.put(event.getTemplateKeys().get(TemplateObjectKeysImpl.CATERING), catering);
		UserVo user = new UserVo();
		user.setEmail(catering.getEmail());
		notificationData.put(event.getTemplateKeys().get(TemplateObjectKeysImpl.USER), user);
		notificationData.put(event.getTemplateKeys().get(TemplateObjectKeysImpl.IMAGE_BASE_URL),
				configurationService.getConfigValue(String.class, ConfigurationKeyImpl.PRIMOS_IMAGE_BASE_URL));
		return notificationData;

	}

}
