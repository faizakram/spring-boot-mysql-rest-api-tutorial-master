package com.example.easynotes.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.easynotes.model.TxnDocument;

@Repository
public interface TxnDocumentRepository extends JpaRepository<TxnDocument, Long> {

	@Query("select r from TxnDocument r where r.doctypeid=:id")
	List<TxnDocument> findBy(@Param("id") Integer id);
	
	@Query("select r from TxnDocument r where r.docreferid=:id")
	TxnDocument getById(Integer id);


}
