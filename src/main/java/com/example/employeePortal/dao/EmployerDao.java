package com.example.employeePortal.dao;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.example.employeePortal.entity.Employer;
import com.example.employeePortal.repo.EmployerRepo;

@Repository
public class EmployerDao {
@Autowired
private EmployerRepo employerRepo;

public Employer addEmployer(Employer employer)
{
	return employerRepo.save(employer);
}

public Employer getEmployer(long employerId) 
{
Optional<Employer> optional= employerRepo.findById(employerId);

if(optional.isEmpty())
{
	return null;
}
else
{
	return optional.get();
}

}



public Employer deleteEmployer(long employerId) 
{
Optional<Employer> optional= employerRepo.findById(employerId);

if(optional.isEmpty())
{
	return null;
}
else
{
	 employerRepo.deleteById(employerId);
	 return optional.get();
}

}
}