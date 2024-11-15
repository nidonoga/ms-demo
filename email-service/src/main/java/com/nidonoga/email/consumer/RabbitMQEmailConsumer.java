package com.nidonoga.email.consumer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import com.nidonoga.email.dtos.EmailDto;
import com.nidonoga.email.services.EmailService;

@Component
public class RabbitMQEmailConsumer {
	
	private Logger logger = LoggerFactory.getLogger(RabbitMQEmailConsumer.class);
	
	@Autowired
	private EmailService emailService;
	
	@RabbitListener(queues = "${broker.queue.email.name}")
	public void listenEmail(@Payload EmailDto emailDto) {
		logger.info("Received Msg RabbitMQ: " + emailDto);
		emailService.sendEmail(emailDto);
	}
}
