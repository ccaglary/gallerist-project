package com.caglar.service;

import java.util.List;

import com.caglar.dto.DtoAddress;
import com.caglar.dto.DtoAddressIU;

public interface IAddressService {

	public DtoAddress saveAddress(DtoAddressIU dtoAddressIU);
	
	public List<DtoAddress> getAllAddress();
	
	public DtoAddress getAddressById(Long id);
}
