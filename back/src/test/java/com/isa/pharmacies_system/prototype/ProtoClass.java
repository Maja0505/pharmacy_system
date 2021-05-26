package com.isa.pharmacies_system.prototype;

import com.isa.pharmacies_system.domain.medicine.*;
import com.isa.pharmacies_system.domain.pharmacy.Address;
import com.isa.pharmacies_system.domain.pharmacy.Pharmacy;
import com.isa.pharmacies_system.domain.report.DermatologistReport;
import com.isa.pharmacies_system.domain.schedule.DermatologistAppointment;
import com.isa.pharmacies_system.domain.schedule.StatusOfAppointment;
import com.isa.pharmacies_system.domain.schedule.TypeOfAppointment;
import com.isa.pharmacies_system.domain.storage.PharmacyStorage;
import com.isa.pharmacies_system.domain.storage.PharmacyStorageItem;
import com.isa.pharmacies_system.domain.user.Dermatologist;
import com.isa.pharmacies_system.domain.user.Patient;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ProtoClass {

    public static DermatologistReport protoDermatologistReport(DermatologistReport dr){
        dr.setId(1l);
        dr.setReportInfo("Report info");
        dr.setDermatologistAppointment(protoDermatologistAppointment(new DermatologistAppointment()));
        return dr;
    }

    public static DermatologistAppointment protoDermatologistAppointment(DermatologistAppointment d){
        d.setId(1l);
        d.setStatusOfAppointment(StatusOfAppointment.Reserved);
        d.setAppointmentPrice(100);
        d.setAppointmentPoints(10);
        d.setTypeOfAppointment(TypeOfAppointment.Dermatologist_appointment);
        d.setDermatologistAppointmentStartTime(LocalDateTime.now().minusHours(1));
        d.setDermatologistAppointmentEndTime(LocalDateTime.now().plusHours(1));
        d.setPatientWithDermatologistAppointment(protoPatient(new Patient()));
        d.setDermatologistForAppointment(protoDermatologist(new Dermatologist()));
        d.setPharmacyForDermatologistAppointment(protoPharmacy(new Pharmacy()));
        return d;
    }

    public static Patient protoPatient(Patient p){
        p.setId(1l);
        p.setFirstName("Mile");
        p.setLastName("Milosevic");
        p.setEmail("mile@gmail.com");
        p.setPhoneNumber("+381545455");
        p.setPatientPoints(0);
        p.setPenalty(0);
        p.setMedicineAllergies(protoMedicineAllergies());
        return p;
    }

    public static Set<Medicine> protoMedicineAllergies(){
        Set<Medicine> medicineSet = new HashSet<>();
        Medicine m1 = protoMedicine(new Medicine());
        m1.setId(1l);
        medicineSet.add(m1);
        return medicineSet;
    }

    public static Dermatologist protoDermatologist(Dermatologist d){
        d.setId(1L);
        d.setFirstName("Mika");
        d.setLastName("Mikic");
        d.setEmail("mika@gmail.com");
        d.setPhoneNumber("+381545455");
        return d;
    }

    public static Pharmacy protoPharmacy(Pharmacy p){
        p.setId(1L);
        p.setPharmacyName("Apoteka");
        p.setPharmacyAddress(protoAddress(new Address()));
        return p;
    }

    public static Address protoAddress(Address a){
        a.setStreetName("Ulica");
        a.setStreetNumber("Broj");
        a.setCity("Grad");
        a.setCountry("Drzava");
        return a;
    }

    public static PharmacyStorageItem protoPharmacyStorageItem(PharmacyStorageItem i){
        i.setId(1l);
        i.setMedicineAmount(1);
        i.setTypeOfItem(TypeOfItem.Pharmacy_storage_item);
        i.setPharmacyStorageWithItem(protoPharmacyStorage(new PharmacyStorage()));
        i.setMedicineItem(protoMedicine(new Medicine()));
        return i;
    }

    public static Medicine protoMedicine(Medicine m){
        m.setId(1l);
        m.setMedicineName("Brufen");
        m.setMedicineCode("123");
        m.setTypeOfMedicine(TypeOfMedicine.Antibiotics);
        return m;
    }

    public static PharmacyStorage protoPharmacyStorage(PharmacyStorage p){
        p.setId(1l);
        p.setPharmacy(protoPharmacy(new Pharmacy()));
        p.setPharmacyStorageItems(new HashSet<>());
        return p;
    }

    public static List<MedicineReservation> protoMedicineReservations(MedicineReservation m){
        m.setStatusOfMedicineReservation(StatusOfMedicineReservation.CREATED);
        m.setId(1l);
        m.setReservedMedicine(protoMedicine(new Medicine()));
        m.setPatientForMedicineReservation(protoPatient(new Patient()));
        m.setPharmacyForMedicineReservation(protoPharmacy(new Pharmacy()));
        m.setDateOfTakingMedicine(LocalDate.now().plusDays(5));
        return Arrays.asList(m);
    }

}