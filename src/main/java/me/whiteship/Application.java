package me.whiteship;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import javax.swing.*;

/**
 * @author Keeun Baik
 */
@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        SpringApplication application = new SpringApplication(args);
        application.run(Application.class);
    }

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }

}
