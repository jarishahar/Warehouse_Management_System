package com.jsp.wms.entity;

import java.util.List;

import com.jsp.wms.enums.MaterialTypes;


import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
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

	@Enumerated(EnumType.STRING)
	List<MaterialTypes> materialTypes;
	private double maxAdditionalWeight;
	private double availableArea;
	
	private int sellerId;

	@ManyToOne
	private WareHouse wareHouse;
	
	@ManyToOne
	private StorageType storageType;
	
	@OneToMany(mappedBy = "storage")
	private List<Batch> batchs;
	
}
