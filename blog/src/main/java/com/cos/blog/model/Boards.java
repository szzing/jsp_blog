package com.cos.blog.model;

import java.sql.Timestamp;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Table(name="boards")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@SequenceGenerator(
		name = "USER_SEQ_GENERATOR2"
		, sequenceName = "USER_SEQ2"
		, initialValue = 1
		, allocationSize = 1
	)
public class Boards {
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="USER_SEQ_GENERATOR2")
	private int id;
	
	@Column(nullable=false, length=100)
	private String title;
	
	@Lob
	private String content;
	
//	@ColumnDefault("0")
	private int count;
	
	@ManyToOne(fetch = FetchType.EAGER)
	//Many=Board, User = One 한명이 여러개의 글을 쓸 수 있다.
	@JoinColumn(name="userId")
	private Users users; // 자바는 오브젝트를 저장할 수 있지만
						// DB는 오브젝트를 저장할 수 없어서 외래키를 사용한다. 
	
	@OneToMany (mappedBy="boards", fetch = FetchType.EAGER)
	//mappedBy는 연관관계의 주인이 아니라, DB에 컬럼을 만들지 않는다는 의미
	//하나의 게시글에는 여러개 댓글 작성
	private List<Reply> reply;
	
	@CreationTimestamp
	private Timestamp createDate;
}
