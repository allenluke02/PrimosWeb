-- MySQL dump 10.13  Distrib 8.0.21, for Win64 (x86_64)
--
-- Host: localhost    Database: food_ordering
-- ------------------------------------------------------
-- Server version	8.0.21

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `address`
--

DROP TABLE IF EXISTS `address`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `address` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `address1` varchar(255) NOT NULL,
  `address2` varchar(255) DEFAULT NULL,
  `created_on` datetime DEFAULT NULL,
  `zip` varchar(255) NOT NULL,
  `city_id` bigint NOT NULL,
  `country_id` bigint NOT NULL,
  `state_id` bigint NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_t_address_cityId_t_city_id` (`city_id`),
  KEY `fk_t_address_countryId_t_country_id` (`country_id`),
  KEY `fk_t_address_stateId_t_state_id` (`state_id`),
  CONSTRAINT `fk_t_address_cityId_t_city_id` FOREIGN KEY (`city_id`) REFERENCES `city` (`id`),
  CONSTRAINT `fk_t_address_countryId_t_country_id` FOREIGN KEY (`country_id`) REFERENCES `country` (`id`),
  CONSTRAINT `fk_t_address_stateId_t_state_id` FOREIGN KEY (`state_id`) REFERENCES `state` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `address`
--

LOCK TABLES `address` WRITE;
/*!40000 ALTER TABLE `address` DISABLE KEYS */;
INSERT INTO `address` VALUES (1,'801 South Twin Oaks Rd.',NULL,NULL,'CA 92078',1,1,1),(2,'2556 Laning Rd.',NULL,NULL,'CA 92106',2,1,1),(3,'7343 Jackson Dr.',NULL,NULL,'CA 92119',3,1,1),(4,'16621 Dove Canyon Rd.',NULL,NULL,'CA 92127',4,1,1),(5,'919 Lomas Santa Fe',NULL,NULL,'CA 92075',5,1,1),(6,'2708 Loker Ave West #102',NULL,NULL,'CA 92010',6,1,1),(7,'16711 Bernardo Center Dr.',NULL,NULL,'CA 92128',7,1,1),(8,'7770 Regents Rd.',NULL,NULL,'CA 92122',8,1,1),(9,'1348 West Valley Parkway',NULL,NULL,'CA 92029',9,1,1),(10,'3202 Governor Dr.',NULL,NULL,'CA 92122',10,1,1);
/*!40000 ALTER TABLE `address` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `careers`
--

DROP TABLE IF EXISTS `careers`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `careers` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `doc` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `first_name` varchar(255) DEFAULT NULL,
  `last_name` varchar(255) DEFAULT NULL,
  `mailing_address` varchar(255) DEFAULT NULL,
  `phone` varchar(255) DEFAULT NULL,
  `county_id` bigint NOT NULL,
  `job_id` bigint NOT NULL,
  `pickup_point_id` bigint NOT NULL,
  `is_legal` bit(1) NOT NULL,
  `employer_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_t_careers_countyId_t_county_id` (`county_id`),
  KEY `fk_t_careers_jobId_t_job_id` (`job_id`),
  KEY `fk_t_careers_pickupPointId_t_pickup_point_id` (`pickup_point_id`),
  KEY `fk_t_careers_employerId_t_employer_id` (`employer_id`),
  CONSTRAINT `fk_t_careers_countyId_t_county_id` FOREIGN KEY (`county_id`) REFERENCES `county` (`id`),
  CONSTRAINT `fk_t_careers_employerId_t_employer_id` FOREIGN KEY (`employer_id`) REFERENCES `employers` (`id`),
  CONSTRAINT `fk_t_careers_jobId_t_job_id` FOREIGN KEY (`job_id`) REFERENCES `jobs` (`id`),
  CONSTRAINT `fk_t_careers_pickupPointId_t_pickup_point_id` FOREIGN KEY (`pickup_point_id`) REFERENCES `pickup_points` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `careers`
--

LOCK TABLES `careers` WRITE;
/*!40000 ALTER TABLE `careers` DISABLE KEYS */;
/*!40000 ALTER TABLE `careers` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `city`
--

DROP TABLE IF EXISTS `city`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `city` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `county_id` bigint DEFAULT NULL,
  `state_id` bigint NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `fp_city_name_unique` (`name`),
  UNIQUE KEY `fp_city_name_state_id_unique` (`name`,`state_id`),
  KEY `fk_t_city_countyId_t_county_id` (`county_id`),
  CONSTRAINT `fk_t_city_countyId_t_county_id` FOREIGN KEY (`county_id`) REFERENCES `county` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `city`
--

LOCK TABLES `city` WRITE;
/*!40000 ALTER TABLE `city` DISABLE KEYS */;
INSERT INTO `city` VALUES (1,'San Marcos',1,1),(2,'Point Loma',1,1),(3,'La Mesa',1,1),(4,'4S Ranch',1,1),(5,'Solana Beach',1,1),(6,'Carlsbad',1,1),(7,'Rancho Bernardo',1,1),(8,'La Jolla',1,1),(9,'Escondido',1,1),(10,'San Diego',1,1);
/*!40000 ALTER TABLE `city` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `contact_us`
--

DROP TABLE IF EXISTS `contact_us`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `contact_us` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `email` varchar(255) DEFAULT NULL,
  `message` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `phone` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `contact_us`
--

LOCK TABLES `contact_us` WRITE;
/*!40000 ALTER TABLE `contact_us` DISABLE KEYS */;
INSERT INTO `contact_us` VALUES (1,'hhimanshusharma70@gmail.com','Test ','Himanshu','9958898029');
/*!40000 ALTER TABLE `contact_us` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `country`
--

DROP TABLE IF EXISTS `country`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `country` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `country_code` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `country_name_country_code_name_countryCode_unique` (`name`,`country_code`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `country`
--

LOCK TABLES `country` WRITE;
/*!40000 ALTER TABLE `country` DISABLE KEYS */;
INSERT INTO `country` VALUES (1,'U.S','United States');
/*!40000 ALTER TABLE `country` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `county`
--

DROP TABLE IF EXISTS `county`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `county` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `state_id` bigint NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `fp_county_name_unique` (`name`),
  UNIQUE KEY `fp_county_name_state_id_unique` (`name`,`state_id`),
  KEY `fk_t_county_stateId_t_state_id` (`state_id`),
  CONSTRAINT `fk_t_county_stateId_t_state_id` FOREIGN KEY (`state_id`) REFERENCES `state` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `county`
--

LOCK TABLES `county` WRITE;
/*!40000 ALTER TABLE `county` DISABLE KEYS */;
INSERT INTO `county` VALUES (2,'Inland Empire',1),(3,'Orange County',1),(1,'San Diego County',1);
/*!40000 ALTER TABLE `county` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `employers`
--

DROP TABLE IF EXISTS `employers`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `employers` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `comments` varchar(255) DEFAULT NULL,
  `end_date` date DEFAULT NULL,
  `is_applicable` bit(1) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `phone` varchar(255) DEFAULT NULL,
  `reason` varchar(255) DEFAULT NULL,
  `start_date` date DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `employers`
--

LOCK TABLES `employers` WRITE;
/*!40000 ALTER TABLE `employers` DISABLE KEYS */;
/*!40000 ALTER TABLE `employers` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `feedbacks`
--

DROP TABLE IF EXISTS `feedbacks`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `feedbacks` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `comment` varchar(255) DEFAULT NULL,
  `compliment` varchar(255) DEFAULT NULL,
  `contact_mode` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `first_name` varchar(255) DEFAULT NULL,
  `last_name` varchar(255) DEFAULT NULL,
  `mailing_address_line1` varchar(255) DEFAULT NULL,
  `mailing_address_line2` varchar(255) DEFAULT NULL,
  `phone` varchar(255) DEFAULT NULL,
  `posting_dt` datetime DEFAULT NULL,
  `time_period` varchar(255) DEFAULT NULL,
  `zip` varchar(255) DEFAULT NULL,
  `city_id` bigint NOT NULL,
  `health_concern_id` bigint DEFAULT NULL,
  `pickup_point_id` bigint NOT NULL,
  `product_concern_id` bigint DEFAULT NULL,
  `service_concern_id` bigint DEFAULT NULL,
  `state_id` bigint NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_t_feedbacks_cityId_t_city_id` (`city_id`),
  KEY `fk_t_feedbacks_healthConcernId_t_health_concern_id` (`health_concern_id`),
  KEY `fk_t_feedbacks_pickupPointId_t_pickup_point_id` (`pickup_point_id`),
  KEY `fk_t_feedbacks_productConcernId_t_product_concern_id` (`product_concern_id`),
  KEY `fk_t_feedbacks_serviceConcernId_t_service_concern_id` (`service_concern_id`),
  KEY `fk_t_feedbacks_stateId_t_state_id` (`state_id`),
  CONSTRAINT `fk_t_feedbacks_cityId_t_city_id` FOREIGN KEY (`city_id`) REFERENCES `city` (`id`),
  CONSTRAINT `fk_t_feedbacks_healthConcernId_t_health_concern_id` FOREIGN KEY (`health_concern_id`) REFERENCES `health_concern` (`id`),
  CONSTRAINT `fk_t_feedbacks_pickupPointId_t_pickup_point_id` FOREIGN KEY (`pickup_point_id`) REFERENCES `pickup_points` (`id`),
  CONSTRAINT `fk_t_feedbacks_productConcernId_t_product_concern_id` FOREIGN KEY (`product_concern_id`) REFERENCES `product_concern` (`id`),
  CONSTRAINT `fk_t_feedbacks_serviceConcernId_t_service_concern_id` FOREIGN KEY (`service_concern_id`) REFERENCES `service_concern` (`id`),
  CONSTRAINT `fk_t_feedbacks_stateId_t_state_id` FOREIGN KEY (`state_id`) REFERENCES `state` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `feedbacks`
--

LOCK TABLES `feedbacks` WRITE;
/*!40000 ALTER TABLE `feedbacks` DISABLE KEYS */;
/*!40000 ALTER TABLE `feedbacks` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `health_concern`
--

DROP TABLE IF EXISTS `health_concern`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `health_concern` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `fp_health_concern_name_unique` (`name`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `health_concern`
--

LOCK TABLES `health_concern` WRITE;
/*!40000 ALTER TABLE `health_concern` DISABLE KEYS */;
INSERT INTO `health_concern` VALUES (1,' Allergic Reaction '),(4,'Emploee Not Handwashing'),(3,'Foreign Object'),(2,'Illness'),(5,'Other');
/*!40000 ALTER TABLE `health_concern` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `job_types`
--

DROP TABLE IF EXISTS `job_types`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `job_types` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `job_types`
--

LOCK TABLES `job_types` WRITE;
/*!40000 ALTER TABLE `job_types` DISABLE KEYS */;
INSERT INTO `job_types` VALUES (1,'Full Time'),(2,'Part Time');
/*!40000 ALTER TABLE `job_types` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `jobs`
--

DROP TABLE IF EXISTS `jobs`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `jobs` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `company_name` varchar(255) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `image_url` varchar(255) DEFAULT NULL,
  `is_expired` bit(1) NOT NULL,
  `posting_dt` datetime DEFAULT NULL,
  `title` varchar(255) DEFAULT NULL,
  `job_type_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `fp_jobs_title_unique` (`title`),
  KEY `fk_t_jobs_jobTypeId_t_job_type_id` (`job_type_id`),
  CONSTRAINT `fk_t_jobs_jobTypeId_t_job_type_id` FOREIGN KEY (`job_type_id`) REFERENCES `job_types` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `jobs`
--

LOCK TABLES `jobs` WRITE;
/*!40000 ALTER TABLE `jobs` DISABLE KEYS */;
INSERT INTO `jobs` VALUES (2,'Primos Mexican Food','Assistant Store Leader','string',_binary '\0','2021-04-12 13:47:22','Assistant Store Leader',1),(4,'Primos Mexican Food','Cashier','string',_binary '\0','2021-04-12 13:47:22','Cashier',2);
/*!40000 ALTER TABLE `jobs` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `jobs_pickup_points`
--

DROP TABLE IF EXISTS `jobs_pickup_points`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `jobs_pickup_points` (
  `jobs_id` bigint NOT NULL,
  `pickup_point_id` bigint NOT NULL,
  PRIMARY KEY (`jobs_id`,`pickup_point_id`),
  KEY `fk_t_pickup_points_pickup_point_id_t_pickupPointId` (`pickup_point_id`),
  CONSTRAINT `fk_t_pickup_points_jobs_id_t_jobsId` FOREIGN KEY (`jobs_id`) REFERENCES `jobs` (`id`),
  CONSTRAINT `fk_t_pickup_points_pickup_point_id_t_pickupPointId` FOREIGN KEY (`pickup_point_id`) REFERENCES `pickup_points` (`id`),
  CONSTRAINT `FKlnb2fg8atdbna5gn7cbyvcu8v` FOREIGN KEY (`jobs_id`) REFERENCES `pickup_points` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `jobs_pickup_points`
--

LOCK TABLES `jobs_pickup_points` WRITE;
/*!40000 ALTER TABLE `jobs_pickup_points` DISABLE KEYS */;
INSERT INTO `jobs_pickup_points` VALUES (2,1),(4,4);
/*!40000 ALTER TABLE `jobs_pickup_points` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `payments`
--

DROP TABLE IF EXISTS `payments`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `payments` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `amount` decimal(19,2) DEFAULT NULL,
  `created_on` datetime DEFAULT NULL,
  `currency` varchar(255) DEFAULT NULL,
  `payment_type` varchar(255) DEFAULT NULL,
  `user_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_t_payment_userId_t_user_id` (`user_id`),
  CONSTRAINT `fk_t_payment_userId_t_user_id` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `payments`
--

LOCK TABLES `payments` WRITE;
/*!40000 ALTER TABLE `payments` DISABLE KEYS */;
/*!40000 ALTER TABLE `payments` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `pickup_hours`
--

DROP TABLE IF EXISTS `pickup_hours`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `pickup_hours` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `fp_pickup_hours_name_unique` (`name`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `pickup_hours`
--

LOCK TABLES `pickup_hours` WRITE;
/*!40000 ALTER TABLE `pickup_hours` DISABLE KEYS */;
INSERT INTO `pickup_hours` VALUES (3,'7am - 10pm Daily'),(8,'7am - 9pm Daily'),(1,'8am - 10pm Daily'),(2,'8am - 9pm Daily'),(4,'Mon-Sat 7am-9pm'),(6,'Mon-Sat: 6am - 9pm'),(5,'Sun 8am-9pm'),(7,'Sun: 7am - 9pm');
/*!40000 ALTER TABLE `pickup_hours` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `pickup_point_notes`
--

DROP TABLE IF EXISTS `pickup_point_notes`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `pickup_point_notes` (
  `pickup_point_id` bigint NOT NULL,
  `special_notes` varchar(255) DEFAULT NULL,
  UNIQUE KEY `pickup_point_id_special_notes` (`pickup_point_id`,`special_notes`),
  CONSTRAINT `FKjepmm12nqfmhdyuxbjkrw97gd` FOREIGN KEY (`pickup_point_id`) REFERENCES `pickup_points` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `pickup_point_notes`
--

LOCK TABLES `pickup_point_notes` WRITE;
/*!40000 ALTER TABLE `pickup_point_notes` DISABLE KEYS */;
/*!40000 ALTER TABLE `pickup_point_notes` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `pickup_points`
--

DROP TABLE IF EXISTS `pickup_points`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `pickup_points` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `drive_thru_avlb` bit(1) NOT NULL,
  `is_beer_wine_avlb` bit(1) NOT NULL,
  `is_cantina_hrs_avlb` bit(1) NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  `outdoor_dining_avlb` bit(1) NOT NULL,
  `phone` varchar(255) DEFAULT NULL,
  `address_id` bigint NOT NULL,
  `county_id` bigint NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_t_pickup_points_addressId_t_address_id` (`address_id`),
  KEY `fk_t_pickup_points_countyIdId_t_county_id` (`county_id`),
  CONSTRAINT `fk_t_pickup_points_addressId_t_address_id` FOREIGN KEY (`address_id`) REFERENCES `address` (`id`),
  CONSTRAINT `fk_t_pickup_points_countyIdId_t_county_id` FOREIGN KEY (`county_id`) REFERENCES `county` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `pickup_points`
--

LOCK TABLES `pickup_points` WRITE;
/*!40000 ALTER TABLE `pickup_points` DISABLE KEYS */;
INSERT INTO `pickup_points` VALUES (1,_binary '\0',_binary '',_binary '\0','San Marcos',_binary '\0','760.471.8226',1,1),(2,_binary '\0',_binary '',_binary '\0','Point Loma',_binary '','619.224.8226',2,1),(3,_binary '',_binary '',_binary '\0','La Mesa',_binary '\0','619.698.8226',3,1),(4,_binary '\0',_binary '',_binary '\0','4S Ranch',_binary '','858.592.0960',4,1),(5,_binary '\0',_binary '',_binary '\0','Solana Beach',_binary '','858.792.8226',5,1),(6,_binary '\0',_binary '\0',_binary '\0','Carlsbad',_binary '\0','760.431.0202',6,1),(7,_binary '\0',_binary '\0',_binary '\0','Rancho Bernardo',_binary '\0','858.674.1141',7,1),(8,_binary '\0',_binary '',_binary '','La Jolla',_binary '','858.638.0003',8,1),(9,_binary '\0',_binary '',_binary '','Escondido',_binary '','760.735.8226',9,1),(10,_binary '\0',_binary '',_binary '\0','University City',_binary '','858.412.4688',10,1);
/*!40000 ALTER TABLE `pickup_points` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `pickup_points_pickup_hours`
--

DROP TABLE IF EXISTS `pickup_points_pickup_hours`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `pickup_points_pickup_hours` (
  `pickup_points_id` bigint NOT NULL,
  `pickup_hour_id` bigint NOT NULL,
  PRIMARY KEY (`pickup_points_id`,`pickup_hour_id`),
  KEY `fk_t_pickup_hours_hour_id_t_hourId` (`pickup_hour_id`),
  CONSTRAINT `fk_t_pickup_hours_hour_id_t_hourId` FOREIGN KEY (`pickup_hour_id`) REFERENCES `pickup_hours` (`id`),
  CONSTRAINT `fk_t_pickup_hours_pickup_point_id_t_pickupPointId` FOREIGN KEY (`pickup_points_id`) REFERENCES `pickup_points` (`id`),
  CONSTRAINT `FKmgcfs4g647wy97a2s90ncfr12` FOREIGN KEY (`pickup_hour_id`) REFERENCES `pickup_points` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `pickup_points_pickup_hours`
--

LOCK TABLES `pickup_points_pickup_hours` WRITE;
/*!40000 ALTER TABLE `pickup_points_pickup_hours` DISABLE KEYS */;
INSERT INTO `pickup_points_pickup_hours` VALUES (1,1),(2,2),(3,3),(4,3),(5,4),(5,5),(6,6),(6,7),(7,8),(10,8);
/*!40000 ALTER TABLE `pickup_points_pickup_hours` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `pickup_points_service_providers`
--

DROP TABLE IF EXISTS `pickup_points_service_providers`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `pickup_points_service_providers` (
  `pickup_points_id` bigint NOT NULL,
  `service_provider_id` bigint NOT NULL,
  PRIMARY KEY (`pickup_points_id`,`service_provider_id`),
  KEY `fk_t_service_providers_provider_id_t_providerId` (`service_provider_id`),
  CONSTRAINT `fk_t_service_provider_pickup_point_id_t_pickupPointId` FOREIGN KEY (`pickup_points_id`) REFERENCES `pickup_points` (`id`),
  CONSTRAINT `fk_t_service_providers_provider_id_t_providerId` FOREIGN KEY (`service_provider_id`) REFERENCES `service_providers` (`id`),
  CONSTRAINT `FKmnf25h2o0iwb3hfe61jx252i` FOREIGN KEY (`service_provider_id`) REFERENCES `pickup_points` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `pickup_points_service_providers`
--

LOCK TABLES `pickup_points_service_providers` WRITE;
/*!40000 ALTER TABLE `pickup_points_service_providers` DISABLE KEYS */;
INSERT INTO `pickup_points_service_providers` VALUES (1,1),(2,1),(3,1),(4,1),(5,1),(6,1),(7,1),(8,1),(9,1),(10,1);
/*!40000 ALTER TABLE `pickup_points_service_providers` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `product_concern`
--

DROP TABLE IF EXISTS `product_concern`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `product_concern` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `fp_product_concern_name_unique` (`name`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `product_concern`
--

LOCK TABLES `product_concern` WRITE;
/*!40000 ALTER TABLE `product_concern` DISABLE KEYS */;
INSERT INTO `product_concern` VALUES (2,'Cold Product'),(3,'Missing Item'),(7,'Other'),(6,'Product Availability'),(4,'Product Presentation'),(5,'Quality of Product'),(1,'Wrong Product');
/*!40000 ALTER TABLE `product_concern` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `revinfo`
--

DROP TABLE IF EXISTS `revinfo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `revinfo` (
  `rev` int NOT NULL AUTO_INCREMENT,
  `revtstmp` bigint DEFAULT NULL,
  PRIMARY KEY (`rev`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `revinfo`
--

LOCK TABLES `revinfo` WRITE;
/*!40000 ALTER TABLE `revinfo` DISABLE KEYS */;
/*!40000 ALTER TABLE `revinfo` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `service_concern`
--

DROP TABLE IF EXISTS `service_concern`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `service_concern` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `fp_service_concern_name_unique` (`name`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `service_concern`
--

LOCK TABLES `service_concern` WRITE;
/*!40000 ALTER TABLE `service_concern` DISABLE KEYS */;
INSERT INTO `service_concern` VALUES (3,'Cleanliness of Restaurants'),(2,'Employee Appearance'),(1,'Employee Attitude'),(6,'Other'),(5,'Restaurant closed'),(4,'Speed of service');
/*!40000 ALTER TABLE `service_concern` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `service_providers`
--

DROP TABLE IF EXISTS `service_providers`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `service_providers` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `image_url` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `fp_service_providers_name_unique` (`name`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `service_providers`
--

LOCK TABLES `service_providers` WRITE;
/*!40000 ALTER TABLE `service_providers` DISABLE KEYS */;
INSERT INTO `service_providers` VALUES (1,'','Uber Eats'),(2,NULL,'Doordash'),(3,NULL,'Postmates');
/*!40000 ALTER TABLE `service_providers` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `state`
--

DROP TABLE IF EXISTS `state`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `state` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `country_id` bigint NOT NULL,
  `code` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `fp_state_name_unique` (`name`),
  UNIQUE KEY `fp_state_name_country_id_unique` (`name`,`country_id`),
  UNIQUE KEY `fp_state_name_code_country_id_unique` (`name`,`code`,`country_id`),
  KEY `fk_t_country_stateId_t_state_id` (`country_id`),
  CONSTRAINT `fk_t_country_stateId_t_state_id` FOREIGN KEY (`country_id`) REFERENCES `country` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `state`
--

LOCK TABLES `state` WRITE;
/*!40000 ALTER TABLE `state` DISABLE KEYS */;
INSERT INTO `state` VALUES (1,'California',1,'CA');
/*!40000 ALTER TABLE `state` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `users` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `email` varchar(255) DEFAULT NULL,
  `first_name` varchar(255) DEFAULT NULL,
  `is_terms_policy_agreed` bit(1) NOT NULL,
  `last_name` varchar(255) DEFAULT NULL,
  `login_name` varchar(255) DEFAULT NULL,
  `middle_name` varchar(255) DEFAULT NULL,
  `phone` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `user_loginName_unique` (`login_name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
/*!40000 ALTER TABLE `users` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users_aud`
--

DROP TABLE IF EXISTS `users_aud`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `users_aud` (
  `id` bigint NOT NULL,
  `rev` int NOT NULL,
  `revtype` tinyint DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `first_name` varchar(255) DEFAULT NULL,
  `is_terms_policy_agreed` bit(1) DEFAULT NULL,
  `last_name` varchar(255) DEFAULT NULL,
  `login_name` varchar(255) DEFAULT NULL,
  `middle_name` varchar(255) DEFAULT NULL,
  `phone` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`,`rev`),
  KEY `FKc4vk4tui2la36415jpgm9leoq` (`rev`),
  CONSTRAINT `FKc4vk4tui2la36415jpgm9leoq` FOREIGN KEY (`rev`) REFERENCES `revinfo` (`rev`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users_aud`
--

LOCK TABLES `users_aud` WRITE;
/*!40000 ALTER TABLE `users_aud` DISABLE KEYS */;
/*!40000 ALTER TABLE `users_aud` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2021-04-15 17:55:52

------------------------------------------------------------------- New Queries -------------------

INSERT INTO `address` (`id`, `address1`, `zip`, `city_id`, `country_id`, `state_id`) VALUES ('11', '40720 Winchester Rd', 'CA 92591', '11', '1', '1');
INSERT INTO `address` (`id`, `address1`, `zip`, `city_id`, `country_id`, `state_id`) VALUES ('12', '39252 Winchester Rd.', 'CA 92563', '12', '1', '1');
INSERT INTO `address` (`id`, `address1`, `zip`, `city_id`, `country_id`, `state_id`) VALUES ('13', '40981 Cal Oaks Rd.', 'CA 92562', '12', '1', '1');
INSERT INTO `address` (`id`, `address1`, `zip`, `city_id`, `country_id`, `state_id`) VALUES ('14', '30628 Benton Rd.', 'CA 92596', '13', '1', '1');
INSERT INTO `address` (`id`, `address1`, `zip`, `city_id`, `country_id`, `state_id`) VALUES ('15', '26825 Newport Rd', 'CA 92584', '14', '1', '1');
INSERT INTO `address` (`id`, `address1`, `zip`, `city_id`, `country_id`, `state_id`) VALUES ('16', '3150 Case Rd.', 'CA 92570', '15', '1', '1');
INSERT INTO `address` (`id`, `address1`, `zip`, `city_id`, `country_id`, `state_id`) VALUES ('17', '15390 Fairfield Ranch Rd.', 'CA 91709', '16', '1', '1');
INSERT INTO `address` (`id`, `address1`, `zip`, `city_id`, `country_id`, `state_id`) VALUES ('18', '21612 Plano Trabuco Road', 'CA 92679', '17', '1', '1');



INSERT INTO `pickup_points` (`id`, `drive_thru_avlb`, `is_beer_wine_avlb`, `is_cantina_hrs_avlb`, `name`, `outdoor_dining_avlb`, `phone`, `address_id`, `county_id`) VALUES ('11', b'1', b'0', b'0', 'Temecula', b'1', '951.719.3600', '11', '2');
INSERT INTO `pickup_points` (`id`, `drive_thru_avlb`, `is_beer_wine_avlb`, `is_cantina_hrs_avlb`, `name`, `outdoor_dining_avlb`, `phone`, `address_id`, `county_id`) VALUES ('12', b'0', b'1', b'0', 'Murrieta', b'0', '951.600.9107', '12', '2');
INSERT INTO `pickup_points` (`id`, `drive_thru_avlb`, `is_beer_wine_avlb`, `is_cantina_hrs_avlb`, `name`, `outdoor_dining_avlb`, `phone`, `address_id`, `county_id`) VALUES ('13', b'1', b'0', b'0', 'Murrieta', b'0', '951.461.0441', '13', '2');
INSERT INTO `pickup_points` (`id`, `drive_thru_avlb`, `is_beer_wine_avlb`, `is_cantina_hrs_avlb`, `name`, `outdoor_dining_avlb`, `phone`, `address_id`, `county_id`) VALUES ('14', b'0', b'1', b'0', 'French Valley', b'0', '951.926.8700', '14', '2');
INSERT INTO `pickup_points` (`id`, `drive_thru_avlb`, `is_beer_wine_avlb`, `is_cantina_hrs_avlb`, `name`, `outdoor_dining_avlb`, `phone`, `address_id`, `county_id`) VALUES ('15', b'1', b'0', b'0', 'Menifee', b'0', '951.244.7746', '15', '2');
INSERT INTO `pickup_points` (`id`, `drive_thru_avlb`, `is_beer_wine_avlb`, `is_cantina_hrs_avlb`, `name`, `outdoor_dining_avlb`, `phone`, `address_id`, `county_id`) VALUES ('16', b'0', b'1', b'0', 'Perris', b'0', '951.928.1077', '16', '2');
INSERT INTO `pickup_points` (`id`, `drive_thru_avlb`, `is_beer_wine_avlb`, `is_cantina_hrs_avlb`, `name`, `outdoor_dining_avlb`, `phone`, `address_id`, `county_id`) VALUES ('17', b'1', b'1', b'0', 'Chino Hills', b'0', '909.393.8040', '17', '2');
INSERT INTO `pickup_points` (`id`, `drive_thru_avlb`, `is_beer_wine_avlb`, `is_cantina_hrs_avlb`, `name`, `outdoor_dining_avlb`, `phone`, `address_id`, `county_id`) VALUES ('18', b'0', b'0', b'0', 'Santa Margarita', b'1', '949.858.0311', '18', '3');

ALTER TABLE `pickup_points_pickup_hours` DROP FOREIGN KEY `FKmgcfs4g647wy97a2s90ncfr12`;
ALTER TABLE `pickup_points_service_providers`	DROP FOREIGN KEY `FKmnf25h2o0iwb3hfe61jx252i`;
INSERT INTO `pickup_hours` (`id`, `name`) VALUES ('9', '6am - 11pm Daily');
INSERT INTO `pickup_hours` (`id`, `name`) VALUES ('10', '7am - 11pm Daily');

INSERT INTO `pickup_points_pickup_hours` (`pickup_points_id`, `pickup_hour_id`) VALUES ('11', '9');
INSERT INTO `pickup_points_pickup_hours` (`pickup_points_id`, `pickup_hour_id`) VALUES ('12', '8');
INSERT INTO `pickup_points_pickup_hours` (`pickup_points_id`, `pickup_hour_id`) VALUES ('13', '9');
INSERT INTO `pickup_points_pickup_hours` (`pickup_points_id`, `pickup_hour_id`) VALUES ('14', '3');
INSERT INTO `pickup_points_pickup_hours` (`pickup_points_id`, `pickup_hour_id`) VALUES ('15', '10');
INSERT INTO `pickup_points_pickup_hours` (`pickup_points_id`, `pickup_hour_id`) VALUES ('16', '2');
INSERT INTO `pickup_points_pickup_hours` (`pickup_points_id`, `pickup_hour_id`) VALUES ('17', '10');
INSERT INTO `pickup_points_pickup_hours` (`pickup_points_id`, `pickup_hour_id`) VALUES ('18', '3');

INSERT INTO `pickup_points_service_providers` (`pickup_points_id`, `service_provider_id`) VALUES ('11', '1');
INSERT INTO `pickup_points_service_providers` (`pickup_points_id`, `service_provider_id`) VALUES ('12', '1');
INSERT INTO `pickup_points_service_providers` (`pickup_points_id`, `service_provider_id`) VALUES ('13', '1');
INSERT INTO `pickup_points_service_providers` (`pickup_points_id`, `service_provider_id`) VALUES ('14', '1');
INSERT INTO `pickup_points_service_providers` (`pickup_points_id`, `service_provider_id`) VALUES ('15', '1');
INSERT INTO `pickup_points_service_providers` (`pickup_points_id`, `service_provider_id`) VALUES ('16', '1');
INSERT INTO `pickup_points_service_providers` (`pickup_points_id`, `service_provider_id`) VALUES ('17', '1');
INSERT INTO `pickup_points_service_providers` (`pickup_points_id`, `service_provider_id`) VALUES ('18', '1');
alter table feedbacks MODIFY  city_id  bigint null;
alter table feedbacks MODIFY  state_id  bigint null;
alter table feedbacks ADD column visit_type varchar(255); 
alter table feedbacks DROP column time_period;

-- Himanshu 10-05-2021
alter table careers MODIFY first_name varchar(255) NOT NULL;
alter table careers MODIFY last_name varchar(255) NOT NULL;
alter table careers MODIFY mailing_address varchar(255) NOT NULL;
alter table careers MODIFY phone varchar(255) NOT NULL; 
alter table careers DROP  email;
alter table careers MODIFY county_id bigint null;
alter table careers MODIFY pickup_point_id bigint null;
alter table employers DROP comments;
alter table careers add column comments varchar(255);
alter table feedbacks DROP FOREIGN KEY fk_t_feedbacks_cityId_t_city_id;
alter table feedbacks drop column city_id;
alter table feedbacks add column city varchar(255);
alter table city add constraint fk_t_city_stateId_t_state_id foreign key (state_id) references state (id);

-- Ankit 11-05-2021  --Document Table Queries
create table document (id bigint not null auto_increment, creation_date datetime, last_modified_date datetime, content_type varchar(255), document_description varchar(255), document_format varchar(255), document_name varchar(255), document_type varchar(255), file_name varchar(255), file_storageuri varchar(255), fileuri varchar(255), guid varchar(255), is_deleted boolean default false, created_by_id bigint, last_modified_by_id bigint, primary key (id)) engine=InnoDB;
create table document_aud (id bigint not null, rev integer not null, revtype tinyint, creation_date datetime, last_modified_date datetime, content_type varchar(255), document_description varchar(255), document_format varchar(255), document_name varchar(255), document_type varchar(255), file_name varchar(255), file_storageuri varchar(255), fileuri varchar(255), guid varchar(255), is_deleted boolean default false, created_by_id bigint, last_modified_by_id bigint, primary key (id, rev)) engine=InnoDB;
create table token (id bigint not null auto_increment, expiryms bigint, issued_date datetime, token varchar(255), token_for varchar(255), token_type varchar(255), primary key (id)) engine=InnoDB;
alter table document drop index UK_ga5si8th3y27p2jpj5c3p760g;
alter table document add constraint UK_ga5si8th3y27p2jpj5c3p760g unique (guid);
alter table document add constraint FKadxfrvn65sap3tvuacb3wid10 foreign key (created_by_id) references users (id);
alter table document add constraint FKrfmyplhd4yxngwvil8oy6aevk foreign key (last_modified_by_id) references users (id);
alter table document_aud add constraint FKnb1mvvdy2r5eufnxnwv45tu20 foreign key (rev) references revinfo (rev);
alter table users add column is_deleted boolean default false; 
alter table careers DROP column doc;
alter table careers add column doc_url varchar(255);
alter table careers add column document_type varchar(255);


create table franchise_info (id bigint not null auto_increment, title varchar(255), primary key (id)) engine=InnoDB;
create table franchise_liquid_assets (id bigint not null auto_increment, title varchar(255), primary key (id)) engine=InnoDB;
create table franchise_net_worth (id bigint not null auto_increment, title varchar(255), primary key (id)) engine=InnoDB;
create table franchises (id bigint not null auto_increment, additional_comments varchar(255), city varchar(255), company varchar(255), email varchar(255) not null, first_name varchar(255), franchisse_with_other_brand bit not null, has_equity_partner bit not null, has_operational_partner bit not null, has_operational_partner_in_area bit not null, has_secured_finance bit not null, last_name varchar(255), mailing_address_line1 varchar(255), mailing_address_line2 varchar(255), office_phone varchar(255) not null, zip varchar(255), country_to_develop_id bigint not null, franchise_info_id bigint not null, franchise_liquid_assets_id bigint not null, franchise_net_worth_id bigint not null, restaurants_capacity_id bigint not null, state_id bigint, state_to_develop_id bigint not null, primary key (id)) engine=InnoDB;
create table restaurants_capacity (id bigint not null auto_increment, description varchar(255), title varchar(255), primary key (id)) engine=InnoDB;
create table site_information (id bigint not null auto_increment, city varchar(255), mailing_address_line1 varchar(255), mailing_address_line2 varchar(255), office_phone varchar(255) not null, zip varchar(255), state_id bigint, primary key (id)) engine=InnoDB;
create table site_submittal (id bigint not null auto_increment, asking_price varchar(255), asking_rent varchar(255), building_size varchar(255), dimension varchar(255), is_lease bit not null, is_purchase bit not null, lot_size varchar(255), primary key (id)) engine=InnoDB;
create table site_submitters (id bigint not null auto_increment, city varchar(255), company varchar(255), doc_url varchar(255), document_type varchar(255), email varchar(255) not null, fax varchar(255) not null, first_name varchar(255), last_name varchar(255), mailing_address_line1 varchar(255), mailing_address_line2 varchar(255), mobile_phone varchar(255) not null, office_phone varchar(255) not null, zip varchar(255), site_information_id bigint, site_submittal_id bigint, site_type_id bigint, state_id bigint, primary key (id)) engine=InnoDB;
create table site_type (id bigint not null auto_increment, end_cap bit not null, has_free_standing_with_drive_thru bit not null, in_line bit not null, is_pad_site bit not null, office_phone varchar(255) not null, primary key (id)) engine=InnoDB;
alter table franchise_info drop index franchise_info_title_unique;
alter table franchise_info add constraint franchise_info_title_unique unique (title);
alter table franchise_liquid_assets drop index franchise_liquid_assets_title_unique;
 alter table franchise_liquid_assets add constraint franchise_liquid_assets_title_unique unique (title);
alter table franchise_net_worth drop index franchise_net_worth_assets_title_unique;
alter table franchise_net_worth add constraint franchise_net_worth_assets_title_unique unique (title);

alter table restaurants_capacity drop index restaurants_capacity_title_unique;
alter table restaurants_capacity add constraint restaurants_capacity_title_unique unique (title);
alter table city add constraint fk_t_city_stateId_t_state_id foreign key (state_id) references state (id);
alter table franchises add constraint fk_t_franchise_countryToDevelopId_t_country_to_develop_id foreign key (country_to_develop_id) references country (id);
alter table franchises add constraint fk_t_franchise_infoId_t_info_id foreign key (franchise_info_id) references franchise_info (id);
alter table franchises add constraint fk_t_franchise_liquidAssetsId_t_liquid_assets_id foreign key (franchise_liquid_assets_id) references franchise_liquid_assets (id);
alter table franchises add constraint fk_t_franchise_netWorthId_t_net_worth_id foreign key (franchise_net_worth_id) references franchise_net_worth (id);
alter table franchises add constraint fk_t_franchise_restaurantsCapacityId_t_restaurants_capacity_id foreign key (restaurants_capacity_id) references restaurants_capacity (id);
alter table franchises add constraint fk_t_franchise_stateId_t_state_id foreign key (state_id) references state (id);
alter table franchises add constraint fk_t_franchise_stateToDevelopId_t_state_to_develop_id foreign key (state_to_develop_id) references state (id);
alter table site_information add constraint fk_t_site_information_stateId_t_state_id foreign key (state_id) references state (id);
alter table site_submitters add constraint fk_t_site_submitters_siteInformationId_t_site_information_id foreign key (site_information_id) references site_information (id);
alter table site_submitters add constraint fk_t_site_submitters_siteSubmittalId_t_site_submittal_id foreign key (site_submittal_id) references site_submittal (id);
alter table site_submitters add constraint fk_t_site_submitters_siteTypeId_t_site_type_id foreign key (site_type_id) references site_type (id);
alter table site_submitters add constraint fk_t_site_submitters_stateId_t_state_id foreign key (state_id) references state (id);



INSERT INTO franchise_info (`id`, `title`) VALUES ('1', 'Referral');
INSERT INTO franchise_info (`id`, `title`) VALUES ('2', 'Trade Publication');
INSERT INTO franchise_info (`id`, `title`) VALUES ('3', 'Brand Awareness');
INSERT INTO franchise_info (`id`, `title`) VALUES ('4', 'Trade Show');
INSERT INTO franchise_info (`id`, `title`) VALUES ('5', 'Search Engine');
INSERT INTO franchise_info (`id`, `title`) VALUES ('6', 'Other');


insert into country_to_develop (name) values ("United States");
insert into country_to_develop (name) values ("Outside the US");

insert into franchise_liquid_assets (title) values ("Under $300,000");
insert into franchise_liquid_assets (title) values ("$300,000 - $500,000");
insert into franchise_liquid_assets (title) values ("$500,000+");


insert into franchise_net_worth (title) values ("Under $1 Million");
insert into franchise_net_worth (title) values ("$1-$5 Million");
insert into franchise_net_worth (title) values ("$6-$10 Million");
insert into franchise_net_worth (title) values ("$10+ Million");



INSERT INTO restaurants_capacity (`id`, `description`, `title`) VALUES ('1', '3 TO 6', '3 TO 6');
INSERT INTO restaurants_capacity (`id`, `description`, `title`) VALUES ('2', '7 TO 15', '7 TO 15');
INSERT INTO restaurants_capacity (`id`, `description`, `title`) VALUES ('3', '16 TO 25', '16 TO 25');
INSERT INTO restaurants_capacity (`id`, `description`, `title`) VALUES ('4', 'MORE THAN 25', 'MORE THAN 25');

-- 25-05-2021 --Ankit Tyagi --Fund Raising
create table fund_raising (id bigint not null auto_increment, check_payable_to varchar(255), city varchar(255), comment varchar(1000), date datetime not null, email varchar(255) not null, first_name varchar(255) not null, last_name varchar(255) not null, mailing_address_line1 varchar(255), mailing_address_line2 varchar(255), organization_cause varchar(255) not null, phone varchar(255) not null, team_name varchar(255), zip varchar(255), state_id bigint, store_location_id bigint not null, primary key (id));
alter table fund_raising add constraint fk_t_foundRaising_stateId_t_state_id foreign key (state_id) references state (id);
alter table fund_raising add constraint fk_t_fundRaising_fundRaisingId_t_pickup_point_id foreign key (store_location_id) references pickup_points (id);

-- 10-06-2021 --Himanshu -- Changes for lat lng and Service Providers
alter table address drop constraint fk_t_address_cityId_t_city_id;
alter table address drop column city_id;
alter table address add column city varchar(255) not null;
alter table careers add column postal_address varchar(255);
alter table pickup_points add column lng varchar(255);
alter table pickup_points add column lat varchar(255);

UPDATE `pickup_points` SET `lat`='33.12349', `lng`='-117.16569' WHERE  `id`=1;
UPDATE `pickup_points` SET `lat`='32.72831', `lng`='-117.21772' WHERE  `id`=2;
UPDATE `pickup_points` SET `lat`='32.80224', `lng`='-117.04304' WHERE  `id`=3;
UPDATE `pickup_points` SET `lat`='33.01621', `lng`='-117.11076' WHERE  `id`=4;
UPDATE `pickup_points` SET `lat`='32.99586', `lng`='-117.25511' WHERE  `id`=5;
UPDATE `pickup_points` SET `lat`='33.13142', `lng`='-117.26062' WHERE  `id`=6;
UPDATE `pickup_points` SET `lat`='33.01729', `lng`='-117.07499' WHERE  `id`=7;
UPDATE `pickup_points` SET `lat`='32.86307', `lng`='-117.22437' WHERE  `id`=8;
UPDATE `pickup_points` SET `lat`='33.11256', `lng`='-117.10294' WHERE  `id`=9;
UPDATE `pickup_points` SET `lat`='32.85199', `lng`='-117.21594' WHERE  `id`=10;
UPDATE `pickup_points` SET `lat`='33.52628', `lng`='-117.15716' WHERE  `id`=11;
UPDATE `pickup_points` SET `lat`='33.55112', `lng`='-117.13878' WHERE  `id`=12;
UPDATE `pickup_points` SET `lat`='33.56833', `lng`='-117.20644' WHERE  `id`=13;
UPDATE `pickup_points` SET `lat`='33.59263', `lng`='-117.12366' WHERE  `id`=14;
UPDATE `pickup_points` SET `lat`='33.68461', `lng`='-117.19240' WHERE  `id`=15;
UPDATE `pickup_points` SET `lat`='33.74559', `lng`='-117.19196' WHERE  `id`=16;
UPDATE `pickup_points` SET `lat`='33.97312', `lng`='-117.68945' WHERE  `id`=17;
UPDATE `pickup_points` SET `lat`='33.64814', `lng`='-117.57618' WHERE  `id`=18;

UPDATE address SET `city`='San Marcos' WHERE  `id`=1;
UPDATE address SET `city`='Point Loma' WHERE  `id`=2;
UPDATE address SET `city`='La Mesa' WHERE  `id`=3;
UPDATE address SET `city`='4S Ranch' WHERE  `id`=4;
UPDATE address SET `city`='Solana Beach' WHERE  `id`=5;
UPDATE address SET `city`='Carlsbad' WHERE  `id`=6;
UPDATE address SET `city`='Rancho Bernardo' WHERE  `id`=7;
UPDATE address SET `city`='La Jolla' WHERE  `id`=8;
UPDATE address SET `city`='Escondido' WHERE  `id`=9;
UPDATE address SET `city`='San Diego' WHERE  `id`=10;
UPDATE address SET `city`='Temecula' WHERE  `id`=11;
UPDATE address SET `city`='Murrieta' WHERE  `id`=12;
UPDATE address SET `city`='Murrieta' WHERE  `id`=13;
UPDATE address SET `city`='Winchester' WHERE  `id`=14;
UPDATE address SET `city`='Menifee' WHERE  `id`=15;
UPDATE address SET `city`='Perris' WHERE  `id`=16;
UPDATE address SET `city`='Chino Hills' WHERE  `id`=17;
UPDATE address SET `city`='Trabuco Canyon' WHERE  `id`=18;


alter table pickup_points add column cake_url varchar(255);
drop table pickup_points_service_providers;
create table pickup_points_service_providers (pickup_point_id bigint not null, service_provider_id bigint not null, pickup_provider_url varchar(255), primary key (pickup_point_id, service_provider_id)) engine=InnoDB;
alter table pickup_points_service_providers add constraint fk_t_pickup_pts_sp_pickupPointId_t_pickup_point_id foreign key (pickup_point_id) references pickup_points (id);
alter table pickup_points_service_providers add constraint fk_t_pickup_pts_sp_serviceProviderId_t_service_provider_id foreign key (service_provider_id) references service_providers (id);

-- 16-06-2021
UPDATE `jobs` SET `title`='Cashier New' WHERE  `id`=4;
INSERT INTO `jobs` (`id`, `company_name`, `description`, `is_expired`, `title`,`posting_dt`,`job_type_id`) VALUES ('1', 'Primos Mexican Food', 'Cashier', b'0', 'Cashier','2021-06-11 19:42:08','2');
UPDATE `jobs` SET `description`='Cook', `image_url`='', `title`='Cook' WHERE  `id`=2;
INSERT INTO `jobs` (`id`, `company_name`, `description`, `is_expired`, `posting_dt`, `title`, `job_type_id`) VALUES ('3', 'Primos Mexican Food', 'Assistant Leader', b'0', '2021-06-11 19:46:45', 'Assistant Leader', '1');
UPDATE `jobs` SET `description`='Kitchen Leader', `image_url`='', `title`='Kitchen Leader', `job_type_id`='1' WHERE  `id`=4;
INSERT INTO `jobs` (`id`, `company_name`, `description`, `is_expired`, `posting_dt`, `title`, `job_type_id`) VALUES ('5', 'Primos Mexican Food', 'Store Leader', b'0', '2021-06-11 19:46:45', 'Store Leader', '1');
alter table state add column state_code varchar(255);
alter table state DROP column code ;
alter table state MODIFY country_id bigint null;
alter table country drop country_code;
alter table country add column countryiatacode varchar(255);
update state set state_code="CA", name="California" where id=1;
update country set countryiatacode="USA",name="United States"  where id=1;
alter table careers add column created_date datetime;
alter table contact_us add column created_date datetime;
alter table feedbacks add column created_date datetime;
alter table franchises add column created_date datetime;
alter table fund_raising add column created_date datetime;
alter table site_submitters add column created_date datetime;

--  18-06-2021
--State Listing 
UPDATE `state` SET `name`='Alabama', `state_code`='AL' WHERE  `id`=1;
INSERT INTO `state` (`id`, `name`, `state_code`,`country_id`) VALUES ('2','Alaska', 'AK',  '1');
INSERT INTO `state` (`id`, `name`, `state_code`,`country_id`) VALUES ('3','Arizona', 'AZ',  '1');
INSERT INTO `state` (`id`, `name`, `state_code`,`country_id`) VALUES ('4','Arkansas', 'AR',  '1');
INSERT INTO `state` (`id`, `name`, `state_code`,`country_id`) VALUES ('5','California', 'CA',  '1');
INSERT INTO `state` (`id`, `name`, `state_code`,`country_id`) VALUES ('6','Colorado', 'CO',  '1');
INSERT INTO `state` (`id`, `name`, `state_code`,`country_id`) VALUES ('7','Connecticut', 'CT',  '1');
INSERT INTO `state` (`id`, `name`, `state_code`,`country_id`) VALUES ('8','Delaware', 'DE',  '1');
INSERT INTO `state` (`id`, `name`, `state_code`,`country_id`) VALUES ('9','Florida', 'FL',  '1');
INSERT INTO `state` (`id`, `name`, `state_code`,`country_id`) VALUES ('10','Georgia', 'GA',  '1');
INSERT INTO `state` (`id`, `name`, `state_code`,`country_id`) VALUES ('11','Hawaii', 'HI',  '1');
INSERT INTO `state` (`id`, `name`, `state_code`,`country_id`) VALUES ('12','Idaho', 'ID',  '1');
INSERT INTO `state` (`id`, `name`, `state_code`,`country_id`) VALUES ('13','Illinois', 'IL',  '1');
INSERT INTO `state` (`id`, `name`, `state_code`,`country_id`) VALUES ('14','Indiana', 'IN',  '1');
INSERT INTO `state` (`id`, `name`, `state_code`,`country_id`) VALUES ('15','Iowa', 'IA',  '1');
INSERT INTO `state` (`id`, `name`, `state_code`,`country_id`) VALUES ('16','Kansas', 'KS',  '1');
INSERT INTO `state` (`id`, `name`, `state_code`,`country_id`) VALUES ('17','Kentucky', 'KY',  '1');
INSERT INTO `state` (`id`, `name`, `state_code`,`country_id`) VALUES ('18','Louisiana', 'LA',  '1');
INSERT INTO `state` (`id`, `name`, `state_code`,`country_id`) VALUES ('19','Maine', 'ME',  '1');
INSERT INTO `state` (`id`, `name`, `state_code`,`country_id`) VALUES ('20','Maryland', 'MD',  '1');
INSERT INTO `state` (`id`, `name`, `state_code`,`country_id`) VALUES ('21','Massachusetts', 'MA',  '1');
INSERT INTO `state` (`id`, `name`, `state_code`,`country_id`) VALUES ('22','Michigan', 'MI',  '1');
INSERT INTO `state` (`id`, `name`, `state_code`,`country_id`) VALUES ('23','Minnesota', 'MN',  '1');
INSERT INTO `state` (`id`, `name`, `state_code`,`country_id`) VALUES ('24','Mississippi', 'MS',  '1');
INSERT INTO `state` (`id`, `name`, `state_code`,`country_id`) VALUES ('25','Missouri', 'MO',  '1');
INSERT INTO `state` (`id`, `name`, `state_code`,`country_id`) VALUES ('26','Montana', 'MT',  '1');
INSERT INTO `state` (`id`, `name`, `state_code`,`country_id`) VALUES ('27','Nebraska', 'NE',  '1');
INSERT INTO `state` (`id`, `name`, `state_code`,`country_id`) VALUES ('28','Nevada', 'NV',  '1');
INSERT INTO `state` (`id`, `name`, `state_code`,`country_id`) VALUES ('29','New Hampshire', 'NH',  '1');
INSERT INTO `state` (`id`, `name`, `state_code`,`country_id`) VALUES ('30','New Jersey', 'NJ',  '1');
INSERT INTO `state` (`id`, `name`, `state_code`,`country_id`) VALUES ('31','New Mexico', 'NM',  '1');
INSERT INTO `state` (`id`, `name`, `state_code`,`country_id`) VALUES ('32','New York', 'NY',  '1');
INSERT INTO `state` (`id`, `name`, `state_code`,`country_id`) VALUES ('33','North Carolina', 'NC',  '1');
INSERT INTO `state` (`id`, `name`, `state_code`,`country_id`) VALUES ('34','North Dakota', 'ND',  '1');
INSERT INTO `state` (`id`, `name`, `state_code`,`country_id`) VALUES ('35','Ohio', 'OH',  '1');
INSERT INTO `state` (`id`, `name`, `state_code`,`country_id`) VALUES ('36','Oklahoma', 'OK',  '1');
INSERT INTO `state` (`id`, `name`, `state_code`,`country_id`) VALUES ('37','Oregon', 'OR',  '1');
INSERT INTO `state` (`id`, `name`, `state_code`,`country_id`) VALUES ('38','Pennsylvania', 'PA',  '1');
INSERT INTO `state` (`id`, `name`, `state_code`,`country_id`) VALUES ('39','Rhode Island', 'RI',  '1');
INSERT INTO `state` (`id`, `name`, `state_code`,`country_id`) VALUES ('40','South Carolina', 'SC',  '1');
INSERT INTO `state` (`id`, `name`, `state_code`,`country_id`) VALUES ('41','South Dakota', 'SD',  '1');
INSERT INTO `state` (`id`, `name`, `state_code`,`country_id`) VALUES ('42','Tennessee', 'TN',  '1');
INSERT INTO `state` (`id`, `name`, `state_code`,`country_id`) VALUES ('43','Texas', 'TX',  '1');
INSERT INTO `state` (`id`, `name`, `state_code`,`country_id`) VALUES ('44','Utah', 'UT',  '1');
INSERT INTO `state` (`id`, `name`, `state_code`,`country_id`) VALUES ('45','Vermont', 'VT',  '1');
INSERT INTO `state` (`id`, `name`, `state_code`,`country_id`) VALUES ('46','Virginia', 'VA',  '1');
INSERT INTO `state` (`id`, `name`, `state_code`,`country_id`) VALUES ('47','Washington', 'WA',  '1');
INSERT INTO `state` (`id`, `name`, `state_code`,`country_id`) VALUES ('48','West Virginia', 'WV',  '1');
INSERT INTO `state` (`id`, `name`, `state_code`,`country_id`) VALUES ('49','Wisconsin', 'WI',  '1');
INSERT INTO `state` (`id`, `name`, `state_code`,`country_id`) VALUES ('50','Wyoming', 'WY',  '1');
INSERT INTO `state` (`id`, `name`, `state_code`,`country_id`) VALUES ('51','ARMED FORCES AFRICA \\ CANADA \\ EUROPE \\ MIDDLE EAST', 'AE',  '1');
INSERT INTO `state` (`id`, `name`, `state_code`,`country_id`) VALUES ('52','ARMED FORCES AMERICA (EXCEPT CANADA)', 'AA',  '1');
INSERT INTO `state` (`id`, `name`, `state_code`,`country_id`) VALUES ('53','ARMED FORCES PACIFIC', 'AP',  '1');
 update address set state_id=3;

--job pickup addess
INSERT INTO `jobs_pickup_points` (`jobs_id`, `pickup_point_id`) VALUES ('1', '1');
INSERT INTO `jobs_pickup_points` (`jobs_id`, `pickup_point_id`) VALUES ('3', '1');
INSERT INTO `jobs_pickup_points` (`jobs_id`, `pickup_point_id`) VALUES ('5', '4');


--24-06
create table beans_types (id bigint not null auto_increment, description varchar(255), name varchar(255), primary key (id)) engine=InnoDB;
create table catering_price (id bigint not null auto_increment, min_no_of_people integer not null, price double precision not null, service_fee float not null, primary key (id)) engine=InnoDB;
create table catering_types (id bigint not null auto_increment, description varchar(255), name varchar(255), primary key (id)) engine=InnoDB;
create table caterings (id bigint not null auto_increment, city varchar(255), email varchar(255) not null, event_date datetime, first_name varchar(255), last_name varchar(255), mailing_address_line1 varchar(255), mailing_address_line2 varchar(255), no_of_people integer not null, note varchar(255), phone varchar(255) not null, zip varchar(255), beans_type_id bigint, catering_price_id bigint, catering_type_id bigint, choice_first_id bigint, choice_second_id bigint, state_id bigint, tortilla_type_id bigint, primary key (id)) engine=InnoDB;
create table choice_catering_types (choice_id bigint not null, catering_type_id bigint not null, primary key (choice_id, catering_type_id)) engine=InnoDB;
create table choices (id bigint not null auto_increment, description varchar(255), name varchar(255), primary key (id)) engine=InnoDB;
create table tortilla_types (id bigint not null auto_increment, description varchar(255), name varchar(255), primary key (id)) engine=InnoDB;
alter table caterings add constraint fk_t_caterings_beansTypeId_t_beans_type_id foreign key (beans_type_id) references beans_types (id);
alter table caterings add constraint fk_t_caterings_cateringPriceId_t_catering_price_id foreign key (catering_price_id) references catering_price (id);
alter table caterings add constraint fk_t_caterings_cateringTypeId_t_catering_type_id foreign key (catering_type_id) references catering_types (id);
alter table caterings add constraint fk_t_caterings_choiceFirstId_t_choice_first_id foreign key (choice_first_id) references choices (id);
alter table caterings add constraint fk_t_caterings_choiceSecondId_t_choice_second_id foreign key (choice_second_id) references choices (id);
alter table caterings add constraint fk_t_franchise_stateId_t_state_id foreign key (state_id) references state (id);
alter table caterings add constraint fk_t_caterings_tortillaTypeId_t_tortilla_type_id foreign key (tortilla_type_id) references tortilla_types (id);
alter table choice_catering_types add constraint fk_t_catering_type_choice_t_cateringTypeId foreign key (catering_type_id) references catering_types (id);
alter table choice_catering_types add constraint fk_t_choice_catering_type_t_choiceId foreign key (choice_id) references choices (id);
alter table caterings add constraint fk_t_catering_stateId_t_state_id foreign key (state_id) references state (id);

insert into catering_price (id,min_no_of_people,price,service_fee) values (1, 50,10,18);
insert into catering_types (id, name,description) values (1, 'Taquero Style Catering', 'Taquero Style Catering');
insert into catering_types (id, name,description) values (2, 'Full Service Catering', 'Full Service Catering');
insert into choices (name,description) values ('Angus Steak','Angus Steak');
insert into choices (name,description) values ('Authentic Adobada','Authentic Adobada');
insert into choice_catering_types (choice_id,catering_type_id) values (1,2);
insert into choice_catering_types (choice_id,catering_type_id) values (1,1);
insert into choice_catering_types (choice_id,catering_type_id) values (2,1);
insert into choice_catering_types (choice_id,catering_type_id) values (2,2);


-------29-06
create table beans_type_catering_types (beans_type_id bigint not null, catering_type_id bigint not null, primary key (beans_type_id, catering_type_id)) engine=InnoDB;
alter table beans_type_catering_types add constraint fk_t_beans_type_catering_type_id_t_cateringTypeId foreign key (catering_type_id) references catering_types (id);
alter table beans_type_catering_types add constraint fk_t_beans_type_beans_type_id_t_beansTypeId foreign key (beans_type_id) references beans_types (id);
create table tortilla_type_catering_types (tortilla_type_id bigint not null, catering_type_id bigint not null, primary key (tortilla_type_id, catering_type_id)) engine=InnoDB;
alter table tortilla_type_catering_types add constraint fk_t_tortilla_type_catering_type_id_t_cateringTypeId foreign key (catering_type_id) references catering_types (id);
alter table tortilla_type_catering_types add constraint fk_t_tortilla_type_tortilla_type_id_t_tortillaTypeId foreign key (tortilla_type_id) references tortilla_types (id);



INSERT INTO `choices` (`id`, `description`, `name`) VALUES ('3', 'Chicken Breast', 'Chicken Breast');
INSERT INTO `choices` (`id`, `description`, `name`) VALUES ('4', 'Fresh Carnitas', 'Fresh Carnitas');
INSERT INTO `choices` (`id`, `description`, `name`) VALUES ('5', 'Steak Fajitas', 'Steak Fajitas');
INSERT INTO `choices` (`id`, `description`, `name`) VALUES ('6', 'Chicken Fajitas', 'Chicken Fajitas');
INSERT INTO `choices` (`id`, `description`, `name`) VALUES ('7', 'Chile Verde', 'Chile Verde');
INSERT INTO `choices` (`id`, `description`, `name`) VALUES ('8', 'Cheese Enchiladas', 'Cheese Enchiladas');
INSERT INTO `choices` (`id`, `description`, `name`) VALUES ('9', 'Beef Enchiladas', 'Beef Enchiladas');
INSERT INTO `choices` (`id`, `description`, `name`) VALUES ('10', 'Chicken Enchiladas', 'Chicken Enchiladas');
INSERT INTO `choices` (`id`, `description`, `name`) VALUES ('11', 'Chile Rellenos', 'Chile Rellenos');
INSERT INTO `choice_catering_types` (`choice_id`, `catering_type_id`) VALUES ('3', '1');
INSERT INTO `choice_catering_types` (`choice_id`, `catering_type_id`) VALUES ('3', '2');
INSERT INTO `choice_catering_types` (`choice_id`, `catering_type_id`) VALUES ('4', '1');
INSERT INTO `choice_catering_types` (`choice_id`, `catering_type_id`) VALUES ('4', '2');
INSERT INTO `choice_catering_types` (`choice_id`, `catering_type_id`) VALUES ('5', '2');
INSERT INTO `choice_catering_types` (`choice_id`, `catering_type_id`) VALUES ('6', '2');
INSERT INTO `choice_catering_types` (`choice_id`, `catering_type_id`) VALUES ('7', '2');
INSERT INTO `choice_catering_types` (`choice_id`, `catering_type_id`) VALUES ('8', '2');
INSERT INTO `choice_catering_types` (`choice_id`, `catering_type_id`) VALUES ('9', '2');
INSERT INTO `choice_catering_types` (`choice_id`, `catering_type_id`) VALUES ('10', '2');
INSERT INTO `choice_catering_types` (`choice_id`, `catering_type_id`) VALUES ('11', '2');

INSERT INTO `tortilla_types` (`id`, `description`, `name`) VALUES ('1', 'Flour', 'Flour');
INSERT INTO `tortilla_types` (`id`, `description`, `name`) VALUES ('2', 'Corn', 'Corn');
INSERT INTO `tortilla_types` (`id`, `description`, `name`) VALUES ('3', 'Half & Half', 'Half & Half');

INSERT INTO `tortilla_type_catering_types` (`tortilla_type_id`, `catering_type_id`) VALUES ('1', '1');
INSERT INTO `tortilla_type_catering_types` (`tortilla_type_id`, `catering_type_id`) VALUES ('1', '2');
INSERT INTO `tortilla_type_catering_types` (`tortilla_type_id`, `catering_type_id`) VALUES ('2', '1');
INSERT INTO `tortilla_type_catering_types` (`tortilla_type_id`, `catering_type_id`) VALUES ('2', '2');
INSERT INTO `tortilla_type_catering_types` (`tortilla_type_id`, `catering_type_id`) VALUES ('3', '1');
INSERT INTO `tortilla_type_catering_types` (`tortilla_type_id`, `catering_type_id`) VALUES ('3', '2');



INSERT INTO `beans_types` (`id`, `description`, `name`) VALUES ('1', 'Refried Beans', 'Refried Beans');
INSERT INTO `beans_types` (`id`, `description`, `name`) VALUES ('2', 'Whole Beans', 'Whole Beans');
INSERT INTO `beans_type_catering_types` (`beans_type_id`, `catering_type_id`) VALUES ('1', '2');
INSERT INTO `beans_type_catering_types` (`beans_type_id`, `catering_type_id`) VALUES ('2', '2');


--01-07
 alter table site_submitters modify mobile_phone varchar(255) null;

--12-07
alter table pickup_points add column active bit not null;

--09-08
alter table fund_raising add column end_date datetime not null;
alter table jobs_pickup_points DROP constraint `FKlnb2fg8atdbna5gn7cbyvcu8v`;
 
alter table site_submittal modify is_lease bit null;
alter table site_submittal modify is_purchase bit null;

alter table site_type modify has_free_standing_with_drive_thru bit null;
alter table site_type modify end_cap bit null;
alter table site_type modify is_pad_site bit null;
alter table site_type modify in_line bit null;
alter table employers modify end_date datetime;
alter table employers modify start_date datetime;

alter table careers modify `mailing_address` varchar(255) NULL;
alter table careers DROP column is_legal;

-- 10-018-2021 --Akshay -- Jira PRM-267 
ALTER TABLE pickup_points ADD indoor_dining_avlb bit(1) NOT NULL;
INSERT INTO pickup_hours (name) VALUES ('Sun-Thurs 7am-9pm');
INSERT INTO pickup_hours (name) VALUES ('Fri-Sat 7am-10pm');
INSERT INTO pickup_hours (name) VALUES ('6am-10pm.daily');
INSERT INTO pickup_hours (name) VALUES ('Dining room closes at 9pm');
ALTER TABLE pickup_points ADD email varchar(255) DEFAULT NULL;
