-- MySQL dump 10.13  Distrib 8.0.17, for Win64 (x86_64)
--
-- Host: localhost    Database: reastaurant_db
-- ------------------------------------------------------
-- Server version	8.0.17

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
-- Table structure for table `orders`
--

DROP TABLE IF EXISTS `orders`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `orders` (
  `order_id` int(11) NOT NULL AUTO_INCREMENT,
  `table_id` varchar(20) DEFAULT NULL,
  `dish_name` varchar(20) DEFAULT NULL,
  `drink_name` varchar(20) DEFAULT NULL,
  `dish_price` int(11) DEFAULT NULL,
  `drink_price` int(11) DEFAULT NULL,
  `is_pay` tinyint(1) DEFAULT '0',
  PRIMARY KEY (`order_id`),
  KEY `table_id` (`table_id`),
  CONSTRAINT `orders_ibfk_1` FOREIGN KEY (`table_id`) REFERENCES `restaurants_tables` (`table_id`)
) ENGINE=InnoDB AUTO_INCREMENT=48 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `orders`
--

LOCK TABLES `orders` WRITE;
/*!40000 ALTER TABLE `orders` DISABLE KEYS */;
INSERT INTO `orders` VALUES (1,'IN2','Teriyaki',NULL,27,NULL,0),(2,'IN2','Teriyaki',NULL,27,NULL,0),(3,'IN2',NULL,'Esspresso',NULL,6,0),(4,'IN2','Szarlotka',NULL,25,NULL,0),(5,'IN2','Teriyaki',NULL,27,NULL,0),(6,'IN2',NULL,'Esspresso',NULL,6,0),(7,'IN2','Szarlotka',NULL,25,NULL,0),(8,'IN2','Teriyaki',NULL,27,NULL,0),(9,'IN2',NULL,'Esspresso',NULL,6,0),(10,'IN2','Szarlotka',NULL,25,NULL,0),(11,'IN2',NULL,'Esspresso',NULL,6,0),(12,'IN1','Indyk',NULL,30,NULL,0),(13,'IN1','Indyk',NULL,30,NULL,0),(14,'IN1','Rosół',NULL,15,NULL,0),(15,'IN1','Lody',NULL,15,NULL,0),(16,'IN1','Lody',NULL,15,NULL,0),(17,'IN1','Potato',NULL,6,NULL,0),(18,'OUT2','Cesar',NULL,24,NULL,0),(19,'OUT2','Polędwiczki',NULL,35,NULL,0),(20,'OUT2','Lody',NULL,15,NULL,0),(21,'OUT2','Lody',NULL,15,NULL,0),(22,'OUT2','Lody',NULL,15,NULL,0),(23,'IN2','Cesar',NULL,24,NULL,0),(24,'IN2','Cesar',NULL,24,NULL,0),(25,'IN2','Cesar',NULL,24,NULL,0),(26,'IN2','Cesar',NULL,24,NULL,0),(27,'IN1','Lody',NULL,15,NULL,0),(28,'IN1','Lody',NULL,15,NULL,0),(29,'IN1','Lody',NULL,15,NULL,0),(30,'IN1','Cesar',NULL,24,NULL,0),(31,'IN1','Cesar',NULL,24,NULL,0),(32,'IN1','Lody',NULL,15,NULL,0),(33,'IN1','Lody',NULL,15,NULL,0),(34,'IN2','Cesar',NULL,24,NULL,0),(35,'IN2','Cesar',NULL,24,NULL,0),(36,'IN2','Cesar',NULL,24,NULL,0),(37,'IN2','Cesar',NULL,24,NULL,0),(38,'IN2','Cesar',NULL,24,NULL,0),(39,'IN2','Cesar',NULL,24,NULL,0),(40,'IN2','Cesar',NULL,24,NULL,0),(41,'IN2','Cesar',NULL,24,NULL,0),(42,'IN2','Cesar',NULL,24,NULL,0),(43,'IN2','Cesar',NULL,24,NULL,0),(44,'IN2','Cesar',NULL,24,NULL,0),(45,'IN2',NULL,'Martini',NULL,15,0),(46,'OUT5',NULL,'Fanta',NULL,5,0),(47,'OUT5','Kurczak',NULL,25,NULL,0);
/*!40000 ALTER TABLE `orders` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2019-09-07 18:01:53
