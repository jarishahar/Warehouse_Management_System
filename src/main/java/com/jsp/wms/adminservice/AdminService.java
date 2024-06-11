package com.jsp.wms.adminservice;

import org.springframework.http.ResponseEntity;

import com.jsp.wms.Utility.ResponseStructure;
import com.jsp.wms.requestdto.AdminRequest;
import com.jsp.wms.responsedto.AdminResponse;

import io.swagger.v3.oas.annotations.parameters.RequestBody;

public interface AdminService {
	
	ResponseEntity<ResponseStructure<AdminResponse>> registerSuperAdmin(@RequestBody AdminRequest adminRequest);

	ResponseEntity<ResponseStructure<AdminResponse>> saveAdmin(@RequestBody AdminRequest adminRequest ,int warehouseId);

	
}
