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
-- Table structure for table `courses`
--

DROP TABLE IF EXISTS `courses`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `courses` (
  `idcourses` int NOT NULL,
  `title` varchar(45) NOT NULL,
  `semester` int NOT NULL,
  `hours` int NOT NULL,
  `type` varchar(45) NOT NULL,
  PRIMARY KEY (`idcourses`),
  UNIQUE KEY `idcourses_UNIQUE` (`idcourses`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `courses`
--

LOCK TABLES `courses` WRITE;
/*!40000 ALTER TABLE `courses` DISABLE KEYS */;
INSERT INTO `courses` VALUES (100,'Analysis I',1,4,'Mandatory'),(101,'Introduction To Programming',1,4,'Mandatory'),(102,'Introduction To Computer Science',1,6,'Optional'),(200,'Analysis II',2,4,'Mandatory'),(201,'Object Oriented Programming',2,6,'Mandatory'),(202,'Data Structures',2,5,'Optional'),(300,'Object Oriented Application Development',3,6,'Mandatory'),(301,'Operating Systems',3,5,'Mandatory'),(302,'Compilers',3,5,'Optional'),(400,'Algorithms',4,4,'Mandatory'),(401,'Internet and Web Programming',4,6,'Mandatory'),(402,'Databases',4,5,'Optional'),(500,'Pattern Recognition',5,6,'Mandatory'),(501,'Information Systems',5,4,'Mandatory'),(502,'Game Theory and Applications',5,5,'Optional'),(600,'Artificial Intelligence and Expert Systems',6,4,'Mandatory'),(601,'Software Engineering',6,5,'Optional'),(602,'Security Governance',6,4,'Optional'),(700,'Virtual Reality',7,6,'Mandatory'),(701,'Systems Simulation',7,6,'Mandatory'),(702,'Information Systems Security',7,6,'Optional'),(800,'Networking Security',8,6,'Mandatory'),(801,'It Project Managment',8,5,'Mandatory'),(802,'Intelligent Agents',8,5,'Optional');
/*!40000 ALTER TABLE `courses` ENABLE KEYS */;
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
