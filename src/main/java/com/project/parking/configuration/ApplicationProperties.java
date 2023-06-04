package com.project.parking.configuration;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.validation.annotation.Validated;

@Data
@Validated
@Configuration
@NoArgsConstructor
@ConfigurationProperties(prefix = "parking")
public class ApplicationProperties {
    private String originEmail;
    private String replyEmail;
}
