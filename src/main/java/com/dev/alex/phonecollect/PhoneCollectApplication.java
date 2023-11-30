package com.dev.alex.phonecollect;

import org.hibernate.Session;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class PhoneCollectApplication {

    public static void main(String[] args) {
        SpringApplication.run(PhoneCollectApplication.class, args);
    }
}
