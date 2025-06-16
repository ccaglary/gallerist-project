package com.caglar.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.caglar.dto.DtoAccount;
import com.caglar.dto.DtoAccountIU;
import com.caglar.exception.BaseException;
import com.caglar.exception.ErrorMessage;
import com.caglar.exception.MessageType;
import com.caglar.mapper.AppMapper;
import com.caglar.model.Account;
import com.caglar.repository.AccountRepository;
import com.caglar.service.IAccountService;

@Service
public class AccountServiceImpl implements IAccountService {

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private AppMapper appMapper;

    private Account createAccount(DtoAccountIU dtoAccountIU) {
        Account account = appMapper.toEntity(dtoAccountIU);
        account.setCreateTime(new Date());
        return account;
    }

    @Override
    public DtoAccount saveAccount(DtoAccountIU dtoAccountIU) {
        Account savedAccount = accountRepository.save(createAccount(dtoAccountIU));
        return appMapper.toDto(savedAccount);
    }

	@Override
	public List<DtoAccount> getAllAccounts() {
		
		List<Account> accounts = accountRepository.findAll();
		return accounts.stream().map(appMapper::toDto).toList();		
	}

	@Override
	public DtoAccount getAccountById(Long id) {
		Account account = accountRepository.findById(id)
				.orElseThrow(() -> new BaseException(new ErrorMessage(
	                    MessageType.NO_RECORD_EXIST, id.toString())));
		return appMapper.toDto(account);
	}
}
