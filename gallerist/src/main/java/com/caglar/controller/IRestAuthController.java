package com.caglar.controller;

import com.caglar.dto.AuthRequest;
import com.caglar.dto.AuthResponse;
import com.caglar.dto.DtoUser;
import com.caglar.dto.RefreshTokenRequest;

public interface IRestAuthController {

	public RootEntity<DtoUser> register(AuthRequest request);
	
	public RootEntity<AuthResponse> authenticate(AuthRequest request);
	
	public RootEntity<AuthResponse> refreshToken(RefreshTokenRequest request);
}
