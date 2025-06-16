package com.caglar.dto;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DtoUser {

	private Long id;
	
	private Date createTime;
	
	private String username;
	
	private String password;
	
}
