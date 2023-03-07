package com.keduit.bpro53.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Table(name="board")
@ToString(exclude="writer")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
//[추가]BaseEntity상속받는다
public class Board extends BaseEntity{
	@Id
	@GeneratedValue(strategy =GenerationType.IDENTITY)
	private Long bno;
	
	//[컬럼추가] 제목
	@Column(name="title", length = 200, nullable = false)
	private String title;
	
	//[컬럼추가] 내용
	@Column(name = "content", length = 2000, nullable = false)
	private String content;
	
	//[컬럼추가] 사용자
	//@Column(name="writer", length = 50, nullable = false)
	@ManyToOne(fetch=FetchType.LAZY)
	private Member writer;
	
	public void change(String title, String content) {
		this.title =title;
		this.content =content;
		
	}
}
