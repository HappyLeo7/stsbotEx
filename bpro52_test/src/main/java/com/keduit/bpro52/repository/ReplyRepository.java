package com.keduit.bpro52.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.keduit.bpro52.entity.Reply;

public interface ReplyRepository extends JpaRepository<Reply, Long> {

	@Modifying
	//삭제
	@Query("delete from Reply r where r.board.bno = :bno")
	void deleteByBno(Long bno) ;

		
	
}
