package com.caglar.controller;

import java.util.List;

import com.caglar.dto.DtoSaledCar;
import com.caglar.dto.DtoSaledCarIU;

public interface IRestSaledCarController {

	public RootEntity<DtoSaledCar> buyCar(DtoSaledCarIU dtoSaledCarIU);
	
	public RootEntity<List<DtoSaledCar>> getAllSaledCar();
	
	public RootEntity<DtoSaledCar> getSaledCarById(Long id);
}
