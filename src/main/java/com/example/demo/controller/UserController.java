package com.example.demo.controller;

import com.example.demo.dto.UserDto;
import com.example.demo.service.UserService;
import com.example.demo.utill.CommonResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("api/users")
public class UserController {

@Autowired
    private final UserService userService;


    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/save/user")
    CommonResponse saveUser (@RequestBody UserDto userDto){
        return userService.saveUser(userDto);
    }

    @PostMapping("/findByUserNameAndUserPassword")
    public String getUserDetails(@RequestParam ("userName") String userName, @RequestParam ("userPassword") String userPassword) {
        return userService.getDetalisUser(userName,userPassword);
    }
    @GetMapping("/findByUserName/{userName}")
    public CommonResponse getUserDetails(@PathVariable("userName") String userName) {
        return userService.getUserDetails(userName);
    }
}
