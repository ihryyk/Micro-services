package com.app.microcollector;

import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    public User mapUserDtoToUser(UserDto userDto) {
        User user = new User();
        user.setFirstName(userDto.getFirstName());
        user.setLastName(userDto.getLastName());
        return user;
    }

}
