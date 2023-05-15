package com.bi.services;

import java.util.List;

import javax.validation.Valid;

import com.bi.model.ContactUs;
import com.bi.utils.PaginationConfig;
import com.bi.vo.ApiListResponseVo;

public interface ContactUsService {

	ContactUs createContactUs(@Valid ContactUs contactUs);

	ApiListResponseVo<ContactUs> getAllContactUs(String search, PaginationConfig paginationConfig);

	ContactUs getContactUsById(Long id);

	ContactUs update(ContactUs contactUs);

	void deleteContactUsById(Long id);


}
