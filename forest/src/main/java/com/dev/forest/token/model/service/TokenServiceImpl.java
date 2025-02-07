package com.dev.forest.token.model.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.dev.forest.auth.util.JwtUtil;
import com.dev.forest.token.model.dto.RefreshTokenDTO;
import com.dev.forest.token.model.mapper.TokenMapper;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class TokenServiceImpl implements TokenService {
	
	private final JwtUtil jwt;
	private final TokenMapper tokenMapper;

	@Override
	public Map<String, String> generatorToken(String username, Long userNo) {
		Map<String, String> tokens = createTokens(username);
		saveToken(tokens.get("refreshToken"), userNo);
		deleteExpiredRefreshToken(userNo);
		return tokens;
	}
	
	private Map<String, String> createTokens(String username) {
		Map<String, String> tokens = new HashMap<String, String>();
		String accessToken = jwt.getAccessToken(username);
		String refreshToken = jwt.getRefressToken(username);
		tokens.put("accessToken", accessToken);
		tokens.put("refreshToken", refreshToken);
		return tokens;
	}
	
	private void saveToken(String refreshToken, Long userNo) {
		RefreshTokenDTO token = RefreshTokenDTO.builder().token(refreshToken).userNo(userNo)
				.expiredAt(System.currentTimeMillis() + 3600000L * 72).build();
		tokenMapper.saveToken(token);
	}
	
	private void deleteExpiredRefreshToken(Long userNo) {
		Map<String, Long> params = new HashMap<String, Long>();
		params.put("userNo", userNo);
		params.put("currentTime", System.currentTimeMillis());
		tokenMapper.deleteExpiredRefreshToken(params);
	}

}
