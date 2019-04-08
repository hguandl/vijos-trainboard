package cn.edu.sustech.adoj.trainboard;

import cn.edu.sustech.adoj.trainboard.config.CallUrls;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties(CallUrls.class)
public class TrainboardApplication {

    public static void main(String[] args) {
        SpringApplication.run(TrainboardApplication.class, args);
    }

}
