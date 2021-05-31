package com.isa.pharmacies_system.prototype;

import com.isa.pharmacies_system.DTO.*;
import com.isa.pharmacies_system.domain.medicine.*;
import com.isa.pharmacies_system.domain.pharmacy.Address;
import com.isa.pharmacies_system.domain.pharmacy.Pharmacy;
import com.isa.pharmacies_system.domain.report.DermatologistReport;
import com.isa.pharmacies_system.domain.schedule.DermatologistAppointment;
import com.isa.pharmacies_system.domain.schedule.StatusOfAppointment;
import com.isa.pharmacies_system.domain.schedule.TypeOfAppointment;
import com.isa.pharmacies_system.domain.schedule.TypeOfVacation;
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

    public static DermatologistAppointment protoDermatologistAppointmentOpen(DermatologistAppointment d){
        d.setId(1l);
        d.setStatusOfAppointment(StatusOfAppointment.Open);
        d.setAppointmentPrice(100);
        d.setAppointmentPoints(10);
        d.setTypeOfAppointment(TypeOfAppointment.Dermatologist_appointment);
        d.setDermatologistAppointmentStartTime(LocalDateTime.now().minusHours(1));
        d.setDermatologistAppointmentEndTime(LocalDateTime.now().plusHours(1));
        d.setPatientWithDermatologistAppointment(null);
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
        i.setMedicineAmount(1L);
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
        m.setFormOfMedicine(FormOfMedicine.Capsule);
        m.setManufacturerOfMedicine("Ivancic i sinovi");
        m.setNotes("Bilo sta");
        m.setIngredients(new HashSet<>());
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

    public static MedicineReservation protoMedicineReservation(MedicineReservation m){
        m.setStatusOfMedicineReservation(StatusOfMedicineReservation.CREATED);
        m.setId(1l);
        m.setReservedMedicine(protoMedicine(new Medicine()));
        m.setPatientForMedicineReservation(protoPatient(new Patient()));
        m.setPharmacyForMedicineReservation(protoPharmacy(new Pharmacy()));
        m.setDateOfTakingMedicine(LocalDate.now().plusDays(5));
        return m;
    }


    public static UserPasswordDTO protoUserPasswordDTO(UserPasswordDTO u){
        u.setId(1l);
        u.setConfirmedNewPassword("1234");
        u.setNewPassword("1234");
        u.setConfirmedPassword("12345");
        return u;
    }

    public static VacationRequestDTO protoVacationRequestDTO(VacationRequestDTO v){
        v.setStaffId(1l);
        v.setTypeOfVacation(TypeOfVacation.Holiday);
        v.setVacationRequestNotes("Some Notes");
        v.setVacationStartDate(LocalDate.now());
        v.setVacationEndDate(LocalDate.now().plusMonths(1));
        return v;
    }

    public static Address protoAddress(){
       Address a = new Address();
       a.setStreetNumber("Bulevar");
       a.setStreetNumber("12");
       a.setCity("Novi Sad");
       a.setCountry("Srbija");
       a.setLatitude(11111);
       a.setLongitude(22222);
       return a;
    }

    public static UserPersonalInfoDTO protoUserPersonalInfoDTO(UserPersonalInfoDTO u){
        u.setId(2l);
        u. setFirstName("Peraaaa");
        u.setLastName("Pericccc");
        u.setAddress(protoAddress());
        u.setPhoneNumber("065/123-123");
        u.setEmail("patient1@gmail.com");
        return u;
    }

    public static MedicineReservationDTO protoMedicineReservationDTO(MedicineReservationDTO m){
        m.setDateOfTakingMedicine(LocalDate.now().plusDays(3));
        return m;
    }

    public static MedicineReservationInfoDTO protoMedicineReservationInfo(MedicineReservationInfoDTO m){
        m.setReservationId(1l);
        m.setMedicineId(1l);
        m.setPharmacyId(1l);
        m.setMedicineName("Bromic");
        m.setPharmacyName("Pharmacy1");
        m.setTakingDate(LocalDate.now().plusDays(3));
        m.setStatusOfMedicineReservation(StatusOfMedicineReservation.CREATED);
        return m;
    }

    public static DermatologistAppointmentDTO protoDermatologistAppointmentDTO(DermatologistAppointmentDTO d){
        d.setId(2);
        return d;
    }
}
