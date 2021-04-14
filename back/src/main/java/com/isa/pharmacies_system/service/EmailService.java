package com.isa.pharmacies_system.service;

import com.isa.pharmacies_system.domain.schedule.DermatologistAppointment;
import com.isa.pharmacies_system.domain.user.Patient;
import com.isa.pharmacies_system.domain.user.Users;
import com.isa.pharmacies_system.repository.IPatientRepository;
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

    private JavaMailSender javaMailSender;
    private Environment env;
    private IPatientRepository patientRepository;

    @Autowired
    public EmailService(JavaMailSender javaMailSender, Environment env, IPatientRepository patientRepository) {
        this.javaMailSender = javaMailSender;
        this.env = env;
        this.patientRepository = patientRepository;
    }

    @Async
    public void sendNotificationForSuccessBookAppointment(Long patientId) throws MailException, InterruptedException {

        Patient patient = patientRepository.findById(patientId).orElse(null);
        SimpleMailMessage mail = new SimpleMailMessage();
        mail.setTo("isa2020.team36@gmail.com");
        mail.setFrom(env.getProperty("spring.mail.username"));
        mail.setSubject("Zakazivanje pregleda");
        if(patient != null){
            mail.setText("Pozdrav " + patient.getFirstName() + ",\n\nUspesno ste zakazali pregled.\n");
        }else{
            mail.setText("Pozdrav !!!!");
        }
        javaMailSender.send(mail);
    }


}
