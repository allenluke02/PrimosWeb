-- SELECT Concat('TRUNCATE TABLE ',TABLE_NAME, ';') 
-- FROM INFORMATION_SCHEMA.TABLES where  table_schema in ('gmc');
-- this list shal be sufficient. However, if there is table name change or create/remove table
-- that needs to be accommodated here. Alternatively, one can iterate over all the models metadata
-- and execute them one by one. Even more alternatively, one can use "TRUNCATE SCHEMA PUBLIC RESTART IDENTITY AND COMMIT NO CHECK"
-- 1. First execute above and copy paste result here, 
-- 2. leave foreign key check commands at start and end as such
-- 3. remove TRUNCATE TABLE hibernate_sequence;
SET FOREIGN_KEY_CHECKS=0;
TRUNCATE TABLE address_type;
TRUNCATE TABLE appointment;
TRUNCATE TABLE authorities;
TRUNCATE TABLE city;
TRUNCATE TABLE clinic;
TRUNCATE TABLE configuration;
TRUNCATE TABLE country;
TRUNCATE TABLE doctor;
TRUNCATE TABLE education;
TRUNCATE TABLE emr;
TRUNCATE TABLE emr_complaints;
TRUNCATE TABLE emr_diagnosis;
TRUNCATE TABLE emrsuggestion;
TRUNCATE TABLE emrsuggestion_avoid;
TRUNCATE TABLE emrsuggestion_recommended;
TRUNCATE TABLE experience;
TRUNCATE TABLE facility;
TRUNCATE TABLE facility_specialities;
TRUNCATE TABLE master_physical_exam;
TRUNCATE TABLE emr_medicine;

TRUNCATE TABLE month_week_availablity;
TRUNCATE TABLE oauth_client_details;
TRUNCATE TABLE organization_address;
TRUNCATE TABLE otp;
TRUNCATE TABLE patient;
TRUNCATE TABLE payment;
TRUNCATE TABLE person;
TRUNCATE TABLE person_address;
TRUNCATE TABLE physical_exam;
TRUNCATE TABLE plan;
TRUNCATE TABLE roles;
TRUNCATE TABLE roles_authorities;
TRUNCATE TABLE speciality;
TRUNCATE TABLE staff;
TRUNCATE TABLE staff_schedule;
TRUNCATE TABLE state;
TRUNCATE TABLE subscription;
TRUNCATE TABLE support_staff;
TRUNCATE TABLE test;
TRUNCATE TABLE users;
TRUNCATE TABLE users_roles;
TRUNCATE TABLE zip;
SET FOREIGN_KEY_CHECKS=1;