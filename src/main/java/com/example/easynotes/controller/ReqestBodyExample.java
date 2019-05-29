package com.example.easynotes.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.easynotes.utils.ContactInfo;

@RestController
public class ReqestBodyExample {

	

	@PostMapping("requestBodytest")
	public Map<String, Object> requestBody(@RequestParam ContactInfo contactInfo){
		Map<String, Object> maps = new HashMap<>();
		maps.put("contactInfo", contactInfo);
		return maps;
	}

	

}
