package com.jsp.wms.requestdto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StorageTypeRequest {
	private double lengthInMetres;
	private double breadthInMetres;
	private double heightInMetres;
	private double capacityInkg;
}
