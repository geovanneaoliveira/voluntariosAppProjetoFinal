-- MySQL dump 10.13  Distrib 8.0.29, for Win64 (x86_64)
--
-- Host: localhost    Database: voluntariosapp
-- ------------------------------------------------------
-- Server version	8.0.29

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `contribuicao`
--

DROP TABLE IF EXISTS `contribuicao`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `contribuicao` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `valor` decimal(9,2) NOT NULL,
  `data` datetime NOT NULL,
  `id_organizacao` bigint NOT NULL,
  `id_pessoa` bigint NOT NULL,
  PRIMARY KEY (`id`),
  KEY `id_organizacao` (`id_organizacao`),
  KEY `id_pessoa` (`id_pessoa`),
  CONSTRAINT `contribuicao_ibfk_1` FOREIGN KEY (`id_organizacao`) REFERENCES `organizacao` (`id`),
  CONSTRAINT `contribuicao_ibfk_2` FOREIGN KEY (`id_pessoa`) REFERENCES `pessoa` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `contribuicao`
--

LOCK TABLES `contribuicao` WRITE;
/*!40000 ALTER TABLE `contribuicao` DISABLE KEYS */;
INSERT INTO `contribuicao` VALUES (1,250.00,'2022-09-19 20:00:00',1,1),(2,280.00,'2022-09-19 20:00:00',1,1),(3,40.00,'2022-09-20 20:00:00',1,1),(4,40.00,'2022-09-22 20:00:00',2,1),(5,100.00,'2022-09-22 20:00:00',2,1),(6,30.00,'2022-09-22 20:00:00',2,1),(7,30.00,'2022-09-26 20:00:00',3,1),(8,70.00,'2022-09-24 20:00:00',4,1),(9,50.00,'2022-09-24 20:00:00',4,1),(10,3000.00,'2022-09-24 20:00:00',5,1);
/*!40000 ALTER TABLE `contribuicao` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `evento`
--

DROP TABLE IF EXISTS `evento`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `evento` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `nome` varchar(250) NOT NULL,
  `data` datetime NOT NULL,
  `id_organizadora` bigint NOT NULL,
  PRIMARY KEY (`id`),
  KEY `id_organizadora` (`id_organizadora`),
  CONSTRAINT `evento_ibfk_1` FOREIGN KEY (`id_organizadora`) REFERENCES `organizacao` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `evento`
--

LOCK TABLES `evento` WRITE;
/*!40000 ALTER TABLE `evento` DISABLE KEYS */;
INSERT INTO `evento` VALUES (1,'Alimentar animais abandonados','2022-12-11 22:10:00',1);
/*!40000 ALTER TABLE `evento` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `organizacao`
--

DROP TABLE IF EXISTS `organizacao`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `organizacao` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `nome` varchar(250) NOT NULL,
  `id_supervisor` bigint NOT NULL,
  `descricao` varchar(500) DEFAULT NULL,
  `photoPath` varchar(100) DEFAULT NULL,
  `ativo` bit(1) NOT NULL DEFAULT b'1',
  `cnpj` varchar(14) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `id_supervisor` (`id_supervisor`),
  CONSTRAINT `organizacao_ibfk_1` FOREIGN KEY (`id_supervisor`) REFERENCES `pessoa` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `organizacao`
--

LOCK TABLES `organizacao` WRITE;
/*!40000 ALTER TABLE `organizacao` DISABLE KEYS */;
INSERT INTO `organizacao` VALUES (1,'ONG Pets',1,'Cuidamos de pets e animais abandonados',NULL,_binary '',NULL),(2,'Instituto aMar',1,'Defesa da biodiversidade marinha',NULL,_binary '','12345678910000'),(3,'ProVida',1,'Garantia dos direitos básicos aos cidadãos em condições precárias',NULL,_binary '','12345678910001'),(4,'Instituto Nacional de Câncer',1,'Auxiliar vítimas de câncer e tratamentos de recém diagnosticados',NULL,_binary '','12345678910001'),(5,'EducaInter',1,'Educação para crianças em todo o mundo',NULL,_binary '','12345678910001'),(6,'Melhor idade',1,'Defesa e apoio a idoso abandonados pela sua família',NULL,_binary '','12345678910004');
/*!40000 ALTER TABLE `organizacao` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `patrocinador`
--

DROP TABLE IF EXISTS `patrocinador`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `patrocinador` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `nome` varchar(100) NOT NULL,
  `id_representante` bigint NOT NULL,
  PRIMARY KEY (`id`),
  KEY `id_representante` (`id_representante`),
  CONSTRAINT `patrocinador_ibfk_1` FOREIGN KEY (`id_representante`) REFERENCES `pessoa` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `patrocinador`
--

LOCK TABLES `patrocinador` WRITE;
/*!40000 ALTER TABLE `patrocinador` DISABLE KEYS */;
/*!40000 ALTER TABLE `patrocinador` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `patrocinadores_evento`
--

DROP TABLE IF EXISTS `patrocinadores_evento`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `patrocinadores_evento` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `id_patrocinador` bigint NOT NULL,
  `id_evento` bigint NOT NULL,
  PRIMARY KEY (`id`),
  KEY `id_patrocinador` (`id_patrocinador`),
  KEY `id_evento` (`id_evento`),
  CONSTRAINT `patrocinadores_evento_ibfk_1` FOREIGN KEY (`id_patrocinador`) REFERENCES `patrocinador` (`id`),
  CONSTRAINT `patrocinadores_evento_ibfk_2` FOREIGN KEY (`id_evento`) REFERENCES `evento` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `patrocinadores_evento`
--

LOCK TABLES `patrocinadores_evento` WRITE;
/*!40000 ALTER TABLE `patrocinadores_evento` DISABLE KEYS */;
/*!40000 ALTER TABLE `patrocinadores_evento` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `pessoa`
--

DROP TABLE IF EXISTS `pessoa`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `pessoa` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `nome` varchar(50) NOT NULL,
  `sobrenome` varchar(50) NOT NULL,
  `telefone` varchar(50) NOT NULL,
  `cpf` char(11) NOT NULL,
  `login` varchar(100) NOT NULL,
  `senha` varchar(50) NOT NULL,
  `ativo` bit(1) NOT NULL DEFAULT b'1',
  PRIMARY KEY (`id`),
  UNIQUE KEY `login` (`login`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `pessoa`
--

LOCK TABLES `pessoa` WRITE;
/*!40000 ALTER TABLE `pessoa` DISABLE KEYS */;
INSERT INTO `pessoa` VALUES (1,'geovanne','almeida','40028922','12312312312','admin','admin',_binary '');
/*!40000 ALTER TABLE `pessoa` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `pessoas_evento`
--

DROP TABLE IF EXISTS `pessoas_evento`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `pessoas_evento` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `id_pessoa` bigint NOT NULL,
  `id_evento` bigint NOT NULL,
  `presenca` bit(1) NOT NULL DEFAULT b'1',
  PRIMARY KEY (`id`),
  KEY `id_evento` (`id_evento`),
  KEY `id_pessoa` (`id_pessoa`),
  CONSTRAINT `pessoas_evento_ibfk_1` FOREIGN KEY (`id_evento`) REFERENCES `evento` (`id`),
  CONSTRAINT `pessoas_evento_ibfk_2` FOREIGN KEY (`id_pessoa`) REFERENCES `pessoa` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `pessoas_evento`
--

LOCK TABLES `pessoas_evento` WRITE;
/*!40000 ALTER TABLE `pessoas_evento` DISABLE KEYS */;
/*!40000 ALTER TABLE `pessoas_evento` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tag_interesse_pessoa`
--

DROP TABLE IF EXISTS `tag_interesse_pessoa`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tag_interesse_pessoa` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `id_pessoa` bigint NOT NULL,
  `id_tag` bigint NOT NULL,
  PRIMARY KEY (`id`),
  KEY `id_pessoa` (`id_pessoa`),
  KEY `id_tag` (`id_tag`),
  CONSTRAINT `tag_interesse_pessoa_ibfk_1` FOREIGN KEY (`id_pessoa`) REFERENCES `pessoa` (`id`),
  CONSTRAINT `tag_interesse_pessoa_ibfk_2` FOREIGN KEY (`id_tag`) REFERENCES `tags` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tag_interesse_pessoa`
--

LOCK TABLES `tag_interesse_pessoa` WRITE;
/*!40000 ALTER TABLE `tag_interesse_pessoa` DISABLE KEYS */;
INSERT INTO `tag_interesse_pessoa` VALUES (1,1,1),(2,1,2);
/*!40000 ALTER TABLE `tag_interesse_pessoa` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tags`
--

DROP TABLE IF EXISTS `tags`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tags` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `nome` varchar(45) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tags`
--

LOCK TABLES `tags` WRITE;
/*!40000 ALTER TABLE `tags` DISABLE KEYS */;
INSERT INTO `tags` VALUES (1,'Culinária'),(2,'Doação de Sangue'),(4,'Arrecadação de Roupas'),(5,'Doação de cestas-básicas'),(6,'Feira de adoção'),(7,'Pets'),(8,'Crianças'),(9,'Caridade'),(10,'Vaquinha');
/*!40000 ALTER TABLE `tags` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tags_evento`
--

DROP TABLE IF EXISTS `tags_evento`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tags_evento` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `id_evento` bigint NOT NULL,
  `id_tag` bigint NOT NULL,
  PRIMARY KEY (`id`),
  KEY `id_evento` (`id_evento`),
  KEY `id_tag` (`id_tag`),
  CONSTRAINT `tags_evento_ibfk_1` FOREIGN KEY (`id_evento`) REFERENCES `evento` (`id`),
  CONSTRAINT `tags_evento_ibfk_2` FOREIGN KEY (`id_tag`) REFERENCES `tags` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tags_evento`
--

LOCK TABLES `tags_evento` WRITE;
/*!40000 ALTER TABLE `tags_evento` DISABLE KEYS */;
INSERT INTO `tags_evento` VALUES (3,1,1);
/*!40000 ALTER TABLE `tags_evento` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping routines for database 'voluntariosapp'
--
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2022-09-23 18:55:27
