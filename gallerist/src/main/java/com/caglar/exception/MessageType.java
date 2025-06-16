package com.caglar.exception;

import lombok.Getter;

@Getter
public enum MessageType {

	NO_RECORD_EXIST("1004", "Kayıt Bulunamadı"),
	TOKEN_IS_EXPIRED("1005", "Token'nın Süresi Bitmiştir"),
	INVALID_USERNAME_OR_PASSWORD("1006", "Kullanıcı adı veya şifre hatalıdır"),
	USERNAME_NOT_FOUND("1007","Username bulunamadı"),
	REFRESH_TOKEN_NOT_FOUND("1008" , "Geçersiz Refresh Token"),
	REFRESH_TOKEN_IS_EXPIRED("1009" , "Refresh Token'ın Süresi Bitmiştir"),
	CURRENCY_RATES_IS_OCCURED("2000 ","Döviz Kuru Alınamadı"),
	CUSTOMER_AMOUNT_IS_NOT_ENOUGH ("2025" , "Müşterinin Parası Yeterli Değil"),
	CAR_STATUS_IS_SALED("2050", "Araba Satılmış Gözüktüğü İçin Tekrardan Satılamaz"),
	GENERAL_EXEPTION("9999", "Genl Bir Hata Oluştu");
	
	private String code;
	private String message;
	
	MessageType(String code , String message) {
		this.code = code;
		this.message = message;
	}
}
