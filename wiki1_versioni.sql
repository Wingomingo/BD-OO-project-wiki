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

-- Dump completed on 2025-03-26 12:57:58
