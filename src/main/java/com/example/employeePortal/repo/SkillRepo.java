package com.example.employeePortal.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.employeePortal.entity.Skill;

public interface SkillRepo extends JpaRepository<Skill,Long>{
//jpql query
	@Query(value="select s from Skill s where s.skillName=?1")
	public Optional<Skill> getSkillByName(String skillName);



}
