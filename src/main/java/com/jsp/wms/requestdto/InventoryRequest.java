package com.jsp.wms.requestdto;

import java.util.List;

import com.jsp.wms.enums.MaterialTypes;

import lombok.Getter;
import lombok.Setter;


@Setter
@Getter
public class InventoryRequest {
	private String productTitle;
	private double lengthInMetres;
	private double breadthInMetres;
	private double heightInMetres;
	private double weightInKg;
	private int quantity;
	
	List<MaterialTypes>materialTypes;
	
	private int sellerId;
}
