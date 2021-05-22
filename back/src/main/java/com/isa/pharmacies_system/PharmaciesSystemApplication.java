package com.isa.pharmacies_system;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.scheduling.annotation.EnableAsync;

import java.util.Properties;

@SpringBootApplication

@EnableAsync
public class PharmaciesSystemApplication {

	public static void main(String[] args) {
		SpringApplication.run(PharmaciesSystemApplication.class, args);
	}
}
