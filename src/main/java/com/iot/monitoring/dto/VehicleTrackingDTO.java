package com.iot.monitoring.dto;

import com.iot.monitoring.model.VehicleTracking;
import com.iot.monitoring.model.VehicleTracking.EventType;

public record VehicleTrackingDTO(String deviceId, Double latitude, Double longitude, Double speed, Double angle,
		Long mileage, EventType eventType) {

	public static VehicleTrackingDTO mapToVehicleTrackingDTO(VehicleTracking trackingData) {
		return new VehicleTrackingDTO(trackingData.getDeviceId(), trackingData.getLatitude(),
				trackingData.getLongitude(), trackingData.getSpeed(), trackingData.getAngle(),
				trackingData.getMileage(), trackingData.getEventType());
	}

}
