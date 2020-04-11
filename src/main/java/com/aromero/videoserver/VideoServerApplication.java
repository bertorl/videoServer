package com.aromero.videoserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.reactive.config.EnableWebFlux;

@SpringBootApplication
@EnableWebFlux
public class VideoServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(VideoServerApplication.class, args);
    }

}
