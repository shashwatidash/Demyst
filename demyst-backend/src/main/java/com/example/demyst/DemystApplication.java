package com.example.demyst;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
//@ComponentScan("com.example.demyst.repository.AccountingRepository")
public class DemystApplication {
    public DemystApplication() {
    }

    public static void main(String[] args) {
        SpringApplication.run(DemystApplication.class, args);
    }
}
