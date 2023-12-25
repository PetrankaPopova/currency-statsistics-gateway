package com.currency.convertor;

import com.currency.convertor.client.RabbitMQSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication()
@EnableScheduling
public class ConvertorApplication {

    public static void main(String[] args) {

        SpringApplication.run(ConvertorApplication.class, args);
    }

}
