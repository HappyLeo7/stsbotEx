package com.keduit.bpro52.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.keduit.bpro52.dto.BoardDTO;
import com.keduit.bpro52.dto.PageRequestDTO;
import com.keduit.bpro52.dto.PageResultDTO;

@SpringBootTest
public class BoardServiceTests {

	@Autowired
	private BoardService boardService;
	
	@Test
	public void testRegister() {
		BoardDTO dto = BoardDTO.builder()
				.title("테스트 제목")
				.content("테스트 내용")
				.writerEmail("user50@abc.com")
				.build();
		
		Long bno = boardService.register(dto);
		
		System.out.println("|||||||||||||||||||||||||||");
		System.out.println(bno+" : "+dto);
		System.out.println("|||||||||||||||||||||||||||");
	}
	
	@Test
	public void testList() {
		PageRequestDTO pageRequestDTO = new PageRequestDTO();
		
		PageResultDTO<BoardDTO, Object[]> result= boardService.getList(pageRequestDTO);
		for(BoardDTO boardDTO : result.getDtoList()) {
			System.out.println("boardDTO" + boardDTO);
			
		}}
		
		@Test
		public void testGet() {
			Long bno = 97L;
			BoardDTO boardDTO = boardService.get(bno);
			System.out.println("testget....."+boardDTO);
		}
		
		@Test
		public void testDelete() {
			Long bno = 99L;
			boardService.removeWithReplies(bno);
		}
		
		@Test
		public void testModify() {
			BoardDTO boardDTO = BoardDTO.builder()
					.bno(2L)
					.title("제목 수정 테스트중")
					.content("내용 수정 테스트중")
					.build();
			boardService.modify(boardDTO);
		}
}
