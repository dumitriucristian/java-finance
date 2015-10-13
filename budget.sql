-- MySQL dump 10.13  Distrib 5.5.44, for debian-linux-gnu (i686)
--
-- Host: localhost    Database: Budget
-- ------------------------------------------------------
-- Server version	5.5.44-0ubuntu0.14.04.1

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `detailed_recurrence_income`
--

DROP TABLE IF EXISTS `detailed_recurrence_income`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `detailed_recurrence_income` (
  `dri_id` int(8) NOT NULL AUTO_INCREMENT,
  `inc_rec_id` int(8) NOT NULL,
  `inc_date` datetime NOT NULL,
  PRIMARY KEY (`dri_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `detailed_recurrence_income`
--

LOCK TABLES `detailed_recurrence_income` WRITE;
/*!40000 ALTER TABLE `detailed_recurrence_income` DISABLE KEYS */;
INSERT INTO `detailed_recurrence_income` VALUES (1,1,'2015-10-31 00:00:00');
/*!40000 ALTER TABLE `detailed_recurrence_income` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `income`
--

DROP TABLE IF EXISTS `income`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `income` (
  `income_id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) NOT NULL,
  `incom_name` varchar(32) NOT NULL,
  `value` decimal(8,2) NOT NULL,
  `income_date` date NOT NULL,
  `recorded_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `income_type_id` int(8) NOT NULL,
  `recurrent` tinyint(4) NOT NULL,
  `income_preset` tinyint(4) NOT NULL,
  PRIMARY KEY (`income_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `income`
--

LOCK TABLES `income` WRITE;
/*!40000 ALTER TABLE `income` DISABLE KEYS */;
INSERT INTO `income` VALUES (1,8,'avans salariu',1555.55,'2015-08-06','2015-09-23 14:30:59',1,1,2),(2,8,'petrecre',15.05,'2015-10-05','2015-09-23 14:33:37',5,0,2);
/*!40000 ALTER TABLE `income` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `income_recurrence`
--

DROP TABLE IF EXISTS `income_recurrence`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `income_recurrence` (
  `inc_rec_id` int(8) NOT NULL AUTO_INCREMENT,
  `income_id` int(8) NOT NULL,
  `user_id` int(8) NOT NULL,
  `start_date` datetime NOT NULL,
  `end_date` datetime DEFAULT NULL,
  `frequency_id` tinyint(4) NOT NULL,
  `frequency_value` varchar(12) NOT NULL,
  `set_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`inc_rec_id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `income_recurrence`
--

LOCK TABLES `income_recurrence` WRITE;
/*!40000 ALTER TABLE `income_recurrence` DISABLE KEYS */;
INSERT INTO `income_recurrence` VALUES (1,1,8,'2015-08-25 00:00:00','2015-09-25 00:00:00',0,'','2015-09-18 14:14:07'),(2,1,1,'2015-11-08 00:00:00','2015-11-09 00:00:00',1,'1','2015-09-24 06:43:01'),(3,1,1,'2015-11-08 00:00:00','2015-11-09 00:00:00',1,'1','2015-09-24 07:46:46'),(4,1,1,'2015-11-08 00:00:00','2015-11-09 00:00:00',1,'1','2015-09-24 07:47:38'),(5,1,1,'2015-11-08 00:00:00','2015-11-09 00:00:00',1,'1','2015-09-25 07:07:57');
/*!40000 ALTER TABLE `income_recurrence` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `income_type`
--

DROP TABLE IF EXISTS `income_type`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `income_type` (
  `income_type_id` int(8) NOT NULL AUTO_INCREMENT,
  `name` varchar(30) NOT NULL,
  `description` varchar(220) DEFAULT NULL,
  PRIMARY KEY (`income_type_id`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `income_type`
--

LOCK TABLES `income_type` WRITE;
/*!40000 ALTER TABLE `income_type` DISABLE KEYS */;
INSERT INTO `income_type` VALUES (1,'salaries',NULL),(2,'services',NULL),(3,'pensions',NULL),(4,'goods',NULL),(5,'stocks',NULL),(6,'dividends',NULL),(7,'rentals',NULL),(8,'shcolarships',NULL),(9,'alimonys',NULL),(10,'interest',NULL),(11,'legacies',NULL),(12,'fortune',NULL),(13,'gambling',NULL),(14,'prizes',NULL),(15,'gaming',NULL),(16,'loan',NULL);
/*!40000 ALTER TABLE `income_type` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `recurrence_frequency`
--

DROP TABLE IF EXISTS `recurrence_frequency`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `recurrence_frequency` (
  `frequency_id` int(2) NOT NULL AUTO_INCREMENT,
  `frequency_name` varchar(8) NOT NULL,
  PRIMARY KEY (`frequency_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `recurrence_frequency`
--

LOCK TABLES `recurrence_frequency` WRITE;
/*!40000 ALTER TABLE `recurrence_frequency` DISABLE KEYS */;
INSERT INTO `recurrence_frequency` VALUES (1,'Daily'),(2,'Monthly');
/*!40000 ALTER TABLE `recurrence_frequency` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `users` (
  `id` int(6) unsigned NOT NULL AUTO_INCREMENT,
  `firstname` varchar(30) NOT NULL,
  `lastname` varchar(30) NOT NULL,
  `email` varchar(30) NOT NULL,
  `password` varchar(30) NOT NULL,
  `reg_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES (8,'Cristian','Dumitriu','dumicriucristian@yahoo.com','test2','2015-09-15 07:02:42'),(9,'Simona','Halep','simona@yahoo.com','222','2015-09-08 11:17:13'),(10,'Andrei','Sfia','andrei.sfia@gmail.com','gogosar','2015-09-15 06:26:43');
/*!40000 ALTER TABLE `users` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2015-10-13 16:58:42
