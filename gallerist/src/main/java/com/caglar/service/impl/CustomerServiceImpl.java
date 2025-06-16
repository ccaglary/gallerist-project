package com.caglar.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.caglar.dto.DtoCustomer;
import com.caglar.dto.DtoCustomerIU;
import com.caglar.exception.BaseException;
import com.caglar.exception.ErrorMessage;
import com.caglar.exception.MessageType;
import com.caglar.mapper.AppMapper;
import com.caglar.model.Account;
import com.caglar.model.Address;
import com.caglar.model.Customer;
import com.caglar.repository.AccountRepository;
import com.caglar.repository.AddressRepository;
import com.caglar.repository.CustomerRepository;
import com.caglar.service.ICustomerService;

@Service
public class CustomerServiceImpl implements ICustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private AddressRepository addressRepository;

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private AppMapper appMapper;

    private Customer createCustomer(DtoCustomerIU dtoCustomerIU) {
        Address address = addressRepository.findById(dtoCustomerIU.getAddressId())
                .orElseThrow(() -> new BaseException(new ErrorMessage(
                        MessageType.NO_RECORD_EXIST, dtoCustomerIU.getAddressId().toString())));

        Account account = accountRepository.findById(dtoCustomerIU.getAccountId())
                .orElseThrow(() -> new BaseException(new ErrorMessage(
                        MessageType.NO_RECORD_EXIST, dtoCustomerIU.getAccountId().toString())));

        Customer customer = appMapper.toEntity(dtoCustomerIU);
        customer.setCreateTime(new Date());
        customer.setAddress(address);
        customer.setAccount(account);

        return customer;
    }

    @Override
    public DtoCustomer saveCustomer(DtoCustomerIU dtoCustomerIU) {
        Customer savedCustomer = customerRepository.save(createCustomer(dtoCustomerIU));
        return appMapper.toDto(savedCustomer);
    }

	@Override
	public List<DtoCustomer> getAllCustomer() {
		List<Customer> customer = customerRepository.findAll();
		
		return customer.stream().map(appMapper::toDto).toList();
	}

	@Override
	public DtoCustomer getCustomerById(Long id) {
		Customer customer = customerRepository.findById(id).orElseThrow(()->
		new BaseException(new ErrorMessage(MessageType.NO_RECORD_EXIST, id.toString())));
		return appMapper.toDto(customer);
	}
}
