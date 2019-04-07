package com.example.easynotes.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.easynotes.model.CopyObjects;
import com.example.easynotes.model.Resource;

@Repository
public interface CopyObjectRepository extends JpaRepository<CopyObjects, Long> {

	@Query("select r from Resource r where r.id=:id")
	Resource findBy(@Param("id") String id);
	@Query("select r from CopyObjects r where r.expirationTime <=:date")
	List<CopyObjects> findByCurrentTime(@Param("date") Date date);
	@Query("select r from CopyObjects r where r.fileName in (:fileName)")
	List<CopyObjects> findAll(@Param("fileName") List<String> resources);

}
