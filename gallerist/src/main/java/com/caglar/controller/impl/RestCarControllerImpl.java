package com.caglar.controller.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.caglar.controller.IRestCarController;
import com.caglar.controller.RestBaseController;
import com.caglar.controller.RootEntity;
import com.caglar.dto.DtoCar;
import com.caglar.dto.DtoCarIU;
import com.caglar.service.ICarService;

import jakarta.validation.Valid;

@RequestMapping("/rest/api/car")
@RestController
public class RestCarControllerImpl extends RestBaseController implements IRestCarController{

	@Autowired
	private ICarService carService;
	
	@PostMapping("/save")
	@Override
	public RootEntity<DtoCar> saveCar(@Valid @RequestBody DtoCarIU dtoCarIU) {
		return ok(carService.saveCar(dtoCarIU));
	}

	@GetMapping("/list")
	@Override
	public RootEntity<List<DtoCar>> getAllCar() {
		return ok(carService.getAllCar());
	}
	@GetMapping("/list/{id}")
	@Override
	public RootEntity<DtoCar> getCarById(@PathVariable Long id) {
		return ok(carService.getCarById(id));
	}

}
