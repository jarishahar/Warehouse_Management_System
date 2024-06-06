package com.jsp.wms.adminrepository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jsp.wms.entity.WareHouseEntity;

public interface WarehouseResository extends JpaRepository<WareHouseEntity, Integer> {

}
