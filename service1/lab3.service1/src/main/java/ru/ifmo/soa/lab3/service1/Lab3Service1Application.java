package ru.ifmo.soa.lab3.service1;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class Lab3Service1Application {

    public static void main(String[] args) {
        SpringApplication.run(Lab3Service1Application.class, args);
    }

}
