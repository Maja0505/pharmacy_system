/*password : 12345*/
INSERT INTO users (id, email, password, first_name, last_name, phone_number, type_of_user,city,country,latitude,longitude,street_name,street_number,enabled,enable_login,is_first_login, last_password_reset_date,version) VALUES (nextval('my_seq_users'), 'patient1@gmail.com', '$2a$05$j41h6yKxMGORqXRsQuf8EO53aIDL8nd/MNZtIdsbl5zRShHJwt0Om', 'Pera', 'Peric', '064000000', 2,'Chicago','USD', 41, 87, 'Fifth Ave','5A', true, true, true, '2021-01-01T00:58:53',1);
INSERT INTO users (id, email, password, first_name, last_name, phone_number, type_of_user,city,country,latitude,longitude,street_name,street_number,enabled,enable_login,is_first_login, last_password_reset_date,version) VALUES (nextval('my_seq_users'), 'patient2@gmail.com', '$2a$05$j41h6yKxMGORqXRsQuf8EO53aIDL8nd/MNZtIdsbl5zRShHJwt0Om', 'Mika', 'Mikic', '064000000', 2,'Chicago','USD', 41, 87, 'Fifth Ave','5A', true, true, true, '2021-01-01T00:58:53',1);
INSERT INTO users (id, email, password, first_name, last_name, phone_number, type_of_user,city,country,latitude,longitude,street_name,street_number,enabled,enable_login,is_first_login, last_password_reset_date,version) VALUES (nextval('my_seq_users'), 'systemadmin1@gmail.com', '12345', 'Ivan', 'Ivanovic', '064000000', 0,'Chicago','USD', 41, 87, 'Fifth Ave','5A', true, true, true, '2021-01-01T00:58:53',1);
INSERT INTO users (id, email, password, first_name, last_name, phone_number, type_of_user,city,country,latitude,longitude,street_name,street_number,enabled,enable_login,is_first_login, last_password_reset_date,version) VALUES (nextval('my_seq_users'), 'pharmacyadmin1@gmail.com', '12345', 'Janko', 'Jankovic', '064000000', 1,'Chicago','USD', 41, 87, 'Fifth Ave','5A', true, true, true, '2021-01-01T00:58:53',1);
INSERT INTO users (id, email, password, first_name, last_name, phone_number, type_of_user,city,country,latitude,longitude,street_name,street_number,enabled,enable_login,is_first_login, last_password_reset_date,version) VALUES (nextval('my_seq_users'), 'pharmacyadmin2@gmail.com', '12345', 'Nikola', 'Nikolic', '064000000', 1,'Chicago','USD', 41, 87, 'Fifth Ave','5A', true, true, true, '2021-01-01T00:58:53',1);
INSERT INTO users (id, email, password, first_name, last_name, phone_number, type_of_user,city,country,latitude,longitude,street_name,street_number,enabled,enable_login,is_first_login, last_password_reset_date,version) VALUES (nextval('my_seq_users'), 'pharmacist1@gmail.com', '$2a$05$j41h6yKxMGORqXRsQuf8EO53aIDL8nd/MNZtIdsbl5zRShHJwt0Om', 'Jana', 'Jankovic', '064000000', 3,'Chicago','USD', 41, 87, 'Fifth Ave','5A', true, true, true, '2021-01-01T00:58:53',1);
INSERT INTO users (id, email, password, first_name, last_name, phone_number, type_of_user,city,country,latitude,longitude,street_name,street_number,enabled,enable_login,is_first_login, last_password_reset_date,version) VALUES (nextval('my_seq_users'), 'pharmacist2@gmail.com', '$2a$05$j41h6yKxMGORqXRsQuf8EO53aIDL8nd/MNZtIdsbl5zRShHJwt0Om', 'Nina', 'Ninkovic', '064000000', 3,'Chicago','USD', 41, 87, 'Fifth Ave','5A', true, true, true, '2021-01-01T00:58:53',1);
INSERT INTO users (id, email, password, first_name, last_name, phone_number, type_of_user,city,country,latitude,longitude,street_name,street_number,enabled,enable_login,is_first_login, last_password_reset_date,version) VALUES (nextval('my_seq_users'), 'dermatologist1@gmail.com', '$2a$05$j41h6yKxMGORqXRsQuf8EO53aIDL8nd/MNZtIdsbl5zRShHJwt0Om', 'Pavle', 'Pavlovic', '064000000', 4,'Chicago','USD', 41, 87, 'Fifth Ave','5A', true, true, true, '2021-01-01T00:58:53',1);
INSERT INTO users (id, email, password, first_name, last_name, phone_number, type_of_user,city,country,latitude,longitude,street_name,street_number,enabled,enable_login,is_first_login, last_password_reset_date,version) VALUES (nextval('my_seq_users'), 'dermatologist2@gmail.com', '$2a$05$j41h6yKxMGORqXRsQuf8EO53aIDL8nd/MNZtIdsbl5zRShHJwt0Om', 'Marko', 'Markovic', '064000000', 4,'Chicago','USD', 41, 87, 'Fifth Ave','5A', true, true, true, '2021-01-01T00:58:53',1);
INSERT INTO users (id, email, password, first_name, last_name, phone_number, type_of_user,city,country,latitude,longitude,street_name,street_number,enabled,enable_login,is_first_login, last_password_reset_date,version) VALUES (nextval('my_seq_users'), 'supplier1@gmail.com', '12345', 'Ana', 'Ivanovic', '064000000', 5,'Chicago','USD', 41, 87, 'Fifth Ave','5A', true, true, true, '2021-01-01T00:58:53',1);
INSERT INTO users (id, email, password, first_name, last_name, phone_number, type_of_user,city,country,latitude,longitude,street_name,street_number,enabled,enable_login,is_first_login, last_password_reset_date,version) VALUES (nextval('my_seq_users'), 'supplier2@gmail.com', '12345', 'Milan', 'Jankovic', '064001100', 5,'Subotica','Srbija', 211, 100, 'Kralja Milana Obrenovica','16A', true, true, true, '2021-01-01T00:58:53',1);


INSERT INTO system_admin (id) VALUES (3);

INSERT INTO pharmacies (id, city, country, latitude, longitude, street_name, street_number, pharmacy_average_rating, pharmacy_description, pharmacy_name) VALUES (nextval('my_seq_pharmacy'), 'Novi Sad', 'Srbija', 100, 200, 'Bulevar Mihajla Pupina', '9', 0, 'Apoteka za sve' , 'Benu');
INSERT INTO pharmacies (id, city, country, latitude, longitude, street_name, street_number, pharmacy_average_rating, pharmacy_description, pharmacy_name) VALUES (nextval('my_seq_pharmacy'), 'Beograd', 'Srbija', 100, 200, 'Resavska', '76', 0, 'Apoteka za sve' , 'Ivancic i sinovi');

INSERT INTO price_lists (id, dermatologist_appointment_price_per_hour, pharmacist_appointment_price_per_hour, pharmacy_id) VALUES (nextval('my_seq_price_list'), 10, 20, 1);
INSERT INTO price_lists (id, dermatologist_appointment_price_per_hour, pharmacist_appointment_price_per_hour, pharmacy_id) VALUES (nextval('my_seq_price_list'), 15, 25, 2);

INSERT INTO pharmacy_admins (id, pharmacy_for_pharmacy_admin_id) VALUES (4, 1);
INSERT INTO pharmacy_admins (id, pharmacy_for_pharmacy_admin_id) VALUES (5, 2);

INSERT INTO dermatologists (id,dermatologist_average_rating) VALUES (8,0);
INSERT INTO dermatologists (id,dermatologist_average_rating) VALUES (9,0);

insert into pharmacies_dermatologists_in_pharmacy(pharmacy_id,dermatologists_in_pharmacy_id) values (1,8);
insert into pharmacies_dermatologists_in_pharmacy(pharmacy_id,dermatologists_in_pharmacy_id) values (2,8);
insert into pharmacies_dermatologists_in_pharmacy(pharmacy_id,dermatologists_in_pharmacy_id) values (1,9);

INSERT INTO pharmacists (id,pharmacist_average_rating,pharmacy_for_pharmacist_id) VALUES (6,0,1);
INSERT INTO pharmacists (id,pharmacist_average_rating,pharmacy_for_pharmacist_id) VALUES (7,0,2);

INSERT INTO patient (id, category_of_patient, patient_points, penalty) VALUES (1, 0, 0.0, 0);
INSERT INTO patient (id, category_of_patient, patient_points, penalty) VALUES (2, 0, 0.0, 0);

insert into worker_schedule (id,dermatologist_id,pharmacy_id,type_of_worker_schedule) values (1,8,1,0);
insert into worker_schedule (id,dermatologist_id,pharmacy_id,type_of_worker_schedule) values (2,8,2,0);
insert into worker_schedule (id,dermatologist_id,pharmacy_id,type_of_worker_schedule) values (3,9,1,0);
insert into worker_schedule (id,pharmacist_id,pharmacy_id,type_of_worker_schedule) values (4,6,1,1);
insert into worker_schedule (id,pharmacist_id,pharmacy_id,type_of_worker_schedule) values (5,7,2,1);


/*za dermatologa 8 u apoteci 1*/
INSERT INTO working_hours (id, status_of_working_hours,working_end_time,working_start_time,worker_schedule_id) VALUES (1,1,'2021-06-08 20:00','2021-06-08 08:00',1);
INSERT INTO working_hours (id, status_of_working_hours,working_end_time,working_start_time,worker_schedule_id) VALUES (2,1,'2021-06-09 20:00','2021-06-09 08:00',1);
INSERT INTO working_hours (id, status_of_working_hours,working_end_time,working_start_time,worker_schedule_id) VALUES (3,1,'2021-06-10 20:00','2021-06-10 08:00',1);
INSERT INTO working_hours (id, status_of_working_hours,working_end_time,working_start_time,worker_schedule_id) VALUES (4,1,'2021-06-11 20:00','2021-06-11 08:00',1);
INSERT INTO working_hours (id, status_of_working_hours,working_end_time,working_start_time,worker_schedule_id) VALUES (5,1,'2021-06-12 20:00','2021-06-12 08:00',1);
INSERT INTO working_hours (id, status_of_working_hours,working_end_time,working_start_time,worker_schedule_id) VALUES (6,1,'2021-06-13 20:00','2021-06-13 08:00',1);
INSERT INTO working_hours (id, status_of_working_hours,working_end_time,working_start_time,worker_schedule_id) VALUES (7,1,'2021-06-14 20:00','2021-06-14 08:00',1);
INSERT INTO working_hours (id, status_of_working_hours,working_end_time,working_start_time,worker_schedule_id) VALUES (8,1,'2021-06-15 20:00','2021-06-15 08:00',1);
INSERT INTO working_hours (id, status_of_working_hours,working_end_time,working_start_time,worker_schedule_id) VALUES (9,1,'2021-06-16 20:00','2021-06-16 08:00',1);
INSERT INTO working_hours (id, status_of_working_hours,working_end_time,working_start_time,worker_schedule_id) VALUES (10,1,'2021-06-17 20:00','2021-06-17 08:00',1);
INSERT INTO working_hours (id, status_of_working_hours,working_end_time,working_start_time,worker_schedule_id) VALUES (11,1,'2021-06-18 20:00','2021-06-18 08:00',1);
INSERT INTO working_hours (id, status_of_working_hours,working_end_time,working_start_time,worker_schedule_id) VALUES (12,1,'2021-06-19 20:00','2021-06-19 08:00',1);

/*dermatolog 8 u apoteci 2*/
INSERT INTO working_hours (id, status_of_working_hours,working_end_time,working_start_time,worker_schedule_id) VALUES (13,1,'2021-06-20 20:00','2021-06-20 08:00',2);
INSERT INTO working_hours (id, status_of_working_hours,working_end_time,working_start_time,worker_schedule_id) VALUES (14,1,'2021-06-21 20:00','2021-06-21 08:00',2);
INSERT INTO working_hours (id, status_of_working_hours,working_end_time,working_start_time,worker_schedule_id) VALUES (15,1,'2021-06-22 20:00','2021-06-22 08:00',2);
INSERT INTO working_hours (id, status_of_working_hours,working_end_time,working_start_time,worker_schedule_id) VALUES (16,1,'2021-06-23 20:00','2021-06-23 08:00',2);
INSERT INTO working_hours (id, status_of_working_hours,working_end_time,working_start_time,worker_schedule_id) VALUES (17,1,'2021-06-24 20:00','2021-06-24 08:00',2);
INSERT INTO working_hours (id, status_of_working_hours,working_end_time,working_start_time,worker_schedule_id) VALUES (18,1,'2021-06-25 20:00','2021-06-25 08:00',2);

/*dermatolog 9 u apoteci 1*/
INSERT INTO working_hours (id, status_of_working_hours,working_end_time,working_start_time,worker_schedule_id) VALUES (19,1,'2021-06-10 20:00','2021-06-10 08:00',3);
INSERT INTO working_hours (id, status_of_working_hours,working_end_time,working_start_time,worker_schedule_id) VALUES (20,1,'2021-06-11 20:00','2021-06-11 08:00',3);
INSERT INTO working_hours (id, status_of_working_hours,working_end_time,working_start_time,worker_schedule_id) VALUES (21,1,'2021-06-12 20:00','2021-06-12 08:00',3);

/*za farmaceuta 6 u apoteci 1*/
INSERT INTO working_hours (id, status_of_working_hours,working_end_time,working_start_time,worker_schedule_id) VALUES (22,1,'2021-06-08 20:00','2021-06-08 08:00',4);
INSERT INTO working_hours (id, status_of_working_hours,working_end_time,working_start_time,worker_schedule_id) VALUES (23,1,'2021-06-09 20:00','2021-06-09 08:00',4);
INSERT INTO working_hours (id, status_of_working_hours,working_end_time,working_start_time,worker_schedule_id) VALUES (24,1,'2021-06-10 20:00','2021-06-10 08:00',4);
INSERT INTO working_hours (id, status_of_working_hours,working_end_time,working_start_time,worker_schedule_id) VALUES (25,1,'2021-06-11 20:00','2021-06-11 08:00',4);
INSERT INTO working_hours (id, status_of_working_hours,working_end_time,working_start_time,worker_schedule_id) VALUES (26,1,'2021-06-12 20:00','2021-06-12 08:00',4);
INSERT INTO working_hours (id, status_of_working_hours,working_end_time,working_start_time,worker_schedule_id) VALUES (27,1,'2021-06-13 20:00','2021-06-13 08:00',4);
INSERT INTO working_hours (id, status_of_working_hours,working_end_time,working_start_time,worker_schedule_id) VALUES (28,1,'2021-06-14 20:00','2021-06-14 08:00',4);
INSERT INTO working_hours (id, status_of_working_hours,working_end_time,working_start_time,worker_schedule_id) VALUES (29,1,'2021-06-15 20:00','2021-06-15 08:00',4);
INSERT INTO working_hours (id, status_of_working_hours,working_end_time,working_start_time,worker_schedule_id) VALUES (30,1,'2021-06-16 20:00','2021-06-16 08:00',4);
INSERT INTO working_hours (id, status_of_working_hours,working_end_time,working_start_time,worker_schedule_id) VALUES (31,1,'2021-06-17 20:00','2021-06-17 08:00',4);
INSERT INTO working_hours (id, status_of_working_hours,working_end_time,working_start_time,worker_schedule_id) VALUES (32,1,'2021-06-18 20:00','2021-06-18 08:00',4);
INSERT INTO working_hours (id, status_of_working_hours,working_end_time,working_start_time,worker_schedule_id) VALUES (33,1,'2021-06-19 20:00','2021-06-19 08:00',4);

/*farmaceut 7 u apoteci 2*/
INSERT INTO working_hours (id, status_of_working_hours,working_end_time,working_start_time,worker_schedule_id) VALUES (34,1,'2021-06-20 20:00','2021-06-20 08:00',5);
INSERT INTO working_hours (id, status_of_working_hours,working_end_time,working_start_time,worker_schedule_id) VALUES (35,1,'2021-06-21 20:00','2021-06-21 08:00',5);
INSERT INTO working_hours (id, status_of_working_hours,working_end_time,working_start_time,worker_schedule_id) VALUES (36,1,'2021-06-22 20:00','2021-06-22 08:00',5);
INSERT INTO working_hours (id, status_of_working_hours,working_end_time,working_start_time,worker_schedule_id) VALUES (37,1,'2021-06-23 20:00','2021-06-23 08:00',5);
INSERT INTO working_hours (id, status_of_working_hours,working_end_time,working_start_time,worker_schedule_id) VALUES (38,1,'2021-06-24 20:00','2021-06-24 08:00',5);
INSERT INTO working_hours (id, status_of_working_hours,working_end_time,working_start_time,worker_schedule_id) VALUES (39,1,'2021-06-25 20:00','2021-06-25 08:00',5);

insert into ingredient (id, name_of_ingredient) values (nextval('my_seq_ingredient'),'Natrium-kroskameloza');
insert into ingredient (id, name_of_ingredient) values (nextval('my_seq_ingredient'),'Laktoza');
insert into ingredient (id, name_of_ingredient) values (nextval('my_seq_ingredient'),'Mono-hidrat');
insert into ingredient (id, name_of_ingredient) values (nextval('my_seq_ingredient'),'Silicijum-dioksid');
insert into ingredient (id, name_of_ingredient) values (nextval('my_seq_ingredient'),'Natrium-sulfat');
insert into ingredient (id, name_of_ingredient) values (nextval('my_seq_ingredient'),'Valproinska kiselina');
insert into ingredient (id, name_of_ingredient) values (nextval('my_seq_ingredient'),'Natrium-valproat');
insert into ingredient (id, name_of_ingredient) values (nextval('my_seq_ingredient'),'Vitamin C');
insert into ingredient (id, name_of_ingredient) values (nextval('my_seq_ingredient'),'Vitamin E');
insert into ingredient (id, name_of_ingredient) values (nextval('my_seq_ingredient'),'Gluten');

insert into medicine_infos (id, form_of_medicine, manufacturer_of_medicine, medicine_code, medicine_name, notes, prescription_regime, type_of_medicine) values (nextval('my_seq_medicine_info'),2,'Medic Pharmacy', '211233', 'Brufen','Preporuceno je ne konzumirati sa alkoholom',1,0);
insert into medicine_infos (id, form_of_medicine, manufacturer_of_medicine, medicine_code, medicine_name, notes, prescription_regime, type_of_medicine) values (nextval('my_seq_medicine_info'),2,'Medic Pharmacy', '211234', 'Aspirin','Konzumirati 2h nakon obroka',1,1);
insert into medicine_infos (id, form_of_medicine, manufacturer_of_medicine, medicine_code, medicine_name, notes, prescription_regime, type_of_medicine) values (nextval('my_seq_medicine_info'),2,'Medic Pharmacy', '211235', 'Andol','Maksimalni dnevni unos 3 tableta',1,2);
insert into medicine_infos (id, form_of_medicine, manufacturer_of_medicine, medicine_code, medicine_name, notes, prescription_regime, type_of_medicine) values (nextval('my_seq_medicine_info'),2,'Medic Pharmacy', '211236', 'Eftil','Nije preporucen trudnicama i deci',0,3);
insert into medicine_infos (id, form_of_medicine, manufacturer_of_medicine, medicine_code, medicine_name, notes, prescription_regime, type_of_medicine) values (nextval('my_seq_medicine_info'),1,'Medic Pharmacy', '211237', 'Kardiopirin','Moguce kontraindikacije uz konzumirtanje sa drugim lekovima',0,3);
insert into medicine_infos (id, form_of_medicine, manufacturer_of_medicine, medicine_code, medicine_name, notes, prescription_regime, type_of_medicine) values (nextval('my_seq_medicine_info'),1,'Medic Pharmacy', '211238', 'Penicilin','Lek je iskkljucivo namenjen za uzrast iznad 13 godina',0,3);
insert into medicine_infos (id, form_of_medicine, manufacturer_of_medicine, medicine_code, medicine_name, notes, prescription_regime, type_of_medicine) values (nextval('my_seq_medicine_info'),1,'Medic Pharmacy', '211239', 'Lorazepam','Pojacano dejstvo leka kod osoba sa viskim krvim pritiskom',0,3);
insert into medicine_infos (id, form_of_medicine, manufacturer_of_medicine, medicine_code, medicine_name, notes, prescription_regime, type_of_medicine) values (nextval('my_seq_medicine_info'),1,'Medic Pharmacy', '211240', 'Amoksicilin','Ne preporucuje se ljudima sa visokim krvnim pritiskom',0,3);
insert into medicine_infos (id, form_of_medicine, manufacturer_of_medicine, medicine_code, medicine_name, notes, prescription_regime, type_of_medicine) values (nextval('my_seq_medicine_info'),3,'Medic Pharmacy', '211241', 'Metoprolol','Preporuceno je ne konzumirati sa alkoholom',0,3);
insert into medicine_infos (id, form_of_medicine, manufacturer_of_medicine, medicine_code, medicine_name, notes, prescription_regime, type_of_medicine) values (nextval('my_seq_medicine_info'),7,'Medic Pharmacy', '211242', 'Prospan','Ne mesati sa bilo kakvim drugim lekovima',1,7);


insert into medicine (medicine_average_rating, id) values (0.0,1);
insert into medicine (medicine_average_rating, id) values (0.0,2);
insert into medicine (medicine_average_rating, id) values (0.0,3);
insert into medicine (medicine_average_rating, id) values (0.0,4);
insert into medicine (medicine_average_rating, id) values (0.0,5);
insert into medicine (medicine_average_rating, id) values (0.0,6);
insert into medicine (medicine_average_rating, id) values (0.0,7);
insert into medicine (medicine_average_rating, id) values (0.0,8);
insert into medicine (medicine_average_rating, id) values (0.0,9);
insert into medicine (medicine_average_rating, id) values (0.0,10);

/*lek 1*/
insert into medicine_infos_ingredients (medicine_info_id, ingredients_id) values (1,1);
insert into medicine_infos_ingredients (medicine_info_id, ingredients_id) values (1,2);
insert into medicine_infos_ingredients (medicine_info_id, ingredients_id) values (1,7);
insert into medicine_infos_ingredients (medicine_info_id, ingredients_id) values (1,4);

insert into medicine_infos_ingredients (medicine_info_id, ingredients_id) values (2,1);
insert into medicine_infos_ingredients (medicine_info_id, ingredients_id) values (2,5);
insert into medicine_infos_ingredients (medicine_info_id, ingredients_id) values (2,3);
insert into medicine_infos_ingredients (medicine_info_id, ingredients_id) values (2,4);

insert into medicine_infos_ingredients (medicine_info_id, ingredients_id) values (3,1);
insert into medicine_infos_ingredients (medicine_info_id, ingredients_id) values (3,4);
insert into medicine_infos_ingredients (medicine_info_id, ingredients_id) values (3,6);
insert into medicine_infos_ingredients (medicine_info_id, ingredients_id) values (3,8);

insert into medicine_infos_ingredients (medicine_info_id, ingredients_id) values (4,3);
insert into medicine_infos_ingredients (medicine_info_id, ingredients_id) values (4,2);
insert into medicine_infos_ingredients (medicine_info_id, ingredients_id) values (4,6);
insert into medicine_infos_ingredients (medicine_info_id, ingredients_id) values (4,7);

insert into medicine_infos_ingredients (medicine_info_id, ingredients_id) values (5,2);
insert into medicine_infos_ingredients (medicine_info_id, ingredients_id) values (5,5);
insert into medicine_infos_ingredients (medicine_info_id, ingredients_id) values (5,4);
insert into medicine_infos_ingredients (medicine_info_id, ingredients_id) values (5,9);

insert into medicine_infos_ingredients (medicine_info_id, ingredients_id) values (6,1);
insert into medicine_infos_ingredients (medicine_info_id, ingredients_id) values (6,3);
insert into medicine_infos_ingredients (medicine_info_id, ingredients_id) values (6,5);
insert into medicine_infos_ingredients (medicine_info_id, ingredients_id) values (6,7);

insert into medicine_infos_ingredients (medicine_info_id, ingredients_id) values (7,8);
insert into medicine_infos_ingredients (medicine_info_id, ingredients_id) values (7,10);
insert into medicine_infos_ingredients (medicine_info_id, ingredients_id) values (7,2);
insert into medicine_infos_ingredients (medicine_info_id, ingredients_id) values (7,6);

/*lek 1*/
insert into medicine_infos_alternative_medicines (medicine_info_id,alternative_medicines_id) values (1,2);
insert into medicine_infos_alternative_medicines (medicine_info_id,alternative_medicines_id) values (1,3);

insert into medicine_infos_alternative_medicines (medicine_info_id,alternative_medicines_id) values (2,1);
insert into medicine_infos_alternative_medicines (medicine_info_id,alternative_medicines_id) values (2,3);

insert into medicine_infos_alternative_medicines (medicine_info_id,alternative_medicines_id) values (4,5);
insert into medicine_infos_alternative_medicines (medicine_info_id,alternative_medicines_id) values (4,6);

insert into medicine_infos_alternative_medicines (medicine_info_id,alternative_medicines_id) values (7,8);
insert into medicine_infos_alternative_medicines (medicine_info_id,alternative_medicines_id) values (7,6);

insert into patient_medicine_allergies (patient_id,medicine_allergies_id) values (1,1);
insert into patient_medicine_allergies (patient_id,medicine_allergies_id) values (2,2);

insert into suppliers (id) values (10);
insert into suppliers (id) values (11);

/*dermatolog = 8 termin = open apoteka = 1*/
insert into appointments (id,appointment_points,appointment_price,status_of_appointment,type_of_appointment,version) values (nextval('my_seq_appointment'),5,20,0,0,1);
insert into appointments (id,appointment_points,appointment_price,status_of_appointment,type_of_appointment,version) values (nextval('my_seq_appointment'),5,20,0,0,1);
insert into appointments (id,appointment_points,appointment_price,status_of_appointment,type_of_appointment,version) values (nextval('my_seq_appointment'),5,20,0,0,1);
insert into appointments (id,appointment_points,appointment_price,status_of_appointment,type_of_appointment,version) values (nextval('my_seq_appointment'),5,20,0,0,1);
insert into appointments (id,appointment_points,appointment_price,status_of_appointment,type_of_appointment,version) values (nextval('my_seq_appointment'),5,20,0,0,1);

/*dermatolog = 8 termin = open apoteka = 2*/
insert into appointments (id,appointment_points,appointment_price,status_of_appointment,type_of_appointment,version) values (nextval('my_seq_appointment'),5,20,0,0,1);
insert into appointments (id,appointment_points,appointment_price,status_of_appointment,type_of_appointment,version) values (nextval('my_seq_appointment'),5,20,0,0,1);

/*dermatolog = 8 termin = reserved apoteka = 1*/
insert into appointments (id,appointment_points,appointment_price,status_of_appointment,type_of_appointment,version) values (nextval('my_seq_appointment'),5,20,1,0,1);
insert into appointments (id,appointment_points,appointment_price,status_of_appointment,type_of_appointment,version) values (nextval('my_seq_appointment'),5,20,1,0,1);
insert into appointments (id,appointment_points,appointment_price,status_of_appointment,type_of_appointment,version) values (nextval('my_seq_appointment'),5,20,1,0,1);
insert into appointments (id,appointment_points,appointment_price,status_of_appointment,type_of_appointment,version) values (nextval('my_seq_appointment'),5,20,1,0,1);
insert into appointments (id,appointment_points,appointment_price,status_of_appointment,type_of_appointment,version) values (nextval('my_seq_appointment'),5,20,1,0,1);

/*dermatolog = 8 termin = reserved apoteka = 2*/
insert into appointments (id,appointment_points,appointment_price,status_of_appointment,type_of_appointment,version) values (nextval('my_seq_appointment'),5,20,1,0,1);
insert into appointments (id,appointment_points,appointment_price,status_of_appointment,type_of_appointment,version) values (nextval('my_seq_appointment'),5,20,1,0,1);

/*dermatolog = 8 termin = missed apoteka = 1*/
insert into appointments (id,appointment_points,appointment_price,status_of_appointment,type_of_appointment,version) values (nextval('my_seq_appointment'),5,20,2,0,1);
insert into appointments (id,appointment_points,appointment_price,status_of_appointment,type_of_appointment,version) values (nextval('my_seq_appointment'),5,20,2,0,1);

/*dermatolog = 8 termin = expired apoteka = 1*/
insert into appointments (id,appointment_points,appointment_price,status_of_appointment,type_of_appointment,version) values (nextval('my_seq_appointment'),5,20,3,0,1);
insert into appointments (id,appointment_points,appointment_price,status_of_appointment,type_of_appointment,version) values (nextval('my_seq_appointment'),5,20,3,0,1);

/*farmaceut = 6 termin = reserved apoteka = 1*/
insert into appointments (id,appointment_points,appointment_price,status_of_appointment,type_of_appointment,version) values (nextval('my_seq_appointment'),5,20,1,1,1);
insert into appointments (id,appointment_points,appointment_price,status_of_appointment,type_of_appointment,version) values (nextval('my_seq_appointment'),5,20,1,1,1);
insert into appointments (id,appointment_points,appointment_price,status_of_appointment,type_of_appointment,version) values (nextval('my_seq_appointment'),5,20,1,1,1);
insert into appointments (id,appointment_points,appointment_price,status_of_appointment,type_of_appointment,version) values (nextval('my_seq_appointment'),5,20,1,1,1);
insert into appointments (id,appointment_points,appointment_price,status_of_appointment,type_of_appointment,version) values (nextval('my_seq_appointment'),5,20,1,1,1);

/*farmaceut = 6 termin = missed apoteka = 1*/
insert into appointments (id,appointment_points,appointment_price,status_of_appointment,type_of_appointment,version) values (nextval('my_seq_appointment'),5,20,2,1,1);
insert into appointments (id,appointment_points,appointment_price,status_of_appointment,type_of_appointment,version) values (nextval('my_seq_appointment'),5,20,2,1,1);

/*farmaceut = 6 termin = expired apoteka = 1*/
insert into appointments (id,appointment_points,appointment_price,status_of_appointment,type_of_appointment,version) values (nextval('my_seq_appointment'),5,20,3,1,1);
insert into appointments (id,appointment_points,appointment_price,status_of_appointment,type_of_appointment,version) values (nextval('my_seq_appointment'),5,20,3,1,1);


/*dermatolog = 8 termin = open apoteka = 1*/
insert into dermatologist_appointment (dermatologist_appointment_start_time,dermatologist_appointment_end_time,id,dermatologist_for_appointment_id,patient_with_dermatologist_appointment_id,pharmacy_for_dermatologist_appointment_id) values('2021-06-10 14:00','2021-06-10 15:30',1,8,null,1);
insert into dermatologist_appointment (dermatologist_appointment_start_time,dermatologist_appointment_end_time,id,dermatologist_for_appointment_id,patient_with_dermatologist_appointment_id,pharmacy_for_dermatologist_appointment_id) values('2021-06-12 14:00','2021-06-12 15:30',2,8,null,1);
insert into dermatologist_appointment (dermatologist_appointment_start_time,dermatologist_appointment_end_time,id,dermatologist_for_appointment_id,patient_with_dermatologist_appointment_id,pharmacy_for_dermatologist_appointment_id) values('2021-06-14 11:00','2021-06-14 12:30',3,8,null,1);
insert into dermatologist_appointment (dermatologist_appointment_start_time,dermatologist_appointment_end_time,id,dermatologist_for_appointment_id,patient_with_dermatologist_appointment_id,pharmacy_for_dermatologist_appointment_id) values('2021-06-16 12:00','2021-06-16 13:30',4,8,null,1);
insert into dermatologist_appointment (dermatologist_appointment_start_time,dermatologist_appointment_end_time,id,dermatologist_for_appointment_id,patient_with_dermatologist_appointment_id,pharmacy_for_dermatologist_appointment_id) values('2021-06-17 14:00','2021-06-17 15:30',5,8,null,1);

/*dermatolog = 8 termin = open apoteka = 2*/
insert into dermatologist_appointment (dermatologist_appointment_start_time,dermatologist_appointment_end_time,id,dermatologist_for_appointment_id,patient_with_dermatologist_appointment_id,pharmacy_for_dermatologist_appointment_id) values('2021-06-21 12:00','2021-06-21 13:30',6,8,null,2);
insert into dermatologist_appointment (dermatologist_appointment_start_time,dermatologist_appointment_end_time,id,dermatologist_for_appointment_id,patient_with_dermatologist_appointment_id,pharmacy_for_dermatologist_appointment_id) values('2021-06-24 14:00','2021-06-24 15:30',7,8,null,2);

/*dermatolog = 8 termin = reserved apoteka = 1*/
insert into dermatologist_appointment (dermatologist_appointment_start_time,dermatologist_appointment_end_time,id,dermatologist_for_appointment_id,patient_with_dermatologist_appointment_id,pharmacy_for_dermatologist_appointment_id) values('2021-06-10 10:00','2021-06-10 12:30',8,8,1,1);
insert into dermatologist_appointment (dermatologist_appointment_start_time,dermatologist_appointment_end_time,id,dermatologist_for_appointment_id,patient_with_dermatologist_appointment_id,pharmacy_for_dermatologist_appointment_id) values('2021-06-12 09:00','2021-06-12 11:00',9,8,1,1);
insert into dermatologist_appointment (dermatologist_appointment_start_time,dermatologist_appointment_end_time,id,dermatologist_for_appointment_id,patient_with_dermatologist_appointment_id,pharmacy_for_dermatologist_appointment_id) values('2021-06-14 15:00','2021-06-14 16:30',10,8,2,1);
insert into dermatologist_appointment (dermatologist_appointment_start_time,dermatologist_appointment_end_time,id,dermatologist_for_appointment_id,patient_with_dermatologist_appointment_id,pharmacy_for_dermatologist_appointment_id) values('2021-06-16 15:00','2021-06-16 16:00',11,8,2,1);
insert into dermatologist_appointment (dermatologist_appointment_start_time,dermatologist_appointment_end_time,id,dermatologist_for_appointment_id,patient_with_dermatologist_appointment_id,pharmacy_for_dermatologist_appointment_id) values('2021-06-17 18:00','2021-06-17 19:30',12,8,1,1);

/*dermatolog = 8 termin = reserved apoteka = 2*/
insert into dermatologist_appointment (dermatologist_appointment_start_time,dermatologist_appointment_end_time,id,dermatologist_for_appointment_id,patient_with_dermatologist_appointment_id,pharmacy_for_dermatologist_appointment_id) values('2021-06-21 16:00','2021-06-21 18:00',13,8,1,2);
insert into dermatologist_appointment (dermatologist_appointment_start_time,dermatologist_appointment_end_time,id,dermatologist_for_appointment_id,patient_with_dermatologist_appointment_id,pharmacy_for_dermatologist_appointment_id) values('2021-06-24 17:00','2021-06-24 18:30',14,8,2,2);

/*dermatolog = 8 termin = missed apoteka = 1*/
insert into dermatologist_appointment (dermatologist_appointment_start_time,dermatologist_appointment_end_time,id,dermatologist_for_appointment_id,patient_with_dermatologist_appointment_id,pharmacy_for_dermatologist_appointment_id) values('2021-06-05 16:00','2021-06-05 18:00',15,8,1,1);
insert into dermatologist_appointment (dermatologist_appointment_start_time,dermatologist_appointment_end_time,id,dermatologist_for_appointment_id,patient_with_dermatologist_appointment_id,pharmacy_for_dermatologist_appointment_id) values('2021-06-05 14:00','2021-06-05 15:30',16,8,2,2);

/*dermatolog = 8 termin = expired apoteka = 1*/
insert into dermatologist_appointment (dermatologist_appointment_start_time,dermatologist_appointment_end_time,id,dermatologist_for_appointment_id,patient_with_dermatologist_appointment_id,pharmacy_for_dermatologist_appointment_id) values('2021-06-05 10:00','2021-06-05 12:00',17,8,1,1);
insert into dermatologist_appointment (dermatologist_appointment_start_time,dermatologist_appointment_end_time,id,dermatologist_for_appointment_id,patient_with_dermatologist_appointment_id,pharmacy_for_dermatologist_appointment_id) values('2021-06-05 08:00','2021-06-05 09:30',18,8,2,1);

/*farmaceut = 6 termin = reserved apoteka = 1*/
insert into pharmacist_appointment (pharmacist_appointment_duration,pharmacist_appointment_start_time,id,pharmacist_for_appointment_id,patient_with_pharmacist_appointment_id) values (45,'2021-06-11 10:00',19,6,1);
insert into pharmacist_appointment (pharmacist_appointment_duration,pharmacist_appointment_start_time,id,pharmacist_for_appointment_id,patient_with_pharmacist_appointment_id) values (30,'2021-06-13 12:00',20,6,2);
insert into pharmacist_appointment (pharmacist_appointment_duration,pharmacist_appointment_start_time,id,pharmacist_for_appointment_id,patient_with_pharmacist_appointment_id) values (60,'2021-06-15 14:00',21,6,2);
insert into pharmacist_appointment (pharmacist_appointment_duration,pharmacist_appointment_start_time,id,pharmacist_for_appointment_id,patient_with_pharmacist_appointment_id) values (90,'2021-06-18 11:00',22,6,1);
insert into pharmacist_appointment (pharmacist_appointment_duration,pharmacist_appointment_start_time,id,pharmacist_for_appointment_id,patient_with_pharmacist_appointment_id) values (60,'2021-06-19 13:00',23,6,1);


/*farmaceut = 6 termin = missed apoteka = 1*/
insert into pharmacist_appointment (pharmacist_appointment_duration,pharmacist_appointment_start_time,id,pharmacist_for_appointment_id,patient_with_pharmacist_appointment_id) values (90,'2021-06-04 15:00',24,6,1);
insert into pharmacist_appointment (pharmacist_appointment_duration,pharmacist_appointment_start_time,id,pharmacist_for_appointment_id,patient_with_pharmacist_appointment_id) values (60,'2021-06-04 12:00',25,6,2);

/*farmaceut = 6 termin = expired apoteka = 1*/
insert into pharmacist_appointment (pharmacist_appointment_duration,pharmacist_appointment_start_time,id,pharmacist_for_appointment_id,patient_with_pharmacist_appointment_id) values (45,'2021-06-04 10:00',26,6,2);
insert into pharmacist_appointment (pharmacist_appointment_duration,pharmacist_appointment_start_time,id,pharmacist_for_appointment_id,patient_with_pharmacist_appointment_id) values (30,'2021-06-04 08:00',27,6,1);

insert into medicine_reservations(id,patient_for_medicine_reservation_id,date_of_taking_medicine,pharmacy_for_medicine_reservation_id,status_of_medicine_reservation,reserved_medicine_id,version) values (nextval('my_seq_gen_medicine_reservation'),1,'2021-05-15',1,3,1,1);
insert into medicine_reservations(id,patient_for_medicine_reservation_id,date_of_taking_medicine,pharmacy_for_medicine_reservation_id,status_of_medicine_reservation,reserved_medicine_id,version) values (nextval('my_seq_gen_medicine_reservation'),1,'2021-06-28',1,2,1,1);


insert into storages(id,type_of_storage) values (nextval('my_seq_storage'),1);
insert into storages(id,type_of_storage) values (nextval('my_seq_storage'),1);

insert into pharmacy_storages(id,pharmacy_id) values (1,1);
insert into pharmacy_storages(id,pharmacy_id) values (2,2);

insert into items(id,medicine_amount,type_of_item,medicine_item_id,version) values (nextval('my_seq_item'),10,4,1,1);
insert into items(id,medicine_amount,type_of_item,medicine_item_id,version) values (nextval('my_seq_item'),5,4,2,1);
insert into items(id,medicine_amount,type_of_item,medicine_item_id,version) values (nextval('my_seq_item'),40,4,3,1);
insert into items(id,medicine_amount,type_of_item,medicine_item_id,version) values (nextval('my_seq_item'),20,4,4,1);
insert into items(id,medicine_amount,type_of_item,medicine_item_id,version) values (nextval('my_seq_item'),15,4,5,1);
insert into items(id,medicine_amount,type_of_item,medicine_item_id,version) values (nextval('my_seq_item'),3,4,6,1);
insert into items(id,medicine_amount,type_of_item,medicine_item_id,version) values (nextval('my_seq_item'),100,4,7,1);
insert into items(id,medicine_amount,type_of_item,medicine_item_id,version) values (nextval('my_seq_item'),24,4,8,1);
insert into items(id,medicine_amount,type_of_item,medicine_item_id,version) values (nextval('my_seq_item'),50,4,9,1);
insert into items(id,medicine_amount,type_of_item,medicine_item_id,version) values (nextval('my_seq_item'),30,4,10,1);

insert into pharmacy_storage_items(id,pharmacy_storage_with_item_id) values (1,1);
insert into pharmacy_storage_items(id,pharmacy_storage_with_item_id) values (2,1);
insert into pharmacy_storage_items(id,pharmacy_storage_with_item_id) values (5,1);
insert into pharmacy_storage_items(id,pharmacy_storage_with_item_id) values (7,1);
insert into pharmacy_storage_items(id,pharmacy_storage_with_item_id) values (8,1);
insert into pharmacy_storage_items(id,pharmacy_storage_with_item_id) values (9,1);
insert into pharmacy_storage_items(id,pharmacy_storage_with_item_id) values (10,1);

insert into pharmacy_storage_items(id,pharmacy_storage_with_item_id) values (3,2);
insert into pharmacy_storage_items(id,pharmacy_storage_with_item_id) values (4,2);
insert into pharmacy_storage_items(id,pharmacy_storage_with_item_id) values (6,2);

insert into items(id,medicine_amount,type_of_item,medicine_item_id,version) values (nextval('my_seq_item'),5,0,4,1);
insert into items(id,medicine_amount,type_of_item,medicine_item_id,version) values (nextval('my_seq_item'),5,0,3,1);
insert into e_prescriptions(id,creation_date,patient_foreprescription_id,status_ofeprescription) values (1,'2021-05-16 19:05',1,0);
insert into eprescription_items(id,e_prescriton_id) values (11,1);
insert into eprescription_items(id,e_prescriton_id) values (12,1);


insert into medicine_price(id,medicine_price,medicine_with_prices_id,price_list_for_medicine_id) values (nextval('my_seq_medicine_price'),200,1,1);
insert into medicine_price(id,medicine_price,medicine_with_prices_id,price_list_for_medicine_id) values (nextval('my_seq_medicine_price'),100,2,1);
insert into medicine_price(id,medicine_price,medicine_with_prices_id,price_list_for_medicine_id) values (nextval('my_seq_medicine_price'),300,5,1);
insert into medicine_price(id,medicine_price,medicine_with_prices_id,price_list_for_medicine_id) values (nextval('my_seq_medicine_price'),500,7,1);
insert into medicine_price(id,medicine_price,medicine_with_prices_id,price_list_for_medicine_id) values (nextval('my_seq_medicine_price'),220,8,1);
insert into medicine_price(id,medicine_price,medicine_with_prices_id,price_list_for_medicine_id) values (nextval('my_seq_medicine_price'),250,9,1);
insert into medicine_price(id,medicine_price,medicine_with_prices_id,price_list_for_medicine_id) values (nextval('my_seq_medicine_price'),300,10,1);

insert into medicine_price(id,medicine_price,medicine_with_prices_id,price_list_for_medicine_id) values (nextval('my_seq_medicine_price'),200,3,2);
insert into medicine_price(id,medicine_price,medicine_with_prices_id,price_list_for_medicine_id) values (nextval('my_seq_medicine_price'),500,4,2);
insert into medicine_price(id,medicine_price,medicine_with_prices_id,price_list_for_medicine_id) values (nextval('my_seq_medicine_price'),400,6,2);


INSERT INTO autorizations (id, name) values (nextval('my_seq_authority'), 'ROLE_ADMINP');
INSERT INTO autorizations (id, name) values (nextval('my_seq_authority'), 'ROLE_ADMINS');
INSERT INTO autorizations (id, name) values (nextval('my_seq_authority'), 'ROLE_SUPPLIER');
INSERT INTO autorizations (id, name) values (nextval('my_seq_authority'), 'ROLE_PATIENT');
INSERT INTO autorizations (id, name) values (nextval('my_seq_authority'), 'ROLE_PHARMACIST');
INSERT INTO autorizations (id, name) values (nextval('my_seq_authority'), 'ROLE_DERMATOLOGIST');

insert into users_authorities(users_id,authorities_id) values(1,4);
insert into users_authorities(users_id,authorities_id) values(2,4);
insert into users_authorities(users_id,authorities_id) values(6,5);
insert into users_authorities(users_id,authorities_id) values(7,5);
insert into users_authorities(users_id,authorities_id) values(8,6);
insert into users_authorities(users_id,authorities_id) values(9,6);


insert into dermatologist_reports(id,report_infos,dermatologist_appointment_id,recipe_for_dermatologist_report_id) values (nextval('my_seq_dermatologist_report'),'Otici na more sto pre',17,null);
insert into dermatologist_reports(id,report_infos,dermatologist_appointment_id,recipe_for_dermatologist_report_id) values (nextval('my_seq_dermatologist_report'),'Posetiti planinske predele',18,null);

insert into pharmacist_reports(id,report_infos,pharmacist_appointment_id,recipe_for_pharmacist_report_id) values (nextval('my_seq_pharmacist_report'),'Mirovati sto cesce',26,null);
insert into pharmacist_reports(id,report_infos,pharmacist_appointment_id,recipe_for_pharmacist_report_id) values (nextval('my_seq_pharmacist_report'),'Bez prevelikog stresa i samo opusteno',27,null);

insert into patient_pharmacies_subscription(patient_id,pharmacies_subscription_id) values (1,1);
insert into patient_pharmacies_subscription(patient_id,pharmacies_subscription_id) values (2,2);


