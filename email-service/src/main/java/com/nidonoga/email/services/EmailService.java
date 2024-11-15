package com.nidonoga.email.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Service;

import com.nidonoga.email.dtos.EmailDto;

@Service
public class EmailService {
	
	private Logger logger = LoggerFactory.getLogger(EmailService.class);
	
//	@Autowired
//	private JavaMailSender javaMailSender;
	
    public EmailDto sendEmail(EmailDto dto) {
    	logger.info("Enviando email...");
    	
    	SimpleMailMessage message = new SimpleMailMessage();
    	message.setTo(dto.emailTo());
    	message.setSubject(dto.subject());
    	message.setText(dto.text());
    	// javaMailSender.send(message);
    	return dto;
    }
}
