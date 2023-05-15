package com.bi.services;

import com.bi.model.location.PickupPoint;
import com.bi.utils.PaginationConfig;
import com.bi.vo.ApiListResponseVo;

public interface PickupPointsService {

	PickupPoint createPickupPoint(PickupPoint pickupPoint);

	ApiListResponseVo<PickupPoint> getAllPickupPoints(String search, Long countyId, boolean isOutdoorDining,
			boolean isDriveThru, Boolean isActive, PaginationConfig paginationConfig, String lat, String lan, Long pickupPointId,boolean isIndoorDining);

	PickupPoint getPickupPointById(Long id);

	PickupPoint update(PickupPoint pickupPoint);

	void deletePickupPointById(Long id);

	void changePickupPointStatus(Long id, boolean active);

}
