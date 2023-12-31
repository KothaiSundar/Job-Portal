package com.example.employeePortal.entity;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Getter;
import lombok.Setter;
@Entity
@Getter
@Setter
public class JobApplication
{@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
private long jobApplicationId;
private LocalDateTime jobApplicationDateTime;

@ManyToOne
@JoinColumn
private Job job;

@ManyToOne
@JoinColumn
private Applicant applicant;


}
