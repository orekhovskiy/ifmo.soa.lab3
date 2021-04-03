package ru.ifmo.soa.lab3.service1.eureka;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class Lab3Service1EurekaApplication {

    public static void main(String[] args) {
        SpringApplication.run(Lab3Service1EurekaApplication.class, args);
    }

}
