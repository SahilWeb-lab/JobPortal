package com.project.dto;

import java.time.LocalDateTime;

import com.project.enums.Role;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UserResponse {

	private Long id;

	private String fullName;

	private String email;

	private Role role;

	private Boolean enabled;

	private LocalDateTime createdAt;

}
