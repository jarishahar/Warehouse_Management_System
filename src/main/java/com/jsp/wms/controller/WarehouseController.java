package com.jsp.wms.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jsp.wms.Utility.ResponseStructure;
import com.jsp.wms.adminservice.WarehouseService;
import com.jsp.wms.requestdto.WarehouseRequest;
import com.jsp.wms.responsedto.WarehouseResponse;
@RestController
@RequestMapping("/api/v1")
public class WarehouseController {
	private WarehouseService warehouseService;
	
@PostMapping("/warehouses")
	public String saveWareHouse(@RequestBody WarehouseRequest warehouseRequest){
		return "warehouse created";
		
	}
}
 