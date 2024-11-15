package com.nidonoga.email.consumer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import com.nidonoga.email.dtos.EmailDto;
import com.nidonoga.email.services.EmailService;

@Component
public class KafkaEmailConsumer {
	
	private Logger logger = LoggerFactory.getLogger(KafkaEmailConsumer.class);
	
	@Autowired
	private EmailService emailService;
	
	@KafkaListener(topics = "ms-email-kafka", groupId = "ms-email-kafka-group-id", containerFactory = "kafkaListenerContainerFactory")
	public void listenEmail(EmailDto emailDto) {
		logger.info("Received Msg Kafka: " + emailDto);
		emailService.sendEmail(emailDto);
	}
}
