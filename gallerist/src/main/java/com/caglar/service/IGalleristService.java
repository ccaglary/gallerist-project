package com.caglar.service;

import java.util.List;

import com.caglar.dto.DtoGallerist;
import com.caglar.dto.DtoGalleristIU;

public interface IGalleristService {

	public DtoGallerist saveGallerist(DtoGalleristIU dtoGalleristIU);
	
	public List<DtoGallerist> getAllGallerists();
	
	public DtoGallerist getGalleristById(Long id);
}
