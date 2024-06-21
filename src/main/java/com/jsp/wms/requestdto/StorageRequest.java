package com.jsp.wms.requestdto;

import java.util.List;

import com.jsp.wms.enums.MaterialTypes;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
public class StorageRequest {
	@NotNull(message = "Blockname cannot be null")
	@NotBlank(message = "Blockname cannot be blank")
	private String blockName;
	
	@NotNull(message = "Section cannot be null")
	@NotBlank(message = "Section cannot be blank")
	private String section;
	
	@NotNull(message = "Blockname cannot be null")
	private double lengthInMeter;
	
	@NotNull(message = "Blockname cannot be null")
	private double heightInMeter;
	
	@NotNull(message = "Blockname cannot be null")
	private double breathInmeter;
	
	@NotNull(message = "Blockname cannot be null")
	private double capacityInWeigth;
	
	@NotNull(message = "Blockname cannot be null")
	
	List<MaterialTypes> materialTypes;
}
