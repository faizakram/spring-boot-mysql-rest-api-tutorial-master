package com.example.easynotes.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.easynotes.model.CopyObjects;


@Repository
public interface CopyObjectRepository extends JpaRepository<CopyObjects, Long> {

}
