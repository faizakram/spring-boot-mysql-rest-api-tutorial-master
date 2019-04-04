package com.example.easynotes.repository;

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

}
