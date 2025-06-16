package com.caglar.controller.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.caglar.controller.IRestGalleristController;
import com.caglar.controller.RestBaseController;
import com.caglar.controller.RootEntity;
import com.caglar.dto.DtoGallerist;
import com.caglar.dto.DtoGalleristIU;
import com.caglar.service.IGalleristService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/rest/api/gallerist")
public class RestGalleristControllerImpl extends RestBaseController implements IRestGalleristController{

	@Autowired
	private IGalleristService galleristService;
	
	@PreAuthorize("hasRole('ADMIN')")
	@PostMapping("/save")
	@Override
	public RootEntity<DtoGallerist> saveGallerist(@Valid @RequestBody DtoGalleristIU dtoGalleristIU) {

		return ok(galleristService.saveGallerist(dtoGalleristIU));
	}

	@GetMapping("/list")
	@Override
	public RootEntity<List<DtoGallerist>> getAllGallerist() {
		return ok(galleristService.getAllGallerists());
	}
	
	@GetMapping("/list/{id}")
	@Override
	public RootEntity<DtoGallerist> getGalleristById(Long id) {
		return ok(galleristService.getGalleristById(id));
	}

	
}
