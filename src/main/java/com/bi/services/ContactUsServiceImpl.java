package com.bi.services;

import java.util.List;

import javax.validation.Valid;

import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bi.exception.EntityNotFoundException;
import com.bi.model.ContactUs;
import com.bi.model.Feedback;
import com.bi.model.hire.Career;
import com.bi.repositories.ContactUsRepository;
import com.bi.services.event.NotificationService;
import com.bi.utils.PaginationConfig;
import com.bi.vo.ApiListResponseVo;

@Service
public class ContactUsServiceImpl extends SearchServiceImpl<ContactUs, ContactUsRepository> implements ContactUsService {

	private static final Logger LOGGER = LoggerFactory.getLogger(ContactUsServiceImpl.class);

	@Autowired
	private ContactUsRepository contactUsRepository;

	@Autowired
	private MessageByLocaleService messageByLocaleService;

	@Autowired
	private NotificationService notificationService;

	@Override
	public ContactUs createContactUs(@Valid ContactUs contactUs) {
		ContactUs contactUs1 = contactUsRepository.save(contactUs);
		notificationService.createContactUs(contactUs1);
		return contactUs1;
	}

	@Override
	public ApiListResponseVo<ContactUs> getAllContactUs(String search, PaginationConfig paginationConfig) {
		List<ContactUs> contactusList= search(search, ContactUs.class, paginationConfig, null, null,null);
		ApiListResponseVo<ContactUs> resp= new ApiListResponseVo<ContactUs>();
		if(!CollectionUtils.isEmpty(contactusList)) {
			resp.setData(contactusList).setSuccess(Boolean.TRUE).setTotal(count(search, ContactUs.class, null, null,null));
		}else {
			resp.setData(contactusList).setSuccess(Boolean.FALSE).setTotal(0l);
		}
		return resp; 
	}

	@Override
	public ContactUs getContactUsById(Long id) {
		return contactUsRepository.findById(id).orElseThrow(() -> new EntityNotFoundException(
				messageByLocaleService.getMessage("err.contactus.not.found", id.toString())));
	}

	@Override
	public ContactUs update(ContactUs contactUs) {
		return contactUsRepository.save(contactUs);
	}

	@Override
	public void deleteContactUsById(Long id) {
		ContactUs contactUs = getContactUsById(id);
		contactUsRepository.delete(contactUs);
	}


}
