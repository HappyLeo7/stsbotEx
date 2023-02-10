package com.keduit.bpro53.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.keduit.bpro53.dto.MovieDTO;
import com.keduit.bpro53.dto.PageRequestDTO;
import com.keduit.bpro53.dto.PageResultDTO;
import com.keduit.bpro53.entity.Movie;
import com.keduit.bpro53.entity.MovieImage;
import com.keduit.bpro53.repository.MovieImageRepository;
import com.keduit.bpro53.repository.MovieRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Service
@Log4j2
@RequiredArgsConstructor //생성자 주입
public class MovieServiceImpl implements MovieService {

	@Autowired
	private final MovieRepository movieRepository;
	private final MovieImageRepository movieImageRepository;
	
	@Override
	public Long register(MovieDTO movieDTO) {

		Map<String, Object> entityMap = dtoToEntity(movieDTO);
		Movie movie = (Movie) entityMap.get("movie");
		List<MovieImage> movieImageList =(List<MovieImage>)entityMap.get("imgList"); //값을 형변해줘야한다.
		
		movieRepository.save(movie);
		
		
		//for 문으로 리스트 뽑아서 저장한다.
		movieImageList.forEach(movieImage->{
			movieImageRepository.save(movieImage);
		});
		
		return movie.getMno();
	}

	
	
	@Override
	public PageResultDTO<MovieDTO, Object[]> getList(PageRequestDTO pageRequestDTO) {

		Pageable pageable = pageRequestDTO.getPageable(Sort.by("mno").descending());
		Page<Object[]> result = movieRepository.getListPage(pageable);
		log.info("==============영화서비스Impl======================");
		result.getContent().forEach(arr->{
			log.info(Arrays.toString(arr));
		});
		
		
		Function<Object[], MovieDTO> fn=(arr-> entitiesToDTO(
			(Movie)arr[0],
			(List<MovieImage>)(Arrays.asList((MovieImage)arr[1])),
				(Double) arr[2],
				(Long)arr[3])
		);
		
		return new PageResultDTO<>(result, fn);
	}

	@Override
	public MovieDTO getMovie(Long mno) {
		//System.out.println("===점검1===");
		List<Object[]> result = movieRepository.getMovieWithAll(mno);
		//System.out.println("===점검2===");
		
		Movie movie = (Movie) result.get(0)[0]; // 영화의 대표 이미지만 불러오면 서브 이미지도 불러올수 있게 첫이미지만 불러오는걸 의미
		//System.out.println("===점검3===");
		
		List<MovieImage> movieImageList = new ArrayList<>(); //이미지를 처리하기위한것 List
		//System.out.println("===점검4===");
		
		result.forEach(arr ->{
			MovieImage movieImage = (MovieImage)arr[1];
			movieImageList.add(movieImage);
			
			
		});
		//System.out.println("===점검5===");
		
		Double avg= (Double)result.get(0)[2];
		//System.out.println("===점검6===");
		Long reviewCnt= (Long)result.get(0)[3];
		//System.out.println("===점검7===");
		
		//서비스에 있는 entitiesToDTO 메서드
		return entitiesToDTO(movie, movieImageList, avg, reviewCnt);
	}
	
}
