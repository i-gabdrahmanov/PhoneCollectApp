package com.dev.alex.phonecollect.model;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

@Entity
@Getter
@Setter
@Table(name = "phone_collectors_users")
@DynamicInsert
@DynamicUpdate
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    Long id;

    @Column(name = "login", nullable = false, unique = true)
    String login;

    @Column(name = "password", nullable = false)
    String password;

    @Enumerated (value=EnumType.STRING)
    private Role role;
}
