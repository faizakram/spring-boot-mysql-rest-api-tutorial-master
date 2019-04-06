package com.example.easynotes.utils;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.example.easynotes.model.CopyObjects;
import com.example.easynotes.repository.CopyObjectRepository;

@Component
public class SendMailUtils {
	@Autowired
	private EmailNotificationImpl emailNotificationImpl;
	@Autowired
	private CopyObjectRepository copyObjectRepository;
	
	@Autowired
	private S3Utility s3Utility;

	@Scheduled(cron = "0 * * ? * *")
	public void runScheduler() {
		/*
		 * Map<String, Object> emailMap
		 * =emailNotificationImpl.insertEmailRecordForRest("faiz.krm@gmail.com",
		 * "Faiz Akram"); emailNotificationImpl.sendEmailNotification(emailMap,
		 * "faiz.krm@gmail.com", true, null, null);
		 */
		System.out.println("Working");
	}
	
	@Scheduled(cron = "0 * * ? * *")
	public void removeFileOnS3() {
		List<CopyObjects> copyObjects = copyObjectRepository.findByCurrentTime(new Date());
		s3Utility.deleteObjectsFromPublic(copyObjects.stream().map(CopyObjects::getFileName).collect(Collectors.toList()));
		copyObjectRepository.deleteAll(copyObjects);
	}
}
