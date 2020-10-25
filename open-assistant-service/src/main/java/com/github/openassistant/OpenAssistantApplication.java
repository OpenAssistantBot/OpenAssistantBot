package com.github.openassistant;

import com.github.openassistant.service.CsvParserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class OpenAssistantApplication {

    public static void main(String[] args) {
        SpringApplication.run(OpenAssistantApplication.class, args);
    }

    @Bean
    public CommandLineRunner dataLoader(CsvParserService service) {
        return args -> service.initDatabase();
    }
}
