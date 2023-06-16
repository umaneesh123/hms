package com.sillyproject.roomservice.model;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.Version;

import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@MappedSuperclass
public class Base {	
	
	@Id	
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;	
	
	@CreatedBy
	@Column(name = "created_by")
	private String createdBy;
	
	@CreatedDate
	@Column(name = "created_date")
	private LocalDateTime createdDate;
	
	@LastModifiedBy
	@Column(name = "last_modified_by")
	private String lastModifiedBy;
	
	@LastModifiedDate 
	@Column(name = "last_modified_date")
	private LocalDateTime lastModifiedDate;
	
	@Version
	private Integer version;
}
