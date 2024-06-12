package com.jsp.wms.serviceimpl;





import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import java.util.List;
import com.jsp.wms.Utility.ResponseStructure;
import com.jsp.wms.entity.Admin;
import com.jsp.wms.enums.AdminType;
import com.jsp.wms.exception.AdminNotFoundByEmailException;
import com.jsp.wms.exception.AdminNotFoundByIdException;
import com.jsp.wms.exception.SuperAdminAlreadyExistException;
import com.jsp.wms.exception.WarehouseNotFoundByIdException;
import com.jsp.wms.mapper.AdminMapper;
import com.jsp.wms.repository.AdminRepository;
import com.jsp.wms.repository.WarehouseRepository;
import com.jsp.wms.requestdto.AdminRequest;
import com.jsp.wms.responsedto.AdminResponse;
import com.jsp.wms.service.AdminService;



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

	@Override
	public ResponseEntity<ResponseStructure<AdminResponse>> updateAdmin(AdminRequest adminRequest) {
		String email =SecurityContextHolder.getContext().getAuthentication().getName();
		return adminRepository.findByEmail(email).map(exadmin -> {
			adminMapper.mapToAdmin(adminRequest , exadmin);
			
		exadmin= adminRepository.save(exadmin);
			return  ResponseEntity.status(HttpStatus.OK)
					.body(new ResponseStructure<AdminResponse>()
							.setStatus(HttpStatus.OK.value())
							.setMessage("Admin Updated")
							.setData(adminMapper.mapToAdminResponse(exadmin)));
		}).orElseThrow(()-> new AdminNotFoundByEmailException("Admin not Found"));
		
		
	}

	@Override
	public ResponseEntity<ResponseStructure<AdminResponse>> updateAdminBySuperAdmin(AdminRequest adminRequest,
			int adminId) {
		return adminRepository.findById(adminId).map(exadmin -> {
			adminMapper.mapToAdmin(adminRequest, exadmin);
			exadmin= adminRepository.save(exadmin);
			return  ResponseEntity.status(HttpStatus.OK)
					.body(new ResponseStructure<AdminResponse>()
							.setStatus(HttpStatus.OK.value())
							.setMessage("Admin Updated")
							.setData(adminMapper.mapToAdminResponse(exadmin)));
		}).orElseThrow(()-> new AdminNotFoundByIdException("Admin not Found"));
	}

	@Override
	public ResponseEntity<ResponseStructure<AdminResponse>> findAdmin( int adminId) {
		return adminRepository.findById(adminId).map(admin ->
		ResponseEntity.status(HttpStatus.FOUND)
		.body(new ResponseStructure<AdminResponse>()
				.setStatus(HttpStatus.FOUND.value())
				.setMessage("Admin Found")
				.setData(adminMapper.mapToAdminResponse(admin)))
		).orElseThrow(()-> new AdminNotFoundByIdException("Admin Not Found"));
			

		}

	@Override
	public ResponseEntity<ResponseStructure<List<AdminResponse>>> findAllByAdminType() {
		
			List<AdminResponse> adminsList = adminRepository.findAllByAdminType(AdminType.ADMIN).stream().map(admin -> 
				adminMapper.mapToAdminResponse(admin)).toList();
			
			return ResponseEntity.status(HttpStatus.FOUND)
					.body(new ResponseStructure<List<AdminResponse>>()
							.setStatus(HttpStatus.FOUND.value())
							.setMessage("Admins Found")
							.setData(adminsList));
			
		
	}

	}

	


	
	
	
	
	
	
	
	
	
	
	
	
	
