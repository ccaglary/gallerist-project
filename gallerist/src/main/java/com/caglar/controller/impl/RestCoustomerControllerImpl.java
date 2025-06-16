package com.caglar.controller.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.caglar.controller.IRestCoustomerController;
import com.caglar.controller.RestBaseController;
import com.caglar.controller.RootEntity;
import com.caglar.dto.DtoCustomer;
import com.caglar.dto.DtoCustomerIU;
import com.caglar.service.ICustomerService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/rest/api/customer")
public class RestCoustomerControllerImpl extends RestBaseController implements IRestCoustomerController{

	@Autowired
	private ICustomerService customerService;
	
	@PreAuthorize("hasRole('ADMIN')")
	@PostMapping("/save")
	@Override
	public RootEntity<DtoCustomer> saveCustomer(@Valid @RequestBody DtoCustomerIU dtoCustomerIU) {
		return ok(customerService.saveCustomer(dtoCustomerIU));
	}

	@GetMapping("/list")
	@Override
	public RootEntity<List<DtoCustomer>> getAllCustomer() {
		return ok(customerService.getAllCustomer());
	}

	@GetMapping("/list/{id}")
	@Override
	public RootEntity<DtoCustomer> getCustomerById(Long id) {
		return ok(customerService.getCustomerById(id));
	}

}
