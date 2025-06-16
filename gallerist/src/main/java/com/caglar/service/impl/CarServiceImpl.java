package com.caglar.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.caglar.dto.DtoCar;
import com.caglar.dto.DtoCarIU;
import com.caglar.exception.BaseException;
import com.caglar.exception.ErrorMessage;
import com.caglar.exception.MessageType;
import com.caglar.mapper.AppMapper;
import com.caglar.model.Car;
import com.caglar.repository.CarRepository;
import com.caglar.service.ICarService;

@Service
public class CarServiceImpl implements ICarService {

    @Autowired
    private CarRepository carRepository;

    @Autowired
    private AppMapper appMapper;

    private Car createCar(DtoCarIU dtoCarIU) {
        Car car = appMapper.toEntity(dtoCarIU);
        car.setCreateTime(new Date());
        return car;
    }

    @Override
    public DtoCar saveCar(DtoCarIU dtoCarIU) {
        Car savedCar = carRepository.save(createCar(dtoCarIU));
        return appMapper.toDto(savedCar);
    }

	@Override
	public List<DtoCar> getAllCar() {
		List<Car> car = carRepository.findAll();
		return car.stream().map(appMapper::toDto).toList();
	}

	@Override
	public DtoCar getCarById(Long id) {
		Car car =carRepository.findById(id).orElseThrow( ()->new BaseException(new ErrorMessage(MessageType.NO_RECORD_EXIST, id.toString())));
		return appMapper.toDto(car);
	}
}
