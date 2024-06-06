package com.jsp.wms.entity;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class WareHouseEntity {
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int warehouseId;
	private String warehousename;
}
