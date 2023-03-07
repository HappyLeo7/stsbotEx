package com.keduit.bpro54.repositoryT;

import java.util.List;
import java.util.stream.IntStream;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.keduit.bpro54.entity.Member;
import com.keduit.bpro54.entity.Movie;
import com.keduit.bpro54.entity.Review;
import com.keduit.bpro54.repository.ReviewRepository;

@SpringBootTest
public class ReviewRepositoryTests {

	@Autowired
	private ReviewRepository reviewRepository;
	
	@Test
	public void insertReviews() {
		//200개 리뷰 등록
		IntStream.range(1, 200).forEach(i->{
			System.out.println("=====>i : "+i+"번"+"<=====");
			//영화번호
			Long mno =(long)(Math.random()*100)+1;
			System.out.println("=====>mno :  "+mno+"번"+"<=====");
			
			//리뷰어 번호
			Long mid = ((long)(Math.random()*100)+1);
			System.out.println("=====>mid : "+mid+"번"+"<=====");
			Member member = Member.builder()
									.mid(mid)
									.build();
			
			Review movieReview = Review.builder()
									.member(member)
									.movie(Movie.builder().mno(mno).build())
									.grade((int)(Math.random()*5)+1)
									.text("이 영화에 대한 느낌은 ..."+i)
									.build();
			System.out.println("=====>"+movieReview+"<=====");
			
			reviewRepository.save(movieReview);
		});
	}
	
	
	//TODO 리뷰 불러오기 테스트
	@Test
	public void testGetMovieReviews() {
		Movie movie =Movie.builder()
				.mno(4L)
				.build();
		
		List<Review> result =reviewRepository.findByMovie(movie);
		result.forEach(i->{
			System.out.println(i);
			System.out.println("========================");
			System.out.print("Reviewnum : "+i.getReviewnum());
			System.out.print("  //  ");
			
			System.out.print("Grade : "+ i.getGrade());
			System.out.print("  //  ");
			System.out.print("내용 : "+i.getText());
			System.out.print("  //  ");
			System.out.println("회원 메일주소 : "+i.getMember().getEmail());
			System.out.println("========================");
			
		});
		
	}
}
