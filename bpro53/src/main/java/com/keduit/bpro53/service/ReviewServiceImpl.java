package com.keduit.bpro53.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.keduit.bpro53.dto.ReviewDTO;
import com.keduit.bpro53.entity.Movie;
import com.keduit.bpro53.entity.Review;
import com.keduit.bpro53.repository.ReviewRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;


@Service
@Log4j2
@RequiredArgsConstructor
public class ReviewServiceImpl implements ReviewService {

	@Autowired
	private final ReviewRepository reviewRepository;
	
	
	@Override
	public List<ReviewDTO> getListOfMovie(Long mno) {
		Movie movie = Movie.builder()
				.mno(mno)
				.build();
		
		List<Review> result = reviewRepository.findByMovie(movie);
		
		//TODO entityToDTO는 데이터를 사용자에게 보내주는것
		return result.stream().map(movieReview -> entityToDTO(movieReview)).collect(Collectors.toList());
	}

	@Override
	public Long register(ReviewDTO reviewDTO) {
		//TODO 사용자가 입력한 것을 데이터베이스로 저장한다.
		Review review = dtoToEntity(reviewDTO);
		reviewRepository.save(review);
		
		//저장된 리뷰 번호를 리턴해줘
		return review.getReviewnum();
	}

	@Override
	public void modify(ReviewDTO reviewDTO) {

		Optional<Review> result= reviewRepository.findById(reviewDTO.getReviewnum());
		
		if(result.isPresent()) {
			Review review=result.get();
			//웹에서 보내준 값들을 넣는다.
			review.changeGrade(reviewDTO.getGrade());
			review.changeText(reviewDTO.getText());;
			
			//데이터 베이스에 저장
			reviewRepository.save(review);
			
		}
		
	}

	@Override
	public void remove(Long reviewNum) {
		
		//웹에서 입력한 번호의 데이터 베이스에서 삭제해준다.
		reviewRepository.deleteById(reviewNum);
	}

}
