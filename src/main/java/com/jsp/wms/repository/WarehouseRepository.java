package com.jsp.wms.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jsp.wms.entity.WareHouse;

public interface WarehouseRepository extends JpaRepository<WareHouse, Integer> {

}
