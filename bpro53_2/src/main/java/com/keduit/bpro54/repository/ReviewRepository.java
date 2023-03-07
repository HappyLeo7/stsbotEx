package com.keduit.bpro54.repository;

import java.util.List;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.EntityGraph.EntityGraphType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.keduit.bpro54.entity.Member;
import com.keduit.bpro54.entity.Movie;
import com.keduit.bpro54.entity.Review;

public interface ReviewRepository extends JpaRepository<Review, Long>{
	
	//리뷰를 가지고올거다
	@EntityGraph(attributePaths = {"member"}, type=EntityGraphType.FETCH) // 뜻 찾아보기
	List<Review> findByMovie(Movie movie);
	
	
	@Modifying
	@Query("delete from Review mr where mr.member = :member")
	void deleteByMember(Member member);
}
