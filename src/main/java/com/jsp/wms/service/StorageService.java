package com.jsp.wms.service;

import org.springframework.http.ResponseEntity;

import com.jsp.wms.Utility.ResponseStructure;
import com.jsp.wms.Utility.SimpleStructure;
import com.jsp.wms.requestdto.StorageRequest;
import com.jsp.wms.responsedto.StorageResponse;

import jakarta.validation.Valid;

public interface StorageService {




	public ResponseEntity<ResponseStructure<StorageResponse>> updateStorage(@Valid StorageRequest storageRequest,
			int storageId);

	ResponseEntity<SimpleStructure<String>> createStorage(StorageRequest storageRequest, int wareHouseId,
			int noOfStorageUnits, int storageTypeId);

}
