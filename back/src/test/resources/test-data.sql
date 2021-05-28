INSERT INTO users (id, email, password, first_name, last_name, phone_number, type_of_user,city,country,latitude,longitude,street_name,street_number,enabled,enable_login,is_first_login, last_password_reset_date) VALUES (1, 'dermatologist1@gmail.com', '$2a$05$j41h6yKxMGORqXRsQuf8EO53aIDL8nd/MNZtIdsbl5zRShHJwt0Om', 'Pavle', 'Pavlovic', '064000000', 4,'Chicago','USD', 41, 87, 'Fifth Ave','5A', true, true, false, '2021-01-01T00:58:53');
INSERT INTO users (id, email, password, first_name, last_name, phone_number, type_of_user,city,country,latitude,longitude,street_name,street_number,enabled,enable_login,is_first_login, last_password_reset_date) VALUES (2, 'patient1@gmail.com', '$2a$05$j41h6yKxMGORqXRsQuf8EO53aIDL8nd/MNZtIdsbl5zRShHJwt0Om', 'Pera', 'Peric', '064000000', 2,'Chicago','USD', 41, 87, 'Fifth Ave','5A', true, true, false, '2021-01-01T00:58:53');

INSERT INTO dermatologists (id,dermatologist_average_rating) VALUES (1,0);
INSERT INTO patient (id, category_of_patient, patient_points, penalty) VALUES (2, 0, 0.0, 2);
INSERT INTO pharmacies (id, city, country, latitude, longitude, street_name, street_number, pharmacy_average_rating, pharmacy_description, pharmacy_name) VALUES (1, 'Novi Sad', 'Srbija', 100, 200, 'Bore Prodanovica', '15A', 9.1, 'Apoteka za sve' , 'Apoteka1');
insert into appointments (id,appointment_points,appointment_price,status_of_appointment,type_of_appointment) values (1,5,50,1,1);
insert into appointments (id,appointment_points,appointment_price,status_of_appointment,type_of_appointment) values (2,5,50,1,1);
insert into dermatologist_appointment (dermatologist_appointment_start_time,dermatologist_appointment_end_time,id,dermatologist_for_appointment_id,patient_with_dermatologist_appointment_id,pharmacy_for_dermatologist_appointment_id) values('2021-05-11 15:00','2021-05-11 15:30',1,1,2,1);
insert into dermatologist_appointment (dermatologist_appointment_start_time,dermatologist_appointment_end_time,id,dermatologist_for_appointment_id,patient_with_dermatologist_appointment_id,pharmacy_for_dermatologist_appointment_id) values('2022-05-11 15:00','2022-05-11 15:30',2,1,2,1);
insert into medicine_infos (id, form_of_medicine, manufacturer_of_medicine, medicine_code, medicine_name, notes, prescription_regime, type_of_medicine) values (1,0,'Medic Pharmacy', '211236', 'Bromic','Brapoooooooo',1,1);
insert into medicine (medicine_average_rating, id) values (0,1);

insert into storages(id,type_of_storage) values (1,1);

insert into pharmacy_storages(id,pharmacy_id) values (1,1);

insert into items(id,medicine_amount,type_of_item,medicine_item_id) values (1,30,4,1);

insert into pharmacy_storage_items(id,pharmacy_storage_with_item_id) values (1,1);

insert into medicine_reservations(id,patient_for_medicine_reservation_id,date_of_taking_medicine,pharmacy_for_medicine_reservation_id,status_of_medicine_reservation,reserved_medicine_id) values (1,2,'2021-05-15',1,2,1);

