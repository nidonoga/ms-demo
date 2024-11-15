package com.nidonoga.user.producers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.nidonoga.user.dtos.EmailRecordDto;

@Component
public class RabbitMQEmailProducer {
	
	private Logger logger = LoggerFactory.getLogger(RabbitMQEmailProducer.class);
	
	@Autowired
	private RabbitTemplate rabbitTemplate;
	
	@Value("${broker.queue.email.name}")
	private String routingKey;
	
	public void publishMessageEmail(EmailRecordDto dto) {
		logger.info("Send Msg RabbitMQ: " + dto);
		rabbitTemplate.convertAndSend("", routingKey, dto);
	}
}
