package com.app.microsender;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

@Slf4j
@RequiredArgsConstructor
@Service
public class MicroSenderService {

    private final RabbitTemplate rabbitTemplate;

    public void sendMessage(MicroSenderMessage microSenderMessage) {
      log.info("Sending MicroSenderMessage: {}", microSenderMessage);
       String message = JsonObjectMapper.toJson(microSenderMessage);
        rabbitTemplate.convertAndSend("micro-services",message);
    }

}
