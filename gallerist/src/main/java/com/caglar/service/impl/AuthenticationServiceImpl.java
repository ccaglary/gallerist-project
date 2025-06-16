package com.caglar.service.impl;

import java.util.Date;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.caglar.dto.AuthRequest;
import com.caglar.dto.AuthResponse;
import com.caglar.dto.DtoUser;
import com.caglar.dto.RefreshTokenRequest;
import com.caglar.exception.BaseException;
import com.caglar.exception.ErrorMessage;
import com.caglar.exception.MessageType;
import com.caglar.jwt.JwtService;
import com.caglar.model.RefreshToken;
import com.caglar.model.Role;
import com.caglar.model.User;
import com.caglar.repository.RefreshTokenRepository;
import com.caglar.repository.RoleRepository;
import com.caglar.repository.UserRepository;
import com.caglar.service.IAuthenticationService;

@Service
public class AuthenticationServiceImpl implements IAuthenticationService {
	
	@Autowired
	private UserRepository userRepository;

	@Autowired
	private BCryptPasswordEncoder passwordEncoder;

	@Autowired
	private AuthenticationProvider authenticationProvider;

	@Autowired
	private JwtService jwtService;

	@Autowired
	private RefreshTokenRepository refreshTokenRepository;
	
	@Autowired
	private RoleRepository roleRepository;

	@Override
	public DtoUser register(AuthRequest request) {
		DtoUser dto = new DtoUser();
		User user = new User();
		Role userRole = roleRepository.findByName("ROLE_USER")
			    .orElseThrow(() -> new BaseException(
			        new ErrorMessage(MessageType.NO_RECORD_EXIST, "ROLE_USER")
			    ));
		
		
		user.setUsername(request.getUsername());
		user.setPassword(passwordEncoder.encode(request.getPassword()));
		user.setCreateTime(new Date());
		
		
		user.setRole(userRole);

		
		
		User savedUser = userRepository.save(user);
		BeanUtils.copyProperties(savedUser, dto);
		return dto;

	}

	private RefreshToken createRefreshToken(User user) {
		RefreshToken refreshToken = new RefreshToken();
		refreshToken.setCreateTime(new Date());
		refreshToken.setRefreshToken(UUID.randomUUID().toString());
		refreshToken.setExpiredDate(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 4));
		refreshToken.setUser(user);

		return refreshToken;
	}

	@Override
	public AuthResponse authenticate(AuthRequest request) {
		try {
			UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(request.getUsername(),
					request.getPassword());
			authenticationProvider.authenticate(auth);
			Optional<User> optionalUser = userRepository.findByUsername(request.getUsername());
			String accesToken = jwtService.generateToken(optionalUser.get());

			RefreshToken refreshToken = createRefreshToken(optionalUser.get());
			refreshTokenRepository.save(refreshToken);

			return new AuthResponse(accesToken, refreshToken.getRefreshToken());

		} catch (Exception e) {

			throw new BaseException(new ErrorMessage(MessageType.INVALID_USERNAME_OR_PASSWORD, e.getMessage()));

		}
	}

	public boolean isValidRefreshToken(Date expiredDate) {
		return new Date().before(expiredDate);
	}
	
	@Override
	public AuthResponse refreshToken(RefreshTokenRequest request) {
		Optional<RefreshToken> optRefreshToken =
				refreshTokenRepository.findByRefreshToken(request.getRefreshToken());
		if (optRefreshToken.isEmpty()) {
			throw new BaseException(new ErrorMessage(MessageType.REFRESH_TOKEN_NOT_FOUND, request.getRefreshToken()));
		}
		
		if (!isValidRefreshToken(optRefreshToken.get().getExpiredDate())) {
			throw new BaseException(new ErrorMessage(MessageType.REFRESH_TOKEN_IS_EXPIRED, request.getRefreshToken()));
		}
		
		User user = optRefreshToken.get().getUser();
		String accesToken = jwtService.generateToken(user);
		RefreshToken refreshToken = createRefreshToken(user);
		RefreshToken savedRefreshToken = refreshTokenRepository.save(refreshToken);
		
		return new AuthResponse(accesToken,savedRefreshToken.getRefreshToken());
	}

}
