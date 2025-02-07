package com.dev.forest.auth.model.service;

import java.util.Collections;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.dev.forest.auth.model.vo.CustomUserDetails;
import com.dev.forest.member.model.dto.MemberDTO;
import com.dev.forest.member.model.mapper.MemberMapper;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserDetailsService {
	
	private final MemberMapper memberMapper;

	@Override
	public UserDetails loadUserByUsername(String username) {
		MemberDTO user = memberMapper.findByUserId(username);
		if(user == null) {
			throw new UsernameNotFoundException("존재하지 않는 사용자입니다.");
		}
		return CustomUserDetails.builder().userNo(user.getUserNo())
										  .username(user.getUserId())
										  .password(user.getUserPwd())
										  .authorities(Collections.singletonList(new SimpleGrantedAuthority(user.getRole())))
										  .build();
	}


}
