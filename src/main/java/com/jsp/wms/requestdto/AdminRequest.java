package com.jsp.wms.requestdto;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
public class AdminRequest {
	@NotNull(message = "Adminname cannot be null")
	@NotBlank(message = "Adminname cannot be blank")
	@Pattern(regexp = "^[a-zA-Z]*$",message = "Name must contain character only")
	private String adminname;
	
	@NotNull(message = "email cannot be null")
	@NotBlank(message = "email cannot be blank")
	@Email(regexp = "[a-zA-Z0-9+_.-]+@[a-zA-Z0-9.-]+\\.[a-z]{2,}", message = "invalid email ")
	private String email;
	
	@NotNull(message = "password cannot be null")
	@NotBlank(message = "password cannot be blank")
	@Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}$", message = "Password must"
			+ " contain at least one letter, one number, one special character")
	private String password;
	


}
