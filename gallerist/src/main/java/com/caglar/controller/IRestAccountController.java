package com.caglar.controller;

import java.util.List;

import com.caglar.dto.DtoAccount;
import com.caglar.dto.DtoAccountIU;

public interface IRestAccountController {

	public RootEntity<DtoAccount> saveAccount(DtoAccountIU doAccountIU);
	
	public RootEntity<List<DtoAccount>> getAllAccounts();
	
	public RootEntity<DtoAccount> getAccountById(Long id);
}
