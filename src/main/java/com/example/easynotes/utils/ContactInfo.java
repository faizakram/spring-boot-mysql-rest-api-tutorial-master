package com.example.easynotes.utils;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

public class ContactInfo {

	private Integer id;
	private String name;
	private Integer age;
	private List<MultipartFile> file;
	/**
	 * @return the id
	 */
	public Integer getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(Integer id) {
		this.id = id;
	}
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * @return the age
	 */
	public Integer getAge() {
		return age;
	}
	/**
	 * @param age the age to set
	 */
	public void setAge(Integer age) {
		this.age = age;
	}
	/**
	 * @return the file
	 */
	public List<MultipartFile> getFile() {
		return file;
	}
	/**
	 * @param file the file to set
	 */
	public void setFile(List<MultipartFile> file) {
		this.file = file;
	}
	
}
