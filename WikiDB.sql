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
-- Table structure for table `collegamenti`
--

DROP TABLE IF EXISTS `collegamenti`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `collegamenti` (
  `ID_collegamento` int NOT NULL AUTO_INCREMENT,
  `ID_frase_collegamento` int DEFAULT NULL,
  `ID_pagina_destinazione` int DEFAULT NULL,
  PRIMARY KEY (`ID_collegamento`),
  KEY `ID_frase_collegamento` (`ID_frase_collegamento`),
  KEY `ID_pagina_destinazione` (`ID_pagina_destinazione`),
  CONSTRAINT `collegamenti_ibfk_1` FOREIGN KEY (`ID_frase_collegamento`) REFERENCES `frasi` (`ID_frase`) ON DELETE CASCADE,
  CONSTRAINT `collegamenti_ibfk_2` FOREIGN KEY (`ID_pagina_destinazione`) REFERENCES `pagine` (`ID_pagina`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=48 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `collegamenti`
--

LOCK TABLES `collegamenti` WRITE;
/*!40000 ALTER TABLE `collegamenti` DISABLE KEYS */;
INSERT INTO `collegamenti` VALUES (37,105,42),(38,108,43),(43,117,48),(46,150,41),(47,152,43);
/*!40000 ALTER TABLE `collegamenti` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `frasi`
--

DROP TABLE IF EXISTS `frasi`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `frasi` (
  `ID_frase` int NOT NULL AUTO_INCREMENT,
  `Frase` varchar(200) DEFAULT NULL,
  `Collegamento` tinyint(1) DEFAULT '0',
  `ID_pagina` int DEFAULT NULL,
  `ID_pagina_collegata` int DEFAULT NULL,
  PRIMARY KEY (`ID_frase`),
  KEY `ID_pagina` (`ID_pagina`),
  CONSTRAINT `frasi_ibfk_1` FOREIGN KEY (`ID_pagina`) REFERENCES `pagine` (`ID_pagina`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=169 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `frasi`
--

LOCK TABLES `frasi` WRITE;
/*!40000 ALTER TABLE `frasi` DISABLE KEYS */;
INSERT INTO `frasi` VALUES (103,'Le informazioni memorizzate nel database',0,41,NULL),(104,'come',0,41,NULL),(105,'record',1,41,42),(106,'e prodotti',0,41,NULL),(107,'è la riga di una',0,42,NULL),(108,'tabella',1,42,43),(109,'del database',0,42,NULL),(116,'Super Cattivo della',0,45,NULL),(117,'MCU',1,45,48),(130,'Ciao mi chiamo Marco',0,40,NULL),(142,'Ciao mi chiamo marco',0,44,NULL),(146,'Hello my darlin',0,51,NULL),(149,'i',0,52,NULL),(150,'dati',1,52,41),(151,'all\'interno della',0,52,NULL),(152,'tabella',1,52,43),(153,'pagine sono molto grandi',0,52,NULL),(161,'Marvel dajdioahsifaufg',0,48,NULL),(165,'HELLO THERE',0,43,NULL),(166,'HELLO THERE',0,50,NULL),(167,'GENERAL KENOBI',0,50,NULL),(168,'(citazione a star wars)',0,50,NULL);
/*!40000 ALTER TABLE `frasi` ENABLE KEYS */;
UNLOCK TABLES;

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

--
-- Table structure for table `notifiche`
--

DROP TABLE IF EXISTS `notifiche`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `notifiche` (
  `ID_notifica` int NOT NULL AUTO_INCREMENT,
  `Data` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `Letta` tinyint(1) NOT NULL DEFAULT '0',
  `ID_modificatore` int DEFAULT NULL,
  `ID_autore` int DEFAULT NULL,
  `ID_creatore_pagina` int DEFAULT NULL,
  PRIMARY KEY (`ID_notifica`),
  KEY `ID_modificatore` (`ID_modificatore`),
  KEY `ID_autore` (`ID_autore`),
  CONSTRAINT `notifiche_ibfk_1` FOREIGN KEY (`ID_modificatore`) REFERENCES `modifiche` (`ID_modifica`) ON DELETE CASCADE,
  CONSTRAINT `notifiche_ibfk_2` FOREIGN KEY (`ID_autore`) REFERENCES `utente` (`ID_utente`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=33 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `notifiche`
--

LOCK TABLES `notifiche` WRITE;
/*!40000 ALTER TABLE `notifiche` DISABLE KEYS */;
INSERT INTO `notifiche` VALUES (25,'2025-02-07 12:07:59',1,35,10,10),(26,'2025-02-07 12:13:49',1,36,10,10),(27,'2025-02-07 12:17:19',1,37,10,10),(28,'2025-03-10 17:32:10',1,38,15,8),(30,'2025-03-10 19:52:58',1,40,15,10),(31,'2025-03-12 10:30:30',1,41,15,8),(32,'2025-03-12 10:30:53',1,42,15,8);
/*!40000 ALTER TABLE `notifiche` ENABLE KEYS */;
UNLOCK TABLES;

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

--
-- Table structure for table `utente`
--

DROP TABLE IF EXISTS `utente`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `utente` (
  `ID_utente` int NOT NULL AUTO_INCREMENT,
  `Nome` varchar(50) NOT NULL,
  `Username` varchar(50) NOT NULL,
  `Email` varchar(100) DEFAULT NULL,
  `Password` varchar(100) DEFAULT NULL,
  `Ruolo` enum('Scrittore','Visitatore') DEFAULT 'Visitatore',
  PRIMARY KEY (`ID_utente`)
) ENGINE=InnoDB AUTO_INCREMENT=23 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `utente`
--

LOCK TABLES `utente` WRITE;
/*!40000 ALTER TABLE `utente` DISABLE KEYS */;
INSERT INTO `utente` VALUES (6,'Mario Rossi','ValentinoVR','mario.rossi@gmail.com','rossi46','Scrittore'),(7,'Luca Bianchi','JingJang',NULL,NULL,'Visitatore'),(8,'Giulia Verdi','Pollice','giulia.verdi@hotmail.com','fogliame1','Scrittore'),(9,'Alessandra Neri','AlexN',NULL,NULL,'Visitatore'),(10,'Francesco Gialli','Zafferano','francesco.gialli@virgilio.com','ciao','Scrittore'),(12,'Alessandro Verdi ','IlVerdone','alessandroVerdi@gmail.com','ALLORAAAA','Scrittore'),(14,'Alessandro','Ale','asgdvjasgdgv','asgfhasvfj','Scrittore'),(15,'Wingo','Wingo','Ale@gmail.com','wingo','Scrittore'),(19,'giovanna','gmorra',NULL,NULL,'Visitatore'),(20,'Danilo','Virgelio',NULL,NULL,'Visitatore'),(21,'palle','palle',NULL,NULL,'Visitatore');
/*!40000 ALTER TABLE `utente` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `versioni`
--

DROP TABLE IF EXISTS `versioni`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `versioni` (
  `ID_versione` int NOT NULL AUTO_INCREMENT,
  `Testo` text,
  `Data` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `Numero` int NOT NULL,
  `ID_pagina` int DEFAULT NULL,
  `ID_autore` int DEFAULT NULL,
  `ID_utente_modifica` int DEFAULT NULL,
  PRIMARY KEY (`ID_versione`),
  KEY `ID_pagina` (`ID_pagina`),
  CONSTRAINT `versioni_ibfk_1` FOREIGN KEY (`ID_pagina`) REFERENCES `pagine` (`ID_pagina`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=49 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `versioni`
--

LOCK TABLES `versioni` WRITE;
/*!40000 ALTER TABLE `versioni` DISABLE KEYS */;
INSERT INTO `versioni` VALUES (11,'cos è un database? Un database è un insieme organizzato di [dati], memorizzati e gestiti in modo che possano essere facilmente accessibili.','2025-02-06 14:15:30',1,40,NULL,NULL),(12,'Le informazioni memorizzate nel database, come [record] e prodotti.','2025-02-06 14:15:30',1,41,NULL,NULL),(13,'è la riga di una [tabella] del database.','2025-02-06 14:15:30',1,42,NULL,NULL),(14,'è fondamentale per memorizzare i dati in un database, essa è composta da righe e colonne.','2025-02-06 14:15:30',1,43,NULL,NULL),(15,'I droni di [misterio] erano molto sofisticati ed utilizzavano una quantità enorme di [dati].','2025-02-06 14:15:30',1,44,NULL,NULL),(16,'Super Cattivo della [MCU]','2025-02-06 14:15:30',1,45,NULL,NULL),(17,'Ciao mi chiamo Marco','2025-02-06 14:19:06',2,40,NULL,NULL),(18,'Ciao mi chiamo Marco','2025-02-06 14:23:18',3,40,NULL,NULL),(19,'Ciao mi chiamo Fabrizio','2025-02-06 14:36:24',4,40,NULL,NULL),(20,'Ciao mi chiamo Marco','2025-02-06 15:19:06',5,40,NULL,NULL),(21,'Approvata','2025-02-06 15:28:32',6,40,NULL,NULL),(23,'Marvel Cinematic Universe','2025-02-06 15:55:36',1,48,NULL,NULL),(24,'cos è un database? Un database è un insieme organizzato di [dati], memorizzati e gestiti in modo che possano essere facilmente accessibili.','2025-02-06 15:59:51',7,40,NULL,NULL),(25,'Ciao mi chiamo Marco','2025-02-07 11:30:40',8,40,NULL,NULL),(28,'HELLO THERE','2025-02-07 11:56:45',2,43,NULL,NULL),(29,'HELLO THERE','2025-02-07 12:03:35',3,43,10,8),(30,'HELLO THERE','2025-02-07 12:07:59',4,43,NULL,NULL),(31,'HELLO THERE','2025-02-07 12:13:49',5,43,NULL,NULL),(32,'HELLO THERE','2025-02-07 12:13:49',6,43,10,10),(33,'HELLO THERE','2025-02-07 12:17:19',7,43,10,10),(34,'HELLO THERE','2025-02-07 12:30:43',1,50,8,NULL),(35,'Ciao come stai? mi chiamo [record]','2025-03-10 17:00:19',1,51,15,NULL),(36,'HELLO THERE, GENERAL KENOBI!','2025-03-10 17:33:53',2,50,8,15),(37,'i [dati] all\'interno della [tabella] pagine sono molto grandi.','2025-03-10 19:00:23',1,52,15,NULL),(38,'HELLO THERE, GENERAL KENOBI!','2025-03-10 19:25:25',3,50,8,15),(39,'HELLO THERE, GENERAL KENOBI!','2025-03-10 19:26:34',4,50,8,15),(43,'Marvel dajdioahsifaufg','2025-03-12 10:27:20',2,48,10,15),(44,'HELLO THERE','2025-03-12 10:27:35',8,43,10,10),(45,'HELLO THERE','2025-03-12 10:27:38',9,43,10,10),(46,'HELLO THERE','2025-03-12 10:27:41',10,43,10,10),(47,'HELLO THERE','2025-03-12 10:27:43',11,43,10,10),(48,'HELLO THERE, GENERAL KENOBI!!! (citazione a star wars)','2025-03-12 10:31:52',5,50,8,15);
/*!40000 ALTER TABLE `versioni` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2025-03-26 11:24:20
