package com.example.easynotes.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.easynotes.model.CFGApplication;

@Repository
public interface ApplicationRepository extends JpaRepository<CFGApplication, Long> {


}
