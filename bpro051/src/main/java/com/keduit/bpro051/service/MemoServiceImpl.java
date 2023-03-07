package com.keduit.bpro051.service;

import java.util.Optional;
import java.util.function.Function;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.keduit.bpro051.dto.MemomDTO;
import com.keduit.bpro051.dto.PageRequestDTO;
import com.keduit.bpro051.dto.PageResultDTO;
import com.keduit.bpro051.entity.Memom;
import com.keduit.bpro051.entity.QMemom;
import com.keduit.bpro051.re.MemoRe;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.dsl.BooleanExpression;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Service
@Log4j2
@RequiredArgsConstructor
public class MemoServiceImpl implements MemoService {
	
	private final MemoRe memore;
	
	@Override
	public Long register(MemomDTO dto) {
		log.info("DTO-------------->");
		log.info("[DTO] ::=>"+dto);
		
		Memom entity = dtoToEntity(dto);
		log.info("세이브 entity ::"+entity);
		
		memore.save(entity);
		
		return entity.getBno();
	}
	
	@Override
	public PageResultDTO<MemomDTO, Memom> getList(PageRequestDTO requestDTO){
		Pageable pageable =requestDTO.getPageable(Sort.by("bno").descending());
	
		BooleanBuilder booleanBuilder = getSearch(requestDTO);
		Page<Memom> result = memore.findAll(pageable);
		
	
		Function<Memom, MemomDTO> fn = (entity ->entityTODto(entity));
		return new PageResultDTO<>(result, fn);
	}
	
	@Override
	public MemomDTO read(Long bno) {
		Optional<Memom> result = memore.findById(bno);
		return result.isPresent()?entityTODto(result.get()):null;
	}

	@Override
	public void modify(MemomDTO dto) {
		Optional<Memom> result=memore.findById(dto.getBno());
		if(result.isPresent()) {
			//수정시켜줘
			Memom entity=result.get();
			entity.change(dto.getTitle(), dto.getContent());
			memore.save(entity);
		}
		
	}

	@Override
	public void remove(Long bno) {
		// TODO Auto-generated method stub
		
	}
	private BooleanBuilder getSearch(PageRequestDTO requestDTO){
		String type =requestDTO.getType();
		BooleanBuilder booleanBuilder = new BooleanBuilder();
		QMemom qMemom = QMemom.memom;
		String keyword=requestDTO.getKeyword();
		BooleanExpression expression = qMemom.bno.gt(0L);
		booleanBuilder.and(expression);
		
		//trim() 좌우공백무시
		if(type == null||type.trim().length() ==0) {
			return booleanBuilder;
		}
		BooleanBuilder conditionBuilder =new BooleanBuilder();
		
		if(type.contains("T")) {
			conditionBuilder.or(qMemom.title.contains(keyword));
		}
		
		if(type.contains("C")) {
			conditionBuilder.or(qMemom.content.contains(keyword));
		}
		
		if(type.contains("W")) {
			conditionBuilder.or(qMemom.writer.contains(keyword));
		}
		booleanBuilder.and(conditionBuilder);
		return booleanBuilder;
	}
}
  