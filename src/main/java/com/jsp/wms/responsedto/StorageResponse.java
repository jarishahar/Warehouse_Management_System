package com.jsp.wms.responsedto;

import com.jsp.wms.enums.MaterialTypes;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class StorageResponse {
	private int storageId;
	private String blockName;
	private String section;
	private double lengthInMeter;
	private double heightInMeter;
	private double breathInmeter;
	private double capacityInWeigth;
	private double maxAdditionalWeight;
	private double availableAreaInMeter;
	private MaterialTypes materialTypes;
}
