package com.dev.alex.phonecollect.model;

import lombok.Data;

@Data
public class UserDTO {
    private String username;
    private String password;
    private Role role;
}
