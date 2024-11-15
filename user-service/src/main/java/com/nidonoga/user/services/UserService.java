package com.nidonoga.user.services;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nidonoga.user.controllers.UserController;
import com.nidonoga.user.dtos.EmailRecordDto;
import com.nidonoga.user.dtos.MessageValidationDto;
import com.nidonoga.user.dtos.UserRecordDto;
import com.nidonoga.user.enums.ValidationStatusEnum;
import com.nidonoga.user.models.UserModel;
import com.nidonoga.user.producers.KafkaEmailProducer;
import com.nidonoga.user.producers.RabbitMQEmailProducer;
import com.nidonoga.user.proxy.EmailProxy;
import com.nidonoga.user.repositories.UserRepository;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import jakarta.validation.Valid;

@Service
public class UserService {
	private static final String MSG_LOGIN_JA_CADASTRADO = "Login já cadastrado";
	private static final String MSG_EMAIL_JA_CADASTRADO = "E-mail já cadastrado";
	private static final String SUBJECT_DEFAULT = "Email de confirmação";
	private static final String BODY_TEXT_DEFAULT = "Cadastro efetuado com sucesso";
	
	private Logger logger = LoggerFactory.getLogger(UserController.class);
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private KafkaEmailProducer kafkaEmailProducer;
	
	@Autowired
	private RabbitMQEmailProducer rabbitMQEmailProducer;
	
	@Autowired
	private EmailProxy emailProxy;
	
	@Transactional
	@CircuitBreaker(name = "default", fallbackMethod = "saveFallbackMethod")
	public UserRecordDto save(UserRecordDto dto) {
		var model = new UserModel();
		BeanUtils.copyProperties(dto, model);
		model.setRegistrationDate(LocalDateTime.now(ZoneId.of("UTC")));
		model.setUpdateDate(LocalDateTime.now(ZoneId.of("UTC")));
		model = userRepository.save(model);
		
		emailProxy.sendEmail(new EmailRecordDto(dto.email(), SUBJECT_DEFAULT, BODY_TEXT_DEFAULT));
		
		return convertToDto(model);
	}
		
	public UserRecordDto saveFallbackMethod(UserRecordDto dto, Throwable ex) {
		logger.error("Falha ao enviar e-mail. Email adicionado na fila para envio posterior." , ex);
		return saveWithEmailAsync(dto);
	}
	
	
	@Transactional
	public UserRecordDto saveWithEmailAsync(UserRecordDto dto) {
		var model = new UserModel();
		BeanUtils.copyProperties(dto, model);
		model.setRegistrationDate(LocalDateTime.now(ZoneId.of("UTC")));
		model.setUpdateDate(LocalDateTime.now(ZoneId.of("UTC")));
		model = userRepository.save(model);
		
		kafkaEmailProducer.publishMessageEmail(new EmailRecordDto(model.getEmail(), "Subject-Test", "EmailBody-Test"));
		rabbitMQEmailProducer.publishMessageEmail(new EmailRecordDto(model.getEmail(), "Subject-Test", "EmailBody-Test"));
		
		return convertToDto(model);
	}
	
	public List<MessageValidationDto> validateNewUser(UserRecordDto dto) {
		List<MessageValidationDto> listValidations = new ArrayList<>();
		if(userRepository.existsByLogin(dto.login())) {
			listValidations.add(new MessageValidationDto(MSG_LOGIN_JA_CADASTRADO, ValidationStatusEnum.ERROR));
		}
		
		if(userRepository.existsByEmail(dto.email())) {
			listValidations.add(new MessageValidationDto(MSG_EMAIL_JA_CADASTRADO, ValidationStatusEnum.ERROR));
		}
		
		return listValidations;
	}


	public Page<UserRecordDto> findAll(Pageable pageable) {
		Page<UserModel> modelPage = userRepository.findAll(pageable);
		return modelPage.map(this::convertToDto);
	}
	
	
    private UserRecordDto convertToDto(UserModel model) {
        return new UserRecordDto(
            model.getId(),
            model.getLogin(),
            model.getEmail(),
            model.getName(),
            model.getRegistrationDate(),
            model.getUpdateDate()
        );
    }

	public Optional<UserRecordDto> findById(UUID id) {
		Optional<UserModel> optional = userRepository.findById(id);
		if(!optional.isPresent()) {
			return Optional.empty();
		}
		
		
		return Optional.of(convertToDto(optional.get()));
	}

	@Transactional
	public void delete(UserRecordDto userRecordDto) {
		userRepository.deleteById(userRecordDto.id());
	}

	@Transactional
	public UserRecordDto update(UserRecordDto dtoCurrent, @Valid UserRecordDto dtoNew) {
		var model = new UserModel();
		BeanUtils.copyProperties(dtoCurrent, model);
		model.setName(dtoNew.name());
		model.setUpdateDate(LocalDateTime.now(ZoneId.of("UTC")));
		model = userRepository.save(model);
		return convertToDto(model);
	}
}
