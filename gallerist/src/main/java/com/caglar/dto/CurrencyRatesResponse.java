package com.caglar.dto;

import java.util.List;

import lombok.Data;

@Data
public class CurrencyRatesResponse {

	private Integer totalCount;
	
	private List<CurrencyRatesItems> items;
}
