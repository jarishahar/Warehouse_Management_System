package com.jsp.wms.mapper;

import org.springframework.stereotype.Component;

import com.jsp.wms.entity.Storage;
import com.jsp.wms.requestdto.StorageRequest;
import com.jsp.wms.responsedto.StorageResponse;

@Component
public class StorageMapper {
	
	public Storage mapToStorage(StorageRequest storageRequest, Storage storage) {
		storage.setSection(storageRequest.getSection());
		storage.setBlockName(storageRequest.getBlockName());
		storage.setLengthInMeter(storageRequest.getLengthInMeter());
		storage.setBreathInmeter(storageRequest.getBreathInmeter());
		storage.setHeightInMeter(storageRequest.getHeightInMeter());
		storage.setCapacityInWeigth(storageRequest.getCapacityInWeigth());
		storage.setMaxAdditionalWeight(storage.getCapacityInWeigth());
		storage.setAvailableAreaInMeter(storageRequest.getLengthInMeter()*storageRequest.getBreathInmeter()*storageRequest.getHeightInMeter());
		storage.setMaterialTypes(storageRequest.getMaterialTypes());
		return storage;
		
	}
	
	public StorageResponse mapToStorageResponse(Storage storage) {
		return StorageResponse.builder()
				.storageId(storage.getStorageId())
				.section(storage.getSection())
				.blockName(storage.getBlockName())
				.lengthInMeter(storage.getLengthInMeter())
				.heightInMeter(storage.getHeightInMeter())
				.lengthInMeter(storage.getLengthInMeter())
				.maxAdditionalWeight(storage.getMaxAdditionalWeight())
				.availableAreaInMeter(storage.getAvailableAreaInMeter())
				.materialTypes(storage.getMaterialTypes())
				.build();
	}

}
