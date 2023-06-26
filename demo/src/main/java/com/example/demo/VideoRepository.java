package com.example.demo;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Repository;

@Repository
public interface VideoRepository extends JpaRepository<VideoEntity, Long>{
	List<VideoEntity> findAll();
	
	List<VideoEntity> findByNameContainsIgnoreCase(String partialName);

	List<VideoEntity> findByDescriptionContainsIgnoreCase(String partialDescription);

	List<VideoEntity> findByNameContainsOrDescriptionContainsAllIgnoreCase(String partialName,
	    String partialDescription);
	
	@PreAuthorize("#entity.username == authentication.name")
	@Override
	void delete (VideoEntity entity);
}