package com.caglar.controller;

import java.util.List;

import com.caglar.dto.DtoGallerist;
import com.caglar.dto.DtoGalleristIU;

public interface IRestGalleristController {

	public RootEntity<DtoGallerist> saveGallerist(DtoGalleristIU dtoGalleristIU);
	
	public RootEntity<List<DtoGallerist>> getAllGallerist();
	
	public RootEntity<DtoGallerist> getGalleristById(Long id);
}
