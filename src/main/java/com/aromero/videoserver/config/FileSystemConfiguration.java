package com.aromero.videoserver.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Data
@Configuration
@ConfigurationProperties(prefix = "file-system-config")
public class FileSystemConfiguration {

    private String path;
}
