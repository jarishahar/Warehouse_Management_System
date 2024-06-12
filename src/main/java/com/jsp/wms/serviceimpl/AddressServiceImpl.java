package com.jsp.wms.serviceimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.jsp.wms.Utility.ResponseStructure;
import com.jsp.wms.entity.Address;
import com.jsp.wms.exception.AddressNotFoundByIdException;
import com.jsp.wms.exception.WarehouseNotFoundByIdException;
import com.jsp.wms.mapper.AddressMapper;
import com.jsp.wms.repository.AddressRepository;
import com.jsp.wms.repository.WarehouseRepository;
import com.jsp.wms.requestdto.AddressRequest;
import com.jsp.wms.responsedto.AddressResponse;
import com.jsp.wms.service.AddressService;

import jakarta.validation.Valid;
@Service
public class AddressServiceImpl implements AddressService {
    @Autowired
	private AddressRepository addressRepository;
    @Autowired
    private AddressMapper addressMapper;
    @Autowired
    private WarehouseRepository warehouseRepository;
    
    


	@Override
	public ResponseEntity<ResponseStructure<AddressResponse>> saveAddress(AddressRequest addressRequest,
			int warehouseId) {
		return warehouseRepository.findById(warehouseId).map(warehouse ->{
			Address address = addressMapper.mapToAddress(addressRequest, new Address());
			 addressRepository.save(address);
			 address.setWareHouse(warehouse);
			 warehouseRepository.save(warehouse);
			 return  ResponseEntity.status(HttpStatus.CREATED)
						.body(new ResponseStructure<AddressResponse>()
								.setStatus(HttpStatus.CREATED.value())
								.setMessage("Address Created")
								.setData(addressMapper.mapToAddressResponse(address)));
		}).orElseThrow(()-> 
		new WarehouseNotFoundByIdException("Warehouse with the given id is not present"));
	}




	@Override
	public ResponseEntity<ResponseStructure<AddressResponse>> findAddressById(int addressId) {
		return addressRepository.findById(addressId).map(address ->
		ResponseEntity.status(HttpStatus.OK)
		.body(new ResponseStructure<AddressResponse>()
				.setStatus(HttpStatus.OK.value())
				.setMessage("Address found")
				.setData(addressMapper.mapToAddressResponse(address)))
		).orElseThrow(()-> new AddressNotFoundByIdException("Address not found by the given id"));
	}



	
	@Override
	public ResponseEntity<ResponseStructure<AddressResponse>> updateAddress(@Valid AddressRequest addressRequest,
			int addressId) {
		return addressRepository.findById(addressId).map(exaddress ->{
			addressMapper.mapToAddress(addressRequest, exaddress);
			exaddress = addressRepository.save(exaddress);
			return  ResponseEntity.status(HttpStatus.CREATED)
					.body(new ResponseStructure<AddressResponse>()
							.setStatus(HttpStatus.CREATED.value())
							.setMessage("Address Created")
							.setData(addressMapper.mapToAddressResponse(exaddress)));
		}).orElseThrow(()-> new AddressNotFoundByIdException("Address not found by the given id"));
		
	}

}
