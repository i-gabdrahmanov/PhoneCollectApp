package com.dev.alex.phonecollect.model;

import lombok.Data;

@Data
public class UserDTO {
    private String login;
    private String password;
    private Role role;
}
