package com.iot.monitoring.registration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.iot.monitoring.model.DeviceMetadata;
import com.iot.monitoring.repository.DeviceMetadataRepository;

@Service
public class DeviceRegistrationService {

	@Autowired
	private DeviceMetadataRepository deviceMetadataRepository;

	public void registerDevice(DeviceMetadata deviceMetadata) {
		deviceMetadataRepository.save(deviceMetadata);
	}
}
