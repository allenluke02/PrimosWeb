package com.bi.services;

import javax.validation.Valid;

import com.bi.model.franchise.SiteSubmitter;
import com.bi.utils.PaginationConfig;
import com.bi.vo.ApiListResponseVo;

public interface SiteSubmitterService {

	SiteSubmitter createSiteSubmitters(@Valid SiteSubmitter siteSubmitter);

	ApiListResponseVo<SiteSubmitter> getAllSiteSubmitters(String search, PaginationConfig paginationConfig);

	SiteSubmitter getSiteSubmittersById(Long id);

	SiteSubmitter update(SiteSubmitter siteSubmitter);

	void deleteSiteSubmittersById(Long id);

	
}
