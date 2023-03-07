package com.keduit.bpro53;

import java.util.stream.IntStream;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.keduit.bpro53.entity.Board;
import com.keduit.bpro53.entity.Reply;
import com.keduit.bpro53.repository.ReplyRepository;

@SpringBootTest
public class ReplyRepositoryTests {

	@Autowired
	//인터페이스
	private ReplyRepository replyRepository;
	
	@Test
	public void insertReplyTest() {
		IntStream.range(1, 300).forEach(i->{
			//무작이 번호 선정
			long bno = (long)(Math.random()*100)+1;
			Board board = Board.builder().bno(bno).build();
			
			//댓글작성
			Reply reply =Reply.builder()
					.text("댓글"+i)
					.board(board)
					.replyer("guest"+i)
					.build();
			replyRepository.save(reply);
		});
		
	}
}
