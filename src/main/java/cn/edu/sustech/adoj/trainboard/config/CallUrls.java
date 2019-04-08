package cn.edu.sustech.adoj.trainboard.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Getter
@Setter
@Configuration
@ConfigurationProperties(prefix = "cas.call-urls")
public class CallUrls {
    private String serviceUrl;
    private String validateUrl;
    private String loginBaseUrl;
}
