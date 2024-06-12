package com.jsp.wms.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import com.jsp.wms.Utility.ResponseStructure;
import com.jsp.wms.requestdto.AdminRequest;
import com.jsp.wms.responsedto.AdminResponse;
import com.jsp.wms.service.AdminService;

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
	@PreAuthorize("hasAuthority('CREATE_ADMIN')")
	@PostMapping("/warehouses/{warehouseId}/admins")
	public ResponseEntity<ResponseStructure<AdminResponse>>saveAdmin(@RequestBody @Valid AdminRequest adminRequest,
			@PathVariable int warehouseId){
		return adminService.saveAdmin(adminRequest, warehouseId);
		
	}
	
	@PutMapping("/admins")
	public ResponseEntity<ResponseStructure<AdminResponse>>updateAdmin(@RequestBody @Valid AdminRequest adminRequest){
		return adminService.updateAdmin(adminRequest);
	}
	
	@PreAuthorize("hasAuthority('CREATE_ADMIN')")
	@PutMapping("/admins/{adminId}")
	public ResponseEntity<ResponseStructure<AdminResponse>>updateAdminBySuperAdmin(@RequestBody @Valid AdminRequest adminRequest,
			@PathVariable int adminId){
		return adminService.updateAdminBySuperAdmin(adminRequest,adminId);
	}
	@GetMapping("/admins/{adminId}")
	public ResponseEntity<ResponseStructure<AdminResponse>>findAdmin(@PathVariable int adminId){
		return adminService.findAdmin(adminId);
	}
	
	@GetMapping("/admins")
	public ResponseEntity<ResponseStructure<List<AdminResponse>>>findAllByAdminType(){
		return adminService.findAllByAdminType();
	} 

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
