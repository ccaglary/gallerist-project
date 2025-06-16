package com.caglar.service;

import java.util.List;

import com.caglar.dto.DtoSaledCar;
import com.caglar.dto.DtoSaledCarIU;

public interface ISaledCarService {

	public DtoSaledCar buyCar(DtoSaledCarIU dtoSaledCarIU); 
	
	public List<DtoSaledCar> getAllCar();
	
	public DtoSaledCar getCarById(Long id);
	
	
	
}
