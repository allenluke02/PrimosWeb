package com.bi.services;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bi.exception.EntityNotFoundException;
import com.bi.model.hire.Career;
import com.bi.model.location.PickupPoint;
import com.bi.repositories.CareerRepository;
import com.bi.services.event.NotificationService;
import com.bi.utils.PaginationConfig;
import com.bi.vo.ApiListResponseVo;

@Service
public class CareerServiceImpl extends SearchServiceImpl<Career, CareerRepository> implements CareerService {

	private static final Logger LOGGER = LoggerFactory.getLogger(CareerServiceImpl.class);

	@Autowired
	private CareerRepository careerRepository;

	@Autowired
	private NotificationService notificationService;

	@Autowired
	private MessageByLocaleService messageByLocaleService;
	
	@Autowired
	private PickupPointsService pickupPointsService;
	
	@Override
	public Career createCareer(@Valid Career career) {
		Career career1 = careerRepository.save(career);
		PickupPoint pickupPoint=pickupPointsService.getPickupPointById(career.getPickupPoint().getId());
		career1.setPickupPoint(pickupPoint);
		notificationService.submitCareer(career1);
		return career1;
	}

	@Override
	public ApiListResponseVo<Career> getAllCareers(String search, PaginationConfig paginationConfig) {
		List<Career> careerList= search(search, Career.class, paginationConfig, null, null,null);
		ApiListResponseVo<Career> resp= new ApiListResponseVo<Career>();
		if(!CollectionUtils.isEmpty(careerList)) {
			resp.setData(careerList).setSuccess(Boolean.TRUE).setTotal(count(search, Career.class, null, null,null));
		}else {
			resp.setData(careerList).setSuccess(Boolean.FALSE).setTotal(0l);
		}
		return resp;
	}

	@Override
	public Career getCareersById(Long id) {
		return careerRepository.findById(id).orElseThrow(() -> new EntityNotFoundException(
				messageByLocaleService.getMessage("err.career.not.found", id.toString())));
	}

	@Override
	public Career update(Career career) {
		return careerRepository.save(career);
	}

	@Override
	public void deleteCareersById(Long id) {
		Career career = getCareersById(id);
		careerRepository.delete(career);
	}

}
