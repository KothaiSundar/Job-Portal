package com.example.employeePortal.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@SuppressWarnings("serial")
@Getter
@AllArgsConstructor
public class SkillNotFoundByIdException extends RuntimeException {
	private String message;
	}


