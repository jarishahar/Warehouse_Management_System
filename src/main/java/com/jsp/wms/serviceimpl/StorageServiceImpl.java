package com.jsp.wms.serviceimpl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.jsp.wms.Utility.ResponseStructure;
import com.jsp.wms.Utility.SimpleStructure;
import com.jsp.wms.entity.Storage;
import com.jsp.wms.exception.StorageNotFoundByIdException;
import com.jsp.wms.exception.WarehouseNotFoundByIdException;
import com.jsp.wms.mapper.StorageMapper;
import com.jsp.wms.repository.StorageRepository;
import com.jsp.wms.repository.WarehouseRepository;
import com.jsp.wms.requestdto.StorageRequest;
import com.jsp.wms.responsedto.StorageResponse;
import com.jsp.wms.service.StorageService;

import jakarta.validation.Valid;
@Service
public class StorageServiceImpl implements StorageService {
	@Autowired
private StorageRepository storageRepository;
	@Autowired
private StorageMapper storageMapper;
	@Autowired
	private WarehouseRepository warehouseRepository;
	@Override
	public ResponseEntity<SimpleStructure<String>> createStorage(@Valid StorageRequest storageRequest,
			int warehouseId,int noOfStorageUnits) {
		return warehouseRepository.findById(warehouseId).map(warehouse ->{
			List<Storage>storages = new ArrayList<>();
//			while(noOfStorageUnits > 0) {
//				storages.add(storageMapper.mapToStorage(storageRequest, new Storage()));
//				noOfStorageUnits -- ;
//			}
			for (int i = 0; i < noOfStorageUnits; i++) {
				Storage storage = new Storage();
	           storage = storageMapper.mapToStorage(storageRequest, new Storage());
	           storage.setWareHouse(warehouse);
	           storages.add(storage);
	            
	            
	        }
			
		   storageRepository.saveAll(storages);
		return ResponseEntity.status(HttpStatus.CREATED)
				.body(new SimpleStructure<String>()
						.setStatus(HttpStatus.CREATED.value())
						.setMesssage("Storage Created"));
		}).orElseThrow(()-> 
		new WarehouseNotFoundByIdException("Warehouse with the given id is not present"));

}
	@Override
	public ResponseEntity<ResponseStructure<StorageResponse>> updateStorage(@Valid StorageRequest storageRequest,
			int storageId) {
return storageRepository.findById(storageId).map(existingStorage -> {
			
			existingStorage = storageMapper.mapToStorage(storageRequest,existingStorage);
			storageRepository.save(existingStorage);
			
			return ResponseEntity.status(HttpStatus.OK).body(new ResponseStructure<StorageResponse>()
					.setStatus(HttpStatus.OK.value())
					.setMessage("Storage updated")
					.setData(storageMapper.mapToStorageResponse(existingStorage)));
					
			
		}).orElseThrow(()-> new StorageNotFoundByIdException("storage Not Found"));
	}

	}

