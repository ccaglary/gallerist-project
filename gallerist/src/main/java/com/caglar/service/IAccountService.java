package com.caglar.service;

import java.util.List;

import com.caglar.dto.DtoAccount;
import com.caglar.dto.DtoAccountIU;

public interface IAccountService {

	public DtoAccount saveAccount(DtoAccountIU dtoAccountIU);

	public List<DtoAccount> getAllAccounts();
	
	public DtoAccount getAccountById(Long id);
}
