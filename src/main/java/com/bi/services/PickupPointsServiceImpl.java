package com.bi.services;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientResponseException;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import com.bi.config.ConfigurationService;
import com.bi.exception.EntityNotFoundException;
import com.bi.model.ConfigurationKeyImpl;
import com.bi.model.QAddress;
import com.bi.model.location.PickupPoint;
import com.bi.model.location.PickupPointServiceProviderId;
import com.bi.model.location.QPickupPoint;
import com.bi.repositories.CountryRepository;
import com.bi.repositories.PickupPointRepository;
import com.bi.repositories.PickupPointServiceProviderRepository;
import com.bi.repositories.StateRepository;
import com.bi.service.HTTPConnectService;
import com.bi.utils.BIConstant;
import com.bi.utils.BIUtils;
import com.bi.utils.PaginationConfig;
import com.bi.vo.ApiListResponseVo;
import com.bi.vo.GoogleMapResponseVo;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.BooleanOperation;
import com.querydsl.core.types.dsl.Expressions;

@Service
public class PickupPointsServiceImpl extends SearchServiceImpl<PickupPoint, PickupPointRepository>
		implements PickupPointsService {

	private static final Logger LOGGER = LoggerFactory.getLogger(PickupPointsServiceImpl.class);

	@Autowired
	private PickupPointRepository pickupPointRepos;

	@Autowired
	private MessageByLocaleService messageByLocaleService;
	
	@Autowired
	PickupPointServiceProviderRepository pickupPtServiceProviderRepo;
	
	@Autowired
	StateRepository stateRepository;
	
	@Autowired
    private ConfigurationService configurationService;

	@Autowired
	CountryRepository countryRepository;
	
	@Autowired
	BIUtils  biUtils;

	@Autowired
	HTTPConnectService httpConnectionService;
	@Override
	public PickupPoint createPickupPoint(PickupPoint pickupPoint) {
		   pickupPoint.setActive(true);
			pickupPointRepos.save(pickupPoint);
			if (!CollectionUtils.isEmpty(pickupPoint.getPickupPtServiceProviders())) {
				pickupPoint.getPickupPtServiceProviders().stream().forEach(pvd -> {
					pvd.setPickupPointServiceProviderId(new PickupPointServiceProviderId()
							.setPickupPointId(pickupPoint.getId()).setServiceProviderId(pvd.getServiceProvider().getId()));
					pvd.setPickupPoint(pickupPoint);
				}); 
				pickupPtServiceProviderRepo.saveAll(pickupPoint.getPickupPtServiceProviders());
			}
		return pickupPoint;
	}

	@Override
	public ApiListResponseVo<PickupPoint> getAllPickupPoints(String search, Long countyId, boolean isOutdoorDining,
			boolean isDriveThru, Boolean isActive, PaginationConfig paginationConfig, String lat, String lan, Long pickupPointId,boolean isIndoorDining) {
		List<PickupPoint> pickupPointList=null;
		BooleanExpression predicate = Expressions.TRUE.isTrue();
		BooleanExpression searchPredicate = null;
		if (StringUtils.isNotBlank(lat) && StringUtils.isNotBlank(lan)) {
			pickupPointList= getPickuppointsByCurrentLoc(lat, lan);
		}else {
			
			if (countyId != null) {
				predicate = predicate.and(QPickupPoint.pickupPoint.county.id.eq(countyId));
			}
			
			if(pickupPointId != null) {
				predicate = predicate.and(QPickupPoint.pickupPoint.id.eq(pickupPointId));
			}

			if (isOutdoorDining) {
				predicate = predicate.and(QPickupPoint.pickupPoint.outdoorDiningAvlb.eq(isOutdoorDining));
			}

			if (isDriveThru) {
				predicate = predicate.and(QPickupPoint.pickupPoint.driveThruAvlb.eq(isDriveThru));
			}
			
			if(isActive !=null) {
				predicate=predicate.and(QPickupPoint.pickupPoint.active.eq(isActive));
			}

			if (isIndoorDining) {
				predicate = predicate.and(QPickupPoint.pickupPoint.indoorDiningAvlb.eq(isIndoorDining));
			}

			if (search != null && !"".equals(search)) {
				QAddress addrs = QPickupPoint.pickupPoint.address;
				searchPredicate = addrs.address1.like("%" + search + "%").or(addrs.state.name.like("%" + search + "%"))
						.or(addrs.city.like("%" + search + "%")).or(addrs.zip.like("%" + search + "%"))
						.or(addrs.address2.like("%" + search + "%"));
			}
			pickupPointList= search(search, PickupPoint.class, paginationConfig, null, (BooleanOperation) predicate,
					(BooleanOperation) searchPredicate);
		}
		
		ApiListResponseVo<PickupPoint> resp= new ApiListResponseVo<PickupPoint>();
		if(!CollectionUtils.isEmpty(pickupPointList)) {
			if (StringUtils.isNotBlank(lat) && StringUtils.isNotBlank(lan)) {
				resp.setData(pickupPointList).setSuccess(Boolean.TRUE).setTotal(Long.valueOf(pickupPointList.size()));
			}else {
			resp.setData(pickupPointList).setSuccess(Boolean.TRUE).setTotal(count(search, PickupPoint.class, null, (BooleanOperation) predicate,
					(BooleanOperation) searchPredicate));
			}
		}else {
			resp.setData(pickupPointList).setSuccess(Boolean.FALSE).setTotal(0l);
		}
		return resp;
	}

	private List<PickupPoint> getPickuppointsByCurrentLoc(String currentLat, String currentLan) {
		List<PickupPoint> pickupPointList= (List<PickupPoint>) pickupPointRepos.findAll(QPickupPoint.pickupPoint.active.eq(true));
		
		List<String> latLanList=new ArrayList<>();
		
		int window=BIConstant.LOCATION_SEARCH_WINDOW;
		int startIndex=0;
		for(int i=0;i < pickupPointList.size();i++) {
			PickupPoint pt=pickupPointList.get(i);
			latLanList.add(pt.getLat()+"%2C"+pt.getLng());
			if(i % window == 0) {
				if(i != 0) {
					startIndex=i-window;
					updateDistance(pickupPointList,startIndex,i,latLanList,currentLat,currentLan);
				}
			}
		}
		if(startIndex ==0 || (startIndex != pickupPointList.size() - window)) {
			if(startIndex == 0) {
				updateDistance(pickupPointList,startIndex,pickupPointList.size(),latLanList,currentLat,currentLan);
			}else {
				updateDistance(pickupPointList,startIndex+window,pickupPointList.size(),latLanList,currentLat,currentLan);
			}
			
		}
		
		pickupPointList = pickupPointList.stream()
				.filter(pt -> pt.getDistance() != null && pt.getDistance().doubleValue() != -1.0)
				.sorted((o1, o2) -> o1.getDistance().compareTo(o2.getDistance())).collect(Collectors.toList());
		return pickupPointList;
	}

	private void updateDistance(List<PickupPoint> pickupPointList, int startIndex, int endIndex, List<String> latLanList, String currentLat, String currentLan) {
		GoogleMapResponseVo mapResp= null;
		List<String> splitLatList=latLanList.subList(startIndex, endIndex);
		String splitRes = StringUtils.join(splitLatList, "%7C");
		
		String url = configurationService.getConfigValue(String.class, ConfigurationKeyImpl.GOOGLE_DISTANCE_MATRIX_URL);
		UriComponents builder = UriComponentsBuilder.fromUriString(url)
				.queryParam("origins", currentLat+","+currentLan)
				.queryParam("destinations", splitRes)
				.queryParam("mode", BIConstant.MODE_DRIVING)
				.queryParam("language", BIConstant.LANG_ENG)
				.queryParam("key", configurationService.getConfigValue(String.class, ConfigurationKeyImpl.GOOGLE_DISTANCE_MATRIX_API_KEY))
				.build(false);
		
		try {
				LOGGER.info("updateDistance : Calling GDM url " + builder.toUriString());
				HttpHeaders headers = new HttpHeaders();
				headers.setContentType(MediaType.APPLICATION_JSON);
				headers.setAccept(Collections.singletonList((MediaType.APPLICATION_JSON)));
				 
				HttpEntity<Object> requestEntity = new HttpEntity<>(headers);
				
				mapResp=httpConnectionService.createHttpRequest(builder.toUriString(), HttpMethod.GET, requestEntity, null, GoogleMapResponseVo.class);
				if(mapResp != null && "OK".equalsIgnoreCase(mapResp.getStatus())) {
					List<GoogleMapResponseVo.Element> elements=mapResp.getRows().get(0).getElements();
					for(int i=0;i< elements.size();i++) {
						PickupPoint pickUpPt= pickupPointList.get(i+startIndex);
						GoogleMapResponseVo.Element ele=elements.get(i);
						if(ele.getDistance() != null) {
							pickUpPt.setDistance(Double.valueOf(ele.getDistance().getValue()));
						}else {
							pickUpPt.setDistance(-1.0);
						}
					}
				}
		}
		catch (RestClientResponseException exception) {
			LOGGER.error("Error in Google Distance Matrix Api url{} with status {} ",  builder.toUriString(),exception.getRawStatusCode());
		}
		catch (Exception e) {
			LOGGER.error("Error fetching Distance with error : {} ",e);
		}
	}

	@Override
	public PickupPoint getPickupPointById(Long id) {
		return pickupPointRepos.findById(id).orElseThrow(() -> new EntityNotFoundException(
				messageByLocaleService.getMessage("err.pickup.point.not.found", id.toString())));
	}

	
	@Override
	public PickupPoint update(PickupPoint pickupPoint) {
		if (!CollectionUtils.isEmpty(pickupPoint.getPickupPtServiceProviders())) {
			pickupPoint.getPickupPtServiceProviders().stream().forEach(pvd -> {
				pvd.setPickupPointServiceProviderId(new PickupPointServiceProviderId()
						.setPickupPointId(pickupPoint.getId()).setServiceProviderId(pvd.getServiceProvider().getId()));
				pvd.setPickupPoint(pickupPoint);
			}); 
			pickupPtServiceProviderRepo.saveAll(pickupPoint.getPickupPtServiceProviders());
		}
		return pickupPointRepos.save(pickupPoint);
	}

	@Override
	public void deletePickupPointById(Long id) {
		PickupPoint pts = getPickupPointById(id);
		pickupPointRepos.delete(pts);
	}

	@Override
	public void changePickupPointStatus(Long id,boolean active) {
		PickupPoint pts = getPickupPointById(id);
		pts.setActive(active);
		pickupPointRepos.save(pts);
	}

}
