package com.caglar.handler;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Exception <E> {
 
	private String hostName;
	private String path;
	private Date createTime;
	private E message;
	
}
