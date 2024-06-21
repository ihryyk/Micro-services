package com.app.microcollector;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class MicroCollectorApplication {

    public static void main(String[] args) {
        SpringApplication.run(MicroCollectorApplication.class, args);
    }

}
