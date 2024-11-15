package com.nidonoga.user.proxy;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;

import com.nidonoga.user.dtos.EmailRecordDto;

@FeignClient(name = "email-service")
public interface EmailProxy {
	
	@PostMapping(value = "/email-service")
	public EmailRecordDto sendEmail(EmailRecordDto dto);

}
