package com.caglar.controller;

import java.util.List;

import com.caglar.dto.DtoCar;
import com.caglar.dto.DtoCarIU;

public interface IRestCarController {

	public RootEntity<DtoCar> saveCar(DtoCarIU dtoCarIU);
	
	public RootEntity<List<DtoCar>> getAllCar();
	
	public RootEntity<DtoCar> getCarById(Long id);
}
