package com.dev.forest.auth.model.service;

import java.util.Collections;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.dev.forest.auth.model.vo.CustomUserDetails;
import com.dev.forest.member.model.dto.MemberDTO;
import com.dev.forest.member.model.dto.SnsMemberDTO;
import com.dev.forest.member.model.mapper.MemberMapper;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserDetailsService {

	private final MemberMapper memberMapper;

	@Override
	public UserDetails loadUserByUsername(String username) {
		CustomUserDetails userDetails = null;
		MemberDTO user = memberMapper.findByUserId(username);
		if (user != null) {
			userDetails = CustomUserDetails.builder().userNo(user.getUserNo()).username(user.getUserId())
					.password(user.getUserPwd()).nickname(user.getNickname())
					.authorities(Collections.singletonList(new SimpleGrantedAuthority(user.getRole())))
					.status(user.getStatus()).build();
		} else {
			SnsMemberDTO snsUser = memberMapper.findBySnsId(username);
			if (snsUser != null) {
				userDetails = CustomUserDetails.builder().userNo(snsUser.getUserNo()).username(snsUser.getSnsId())
						.nickname(snsUser.getNickname())
						.authorities(Collections.singletonList(new SimpleGrantedAuthority(snsUser.getRole())))
						.status(snsUser.getStatus()).build();
			}
		}
		if (userDetails == null) {
			throw new UsernameNotFoundException("존재하지 않는 사용자입니다.");
		}
		return userDetails;
	}

}
