package com.jsp.wms.adminserviceimpl;




import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.jsp.wms.Utility.ResponseStructure;
import com.jsp.wms.adminrepository.AdminRepository;
import com.jsp.wms.adminrepository.WarehouseRepository;
import com.jsp.wms.adminservice.AdminService;
import com.jsp.wms.entity.Admin;
import com.jsp.wms.entity.WareHouseEntity;
import com.jsp.wms.enums.AdminType;
import com.jsp.wms.exception.SuperAdminAlreadyExistException;
import com.jsp.wms.exception.WarehouseNotFoundByIdException;
import com.jsp.wms.mapper.AdminMapper;
import com.jsp.wms.requestdto.AdminRequest;
import com.jsp.wms.responsedto.AdminResponse;


@Service
public class AdminServiceImpl implements AdminService {
	@Autowired
	private AdminRepository adminRepository;
	@Autowired
	private AdminMapper adminMapper;
	@Autowired
	private WarehouseRepository warehouseRepository;

	@Override
	public ResponseEntity<ResponseStructure<AdminResponse>> registerSuperAdmin(AdminRequest adminRequest) {
		if(adminRepository.existsByAdminType(AdminType.SUPER_ADMIN))
			throw new SuperAdminAlreadyExistException(" Super Admin Already exist");
		Admin admin = adminMapper.mapToAdmin(adminRequest , new Admin());
		admin.setAdminType(AdminType.SUPER_ADMIN);
		
		admin= adminRepository.save(admin);

		return ResponseEntity.status(HttpStatus.CREATED)
				.body(new ResponseStructure<AdminResponse>()
						.setStatus(HttpStatus.CREATED.value())
						.setMessage("SuperAdmin Created")
						.setData(adminMapper.mapToAdminResponse(admin)));
	}

	@Override
	public ResponseEntity<ResponseStructure<AdminResponse>> saveAdmin(AdminRequest adminRequest,int warehouseId ) {
		 return warehouseRepository.findById(warehouseId).map(warehouse -> {
			 Admin admin = adminMapper.mapToAdmin(adminRequest, new Admin());
			 admin.setAdminType(AdminType.ADMIN);
			 admin= adminRepository.save(admin);
	
			warehouse.setAdmin(admin);
			warehouseRepository.save(warehouse);
			 return  ResponseEntity.status(HttpStatus.CREATED)
						.body(new ResponseStructure<AdminResponse>()
								.setStatus(HttpStatus.CREATED.value())
								.setMessage("Admin Created")
								.setData(adminMapper.mapToAdminResponse(admin)));
		}).orElseThrow(()-> 
		new WarehouseNotFoundByIdException("Warehouse with the given id is not present"));
			
			
		
}
}

	
	
	
	
	
	
	
	
	
	
	
	
	
