package com.jsp.wms.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import com.jsp.wms.Utility.ResponseStructure;
import com.jsp.wms.adminservice.AdminService;
import com.jsp.wms.requestdto.AdminRequest;
import com.jsp.wms.responsedto.AdminResponse;


import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1")
public class AdminController {
	@Autowired
	private AdminService adminService;
	
	
	
	@PostMapping("/register")
	public ResponseEntity<ResponseStructure<AdminResponse>>registerSuperAdmin(@RequestBody @Valid AdminRequest adminRequest){
		return adminService.registerSuperAdmin(adminRequest);
		
	}
	@PostMapping("/warehouses/{warehouseId}/admins")
	public ResponseEntity<ResponseStructure<AdminResponse>>saveAdmin(@RequestBody @Valid AdminRequest adminRequest,
			@PathVariable int warehouseId){
		return adminService.saveAdmin(adminRequest, warehouseId);
		
	}
	
	

}
