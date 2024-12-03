package com.klef.jfsd.springboot.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.klef.jfsd.springboot.model.ProjectFeedback;

@Repository
public interface ProjectFeedbackRepository extends JpaRepository<ProjectFeedback, Integer>
{
	List<ProjectFeedback> findByFacultyId(int facultyId);
	List<ProjectFeedback> findByProjectid(int projectid);

}
