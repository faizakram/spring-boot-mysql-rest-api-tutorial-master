package com.example.easynotes.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.easynotes.model.HierarchalUser;

public interface UserHierarchalRepository extends JpaRepository<HierarchalUser, Long>{

}
