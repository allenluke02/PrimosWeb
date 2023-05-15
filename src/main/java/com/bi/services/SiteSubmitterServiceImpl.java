package com.bi.services;

import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bi.exception.BIValidationException;
import com.bi.exception.EntityNotFoundException;
import com.bi.model.franchise.Franchise;
import com.bi.model.franchise.SiteSubmitter;
import com.bi.repositories.SiteSubmitterRepository;
import com.bi.services.event.NotificationService;
import com.bi.utils.BIUtils;
import com.bi.utils.PaginationConfig;
import com.bi.vo.ApiListResponseVo;

@Service
public class SiteSubmitterServiceImpl extends SearchServiceImpl<SiteSubmitter, SiteSubmitterRepository> implements SiteSubmitterService {

	private static final Logger LOGGER = LoggerFactory.getLogger(SiteSubmitterServiceImpl.class);

	@Autowired
	private SiteSubmitterRepository siteSubmitterRepository;

	@Autowired
	private MessageByLocaleService messageByLocaleService;

	@Autowired
	private NotificationService notificationService;
	
	@Autowired
	BIUtils biUtils;

	@Override
	public SiteSubmitter createSiteSubmitters(SiteSubmitter siteSubmitter) {
		if(siteSubmitter.getReCaptcha() == null ) {
			throw new BIValidationException(messageByLocaleService.getMessage("err.recaptcha.empty"));
		}else {
			if(biUtils.isValidCaptcha(siteSubmitter.getReCaptcha())) {
				SiteSubmitter siteSubmitterResp = siteSubmitterRepository.save(siteSubmitter);
				notificationService.siteSubmitterRequest(siteSubmitter);
				return siteSubmitterResp;
			}else {
				throw new BIValidationException(messageByLocaleService.getMessage("err.invalid.recaptcha"));
			}
		}
	}

	@Override
	public ApiListResponseVo<SiteSubmitter> getAllSiteSubmitters(String search, PaginationConfig paginationConfig) {
		List<SiteSubmitter> siteSubmitterList= search(search, SiteSubmitter.class, paginationConfig, null, null,null);
		ApiListResponseVo<SiteSubmitter> resp= new ApiListResponseVo<SiteSubmitter>();
		if(!CollectionUtils.isEmpty(siteSubmitterList)) {
			resp.setData(siteSubmitterList).setSuccess(Boolean.TRUE).setTotal(count(search, SiteSubmitter.class, null, null,null));
		}else {
			resp.setData(siteSubmitterList).setSuccess(Boolean.FALSE).setTotal(0l);
		}
		return resp;
	}

	@Override
	public SiteSubmitter getSiteSubmittersById(Long id) {
		return siteSubmitterRepository.findById(id).orElseThrow(() -> new EntityNotFoundException(
				messageByLocaleService.getMessage("err.site.submitter.not.found", id.toString())));
	}

	@Override
	public SiteSubmitter update(SiteSubmitter siteSubmitter) {
		return siteSubmitterRepository.save(siteSubmitter);
	}

	@Override
	public void deleteSiteSubmittersById(Long id) {
		SiteSubmitter siteSubmitter = getSiteSubmittersById(id);
		siteSubmitterRepository.delete(siteSubmitter);		
	}

	

}
