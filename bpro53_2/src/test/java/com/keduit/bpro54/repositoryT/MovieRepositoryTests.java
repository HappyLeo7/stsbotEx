package com.keduit.bpro54.repositoryT;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.stream.IntStream;

import javax.transaction.Transactional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.test.annotation.Commit;

import com.keduit.bpro54.entity.Movie;
import com.keduit.bpro54.entity.MovieImage;
import com.keduit.bpro54.repository.MovieImageRepository;
import com.keduit.bpro54.repository.MovieRepository;

@SpringBootTest
public class MovieRepositoryTests {
	
	@Autowired
	private MovieRepository movieRepository;
	
	@Autowired
	private MovieImageRepository movieImageRepository;
	
	@Test
	@Transactional
	@Commit
	public void insertMovies() { //TODO 이미지 등록테스트
		IntStream.range(1, 100).forEach(i->{
			
		Movie movie = Movie.builder().title("Movie....").build();
		
		System.out.println("--------save"+i+"---------");
		movieRepository.save(movie);
		System.out.println("--------movie : "+movie+"----");
		System.out.println("--------/save---------");
		
		int count = (int)(Math.random()*5)+1;
		System.out.println("---count : "+count+"------");
		for(int j=0; j<count; j++) {
			MovieImage movieImage = MovieImage.builder()
					.uuid(UUID.randomUUID().toString()) //??
					.movie(movie)
					.imgName("test이미지"+j+".jpg")
					.build();
			
			movieImageRepository.save(movieImage);
		}
		});
	}
	//
	
	
	@Test
	public void testListPage() {
		//PageRequest 인포트함
		PageRequest pageRequest = PageRequest.of(0, 10, Sort.by(Sort.Direction.DESC, "mno"));
		
		Page<Object[]> result =movieRepository.getListPage(pageRequest);
		
		System.out.println("[한 페이지에 해당하는 내용]");
		for(Object[] objects : result.getContent()) {
			System.out.println("한 페이지에 해당하는 내용 : "+Arrays.toString(objects));
		}
	}
	
	//리뷰를 가져오기
	@Test
	public void testGetMovieWithAll() {
		List<Object[]> result = movieRepository.getMovieWithAll(3L);
		
		System.out.println("result : "+result);
		for(Object[] arr : result) {
			System.out.println(Arrays.toString(arr));
		}
	}
}
