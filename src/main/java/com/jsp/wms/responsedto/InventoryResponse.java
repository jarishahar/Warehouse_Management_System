package com.jsp.wms.responsedto;

import java.time.LocalDate;
import java.util.List;

import com.jsp.wms.enums.MaterialTypes;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;


@Setter
@Getter
@AllArgsConstructor
@Builder
public class InventoryResponse {
	private int productId;
	private String productTitle;
	private double lengthInMetres;
	private double breadthInMetres;
	private double heightInMetres;
	private double weightInKg;
	List<MaterialTypes>materialTypes;
	private LocalDate restockedAt;
	private int sellerId;
	BatchResponse batch;
}
