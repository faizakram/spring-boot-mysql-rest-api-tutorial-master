package com.example.easynotes.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.easynotes.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

	@Query("select u from User u where u.userName =:userName and u.password=:password")
	User getLogin(@Param("userName") String userName, @Param("password") String password);

}
