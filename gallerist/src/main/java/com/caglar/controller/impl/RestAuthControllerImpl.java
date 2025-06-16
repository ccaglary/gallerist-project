package com.caglar.controller.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.caglar.controller.IRestAuthController;
import com.caglar.controller.RestBaseController;
import com.caglar.controller.RootEntity;
import com.caglar.dto.AuthRequest;
import com.caglar.dto.AuthResponse;
import com.caglar.dto.DtoUser;
import com.caglar.dto.RefreshTokenRequest;
import com.caglar.service.IAuthenticationService;

import jakarta.validation.Valid;

@RestController
public class RestAuthControllerImpl extends RestBaseController implements IRestAuthController{

	@Autowired
	private IAuthenticationService authenticationService;
	
	@PostMapping("/register")
	@Override
	public RootEntity<DtoUser> register(@Valid @RequestBody AuthRequest request) {
		
		return ok(authenticationService.register(request));
	}

	@PostMapping("/authenticate")
	@Override
	public RootEntity<AuthResponse> authenticate(@Valid @RequestBody AuthRequest request) {
		
		
		return ok(authenticationService.authenticate(request));
	}

	@PostMapping("/refreshToken")
	@Override
	public RootEntity<AuthResponse> refreshToken(@Valid @RequestBody RefreshTokenRequest request) {
		
		return ok(authenticationService.refreshToken(request));  
	}

	
}
