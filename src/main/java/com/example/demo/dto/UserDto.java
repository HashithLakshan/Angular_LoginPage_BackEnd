package com.example.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class UserDto {

    private String userId;
    private String userName;
    private String userPassword;
    private String firstName;
    private String lastName;
    private String contact;
    private String gender;
    private String email;


}
