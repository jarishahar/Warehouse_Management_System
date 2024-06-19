package com.jsp.wms.entity;

import com.jsp.wms.enums.MaterialTypes;


import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class Storage {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int storageId;
	private String blockName;
	private String section;
	private double lengthInMeter;
	private double heightInMeter;
	private double breathInmeter;
	private double capacityInWeigth;
	private double maxAdditionalWeight;
	private double availableAreaInMeter;
	@Enumerated(EnumType.STRING)
	private MaterialTypes materialTypes;
	@ManyToOne
	private WareHouse  wareHouse;
}
