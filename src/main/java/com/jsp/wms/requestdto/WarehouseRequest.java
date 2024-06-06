package com.jsp.wms.requestdto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class WarehouseRequest {
	@NotNull(message = "Warehousename cannot be null")
	@NotBlank(message = "Warehousename cannot be blank")
private String name;
}
