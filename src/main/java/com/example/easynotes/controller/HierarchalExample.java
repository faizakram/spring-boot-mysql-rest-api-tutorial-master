package com.example.easynotes.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.easynotes.model.UserHe;
import com.example.easynotes.repository.UserHeRepository;
import com.example.easynotes.utils.Users;

@RestController
public class HierarchalExample {

	@Autowired
	private UserHeRepository userHeRepository;

	@PostMapping("/hierarchy-user")
	public Map<String, Object> addUsers(@RequestBody Users users) {
		UserHe userhe = new UserHe();
		userhe.setImg(users.getImg());
		userhe.setDesignation(users.getDesignation());
		userhe.setName(users.getName());
		if (users.getParentId() != null)
			userhe.setUser(userHeRepository.getOne(users.getParentId()));
		userHeRepository.save(userhe);
		Map<String, Object> map = new HashMap<>();
		map.put("Added Successfully", userhe.getId());
		return map;
	}

	@GetMapping("/hierarchys-user")
	public Map<String, Object> getHierarchy() {
		List<UserHe> hierarchicals = userHeRepository.findAll();
		Map<String, Object> map = new HashMap<>();
		map.put("users", hierarchicals);
		return map;
	}

	/*
	 * @PutMapping("/hierarchy/{id}") public Map<String, Object>
	 * updateHierarchy(@PathVariable Long id, @RequestParam(required = false) Long
	 * parentId,
	 * 
	 * @RequestParam String name) { Map<String, Object> map = new HashMap<>();
	 * Optional<Hierarchical> hierarchical = hierarchalRepository.findById(id); if
	 * (hierarchical.isPresent()) { hierarchical.get().setName(name); if (parentId
	 * != null) { Optional<Hierarchical> hierarchicalOptional =
	 * hierarchalRepository.findById(parentId); if
	 * (hierarchicalOptional.isPresent()) {
	 * hierarchical.get().setHierarchical(hierarchicalOptional.get()); } else {
	 * hierarchical.get().setHierarchical(null); } } else {
	 * hierarchical.get().setHierarchical(null); }
	 * hierarchalRepository.save(hierarchical.get()); } else { map.put("Error",
	 * "No Data Found"); } map.put("Updated Successfully",
	 * hierarchical.get().getId()); return map; }
	 * 
	 * @GetMapping("/hierarchy") public Map<String, Object>
	 * getHierarchy(@RequestParam Long id) { Optional<Hierarchical>
	 * hierarchicalOptional = hierarchalRepository.findById(id); Map<String, Object>
	 * map = new HashMap<>(); map.put("hierarchy", hierarchicalOptional.get());
	 * return map; }
	 * 
	 * @GetMapping("/hierarchys") public Map<String, Object> getHierarchy() {
	 * List<Hierarchical> hierarchicals = hierarchalRepository.findAll();
	 * Map<String, Object> map = new HashMap<>(); map.put("hierarchys",
	 * hierarchicals); return map; }
	 */

}
