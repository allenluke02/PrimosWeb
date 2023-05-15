package com.bi.services;

import java.util.List;

import javax.validation.Valid;

import com.bi.model.hire.Career;
import com.bi.utils.PaginationConfig;
import com.bi.vo.ApiListResponseVo;

public interface CareerService {

	Career createCareer(@Valid Career career);

	ApiListResponseVo<Career> getAllCareers(String search, PaginationConfig paginationConfig);

	Career getCareersById(Long id);

	Career update(Career career);

	void deleteCareersById(Long id);

	
}
