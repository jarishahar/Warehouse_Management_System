package com.jsp.wms.mapper;

import org.springframework.stereotype.Component;

import com.jsp.wms.entity.Admin;
import com.jsp.wms.requestdto.AdminRequest;
import com.jsp.wms.responsedto.AdminResponse;
@Component
public class AdminMapper {
	
	public Admin mapToAdmin(AdminRequest adminRequest, Admin admin) {
		admin.setAdminname(adminRequest.getAdminname());
		admin.setEmail(adminRequest.getEmail());
		admin.setPassword(adminRequest.getPassword());
		return admin;
		
	}

	public  AdminResponse mapToAdminResponse(Admin admin) {
		return AdminResponse.builder()
				.adminId(admin.getAdminId())
				.adminname(admin.getAdminname())
				.email(admin.getEmail())
				.adminType(admin.getAdminType())
				
				.build();
	}
}
