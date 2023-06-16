package com.sillyproject.roomservice.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

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
@Table(name = "rooms")
@EntityListeners(AuditingEntityListener.class)
public class Room extends Base {
	
	@Column(unique = true)
	private Integer roomNo;
	
	@Column(name = "is_vacant")
	private Boolean isVacant;
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "hostel_id", referencedColumnName ="id" )
	private Hostel hostel;
	
	private String username;
}
