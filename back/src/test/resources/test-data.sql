INSERT INTO users (id, email, password, first_name, last_name, phone_number, type_of_user,city,country,latitude,longitude,street_name,street_number,enabled,enable_login,is_first_login, last_password_reset_date) VALUES (nextval('my_seq_users'), 'dermatologist1@gmail.com', '$2a$05$j41h6yKxMGORqXRsQuf8EO53aIDL8nd/MNZtIdsbl5zRShHJwt0Om', 'Pavle', 'Pavlovic', '064000000', 4,'Chicago','USD', 41, 87, 'Fifth Ave','5A', true, true, false, '2021-01-01T00:58:53');
INSERT INTO users (id, email, password, first_name, last_name, phone_number, type_of_user,city,country,latitude,longitude,street_name,street_number,enabled,enable_login,is_first_login, last_password_reset_date) VALUES (nextval('my_seq_users'), 'patient1@gmail.com', '$2a$05$j41h6yKxMGORqXRsQuf8EO53aIDL8nd/MNZtIdsbl5zRShHJwt0Om', 'Pera', 'Peric', '064000000', 2,'Chicago','USD', 41, 87, 'Fifth Ave','5A', true, true, false, '2021-01-01T00:58:53');
INSERT INTO users (id, email, password, first_name, last_name, phone_number, type_of_user,city,country,latitude,longitude,street_name,street_number,enabled,enable_login,is_first_login, last_password_reset_date) VALUES (nextval('my_seq_users'), 'patient2@gmail.com', '$2a$05$j41h6yKxMGORqXRsQuf8EO53aIDL8nd/MNZtIdsbl5zRShHJwt0Om', 'Mika', 'Peric', '064000000', 2,'Chicago','USD', 41, 87, 'Fifth Ave','5A', true, true, false, '2021-01-01T00:58:53');
INSERT INTO users (id, email, password, first_name, last_name, phone_number, type_of_user,city,country,latitude,longitude,street_name,street_number,enabled,enable_login,is_first_login, last_password_reset_date) VALUES (nextval('my_seq_users'), 'pharmacist1@gmail.com', '$2a$05$j41h6yKxMGORqXRsQuf8EO53aIDL8nd/MNZtIdsbl5zRShHJwt0Om', 'Jana', 'Jankovic', '064000000', 3,'Chicago','USD', 41, 87, 'Fifth Ave','5A', true, true, false, '2021-01-01T00:58:53');

INSERT INTO pharmacies (id, city, country, latitude, longitude, street_name, street_number, pharmacy_average_rating, pharmacy_description, pharmacy_name) VALUES (nextval('my_seq_pharmacy'), 'Novi Sad', 'Srbija', 100, 200, 'Bore Prodanovica', '15A', 9.1, 'Apoteka za sve' , 'Apoteka1');


INSERT INTO dermatologists (id,dermatologist_average_rating) VALUES (1,0);
INSERT INTO patient (id, category_of_patient, patient_points, penalty) VALUES (2, 0, 0.0, 2);
INSERT INTO patient (id, category_of_patient, patient_points, penalty) VALUES (3, 0, 0.0, 0);
INSERT INTO pharmacists (id,pharmacist_average_rating,pharmacy_for_pharmacist_id) VALUES (4,4.3,1);


insert into worker_schedule (id,dermatologist_id,pharmacy_id,type_of_worker_schedule) values (1,1,1,1);
insert into worker_schedule (id,pharmacy_id,pharmacist_id,type_of_worker_schedule) values (2,1,4,0);


INSERT INTO working_hours (id, status_of_working_hours,working_end_time,working_start_time,worker_schedule_id) VALUES (1,1,'2022-05-29 22:00','2022-05-29 10:50',1);
INSERT INTO working_hours (id, status_of_working_hours,working_end_time,working_start_time,worker_schedule_id) VALUES (2,1,'2022-05-01 22:00','2022-05-01 10:50',2);


insert into appointments (id,appointment_points,appointment_price,status_of_appointment,type_of_appointment,version) values (nextval('my_seq_appointment'),5,50,1,1,1);
insert into appointments (id,appointment_points,appointment_price,status_of_appointment,type_of_appointment,version) values (nextval('my_seq_appointment'),5,50,1,1,1);
insert into appointments (id,appointment_points,appointment_price,status_of_appointment,type_of_appointment,version) values (nextval('my_seq_appointment'),5,50,0,0,1);

insert into dermatologist_appointment (dermatologist_appointment_start_time,dermatologist_appointment_end_time,id,dermatologist_for_appointment_id,patient_with_dermatologist_appointment_id,pharmacy_for_dermatologist_appointment_id) values('2021-05-11 15:00','2021-05-11 15:30',1,1,2,1);
insert into dermatologist_appointment (dermatologist_appointment_start_time,dermatologist_appointment_end_time,id,dermatologist_for_appointment_id,patient_with_dermatologist_appointment_id,pharmacy_for_dermatologist_appointment_id) values('2022-05-11 15:00','2022-05-11 15:30',2,1,2,1);
insert into dermatologist_appointment (dermatologist_appointment_start_time,dermatologist_appointment_end_time,id,dermatologist_for_appointment_id,patient_with_dermatologist_appointment_id,pharmacy_for_dermatologist_appointment_id) values('2022-05-29 15:00','2022-05-29 15:30',3,1,null,1);

insert into medicine_infos (id, form_of_medicine, manufacturer_of_medicine, medicine_code, medicine_name, notes, prescription_regime, type_of_medicine) values (1,0,'Medic Pharmacy', '211236', 'Bromic','Brapoooooooo',1,1);
insert into medicine (medicine_average_rating, id) values (0,1);

insert into medicine_reservations(id,patient_for_medicine_reservation_id,date_of_taking_medicine,pharmacy_for_medicine_reservation_id,status_of_medicine_reservation,reserved_medicine_id,version) values (nextval('my_seq_gen_medicine_reservation'),2,'2021-05-15',1,2,1,1);
insert into medicine_reservations(id,patient_for_medicine_reservation_id,date_of_taking_medicine,pharmacy_for_medicine_reservation_id,status_of_medicine_reservation,reserved_medicine_id,version) values (nextval('my_seq_gen_medicine_reservation'),2,'2022-05-15',1,2,1,1);

INSERT INTO price_lists (id, dermatologist_appointment_price_per_hour, pharmacist_appointment_price_per_hour, pharmacy_id) VALUES (nextval('my_seq_price_list'), 10, 20, 1);

insert into storages(id,type_of_storage) values (nextval('my_seq_storage'),1);
insert into pharmacy_storages(id,pharmacy_id) values (1,1);
insert into items(id,medicine_amount,type_of_item,medicine_item_id,version) values (nextval('my_seq_item'),1,4,1,1);
insert into pharmacy_storage_items(id,pharmacy_storage_with_item_id) values (1,1);



