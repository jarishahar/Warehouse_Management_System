package com.jsp.wms.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.jsp.wms.Utility.ResponseStructure;
import com.jsp.wms.Utility.SimpleStructure;
import com.jsp.wms.requestdto.StorageRequest;
import com.jsp.wms.responsedto.StorageResponse;
import com.jsp.wms.service.StorageService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1")
public class StorageController {
	
	@Autowired
	private StorageService storageService;
	@PreAuthorize("hasAuthority('READ')")
	@PostMapping("/warehouses/{warehouseId}/storages")
	public ResponseEntity<SimpleStructure<String>>createStorage(@RequestBody @Valid StorageRequest storageRequest,
			@PathVariable int warehouseId ,@RequestParam ("no_of_storage_units") int noOfStorageUnits){
		return storageService.createStorage(storageRequest,warehouseId,noOfStorageUnits);
	}
	@PreAuthorize("hasAuthority('UPDATE_STORAGE')")
	@PutMapping("/storages/{storageId}")
public ResponseEntity<ResponseStructure<StorageResponse>>updateStorage(@RequestBody @Valid StorageRequest storageRequest,
		@PathVariable int storageId){
	return storageService.updateStorage(storageRequest,storageId);
}
}
