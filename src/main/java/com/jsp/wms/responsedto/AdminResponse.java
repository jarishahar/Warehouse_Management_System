package com.jsp.wms.responsedto;


import com.jsp.wms.enums.AdminType;

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
public class AdminResponse {
	private int adminId;
	private String adminname;
	private String email;
	private AdminType adminType;
	
}
