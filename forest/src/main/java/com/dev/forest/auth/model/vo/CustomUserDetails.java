package com.dev.forest.auth.model.vo;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class CustomUserDetails implements UserDetails {

	private Long userNo;
	private String username;
	private String password;
	private String nickname;
	private Collection<? extends GrantedAuthority> authorities;
	private String status;

}
