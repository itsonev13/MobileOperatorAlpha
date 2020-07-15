-- MySQL dump 10.13  Distrib 8.0.19, for Win64 (x86_64)
--
-- Host: localhost    Database: mobileoperatorapp
-- ------------------------------------------------------
-- Server version	8.0.19

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `plan`
--

DROP TABLE IF EXISTS `plan`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `plan` (
  `id` int NOT NULL AUTO_INCREMENT,
  `SMS` int DEFAULT NULL,
  `minutes` int DEFAULT NULL,
  `mobile_data` int DEFAULT NULL,
  `price` double DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `plan`
--

LOCK TABLES `plan` WRITE;
/*!40000 ALTER TABLE `plan` DISABLE KEYS */;
INSERT INTO `plan` VALUES (1,100,100,1024,20,'smart'),(2,0,500,2048,23.3,'icon'),(3,1000,1000,8000,30,'infinity');
/*!40000 ALTER TABLE `plan` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `planusages`
--

DROP TABLE IF EXISTS `planusages`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `planusages` (
  `id` int NOT NULL AUTO_INCREMENT,
  `fullplan_id` int DEFAULT NULL,
  `used_SMS` int DEFAULT NULL,
  `used_minutes` int DEFAULT NULL,
  `used_mobile_data` int DEFAULT NULL,
  `IsActiveSMS` tinyint(1) DEFAULT NULL,
  `IsActiveMinutes` tinyint(1) DEFAULT NULL,
  `IsActiveMobileData` tinyint(1) DEFAULT NULL,
  `isPayed` tinyint(1) DEFAULT NULL,
  `month` tinyint DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `planusages_ibfk_1` (`fullplan_id`),
  CONSTRAINT `planusages_ibfk_1` FOREIGN KEY (`fullplan_id`) REFERENCES `users_plan` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=25 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `planusages`
--

LOCK TABLES `planusages` WRITE;
/*!40000 ALTER TABLE `planusages` DISABLE KEYS */;
INSERT INTO `planusages` VALUES (1,1,11,100,400,1,0,1,1,7),(2,2,0,23,200,1,1,1,1,7),(3,3,15,30,300,1,1,1,1,7),(4,4,20,32,400,1,1,1,1,7),(5,1,0,0,0,1,1,1,1,8),(7,13,0,500,2048,1,1,1,1,7),(8,2,0,0,0,1,1,1,1,8),(9,2,0,0,0,1,1,1,1,9),(10,14,0,500,2048,1,1,1,1,7),(11,15,100,100,1024,1,1,1,1,7),(12,16,100,100,1024,1,1,1,1,7),(16,19,0,500,2048,1,1,1,1,7),(17,20,1000,1000,8000,1,1,1,1,7),(18,21,100,100,1024,1,1,1,1,7),(19,22,0,0,0,1,1,1,1,7),(20,23,0,0,0,1,1,1,1,7),(21,4,0,0,0,0,0,0,1,8),(22,24,0,0,0,1,1,1,1,7),(23,25,0,0,0,1,1,1,1,7),(24,1,5,20,100,1,1,1,1,6);
/*!40000 ALTER TABLE `planusages` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `users` (
  `id` int NOT NULL AUTO_INCREMENT,
  `email` varchar(100) DEFAULT NULL,
  `phone` varchar(20) DEFAULT NULL,
  `password` varchar(100) DEFAULT NULL,
  `isAdmin` tinyint(1) DEFAULT NULL,
  `nickname` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `nickname` (`nickname`)
) ENGINE=InnoDB AUTO_INCREMENT=62 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES (1,'ivan@abv.bg','0858567543','123456',0,'vankata23'),(2,'dimitar@abv.bg','0789356782','1234faz',0,'mitaka11'),(3,'maria23@mail.bg','0983456783','123qwe',0,'mara'),(4,'neli@abv.bg','09838383831','1234561',1,'nelitoo'),(5,'niki@abv.bg','09838385831','12345612',0,'nikity'),(19,'koceto@abv.bg','0763567895','123456789',0,'kocaka11'),(56,'rostiman@abv.bg','0998324586','123456',0,'rosti123'),(57,'georgi@abv.bg','0973578392','123456',0,'go6oo'),(59,'dimaka@abv.bg','0965236382','123456',0,'dim4o'),(60,'bobi@abv.bg','0843958213','123456',0,'bobito'),(61,'vili@abv.bg','0897378523','123456',0,'vilito');
/*!40000 ALTER TABLE `users` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users_plan`
--

DROP TABLE IF EXISTS `users_plan`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `users_plan` (
  `id` int NOT NULL AUTO_INCREMENT,
  `user_id` int DEFAULT NULL,
  `plan_id` int DEFAULT NULL,
  `startdate` date DEFAULT NULL,
  `IsActivePlan` tinyint(1) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `user_id` (`user_id`),
  KEY `plan_id` (`plan_id`),
  CONSTRAINT `users_plan_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`) ON DELETE CASCADE,
  CONSTRAINT `users_plan_ibfk_2` FOREIGN KEY (`plan_id`) REFERENCES `plan` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=26 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users_plan`
--

LOCK TABLES `users_plan` WRITE;
/*!40000 ALTER TABLE `users_plan` DISABLE KEYS */;
INSERT INTO `users_plan` VALUES (1,1,1,'2020-07-09',1),(2,1,2,'2020-07-20',1),(3,2,1,'2020-07-13',1),(4,3,3,'2020-07-09',1),(13,56,2,'2020-07-12',1),(14,57,2,'2020-07-13',1),(15,5,1,'2020-07-13',1),(16,19,1,'2020-07-13',1),(19,19,2,'2020-07-13',1),(20,59,3,'2020-07-14',1),(21,59,1,'2020-07-14',1),(22,60,2,'2020-07-14',1),(23,60,1,'2020-07-14',1),(24,61,2,'2020-07-14',1),(25,61,3,'2020-07-14',1);
/*!40000 ALTER TABLE `users_plan` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2020-07-15 21:00:01
