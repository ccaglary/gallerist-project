package com.caglar.controller.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.caglar.controller.IRestAddressController;
import com.caglar.controller.RestBaseController;
import com.caglar.controller.RootEntity;
import com.caglar.dto.DtoAddress;
import com.caglar.dto.DtoAddressIU;
import com.caglar.service.IAddressService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/rest/api/address")
public class RestAddressControllerImpl extends RestBaseController implements IRestAddressController {

	@Autowired
	private IAddressService addressService;
	
	@PreAuthorize("hasRole('ADMIN')")
	@PostMapping("/save")
	@Override
	public RootEntity<DtoAddress> saveAddress(@Valid @RequestBody DtoAddressIU dtoAddressIU) {
		return ok(addressService.saveAddress(dtoAddressIU));
	}

	@GetMapping("/list")
	@Override
	public RootEntity<List<DtoAddress>> getAllAddress() {
		return ok(addressService.getAllAddress());
	}

	@GetMapping("list/{id}")
	@Override
	public RootEntity<DtoAddress> getAddressById(@PathVariable Long id) {
		return ok(addressService.getAddressById(id));
	}

	
}
