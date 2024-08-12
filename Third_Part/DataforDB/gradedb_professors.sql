-- MySQL dump 10.13  Distrib 8.0.29, for Win64 (x86_64)
--
-- Host: localhost    Database: gradedb
-- ------------------------------------------------------
-- Server version	8.0.29

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
-- Table structure for table `professors`
--

DROP TABLE IF EXISTS `professors`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `professors` (
  `registnumber` int NOT NULL,
  `afmnumber` int NOT NULL,
  `name` varchar(45) NOT NULL,
  `surname` varchar(45) NOT NULL,
  `acadyearadmission` year NOT NULL,
  `birthdate` date NOT NULL,
  `email` varchar(45) NOT NULL,
  `password` binary(64) NOT NULL,
  `salt` varchar(32) NOT NULL,
  PRIMARY KEY (`registnumber`),
  UNIQUE KEY `registnumber_UNIQUE` (`registnumber`),
  UNIQUE KEY `afmnumber_UNIQUE` (`afmnumber`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `professors`
--

LOCK TABLES `professors` WRITE;
/*!40000 ALTER TABLE `professors` DISABLE KEYS */;
INSERT INTO `professors` VALUES (20001,678912345,'Kostas','Adamidis',2015,'1972-05-20','kostadam@gmail.com',_binary 'b23ba3c9e6a487b38735eec81eb40de854398b80\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0','a51MHb0laW3duLn4'),(20002,567891234,'Nikos','Papadopoulos',2010,'1962-05-22','nikpap@gmail.com',_binary '3b99750517835e6a9d2ebe2207582b852f9daf29\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0','Duvs09vsph7nRtz6'),(20003,789123456,'George','Ananiadis',2018,'1981-01-06','geoanan@gmail.com',_binary '86c91e01b61ef306e24f00016075c83199a97266\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0','uBV6XRu2ly70LNlg'),(20004,891234567,'Aggelos','Vaxevanis',2020,'1978-08-16','aggvax@gmail.com',_binary '1514ca0204721f46e21374fac058348157806ba4\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0','FZ4DeS8NPVMBOtA1'),(20005,912345678,'Maria','Louiza',2017,'1978-11-22','marloui@gmai.com',_binary 'bf7afa4b25d0cd72e54e4f232bbe8231c87e402b\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0','ARaHE5tfzblurksj'),(20006,213456789,'Nektaria','Peropoulou',2014,'1966-05-23','nekper@gmail.com',_binary '517a430d18591d134cff9ecaf60d7d70fcccdae8\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0','LoknvVtFZSeDR5dy');
/*!40000 ALTER TABLE `professors` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2022-07-07  4:26:59
