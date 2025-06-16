package com.caglar.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.caglar.dto.DtoAddress;
import com.caglar.dto.DtoAddressIU;
import com.caglar.exception.BaseException;
import com.caglar.exception.ErrorMessage;
import com.caglar.exception.MessageType;
import com.caglar.mapper.AppMapper;
import com.caglar.model.Address;
import com.caglar.repository.AddressRepository;
import com.caglar.service.IAddressService;

@Service
public class AddressServiceImpl implements IAddressService {

    @Autowired
    private AddressRepository addressRepository;

    @Autowired
    private AppMapper appMapper;

    private Address createAddress(DtoAddressIU dtoAddressIU) {
        Address address = appMapper.toEntity(dtoAddressIU);
        address.setCreateTime(new Date());
        return address;
    }

    @Override
    public DtoAddress saveAddress(DtoAddressIU dtoAddressIU) {
        Address savedAddress = addressRepository.save(createAddress(dtoAddressIU));
        return appMapper.toDto(savedAddress);
    }

	@Override
	public List<DtoAddress> getAllAddress() {
		List<Address> address = addressRepository.findAll();
		return address.stream().map(appMapper::toDto).toList();
	}

	@Override
	public DtoAddress getAddressById(Long id) {
		Address address = addressRepository.findById(id)
				.orElseThrow(() -> new BaseException(new ErrorMessage
						(MessageType.NO_RECORD_EXIST, id.toString())));
		return appMapper.toDto(address);
	}
}
