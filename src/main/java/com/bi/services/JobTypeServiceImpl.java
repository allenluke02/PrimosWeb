package com.bi.services;

import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bi.exception.EntityNotFoundException;
import com.bi.model.hire.JobType;
import com.bi.repositories.JobTypeRepository;

@Service
public class JobTypeServiceImpl implements JobTypeService {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(JobTypeServiceImpl.class);
	
	@Autowired
	private JobTypeRepository jobTypeRepository;
	
	@Autowired
	private MessageByLocaleService messageByLocaleService;

	@Override
	public JobType createJobType(@Valid JobType jobType) {
		return jobTypeRepository.save(jobType);
	}

	@Override
	public List<JobType> getAllJobTypes() {
		return jobTypeRepository.findAll();
	}

	@Override
	public JobType update(JobType jobType) {
		return jobTypeRepository.save(jobType);
	}

	@Override
	public JobType getJobTypesById(Long id) {
		return jobTypeRepository.findById(id).orElseThrow(() -> new EntityNotFoundException(
				messageByLocaleService.getMessage("err.jobtype.not.found", id.toString())));
	}

	@Override
	public void deleteJobTypesById(Long id) {
		JobType jobType = getJobTypesById(id);
		jobTypeRepository.delete(jobType);
	}


}
