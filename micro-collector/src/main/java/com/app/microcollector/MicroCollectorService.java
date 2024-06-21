package com.app.microcollector;

import com.fasterxml.jackson.core.type.TypeReference;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Slf4j
@RequiredArgsConstructor
@Service
public class MicroCollectorService {

    private final MicroCollectorClient microCollectorClient;
    private final MicroCollectorRepository microCollectorRepository;
    private final UserMapper userMapper;

    @Scheduled(fixedRateString = "6000")
    public void getAllUsers() {
        log.info("Getting all users");
        ResponseEntity<String> response = microCollectorClient.getAllUsers();
        List<UserDto> userDtoList = JsonObjectMapper.fromJson(response.getBody(), new TypeReference<>() {
        });
        for (UserDto userDto : userDtoList) {
            User user = microCollectorRepository.save(userMapper.mapUserDtoToUser(userDto));
            log.info("User: {}", user);
        }
        log.info("Saved all users: {}", userDtoList);
    }


}
