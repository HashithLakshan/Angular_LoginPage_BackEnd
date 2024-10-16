package com.example.demo.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "user_Registraion")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column (name = "User_Id")
    private Long userId;

    @Column (name = "User_Name")
    private String userName;

    @Column (name = "User_Password")
    private String userPassword;

    @Column (name = "First_Name")
    private String firstName;

    @Column (name = "Last_Name")
    private String lastName;

    @Column (name = "Contact")
    private String contact;

    @Column (name = "Gender")
    private String gender;

    @Column (name = "email")
    private String email;

}
