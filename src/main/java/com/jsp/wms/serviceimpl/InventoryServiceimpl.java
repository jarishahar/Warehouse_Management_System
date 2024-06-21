package com.jsp.wms.serviceimpl;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.jsp.wms.Utility.ResponseStructure;
import com.jsp.wms.entity.Batch;
import com.jsp.wms.entity.Client;
import com.jsp.wms.entity.Inventory;
import com.jsp.wms.entity.Storage;
import com.jsp.wms.exception.BatchNotFoundException;
import com.jsp.wms.exception.ClientNotFoundByIdException;
import com.jsp.wms.exception.InventoryNotFoundByIdException;
import com.jsp.wms.exception.SpaceOrWeightNotAvailableException;
import com.jsp.wms.exception.StorageNotFoundByIdException;
import com.jsp.wms.mapper.InventoryMapper;
import com.jsp.wms.repository.BatchRepository;
import com.jsp.wms.repository.ClientRepository;
import com.jsp.wms.repository.InventoryRepository;
import com.jsp.wms.repository.StorageRepository;
import com.jsp.wms.requestdto.InventoryRequest;
import com.jsp.wms.responsedto.InventoryResponse;
import com.jsp.wms.service.InventoryService;



@Service
public class InventoryServiceimpl implements InventoryService{
	@Autowired
	private InventoryMapper inventoryMapper;

	@Autowired
	private StorageRepository storageRepo;

	@Autowired
	private InventoryRepository inventoryRepo;
	
@Autowired
private ClientRepository clientRepository;

	@Autowired
	private BatchRepository batchRepository;

	
		 

	

	@Override
	public ResponseEntity<ResponseStructure<InventoryResponse>> findInventoryByProductId(int productId) {
		return inventoryRepo.findById(productId).map(inventory ->
		ResponseEntity.status(HttpStatus.FOUND)
		.body(new ResponseStructure<InventoryResponse>()
				.setStatus(HttpStatus.FOUND.value())
				.setMessage("inventory Found")
				.setData(inventoryMapper.mapToInventoryResponse(inventory)))
				).orElseThrow(()-> new InventoryNotFoundByIdException("inventory Not Found"));

	}

	@Override
	public ResponseEntity<ResponseStructure<List<InventoryResponse>>> findAllInventories() {
		List<InventoryResponse>inventoryList =inventoryRepo.findAll().stream().map(inventory ->
		inventoryMapper.mapToInventoryResponse(inventory)).toList();

		return ResponseEntity.status(HttpStatus.FOUND)
				.body(new ResponseStructure<List<InventoryResponse>>()
						.setStatus(HttpStatus.FOUND.value())
						.setMessage("inventories Found")
						.setData(inventoryList));


	}


	@Override
	public ResponseEntity<ResponseStructure<InventoryResponse>> createInventory(InventoryRequest inventoryRequest,
			int clientId, int storageId, int quantity) {
		Storage storage = storageRepo.findById(storageId).orElseThrow(()-> new StorageNotFoundByIdException("storage Not Found"));

		Inventory inventory = inventoryRepo.save(inventoryMapper.mapToInventory(inventoryRequest, new Inventory()));

		Client client = clientRepository.findById(clientId).orElseThrow(()-> new ClientNotFoundByIdException("Client Not Found"));

		inventory.setRestockedAt(LocalDate.now());
		inventory.setClient(client);
		
		 Batch batch=new Batch();
		


		storage.setMaxAdditionalWeight(storage.getMaxAdditionalWeight()  - inventoryRequest.getWeightInKg()*quantity);
		storage.setAvailableArea(((storage.getAvailableArea())-(inventory.getLengthInMetres() * inventory.getBreadthInMetres() * inventory.getHeightInMetres())));
		storage.setSellerId(inventoryRequest.getSellerId());

		Inventory inventory1 = inventoryRepo.save(inventory);
		
		
				
		 Storage storage1=storageRepo.save(storage);
		 
		 batch.setInventory(inventory1);
		 batch.setStorage(storage1);
		 batch.setQuantity(quantity);
		 batch=storageRepo.save(batch);
				
		clientRepository.save(client);

		return ResponseEntity.status(HttpStatus.CREATED)
				.body(new ResponseStructure<InventoryResponse>()
						.setStatus(HttpStatus.CREATED.value())
						.setMessage("Inventory created")
						.setData(inventoryMapper.mapToInventoryResponse(inventory,batch)));
	}
	
	@Override
	public ResponseEntity<ResponseStructure<InventoryResponse>>updateQuantity(int productId, int storageId, int quantity) {
		Inventory inventory = inventoryRepo.findById(productId).orElseThrow(()-> new InventoryNotFoundByIdException("Inventory Not Found"));
		Storage storage = storageRepo.findById(storageId).orElseThrow(() -> new StorageNotFoundByIdException("Storage Not Found"));

		List<Batch> batches = batchRepository.findByInventoryAndStorage(inventory, storage);

		Batch batch = new Batch();
		batch.setQuantity(quantity);
		batch.setInventory(inventory);
		batch.setStorage(storage);

		batchRepository.save(batch);
		inventoryRepo.save(inventory);
		storageRepo.save(storage);

		return ResponseEntity.status(HttpStatus.OK)
				.body(new ResponseStructure<InventoryResponse>()
						.setStatus(HttpStatus.OK.value())
						.setMessage("Quantity Updated")
						.setData(inventoryMapper.mapToInventoryResponse(inventory, batch))
						);
	    			
	}
	@Override
	public ResponseEntity<ResponseStructure<InventoryResponse>> updateInventory(InventoryRequest inventoryRequest, int storageId, int productId) {
	
		return inventoryRepo.findById(productId).map(inventory -> {
			
			Batch batch = batchRepository.findByInventory_ProductId(productId).orElseThrow(() -> new BatchNotFoundException("Stock not found"));
			
				int oldQuantity = batch.getQuantity();
				double oldLength = inventory.getLengthInMetres();
				double oldBreadth = inventory.getBreadthInMetres();
				double oldHeight = inventory.getHeightInMetres();
				
			 double originalWeight = inventory.getWeightInKg() * oldQuantity;
			 double originalArea = inventory.getBreadthInMetres() * inventory.getHeightInMetres() * inventory.getLengthInMetres();
			 
			 Storage storage = batch.getStorage();
			 
			Inventory updatedInventory = inventoryMapper.mapToInventory(inventoryRequest, inventory);
			 

			   double newWeight = updatedInventory.getWeightInKg() * oldQuantity;
			   double newArea = updatedInventory.getBreadthInMetres() * updatedInventory.getHeightInMetres() * updatedInventory.getLengthInMetres();
			   
			  if((oldLength != updatedInventory.getLengthInMetres() || oldBreadth != updatedInventory.getBreadthInMetres() || oldHeight != updatedInventory.getHeightInMetres())
					  || originalWeight != newWeight)
					  {
								if(storage.getAvailableArea()>0 && storage.getMaxAdditionalWeight() >0)
								{
									storage.setMaxAdditionalWeight(storage.getMaxAdditionalWeight() + originalWeight - newWeight);
							        storage.setAvailableArea(storage.getAvailableArea() + originalArea - newArea);  
								}
								
								else
								{
									throw new SpaceOrWeightNotAvailableException("No Available Area or Capacity of Storage Full");
								}

					  }
			  
			  	updatedInventory = inventoryRepo.save(updatedInventory);
				batch.setInventory(updatedInventory);
				batch.setStorage(storage);
				batchRepository.save(batch); 
				storageRepo.save(storage);
	
		    return ResponseEntity.status(HttpStatus.OK)
		            .body(new ResponseStructure<InventoryResponse>()
		                    .setData(inventoryMapper.mapToInventoryResponse(updatedInventory, batch))
		                    .setMessage("Inventory updated")
		                    .setStatus(HttpStatus.OK.value()));
			
			
		}).orElseThrow(() -> new InventoryNotFoundByIdException("Inventory not found"));
	}

	

	
	
}
