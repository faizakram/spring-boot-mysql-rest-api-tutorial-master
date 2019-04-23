package com.example.easynotes.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.easynotes.model.Hierarchical;

@Repository
public interface HierarchalRepository extends JpaRepository<Hierarchical, Long> {

	

}
