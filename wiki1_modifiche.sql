-- MySQL dump 10.13  Distrib 8.0.40, for Win64 (x86_64)
--
-- Host: localhost    Database: wiki1
-- ------------------------------------------------------
-- Server version	8.0.40

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
-- Table structure for table `modifiche`
--

DROP TABLE IF EXISTS `modifiche`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `modifiche` (
  `ID_modifica` int NOT NULL AUTO_INCREMENT,
  `Testo` text,
  `Stato` enum('Proposta','Approvata','Rifiutata') NOT NULL DEFAULT 'Proposta',
  `ID_utente` int DEFAULT NULL,
  `ID_pagina` int DEFAULT NULL,
  `Data` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`ID_modifica`),
  KEY `ID_utente` (`ID_utente`),
  KEY `ID_pagina` (`ID_pagina`),
  CONSTRAINT `modifiche_ibfk_1` FOREIGN KEY (`ID_utente`) REFERENCES `utente` (`ID_utente`) ON DELETE CASCADE,
  CONSTRAINT `modifiche_ibfk_2` FOREIGN KEY (`ID_pagina`) REFERENCES `pagine` (`ID_pagina`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=43 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `modifiche`
--

LOCK TABLES `modifiche` WRITE;
/*!40000 ALTER TABLE `modifiche` DISABLE KEYS */;
INSERT INTO `modifiche` VALUES (35,'HELLO THERE','Approvata',10,43,'2025-02-07 12:07:59'),(36,'HELLO THERE','Approvata',10,43,'2025-02-07 12:13:49'),(37,'HELLO THERE','Approvata',10,43,'2025-02-07 12:17:19'),(38,'HELLO THERE, GENERAL KENOBI!','Approvata',15,50,'2025-03-10 17:32:10'),(40,'Marvel dajdioahsifaufg','Approvata',15,48,'2025-03-10 19:52:58'),(41,'HELLO THERE, GENERAL KENOBI!!! (citazione a star wars)','Approvata',15,50,'2025-03-12 10:30:30'),(42,'falso non è una citazione a star wars','Rifiutata',15,50,'2025-03-12 10:30:53');
/*!40000 ALTER TABLE `modifiche` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2025-03-26 12:57:58
