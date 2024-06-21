package com.jsp.wms.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jsp.wms.entity.Batch;
import com.jsp.wms.entity.Inventory;
import com.jsp.wms.entity.Storage;


public interface BatchRepository extends JpaRepository<Batch, Integer>{

	Optional<Batch> findByInventory_ProductId(int productId);


	List<Batch> findByInventoryAndStorage(Inventory inventory, Storage storage);

	


}
