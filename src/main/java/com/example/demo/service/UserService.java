package com.example.demo.service;

import com.example.demo.dto.UserDto;
import com.example.demo.utill.CommonResponse;

public interface UserService {

    CommonResponse saveUser(UserDto userDto);

    String getDetalisUser(String userName, String userPassword);

    CommonResponse getUserDetails(String userName);
}
