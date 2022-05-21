-- MySQL dump 10.13  Distrib 8.0.21, for Win64 (x86_64)
--
-- Host: 101.132.122.132    Database: testRig
-- ------------------------------------------------------
-- Server version	8.0.28-0ubuntu0.20.04.3

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
-- Table structure for table `post`
--

DROP TABLE IF EXISTS `post`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `post` (
  `pid` int NOT NULL AUTO_INCREMENT COMMENT '帖子id',
  `username` varchar(20) NOT NULL COMMENT '用户名',
  `post_name` varchar(128) DEFAULT NULL COMMENT '帖子名称',
  `text` text NOT NULL COMMENT '帖子内容',
  `likes` int DEFAULT NULL COMMENT '点赞',
  `hates` int DEFAULT NULL COMMENT '踩',
  `access_permission` varchar(20) DEFAULT NULL COMMENT '访问权限: only-me/public/private',
  `access_times` int DEFAULT NULL COMMENT '访问次数',
  `view_count` int DEFAULT NULL,
  `is_delete` int DEFAULT NULL COMMENT '是否删除：0-未删除，1-已删除',
  `is_select` int DEFAULT NULL COMMENT '是否精选：0-未精选，1-精选',
  `is_above` int DEFAULT NULL COMMENT '是否精选：0-不置顶，1-置顶',
  `tag1` varchar(20) DEFAULT NULL,
  `tag2` varchar(20) DEFAULT NULL,
  `tag3` varchar(20) DEFAULT NULL,
  `tag4` varchar(20) DEFAULT NULL,
  `tag5` varchar(20) DEFAULT NULL,
  `tag6` varchar(20) DEFAULT NULL,
  `tag7` varchar(20) DEFAULT NULL,
  `tag8` varchar(20) DEFAULT NULL,
  `feature1` varchar(20) DEFAULT NULL,
  `feature2` varchar(20) DEFAULT NULL,
  `feature3` varchar(20) DEFAULT NULL,
  `feature4` varchar(20) DEFAULT NULL,
  `feature5` varchar(20) DEFAULT NULL,
  `feature6` varchar(20) DEFAULT NULL,
  `feature7` varchar(20) DEFAULT NULL,
  `feature8` varchar(20) DEFAULT NULL,
  `created_user` varchar(20) DEFAULT NULL COMMENT '日志-创建人',
  `created_time` datetime DEFAULT NULL COMMENT '日志-创建时间',
  `modified_user` varchar(20) DEFAULT NULL COMMENT '日志-最后修改执行人',
  `modified_time` datetime DEFAULT NULL COMMENT '日志-最后修改时间',
  `is_like` tinyint DEFAULT NULL,
  PRIMARY KEY (`pid`)
) ENGINE=InnoDB AUTO_INCREMENT=125 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `post`
--

/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2022-04-22  1:12:52
