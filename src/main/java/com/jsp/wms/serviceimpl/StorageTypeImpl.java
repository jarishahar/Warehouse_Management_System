package com.jsp.wms.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.jsp.wms.Utility.ResponseStructure;
import com.jsp.wms.entity.StorageType;
import com.jsp.wms.exception.StorageTypeNotFoundByIdException;
import com.jsp.wms.mapper.StorageTypeMapper;
import com.jsp.wms.repository.StorageTypeRepository;
import com.jsp.wms.requestdto.StorageTypeRequest;
import com.jsp.wms.responsedto.StorageTypeResponse;
import com.jsp.wms.service.StorageTypeService;
@Service
public class StorageTypeImpl implements StorageTypeService{
	@Autowired
	private StorageTypeRepository storageTypeRepository;
	@Autowired
	private StorageTypeMapper storageTypeMapper;
	@Override
	public ResponseEntity<ResponseStructure<StorageTypeResponse>> createStorageType(
			StorageTypeRequest storageTypeRequest) {
		StorageType storageType = storageTypeMapper.mapToStorageType(storageTypeRequest, new StorageType());
		storageType = storageTypeRepository.save(storageType);
		return ResponseEntity.status(HttpStatus.CREATED)
				.body(new ResponseStructure<StorageTypeResponse>().setStatus(HttpStatus.CREATED.value())
						.setMessage("StorageType created")
						.setData(storageTypeMapper.mapToStorageTypeResponse(storageType)));
	}

	@Override
	public ResponseEntity<ResponseStructure<StorageTypeResponse>> updateStorageType(
	        StorageTypeRequest storageTypeRequest, int storageTypeId) {
	    return storageTypeRepository.findById(storageTypeId)
	            .map(exStorageType -> {
	                exStorageType = storageTypeMapper.mapToStorageType(storageTypeRequest, exStorageType);
	                exStorageType = storageTypeRepository.save(exStorageType);

	                return ResponseEntity.status(HttpStatus.OK)
	                        .body(new ResponseStructure<StorageTypeResponse>()
	                                .setStatus(HttpStatus.OK.value())
	                                .setMessage("Storage Type Updated") 
	                                .setData(storageTypeMapper.mapToStorageTypeResponse(exStorageType)));
	            }).orElseThrow(() -> new StorageTypeNotFoundByIdException("Storage Type Not Found")); 
	}


	@Override
	public ResponseEntity<ResponseStructure<List<StorageTypeResponse>>> findAllStorageType() {
		// TODO Auto-generated method stub

		List<StorageTypeResponse> storageTypeList = storageTypeRepository.findAll().stream()
				.map(storageType -> 
				storageTypeMapper.mapToStorageTypeResponse(storageType)).toList();

		return ResponseEntity.status(HttpStatus.FOUND)
				.body(new ResponseStructure<List<StorageTypeResponse>>()
				.setStatus(HttpStatus.FOUND.value())
				.setMessage("StoarageType Found")
				.setData(storageTypeList));

	}

}
