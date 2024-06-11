package com.jsp.wms.adminserviceimpl;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.jsp.wms.Utility.ResponseStructure;
import com.jsp.wms.adminrepository.WarehouseRepository;
import com.jsp.wms.adminservice.WarehouseService;
import com.jsp.wms.entity.WareHouseEntity;
import com.jsp.wms.exception.WarehouseNotFoundByIdException;
import com.jsp.wms.mapper.WarehouseMapper;
import com.jsp.wms.requestdto.WarehouseRequest;
import com.jsp.wms.responsedto.WarehouseResponse;
@Service
public class WarehouseServiceImpl implements WarehouseService{
	@Autowired
	private WarehouseRepository warehouseRepository;

	@Autowired
	private WarehouseMapper warehouseMapper;
	@Override

	public ResponseEntity<ResponseStructure<WarehouseResponse>>createWarehouse(WarehouseRequest warehouseRequest) {
		WareHouseEntity wareHouseEntity = warehouseMapper.mapToWareHouseEntity(warehouseRequest, new WareHouseEntity());
		warehouseRepository.save(wareHouseEntity);
		return ResponseEntity.status(HttpStatus.CREATED)
				.body(new ResponseStructure<WarehouseResponse>()
						.setStatus(HttpStatus.CREATED.value())
						.setMessage("Warehouse Created")
						.setData(warehouseMapper.mapToWarehouseResponse(wareHouseEntity)));
	}


	@Override
	public ResponseEntity<ResponseStructure<WarehouseResponse>>updateWarehouse(WarehouseRequest warehouseRequest,
			int warehouseId) {
		return warehouseRepository.findById(warehouseId).map(exwarehouse ->{
			WareHouseEntity updateHouseEntity = warehouseMapper.mapToWareHouseEntity(warehouseRequest, exwarehouse);
			WareHouseEntity updatehouseEntity = warehouseRepository.save(updateHouseEntity);
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
