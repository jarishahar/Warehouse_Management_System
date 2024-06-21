package com.jsp.wms.service;

import org.springframework.http.ResponseEntity;

import com.jsp.wms.Utility.ResponseStructure;
import com.jsp.wms.requestdto.ClientRequest;
import com.jsp.wms.responsedto.ApiKeyResponse;

import jakarta.validation.Valid;

public interface ClientService {


	ResponseEntity<ResponseStructure<ApiKeyResponse>> updateClient(@Valid ClientRequest clientRequest, int clientId);

	ResponseEntity<ResponseStructure<ApiKeyResponse>> registerClient(@Valid ClientRequest clientRequest);

}
