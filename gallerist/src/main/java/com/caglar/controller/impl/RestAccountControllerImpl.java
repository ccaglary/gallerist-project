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

import com.caglar.controller.IRestAccountController;
import com.caglar.controller.RestBaseController;
import com.caglar.controller.RootEntity;
import com.caglar.dto.DtoAccount;
import com.caglar.dto.DtoAccountIU;
import com.caglar.service.IAccountService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/rest/api/account")
public class RestAccountControllerImpl extends RestBaseController implements IRestAccountController {

	@Autowired
	private IAccountService accountService;
	
	@PreAuthorize("hasRole('ADMIN')")
	@PostMapping("/save")
	@Override
	public RootEntity<DtoAccount> saveAccount(@Valid @RequestBody DtoAccountIU doAccountIU) {
		return ok(accountService.saveAccount(doAccountIU));
	}

	@GetMapping("/list")
	@Override
	public RootEntity<List<DtoAccount>> getAllAccounts() {
		return ok(accountService.getAllAccounts());
	}

	@GetMapping("/list/{id}")
	@Override
	public RootEntity<DtoAccount> getAccountById(@PathVariable Long id) {
		return ok(accountService.getAccountById(id));
	}

}
