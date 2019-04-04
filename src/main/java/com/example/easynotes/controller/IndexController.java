package com.example.easynotes.controller;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.example.easynotes.model.Resource;
import com.example.easynotes.repository.CopyObjectRepository;

@RestController
public class IndexController {

	@Autowired
	private CopyObjectRepository copyObjectRepository;

	@GetMapping("/")
	public String sayHello() {
		return "Hello and Welcome to the EasyNotes application. You can create a new Note by making a POST request to /api/notes endpoint.";
	}

	@GetMapping("/getName/{id}")
	public Map<String, Object> getNameById(@PathVariable String id) {
		Map<String, Object> map = new HashMap<>();
		Resource resouce = copyObjectRepository.findBy(id);
		if (resouce != null)
			map.put("value", resouce.getName());
		else
			map.put("value", "Not Found");
		return map;
	}
}
