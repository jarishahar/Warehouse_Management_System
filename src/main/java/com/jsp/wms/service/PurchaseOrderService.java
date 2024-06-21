package com.jsp.wms.service;

import org.springframework.http.ResponseEntity;

import com.jsp.wms.Utility.ResponseStructure;
import com.jsp.wms.requestdto.PurchaseOrderRequest;
import com.jsp.wms.responsedto.PurchaseOrderResponse;

public interface PurchaseOrderService {

	ResponseEntity<ResponseStructure<PurchaseOrderResponse>> createPurchaseOrder(
			PurchaseOrderRequest purchaseOrderRequest, int productId);

}
