package com.caglar.controller;

import java.util.List;

import com.caglar.dto.DtoAddress;
import com.caglar.dto.DtoAddressIU;

public interface IRestAddressController {

	public RootEntity<DtoAddress> saveAddress(DtoAddressIU dtoAddressIU);
	
	public RootEntity<List<DtoAddress>> getAllAddress();
	
	public RootEntity<DtoAddress> getAddressById(Long id);
	
}
