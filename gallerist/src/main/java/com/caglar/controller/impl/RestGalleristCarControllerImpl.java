package com.caglar.controller.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.caglar.controller.IRestGalleristCarController;
import com.caglar.controller.RestBaseController;
import com.caglar.controller.RootEntity;
import com.caglar.dto.DtoGalleristCar;
import com.caglar.dto.DtoGalleristCarIU;
import com.caglar.service.IGalleristCarService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/rest/api/galleristcar")
public class RestGalleristCarControllerImpl extends RestBaseController implements IRestGalleristCarController{

	@Autowired
	private IGalleristCarService galleristCarService;
	
	@PreAuthorize("hasRole('ADMIN')")
	@PostMapping("/save")
	@Override
	public RootEntity<DtoGalleristCar> saveGalleristCar(@Valid @RequestBody DtoGalleristCarIU dtoGalleristCarIU) {
		return ok(galleristCarService.saveGalleristCar(dtoGalleristCarIU));
	}

	@GetMapping("/list")
	@Override
	public RootEntity<List<DtoGalleristCar>> getAllGalleristCar() {
		return ok(galleristCarService.getAllGalleristCar());
	}
	
	@GetMapping("/list/{id}")
	@Override
	public RootEntity<DtoGalleristCar> getGalleristCarById(Long id) {
		return ok(galleristCarService.getGalleristCarById(id));
	}

}
