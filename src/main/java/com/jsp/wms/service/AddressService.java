package com.jsp.wms.service;

import org.springframework.http.ResponseEntity;

import com.jsp.wms.Utility.ResponseStructure;
import com.jsp.wms.requestdto.AddressRequest;
import com.jsp.wms.responsedto.AddressResponse;

import jakarta.validation.Valid;

public interface AddressService {


	ResponseEntity<ResponseStructure<AddressResponse>> saveAddress(AddressRequest addressRequest, int warehouseId);

	ResponseEntity<ResponseStructure<AddressResponse>> findAddressById(int addressId);

	ResponseEntity<ResponseStructure<AddressResponse>> updateAddress(@Valid AddressRequest addressRequest, int addressId);

}
