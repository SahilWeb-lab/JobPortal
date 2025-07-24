package com.project.model;

import java.time.LocalDateTime;

import com.project.enums.Role;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "users")
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String fullName;
	
	private String email;
	
	private String password;
	
	private Role role; 
	
	private Boolean enabled = true;
	
	private LocalDateTime createdAt;
	

    @PrePersist
    protected void onCreate() {
        this.createdAt = LocalDateTime.now();
    }
	
}
