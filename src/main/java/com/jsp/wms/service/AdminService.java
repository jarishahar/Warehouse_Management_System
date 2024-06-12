package com.jsp.wms.service;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;

import com.jsp.wms.Utility.ResponseStructure;
import com.jsp.wms.requestdto.AdminRequest;
import com.jsp.wms.responsedto.AdminResponse;

import io.swagger.v3.oas.annotations.parameters.RequestBody;

public interface AdminService {
	
	ResponseEntity<ResponseStructure<AdminResponse>> registerSuperAdmin(@RequestBody AdminRequest adminRequest);

	ResponseEntity<ResponseStructure<AdminResponse>> saveAdmin(@RequestBody AdminRequest adminRequest ,int warehouseId);

	ResponseEntity<ResponseStructure<AdminResponse>> updateAdmin(@RequestBody AdminRequest adminRequest);

	ResponseEntity<ResponseStructure<AdminResponse>> updateAdminBySuperAdmin(@RequestBody AdminRequest adminRequest,
			int adminId);

	ResponseEntity<ResponseStructure<AdminResponse>> findAdmin(@PathVariable int adminId);

	ResponseEntity<ResponseStructure<List<AdminResponse>>> findAllByAdminType();


	

	
}
