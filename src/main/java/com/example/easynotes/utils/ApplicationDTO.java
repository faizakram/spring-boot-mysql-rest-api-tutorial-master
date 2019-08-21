package com.example.easynotes.utils;

import java.util.List;

public class ApplicationDTO {
	private String applicationId;
	List<CommonDTO> commonDTO;
	
	public String getApplicationId() {
		return applicationId;
	}
	public void setApplicationId(String applicationId) {
		this.applicationId = applicationId;
	}
	public List<CommonDTO> getCommonDTO() {
		return commonDTO;
	}
	public void setCommonDTO(List<CommonDTO> commonDTO) {
		this.commonDTO = commonDTO;
	} 
	
	

}
