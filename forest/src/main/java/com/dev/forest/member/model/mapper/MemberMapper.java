package com.dev.forest.member.model.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.dev.forest.member.model.dto.ChangePwdDTO;
import com.dev.forest.member.model.dto.MemberDTO;
import com.dev.forest.member.model.dto.SnsMemberDTO;

@Mapper
public interface MemberMapper {

	void saveMember(MemberDTO member);

	void saveSnsMember(SnsMemberDTO member);
	
	MemberDTO findByUserId(String userId);

	MemberDTO findByNickname(String nickname);

	SnsMemberDTO findBySnsId(Long snsId);
	
	SnsMemberDTO findByNicknameSns(String nickname);

	void update(ChangePwdDTO changePwd);

	void delete(Long userNo);

	SnsMemberDTO snsLogin(SnsMemberDTO member);

}
