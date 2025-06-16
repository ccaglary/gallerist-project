package com.caglar.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.caglar.dto.DtoGallerist;
import com.caglar.dto.DtoGalleristIU;
import com.caglar.exception.BaseException;
import com.caglar.exception.ErrorMessage;
import com.caglar.exception.MessageType;
import com.caglar.mapper.AppMapper;
import com.caglar.model.Address;
import com.caglar.model.Gallerist;
import com.caglar.repository.AddressRepository;
import com.caglar.repository.GalleristRepository;
import com.caglar.service.IGalleristService;

@Service
public class GalleristServiceImpl implements IGalleristService {

    @Autowired
    private GalleristRepository galleristRepository;

    @Autowired
    private AddressRepository addressRepository;

    @Autowired
    private AppMapper appMapper;

    private Gallerist createDtoGallerist(DtoGalleristIU dtoGalleristIU) {
        Address address = addressRepository.findById(dtoGalleristIU.getAddressId())
                .orElseThrow(() -> new BaseException(
                        new ErrorMessage(MessageType.NO_RECORD_EXIST, dtoGalleristIU.getAddressId().toString())));

        Gallerist gallerist = appMapper.toEntity(dtoGalleristIU);
        gallerist.setCreateTime(new Date());
        gallerist.setAddress(address);

        return gallerist;
    }

    @Override
    public DtoGallerist saveGallerist(DtoGalleristIU dtoGalleristIU) {
        Gallerist savedGallerist = galleristRepository.save(createDtoGallerist(dtoGalleristIU));
        return appMapper.toDto(savedGallerist);
    }

	@Override
	public List<DtoGallerist> getAllGallerists() {
		List<Gallerist> gallerist = galleristRepository.findAll();
		return gallerist.stream().map(appMapper::toDto).toList();
	}

	@Override
	public DtoGallerist getGalleristById(Long id) {
		Gallerist gallerist = galleristRepository.findById(id).orElseThrow(()-> new BaseException(new ErrorMessage(MessageType.NO_RECORD_EXIST, id.toString())));
		return appMapper.toDto(gallerist);
	}
}
