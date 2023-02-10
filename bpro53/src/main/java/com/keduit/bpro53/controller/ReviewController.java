package com.keduit.bpro53.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.keduit.bpro53.dto.ReviewDTO;
import com.keduit.bpro53.service.ReviewService;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@RestController
@RequestMapping("/reviews")
@Log4j2
@RequiredArgsConstructor
public class ReviewController {

	private final ReviewService reviewService;
	
	@GetMapping("/{mno}/all")
	//@PathVariable 는 해당 페이지에서 mno를 받아오고 위에 매핑을하기위해서
	public ResponseEntity<List<ReviewDTO>> getList(@PathVariable("mno") Long mno){
		log.info("=========리뷰 컨트롤러 list 페이지 출력========");
		log.info("MNO : "+mno);
		log.info("=========/리뷰 컨트롤러 list 페이지 출력========");
		
		List<ReviewDTO> reviewDTOList = reviewService.getListOfMovie(mno);
		
		return new ResponseEntity<>(reviewDTOList, HttpStatus.OK);
		
	}
	
	//등록처리
	@PostMapping("/{mno}")
	public ResponseEntity<Long> addReview(@RequestBody ReviewDTO reviewDTO) {
		
		log.info("=========리뷰 컨트롤러 addReview 처리 출력========");
		log.info("reviewDTO : "+ reviewDTO);
		log.info("=========/리뷰 컨트롤러 addReview 처리 출력========");
		
		Long reviewnum=reviewService.register(reviewDTO);
		
		return new ResponseEntity<Long>(reviewnum, HttpStatus.OK);
		
	}
	
	//수정
	@PutMapping("/{mno}/{reviewnum}")
	public ResponseEntity<Long> modifyReview(@PathVariable Long reviewnum, @RequestBody ReviewDTO reviewDTO){
		log.info("=========리뷰 컨트롤러 modifyReview 처리 출력========");
		log.info("reviewnum : "+ reviewnum);
		log.info("reviewDTO : "+ reviewDTO);
		log.info("=========/리뷰 컨트롤러 modifyReview 처리 출력========");
		
		reviewService.modify(reviewDTO);
		
		return new ResponseEntity<Long>(reviewnum, HttpStatus.OK);
	}
	
	//삭제
	@DeleteMapping("/{mno}/{reviewnum}")
	public ResponseEntity<Long> removeReview(@PathVariable Long reviewnum){
		log.info("=========리뷰 컨트롤러 removeReview 처리 출력========");
		log.info("reviewnum : "+ reviewnum);
		log.info("=========/리뷰 컨트롤러 removeReview 처리 출력========");
		
		reviewService.remove(reviewnum);
		return new ResponseEntity<Long>(reviewnum, HttpStatus.OK);
	}
}
