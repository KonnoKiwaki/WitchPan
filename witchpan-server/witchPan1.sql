-- MySQL dump 10.13  Distrib 8.0.30, for Win64 (x86_64)
--
-- Host: 192.168.150.100    Database: witchPan
-- ------------------------------------------------------
-- Server version	5.7.36-log

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
-- Table structure for table `email_code`
--

DROP TABLE IF EXISTS `email_code`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `email_code` (
  `email` varchar(150) NOT NULL COMMENT '邮箱',
  `code` varchar(5) NOT NULL COMMENT '验证码',
  `status` tinyint(1) DEFAULT NULL COMMENT '0:未使用 1:已使用',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`email`,`code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `email_code`
--

LOCK TABLES `email_code` WRITE;
/*!40000 ALTER TABLE `email_code` DISABLE KEYS */;
INSERT INTO `email_code` VALUES ('2274483920@qq.com','76771',1,'2024-05-16 16:00:29'),('2314049824@qq.com','01563',1,'2024-05-17 19:27:45'),('2476328100@qq.com','55027',1,'2024-05-16 23:30:09'),('2476328100@qq.com','94379',1,'2024-05-16 23:30:03'),('2476328100@qq.com','97831',1,'2024-05-16 23:30:03'),('2627311935@qq.com','07662',1,'2024-04-23 17:31:21'),('2627311935@qq.com','18624',1,NULL),('2627311935@qq.com','18740',1,NULL),('2627311935@qq.com','38614',1,'2024-04-23 17:34:15'),('2627311935@qq.com','39451',1,NULL),('2627311935@qq.com','52443',1,NULL),('2627311935@qq.com','70266',1,'2024-04-23 16:30:13'),('2627311935@qq.com','73964',1,NULL),('2627311935@qq.com','78583',1,NULL),('2627311935@qq.com','80746',1,'2023-06-04 14:44:19'),('2627311935@qq.com','99851',1,'2023-05-27 13:29:19'),('2729070152@qq.com','15958',1,'2024-05-11 20:30:00'),('2729070152@qq.com','23918',1,'2024-05-09 22:21:03'),('2729070152@qq.com','56305',1,'2024-05-09 22:21:32'),('2729070152@qq.com','62141',0,'2024-05-11 20:48:52'),('2729070152@qq.com','75049',1,'2024-05-11 20:25:06'),('test@qq.test','53570',0,'2023-05-26 22:50:56');
/*!40000 ALTER TABLE `email_code` ENABLE KEYS */;
UNLOCK TABLES;
