package com.unnamedteam.stuffrent;

import com.unnamedteam.stuffrent.config.StorageProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties(StorageProperties.class)
public class StuffRentApplication {

    public static void main(String[] args) {
        SpringApplication.run(StuffRentApplication.class, args);
    }

}
