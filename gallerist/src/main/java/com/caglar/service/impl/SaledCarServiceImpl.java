package com.caglar.service.impl;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.caglar.dto.CurrencyRatesResponse;
import com.caglar.dto.DtoCar;
import com.caglar.dto.DtoCustomer;
import com.caglar.dto.DtoGallerist;
import com.caglar.dto.DtoSaledCar;
import com.caglar.dto.DtoSaledCarIU;
import com.caglar.enums.CarStatusType;
import com.caglar.exception.BaseException;
import com.caglar.exception.ErrorMessage;
import com.caglar.exception.MessageType;
import com.caglar.mapper.AppMapper;
import com.caglar.mapper.AppMapperImpl;
import com.caglar.model.Car;
import com.caglar.model.Customer;
import com.caglar.model.Gallerist;
import com.caglar.model.SaledCar;
import com.caglar.repository.CarRepository;
import com.caglar.repository.CustomerRepository;
import com.caglar.repository.GalleristRepository;
import com.caglar.repository.SaledCarRepository;
import com.caglar.service.ICurrencyRatesService;
import com.caglar.service.ISaledCarService;
import com.caglar.utils.DateUtils;

@Service
public class SaledCarServiceImpl implements ISaledCarService {


	@Autowired
	private SaledCarRepository saledCarRepository; 
	
	@Autowired
	private CustomerRepository customerRepository;

	@Autowired
	private GalleristRepository galleristRepository;

	@Autowired
	private CarRepository carRepository;

	@Autowired
	private ICurrencyRatesService currencyRatesService;
	
	@Autowired
	private AppMapper appMapper;




	public BigDecimal convertCustomerAmountToUSD(Customer customer) {

		CurrencyRatesResponse currencyRatesResponse = currencyRatesService
				.getCurrencyRates("13-06-2025", "13-06-2025");
		BigDecimal usd = new BigDecimal(currencyRatesResponse.getItems().get(0).getUsd());

		BigDecimal customerUSDAmount = customer.getAccount().getAmount().divide(usd, 2, RoundingMode.HALF_UP);
		return customerUSDAmount;
	}
	public boolean checkCarStatus(Long carId) {
		Optional<Car> optCar = carRepository.findById(carId);
		if (optCar.isPresent() && optCar.get().getCarStatusType().name().equals(CarStatusType.SALED.name())) {
			return false;
		}
		return true;
	}
	
	public BigDecimal remaninCustomerAmount(Customer customer, Car car) {
		BigDecimal customerUSDAmount = convertCustomerAmountToUSD(customer);
		BigDecimal remaningCustomerUSDAmount = customerUSDAmount.subtract(car.getPrice());
		CurrencyRatesResponse currencyRatesResponse = currencyRatesService
				.getCurrencyRates("13-06-2025", "13-06-2025");
		BigDecimal usd = new BigDecimal(currencyRatesResponse.getItems().get(0).getUsd());
		return remaningCustomerUSDAmount.multiply(usd);
	}

	public boolean checkAmount(DtoSaledCarIU dtoSaledCarIU) {

		Optional<Customer> optCustomer = customerRepository.findById(dtoSaledCarIU.getCustomerId());
		if (optCustomer.isEmpty()) {
			throw new BaseException(
					new ErrorMessage(MessageType.NO_RECORD_EXIST, dtoSaledCarIU.getCustomerId().toString()));
		}

		Optional<Car> optCar = carRepository.findById(dtoSaledCarIU.getCarId());
		if (optCar.isEmpty()) {
			throw new BaseException(new ErrorMessage(MessageType.NO_RECORD_EXIST, dtoSaledCarIU.getCarId().toString()));
		}

		BigDecimal customerUSDAmount = convertCustomerAmountToUSD(optCustomer.get());

		if (customerUSDAmount.compareTo(optCar.get().getPrice()) >= 0) {
			return true;
		}
		return false;

	}
	
	private SaledCar createSaledCar(DtoSaledCarIU dtoSaledCarIU) {
	    SaledCar saledCar = appMapper.toEntity(dtoSaledCarIU);
	    saledCar.setCreateTime(new Date());

	    Gallerist gallerist = galleristRepository.findById(dtoSaledCarIU.getGalleristId())
	        .orElseThrow(() -> new BaseException(new ErrorMessage(MessageType.NO_RECORD_EXIST, dtoSaledCarIU.getGalleristId().toString())));
	    
	    Customer customer = customerRepository.findById(dtoSaledCarIU.getCustomerId())
	        .orElseThrow(() -> new BaseException(new ErrorMessage(MessageType.NO_RECORD_EXIST, dtoSaledCarIU.getCustomerId().toString())));
	    
	    Car car = carRepository.findById(dtoSaledCarIU.getCarId())
	        .orElseThrow(() -> new BaseException(new ErrorMessage(MessageType.NO_RECORD_EXIST, dtoSaledCarIU.getCarId().toString())));

	    saledCar.setCustomer(customer);
	    saledCar.setGallerist(gallerist);
	    saledCar.setCar(car);
	    return saledCar;
	}


	@Override
	public DtoSaledCar buyCar(DtoSaledCarIU dtoSaledCarIU) {
	    if (!checkCarStatus(dtoSaledCarIU.getCarId())) {
	        throw new BaseException(new ErrorMessage(MessageType.CAR_STATUS_IS_SALED, dtoSaledCarIU.getCarId().toString()));
	    }

	    if (!checkAmount(dtoSaledCarIU)) {
	        throw new BaseException(new ErrorMessage(MessageType.CUSTOMER_AMOUNT_IS_NOT_ENOUGH, ""));
	    }

	    SaledCar savedSaledCar = saledCarRepository.save(createSaledCar(dtoSaledCarIU));

	    Car car = savedSaledCar.getCar();
	    car.setCarStatusType(CarStatusType.SALED);
	    carRepository.save(car);

	    Customer customer = savedSaledCar.getCustomer();
	    customer.getAccount().setAmount(remaninCustomerAmount(customer, car));
	    customerRepository.save(customer);

	    return appMapper.toDto(savedSaledCar);
	}
	
	
	@Override
	public List<DtoSaledCar> getAllCar() {
		List<SaledCar> saledCar = saledCarRepository.findAll();
		return saledCar.stream().map(appMapper::toDto).toList();
	}
	@Override
	public DtoSaledCar getCarById(Long id) {
		
		SaledCar saledCar = saledCarRepository.findById(id).orElseThrow(()-> new BaseException
				(new ErrorMessage(MessageType.NO_RECORD_EXIST, id.toString())));
		
		return appMapper.toDto(saledCar);
	}


}
