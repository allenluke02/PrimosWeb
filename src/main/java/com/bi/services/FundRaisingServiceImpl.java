package com.bi.services;

import java.util.Date;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bi.exception.EntityNotFoundException;
import com.bi.model.FundRaising;
import com.bi.model.QAddress;
import com.bi.model.QFundRaising;
import com.bi.model.location.QPickupPoint;
import com.bi.repositories.FundRaisingRepository;
import com.bi.services.event.NotificationService;
import com.bi.utils.PaginationConfig;
import com.bi.vo.ApiListResponseVo;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.BooleanOperation;
import com.querydsl.core.types.dsl.Expressions;

@Service
public class FundRaisingServiceImpl extends SearchServiceImpl<FundRaising, FundRaisingRepository> implements FundRaisingService {


    private static final Logger LOGGER = LoggerFactory.getLogger(FundRaisingServiceImpl.class);

    @Autowired
    private MessageByLocaleService messageByLocaleService;

    @Autowired
    private FundRaisingRepository fundRaisingRepository;

    @Autowired
    private NotificationService notificationService;

    @Override
    public FundRaising createFundRaising(FundRaising fundRaising) {
        FundRaising fundRaisingResp = fundRaisingRepository.save(fundRaising);
        notificationService.fundRaisingRequest(fundRaising);
        return fundRaisingResp;
    }

    @Override
    public ApiListResponseVo<FundRaising> getAllFundRaising(String search, Boolean isActive, PaginationConfig paginationConfig) {

		BooleanExpression predicate = Expressions.TRUE.isTrue();
		
		BooleanExpression searchPredicate = null;

		if (isActive) {
			predicate = predicate.and(QFundRaising.fundRaising.date.after(new Date()).and(QFundRaising.fundRaising.endDate.loe(new Date())));
		}
		
		if (search != null && !"".equals(search)) {
			QPickupPoint pickupPoint=QFundRaising.fundRaising.storeLocation;
			QAddress addrs = pickupPoint.address;
			searchPredicate = pickupPoint.name.like("%" + search + "%")
					.or(addrs.address1.like("%" + search + "%"))
					.or(addrs.state.name.like("%" + search + "%"))
					.or(addrs.city.like("%" + search + "%"))
					.or(addrs.zip.like("%" + search + "%"))
					.or(addrs.address2.like("%" + search + "%"));
		}

		List<FundRaising> fundraisingList = search(search, FundRaising.class, paginationConfig, null,
				(BooleanOperation) predicate, (BooleanOperation) searchPredicate);
		ApiListResponseVo<FundRaising> resp = new ApiListResponseVo<FundRaising>();
		if (!CollectionUtils.isEmpty(fundraisingList)) {
			resp.setData(fundraisingList).setSuccess(Boolean.TRUE)
					.setTotal(count(search, FundRaising.class, null, (BooleanOperation) predicate,
							(BooleanOperation) searchPredicate));
		} else {
			resp.setData(fundraisingList).setSuccess(Boolean.FALSE).setTotal(0l);
		}
		return resp;
    }

    @Override
    public FundRaising getFundRaisingById(Long id) {
        return fundRaisingRepository.findOne(QFundRaising.fundRaising.id.eq(id))
                .orElseThrow(() -> new EntityNotFoundException(
                        messageByLocaleService.getMessage("err.fundRaising.not.found", id.toString())));
    }

    @Override
    public FundRaising update(FundRaising fundRaising) {
        return fundRaisingRepository.save(fundRaising);
    }

    @Override
    public void deleteFundRaisingById(Long id) {
        fundRaisingRepository.delete(getFundRaisingById(id));
    }

	@Override
	public void changeFundRaisingStatus(Long id, Boolean isApproved) {
		if (isApproved != null) {
			FundRaising fundRaising = getFundRaisingById(id);
			fundRaising.setIsApproved(isApproved);
			fundRaisingRepository.save(fundRaising);
			if(isApproved) {
				notificationService.approveFundRaisingRequest(fundRaising);
			}else {
				notificationService.disapproveFundRaisingRequest(fundRaising);
			}
		}
	}
}
