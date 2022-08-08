package org.devnotfound.removecontainers;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class RemoveContainersApplication {

    public static void main(String[] args) {
        SpringApplication.run(RemoveContainersApplication.class, args);
    }
}
