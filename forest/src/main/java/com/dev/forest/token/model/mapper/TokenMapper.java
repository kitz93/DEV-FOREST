package com.dev.forest.token.model.mapper;

import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.dev.forest.token.model.dto.RefreshTokenDTO;

@Mapper
public interface TokenMapper {
	
	void saveToken(RefreshTokenDTO token);
	
	RefreshTokenDTO findByToken(String refreshToken);
	
	void deleteExpiredRefreshToken(Map<String, Long> params);

}
