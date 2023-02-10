package com.keduit.bpro53.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.keduit.bpro53.dto.MovieDTO;
import com.keduit.bpro53.dto.MovieImageDTO;
import com.keduit.bpro53.dto.PageRequestDTO;
import com.keduit.bpro53.dto.PageResultDTO;
import com.keduit.bpro53.entity.Movie;
import com.keduit.bpro53.entity.MovieImage;

public interface MovieService {

	Long register(MovieDTO movieDTO);

	
	//TODO getList
	PageResultDTO<MovieDTO, Object[]> getList(PageRequestDTO pageRequestDTO);
	
	//TODO MovieService getMovie
	MovieDTO getMovie(Long mno);

	default Map<String, Object> dtoToEntity(MovieDTO movieDTO) {
		Map<String, Object> entityMap = new HashMap<>();
		
		
		//영화 번호, 제목을 받아와서 db에 넣는다.
		Movie movie = Movie.builder()
				.mno(movieDTO.getMno())
				.title(movieDTO.getTitle())
				.build();
		
		entityMap.put("movie", movie);
		
		List<MovieImageDTO> imageDTOList = movieDTO.getImageDTOList();

		if (imageDTOList != null && imageDTOList.size() > 0) {
			List<MovieImage> movieImageDTOList = imageDTOList.stream().map(movieImageDTO -> {

				MovieImage movieImage = MovieImage.builder()
						.path(movieImageDTO.getPath())
						.img_name(movieImageDTO.getImg_name())
						.uuid(movieImageDTO.getUuid())
						.movie(movie)
						.build();
				return movieImage;
			}).collect(Collectors.toList());

			entityMap.put("imgList", movieImageDTOList);
		}
		return entityMap;

	}

	default MovieDTO entitiesToDTO(Movie movie, List<MovieImage> moiveImages, Double avg, Long reviewCnt) {

		MovieDTO movieDTO = MovieDTO.builder().mno(movie.getMno()).title(movie.getTitle()).regDate(movie.getRegDate())
				.updateDate(movie.getUpdateDate()).build();

		List<MovieImageDTO> movieImageDTOList = moiveImages.stream().map(movieImage -> {
			//movieImage null 널처리
			if(movieImage !=null) {
				
				return MovieImageDTO.builder().img_name(movieImage.getImg_name()).path(movieImage.getPath())
						.uuid(movieImage.getUuid()).build();
			}else {
				return null;
			}
		}).collect(Collectors.toList());

		movieDTO.setImageDTOList(movieImageDTOList);
		movieDTO.setAvg(avg);
		movieDTO.setReviewCnt(reviewCnt.intValue());

		return movieDTO;

	}

}
