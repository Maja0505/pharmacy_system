package com.isa.pharmacies_system.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import com.isa.pharmacies_system.domain.complaint.DermatologistComplaintResponse;
import com.isa.pharmacies_system.domain.complaint.PharmacistComplaintResponse;
import com.isa.pharmacies_system.domain.complaint.PharmacyComplaintResponse;
import com.isa.pharmacies_system.domain.medicine.EPrescription;
import com.isa.pharmacies_system.domain.medicine.MedicineReservation;
import com.isa.pharmacies_system.domain.user.Patient;
import com.isa.pharmacies_system.repository.IPatientRepository;

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
    public void sendConfirmationMail(String email, String confirmationToken) throws MailException, InterruptedException {

        SimpleMailMessage mail = new SimpleMailMessage();
        mail.setTo(email);
        mail.setFrom(env.getProperty("spring.mail.username"));
        mail.setSubject("Confirm registration");
        mail.setText("Please confirm your registration by clicking the link below \n\n"+ "http://localhost:3000/auth/confirm_account/" + confirmationToken);
        javaMailSender.send(mail);
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

    @Async
    public void sendNotificationForSuccessMedicineReservation(MedicineReservation medicineReservation) throws MailException, InterruptedException {

        String firstName = medicineReservation.getPatientForMedicineReservation().getFirstName();
        SimpleMailMessage mail = new SimpleMailMessage();
        mail.setTo("isa2020.team36@gmail.com");
        mail.setFrom(env.getProperty("spring.mail.username"));
        mail.setSubject("Zakazivanje pregleda");
        mail.setText("Pozdrav " + firstName + ",\n\nUspesno ste izvrsili rezervaciju leka.\nBroj vase rezervacije je: " + medicineReservation.getId());

        javaMailSender.send(mail);
    }

    @Async
    public void sendNotificationForSuccessTakingMedicineReservation(MedicineReservation medicineReservation) throws MailException, InterruptedException {

        String firstName = medicineReservation.getPatientForMedicineReservation().getFirstName();
        SimpleMailMessage mail = new SimpleMailMessage();
        mail.setTo("isa2020.team36@gmail.com");
        mail.setFrom(env.getProperty("spring.mail.username"));
        mail.setSubject("Preuzimanje leka");
        mail.setText("Pozdrav " + firstName + ",\n\nPreuzeli ste lek " +medicineReservation.getReservedMedicine().getMedicineName() + " sa brojem  rezervacije: " + medicineReservation.getId());

        javaMailSender.send(mail);
    }
    
    @Async
    public void sendResponseForPharmacistComplaint(PharmacistComplaintResponse pharmacistComplaintResponse) throws MailException, InterruptedException {

        SimpleMailMessage mail = new SimpleMailMessage();
        mail.setTo(pharmacistComplaintResponse.getPharmacistComplaint().getPatientWithComplaint().getEmail());
        mail.setFrom(env.getProperty("spring.mail.username"));
        mail.setSubject("Preuzimanje leka");
        mail.setText(pharmacistComplaintResponse.getComplaintResponseContent());

        javaMailSender.send(mail);
    }
    
    @Async
    public void sendResponseForDermatologistComplaint(DermatologistComplaintResponse dermatologistComplaintResponse) throws MailException, InterruptedException {

        SimpleMailMessage mail = new SimpleMailMessage();
        mail.setTo(dermatologistComplaintResponse.getDermatologistComplaint().getPatientWithComplaint().getEmail());
        mail.setFrom(env.getProperty("spring.mail.username"));
        mail.setSubject("Preuzimanje leka");
        mail.setText(dermatologistComplaintResponse.getComplaintResponseContent());

        javaMailSender.send(mail);
    }

    @Async
    public void sendResponseForPharmacyComplaint(PharmacyComplaintResponse pharmacyComplaintResponse) throws MailException, InterruptedException {

        SimpleMailMessage mail = new SimpleMailMessage();
        mail.setTo(pharmacyComplaintResponse.getPharmacyComplaint().getPatientWithComplaint().getEmail());
        mail.setFrom(env.getProperty("spring.mail.username"));
        mail.setSubject("Preuzimanje leka");
        mail.setText(pharmacyComplaintResponse.getComplaintResponseContent());

        javaMailSender.send(mail);
    }
    
    @Async
    public void sendNoticeForProcessedEPrescription(EPrescription ePrescription) throws MailException, InterruptedException {
    	String firstName = ePrescription.getPatientForEPrescription().getFirstName();
        SimpleMailMessage mail = new SimpleMailMessage();
        mail.setTo(ePrescription.getPatientForEPrescription().getEmail());
        mail.setFrom(env.getProperty("spring.mail.username"));
        mail.setSubject("Preuzimanje leka");
        mail.setText("Pozdrav " + firstName + ",\n\nPreuzeli ste lijekove prepisane preko ERecepta.\n\nSrdacan pozdrav!");

        javaMailSender.send(mail);
    }
}
