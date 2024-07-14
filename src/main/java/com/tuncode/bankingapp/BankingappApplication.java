package com.tuncode.bankingapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class BankingappApplication {

    public static void main(String[] args) {
        SpringApplication.run(BankingappApplication.class, args);
    }

}
