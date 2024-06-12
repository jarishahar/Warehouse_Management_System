package com.jsp.wms.mapper;

import org.springframework.stereotype.Component;

import com.jsp.wms.entity.WareHouse;
import com.jsp.wms.requestdto.WarehouseRequest;
import com.jsp.wms.responsedto.WarehouseResponse;
@Component
public class WarehouseMapper {
	
	public WareHouse mapToWareHouseEntity(WarehouseRequest warehouseRequest ,WareHouse wareHouse) {
		System.out.println(warehouseRequest.getWarehouseName());
		wareHouse.setWarehouseName(warehouseRequest.getWarehouseName());
		return wareHouse;
		
	}
//to provide response to the client
	public WarehouseResponse mapToWarehouseResponse(WareHouse wareHouse) {
		return WarehouseResponse.builder()
				.warehouseId(wareHouse.getWarehouseId())
				.warehouseName(wareHouse.getWarehouseName())
				.totalCapacity(0)
				.build();
	}
}
