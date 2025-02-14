package com.dev.forest.member.model.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.dev.forest.member.model.dto.ChangePwdDTO;
import com.dev.forest.member.model.dto.MemberDTO;

@Mapper
public interface MemberMapper {

	void saveMember(MemberDTO member);

	MemberDTO findByUserId(String userId);

	MemberDTO findByNickname(String nickname);

	MemberDTO findByNicknameSns(String nickname);

	void update(ChangePwdDTO changePwd);

	void delete(Long userNo);

}
