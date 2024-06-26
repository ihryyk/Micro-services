package com.app.microrecipient;

import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
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

    @Scheduled(fixedRateString = "3000")
    private void receiveMessage() {
        Object message = rabbitTemplate.receiveAndConvert("micro-services");
        if (message != null) {
            MicroSenderMessageResponse microSenderMessageResponse = JsonObjectMapper.fromJson((String) message,
                    MicroSenderMessageResponse.class);
            saveUser(microSenderMessageResponse.user());
            log.info("Read message form rabbitmq: {} ...", message);
            return;
        }
        log.error("No messages available in the queue");

    }

}
