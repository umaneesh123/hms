package com.sillyproject.concernservice.model;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Version;

import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "concerns")
@EntityListeners(AuditingEntityListener.class)
public class Concern {
	
	@Id	
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;	
	
	private String subject;
	
	private String message;
	
	@Column(name = "hostel_name")
	private String hostelName;
	
	@Column(name = "room_no")
	private int roomNo;
	
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
