package com.nidonoga.user.producers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import com.nidonoga.user.dtos.EmailRecordDto;

@Component
public class KafkaEmailProducer {
	
	private Logger logger = LoggerFactory.getLogger(KafkaEmailProducer.class);
	
	@Autowired
	private KafkaTemplate<String, EmailRecordDto> kafkaTemplate;
	
	@Value("${broker.kafka.topic.email.name}")
	private String topic;
	
	public void publishMessageEmail(EmailRecordDto dto) {
		logger.info("Send Msg Kafka: " + dto);
		kafkaTemplate.send(topic, dto);
	}
}
