package com.jsp.wms.mapper;

import org.springframework.stereotype.Component;

import com.jsp.wms.entity.Client;
import com.jsp.wms.requestdto.ClientRequest;
import com.jsp.wms.responsedto.ApiKeyResponse;
@Component
public class ClientMapper {
	public Client mapToClient(ClientRequest clientRequest,Client client) {
		client.setBusinessName(clientRequest.getBusinessName());
		client.setEmail(clientRequest.getEmail());
		client.setContactNumber(clientRequest.getContactNumber());
		return client;
	}

	public ApiKeyResponse mapToClientResponse(Client client) {
		return ApiKeyResponse.builder()
				
				.apiKey(client.getApiKey())
				.build();
	}
}
