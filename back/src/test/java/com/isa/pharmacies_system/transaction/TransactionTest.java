package com.isa.pharmacies_system.transaction;

import com.isa.pharmacies_system.DTO.PharmacistAppointmentTimeDTO;
import com.isa.pharmacies_system.domain.medicine.MedicineReservation;
import com.isa.pharmacies_system.domain.schedule.DermatologistAppointment;
import com.isa.pharmacies_system.domain.storage.PharmacyStorageItem;
import com.isa.pharmacies_system.service.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.orm.ObjectOptimisticLockingFailureException;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TransactionTest {

    @Autowired
    private DermatologistAppointmentService dermatologistAppointmentService;

    @Autowired
    private MedicineReservationService medicineReservationService;

    @Autowired
    PharmacistAppointmentService pharmacistAppointmentService;

    @Autowired
    PharmacistService pharmacistService;

    @Autowired
    PharmacyStorageItemService pharmacyStorageItemService;

    //Nemanja
    @Test(expected = ObjectOptimisticLockingFailureException.class)
    public void testOptimisticLockingScenarioConflict1Student3() throws Throwable {

        ExecutorService executor = Executors.newFixedThreadPool(2);
        Future<?> future1 = executor.submit(new Runnable() {
            @Override
            public void run() {
                System.out.println("Startovan Thread 1");
                dermatologistAppointmentService.bookDermatologistAppointmentTest(2l,3l,5000L);

            }
        });
        executor.submit(new Runnable() {

            @Override
            public void run() {
                System.out.println("Startovan Thread 2");
                dermatologistAppointmentService.bookDermatologistAppointmentTest(3l,3l,0l);
                DermatologistAppointment da = dermatologistAppointmentService.findOne(3l);
                System.out.println("Version 2 : "  + da.getVersion());
            }
        });
        try {
            future1.get();
        } catch (ExecutionException e) {
            System.out.println("Exception from thread " + e.getCause().getClass());
            throw e.getCause();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        executor.shutdown();

    }

    //Nemanja
    @Test
    public void testPessimisticLockingScenarioConflict2Student3() throws Throwable {

        ExecutorService executor = Executors.newFixedThreadPool(2);
        Future<?> future1 = executor.submit(new Runnable() {
            @Override
            public void run() {
                System.out.println("Startovan Thread 1. Farmaceut pokusava da zakaze novi pregled pacijentu");
                PharmacistAppointmentTimeDTO timeDTO = new PharmacistAppointmentTimeDTO();
                timeDTO.setStartTime(LocalDateTime.of(2022, Month.MAY,1,15,10,0));
                timeDTO.setDuration(40);
                Boolean Thread1  = pharmacistAppointmentService.bookPharmacistAppointmentTest(2l,4l,timeDTO,false,5000L );
                System.out.println("Thread 1 upisao : " + Thread1);
            }
        });
        executor.submit(new Runnable() {

            @Override
            public void run() {
                System.out.println("Startovan Thread 2. Pacijent pokusava da zakaze novi pregled");
                PharmacistAppointmentTimeDTO timeDTO = new PharmacistAppointmentTimeDTO();
                timeDTO.setStartTime(LocalDateTime.of(2022, Month.MAY,1,15,10,0));
                timeDTO.setDuration(40);
                Boolean Thread2  = pharmacistAppointmentService.bookPharmacistAppointmentTest(3l,4l,timeDTO,true,0l);
                System.out.println("Thread 2 upisao : " + Thread2);
            }
        });
        try {
            future1.get();
        } catch (ExecutionException e) {
            System.out.println("Exception from thread " + e.getCause().getClass());
            throw e.getCause();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        executor.shutdown();

    }

    //Nemanja
    @Test(expected = ObjectOptimisticLockingFailureException.class)
    public void testOptimisticLockingScenarioConflict3Student3() throws Throwable {

        ExecutorService executor = Executors.newFixedThreadPool(2);
        Future<?> future1 = executor.submit(new Runnable() {
            @Override
            public void run() {
                System.out.println("Startovan Thread 1");
                MedicineReservation medicineReservation = medicineReservationService.getMedicineReservationById(2l);
                medicineReservationService.finishMedicineReservationTest(medicineReservation,3000l);// ocitan objekat sa id 1
            }
        });
        executor.submit(new Runnable() {

            @Override
            public void run() {
                System.out.println("Startovan Thread 2");
                MedicineReservation medicineReservation = medicineReservationService.getMedicineReservationById(2l);
                medicineReservationService.finishMedicineReservationTest(medicineReservation,0L);
                MedicineReservation mr = medicineReservationService.getMedicineReservationById(2L);
                System.out.println("Version : "  + mr.getVersion());
            }
        });
        try {
            future1.get();
        } catch (ExecutionException e) {
            System.out.println("Exception from thread " + e.getCause().getClass());
            throw e.getCause();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        executor.shutdown();

    }

    @Test(expected = ObjectOptimisticLockingFailureException.class)
    public void testOptimisticLockingScenarioConflict1Student1() throws Throwable {

        ExecutorService executor = Executors.newFixedThreadPool(2);
        Future<?> future1 = executor.submit(new Runnable() {
            @Override
            public void run() {
                System.out.println("Startovan Thread 1");
                MedicineReservation mr = medicineReservationService.getMedicineReservationById(2l);
                mr.setId(0l); //da ne bismo pravili dto
                medicineReservationService.createMedicineReservationTest(mr,5000L);// ocitan objekat sa id 1
            }
        });
        executor.submit(new Runnable() {

            @Override
            public void run() {
                System.out.println("Startovan Thread 2");
                MedicineReservation mr = medicineReservationService.getMedicineReservationById(2l);
                mr.setId(0l);
                medicineReservationService.createMedicineReservationTest(mr,0L);
                PharmacyStorageItem pi = pharmacyStorageItemService.findOne(1l);
                System.out.println("Version : "  + pi.getVersion());
            }
        });
        try {
            future1.get();
        } catch (ExecutionException e) {
            System.out.println("Exception from thread " + e.getCause().getClass());
            throw e.getCause();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        executor.shutdown();

    }

    @Test
    public void testPessimisticLockingScenarioConflict2Student1() throws Throwable {

        ExecutorService executor = Executors.newFixedThreadPool(2);
        Future<?> future1 = executor.submit(new Runnable() {
            @Override
            public void run() {
                System.out.println("Startovan Thread 1. Pacijent 1 pokusava da zakaze novi pregled");
                PharmacistAppointmentTimeDTO timeDTO = new PharmacistAppointmentTimeDTO();
                timeDTO.setStartTime(LocalDateTime.of(2022, Month.MAY,1,15,10,0));
                timeDTO.setDuration(40);
                Boolean Thread1  = pharmacistAppointmentService.bookPharmacistAppointmentTest(2l,4l,timeDTO,true,3000L );
                System.out.println("Thread 1 upisao : " + Thread1);
            }
        });
        executor.submit(new Runnable() {

            @Override
            public void run() {
                System.out.println("Startovan Thread 2. Pacijent 2 pokusava da zakaze novi pregled");
                PharmacistAppointmentTimeDTO timeDTO = new PharmacistAppointmentTimeDTO();
                timeDTO.setStartTime(LocalDateTime.of(2022, Month.MAY,1,15,10,0));
                timeDTO.setDuration(40);
                Boolean Thread2  = pharmacistAppointmentService.bookPharmacistAppointmentTest(3l,4l,timeDTO,true,0l);
                System.out.println("Thread 2 upisao : " + Thread2);
            }
        });
        try {
            future1.get();
        } catch (ExecutionException e) {
            System.out.println("Exception from thread " + e.getCause().getClass());
            throw e.getCause();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        executor.shutdown();

    }

    @Test(expected = ObjectOptimisticLockingFailureException.class)
    public void testOptimisticLockingScenarioConflict3Student1() throws Throwable {

        ExecutorService executor = Executors.newFixedThreadPool(2);
        Future<?> future1 = executor.submit(new Runnable() {
            @Override
            public void run() {
                System.out.println("Startovan Thread 1. ");
                medicineReservationService.increasePenaltyForMissedMedicineReservationTest(3000L);
                System.out.println("Thread 1 upisao : ");
            }
        });
        executor.submit(new Runnable() {

            @Override
            public void run() {
                System.out.println("Startovan Thread 2. Pacijent 2 pokusava da zakaze novi pregled");
                DermatologistAppointment dermatologistAppointment = dermatologistAppointmentService.findOne(4l);
                dermatologistAppointmentService.changeDermatologistAppointmentStatusToMissedTest(dermatologistAppointment,0l);
                System.out.println("Thread 2 upisao : ");
            }
        });
        try {
            future1.get();
        } catch (ExecutionException e) {
            System.out.println("Exception from thread " + e.getCause().getClass());
            throw e.getCause();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        executor.shutdown();

    }
}
