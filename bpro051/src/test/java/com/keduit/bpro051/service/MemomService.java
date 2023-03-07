package com.keduit.bpro051.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.keduit.bpro051.dto.MemomDTO;
import com.keduit.bpro051.dto.PageRequestDTO;
import com.keduit.bpro051.dto.PageResultDTO;
import com.keduit.bpro051.entity.Memom;

@SpringBootTest
public class MemomService {
	@Autowired
	private MemoService service;
	
	@Test
	public void testRegister() {
		MemomDTO memomdto = MemomDTO.builder()
									.title("테스트 제목")
									.content("테스트 내용")
									.writer("테스트 사용자")
									.build();
		System.out.println(memomdto);
		System.out.println("dto"+service.register(memomdto));
	     
	}
	
	@Test
	public void testList() {
		PageRequestDTO pageRequestDTO = PageRequestDTO.builder().page(1).size(10).build();
		PageResultDTO<MemomDTO, Memom> resultDTO = service.getList(pageRequestDTO);
		
		
		System.out.println("PREV : "+resultDTO.isPrev());
		System.out.println("NEXT : "+resultDTO.isNext());
		System.out.println("TOTAL : "+resultDTO.getTotalPage());
		System.out.println("------------------------------------------");
		
		
		for(MemomDTO memomDTO : resultDTO.getDtolist()) {
			System.out.println(memomDTO);
		}
		System.out.println("=========================================");
		resultDTO.getPageList().forEach(i->System.out.println(i));
	}
	
	@Test
	public void testSearch() {
		PageRequestDTO pageRequestDTO=PageRequestDTO.builder()
				.page(1)
				.size(10)
				.type("tcw")
				.keyword("테스트")
				.build();
		PageResultDTO<MemomDTO, Memom> resultDTO=service.getList(pageRequestDTO);
		
		System.out.println("PREV : "+resultDTO.isPrev());
		System.out.println("NEXT : "+resultDTO.isNext());
		System.out.println("TOTAL : "+resultDTO.getTotalPage());
		
		System.out.println("----------------------");
		for(MemomDTO dto : resultDTO.getDtolist()) {
			System.out.println("DTO : "+dto);
		}			
		System.out.println("======================");
		resultDTO.getPageList().forEach(i->System.out.println(i));
	}
}
