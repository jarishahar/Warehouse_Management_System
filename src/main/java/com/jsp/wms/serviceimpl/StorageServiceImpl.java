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
import com.jsp.wms.entity.StorageType;
import com.jsp.wms.entity.WareHouse;
import com.jsp.wms.exception.StorageNotFoundByIdException;
import com.jsp.wms.exception.StorageTypeNotFoundByIdException;
import com.jsp.wms.exception.WarehouseNotFoundByIdException;
import com.jsp.wms.mapper.StorageMapper;
import com.jsp.wms.repository.StorageRepository;
import com.jsp.wms.repository.StorageTypeRepository;
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
	@Autowired
	private StorageTypeRepository storageTypeRepository;
	
	@Override
	public ResponseEntity<SimpleStructure<String>> createStorage(StorageRequest storageRequest, int wareHouseId, int noOfStorageUnits, int storageTypeId) {
		WareHouse wareHouse =  warehouseRepository.findById(wareHouseId).orElseThrow(()-> new WarehouseNotFoundByIdException("Warehouse Not found by id"));
		StorageType storageType = storageTypeRepository.findById(storageTypeId).orElseThrow(()-> new StorageTypeNotFoundByIdException("Storage Type not available"));
		
		List<Storage> storages = new ArrayList<Storage>();

		int count = 0;

		while(noOfStorageUnits > 0) {

			Storage storage  = storageMapper.mapToStorage(storageRequest, new Storage());

			storage.setWareHouse(wareHouse);
			storage.setStorageType(storageType);
			storage.setMaxAdditionalWeight(storageType.getCapacityInkg());
			storage.setAvailableArea(storageType.getLengthInMetres() * storageType.getBreadthInMetres() * storageType.getHeightInMetres());

			//wareHouse.setTotalCapacityInKg(storageType.getCapacityInWeight() * noOfStorageUnits + wareHouse.getTotalCapacityInKg());
			storageType.setUnitsAvailable(storageType.getUnitsAvailable()+noOfStorageUnits);
			

			storages.add(storage);
			count++;
			noOfStorageUnits --;
		}

		storageRepository.saveAll(storages);
		//wareHouseRespository.save(wareHouse);
		storageTypeRepository.save(storageType);


		return ResponseEntity.status(HttpStatus.CREATED).body(new SimpleStructure<String>()
				.setStatus(HttpStatus.CREATED.value())
				.setMesssage(""+count + " Storages created"));

	}
//	@Override
//	public ResponseEntity<SimpleStructure<String>> createStorage(@Valid StorageRequest storageRequest,
//			int warehouseId,int noOfStorageUnits) {
//		return warehouseRepository.findById(warehouseId).map(warehouse ->{
//			List<Storage>storages = new ArrayList<>();
////			while(noOfStorageUnits > 0) {
////				storages.add(storageMapper.mapToStorage(storageRequest, new Storage()));
////				noOfStorageUnits -- ;
////			}
//			for (int i = 0; i < noOfStorageUnits; i++) {
//				Storage storage = new Storage();
//	           storage = storageMapper.mapToStorage(storageRequest, new Storage());
//	           storage.setWareHouse(warehouse);
//	           storages.add(storage);
//	            
//	            
//	        }
//			
//		   storageRepository.saveAll(storages);
//		   storageTypeRepository.save(storageType);
//		return ResponseEntity.status(HttpStatus.CREATED)
//				.body(new SimpleStructure<String>()
//						.setStatus(HttpStatus.CREATED.value())
//						.setMesssage("Storage Created"));
//		}).orElseThrow(()-> 
//		new WarehouseNotFoundByIdException("Warehouse with the given id is not present"));
//
//}
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

