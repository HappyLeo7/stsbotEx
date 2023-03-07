package com.keduit.bpro53;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.IntStream;

import javax.transaction.Transactional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import com.keduit.bpro53.entity.Board;
import com.keduit.bpro53.entity.Member;
import com.keduit.bpro53.entity.Reply;
import com.keduit.bpro53.repository.BoardRepository;
import com.keduit.bpro53.repository.ReplyRepository;

@SpringBootTest
public class BoardRepositoryTests {

	
	@Autowired
	private BoardRepository boardRepository;
	private ReplyRepository replyRepository;
	
	@Test
	public void insertBoard() {
		IntStream.rangeClosed(1, 100).forEach(i->{
			Member member = Member.builder()
					.email("user"+i+"@abc.com")
					.build();
			
			Board board = Board.builder()
						.title("테스트 제목"+i)
						.content("테스트 내용"+i)
						.writer(member)
						.build();
			boardRepository.save(board);
		});
	}
	
	
	@Transactional
	@Test
	public void testRead() {
		//null 이셉션안내려고, 100L 꺼내온다
		Optional<Board> result=boardRepository.findById(100L);
		
		//읽어올곳
		Board board = result.get();
		System.out.println("board : "+board);
		System.out.println("board.getWriter : "+board.getWriter());
	}
	
	@Test
	public void readReply(){
		Optional<Reply> result = replyRepository.findById(1L);
		Reply reply = result.get();
		System.out.println("board : "+reply);
		System.out.println("board.getWriter : "+reply.getBoard());
	}
	
	
	//읽어오는것
	@Test
	public void testReadWithWriter() {
		Object result = boardRepository.getBoardWithWriter(100L);
		Object[] arr=(Object[])result;
		System.out.println("---------------------");
		System.out.println(Arrays.toString(arr));
		System.out.println("---------------------");
	}
	
	@Test
	public void testGetBoardWithReply() {
		List<Object[]> result = boardRepository.getBoardWithReply(10L);
		for(Object[] arr : result) {
			System.out.println(Arrays.toString(arr));
			System.out.println(arr);
		}
	}
	
	@Test
	public void testWithReplyCount() {
		Pageable pageable = PageRequest.of(0, 10, Sort.by("bno").descending());
		
		Page<Object[]> result = boardRepository.getBoardWithReplyCount(pageable);
		
		result.get().forEach(row->{
			Object[] arr = row;
			System.out.println(Arrays.toString(arr));
		}
				);
		
	}
	
	@Test
	public void testReadOne() {
		Object result = boardRepository.getBoardByBno(40L);
		Object[] arr = (Object[])result;
		System.out.println(Arrays.toString(arr));
	}
	
}
