package com.bi.services;

import org.springframework.stereotype.Service;

import com.bi.model.hire.Job;
import com.bi.utils.PaginationConfig;
import com.bi.vo.ApiListResponseVo;

@Service
public interface JobService {

	Job createJob(Job job);

	ApiListResponseVo<Job> getAllJobs(String search, Long jobTypeId, Long pickupPointId, PaginationConfig paginationConfig, Boolean isExpired);
	
	Job getJobsById(Long id);

	Job update(Job job);

	void deleteJobsById(Long id);

}
