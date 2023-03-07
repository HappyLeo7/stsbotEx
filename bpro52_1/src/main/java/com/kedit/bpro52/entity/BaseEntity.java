package com.kedit.bpro52.entity;

import java.time.LocalDateTime;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;

//클래스를 찾을수 있게 매핑해준다.
@MappedSuperclass

@EntityListeners(value= {AuditingEntityListener.class})
@Getter
abstract class BaseEntity {

	
	@CreatedDate
	@Column(name="regdate", updatable=false )
	protected LocalDateTime regDate;
	
	@LastModifiedDate
	@Column(name="updatedate")
	protected LocalDateTime updateDate;
}
