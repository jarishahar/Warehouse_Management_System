package com.jsp.wms.requestdto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class AddressRequest {
	@NotNull(message = "Address cannot be null")
	@NotBlank(message = "Address cannot be blank")
	private String addressLine;
	
	@NotNull(message = "city cannot be null")
	@NotBlank(message = "city cannot be blank")
	private String city;
	
	@NotNull(message = "state cannot be null")
	@NotBlank(message = "state cannot be blank")
	private String state;
	
	@NotNull(message = "country cannot be null")
	@NotBlank(message = "country cannot be blank")
	private String country;
	
	@NotNull(message = "pincode cannot be null")
	private int pincode;
	
	@NotNull(message = "longitude cannot be null")
	@NotBlank(message = "longitude cannot be blank")
	private String longitude;
	
	@NotNull(message = "Latitude cannot be null")
	@NotBlank(message = "Latitude cannot be blank")
	private String latitude;
}
