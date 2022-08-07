package org.devnotfound.testcontainerscrontask;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class ContainerRemoverApplication {

    public static void main(String[] args) {
        SpringApplication.run(ContainerRemoverApplication.class, args);
    }
}
