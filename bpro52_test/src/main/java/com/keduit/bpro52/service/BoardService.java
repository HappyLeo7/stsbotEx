package com.keduit.bpro52.service;

import org.springframework.data.domain.jaxb.SpringDataJaxb.PageRequestDto;

import com.keduit.bpro52.dto.BoardDTO;
import com.keduit.bpro52.dto.PageRequestDTO;
import com.keduit.bpro52.dto.PageResultDTO;
import com.keduit.bpro52.entity.Board;
import com.keduit.bpro52.entity.Member;

import lombok.extern.java.Log;

public interface BoardService {
	Long register(BoardDTO dto);
	
	
	default Board dtoToEntity(BoardDTO dto) {
		//사용자 이메일을 가지고와서 멤버처리 (타입이 다르기때문에)
		Member member = Member.builder().email(dto.getWriterEmail()).build();
		Board board=Board.builder()
				.bno(dto.getBno())
				.title(dto.getTitle())
				.content(dto.getContent())
				.writer(member)
				.build();
		return board; //널수정하시오
	}
	
	default BoardDTO entitytoDTO(Board board, Member member, Long replyConunt) {
		
		BoardDTO boardDTO = BoardDTO.builder()
				 					.bno(board.getBno())
									.title(board.getTitle())
									.content(board.getContent())
									.regDate(board.getRegDate())
									.UpdateDate(board.getUpdateDate())
									.writerEmail(member.getEmail())
									.writerName(member.getName())
									.build();
		return boardDTO;
	}

	PageResultDTO<BoardDTO, Object[]> getList(PageRequestDTO pageRequestDTO);


	BoardDTO get(Long bno);

	void removeWithReplies(Long bno);
	
	void modify(BoardDTO boardDTO);
}
