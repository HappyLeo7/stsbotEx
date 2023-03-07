package com.keduit.bpro051.service;

import com.keduit.bpro051.dto.MemomDTO;
import com.keduit.bpro051.dto.PageRequestDTO;
import com.keduit.bpro051.dto.PageResultDTO;
import com.keduit.bpro051.entity.Memom;

public interface MemoService {
	Long register(MemomDTO dto);
	
	
	default Memom dtoToEntity(MemomDTO dto) {
		Memom entity =Memom.builder()
				.bno(dto.getBno())
				.title(dto.getTitle())
				.content(dto.getContent())
				.writer(dto.getWriter())
				.build();
		return entity;
	}
	
	default MemomDTO entityTODto(Memom entity) {
		MemomDTO dto = MemomDTO.builder()
				.bno(entity.getBno())
				.title(entity.getTitle())
				.content(entity.getContent())
				.writer(entity.getWriter())
				.regDate(entity.getRegDate())
				.updateDate(entity.getUpdateDate())
				.build();
		
		return dto;
				
	}


	PageResultDTO<MemomDTO, Memom> getList(PageRequestDTO requestDTO);


	MemomDTO read(Long bno);
	
	void remove(Long bno);
	void modify(MemomDTO bno);
}
