package com.keduit.bpro52.service;

import java.util.List;

import com.keduit.bpro52.dto.ReplyDTO;
import com.keduit.bpro52.entity.Board;
import com.keduit.bpro52.entity.Reply;

public interface ReplyService {

		Long register(ReplyDTO replyDTO);
		
		List<ReplyDTO> getList(Long bno);
		
		//TODO 댓글 수정 인터페이스 (ReplyService)
		void modify(ReplyDTO replyDTO);
		
		//TODO 댓글 삭제 인터페이스 (ReplyService.java)
		void remove(Long rno);
		
		default Reply dtoToEntity(ReplyDTO replyDTO) {
			Board board = Board.builder().bno(replyDTO.getBno()).build();
			Reply reply = Reply.builder()
									.rno(replyDTO.getRno())
									.text(replyDTO.getText())
									.replyer(replyDTO.getReplyer())
									.board(board)
									.build();
			return reply;
			
		}
		
		default ReplyDTO entityToDTO(Reply reply) {
			ReplyDTO dto = ReplyDTO.builder()
									.rno(reply.getRno())
									.text(reply.getText())
									.replyer(reply.getReplyer())
									.regDate(reply.getRegDate())
									.updateDate(reply.getUpdateDate())
									.build();
			return dto;
		}
		
		
}
