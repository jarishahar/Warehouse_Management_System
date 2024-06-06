package com.jsp.wms.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api/v1")
public class WarehouseController {


	@GetMapping("/warehouses")
	public String saveWareHouse(){
		return "warehouse created";

	}
}
