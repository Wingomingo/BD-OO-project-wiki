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
-- Table structure for table `pagine`
--

DROP TABLE IF EXISTS `pagine`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `pagine` (
  `ID_pagina` int NOT NULL AUTO_INCREMENT,
  `Titolo` varchar(100) NOT NULL,
  `Testo` text,
  `Data` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `ID_autore` int DEFAULT NULL,
  PRIMARY KEY (`ID_pagina`),
  KEY `ID_autore` (`ID_autore`),
  CONSTRAINT `pagine_ibfk_1` FOREIGN KEY (`ID_autore`) REFERENCES `utente` (`ID_utente`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=54 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `pagine`
--

LOCK TABLES `pagine` WRITE;
/*!40000 ALTER TABLE `pagine` DISABLE KEYS */;
INSERT INTO `pagine` VALUES (40,'Introduzione al Database','Ciao mi chiamo Marco','2025-02-06 14:15:30',6),(41,'Dati','Le informazioni memorizzate nel database, come [record] e prodotti.','2025-02-06 14:15:30',6),(42,'Record','è la riga di una [tabella] del database.','2025-02-06 14:15:30',8),(43,'Tabella','HELLO THERE','2025-02-06 14:15:30',10),(44,'I droni di Misterio','Ciao mi chiamo marco ','2025-02-06 14:15:30',8),(45,'Misterio','Super Cattivo della [MCU]','2025-02-06 14:15:30',8),(48,'MCU','Marvel dajdioahsifaufg','2025-02-06 15:55:36',10),(50,'ciao','HELLO THERE, GENERAL KENOBI!!! (citazione a star wars)','2025-02-07 12:30:43',8),(51,'Ciao','Hello my darlin','2025-03-10 17:00:19',15),(52,'Prova','i [dati] all\'interno della [tabella] pagine sono molto grandi.','2025-03-10 19:00:23',15);
/*!40000 ALTER TABLE `pagine` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2025-03-26 12:57:57
