package com.example.easynotes.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.easynotes.model.HierarchalUser;
import com.example.easynotes.repository.HierarchalRepository;
import com.example.easynotes.repository.UserHierarchalRepository;

@RestController
public class UserController {

	@Autowired
	private UserHierarchalRepository userHierarchalRepository;
	@Autowired
	private HierarchalRepository hierarchalRepository;

	@GetMapping("/user")
	public Map<String, Object> getUser(@RequestParam Long id) {
		Map<String, Object> map = new HashMap<>();
		Optional<HierarchalUser> user = userHierarchalRepository.findById(id);
		if (user.isPresent())
			map.put("user", user.get());
		else
			map.put("Error", "User Not Found");
		return map;
	}

	@GetMapping("/users")
	public Map<String, Object> getHierarchy() {
		List<HierarchalUser> hierarchicalUsers = userHierarchalRepository.findAll();
		Map<String, Object> map = new HashMap<>();
		map.put("users", hierarchicalUsers);
		return map;
	}

	@PostMapping("/user")
	public Map<String, Object> addUser(@RequestParam Long hierarchyId, @RequestParam String name) {
		Map<String, Object> map = new HashMap<>();
		HierarchalUser hierarchalUser = new HierarchalUser();
		hierarchalUser.setUserName(name);
		hierarchalUser.setHierarchical(hierarchalRepository.getOne(hierarchyId));
		userHierarchalRepository.save(hierarchalUser);
		map.put("Added Successfully", hierarchalUser.getId());
		return map;
	}

	@PutMapping("/user/{id}")
	public Map<String, Object> updateUser(@PathVariable Long userId, @RequestParam Long hierarchyId,
			@RequestParam String name) {
		Map<String, Object> map = new HashMap<>();
		HierarchalUser hierarchalUser = userHierarchalRepository.getOne(userId);
		if (hierarchalUser != null) {
			hierarchalUser.setUserName(name);
			hierarchalUser.setHierarchical(hierarchalRepository.getOne(hierarchyId));
			userHierarchalRepository.save(hierarchalUser);
			map.put("Updated Successfully", hierarchalUser.getId());
		} else {
			map.put("Error", "User Not Found");
		}
		return map;
	}

}
