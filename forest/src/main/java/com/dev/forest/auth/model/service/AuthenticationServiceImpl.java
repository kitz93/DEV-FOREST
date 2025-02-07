package com.dev.forest.auth.model.service;

import java.util.Map;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import com.dev.forest.auth.model.vo.CustomUserDetails;
import com.dev.forest.member.model.dto.MemberDTO;
import com.dev.forest.token.model.service.TokenService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {

	private final AuthenticationManager authenticationManager;
	private final TokenService tokenService;

	@Override
	public Map<String, String> login(MemberDTO member) {
		Authentication authentication = authenticationManager
				.authenticate(new UsernamePasswordAuthenticationToken(member.getUserId(), member.getUserPwd()));
		CustomUserDetails user = (CustomUserDetails)authentication.getPrincipal();
		Map<String, String> tokens = tokenService.generatorToken(user.getUsername(), user.getUserNo());
		return tokens;
	}

}
