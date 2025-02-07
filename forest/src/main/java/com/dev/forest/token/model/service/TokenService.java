package com.dev.forest.token.model.service;

import java.util.Map;

public interface TokenService {

	Map<String, String> generatorToken(String username, Long userNo);

}
