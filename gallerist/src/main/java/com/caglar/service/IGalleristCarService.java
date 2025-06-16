package com.caglar.service;

import java.util.List;

import com.caglar.dto.DtoGalleristCar;
import com.caglar.dto.DtoGalleristCarIU;

public interface IGalleristCarService {

	public DtoGalleristCar saveGalleristCar(DtoGalleristCarIU dtoGalleristCarIU);
	
	public List<DtoGalleristCar> getAllGalleristCar();

	public DtoGalleristCar getGalleristCarById(Long id);
}
