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
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2025-03-26 12:57:58
