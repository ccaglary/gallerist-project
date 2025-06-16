package com.caglar.service;

import java.util.List;

import com.caglar.dto.DtoCar;
import com.caglar.dto.DtoCarIU;

public interface ICarService {

	public DtoCar saveCar(DtoCarIU dtoCarIU);
	
	public List<DtoCar> getAllCar();
	
	public DtoCar getCarById(Long id);
}
