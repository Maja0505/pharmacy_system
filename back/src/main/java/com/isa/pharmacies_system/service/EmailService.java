package com.isa.pharmacies_system.service;

import com.isa.pharmacies_system.domain.schedule.DermatologistAppointment;
import com.isa.pharmacies_system.domain.user.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    @Autowired
    private JavaMailSender javaMailSender;


    @Autowired
    private Environment env;

    @Async
    public void sendNotificationForSuccessBookAppointment(Users user) throws MailException, InterruptedException {


        SimpleMailMessage mail = new SimpleMailMessage();
        mail.setTo("isa2020.team36@gmail.com");
        mail.setFrom(env.getProperty("spring.mail.username"));
        mail.setSubject("Zakazivanje pregleda");
        mail.setText("Pozdrav " + user.getFirstName() + ",\n\nUspesno ste zakazali pregled.\n");
        javaMailSender.send(mail);
    }


}
