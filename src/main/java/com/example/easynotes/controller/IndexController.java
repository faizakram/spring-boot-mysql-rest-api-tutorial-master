package com.example.easynotes.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.easynotes.model.CopyObjects;
import com.example.easynotes.model.Resource;
import com.example.easynotes.model.User;
import com.example.easynotes.repository.CopyObjectRepository;
import com.example.easynotes.repository.UserRepository;
import com.example.easynotes.utils.S3Utility;

@RestController
public class IndexController {

	@Autowired
	private CopyObjectRepository copyObjectRepository;
	
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private S3Utility s3Utility;
	
	private static final Logger logger = LogManager.getLogger("app");

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
	
	@PostMapping("/login")
	public Map<String, Object> getLogin(@RequestParam String username, @RequestParam String password) {
		Map<String, Object> map = new HashMap<>();
		User user = userRepository.getLogin(username, password);
		if (user != null) {
			map.put("userId", user.getId());
			map.put("userName", user.getUserName());
			map.put("role", user.getRolePermission().getRole());
			map.put("create", user.getRolePermission().getCreate());
			map.put("delete", user.getRolePermission().getDelete());
			map.put("update", user.getRolePermission().getUpdate());
			map.put("read", user.getRolePermission().getRead());
		}
		else {
			map.put("error", "No User Found");
			logger.info("Hello");
			logger.warn("Warning");
		}
		return map;
	}
	
	@GetMapping(value = "/increaseTime")
	public String moveObjects(@RequestParam List<String> resources,
			@RequestParam(required = false, defaultValue = "1") Long min) throws InterruptedException {
		List<CopyObjects> fileList = copyObjectRepository.findAll(resources);
		fileList.forEach(copyObject->{
			copyObject.setValidMin(min);
			copyObject.setExpirationTime(S3Utility.getExpireDateTime(min));
		});
		copyObjectRepository.saveAll(fileList);
		return "success";
	}
	
	
	@PostMapping("uploadall")
	public String sendMail(@RequestParam List<MultipartFile> file) {
		s3Utility.uploadOnS3All(file);
		return "success";
	}
}
