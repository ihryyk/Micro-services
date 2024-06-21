package com.app.microvisualizer;

import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MicroVisualizerService {

    private static final Logger log = LoggerFactory.getLogger(MicroVisualizerService.class);
    private final MicroVisualizerRepository microVisualizerRepository;
    private final UserMapper userMapper;

    public List<UserDto> getAllUsers(){
        log.info("Get all users");
        List<User> users = microVisualizerRepository.findAll();
        log.info("All users: {}",users);
        return users.stream()
                .map(userMapper::mapUserToUserDto)
                .collect(Collectors.toList());
    }

}
