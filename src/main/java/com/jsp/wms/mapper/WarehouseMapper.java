package com.jsp.wms.mapper;

import org.springframework.stereotype.Component;

import com.jsp.wms.entity.WareHouseEntity;
import com.jsp.wms.requestdto.WarehouseRequest;
import com.jsp.wms.responsedto.WarehouseResponse;
@Component
public class WarehouseMapper {
	
	public WareHouseEntity mapToWareHouseEntity(WarehouseRequest warehouseRequest ,WareHouseEntity wareHouseEntity) {
		System.out.println(warehouseRequest.getWarehouseName());
		wareHouseEntity.setWarehouseName(warehouseRequest.getWarehouseName());
		return wareHouseEntity;
		
	}
//to provide response to the client
	public WarehouseResponse mapToWarehouseResponse(WareHouseEntity wareHouseEntity) {
		return WarehouseResponse.builder()
				.warehouseId(wareHouseEntity.getWarehouseId())
				.warehouseName(wareHouseEntity.getWarehouseName())
				.totalCapacity(0)
				.build();
	}
}
