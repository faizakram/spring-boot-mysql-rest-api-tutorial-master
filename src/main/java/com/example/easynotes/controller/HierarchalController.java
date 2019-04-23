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

import com.example.easynotes.model.Hierarchical;
import com.example.easynotes.repository.HierarchalRepository;

@RestController
public class HierarchalController {

	@Autowired
	private HierarchalRepository hierarchalRepository;

	@PostMapping("/hierarchy")
	public Map<String, Object> getNameById(@RequestParam(required = false) Long parentId, @RequestParam String name) {
		Hierarchical hierarchical = new Hierarchical();
		hierarchical.setName(name);
		if (parentId != null) {
			Optional<Hierarchical> hierarchicalOptional = hierarchalRepository.findById(parentId);
			if (hierarchicalOptional.isPresent()) {
				hierarchical.setHierarchical(hierarchicalOptional.get());
			} else {
				hierarchical.setHierarchical(null);
			}
		}
		hierarchalRepository.save(hierarchical);
		Map<String, Object> map = new HashMap<>();
		map.put("Added Successfully", hierarchical.getId());
		return map;
	}

	@PutMapping("/hierarchy/{id}")
	public Map<String, Object> updateHierarchy(@PathVariable Long id, @RequestParam(required = false) Long parentId,
			@RequestParam String name) {
		Map<String, Object> map = new HashMap<>();
		Optional<Hierarchical> hierarchical = hierarchalRepository.findById(id);
		if (hierarchical.isPresent()) {
			hierarchical.get().setName(name);
			if (parentId != null) {
				Optional<Hierarchical> hierarchicalOptional = hierarchalRepository.findById(parentId);
				if (hierarchicalOptional.isPresent()) {
					hierarchical.get().setHierarchical(hierarchicalOptional.get());
				} else {
					hierarchical.get().setHierarchical(null);
				}
			} else {
				hierarchical.get().setHierarchical(null);
			}
			hierarchalRepository.save(hierarchical.get());
		} else {
			map.put("Error", "No Data Found");
		}
		map.put("Updated Successfully", hierarchical.get().getId());
		return map;
	}

	@GetMapping("/hierarchy")
	public Map<String, Object> getHierarchy(@RequestParam Long id) {
		Optional<Hierarchical> hierarchicalOptional = hierarchalRepository.findById(id);
		Map<String, Object> map = new HashMap<>();
		map.put("hierarchy", hierarchicalOptional.get());
		return map;
	}

	@GetMapping("/hierarchys")
	public Map<String, Object> getHierarchy() {
		List<Hierarchical> hierarchicals = hierarchalRepository.findAll();
		Map<String, Object> map = new HashMap<>();
		map.put("hierarchys", hierarchicals);
		return map;
	}

}
