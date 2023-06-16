package com.sillyproject.userservice.model;

import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "users")
public class User extends Base {
	
	@Column(name = "first_name", nullable = false)
	private String firstName;	
	
	@Column(name = "last_name" , nullable = false)
	private String lastName;	
	
	@Column(name = "user_name", nullable = false, unique = true)
	private String userName;
	
	@Column(nullable = false)
	private String password;	
	
	@ManyToMany
	@JoinTable(
			name = "users_roles",
			joinColumns = @JoinColumn( name = "user_id", referencedColumnName = "id"),
			inverseJoinColumns = @JoinColumn( name = "role_id", referencedColumnName = "id")
			)
	private Collection<Role> roles;		
}
