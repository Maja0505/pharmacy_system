INSERT INTO users (id, email, password, first_name, last_name, phone_number, type_of_user,city,country,latitude,longitude,street_name,street_number) VALUES (nextval('my_seq_users'), 'patient1@gmail.com', '12345', 'Pera', 'Peric', '064000000', 2,'Chicago','USD', 41, 87, 'Fifth Ave','5A');
INSERT INTO users (id, email, password, first_name, last_name, phone_number, type_of_user,city,country,latitude,longitude,street_name,street_number) VALUES (nextval('my_seq_users'), 'patient2@gmail.com', '12345', 'Mika', 'Mikic', '064000000', 2,'Chicago','USD', 41, 87, 'Fifth Ave','5A');
INSERT INTO users (id, email, password, first_name, last_name, phone_number, type_of_user,city,country,latitude,longitude,street_name,street_number) VALUES (nextval('my_seq_users'), 'systemadmin1@gmail.com', '12345', 'Ivan', 'Ivanovic', '064000000', 0,'Chicago','USD', 41, 87, 'Fifth Ave','5A');
INSERT INTO users (id, email, password, first_name, last_name, phone_number, type_of_user,city,country,latitude,longitude,street_name,street_number) VALUES (nextval('my_seq_users'), 'pharmacyadmin1@gmail.com', '12345', 'Janko', 'Jankovic', '064000000', 1,'Chicago','USD', 41, 87, 'Fifth Ave','5A');
INSERT INTO users (id, email, password, first_name, last_name, phone_number, type_of_user,city,country,latitude,longitude,street_name,street_number) VALUES (nextval('my_seq_users'), 'pharmacyadmin2@gmail.com', '12345', 'Nikola', 'Nikolic', '064000000', 1,'Chicago','USD', 41, 87, 'Fifth Ave','5A');
INSERT INTO users (id, email, password, first_name, last_name, phone_number, type_of_user,city,country,latitude,longitude,street_name,street_number) VALUES (nextval('my_seq_users'), 'pharmacist1@gmail.com', '12345', 'Jana', 'Jankovic', '064000000', 3,'Chicago','USD', 41, 87, 'Fifth Ave','5A');
INSERT INTO users (id, email, password, first_name, last_name, phone_number, type_of_user,city,country,latitude,longitude,street_name,street_number) VALUES (nextval('my_seq_users'), 'pharmacist2@gmail.com', '12345', 'Nina', 'Ninkovic', '064000000', 3,'Chicago','USD', 41, 87, 'Fifth Ave','5A');
INSERT INTO users (id, email, password, first_name, last_name, phone_number, type_of_user,city,country,latitude,longitude,street_name,street_number) VALUES (nextval('my_seq_users'), 'dermatologist1@gmail.com', '12345', 'Pavle', 'Pavlovic', '064000000', 4,'Chicago','USD', 41, 87, 'Fifth Ave','5A');
INSERT INTO users (id, email, password, first_name, last_name, phone_number, type_of_user,city,country,latitude,longitude,street_name,street_number) VALUES (nextval('my_seq_users'), 'dermatologist2@gmail.com', '12345', 'Marko', 'Markovic', '064000000', 4,'Chicago','USD', 41, 87, 'Fifth Ave','5A');
INSERT INTO users (id, email, password, first_name, last_name, phone_number, type_of_user,city,country,latitude,longitude,street_name,street_number) VALUES (nextval('my_seq_users'), 'supplier1@gmail.com', '12345', 'Ana', 'Ivanovic', '064000000', 5,'Chicago','USD', 41, 87, 'Fifth Ave','5A');
INSERT INTO users (id, email, password, first_name, last_name, phone_number, type_of_user,city,country,latitude,longitude,street_name,street_number) VALUES (nextval('my_seq_users'), 'supplier2@gmail.com', '12345', 'Milan', 'Jankovic', '064001100', 5,'Subotica','Srbija', 211, 100, 'Kralja Milana Obrenovica','16A');


INSERT INTO system_admin (id) VALUES (3);

INSERT INTO pharmacies (id, city, country, latitude, longitude, street_name, street_number, pharmacy_average_rating, pharmacy_description, pharmacy_name) VALUES (nextval('my_seq_pharmacy'), 'Novi Sad', 'Srbija', 100, 200, 'Bore Prodanovica', '15A', 9.5, 'Apoteka za sve' , 'Apoteka1');
INSERT INTO pharmacies (id, city, country, latitude, longitude, street_name, street_number, pharmacy_average_rating, pharmacy_description, pharmacy_name) VALUES (nextval('my_seq_pharmacy'), 'Novi Sad', 'Srbija', 100, 200, 'Bore Prodanovica', '15A', 9.5, 'Apoteka za sve' , 'Apoteka2');
INSERT INTO pharmacies (id, city, country, latitude, longitude, street_name, street_number, pharmacy_average_rating, pharmacy_description, pharmacy_name) VALUES (nextval('my_seq_pharmacy'), 'Novi Sad', 'Srbija', 100, 200, 'Bore Prodanovica', '15A', 9.5, 'Apoteka nije za sve' , 'Apoteka2');

INSERT INTO price_lists (id, dermatologist_appointment_price_per_hour, pharmacist_appointment_price_per_hour, pharmacy_id) VALUES (nextval('my_seq_price_list'), 10, 20, 1);

INSERT INTO pharmacy_admins (id, pharmacy_for_pharmacy_admin_id) VALUES (4, 2);
INSERT INTO pharmacy_admins (id, pharmacy_for_pharmacy_admin_id) VALUES (5, 2);

INSERT INTO dermatologists (id,dermatologist_average_rating) VALUES (8,4.3);
INSERT INTO dermatologists (id,dermatologist_average_rating) VALUES (9,2.3);

INSERT INTO pharmacists (id,pharmacist_average_rating,pharmacy_for_pharmacist_id) VALUES (6,4.3,1);
INSERT INTO pharmacists (id,pharmacist_average_rating,pharmacy_for_pharmacist_id) VALUES (7,4.3,2);

insert into worker_schedule (id,dermatologist_id,pharmacy_id,type_of_worker_schedule) values (1,8,1,0);
insert into worker_schedule (id,dermatologist_id,pharmacy_id,type_of_worker_schedule) values (2,8,2,0);
insert into worker_schedule (id,pharmacy_id,pharmacist_id,type_of_worker_schedule) values (3,1,6,1);
insert into worker_schedule (id,pharmacy_id,pharmacist_id,type_of_worker_schedule) values (4,2,7,1);

INSERT INTO working_hours (id, status_of_working_hours,working_end_time,working_start_time,worker_schedule_id) VALUES (1,1,'2021-04-13 20:00','2021-04-13 10:50',1);
INSERT INTO working_hours (id, status_of_working_hours,working_end_time,working_start_time,worker_schedule_id) VALUES (2,1,'2021-04-14 20:00','2021-04-14 09:50',1);
INSERT INTO working_hours (id, status_of_working_hours,working_end_time,working_start_time,worker_schedule_id) VALUES (3,1,'2021-04-15 18:00','2021-04-15 08:50',1);
INSERT INTO working_hours (id, status_of_working_hours,working_end_time,working_start_time,worker_schedule_id) VALUES (4,1,'2021-04-16 16:00','2021-04-16 08:30',1);
INSERT INTO working_hours (id, status_of_working_hours,working_end_time,working_start_time,worker_schedule_id) VALUES (5,1,'2021-04-17 20:00','2021-04-17 10:00',1);
INSERT INTO working_hours (id, status_of_working_hours,working_end_time,working_start_time,worker_schedule_id) VALUES (6,1,'2021-04-18 21:00','2021-04-18 11:00',1);
INSERT INTO working_hours (id, status_of_working_hours,working_end_time,working_start_time,worker_schedule_id) VALUES (7,1,'2021-04-19 22:00','2021-04-19 12:00',1);
INSERT INTO working_hours (id, status_of_working_hours,working_end_time,working_start_time,worker_schedule_id) VALUES (8,1,'2021-04-20 21:10','2021-04-20 13:50',1);
INSERT INTO working_hours (id, status_of_working_hours,working_end_time,working_start_time,worker_schedule_id) VALUES (9,1,'2021-04-21 20:00','2021-04-21 11:50',1);
INSERT INTO working_hours (id, status_of_working_hours,working_end_time,working_start_time,worker_schedule_id) VALUES (10,1,'2021-04-22 20:00','2021-04-22 15:50',1);
INSERT INTO working_hours (id, status_of_working_hours,working_end_time,working_start_time,worker_schedule_id) VALUES (11,1,'2021-04-23 20:00','2021-04-23 10:50',1);
INSERT INTO working_hours (id, status_of_working_hours,working_end_time,working_start_time,worker_schedule_id) VALUES (12,1,'2021-04-15 21:00','2021-04-15 12:50',2);
INSERT INTO working_hours (id, status_of_working_hours,working_end_time,working_start_time,worker_schedule_id) VALUES (13,1,'2021-04-16 19:00','2021-04-16 14:50',2);
INSERT INTO working_hours (id, status_of_working_hours,working_end_time,working_start_time,worker_schedule_id) VALUES (14,1,'2021-04-17 18:00','2021-04-17 10:50',2);

INSERT INTO working_hours (id, status_of_working_hours,working_end_time,working_start_time,worker_schedule_id) VALUES (15,1,'2021-04-15 20:00','2021-04-15 10:50',3);
INSERT INTO working_hours (id, status_of_working_hours,working_end_time,working_start_time,worker_schedule_id) VALUES (16,1,'2021-04-15 20:00','2021-04-15 10:50',4);


INSERT INTO patient (id, category_of_patient, patient_points) VALUES (1, 0, 0.0);
INSERT INTO patient (id, category_of_patient, patient_points) VALUES (2, 1, 10.0);

insert into medicine_infos (id, form_of_medicine, manufacturer_of_medicine, medicine_code, medicine_name, notes, prescription_regime, type_of_medicine) values (nextval('my_seq_medicine_info'),1,'Medic Pharmacy', '211233', 'Andol','Familijooo',1,1);
insert into medicine_infos (id, form_of_medicine, manufacturer_of_medicine, medicine_code, medicine_name, notes, prescription_regime, type_of_medicine) values (nextval('my_seq_medicine_info'),0,'Medic Pharmacy', '211234', 'Apaurin','Brapoooooooo',1,1);
insert into medicine_infos (id, form_of_medicine, manufacturer_of_medicine, medicine_code, medicine_name, notes, prescription_regime, type_of_medicine) values (nextval('my_seq_medicine_info'),1,'Medic Pharmacy', '211235', 'Brufen','Familijooo',1,1);
insert into medicine_infos (id, form_of_medicine, manufacturer_of_medicine, medicine_code, medicine_name, notes, prescription_regime, type_of_medicine) values (nextval('my_seq_medicine_info'),0,'Medic Pharmacy', '211236', 'Bromic','Brapoooooooo',1,1);


insert into medicine (medicine_average_rating, id) values (5.0,1);
insert into medicine (medicine_average_rating, id) values (4.9,2);
insert into medicine (medicine_average_rating, id) values (5.0,3);
insert into medicine (medicine_average_rating, id) values (4.9,4);


insert into suppliers (id) values (10);
insert into suppliers (id) values (11);

insert into appointments (id,appointment_points,appointment_price,status_of_appointment,type_of_appointment) values (nextval('my_seq_appointment'),5,15,0,0);
insert into appointments (id,appointment_points,appointment_price,status_of_appointment,type_of_appointment) values (nextval('my_seq_appointment'),5,133,0,0);
insert into appointments (id,appointment_points,appointment_price,status_of_appointment,type_of_appointment) values (nextval('my_seq_appointment'),5,30,1,1);
insert into appointments (id,appointment_points,appointment_price,status_of_appointment,type_of_appointment) values (nextval('my_seq_appointment'),5,30,1,1);
insert into appointments (id,appointment_points,appointment_price,status_of_appointment,type_of_appointment) values (nextval('my_seq_appointment'),5,30,1,1);
insert into appointments (id,appointment_points,appointment_price,status_of_appointment,type_of_appointment) values (nextval('my_seq_appointment'),5,30,0,0);

insert into appointments (id,appointment_points,appointment_price,status_of_appointment,type_of_appointment) values (nextval('my_seq_appointment'),5,15,1,0);
insert into appointments (id,appointment_points,appointment_price,status_of_appointment,type_of_appointment) values (nextval('my_seq_appointment'),5,133,1,0);
insert into appointments (id,appointment_points,appointment_price,status_of_appointment,type_of_appointment) values (nextval('my_seq_appointment'),5,30,0,0);
insert into appointments (id,appointment_points,appointment_price,status_of_appointment,type_of_appointment) values (nextval('my_seq_appointment'),5,30,0,0);
insert into appointments (id,appointment_points,appointment_price,status_of_appointment,type_of_appointment) values (nextval('my_seq_appointment'),5,30,0,0);
insert into appointments (id,appointment_points,appointment_price,status_of_appointment,type_of_appointment) values (nextval('my_seq_appointment'),5,30,0,0);


insert into dermatologist_appointment (dermatologist_appointment_start_time,dermatologist_appointment_end_time,id,dermatologist_for_appointment_id,patient_with_dermatologist_appointment_id,pharmacy_for_dermatologist_appointment_id) values('2021-06-11 15:00','2021-06-11 15:30',1,8,1,1);
insert into dermatologist_appointment (dermatologist_appointment_start_time,dermatologist_appointment_end_time,id,dermatologist_for_appointment_id,patient_with_dermatologist_appointment_id,pharmacy_for_dermatologist_appointment_id) values('2021-02-04 10:35','2021-02-04 10:50',2,8,2,2);
insert into dermatologist_appointment (dermatologist_appointment_start_time,dermatologist_appointment_end_time,id,dermatologist_for_appointment_id,patient_with_dermatologist_appointment_id,pharmacy_for_dermatologist_appointment_id) values('2021-02-04 12:55','2021-02-04 15:50',6,8,1,2);

insert into dermatologist_appointment (dermatologist_appointment_start_time,dermatologist_appointment_end_time,id,dermatologist_for_appointment_id,patient_with_dermatologist_appointment_id,pharmacy_for_dermatologist_appointment_id) values('2021-04-11 15:00','2021-04-11 15:30',7,8,1 ,1);
insert into dermatologist_appointment (dermatologist_appointment_start_time,dermatologist_appointment_end_time,id,dermatologist_for_appointment_id,patient_with_dermatologist_appointment_id,pharmacy_for_dermatologist_appointment_id) values('2021-04-16 10:30','2021-04-16 11:00',8,8,1,1);
insert into dermatologist_appointment (dermatologist_appointment_start_time,dermatologist_appointment_end_time,id,dermatologist_for_appointment_id,patient_with_dermatologist_appointment_id,pharmacy_for_dermatologist_appointment_id) values('2021-04-16 12:30','2021-04-16 13:00',9,8,null,1);
insert into dermatologist_appointment (dermatologist_appointment_start_time,dermatologist_appointment_end_time,id,dermatologist_for_appointment_id,patient_with_dermatologist_appointment_id,pharmacy_for_dermatologist_appointment_id) values('2021-04-16 15:00','2021-04-16 15:15',10,8,null,1);
insert into dermatologist_appointment (dermatologist_appointment_start_time,dermatologist_appointment_end_time,id,dermatologist_for_appointment_id,patient_with_dermatologist_appointment_id,pharmacy_for_dermatologist_appointment_id) values('2021-05-16 10:35','2021-05-16 10:50',11,8,null,2);
insert into dermatologist_appointment (dermatologist_appointment_start_time,dermatologist_appointment_end_time,id,dermatologist_for_appointment_id,patient_with_dermatologist_appointment_id,pharmacy_for_dermatologist_appointment_id) values('2021-05-20 12:55','2021-05-08 15:50',12,8,null,2);



insert into pharmacist_appointment (pharmacist_appointment_duration,pharmacist_appointment_start_time,id,pharmacist_for_appointment_id,patient_with_pharmacist_appointment_id) values (10,'2021-04-17 19:05',3,6,1);
insert into pharmacist_appointment (pharmacist_appointment_duration,pharmacist_appointment_start_time,id,pharmacist_for_appointment_id,patient_with_pharmacist_appointment_id) values (10,'2021-04-15 19:20',4,6,1);
insert into pharmacist_appointment (pharmacist_appointment_duration,pharmacist_appointment_start_time,id,pharmacist_for_appointment_id,patient_with_pharmacist_appointment_id) values (10,'2021-04-15 19:35',5,6,1);


insert into medicine_reservations(id,patient_for_medicine_reservation_id,date_of_taking_medicine,pharmacy_for_medicine_reservation_id,status_of_medicine_reservation,reserved_medicine_id) values (nextval('my_seq_gen_medicine_reservation'),1,'2020-02-04',1,2,1);
insert into medicine_reservations(id,patient_for_medicine_reservation_id,date_of_taking_medicine,pharmacy_for_medicine_reservation_id,status_of_medicine_reservation,reserved_medicine_id) values (nextval('my_seq_gen_medicine_reservation'),1,'2021-02-10',1,2,2);
insert into medicine_reservations(id,patient_for_medicine_reservation_id,date_of_taking_medicine,pharmacy_for_medicine_reservation_id,status_of_medicine_reservation,reserved_medicine_id) values (nextval('my_seq_gen_medicine_reservation'),1,'2022-02-03',1,2,3);
insert into medicine_reservations(id,patient_for_medicine_reservation_id,date_of_taking_medicine,pharmacy_for_medicine_reservation_id,status_of_medicine_reservation,reserved_medicine_id) values (nextval('my_seq_gen_medicine_reservation'),1,'2023-02-03',1,2,4);
