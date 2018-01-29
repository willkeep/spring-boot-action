package com.willkeep;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportResource;

/**
 * <p>Description:</p>
 *
 * @author heng
 * @date 2018-01-19
 */
@ImportResource({"classpath:application-*.xml"})
@SpringBootApplication
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
