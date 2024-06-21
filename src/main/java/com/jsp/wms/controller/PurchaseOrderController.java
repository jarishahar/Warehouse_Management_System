package com.jsp.wms.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jsp.wms.Utility.ResponseStructure;
import com.jsp.wms.requestdto.PurchaseOrderRequest;
import com.jsp.wms.responsedto.PurchaseOrderResponse;
import com.jsp.wms.service.PurchaseOrderService;
@RestController
@RequestMapping("/api/v1")
public class PurchaseOrderController {
private PurchaseOrderService purchaseOrderService;
	
	@PostMapping("/inventories/{productId}/purchaseOrders")
	public ResponseEntity<ResponseStructure<PurchaseOrderResponse>> createPurchaseOrder(@RequestBody PurchaseOrderRequest purchaseOrderRequest, @PathVariable int productId){
		return purchaseOrderService.createPurchaseOrder(purchaseOrderRequest,productId);
	}
}

