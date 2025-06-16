package com.caglar.service;

import com.caglar.dto.CurrencyRatesResponse;

public interface ICurrencyRatesService {

	public CurrencyRatesResponse getCurrencyRates(String startDate, String endDate);
}
