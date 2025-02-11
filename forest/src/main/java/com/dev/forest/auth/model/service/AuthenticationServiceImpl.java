package com.dev.forest.auth.model.service;

import java.util.Map;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
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

	@Override
	public CustomUserDetails getAuthenticatedUser() {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		CustomUserDetails user = (CustomUserDetails)auth.getPrincipal();
		return user;
	}

	@Override
	public void validWriter(String writer, String username) {
		System.out.println("유저네임 : "+ username);
		System.out.println("사용자 네임 : " + writer);
		if(writer != null && !writer.equals(username)) {
			throw new RuntimeException("요청한 사용자와 게시글 작성자가 일치하지 않습니다.");
		}
		
		
	}

}
