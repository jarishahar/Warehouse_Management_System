package com.jsp.wms.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jsp.wms.Utility.ErrorStructure;
import com.jsp.wms.Utility.ResponseStructure;
import com.jsp.wms.requestdto.ClientRequest;
import com.jsp.wms.responsedto.ApiKeyResponse;
import com.jsp.wms.service.ClientService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
@RestController
@RequestMapping("/api/v1")
public class ClientController {
	@Autowired
	private ClientService clientService;
	
	@Operation(description = "the endpoint for registering the client to database", responses = {
			@ApiResponse(responseCode = "201", description = "client registered"),
			@ApiResponse(responseCode = "400", description = "client already registered", content = {
					@Content(schema = @Schema(oneOf = ErrorStructure.class)) }) })
	
	@PostMapping("/clients/register")
	public ResponseEntity<ResponseStructure<ApiKeyResponse>> registerClient(@RequestBody @Valid

			ClientRequest clientRequest){
		return clientService.registerClient(clientRequest);
	}
	
	@PutMapping("/clients/{clientId}/clients")
	public ResponseEntity<ResponseStructure<ApiKeyResponse>> updateClient(@RequestBody @Valid

			ClientRequest clientRequest,@PathVariable int clientId){
		return clientService.updateClient(clientRequest,clientId);
	}
}
