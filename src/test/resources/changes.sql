--CREATE SCHEMA `gmc` DEFAULT CHARACTER SET utf8 ;
--use gmc;
-- jwt token: Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJhdWQiOlsiYXBpIl0sInVzZXJfbmFtZSI6IjEiLCJzY29wZSI6WyJyZWFkIiwid3JpdGUiXSwicm9sZXMiOlsiQURNSU4iXSwiZXhwIjoxNTMwMDk2NjczLCJhdXRob3JpdGllcyI6WyJVUERBVEVfVVNFUiIsIlJFQURfVVNFUiIsIkNSRUFURV9VU0VSIiwiREVMRVRFX1VTRVIiXSwianRpIjoiZGYxNmUxYTctYTA3OS00YzdjLWFjOTgtYmUyYmFiYmFiZmRmIiwiY2xpZW50X2lkIjoiZ21jLWNsaWVudCJ9.SBHxUOfwXXLZgptVZlMfDn6Qj2a1Dqwo97spvxd-n3o
--INSERT INTO roles(ID, NAME) VALUES (1, 'SITE_ADMIN');
INSERT INTO roles(ID, NAME) VALUES (2, 'ADMIN');
INSERT INTO roles(ID, NAME) VALUES (3, 'USER');
--INSERT INTO roles(ID, NAME) VALUES (4, 'DOCTOR');
--INSERT INTO roles(ID, NAME) VALUES (5, 'SUPPORT_STAFF');
insert into users (id, account_expired, account_locked, clinic_id, credentials_expired, enabled, is_deleted, is_terms_policy_agreed, login_name, password, phone, staff_id,is_owner) values 
(1, false, false, null, false, true, false, false, 'admin', /*test123*/'$2a$08$KPvA7WTJ3.7JgWu31coZ8eVAIPB4Dd96R/Uo2bgqZ5HxJrgvc.A.S','1234567890' , null,true);
 
INSERT INTO users_roles(user_id, role_id) VALUES (1, 1);

CREATE TABLE oauth_client_details (
  client_id VARCHAR(255) PRIMARY KEY,
  resource_ids VARCHAR(255),
  client_secret VARCHAR(255),
  scope VARCHAR(255),	
  authorized_grant_types VARCHAR(255),
  web_server_redirect_uri VARCHAR(255),
  authorities VARCHAR(255),
  access_token_validity INTEGER,
  refresh_token_validity INTEGER,
  additional_information VARCHAR(4096),
  autoapprove VARCHAR(255)
);


INSERT INTO oauth_client_details(CLIENT_ID, RESOURCE_IDS, CLIENT_SECRET, SCOPE, AUTHORIZED_GRANT_TYPES, AUTHORITIES, ACCESS_TOKEN_VALIDITY, REFRESH_TOKEN_VALIDITY)
 VALUES ('gmc-client', 'api',
 /*password1234*/'$2a$04$n/Vt.4gKkscgBV9UYHlq5uw.qkp5Ah.a593gYNpItL0g3Lrg.PMiS',
 'read,write', 'password,authorization_code,refresh_token,implicit', 'USER', 2592000, 2592000);

 INSERT INTO configuration(gmc_key, `value`) VALUES ('REGISTER_OTP_EXPIRE_MS', 1800000);
INSERT INTO authorities(ID, NAME) VALUES (1, 'CREATE_USER');
INSERT INTO authorities(ID, NAME) VALUES (2, 'DELETE_USER');
INSERT INTO authorities(ID, NAME) VALUES (3, 'READ_USER');
INSERT INTO authorities(ID, NAME) VALUES (4, 'UPDATE_USER');
INSERT INTO authorities(ID, NAME) VALUES (5, 'CREATE_ENOUNTER');
-- INSERT INTO authorities(ID, NAME) VALUES (1, 'CREATE_USER');

INSERT INTO roles_authorities(ROLE_ID, AUTHORITY_ID) VALUES (1, 1);
INSERT INTO roles_authorities(ROLE_ID, AUTHORITY_ID) VALUES (1, 2);
INSERT INTO roles_authorities(ROLE_ID, AUTHORITY_ID) VALUES (1, 3);
INSERT INTO roles_authorities(ROLE_ID, AUTHORITY_ID) VALUES (1, 4);
INSERT INTO roles_authorities(ROLE_ID, AUTHORITY_ID) VALUES (2, 3);
INSERT INTO roles_authorities(ROLE_ID, AUTHORITY_ID) VALUES (3, 1);
INSERT INTO roles_authorities(ROLE_ID, AUTHORITY_ID) VALUES (4, 1);
INSERT INTO roles_authorities(ROLE_ID, AUTHORITY_ID) VALUES (4, 2);
INSERT INTO roles_authorities(ROLE_ID, AUTHORITY_ID) VALUES (4, 3);
INSERT INTO roles_authorities(ROLE_ID, AUTHORITY_ID) VALUES (4, 4);
INSERT INTO roles_authorities(ROLE_ID, AUTHORITY_ID) VALUES (4, 5);
INSERT INTO roles_authorities(ROLE_ID, AUTHORITY_ID) VALUES (5, 1);
INSERT INTO roles_authorities(ROLE_ID, AUTHORITY_ID) VALUES (5, 2);
INSERT INTO roles_authorities(ROLE_ID, AUTHORITY_ID) VALUES (5, 3);
INSERT INTO roles_authorities(ROLE_ID, AUTHORITY_ID) VALUES (5, 4);

INSERT INTO `country`(`id`,`name`,`created_by_id`,`last_modified_by_id`) VALUES (1, 'India',null,null);
INSERT INTO `state`(`id`,`name`,`country_id`,`created_by_id`,`last_modified_by_id`) VALUES (1,'UP',1,null,null);
INSERT INTO `city`(`id`,`name`,`created_by_id`,`state_id`,`last_modified_by_id`) VALUES (1,'Noida',null,1,null);

-- 24 May 2018

INSERT INTO `city`(`id`,`name`,`created_by_id`,`state_id`,`last_modified_by_id`) VALUES (2,'Greater Noida',null,1,null);
INSERT INTO `city`(`id`,`name`,`created_by_id`,`state_id`,`last_modified_by_id`) VALUES (3,'Ghaziabad',null,1,null);
INSERT INTO `speciality` 
VALUES 
(1,NULL,NULL,NULL,'Clinical Laboratory Sciences',NULL,NULL),
(2,NULL,NULL,NULL,'Clinical Neurophysiology',NULL,NULL),
(3,NULL,NULL,NULL,'Dermatology',NULL,NULL),
(4,NULL,NULL,NULL,'Emergency Medicine',NULL,NULL),
(5,NULL,NULL,NULL,'Endocrinology',NULL,NULL),
(6,NULL,NULL,NULL,'Family Medicine',NULL,NULL),
(7,NULL,NULL,NULL,'Family Practice',NULL,NULL),
(8,NULL,NULL,NULL,'Gastroenterology',NULL,NULL),
(9,NULL,NULL,NULL,'General Surgery',NULL,NULL),
(10,NULL,NULL,NULL,'Geriatrics',NULL,NULL),
(11,NULL,NULL,NULL,'Hematology',NULL,NULL),
(12,NULL,NULL,NULL,'Hepatology',NULL,NULL),
(13,NULL,NULL,NULL,'Infectious Diseases',NULL,NULL),
(14,NULL,NULL,NULL,'Intensive Care Medicine',NULL,NULL),
(15,NULL,NULL,NULL,'Internal Medicine',NULL,NULL),
(16,NULL,NULL,NULL,'Maxillofacial Surgery',NULL,NULL),
(17,NULL,NULL,NULL,'Nephrology',NULL,NULL),
(18,NULL,NULL,NULL,'Neurology',NULL,NULL),
(19,NULL,NULL,NULL,'Neurosurgery',NULL,NULL),
(20,NULL,NULL,NULL,'Obstetrics & Gynaecology',NULL,NULL),
(21,NULL,NULL,NULL,'Oncology',NULL,NULL),
(22,NULL,NULL,NULL,'Ophthalmology',NULL,NULL),
(23,NULL,NULL,NULL,'Orthopedic Surgery',NULL,NULL),
(24,NULL,NULL,NULL,'Otolaryngology (ENT)',NULL,NULL),
(25,NULL,NULL,NULL,'Palliative Care',NULL,NULL),
(26,NULL,NULL,NULL,'Pathology',NULL,NULL),
(27,NULL,NULL,NULL,'Pediatric Surgery',NULL,NULL),
(28,NULL,NULL,NULL,'Pediatrics',NULL,NULL),
(29,NULL,NULL,NULL,'Physical Medicine & Rehabilitation',NULL,NULL),
(30,NULL,NULL,NULL,'Plastic Surgery',NULL,NULL),
(31,NULL,NULL,NULL,'Primary Care',NULL,NULL),
(32,NULL,NULL,NULL,'Proctology',NULL,NULL),
(33,NULL,NULL,NULL,'Psychiatry',NULL,NULL),
(34,NULL,NULL,NULL,'Pulmonology',NULL,NULL),
(35,NULL,NULL,NULL,'Radiology',NULL,NULL),
(36,NULL,NULL,NULL,'Rheumatology',NULL,NULL),
(37,NULL,NULL,NULL,'Surgical Oncology',NULL,NULL),
(38,NULL,NULL,NULL,'Thoracic Surgery',NULL,NULL),
(39,NULL,NULL,NULL,'Transplant Surgery',NULL,NULL),
(40,NULL,NULL,NULL,'Trauma Surgery',NULL,NULL),
(41,NULL,NULL,NULL,'UNKNOWN SPECIALTY',NULL,NULL),
(42,NULL,NULL,NULL,'Urology',NULL,NULL),
(43,NULL,NULL,NULL,'Vascular Surgery',NULL,NULL);

INSERT INTO `meta_physical_exam`(`creation_date`,`last_modified_date`,`name`,`created_by_id`,`last_modified_by_id`) VALUES
(now(),now(),'BP',1,1), (now(),now(),'Height',1,1), (now(),now(),'Weight',1,1);


-- Application Data for testing

-- create clinic
insert into organization_address (id, address1, address2,  city_id, zip) values (1, 'address 1', 't1', 1, 201301);
insert into clinic (id, created_by_id, last_modified_by_id,address_id, description, domain_name, email, gstin, logo, `name`, phone, portalurl, registration_id,is_subscription_active) 
values (1, 1, 1, 1, null, 'domian name', 'email', 'gstn', null, 'clinic 1', 'phone', 'https://test', 'dfsdfd',true);

-- Add Facility
insert into organization_address (id, created_by_id,  last_modified_by_id,  address1, address2, city_id, zip) 
values (2, 1,  1, 'address 1', null,  1, '201301');
insert into facility (id, created_by_id, last_modified_by_id, address_id, clinic_id, description, email, name, phone, is_deleted) 
values (1, 1, 1, 2, 1, 'facility desc', 'a@b.com', 'facility1', '1234567890', 0);
insert into facility_specialities (facility_id, specialities_id) 
values (1, 1);

-- Add Doctor
insert into person_address (address1, address2, city_id, created_by_id, last_modified_by_id, zip, id) 
values ('address 1', null,  1, 1, 1, 203011, 1);
insert into person (id, created_by_id,  last_modified_by_id, address_id, alter_phone, blood_group, email, first_name, gender, is_deleted, last_name, occupation, phone, title) 
values (1, 1, 1,1, '1234567890', 'A+','kishan.mca.du@gmail.com', 'first name', 'M' , FALSE, 'last name', 'occupation', '9654655952', 'Mr');
insert into staff (age, dob, logo, staff_type, id) values (23,'1995-09-03 00:00:00', 'staff logo', 'STAFF', 4);
insert into doctor (practice_license, qualification, specialist_in_id, id) values ('LICENSE 1', 'MBBS', 1, 1);


-- Add Staff
insert into person_address (created_by_id, last_modified_by_id, address1, address2, city_id, zip,id)
values (1,  1,  'ghaziabad','up', 1, '201308',2);
insert into person (created_by_id, last_modified_by_id, address_id, alter_phone, blood_group, email, first_name, gender, is_deleted, last_name, occupation, phone, title,id) 
values (1, 1, 2, '1236547898', 'O--',  'himanshu@gmail.com', 'Himanshu', 'M', FALSE, 'Swarnakar', 'DOCTOR', '9891262969', 'Mr',2);
insert into staff (age, dob, logo, staff_type, id) values (23,'1995-09-03 00:00:00', 'staff logo', 'STAFF', 4);
insert into support_staff (id) values (113)
/*-- Add patient
insert into person_address (created_by_id, last_modified_by_id, address1, address2, city_id, zip,id)
values (1, 1, 'noida sec-15' , 'up', 1, '201301',3);
insert into person (created_by_id,  last_modified_by_id,  address_id, alter_phone, blood_group, dob,age, email, first_name, gender, is_deleted, last_name, occupation, phone, title,id)
values (1, 1, 3 , '8802205751', 'O--', '2012-03-12','22', 'himanshu@gmail.com', 'Himanshu', 'M', FALSE, 'Swarnakar', 'Software Engineer', '9891262969', 'Mr',3);
insert into patient (id) 
values (1);
*/

-- Add patient
insert into person_address (created_by_id, creation_date, last_modified_by_id, last_modified_date, address1, address2, city_id, zip,id)
values (1, now(), 1, now(),'noida sec-15' , 'up', 1, '201301',3);
insert into person (created_by_id, last_modified_by_id, address_id, alter_phone, blood_group, dob,age, email, first_name, gender, is_deleted, last_name, occupation, phone, title,id)
values (1, 1, 3, '8802205751', 'O--', '2012-03-12','22', 'himanshu@gmail.com', 'Himanshu', 'M', FALSE, 'Swarnakar', 'Software Engineer', '9891262969', 'Mr',3);
insert into patient (id) values (1);


-- Add Staff Schedule
insert into staff_schedule (created_by_id, last_modified_by_id, end_date, facility_id, person_id, start_date)
values (1, 1,  '2018-05-14', 1, 1, '2018-07-30');
insert into month_week_availablity (id, created_by_id, last_modified_by_id, fifth_week, first_week, fourth_week, second_week, third_week)
values (1, 1, 1, true, true, false, false, true);
insert into schedule_timing (created_by_id, last_modified_by_id, day_of_week, end_time, month_week_availablities_id, start_time)
values (1, 1, 'MONDAY', '13:00', 1, '09:00');
update schedule_timing set staff_schedule_id=1 where id=1;