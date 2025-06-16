package com.caglar.dto;

import java.util.Date;

import lombok.Data;

@Data
public class DtoCustomer extends DtoBase{

	private String firstName;
	
	private String lastName;
	
	private String tckn;
	
	private Date birthofDate;
	
	private DtoAddress address;
	
	private DtoAccount account;
}
