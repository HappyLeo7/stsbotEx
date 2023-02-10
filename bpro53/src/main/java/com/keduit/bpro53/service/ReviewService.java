package com.keduit.bpro53.service;

import java.util.List;

import com.keduit.bpro53.dto.ReviewDTO;
import com.keduit.bpro53.entity.Member;
import com.keduit.bpro53.entity.Movie;
import com.keduit.bpro53.entity.Review;

public interface ReviewService {
	
	List<ReviewDTO> getListOfMovie(Long mno);
	
	Long register(ReviewDTO reviewDTO);
	
	//변경하는거기때문에 리턴없다
	void modify(ReviewDTO reviewDTO);
	
	void remove(Long reviewNum);
	
	//기본 컬럼과 관련 사용자가 입력한 값을 데이터 베이스로 보내는것
	default Review dtoToEntity(ReviewDTO reviewDTO) {
		//Review에는 컬럼이있다
		Review review= Review.builder()
				.reviewnum(reviewDTO.getReviewnum())
				.movie( //뮤비를 가져온다 FK
						Movie.builder()
						.mno(reviewDTO.getMno())
						.build())
				.member( //멤버를 가져온다 FK
						Member.builder()
						.mid(reviewDTO.getMid())
						.build()
						)
				.grade(reviewDTO.getGrade())
				.text(reviewDTO.getText())
				.build();
		
		return review;
		
	}
	
	// 데이터 베이스에서 사용자에게 보내주는것
	default ReviewDTO entityToDTO(Review review){
		ReviewDTO reviewDTO=ReviewDTO.builder()
				.reviewnum(review.getReviewnum())
				.mno(review.getMovie().getMno())
				.mid(review.getMember().getMid())
				.nickname(review.getMember().getNickname())
				.email(review.getMember().getEmail())
				.grade(review.getGrade())
				.text(review.getText())
				.regDate(review.getRegDate())
				.updateDate(review.getUpdateDate())
				.build();
		
		return reviewDTO;
		
	}
}
