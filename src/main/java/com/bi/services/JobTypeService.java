package com.bi.services;

import java.util.List;

import javax.validation.Valid;

import com.bi.model.hire.JobType;

public interface JobTypeService {

	JobType createJobType(@Valid JobType jobType);

	List<JobType> getAllJobTypes();

	JobType update(JobType jobType);

	JobType getJobTypesById(Long id);

	void deleteJobTypesById(Long id);

	

}
