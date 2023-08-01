package com.example.employeePortal.dao;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.example.employeePortal.entity.Skill;
import com.example.employeePortal.repo.ApplicantRepo;
import com.example.employeePortal.repo.SkillRepo;
@Repository
public class SkillDao {
	@Autowired
	private SkillRepo skillRepo;
	
	public Skill saveSkill(Skill skill )
	{
		return skillRepo.save(skill);
	}
	
	
	public Skill getSkillByName(String skillName) 
	{
		
	Optional<Skill> optional=skillRepo.getSkillByName(skillName);
	if(optional.isEmpty()) {
		return null;
	}
	else
	{
		return optional.get();
	}
	}


	public Skill getSkillById(long skillId) {
		Optional<Skill> optional=skillRepo.findById(skillId);
		if(optional.isEmpty()) {
			return null;
		}
		else
		{
			return optional.get();
		}
	}
}
