-- MySQL dump 10.13  Distrib 8.0.18, for Linux (x86_64)
--
-- Host: localhost    Database: COLECAO-QUADRINHOS
-- ------------------------------------------------------
-- Server version	8.0.18

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
-- Table structure for table `AMIGO`
--

DROP TABLE IF EXISTS `AMIGO`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `AMIGO` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `NOME` varchar(45) DEFAULT NULL,
  `APELIDO` varchar(45) DEFAULT NULL,
  `NUMERO_CELULAR` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `AMIGO`
--

LOCK TABLES `AMIGO` WRITE;
/*!40000 ALTER TABLE `AMIGO` DISABLE KEYS */;
INSERT INTO `AMIGO` VALUES (1,'Eddie Kalleb','Eddinho','12345678');
/*!40000 ALTER TABLE `AMIGO` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `EMPRESTIMO`
--

DROP TABLE IF EXISTS `EMPRESTIMO`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `EMPRESTIMO` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `DATA_REALIZACAO` date NOT NULL,
  `DATA_DEVOLUCAO` date DEFAULT NULL,
  `STATUS` varchar(45) NOT NULL,
  `AMIGO_ID` int(11) NOT NULL,
  PRIMARY KEY (`ID`,`AMIGO_ID`),
  KEY `fk_EMPRESTIMO_AMIGO1_idx` (`AMIGO_ID`),
  CONSTRAINT `fk_EMPRESTIMO_AMIGO1` FOREIGN KEY (`AMIGO_ID`) REFERENCES `AMIGO` (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `EMPRESTIMO`
--

LOCK TABLES `EMPRESTIMO` WRITE;
/*!40000 ALTER TABLE `EMPRESTIMO` DISABLE KEYS */;
INSERT INTO `EMPRESTIMO` VALUES (1,'2019-12-01',NULL,'0',1),(2,'2019-12-01',NULL,'0',1);
/*!40000 ALTER TABLE `EMPRESTIMO` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `EMPRESTIMO_TEM_QUADRINHO`
--

DROP TABLE IF EXISTS `EMPRESTIMO_TEM_QUADRINHO`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `EMPRESTIMO_TEM_QUADRINHO` (
  `EMPRESTIMO_ID` int(11) NOT NULL,
  `QUADRINHO_ID` int(11) NOT NULL,
  PRIMARY KEY (`EMPRESTIMO_ID`,`QUADRINHO_ID`),
  KEY `fk_EMPRESTIMO_has_QUADRINHO_QUADRINHO1_idx` (`QUADRINHO_ID`),
  KEY `fk_EMPRESTIMO_has_QUADRINHO_EMPRESTIMO1_idx` (`EMPRESTIMO_ID`),
  CONSTRAINT `fk_EMPRESTIMO_has_QUADRINHO_EMPRESTIMO1` FOREIGN KEY (`EMPRESTIMO_ID`) REFERENCES `EMPRESTIMO` (`ID`),
  CONSTRAINT `fk_EMPRESTIMO_has_QUADRINHO_QUADRINHO1` FOREIGN KEY (`QUADRINHO_ID`) REFERENCES `QUADRINHO` (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `EMPRESTIMO_TEM_QUADRINHO`
--

LOCK TABLES `EMPRESTIMO_TEM_QUADRINHO` WRITE;
/*!40000 ALTER TABLE `EMPRESTIMO_TEM_QUADRINHO` DISABLE KEYS */;
INSERT INTO `EMPRESTIMO_TEM_QUADRINHO` VALUES (2,2);
/*!40000 ALTER TABLE `EMPRESTIMO_TEM_QUADRINHO` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `QUADRINHO`
--

DROP TABLE IF EXISTS `QUADRINHO`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `QUADRINHO` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `TITULO_ID` int(11) NOT NULL,
  `QUANTIDADE` int(10) DEFAULT NULL,
  `VOLUME` int(10) DEFAULT NULL,
  `ESTADO_CONSERVACAO` varchar(45) DEFAULT NULL,
  `VALOR` double DEFAULT NULL,
  `URL_IMAGEM` mediumtext,
  PRIMARY KEY (`ID`,`TITULO_ID`),
  KEY `fk_QUADRINHO_TITULO1_idx` (`TITULO_ID`),
  CONSTRAINT `fk_QUADRINHO_TITULO1` FOREIGN KEY (`TITULO_ID`) REFERENCES `TITULO` (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `QUADRINHO`
--

LOCK TABLES `QUADRINHO` WRITE;
/*!40000 ALTER TABLE `QUADRINHO` DISABLE KEYS */;
INSERT INTO `QUADRINHO` VALUES (2,1,5,1,'0',2.5,NULL);
/*!40000 ALTER TABLE `QUADRINHO` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `TITULO`
--

DROP TABLE IF EXISTS `TITULO`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `TITULO` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `NOME` varchar(45) DEFAULT NULL,
  `ESTADO_COLECAO` varchar(45) DEFAULT NULL,
  `URL_IMAGEM` mediumtext,
  `AVALIACAO` int(11) DEFAULT NULL,
  `TIPO` varchar(45) DEFAULT NULL,
  `EDITORA` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `TITULO`
--

LOCK TABLES `TITULO` WRITE;
/*!40000 ALTER TABLE `TITULO` DISABLE KEYS */;
INSERT INTO `TITULO` VALUES (1,'Naruto','FINALIZADO','naruto.com',5,'MANGA','Moderna');
/*!40000 ALTER TABLE `TITULO` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping events for database 'COLECAO-QUADRINHOS'
--

--
-- Dumping routines for database 'COLECAO-QUADRINHOS'
--
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2019-12-01 21:52:27
