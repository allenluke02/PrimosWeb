package com.bi.services;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bi.exception.EntityNotFoundException;
import com.bi.model.catering.CateringPrice;
import com.bi.repositories.CateringPriceRepository;
import com.bi.utils.PaginationConfig;

@Service
public class CateringPriceServiceImpl extends SearchServiceImpl<CateringPrice, CateringPriceRepository> implements CateringPriceService {

	private static final Logger LOGGER = LoggerFactory.getLogger(CateringPriceServiceImpl.class);

	@Autowired
	private CateringPriceRepository cateringPriceRepository;

	@Autowired
	private MessageByLocaleService messageByLocaleService;


	@Override
	public CateringPrice createCateringPrice(CateringPrice cateringPrice) {
			return cateringPriceRepository.save(cateringPrice);
	}

	@Override
	public List<CateringPrice> getAllCateringPrice(String search, PaginationConfig paginationConfig) {
		return search(search, CateringPrice.class, paginationConfig, null, null,null);
	}


	@Override
	public CateringPrice getCateringPriceById(Long id) {
		return cateringPriceRepository.findById(id).orElseThrow(() -> new EntityNotFoundException(
				messageByLocaleService.getMessage("err.catering.price.not.found", id.toString())));
	}


	@Override
	public CateringPrice update(CateringPrice cateringPrice) {
		return cateringPriceRepository.save(cateringPrice);
	}


	@Override
	public void deleteCateringPriceById(Long id) {
		CateringPrice cateringPrice = getCateringPriceById(id);
		cateringPriceRepository.delete(cateringPrice);
	}

}
