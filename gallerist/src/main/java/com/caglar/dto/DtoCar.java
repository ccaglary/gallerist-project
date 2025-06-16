package com.caglar.dto;

import java.math.BigDecimal;

import com.caglar.enums.CarStatusType;
import com.caglar.enums.CurrencyType;

import lombok.Data;

@Data
public class DtoCar extends DtoBase {

	private String plaka;
	
	private String brand;
	
	private String model;
	
	private Integer productionYear;
	
	private BigDecimal price;
	
	private CurrencyType currencyType; 
	
	private BigDecimal damagePrice;
	
	private CarStatusType carStatusType;
}
