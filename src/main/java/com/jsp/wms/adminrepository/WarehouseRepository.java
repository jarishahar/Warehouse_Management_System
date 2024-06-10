package com.jsp.wms.adminrepository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jsp.wms.entity.WareHouseEntity;

public interface WarehouseRepository extends JpaRepository<WareHouseEntity, Integer> {

}
