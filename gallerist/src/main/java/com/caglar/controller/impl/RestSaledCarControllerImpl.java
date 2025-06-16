package com.caglar.controller.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.caglar.controller.IRestSaledCarController;
import com.caglar.controller.RestBaseController;
import com.caglar.controller.RootEntity;
import com.caglar.dto.DtoSaledCar;
import com.caglar.dto.DtoSaledCarIU;
import com.caglar.service.ISaledCarService;

import jakarta.validation.Valid;

@RequestMapping("/rest/api/saled-car")
@RestController
public class RestSaledCarControllerImpl extends RestBaseController implements IRestSaledCarController {

	@Autowired
	private ISaledCarService saledCarService;
	
	@PreAuthorize("hasRole('ADMIN')")
	@PostMapping("/save")
	@Override
	public RootEntity<DtoSaledCar> buyCar(@Valid @RequestBody DtoSaledCarIU dtoSaledCarIU) {
		return ok(saledCarService.buyCar(dtoSaledCarIU));
	}

	@GetMapping("/list")
	@Override
	public RootEntity<List<DtoSaledCar>> getAllSaledCar() {
		return ok(saledCarService.getAllCar());
	}

	@GetMapping("/list/{id}")
	@Override
	public RootEntity<DtoSaledCar> getSaledCarById(Long id) {
		return ok(saledCarService.getCarById(id));
	}

}
