package com.coop.migration.schema_intake;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "com.coop.migration.schema_intake")
public class SchemaIntakeApplication {

    public static void main(String[] args) {
        SpringApplication.run(SchemaIntakeApplication.class, args);
    }
}
