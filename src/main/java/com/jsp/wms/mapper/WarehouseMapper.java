package com.jsp.wms.mapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.jsp.wms.entity.WareHouseEntity;
import com.jsp.wms.requestdto.WarehouseRequest;
import com.jsp.wms.responsedto.WarehouseResponse;
@Component
public class WarehouseMapper {
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	public WareHouseEntity mapToWareHouseEntity(WarehouseRequest warehouseRequest ,WareHouseEntity wareHouseEntity) {
		wareHouseEntity.setWarehousename(warehouseRequest.getWarehousename());
		return wareHouseEntity;
		
	}
//to provide response to the client
	public WarehouseResponse mapToWarehouseResponse(WareHouseEntity wareHouseEntity) {
		return WarehouseResponse.builder()
				.warehouseId(wareHouseEntity.getWarehouseId())
				.warehousename(wareHouseEntity.getWarehousename())
				.totalCapacity(0)
				.build();
	}
}
