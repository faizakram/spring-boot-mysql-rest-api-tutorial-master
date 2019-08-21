package com.example.easynotes.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.easynotes.model.CFGApplication;
import com.example.easynotes.repository.ApplicationRepository;
import com.example.easynotes.utils.ApplicationDTO;

@RestController
public class Application {

	@Autowired
	private ApplicationRepository applicationRepository;


	@PostMapping("/saveApplication")
	public Map<String, Object> getLogin(@RequestBody ApplicationDTO applicationDTO) {
		Map<String, Object> map = new HashMap<>();
		applicationDTO.getCommonDTO().forEach(e -> {
			CFGApplication cFGApplication = new CFGApplication();
			cFGApplication.setApplicationid(applicationDTO.getApplicationId());
			cFGApplication.setConfigname(e.getLabelName());
			cFGApplication.setConfigvalue(e.getLabelValue());
			cFGApplication.setIsactive(true);
			applicationRepository.save(cFGApplication);
		});
		map.put("status", "Save Successfully");
		return map;
	}

}
