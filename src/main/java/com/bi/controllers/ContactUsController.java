package com.bi.controllers;

import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bi.model.ContactUs;
import com.bi.model.hire.Career;
import com.bi.services.ContactUsService;
import com.bi.utils.BIConstant;
import com.bi.utils.PaginationConfig;
import com.bi.vo.ApiListResponseVo;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
public class ContactUsController {
	
	private static final Logger logger = LoggerFactory.getLogger(ContactUsController.class);

	@Autowired
	private ContactUsService contactUsService;
	
	@ApiOperation(value = "create a new ContactUs")
	@PostMapping(value = "/contactUs", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ContactUs createContactUs(@Valid @RequestBody ContactUs contactUs) {
		contactUs.setId(null);
		return contactUsService.createContactUs(contactUs);
	}
	
	@GetMapping("/contactUs")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Success"),
			@ApiResponse(code = 500, message = "Internal server error") })
	@ApiOperation(value = "Get all Contact Us")
	public ApiListResponseVo<ContactUs> getAllContactUs(
			@RequestParam(value = "pageSize", required = false,defaultValue = "10") Integer pageSize,
			@RequestParam(value = "pageNumber", required = false,defaultValue = "0") Integer pageNumber,
			@RequestParam(value = "search", required = false) String search,
			@RequestParam(value = "sort", required = false, defaultValue = BIConstant.ASC) String sortDirection,
			@RequestParam(value = "sortOn", required = false,defaultValue = "id") String sortOn) {
		PaginationConfig paginationConfig = new PaginationConfig(pageSize, pageNumber, sortDirection, sortOn);
		return contactUsService.getAllContactUs(search,paginationConfig);
	}
	
	@GetMapping("/contactUs/{id}")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Success"),
			@ApiResponse(code = 500, message = "Internal server error") })
	@ApiOperation(value = "Get ContactUs with id")
	public ContactUs getContactUsById(@PathVariable("id") Long id) {
		return contactUsService.getContactUsById(id);
	}
	
	@ApiOperation(value = "Update a new Catering ")
	@PutMapping(value = "/contactUs/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ContactUs update(@Valid @PathVariable("id") Long contactUsId, @RequestBody ContactUs contactUs) {
		contactUs.setId(contactUsId);
		return contactUsService.update(contactUs);
	}

	@ApiOperation(value = "Delete a Catering ")
	@ApiResponses({ @ApiResponse(code = 200, message = "ok"),
			@ApiResponse(code = 500, message = "Internal server error") })
	@DeleteMapping("/contactUs/{id}")
	public void deleteContactUsById(@PathVariable("id") Long id) {
		contactUsService.deleteContactUsById(id);
	}
}
