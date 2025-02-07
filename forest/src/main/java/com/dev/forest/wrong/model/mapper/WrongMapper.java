package com.dev.forest.wrong.model.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.dev.forest.wrong.model.dto.WrongDTO;

@Mapper
public interface WrongMapper {

	List<WrongDTO> findAll();

	List<WrongDTO> findById(Long userNo);

	void insertWrong(WrongDTO wrong);

}
