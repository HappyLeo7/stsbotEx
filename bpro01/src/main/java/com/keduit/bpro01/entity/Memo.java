package com.keduit.bpro01.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

//import javax.persistence 
//Entity,Table 에노테이션 추가
@Entity //제이피에서 엔티피객체 이걸 추가함으로서 테이블을 추가할수있다.
@Table(name="tbl_memo") //테이블에 이름을주면 사용이가능

//import lombok으로 ToString 에노테이션 추가
@ToString

//
@Builder
@Data
//
@AllArgsConstructor

//
@NoArgsConstructor

//
@Getter
public class Memo {
	
	/* 명령어 : create table tbl_memo (
	mno number(19,0) not null,
    primary key (mno)
	)
	 */
	@Id //테이블의 프라이머리키 생성
	@GeneratedValue(strategy = GenerationType.AUTO) //프라이머리키가 시퀀스일 경우 시퀀스 자동생성을해준다
	private Long mno;
	
	/*명령어 : alter table tbl_memo 
	  			add memo_text varchar2(200 char) not null
	 */
	//컬럼 추가
	@Column(length=200, nullable=false) //칼럼 추가하는 에노테이션null은 불가능해
	private String memoText;
	
	/*
	명령어 : alter table tbl_memo 
			add reg_date date default sysdate not null
	*/
	//컬럼 추가
	@Column(nullable=false, columnDefinition="date default sysdate", insertable=false, updatable=false)
	//유형?
	@Temporal(TemporalType.TIMESTAMP)
	private Date regDate = new Date();
	
	
	
}
