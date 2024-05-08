package com.excel.main;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
@ComponentScan("com.excel")
public class ExcelDemoApplication {
    public static void main(String[] args) {
        SpringApplication.run(ExcelDemoApplication.class, args);
    }
}