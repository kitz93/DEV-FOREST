package com.dev.forest.theory.model.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.dev.forest.theory.model.dto.TheoryDTO;

@Mapper
public interface TheoryMapper {

	List<TheoryDTO> getAllTheorys();

	TheoryDTO getTheoryById(Long id);


}
