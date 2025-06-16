package com.caglar.service;

import com.caglar.dto.AuthRequest;
import com.caglar.dto.AuthResponse;
import com.caglar.dto.DtoUser;
import com.caglar.dto.RefreshTokenRequest;

public interface IAuthenticationService {

	public DtoUser register(AuthRequest request);
	
	public AuthResponse authenticate(AuthRequest request);
	
	public AuthResponse refreshToken(RefreshTokenRequest request);
}
