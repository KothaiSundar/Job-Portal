package com.example.employeePortal.entity;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Job {
@Id
@GeneratedValue(strategy= GenerationType.IDENTITY)
	private long jobId;
	private String jobTitle;
	private String jobDescription;
	private String jobCompany;
	private long jobSalary;
	private LocalDateTime jobCreateDateTime;
	
	@ManyToMany
	@JoinTable
	private List<Skill> skills;
	
	
	@OneToMany(mappedBy="job")
	@JsonIgnore
	private List<JobApplication> jobApplications;
	
	@ManyToOne
	@JoinColumn
	//@JsonIgnore//when getting employer, in job --employer not to be printed
	private Employer employer;
}
//serializer issue: while creating job,it returning job that job has employer in it
//alreay employer has job
//so it is happening stack overflow...that is getting looped..
//whenever if we send job obj, we should get emp obj
//so when we come to employer entity, we should not again fetch job