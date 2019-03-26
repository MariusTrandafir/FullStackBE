package com.fullStack.user.service;

import java.util.List;

import com.fullStack.user.model.User;
import com.fullStack.user.model.UserDto;

public interface UserService {

    User save(UserDto user);

    User findOne(String username);

    UserDto update(UserDto userDto);
}
