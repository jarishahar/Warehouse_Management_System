package com.jsp.wms.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
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
@Entity
public class Batch {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int batchId;
	private int quantity;
	
	@ManyToOne
	private Storage storage;
	@ManyToOne
	private Inventory  inventory;

}
