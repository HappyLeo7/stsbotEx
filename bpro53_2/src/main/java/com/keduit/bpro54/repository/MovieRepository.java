package com.keduit.bpro54.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.keduit.bpro54.entity.Movie;

public interface MovieRepository extends JpaRepository<Movie, Long>{ // <컬럼클래스타입, ID타입>
	//TODO * 리뷰와 영화 연결을 위한 커리문 중요!!
	//평균을 계산
	
	/*//테스트안
	 * @Query("select m, avg(coalesce(r.grade,0)), count(distinct r) from Movie m " 
			+ "left outer join Review r on r.movie = m group by m")*/
	/*//1안
	 * @Query("select m, max(mi), avg(coalesce(r.grade,0)), count(distinct r) from Movie m "
	 * +" left outer join MovieImage mi on mi.movie = m "
	 * +" left outer join Review r on r.movie = m group by m")
	 */
	
	//2안
	@Query("select m, mi, avg(coalesce(r.grade,0)), count(distinct r) from Movie m "
			+" left outer join MovieImage mi on mi.movie = m "
			+" left outer join Review r on r.movie = m group by m")
	Page<Object[]> getListPage(Pageable pageable);
	
	
	//////////////////////////////////////////////////////////
	
	//1개만 불러온다
	/*
	 * @Query("select m, mi " +
	 * " from Movie m left outer join MovieImage mi on mi.movie = m " +
	 * " where m.mno = :mno ")
	 */
	@Query("select m, mi, avg(coalesce(r.grade,0)), count(distinct r) "
			+ " from Movie m left outer join MovieImage mi on mi.movie = m "
			+ " left outer join Review r on r.movie = m "
			+ " where m.mno = :mno group by mi")
	List<Object[]> getMovieWithAll(Long mno);
	
	
	
	
}
