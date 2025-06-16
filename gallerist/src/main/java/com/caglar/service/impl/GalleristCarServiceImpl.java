package com.caglar.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.caglar.dto.DtoGalleristCar;
import com.caglar.dto.DtoGalleristCarIU;
import com.caglar.exception.BaseException;
import com.caglar.exception.ErrorMessage;
import com.caglar.exception.MessageType;
import com.caglar.mapper.AppMapper;
import com.caglar.model.Car;
import com.caglar.model.Gallerist;
import com.caglar.model.GalleristCar;
import com.caglar.repository.CarRepository;
import com.caglar.repository.GalleristCarRepository;
import com.caglar.repository.GalleristRepository;
import com.caglar.service.IGalleristCarService;

@Service
public class GalleristCarServiceImpl implements IGalleristCarService {

    @Autowired
    private GalleristCarRepository galleristCarRepository;

    @Autowired
    private GalleristRepository galleristRepository;

    @Autowired
    private CarRepository carRepository;

    @Autowired
    private AppMapper appMapper;

    private GalleristCar createGalleristCar(DtoGalleristCarIU dtoGalleristCarIU) {
        Gallerist gallerist = galleristRepository.findById(dtoGalleristCarIU.getGalleristId())
                .orElseThrow(() -> new BaseException(new ErrorMessage(
                        MessageType.NO_RECORD_EXIST, dtoGalleristCarIU.getGalleristId().toString())));

        Car car = carRepository.findById(dtoGalleristCarIU.getCarId())
                .orElseThrow(() -> new BaseException(new ErrorMessage(
                        MessageType.NO_RECORD_EXIST, dtoGalleristCarIU.getCarId().toString())));

        GalleristCar galleristCar = new GalleristCar();
        galleristCar.setCreateTime(new Date());
        galleristCar.setCar(car);
        galleristCar.setGallerist(gallerist);

        return galleristCar;
    }

    @Override
    public DtoGalleristCar saveGalleristCar(DtoGalleristCarIU dtoGalleristCarIU) {
        GalleristCar savedGalleristCar = galleristCarRepository.save(createGalleristCar(dtoGalleristCarIU));
        return appMapper.toDto(savedGalleristCar);
    }

	@Override
	public List<DtoGalleristCar> getAllGalleristCar() {
		List<GalleristCar> galleristCar =  galleristCarRepository.findAll();
		return galleristCar.stream().map(appMapper::toDto).toList();
	}

	@Override
	public DtoGalleristCar getGalleristCarById(Long id) {
		GalleristCar galleristCar = galleristCarRepository.findById(id).orElseThrow(
				()-> new BaseException(new ErrorMessage(MessageType.NO_RECORD_EXIST, id.toString())));
		return appMapper.toDto(galleristCar);
	}
}
