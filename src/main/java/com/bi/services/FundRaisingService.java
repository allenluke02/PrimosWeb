package com.bi.services;

import com.bi.model.FundRaising;
import com.bi.utils.PaginationConfig;
import com.bi.vo.ApiListResponseVo;

import org.springframework.stereotype.Service;

@Service
public interface FundRaisingService {

    FundRaising createFundRaising(FundRaising fundRaising);

    ApiListResponseVo<FundRaising> getAllFundRaising(String search, Boolean isActive, PaginationConfig paginationConfig);

    FundRaising getFundRaisingById(Long id);

    FundRaising update(FundRaising fundRaising);

    void deleteFundRaisingById(Long id);

	void changeFundRaisingStatus(Long id, Boolean isApproved);

}
