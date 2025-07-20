package com.jason.spotifycloneback;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement
@EnableJpaAuditing
public class SpotifyCloneBackApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpotifyCloneBackApplication.class, args);
    }

}
