package com.caglar.service;

import java.util.List;

import com.caglar.dto.DtoCustomer;
import com.caglar.dto.DtoCustomerIU;

public interface ICustomerService {

	public DtoCustomer saveCustomer(DtoCustomerIU dtoCustomerIU);
	
	public List<DtoCustomer> getAllCustomer();
	
	public DtoCustomer getCustomerById(Long id);
 }
