package com.example.new_shipping.service;



import com.example.new_shipping.dto.UserDto;
import com.example.new_shipping.entity.User;

import java.util.List;

public interface UserService {
    void saveUser(UserDto userDto);

    User findUserByEmail(String email);

    List<UserDto> findAllUsers();
}