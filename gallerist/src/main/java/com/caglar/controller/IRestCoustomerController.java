package com.caglar.controller;

import java.util.List;

import com.caglar.dto.DtoCustomer;
import com.caglar.dto.DtoCustomerIU;

public interface IRestCoustomerController {

	public RootEntity<DtoCustomer> saveCustomer(DtoCustomerIU dtoCustomerIU);
	
	public RootEntity<List<DtoCustomer>> getAllCustomer();
	
	public RootEntity<DtoCustomer> getCustomerById(Long id);
}
