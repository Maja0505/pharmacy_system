INSERT INTO users (id, email, password, first_name, last_name, phone_number, type_of_user,city,country,latitude,longitude,street_name,street_number,enabled,enable_login,is_first_login, last_password_reset_date) VALUES (nextval('my_seq_users'), 'patient1@gmail.com', '12345', 'Pera', 'Peric', '064000000', 2,'Chicago','USD', 41, 87, 'Fifth Ave','5A', true, true, false, '2021-01-01T00:58:53');
INSERT INTO users (id, email, password, first_name, last_name, phone_number, type_of_user,city,country,latitude,longitude,street_name,street_number,enabled,enable_login,is_first_login, last_password_reset_date) VALUES (nextval('my_seq_users'), 'patient2@gmail.com', '12345', 'Mika', 'Mikic', '064000000', 2,'Chicago','USD', 41, 87, 'Fifth Ave','5A', true, true, false, '2021-01-01T00:58:53');
INSERT INTO users (id, email, password, first_name, last_name, phone_number, type_of_user,city,country,latitude,longitude,street_name,street_number,enabled,enable_login,is_first_login, last_password_reset_date) VALUES (nextval('my_seq_users'), 'systemadmin1@gmail.com', '12345', 'Ivan', 'Ivanovic', '064000000', 0,'Chicago','USD', 41, 87, 'Fifth Ave','5A', true, true, false, '2021-01-01T00:58:53');
INSERT INTO users (id, email, password, first_name, last_name, phone_number, type_of_user,city,country,latitude,longitude,street_name,street_number,enabled,enable_login,is_first_login, last_password_reset_date) VALUES (nextval('my_seq_users'), 'pharmacyadmin1@gmail.com', '12345', 'Janko', 'Jankovic', '064000000', 1,'Chicago','USD', 41, 87, 'Fifth Ave','5A', true, true, false, '2021-01-01T00:58:53');
INSERT INTO users (id, email, password, first_name, last_name, phone_number, type_of_user,city,country,latitude,longitude,street_name,street_number,enabled,enable_login,is_first_login, last_password_reset_date) VALUES (nextval('my_seq_users'), 'pharmacyadmin2@gmail.com', '12345', 'Nikola', 'Nikolic', '064000000', 1,'Chicago','USD', 41, 87, 'Fifth Ave','5A', true, true, false, '2021-01-01T00:58:53');
INSERT INTO users (id, email, password, first_name, last_name, phone_number, type_of_user,city,country,latitude,longitude,street_name,street_number,enabled,enable_login,is_first_login, last_password_reset_date) VALUES (nextval('my_seq_users'), 'pharmacist1@gmail.com', '$2a$05$j41h6yKxMGORqXRsQuf8EO53aIDL8nd/MNZtIdsbl5zRShHJwt0Om', 'Jana', 'Jankovic', '064000000', 3,'Chicago','USD', 41, 87, 'Fifth Ave','5A', true, true, false, '2021-01-01T00:58:53');
INSERT INTO users (id, email, password, first_name, last_name, phone_number, type_of_user,city,country,latitude,longitude,street_name,street_number,enabled,enable_login,is_first_login, last_password_reset_date) VALUES (nextval('my_seq_users'), 'pharmacist2@gmail.com', '$2a$05$j41h6yKxMGORqXRsQuf8EO53aIDL8nd/MNZtIdsbl5zRShHJwt0Om', 'Nina', 'Ninkovic', '064000000', 3,'Chicago','USD', 41, 87, 'Fifth Ave','5A', true, true, false, '2021-01-01T00:58:53');
INSERT INTO users (id, email, password, first_name, last_name, phone_number, type_of_user,city,country,latitude,longitude,street_name,street_number,enabled,enable_login,is_first_login, last_password_reset_date) VALUES (nextval('my_seq_users'), 'dermatologist1@gmail.com', '$2a$05$j41h6yKxMGORqXRsQuf8EO53aIDL8nd/MNZtIdsbl5zRShHJwt0Om', 'Pavle', 'Pavlovic', '064000000', 4,'Chicago','USD', 41, 87, 'Fifth Ave','5A', true, true, false, '2021-01-01T00:58:53');
INSERT INTO users (id, email, password, first_name, last_name, phone_number, type_of_user,city,country,latitude,longitude,street_name,street_number,enabled,enable_login,is_first_login, last_password_reset_date) VALUES (nextval('my_seq_users'), 'dermatologist2@gmail.com', '$2a$05$j41h6yKxMGORqXRsQuf8EO53aIDL8nd/MNZtIdsbl5zRShHJwt0Om', 'Marko', 'Markovic', '064000000', 4,'Chicago','USD', 41, 87, 'Fifth Ave','5A', true, true, false, '2021-01-01T00:58:53');
INSERT INTO users (id, email, password, first_name, last_name, phone_number, type_of_user,city,country,latitude,longitude,street_name,street_number,enabled,enable_login,is_first_login, last_password_reset_date) VALUES (nextval('my_seq_users'), 'supplier1@gmail.com', '12345', 'Ana', 'Ivanovic', '064000000', 5,'Chicago','USD', 41, 87, 'Fifth Ave','5A', true, true, false, '2021-01-01T00:58:53');
INSERT INTO users (id, email, password, first_name, last_name, phone_number, type_of_user,city,country,latitude,longitude,street_name,street_number,enabled,enable_login,is_first_login, last_password_reset_date) VALUES (nextval('my_seq_users'), 'supplier2@gmail.com', '12345', 'Milan', 'Jankovic', '064001100', 5,'Subotica','Srbija', 211, 100, 'Kralja Milana Obrenovica','16A', true, true, false, '2021-01-01T00:58:53');


INSERT INTO system_admin (id) VALUES (3);

INSERT INTO pharmacies (id, city, country, latitude, longitude, street_name, street_number, pharmacy_average_rating, pharmacy_description, pharmacy_name) VALUES (nextval('my_seq_pharmacy'), 'Novi Sad', 'Srbija', 100, 200, 'Bore Prodanovica', '15A', 9.1, 'Apoteka za sve' , 'Apoteka1');
INSERT INTO pharmacies (id, city, country, latitude, longitude, street_name, street_number, pharmacy_average_rating, pharmacy_description, pharmacy_name) VALUES (nextval('my_seq_pharmacy'), 'Novi Sad', 'Srbija', 100, 200, 'Bore Prodanovica', '15A', 9.2, 'Apoteka za sve' , 'Apoteka2');
INSERT INTO pharmacies (id, city, country, latitude, longitude, street_name, street_number, pharmacy_average_rating, pharmacy_description, pharmacy_name) VALUES (nextval('my_seq_pharmacy'), 'Novi Sad', 'Srbija', 100, 200, 'Bore Prodanovica', '15A', 9.3, 'Apoteka nije za sve' , 'Apoteka3');

INSERT INTO price_lists (id, dermatologist_appointment_price_per_hour, pharmacist_appointment_price_per_hour, pharmacy_id) VALUES (nextval('my_seq_price_list'), 10, 20, 1);
INSERT INTO price_lists (id, dermatologist_appointment_price_per_hour, pharmacist_appointment_price_per_hour, pharmacy_id) VALUES (nextval('my_seq_price_list'), 10, 20, 2);
INSERT INTO price_lists (id, dermatologist_appointment_price_per_hour, pharmacist_appointment_price_per_hour, pharmacy_id) VALUES (nextval('my_seq_price_list'), 10, 20, 3);

INSERT INTO pharmacy_admins (id, pharmacy_for_pharmacy_admin_id) VALUES (4, 2);
INSERT INTO pharmacy_admins (id, pharmacy_for_pharmacy_admin_id) VALUES (5, 2);

INSERT INTO dermatologists (id,dermatologist_average_rating) VALUES (8,4.3);
INSERT INTO dermatologists (id,dermatologist_average_rating) VALUES (9,2.3);

INSERT INTO pharmacists (id,pharmacist_average_rating,pharmacy_for_pharmacist_id) VALUES (6,4.3,1);
INSERT INTO pharmacists (id,pharmacist_average_rating,pharmacy_for_pharmacist_id) VALUES (7,4.3,2);

insert into worker_schedule (id,dermatologist_id,pharmacy_id,type_of_worker_schedule) values (1,8,1,1);
insert into worker_schedule (id,dermatologist_id,pharmacy_id,type_of_worker_schedule) values (2,8,2,0);
insert into worker_schedule (id,pharmacy_id,pharmacist_id,type_of_worker_schedule) values (3,1,6,1);
insert into worker_schedule (id,pharmacy_id,pharmacist_id,type_of_worker_schedule) values (4,2,7,1);


INSERT INTO working_hours (id, status_of_working_hours,working_end_time,working_start_time,worker_schedule_id) VALUES (1,1,'2021-05-13 20:00','2021-05-13 10:50',1);
INSERT INTO working_hours (id, status_of_working_hours,working_end_time,working_start_time,worker_schedule_id) VALUES (2,1,'2021-05-14 20:00','2021-05-14 09:50',1);
INSERT INTO working_hours (id, status_of_working_hours,working_end_time,working_start_time,worker_schedule_id) VALUES (3,1,'2021-05-15 18:00','2021-05-15 08:50',1);
INSERT INTO working_hours (id, status_of_working_hours,working_end_time,working_start_time,worker_schedule_id) VALUES (4,1,'2021-05-16 16:00','2021-05-16 08:30',1);
INSERT INTO working_hours (id, status_of_working_hours,working_end_time,working_start_time,worker_schedule_id) VALUES (5,1,'2021-05-17 20:00','2021-05-17 10:00',1);
INSERT INTO working_hours (id, status_of_working_hours,working_end_time,working_start_time,worker_schedule_id) VALUES (6,1,'2021-05-18 21:00','2021-05-18 11:00',1);
INSERT INTO working_hours (id, status_of_working_hours,working_end_time,working_start_time,worker_schedule_id) VALUES (7,1,'2021-05-19 22:00','2021-05-19 12:00',1);
INSERT INTO working_hours (id, status_of_working_hours,working_end_time,working_start_time,worker_schedule_id) VALUES (8,1,'2021-05-20 21:10','2021-05-20 13:50',1);
INSERT INTO working_hours (id, status_of_working_hours,working_end_time,working_start_time,worker_schedule_id) VALUES (9,1,'2021-05-21 20:00','2021-05-21 11:50',1);
INSERT INTO working_hours (id, status_of_working_hours,working_end_time,working_start_time,worker_schedule_id) VALUES (10,1,'2021-05-22 20:00','2021-05-22 15:50',1);
INSERT INTO working_hours (id, status_of_working_hours,working_end_time,working_start_time,worker_schedule_id) VALUES (11,1,'2021-05-23 20:00','2021-05-23 10:50',1);
INSERT INTO working_hours (id, status_of_working_hours,working_end_time,working_start_time,worker_schedule_id) VALUES (12,1,'2021-05-15 21:00','2021-05-15 12:50',2);
INSERT INTO working_hours (id, status_of_working_hours,working_end_time,working_start_time,worker_schedule_id) VALUES (13,1,'2021-05-16 19:00','2021-05-16 14:50',2);
INSERT INTO working_hours (id, status_of_working_hours,working_end_time,working_start_time,worker_schedule_id) VALUES (14,1,'2021-05-17 18:00','2021-05-17 10:50',2);

INSERT INTO working_hours (id, status_of_working_hours,working_end_time,working_start_time,worker_schedule_id) VALUES (15,1,'2021-05-15 23:00','2021-05-15 09:50',3);
INSERT INTO working_hours (id, status_of_working_hours,working_end_time,working_start_time,worker_schedule_id) VALUES (16,1,'2021-05-16 21:00','2021-05-16 11:50',3);
INSERT INTO working_hours (id, status_of_working_hours,working_end_time,working_start_time,worker_schedule_id) VALUES (17,1,'2021-05-17 20:00','2021-05-17 10:50',3);
INSERT INTO working_hours (id, status_of_working_hours,working_end_time,working_start_time,worker_schedule_id) VALUES (18,1,'2021-05-18 18:00','2021-05-18 08:50',3);
INSERT INTO working_hours (id, status_of_working_hours,working_end_time,working_start_time,worker_schedule_id) VALUES (19,1,'2021-05-19 15:00','2021-05-19 07:50',3);
INSERT INTO working_hours (id, status_of_working_hours,working_end_time,working_start_time,worker_schedule_id) VALUES (20,1,'2021-05-20 23:00','2021-05-20 12:50',3);
INSERT INTO working_hours (id, status_of_working_hours,working_end_time,working_start_time,worker_schedule_id) VALUES (21,1,'2021-05-21 23:00','2021-05-21 13:50',3);
INSERT INTO working_hours (id, status_of_working_hours,working_end_time,working_start_time,worker_schedule_id) VALUES (22,1,'2021-05-22 18:00','2021-05-22 15:50',3);
INSERT INTO working_hours (id, status_of_working_hours,working_end_time,working_start_time,worker_schedule_id) VALUES (23,1,'2021-05-23 22:00','2021-05-23 10:50',3);
INSERT INTO working_hours (id, status_of_working_hours,working_end_time,working_start_time,worker_schedule_id) VALUES (24,1,'2021-05-29 22:00','2021-05-29 10:50',3);


INSERT INTO working_hours (id, status_of_working_hours,working_end_time,working_start_time,worker_schedule_id) VALUES (25,1,'2021-05-15 23:00','2021-05-15 10:50',4);


INSERT INTO patient (id, category_of_patient, patient_points, penalty) VALUES (1, 0, 0.0, 2);
INSERT INTO patient (id, category_of_patient, patient_points, penalty) VALUES (2, 1, 1.0, 0);

insert into ingredient (id, name_of_ingredient) values (nextval('my_seq_ingredient'),'BC34');
insert into ingredient (id, name_of_ingredient) values (nextval('my_seq_ingredient'),'AAAA');
insert into ingredient (id, name_of_ingredient) values (nextval('my_seq_ingredient'),'XXXXX');
insert into ingredient (id, name_of_ingredient) values (nextval('my_seq_ingredient'),'YYYYYYYY');
insert into ingredient (id, name_of_ingredient) values (nextval('my_seq_ingredient'),'NNNNNN');



insert into medicine_infos (id, form_of_medicine, manufacturer_of_medicine, medicine_code, medicine_name, notes, prescription_regime, type_of_medicine) values (nextval('my_seq_medicine_info'),1,'Medic Pharmacy', '211233', 'Andol','Familijooo',1,1);
insert into medicine_infos (id, form_of_medicine, manufacturer_of_medicine, medicine_code, medicine_name, notes, prescription_regime, type_of_medicine) values (nextval('my_seq_medicine_info'),0,'Medic Pharmacy', '211234', 'Apaurin','Brapoooooooo',1,1);
insert into medicine_infos (id, form_of_medicine, manufacturer_of_medicine, medicine_code, medicine_name, notes, prescription_regime, type_of_medicine) values (nextval('my_seq_medicine_info'),1,'Medic Pharmacy', '211235', 'Brufen','Familijooo',1,1);
insert into medicine_infos (id, form_of_medicine, manufacturer_of_medicine, medicine_code, medicine_name, notes, prescription_regime, type_of_medicine) values (nextval('my_seq_medicine_info'),0,'Medic Pharmacy', '211236', 'Bromic','Brapoooooooo',1,1);


insert into medicine (medicine_average_rating, id) values (5.0,1);
insert into medicine (medicine_average_rating, id) values (4.9,2);
insert into medicine (medicine_average_rating, id) values (5.0,3);
insert into medicine (medicine_average_rating, id) values (4.9,4);

insert into medicine_infos_ingredients (medicine_info_id, ingredients_id) values (1,1);
insert into medicine_infos_ingredients (medicine_info_id, ingredients_id) values (1,2);
insert into medicine_infos_ingredients (medicine_info_id, ingredients_id) values (1,3);
insert into medicine_infos_ingredients (medicine_info_id, ingredients_id) values (1,4);
insert into medicine_infos_ingredients (medicine_info_id, ingredients_id) values (2,5);





insert into medicine_infos_alternative_medicines (medicine_info_id,alternative_medicines_id) values (1,2);
insert into medicine_infos_alternative_medicines (medicine_info_id,alternative_medicines_id) values (1,3);
insert into medicine_infos_alternative_medicines (medicine_info_id,alternative_medicines_id) values (2,1);
insert into medicine_infos_alternative_medicines (medicine_info_id,alternative_medicines_id) values (2,3);
insert into medicine_infos_alternative_medicines (medicine_info_id,alternative_medicines_id) values (2,4);
insert into medicine_infos_alternative_medicines (medicine_info_id,alternative_medicines_id) values (3,4);


insert into patient_medicine_allergies (patient_id,medicine_allergies_id) values (1,1);
insert into patient_medicine_allergies (patient_id,medicine_allergies_id) values (1,2);

insert into suppliers (id) values (10);
insert into suppliers (id) values (11);

/*pharmacist appointment*/
insert into appointments (id,appointment_points,appointment_price,status_of_appointment,type_of_appointment) values (nextval('my_seq_appointment'),5,13,2,1);
insert into appointments (id,appointment_points,appointment_price,status_of_appointment,type_of_appointment) values (nextval('my_seq_appointment'),5,40,3,1);
insert into appointments (id,appointment_points,appointment_price,status_of_appointment,type_of_appointment) values (nextval('my_seq_appointment'),5,50,3,1);

/*dermatologist missed appointment*/
insert into appointments (id,appointment_points,appointment_price,status_of_appointment,type_of_appointment) values (nextval('my_seq_appointment'),5,120,2,0);
insert into appointments (id,appointment_points,appointment_price,status_of_appointment,type_of_appointment) values (nextval('my_seq_appointment'),5,15,2,0);

/*dermatologist expired appointment*/
insert into appointments (id,appointment_points,appointment_price,status_of_appointment,type_of_appointment) values (nextval('my_seq_appointment'),5,133,3,0);
insert into appointments (id,appointment_points,appointment_price,status_of_appointment,type_of_appointment) values (nextval('my_seq_appointment'),5,15,3,0);
insert into appointments (id,appointment_points,appointment_price,status_of_appointment,type_of_appointment) values (nextval('my_seq_appointment'),5,133,3,0);
insert into appointments (id,appointment_points,appointment_price,status_of_appointment,type_of_appointment) values (nextval('my_seq_appointment'),5,31,3,0);

/*dermatologist reserved appointment*/
insert into appointments (id,appointment_points,appointment_price,status_of_appointment,type_of_appointment) values (nextval('my_seq_appointment'),5,133,1,0);
insert into appointments (id,appointment_points,appointment_price,status_of_appointment,type_of_appointment) values (nextval('my_seq_appointment'),5,31,1,0);


/*dermatologist open appointment*/
insert into appointments (id,appointment_points,appointment_price,status_of_appointment,type_of_appointment) values (nextval('my_seq_appointment'),5,330,0,0);
insert into appointments (id,appointment_points,appointment_price,status_of_appointment,type_of_appointment) values (nextval('my_seq_appointment'),5,10,0,0);
insert into appointments (id,appointment_points,appointment_price,status_of_appointment,type_of_appointment) values (nextval('my_seq_appointment'),5,54,0,0);

/*pharmacist reserved appointment*/
insert into appointments (id,appointment_points,appointment_price,status_of_appointment,type_of_appointment) values (nextval('my_seq_appointment'),5,13,1,1);
insert into appointments (id,appointment_points,appointment_price,status_of_appointment,type_of_appointment) values (nextval('my_seq_appointment'),5,40,1,1);
insert into appointments (id,appointment_points,appointment_price,status_of_appointment,type_of_appointment) values (nextval('my_seq_appointment'),5,50,1,1);




insert into dermatologist_appointment (dermatologist_appointment_start_time,dermatologist_appointment_end_time,id,dermatologist_for_appointment_id,patient_with_dermatologist_appointment_id,pharmacy_for_dermatologist_appointment_id) values('2021-05-11 15:00','2021-05-11 15:30',4,8,1,2);
insert into dermatologist_appointment (dermatologist_appointment_start_time,dermatologist_appointment_end_time,id,dermatologist_for_appointment_id,patient_with_dermatologist_appointment_id,pharmacy_for_dermatologist_appointment_id) values('2021-05-09 10:35','2021-05-09 10:50',5,8,2,2);
insert into dermatologist_appointment (dermatologist_appointment_start_time,dermatologist_appointment_end_time,id,dermatologist_for_appointment_id,patient_with_dermatologist_appointment_id,pharmacy_for_dermatologist_appointment_id) values('2021-05-10 12:55','2021-05-10 15:50',6,8,1,1);
insert into dermatologist_appointment (dermatologist_appointment_start_time,dermatologist_appointment_end_time,id,dermatologist_for_appointment_id,patient_with_dermatologist_appointment_id,pharmacy_for_dermatologist_appointment_id) values('2021-05-11 14:00','2021-05-11 14:20',7,8,2,1);
insert into dermatologist_appointment (dermatologist_appointment_start_time,dermatologist_appointment_end_time,id,dermatologist_for_appointment_id,patient_with_dermatologist_appointment_id,pharmacy_for_dermatologist_appointment_id) values('2021-05-21 12:30','2021-05-21 16:30',8,8,1,1);
insert into dermatologist_appointment (dermatologist_appointment_start_time,dermatologist_appointment_end_time,id,dermatologist_for_appointment_id,patient_with_dermatologist_appointment_id,pharmacy_for_dermatologist_appointment_id) values('2021-05-23 12:30','2021-05-23 13:00',9,8,1,1);
insert into dermatologist_appointment (dermatologist_appointment_start_time,dermatologist_appointment_end_time,id,dermatologist_for_appointment_id,patient_with_dermatologist_appointment_id,pharmacy_for_dermatologist_appointment_id) values('2021-05-21 12:30','2021-05-21 19:30',10,8,2,2);
insert into dermatologist_appointment (dermatologist_appointment_start_time,dermatologist_appointment_end_time,id,dermatologist_for_appointment_id,patient_with_dermatologist_appointment_id,pharmacy_for_dermatologist_appointment_id) values('2021-05-24 15:30','2021-05-24 17:00',11,8,1,1);
insert into dermatologist_appointment (dermatologist_appointment_start_time,dermatologist_appointment_end_time,id,dermatologist_for_appointment_id,patient_with_dermatologist_appointment_id,pharmacy_for_dermatologist_appointment_id) values('2021-05-23 15:00','2021-05-23 15:15',12,8,null,1);
insert into dermatologist_appointment (dermatologist_appointment_start_time,dermatologist_appointment_end_time,id,dermatologist_for_appointment_id,patient_with_dermatologist_appointment_id,pharmacy_for_dermatologist_appointment_id) values('2021-05-21 10:35','2021-05-21 10:50',13,8,null,1);
insert into dermatologist_appointment (dermatologist_appointment_start_time,dermatologist_appointment_end_time,id,dermatologist_for_appointment_id,patient_with_dermatologist_appointment_id,pharmacy_for_dermatologist_appointment_id) values('2021-05-22 14:55','2021-05-22 15:50',14,8,null,1);




insert into pharmacist_appointment (pharmacist_appointment_duration,pharmacist_appointment_start_time,id,pharmacist_for_appointment_id,patient_with_pharmacist_appointment_id) values (16,'2021-05-22 19:05',1,6,1);
insert into pharmacist_appointment (pharmacist_appointment_duration,pharmacist_appointment_start_time,id,pharmacist_for_appointment_id,patient_with_pharmacist_appointment_id) values (20,'2021-05-25 19:20',2,6,2);
insert into pharmacist_appointment (pharmacist_appointment_duration,pharmacist_appointment_start_time,id,pharmacist_for_appointment_id,patient_with_pharmacist_appointment_id) values (45,'2021-05-16 14:35',3,7,1);

insert into pharmacist_appointment (pharmacist_appointment_duration,pharmacist_appointment_start_time,id,pharmacist_for_appointment_id,patient_with_pharmacist_appointment_id) values (40,'2021-05-24 19:05',15,6,1);
insert into pharmacist_appointment (pharmacist_appointment_duration,pharmacist_appointment_start_time,id,pharmacist_for_appointment_id,patient_with_pharmacist_appointment_id) values (50,'2021-05-25 19:20',16,6,2);
insert into pharmacist_appointment (pharmacist_appointment_duration,pharmacist_appointment_start_time,id,pharmacist_for_appointment_id,patient_with_pharmacist_appointment_id) values (75,'2021-05-21 14:35',17,6,1);



insert into medicine_reservations(id,patient_for_medicine_reservation_id,date_of_taking_medicine,pharmacy_for_medicine_reservation_id,status_of_medicine_reservation,reserved_medicine_id) values (nextval('my_seq_gen_medicine_reservation'),1,'2021-05-15',1,2,1);
insert into medicine_reservations(id,patient_for_medicine_reservation_id,date_of_taking_medicine,pharmacy_for_medicine_reservation_id,status_of_medicine_reservation,reserved_medicine_id) values (nextval('my_seq_gen_medicine_reservation'),1,'2021-05-23',1,2,2);
insert into medicine_reservations(id,patient_for_medicine_reservation_id,date_of_taking_medicine,pharmacy_for_medicine_reservation_id,status_of_medicine_reservation,reserved_medicine_id) values (nextval('my_seq_gen_medicine_reservation'),1,'2021-05-23',1,2,3);
insert into medicine_reservations(id,patient_for_medicine_reservation_id,date_of_taking_medicine,pharmacy_for_medicine_reservation_id,status_of_medicine_reservation,reserved_medicine_id) values (nextval('my_seq_gen_medicine_reservation'),1,'2021-05-21',2,2,4);


insert into storages(id,type_of_storage) values (nextval('my_seq_storage'),1);

insert into pharmacy_storages(id,pharmacy_id) values (1,1);

insert into items(id,medicine_amount,type_of_item,medicine_item_id) values (nextval('my_seq_item'),30,4,1);
insert into items(id,medicine_amount,type_of_item,medicine_item_id) values (nextval('my_seq_item'),10,4,2);
insert into items(id,medicine_amount,type_of_item,medicine_item_id) values (nextval('my_seq_item'),45,4,3);
insert into items(id,medicine_amount,type_of_item,medicine_item_id) values (nextval('my_seq_item'),5,4,4);

/*type:EPrescription*/
insert into items(id,medicine_amount,type_of_item,medicine_item_id) values (nextval('my_seq_item'),5,0,4);
insert into items(id,medicine_amount,type_of_item,medicine_item_id) values (nextval('my_seq_item'),5,0,3);


insert into e_prescriptions(id,creation_date,patient_foreprescription_id) values (1,'2021-05-16 19:05',1);

insert into eprescription_items(id,e_prescriton_id) values (5,1);
insert into eprescription_items(id,e_prescriton_id) values (6,1);

insert into pharmacy_storage_items(id,pharmacy_storage_with_item_id) values (1,1);
insert into pharmacy_storage_items(id,pharmacy_storage_with_item_id) values (2,1);
insert into pharmacy_storage_items(id,pharmacy_storage_with_item_id) values (3,1);
insert into pharmacy_storage_items(id,pharmacy_storage_with_item_id) values (4,1);


insert into medicine_price(id,medicine_price,medicine_with_prices_id,price_list_for_medicine_id) values (nextval('my_seq_medicine_price'),200,1,1);

INSERT INTO autorizations (id, name) values (nextval('my_seq_authority'), 'ROLE_ADMINP');
INSERT INTO autorizations (id, name) values (nextval('my_seq_authority'), 'ROLE_ADMINS');
INSERT INTO autorizations (id, name) values (nextval('my_seq_authority'), 'ROLE_SUPPLIER');
INSERT INTO autorizations (id, name) values (nextval('my_seq_authority'), 'ROLE_PATIENT');
INSERT INTO autorizations (id, name) values (nextval('my_seq_authority'), 'ROLE_PHARMACIST');
INSERT INTO autorizations (id, name) values (nextval('my_seq_authority'), 'ROLE_DERMATOLOGIST');

insert into users_authorities(users_id,authorities_id) values(6,5);
insert into users_authorities(users_id,authorities_id) values(7,5);
insert into users_authorities(users_id,authorities_id) values(8,6);
insert into users_authorities(users_id,authorities_id) values(9,6);


insert into dermatologist_reports(id,report_infos,dermatologist_appointment_id,recipe_for_dermatologist_report_id) values (nextval('my_seq_dermatologist_report'),'tralala',7,null);
insert into dermatologist_reports(id,report_infos,dermatologist_appointment_id,recipe_for_dermatologist_report_id) values (nextval('my_seq_dermatologist_report'),'caocao',8,null);

insert into pharmacist_reports(id,report_infos,pharmacist_appointment_id,recipe_for_pharmacist_report_id) values (nextval('my_seq_pharmacist_report'),'ttttt',3,null);
insert into pharmacist_reports(id,report_infos,pharmacist_appointment_id,recipe_for_pharmacist_report_id) values (nextval('my_seq_pharmacist_report'),'ccc',1,null);

insert into patient_pharmacies_subscription(patient_id,pharmacies_subscription_id) values (1,1);
insert into patient_pharmacies_subscription(patient_id,pharmacies_subscription_id) values (1,2);

insert into pharmacies_dermatologists_in_pharmacy(pharmacy_id,dermatologists_in_pharmacy_id) values (1,8);
insert into pharmacies_dermatologists_in_pharmacy(pharmacy_id,dermatologists_in_pharmacy_id) values (2,8);


