package com.caglar.controller;

import java.util.List;

import com.caglar.dto.DtoGalleristCar;
import com.caglar.dto.DtoGalleristCarIU;

public interface IRestGalleristCarController {

	public RootEntity<DtoGalleristCar> saveGalleristCar(DtoGalleristCarIU dtoGalleristCarIU);
	
	public RootEntity<List<DtoGalleristCar>> getAllGalleristCar();
	
	public RootEntity<DtoGalleristCar> getGalleristCarById(Long id);
}
