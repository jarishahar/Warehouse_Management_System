package com.jsp.wms.adminservice;

import org.springframework.http.ResponseEntity;

import com.jsp.wms.Utility.ResponseStructure;
import com.jsp.wms.requestdto.WarehouseRequest;
import com.jsp.wms.responsedto.WarehouseResponse;

public interface WarehouseService {

	ResponseEntity<ResponseStructure<WarehouseResponse>> createWarehouse(WarehouseRequest warehouseRequest);

	ResponseEntity<ResponseStructure<WarehouseResponse>> updateWarehouse(WarehouseRequest warehouseRequest,
			int warehouseId);


	ResponseEntity<ResponseStructure<WarehouseResponse>> findWarehouse(int warehouseId);

	

}
