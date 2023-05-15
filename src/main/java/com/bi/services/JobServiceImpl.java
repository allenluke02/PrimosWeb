package com.bi.services;

import java.util.Date;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bi.exception.EntityNotFoundException;
import com.bi.model.QAddress;
import com.bi.model.hire.Job;
import com.bi.model.hire.QJob;
import com.bi.repositories.JobRepository;
import com.bi.utils.PaginationConfig;
import com.bi.vo.ApiListResponseVo;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.BooleanOperation;
import com.querydsl.core.types.dsl.Expressions;

@Service
public class JobServiceImpl extends SearchServiceImpl<Job, JobRepository> implements JobService {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(JobServiceImpl.class);
	
	@Autowired
	private JobRepository jobRepository;
	
	@Autowired
	private MessageByLocaleService messageByLocaleService;

	@Override
	public Job createJob(Job job) {
		return jobRepository.save(job);
	}

	@Override
	public ApiListResponseVo<Job> getAllJobs(String search, Long jobTypeId, Long pickupPointId, PaginationConfig paginationConfig, Boolean isExpired) {
		BooleanExpression predicate = Expressions.TRUE.isTrue();
		BooleanExpression searchPredicate=null;

		predicate = predicate.and(QJob.job.postingDt.loe(new Date()));
		
		if (jobTypeId != null) {
			predicate = predicate.and(QJob.job.jobType.id.eq(jobTypeId));
		}

		if (pickupPointId != null) {
			predicate = predicate.and(QJob.job.pickupPoint.any().id.eq(pickupPointId));
		}
		
		if(isExpired != null) {
			predicate = predicate.and(QJob.job.isExpired.eq(isExpired));
		}
		
		if(search != null && !"".equals(search)) {
			QAddress addrs= QJob.job.pickupPoint.any().address;
			 searchPredicate=addrs.address1.like("%"+search+"%").or(addrs.state.name.like("%"+search+"%")).
					or(addrs.city.like("%"+search+"%")).or(addrs.zip.like("%"+search+"%")).or(addrs.address2.like("%"+search+"%"));
		}

		List<Job> jobsList= search(search, Job.class, paginationConfig, null, (BooleanOperation) predicate,(BooleanOperation) searchPredicate);
		
		ApiListResponseVo<Job> resp= new ApiListResponseVo<Job>();
		if(!CollectionUtils.isEmpty(jobsList)) {
			resp.setData(jobsList).setSuccess(Boolean.TRUE).setTotal(count(search, Job.class, null, null,null));
		}else {
			resp.setData(jobsList).setSuccess(Boolean.FALSE).setTotal(0l);
		}
		return resp;
	}

	@Override
	public Job getJobsById(Long id) {
		return jobRepository.findById(id).orElseThrow(() -> new EntityNotFoundException(
				messageByLocaleService.getMessage("err.job.not.found", id.toString())));
	}

	@Override
	public Job update(Job job) {
		return jobRepository.save(job);
	}

	@Override
	public void deleteJobsById(Long id) {
		Job job = getJobsById(id);
		jobRepository.delete(job);
		
	}

}
