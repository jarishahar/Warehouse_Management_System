package com.jsp.wms.adminrepository;


import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jsp.wms.entity.Admin;
import com.jsp.wms.enums.AdminType;

public interface AdminRepository extends JpaRepository<Admin, Integer> {

	

	boolean existsByAdminType(AdminType adminType);

	Optional<Admin> findByEmail(String username);

}
