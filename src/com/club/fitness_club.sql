CREATE DATABASE  IF NOT EXISTS `fitness_club` /*!40100 DEFAULT CHARACTER SET latin1 */;
USE `fitness_club`;
-- MySQL dump 10.13  Distrib 8.0.17, for Linux (x86_64)
--
-- Host: localhost    Database: fitness_club
-- ------------------------------------------------------
-- Server version	5.7.27

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
-- Table structure for table `clients`
--

DROP TABLE IF EXISTS `clients`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `clients` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `first_name` varchar(45) DEFAULT NULL,
  `last_name` varchar(45) DEFAULT NULL,
  `is_active` tinyint(4) NOT NULL DEFAULT '1',
  `manager_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_clients_manager_id_idx` (`manager_id`),
  CONSTRAINT `fk_clients_manager_id` FOREIGN KEY (`manager_id`) REFERENCES `managers` (`id`) ON DELETE CASCADE ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=50 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `clients`
--

LOCK TABLES `clients` WRITE;
/*!40000 ALTER TABLE `clients` DISABLE KEYS */;
INSERT INTO `clients` VALUES (1,'Victor','Mitcov',1,2),(2,'Test','Testov',0,2),(3,'Test sdf','Testov',0,1),(4,'Test2','Test2',1,3),(5,'','test updateb fd',1,3),(6,'asd','asdf',1,1),(7,'Woks','or not',1,2),(8,'','',0,1),(9,'FIrst name','Last name sad',1,3),(10,'Is it working ','iuhuu',0,1),(11,'David','Özdemir',0,3),(12,'Nathan','Kleijse',0,2),(13,'Marijn','Kramer',1,2),(14,'Catharina','Blom iii haa',0,1),(15,'Keano','van der Pluijm',1,4),(16,'Julia','Hassan',0,3),(17,'Jente','Mansveld',1,4),(18,'Arthur','Groen',0,3),(19,'Feline','Meijer',1,1),(20,'Gijs asdfa','van der Heyden',1,1),(21,'Tristan','Ketting',1,2),(22,'asdf','asdf',0,3),(23,'adsfas','dasf',1,NULL),(24,'asdf','asdf',1,2),(25,'123','123',1,3),(26,'Trololo','Trololo',1,3),(27,'David','Özdemir',1,NULL),(28,'Nathan','Kleijse',0,NULL),(29,'Marijn','Kramer',1,NULL),(30,'Catharina','Blom',0,3),(31,'Keano','van der Pluijm',1,2),(32,'Julia','Hassan',0,NULL),(33,'Jente','Mansveld',1,NULL),(34,'Arthur','Groen',0,1),(35,'Feline','Meijer',1,NULL),(36,'Gijs','van der Heyden',0,NULL),(37,'Tristan','Ketting',1,1),(38,'David','Özdemir',1,NULL),(39,'Nathan','Kleijse',1,3),(40,'Marijn','Kramer',1,3),(41,'Catharina','Blom',0,NULL),(42,'Keano','van der Pluijm',1,NULL),(43,'Julia','Hassan',0,2),(44,'Jente','Mansveld',1,NULL),(45,'Arthur','Groen',0,3),(46,'Feline','Meijer',1,NULL),(47,'Gijs','van der Heyden',0,NULL),(48,'Tristan','Ketting',1,1),(49,'asdf','asdf',1,1);
/*!40000 ALTER TABLE `clients` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `managers`
--

DROP TABLE IF EXISTS `managers`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `managers` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `first_name` varchar(45) DEFAULT NULL,
  `last_name` varchar(45) DEFAULT NULL,
  `salary` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `managers`
--

LOCK TABLES `managers` WRITE;
/*!40000 ALTER TABLE `managers` DISABLE KEYS */;
INSERT INTO `managers` VALUES (1,'Test','Test','100'),(2,'Test4','Test4','100'),(3,'Test2','Test2','200'),(4,'Test3','Tes3','300'),(5,'tr','tr','2');
/*!40000 ALTER TABLE `managers` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `subscriptions`
--

DROP TABLE IF EXISTS `subscriptions`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `subscriptions` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  `price` int(11) NOT NULL,
  `type` enum('morning','evening','holidays','unlimited') NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `subscriptions`
--

LOCK TABLES `subscriptions` WRITE;
/*!40000 ALTER TABLE `subscriptions` DISABLE KEYS */;
INSERT INTO `subscriptions` VALUES (1,'Morning 6 moths',1000,'morning'),(2,'Morning 1 year',1500,'morning'),(3,'Evening 6 moths',1200,'evening'),(4,'Evening 1 year',1800,'evening'),(5,'Holidays 3 moths',700,'holidays'),(6,'Holidays 6 months',1000,'holidays'),(7,'Unlimited 3 moths',1000,'unlimited'),(8,'Unlimited 6 moths',1700,'unlimited'),(9,'Unlimited 1 year',3000,'unlimited');
/*!40000 ALTER TABLE `subscriptions` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2019-11-18  0:30:37
