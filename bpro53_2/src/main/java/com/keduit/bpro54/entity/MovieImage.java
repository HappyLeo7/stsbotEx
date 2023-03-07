package com.keduit.bpro54.entity;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@ToString(exclude="movie") //TODO fk 달아주기위한
public class MovieImage {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long inum;
	
	private String uuid;
	private String imgName;
	private String path; 
	
	@ManyToOne(fetch = FetchType.LAZY)//TODO LAZY 필요할떄해라 ?
	private Movie movie;
}
