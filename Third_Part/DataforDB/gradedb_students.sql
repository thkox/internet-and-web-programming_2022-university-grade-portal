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
-- Table structure for table `students`
--

DROP TABLE IF EXISTS `students`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `students` (
  `registnumber` int NOT NULL,
  `name` varchar(45) NOT NULL,
  `surname` varchar(45) NOT NULL,
  `acadyearadmission` year NOT NULL,
  `birthdate` date NOT NULL,
  `email` varchar(45) NOT NULL,
  `semester` int NOT NULL,
  `password` binary(64) NOT NULL,
  `salt` varchar(32) NOT NULL,
  PRIMARY KEY (`registnumber`),
  UNIQUE KEY `registnumber_UNIQUE` (`registnumber`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `students`
--

LOCK TABLES `students` WRITE;
/*!40000 ALTER TABLE `students` DISABLE KEYS */;
INSERT INTO `students` VALUES (19007,'Hercules','Papakonstantinou',2019,'2000-05-08','hercpapa@gmail.com',7,_binary '695f996172d555d9c3270d5c60418aa540c6dc4e\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0','V7BGqFc6foapkH9A'),(19008,'Rania','Anastasiadi',2018,'1999-08-03','raniaanast@gmail.com',8,_binary '9a656c3fefd2da1d22df3df40f1212f841a8d3e3\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0','R2JQu5P0RKep7qdn'),(20005,'Katerina','Papanikolaou',2020,'2001-01-09','katpap@gmail.com',5,_binary 'b65731a02f731e8007c0c2329f052a7205c28aaa\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0','1bgFcNvjqqxzl2K0'),(20006,'Stefanos','Korakakis',2020,'2001-04-10','stefkor@gmail.com',6,_binary '4fdd626fd1f44f90bc5d759245f2e3f780afa663\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0','EGE84Sz9P3Pj2RYz'),(21003,'Anastasis','Verikokakis',2021,'2002-01-25','anstver@gmail.com',3,_binary '9dc5884384d4d401bfca335c623f9cc98e403cb2\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0','HqqauzrWH6XI3yK0'),(21004,'Eleni','Danezi',2021,'2001-05-21','eledan@gmail.com',4,_binary '6b4e26ff601487dada64282cde3cdc4fa0c8f836\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0','5jHmLv3eL6rQZz9x'),(22001,'Makis','Kotsampasis',2022,'2003-06-11','makkots@gmail.com',1,_binary '46507f96e0cbbc026a308e244a8c9cccc761e4c6\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0','VCA0q3tZzStePYLJ'),(22002,'Eleftheria','Ekatomati',2022,'2002-05-14','eleekat@gmail.com',2,_binary '8bbcb34a5004aed140bceb5933f78429d0475353\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0','OVQ2b14nx7vrPEzq');
/*!40000 ALTER TABLE `students` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2022-07-07  4:27:00
