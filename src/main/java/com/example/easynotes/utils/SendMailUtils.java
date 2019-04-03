package com.example.easynotes.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class SendMailUtils {
	@Autowired
	private EmailNotificationImpl emailNotificationImpl;

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
}
