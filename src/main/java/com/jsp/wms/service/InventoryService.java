package com.jsp.wms.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.jsp.wms.Utility.ResponseStructure;
import com.jsp.wms.requestdto.InventoryRequest;
import com.jsp.wms.responsedto.InventoryResponse;

public interface InventoryService {

	

	

	ResponseEntity<ResponseStructure<InventoryResponse>> createInventory(InventoryRequest inventoryRequest,int clientId, int storageId, int quantity);

	

	ResponseEntity<ResponseStructure<InventoryResponse>> findInventoryByProductId(int productId);

	ResponseEntity<ResponseStructure<List<InventoryResponse>>> findAllInventories();









	ResponseEntity<ResponseStructure<InventoryResponse>> updateQuantity(int productId, int storageId, int quantity);



	ResponseEntity<ResponseStructure<InventoryResponse>> updateInventory(InventoryRequest inventoryRequest , int storageId,
			int productId);







}
