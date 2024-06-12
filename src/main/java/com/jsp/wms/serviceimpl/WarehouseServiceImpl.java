package com.jsp.wms.serviceimpl;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.jsp.wms.Utility.ResponseStructure;
import com.jsp.wms.entity.WareHouse;
import com.jsp.wms.exception.WarehouseNotFoundByIdException;
import com.jsp.wms.mapper.WarehouseMapper;
import com.jsp.wms.repository.WarehouseRepository;
import com.jsp.wms.requestdto.WarehouseRequest;
import com.jsp.wms.responsedto.WarehouseResponse;
import com.jsp.wms.service.WarehouseService;
@Service
public class WarehouseServiceImpl implements WarehouseService{
	@Autowired
	private WarehouseRepository warehouseRepository;

	@Autowired
	private WarehouseMapper warehouseMapper;
	@Override

	public ResponseEntity<ResponseStructure<WarehouseResponse>>createWarehouse(WarehouseRequest warehouseRequest) {
		WareHouse wareHouse = warehouseMapper.mapToWareHouseEntity(warehouseRequest, new WareHouse());
		warehouseRepository.save(wareHouse);
		return ResponseEntity.status(HttpStatus.CREATED)
				.body(new ResponseStructure<WarehouseResponse>()
						.setStatus(HttpStatus.CREATED.value())
						.setMessage("Warehouse Created")
						.setData(warehouseMapper.mapToWarehouseResponse(wareHouse)));
	}


	@Override
	public ResponseEntity<ResponseStructure<WarehouseResponse>>updateWarehouse(WarehouseRequest warehouseRequest,
			int warehouseId) {
		return warehouseRepository.findById(warehouseId).map(exwarehouse ->{
			WareHouse updateHouseEntity = warehouseMapper.mapToWareHouseEntity(warehouseRequest, exwarehouse);
			WareHouse updatehouseEntity = warehouseRepository.save(updateHouseEntity);
			return  ResponseEntity.status(HttpStatus.OK)
					.body(new ResponseStructure<WarehouseResponse>()
							.setStatus(HttpStatus.OK.value())
							.setMessage("Warehouse Updated")
							.setData(warehouseMapper.mapToWarehouseResponse(updatehouseEntity)));

		}).orElseThrow(()-> new WarehouseNotFoundByIdException("Warehouse not Found"));


	}


	@Override
	public ResponseEntity<ResponseStructure<WarehouseResponse>> findWarehouse(int warehouseId) {
		return warehouseRepository.findById(warehouseId).map(warehouse ->{
			return ResponseEntity.status(HttpStatus.OK)
					.body(new ResponseStructure<WarehouseResponse>()
							.setStatus(HttpStatus.OK.value())
							.setMessage("Warehouse Updated")
							.setData(warehouseMapper.mapToWarehouseResponse(warehouse)));

		}).orElseThrow(()-> new WarehouseNotFoundByIdException("Warehouse not Found"));

	}


}
