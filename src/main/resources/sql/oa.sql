CREATE DATABASE  IF NOT EXISTS `oa` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `oa`;
-- MySQL dump 10.13  Distrib 5.7.22, for Win64 (x86_64)
--
-- Host: localhost    Database: oa
-- ------------------------------------------------------
-- Server version	5.7.22-log

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
-- Table structure for table `department`
--

DROP TABLE IF EXISTS `department`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `department` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(45) DEFAULT NULL,
  `date` date DEFAULT NULL,
  `parent_id` bigint(20) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=27 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `department`
--

LOCK TABLES `department` WRITE;
/*!40000 ALTER TABLE `department` DISABLE KEYS */;
INSERT INTO `department` VALUES (1,'d_aaa','1999-06-12',NULL,'aaa'),(2,'d_bbb','1999-06-12',1,'bbb'),(3,'d_ccc','1999-06-12',2,'ccc'),(4,'d_ddd','1999-06-12',2,'ddd'),(5,'d_eee','1999-06-12',3,'eee'),(6,'d_fff','1999-06-12',3,'fff'),(7,'d_ggg','1999-06-12',3,'ggg'),(8,'d_hhh','1999-06-12',4,'hhh'),(9,'d_iii','1999-06-12',4,'iii'),(10,'d_jjj','1999-06-12',4,'jjj'),(11,'d_kkk','1999-06-12',4,'kkk'),(12,'d_lll','1999-06-12',5,'lll'),(13,'d_mmm','1999-06-12',5,'mmm'),(14,'d_nnn','1999-06-12',5,'nnn'),(15,'d_ooo','1999-06-12',5,'ooo'),(16,'d_ppp','1999-06-12',5,'ppp'),(17,'d_qqq','1999-06-12',6,'qqq'),(18,'d_rrr','1999-06-12',6,'rrr'),(19,'d_sss','1999-06-12',6,'sss'),(20,'d_ttt','1999-06-12',6,'ttt'),(21,'d_uuu','1999-06-12',6,'uuu'),(22,'d_vvv','1999-06-12',6,'vvv'),(23,'d_www','1999-06-12',7,'www'),(24,'d_xxx','1999-06-12',7,'xxx'),(25,'d_yyy','1999-06-12',7,'yyy'),(26,'d_zzz','1999-06-12',7,'zzz');
/*!40000 ALTER TABLE `department` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `member`
--

DROP TABLE IF EXISTS `member`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `member` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `username` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `gender` varchar(255) DEFAULT NULL,
  `phone` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `avatar` varchar(255) DEFAULT NULL,
  `date` date DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `department_id` bigint(20) DEFAULT NULL,
  `role_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_User_Role_idx` (`role_id`),
  CONSTRAINT `fk_User_Role_idx` FOREIGN KEY (`role_id`) REFERENCES `role` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `member`
--

LOCK TABLES `member` WRITE;
/*!40000 ALTER TABLE `member` DISABLE KEYS */;
INSERT INTO `member` VALUES (1,'admin','$2a$10$WMY2HvPRYqNxmKcDEkn7BOUKjgAkJM7RGYoz4loRjUyzeFNO6tSB6','admin','male','15094135415','admin@ccc.com','/images/upload/3f9894fdd495469d9f4a2c2189be4453.png','1998-07-19','111',1,1),(2,'depts','$2a$10$WMY2HvPRYqNxmKcDEkn7BOUKjgAkJM7RGYoz4loRjUyzeFNO6tSB6','depts','female','13648413487','depts@ccc.com','/images/upload/8269fe03e80841a3890770dc7db46c21.png','2003-06-09','111',2,2),(3,'users','$2a$10$WMY2HvPRYqNxmKcDEkn7BOUKjgAkJM7RGYoz4loRjUyzeFNO6tSB6','users','male','13059841164','users@ccc.com','/images/upload/20bb95d232d5419ba70878bc15511d5a.png','2005-08-21','111',3,3),(4,'roles','$2a$10$WMY2HvPRYqNxmKcDEkn7BOUKjgAkJM7RGYoz4loRjUyzeFNO6tSB6','roles','female','17841541134','roles@ccc.com','/images/upload/8bdad886e63f4d5e90023a0320e7d975.png','2001-03-06','111',2,4),(5,'user','$2a$10$WMY2HvPRYqNxmKcDEkn7BOUKjgAkJM7RGYoz4loRjUyzeFNO6tSB6','user','male','15648745615','user@ccc.com','/images/upload/f26548e9f60f4f64bd601fcb0ba511d2.png','2000-01-30','111',3,5),(6,'test','$2a$10$rjgq7LIVp1ctaCntVjnr8uJYySUYMIkMK.v2X3rM2ffO5lwopz6fO','test','male','13712412423','test@ccc.com','/images/upload/560cbb70bf424a2e84cdcb4f5b0bbddb.png','2019-02-11','test',2,5),(10,'test1','$2a$10$QOh471yM6JFzQW.XhHLgfe0995dNYYn/Ln6Bx4Ugi7nEz771k8v1a','test1','female','18561654897','test1@ccc.com','/images/upload/560cbb70bf424a2e84cdcb4f5b0bbddb.png','2019-08-12','tes1',3,5),(11,'test2','$2a$10$WMY2HvPRYqNxmKcDEkn7BOUKjgAkJM7RGYoz4loRjUyzeFNO6tSB6','test2','male','14564513247','test2@ccc.com','/images/upload/560cbb70bf424a2e84cdcb4f5b0bbddb.png','2019-08-12','test2',4,5),(12,'test3','$2a$10$WMY2HvPRYqNxmKcDEkn7BOUKjgAkJM7RGYoz4loRjUyzeFNO6tSB6','test3','male','13545479871','test3@ccc.com','/images/upload/560cbb70bf424a2e84cdcb4f5b0bbddb.png','2019-08-12','test3',5,5),(13,'test4','$2a$10$WMY2HvPRYqNxmKcDEkn7BOUKjgAkJM7RGYoz4loRjUyzeFNO6tSB6','test4','female','13357678979','test4@ccc.com','/images/upload/560cbb70bf424a2e84cdcb4f5b0bbddb.png','2019-08-12','test4',5,5),(14,'test5','$2a$10$WMY2HvPRYqNxmKcDEkn7BOUKjgAkJM7RGYoz4loRjUyzeFNO6tSB6','test5','female','15137678978','test5@ccc.com','/images/upload/560cbb70bf424a2e84cdcb4f5b0bbddb.png','2019-08-12','test5',6,5),(15,'test6','$2a$10$WMY2HvPRYqNxmKcDEkn7BOUKjgAkJM7RGYoz4loRjUyzeFNO6tSB6','test6','female','16578978943','test6@ccc.com','/images/upload/560cbb70bf424a2e84cdcb4f5b0bbddb.png','2019-08-12','test6',6,5),(16,'test7','$2a$10$WMY2HvPRYqNxmKcDEkn7BOUKjgAkJM7RGYoz4loRjUyzeFNO6tSB6','test7','male','19874564564','test7@ccc.com','/images/upload/560cbb70bf424a2e84cdcb4f5b0bbddb.png','2019-08-12','test7',7,5);
/*!40000 ALTER TABLE `member` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `permission`
--

DROP TABLE IF EXISTS `permission`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `permission` (
  `id` bigint(20) NOT NULL,
  `name` varchar(45) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `url` varchar(45) DEFAULT NULL,
  `method` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `permission`
--

LOCK TABLES `permission` WRITE;
/*!40000 ALTER TABLE `permission` DISABLE KEYS */;
INSERT INTO `permission` VALUES (1,'ROLE_USER','USER','',''),(2,'ROLE_DEPTS','DEPTS','',''),(3,'ROLE_USERS','USERS','',''),(4,'ROLE_ROLES','ROLES','','');
/*!40000 ALTER TABLE `permission` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `role`
--

DROP TABLE IF EXISTS `role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `role` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(45) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `role`
--

LOCK TABLES `role` WRITE;
/*!40000 ALTER TABLE `role` DISABLE KEYS */;
INSERT INTO `role` VALUES (1,'administrator','全部权限'),(2,'dept_admin','操作部门权限'),(3,'user_admin','操作用户权限'),(4,'role_admin','操作角色权限'),(5,'user','用户权限');
/*!40000 ALTER TABLE `role` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `role_has_permission`
--

DROP TABLE IF EXISTS `role_has_permission`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `role_has_permission` (
  `Role_id` bigint(20) NOT NULL,
  `Permission_id` bigint(20) NOT NULL,
  PRIMARY KEY (`Role_id`,`Permission_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `role_has_permission`
--

LOCK TABLES `role_has_permission` WRITE;
/*!40000 ALTER TABLE `role_has_permission` DISABLE KEYS */;
INSERT INTO `role_has_permission` VALUES (1,1),(1,2),(1,3),(1,4),(2,1),(2,2),(3,1),(3,3),(4,1),(4,4),(5,1);
/*!40000 ALTER TABLE `role_has_permission` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2019-09-03 19:53:50
