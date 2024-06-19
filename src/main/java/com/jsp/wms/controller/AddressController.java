package com.jsp.wms.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jsp.wms.Utility.ResponseStructure;
import com.jsp.wms.requestdto.AddressRequest;
import com.jsp.wms.responsedto.AddressResponse;
import com.jsp.wms.service.AddressService;

import jakarta.validation.Valid;


@RestController
@RequestMapping("/api/v1")
public class AddressController {
	@Autowired
	private AddressService addressService;
	@PreAuthorize("hasAuthority('CREATE_ADMIN')")
	@PostMapping("/warehouses/{warehouseId}/addresses")
public ResponseEntity<ResponseStructure<AddressResponse>>saveAddress(@RequestBody AddressRequest addressRequest,@PathVariable int warehouseId){
	return addressService.saveAddress(addressRequest,warehouseId);
}
	
	@PreAuthorize("hasAuthority('CREATE_ADMIN')")
	@PutMapping("/addresses/{addressId}")
	public ResponseEntity<ResponseStructure<AddressResponse>>updateAddressById(@RequestBody @Valid AddressRequest addressRequest ,@PathVariable int addressId){
		return addressService.updateAddress(addressRequest,addressId);
	}
	@PreAuthorize("hasAuthority('READ')")
	@GetMapping("/addresses/{addressId}")
	public ResponseEntity<ResponseStructure<AddressResponse>>findAddressById(@PathVariable int addressId){
		return addressService.findAddressById(addressId);
	}
	

}
