package com.klef.jfsd.springboot.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.klef.jfsd.springboot.model.Media;
import com.klef.jfsd.springboot.model.Project;


@Repository
public interface MediaRepository extends JpaRepository<Media, Integer> {
	
	@Query("select m from Media m where m.project.projectId = ?1")
	List<Media> findByProjectId(int pid);
	
	
	


}
