package com.bi.services;

import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bi.exception.BIValidationException;
import com.bi.exception.EntityNotFoundException;
import com.bi.model.catering.Catering;
import com.bi.model.catering.CateringPrice;
import com.bi.repositories.CateringPriceRepository;
import com.bi.repositories.CateringRepository;
import com.bi.services.event.NotificationService;
import com.bi.utils.BIUtils;
import com.bi.utils.PaginationConfig;
import com.bi.vo.ApiListResponseVo;

@Service
public class CateringServiceImpl extends SearchServiceImpl<Catering, CateringRepository> implements CateringService {

	private static final Logger LOGGER = LoggerFactory.getLogger(CateringServiceImpl.class);

	@Autowired
	private CateringRepository cateringRepository;
	
	@Autowired
	private CateringPriceRepository cateringPriceRepository;

	@Autowired
	private MessageByLocaleService messageByLocaleService;
	
	@Autowired
	private NotificationService notificationService;

	@Autowired
	BIUtils biUtils;

	@Override
	public Catering createCatering(Catering catering) {
		if(catering.getReCaptcha() == null ) {
			throw new BIValidationException(messageByLocaleService.getMessage("err.recaptcha.empty"));
		}else {
			if(biUtils.isValidCaptcha(catering.getReCaptcha())) {
				if(isValidPrice(catering)) {
					Catering  dbCatering= cateringRepository.save(catering);
					notificationService.cateringRequest(dbCatering);
					return dbCatering;
				}else {
					throw new BIValidationException(messageByLocaleService.getMessage("err.invalid.catering.data"));
				}
			}else {
				throw new BIValidationException(messageByLocaleService.getMessage("err.invalid.recaptcha"));
			}
		}
	}


	private boolean isValidPrice(Catering catering) {
		int numOfPeople=catering.getNoOfPeople();
		if(numOfPeople == 0 || numOfPeople <= -1) {
			throw new BIValidationException(messageByLocaleService.getMessage("err.invalid.catering.people.count"));
		}
		
		CateringPrice cp=catering.getCateringPrice();
		if(cp == null) {
			throw new BIValidationException(messageByLocaleService.getMessage("err.catering.price.empty"));
		}
		
		CateringPrice dbCp=cateringPriceRepository.findById(cp.getId()).orElseThrow(() -> new EntityNotFoundException(
				messageByLocaleService.getMessage("err.catering.price.not.found", cp.getId().toString())));
		if(numOfPeople < dbCp.getMinNoOfPeople()) {
			throw new BIValidationException(messageByLocaleService.getMessage("err.invalid.catering.min.people", String.valueOf(dbCp.getMinNoOfPeople())));
		}
		
		Double actPrice = dbCp.getPrice()* numOfPeople;
		actPrice =(double) Math.round (actPrice+(actPrice * dbCp.getServiceFee()/100));
		
		Double recPrice=catering.getTotalPrice();
		if(recPrice.compareTo(actPrice)!= 0) {
			throw new BIValidationException(messageByLocaleService.getMessage("err.catering.price.mismatch"));
		}
		return true;
	}


	@Override
	public ApiListResponseVo<Catering> getAllCatering(String search, PaginationConfig paginationConfig) {
		List<Catering> cateringList = search(search, Catering.class, paginationConfig, null, null, null);
		ApiListResponseVo<Catering> resp = new ApiListResponseVo<Catering>();
		if (!CollectionUtils.isEmpty(cateringList)) {
			resp.setData(cateringList).setSuccess(Boolean.TRUE)
					.setTotal(count(search, Catering.class, null, null, null));
		} else {
			resp.setData(cateringList).setSuccess(Boolean.FALSE).setTotal(0l);
		}
		return resp;
	}


	@Override
	public Catering getCateringById(Long id) {
		return cateringRepository.findById(id).orElseThrow(() -> new EntityNotFoundException(
				messageByLocaleService.getMessage("err.catering.not.found", id.toString())));
	}


	@Override
	public Catering update(Catering catering) {
		return cateringRepository.save(catering);
	}


	@Override
	public void deleteCateringById(Long id) {
		Catering catering = getCateringById(id);
		cateringRepository.delete(catering);
	}

}
