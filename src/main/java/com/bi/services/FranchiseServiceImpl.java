package com.bi.services;

import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bi.config.ConfigurationService;
import com.bi.exception.BIValidationException;
import com.bi.exception.EntityNotFoundException;
import com.bi.model.franchise.Franchise;
import com.bi.repositories.FranchiseRepository;
import com.bi.services.event.NotificationService;
import com.bi.utils.BIUtils;
import com.bi.utils.PaginationConfig;
import com.bi.vo.ApiListResponseVo;

@Service
public class FranchiseServiceImpl extends SearchServiceImpl<Franchise, FranchiseRepository> implements FranchiseService {

	private static final Logger LOGGER = LoggerFactory.getLogger(FranchiseServiceImpl.class);

	@Autowired
	private FranchiseRepository franchiseRepository;

	@Autowired
	private MessageByLocaleService messageByLocaleService;
	
	@Autowired
	private ConfigurationService configurationService;

	@Autowired
	private NotificationService notificationService;
	
	@Autowired
	BIUtils biUtils;

	@Override
	public Franchise createFranchise(Franchise franchise) {
		if(franchise.getReCaptcha() == null ) {
			throw new BIValidationException(messageByLocaleService.getMessage("err.recaptcha.empty"));
		}else {
			if(biUtils.isValidCaptcha(franchise.getReCaptcha())) {
				Franchise franchiseResp =  franchiseRepository.save(franchise);
				notificationService.franchiseRequest(franchiseResp);
				return franchiseResp;
			}else {
				throw new BIValidationException(messageByLocaleService.getMessage("err.invalid.recaptcha"));
			}
		}
	}

	

	@Override
	public ApiListResponseVo<Franchise> getAllFranchises(String search, PaginationConfig paginationConfig) {
		List<Franchise> franchiseList= search(search, Franchise.class, paginationConfig, null, null,null);
		ApiListResponseVo<Franchise> resp= new ApiListResponseVo<Franchise>();
		if(!CollectionUtils.isEmpty(franchiseList)) {
			resp.setData(franchiseList).setSuccess(Boolean.TRUE).setTotal(count(search, Franchise.class, null, null,null));
		}else {
			resp.setData(franchiseList).setSuccess(Boolean.FALSE).setTotal(0l);
		}
		return resp;
	}

	@Override
	public Franchise getFranchisesById(Long id) {
		return franchiseRepository.findById(id).orElseThrow(() -> new EntityNotFoundException(
				messageByLocaleService.getMessage("err.franchise.not.found", id.toString())));
	}

	@Override
	public Franchise update(Franchise franchise) {
		return franchiseRepository.save(franchise);
	}

	@Override
	public void deleteFranchisesById(Long id) {
		Franchise franchise = getFranchisesById(id);
		franchiseRepository.delete(franchise);
	}

}
