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
-- Table structure for table `secretaries`
--

DROP TABLE IF EXISTS `secretaries`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `secretaries` (
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
-- Dumping data for table `secretaries`
--

LOCK TABLES `secretaries` WRITE;
/*!40000 ALTER TABLE `secretaries` DISABLE KEYS */;
INSERT INTO `secretaries` VALUES (20001,123456789,'Apostolis','Siampanis',2020,'2002-09-16','apossiamp@gmail.com',_binary 'bffb1fddff88264d55c2fcbe9ff2a6f4c020287c\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0','xNOOrbEgLUtsh89a'),(20002,234567891,'Theodore','Koxanoglou',2020,'2002-03-12','theokoxan@gmail.com',_binary '563b416a24b40f38d96caae3085cfb1f860e17c3\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0','2ao3monrdrpbGGxn'),(20003,345678912,'Aimilianos','Kourpas',2019,'2001-01-18','aimilioskourp@gmail.com',_binary 'efd7606a6d07ff126e7c521e4ce42854a133437c\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0','Xr8i3KpIvvWirY6q'),(20004,456789123,'Aggeliki','Kaldiri',2018,'2000-07-12','aggelkal@gmail.com',_binary '34a0e9504d81485883ca12d3f1e8fa82b5d6532e\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0','DFkeRFRlbLy0ddHv');
/*!40000 ALTER TABLE `secretaries` ENABLE KEYS */;
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
