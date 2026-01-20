package com.example.todo.service;

import com.example.todo.dto.AuthResponse;
import com.example.todo.dto.LoginRequest;
import com.example.todo.dto.RegisterRequest;
import com.example.todo.entity.User;
import com.example.todo.repository.UserRepository;
import com.example.todo.security.JwtService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

	private final UserRepository userRepository;
	private final PasswordEncoder passwordEncoder;
	private final JwtService jwtService;

	public AuthService(
			UserRepository userRepository,
			PasswordEncoder passwordEncoder,
			JwtService jwtService) {
		this.userRepository = userRepository;
		this.passwordEncoder = passwordEncoder;
		this.jwtService = jwtService;
	}

	public AuthResponse register(RegisterRequest request) {
		User user = new User(request.username(), request.email(),
				passwordEncoder.encode(request.password()));
		userRepository.save(user);
		return new AuthResponse(jwtService.generateToken(user.getUsername()));
	}

	public AuthResponse login(LoginRequest request) {
		User user = userRepository.findByUsername(request.username())
				.orElseThrow(() -> new IllegalArgumentException("Invalid credentials"));
		if (!passwordEncoder.matches(request.password(), user.getPasswordHash())) {
			throw new IllegalArgumentException("Invalid credentials");
		}
		return new AuthResponse(jwtService.generateToken(user.getUsername()));
	}
}
