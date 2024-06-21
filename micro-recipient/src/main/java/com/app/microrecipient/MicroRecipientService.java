package com.app.microrecipient;

import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Slf4j
@Service
public class MicroRecipientService {

    private final MicroRecipientRepository microRecipientRepository;
    private final RabbitTemplate rabbitTemplate;

    public List<User> getAllUsers() {
        log.info("Getting all users");
        List<User> users = microRecipientRepository.findAll();
        microRecipientRepository.deleteAll();
        return users;
    }

    private void saveUser(User user) {
        log.info("Saving user: userFirstName={}, userLastName={} ...", user.getFirstName(), user.getLastName());
        User savedUser = microRecipientRepository.save(user);
        log.info("Saved user: userFirstName={}, userLastName={}, userId={} ...", savedUser.getFirstName(),
                savedUser.getLastName(), savedUser.getId());
    }

    @RabbitListener(queues = "micro-services")
    private void receiveMessage(String message) {
        log.info("Reading message form rabbitmq: {} ...", message);
        MicroSenderMessageResponse microSenderMessageResponse = JsonObjectMapper.fromJson(message,
                MicroSenderMessageResponse.class);
        saveUser(microSenderMessageResponse.user());
        log.info("Read message form rabbitmq: {} ...", message);
    }

}
