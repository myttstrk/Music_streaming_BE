-- MySQL dump 10.13  Distrib 8.2.0, for Win64 (x86_64)
--
-- Host: localhost    Database: music_streaming
-- ------------------------------------------------------
-- Server version	8.2.0

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
-- Table structure for table `ads`
--

DROP TABLE IF EXISTS `ads`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `ads` (
  `id` bigint unsigned NOT NULL AUTO_INCREMENT,
  `AdTitle` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL,
  `AdContent` text NOT NULL,
  `AdImage` text,
  `AdType` varchar(255) NOT NULL,
  `TargetAudience` bigint NOT NULL,
  `StartDate` datetime NOT NULL,
  `EndDate` datetime NOT NULL,
  `ad_content` text NOT NULL,
  `ad_image` text,
  `ad_title` varchar(255) NOT NULL,
  `ad_type` varchar(255) NOT NULL,
  `end_date` datetime(6) NOT NULL,
  `start_date` datetime(6) NOT NULL,
  `target_audience` bigint NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ads`
--

LOCK TABLES `ads` WRITE;
/*!40000 ALTER TABLE `ads` DISABLE KEYS */;
/*!40000 ALTER TABLE `ads` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `albums`
--

DROP TABLE IF EXISTS `albums`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `albums` (
  `id` int NOT NULL AUTO_INCREMENT,
  `artist_id` bigint NOT NULL,
  `name` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL,
  `cover` varchar(255) DEFAULT NULL,
  `created_at` datetime NOT NULL,
  `updated_at` datetime DEFAULT NULL,
  `play_count` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `albums_name_index` (`name`),
  KEY `FK72gqyi6l1j674radjyitcm86f` (`artist_id`),
  CONSTRAINT `FK72gqyi6l1j674radjyitcm86f` FOREIGN KEY (`artist_id`) REFERENCES `artists` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `albums`
--

LOCK TABLES `albums` WRITE;
/*!40000 ALTER TABLE `albums` DISABLE KEYS */;
INSERT INTO `albums` VALUES (1,1,'Hß╗ô hß║¡n t├¼nh ─æß╗¥i ','https://storage.googleapis.com/musicstreaming/Cover/Albumnull.jpg','2024-10-26 16:15:06',NULL,NULL),(2,1,'Cuß╗Öc t├¼nh n├áy','https://storage.googleapis.com/musicstreaming/Cover/Albumnull.jpg','2024-10-27 00:38:07',NULL,NULL),(3,1,'Cuß╗Öc t├¼nh n├áy','https://storage.googleapis.com/musicstreaming/Cover/Album3.jpg','2024-10-27 00:43:11',NULL,NULL),(4,1,'Cuß╗Öc t├¼nh n├áy','https://storage.googleapis.com/musicstreaming/Cover/Album/4.jpg','2024-10-27 00:52:27',NULL,NULL),(5,1,'Cuß╗Öc t├¼nh n├áy','https://storage.googleapis.com/musicstreaming/Cover/Album/5.jpg','2024-10-27 00:55:06',NULL,NULL),(6,1,'Cuß╗Öc t├¼nh n├áy 1','https://storage.googleapis.com/musicstreaming/Cover/Album/1/6.jpg','2024-10-27 00:58:08',NULL,NULL),(7,1,'DSADsa','https://storage.googleapis.com/musicstreaming/Cover/Album/1/7.jpg','2024-10-27 01:09:45',NULL,NULL),(8,1,'DSADsa','https://storage.googleapis.com/musicstreaming/Cover/Album/1/8.jpg','2024-10-27 01:14:56',NULL,NULL),(9,1,'DSADsa','https://storage.googleapis.com/musicstreaming/Cover/Album/1/9.jpg','2024-10-27 01:16:18',NULL,NULL),(10,1,'DSADsa','https://storage.googleapis.com/musicstreaming/Cover/Album/1/10.jpg','2024-10-27 01:17:50',NULL,NULL),(11,2,'DSADsa','https://storage.googleapis.com/musicstreaming/Cover/Album/2/11.jpg','2024-10-27 01:18:21',NULL,NULL),(12,2,'DSADsa','https://storage.googleapis.com/musicstreaming/Cover/Album/2/12.jpg','2024-10-27 01:22:00',NULL,NULL),(13,2,'DSADsa','https://storage.googleapis.com/musicstreaming/Cover/Album/2/13.jpg','2024-10-27 01:22:24',NULL,NULL),(14,2,'DSADsa','https://storage.googleapis.com/musicstreaming/Cover/Album/2/14.jpg','2024-10-27 01:24:04',NULL,NULL),(15,2,'DSADsa','https://storage.googleapis.com/musicstreaming/Cover/Album/2/15.jpg','2024-10-27 01:24:08',NULL,NULL),(16,2,'DSADsa','https://storage.googleapis.com/musicstreaming/Cover/Album/2/16.jpg','2024-10-27 01:24:30',NULL,NULL),(17,2,'DSADsa','https://storage.googleapis.com/musicstreaming/Cover/Album/2/17.jpg','2024-10-27 01:25:18',NULL,NULL),(18,2,'DSADsa','https://storage.googleapis.com/musicstreaming/Cover/Album/2/18.jpg','2024-10-27 01:26:39',NULL,NULL),(19,2,'DSADsa','https://storage.googleapis.com/musicstreaming/Cover/Album/2/19.jpg','2024-10-27 01:27:52',NULL,NULL),(20,2,'DSADsa','https://storage.googleapis.com/musicstreaming/Cover/Album/2/20.jpg','2024-10-27 01:29:17',NULL,NULL);
/*!40000 ALTER TABLE `albums` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `albumsong`
--

DROP TABLE IF EXISTS `albumsong`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `albumsong` (
  `asid` bigint NOT NULL AUTO_INCREMENT,
  `albumID` int NOT NULL,
  `songID` int NOT NULL,
  PRIMARY KEY (`asid`),
  KEY `albumID_idx` (`albumID`),
  KEY `songID_idx` (`songID`),
  CONSTRAINT `albumID` FOREIGN KEY (`albumID`) REFERENCES `albums` (`id`),
  CONSTRAINT `songID` FOREIGN KEY (`songID`) REFERENCES `songs` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `albumsong`
--

LOCK TABLES `albumsong` WRITE;
/*!40000 ALTER TABLE `albumsong` DISABLE KEYS */;
/*!40000 ALTER TABLE `albumsong` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `artists`
--

DROP TABLE IF EXISTS `artists`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `artists` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `name` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL,
  `image` varchar(255) DEFAULT NULL,
  `created_at` datetime NOT NULL,
  `updated_at` datetime NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `artists`
--

LOCK TABLES `artists` WRITE;
/*!40000 ALTER TABLE `artists` DISABLE KEYS */;
INSERT INTO `artists` VALUES (1,'Quang L├¬',NULL,'2021-03-03 00:00:00','2021-03-03 00:00:00'),(2,'Hiß╗üu Quang Hß╗æ',NULL,'2021-03-03 00:00:00','2021-03-03 00:00:00');
/*!40000 ALTER TABLE `artists` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `categories`
--

DROP TABLE IF EXISTS `categories`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `categories` (
  `cid` int NOT NULL,
  `category_name` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL,
  PRIMARY KEY (`cid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `categories`
--

LOCK TABLES `categories` WRITE;
/*!40000 ALTER TABLE `categories` DISABLE KEYS */;
INSERT INTO `categories` VALUES (1,'Pop'),(2,'Ballad'),(3,'Hiphop'),(4,'Nhß║íc ─æß╗Å');
/*!40000 ALTER TABLE `categories` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `comment`
--

DROP TABLE IF EXISTS `comment`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `comment` (
  `id` bigint NOT NULL,
  `songID` int NOT NULL,
  `userID` int NOT NULL,
  `content` varchar(1000) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL,
  `createdAt` datetime DEFAULT NULL,
  ` updatedAt` datetime DEFAULT NULL,
  `parentCommentID` int DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='	';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `comment`
--

LOCK TABLES `comment` WRITE;
/*!40000 ALTER TABLE `comment` DISABLE KEYS */;
/*!40000 ALTER TABLE `comment` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `comments`
--

DROP TABLE IF EXISTS `comments`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `comments` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `created_at` datetime(6) DEFAULT NULL,
  `updated_at` datetime(6) DEFAULT NULL,
  `content` text NOT NULL,
  `parent_commentid` int DEFAULT NULL,
  `songid` bigint NOT NULL,
  `userid` bigint NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `comments`
--

LOCK TABLES `comments` WRITE;
/*!40000 ALTER TABLE `comments` DISABLE KEYS */;
/*!40000 ALTER TABLE `comments` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `interactions`
--

DROP TABLE IF EXISTS `interactions`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `interactions` (
  `id` bigint NOT NULL,
  `user_id` bigint NOT NULL,
  `song_id` bigint NOT NULL,
  `liked` tinyint(1) NOT NULL,
  `play_count` int NOT NULL,
  `created_at` datetime NOT NULL,
  `updated_at` datetime NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `interactions`
--

LOCK TABLES `interactions` WRITE;
/*!40000 ALTER TABLE `interactions` DISABLE KEYS */;
/*!40000 ALTER TABLE `interactions` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `password_resets`
--

DROP TABLE IF EXISTS `password_resets`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `password_resets` (
  `email` varchar(255) NOT NULL,
  `token` varchar(255) NOT NULL,
  `created_at` datetime NOT NULL,
  KEY `password_resets_email_index` (`email`),
  KEY `password_resets_token_index` (`token`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `password_resets`
--

LOCK TABLES `password_resets` WRITE;
/*!40000 ALTER TABLE `password_resets` DISABLE KEYS */;
/*!40000 ALTER TABLE `password_resets` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `play_count`
--

DROP TABLE IF EXISTS `play_count`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `play_count` (
  `player_count` bigint NOT NULL DEFAULT '0',
  `songID` int NOT NULL,
  `play_count_id` int NOT NULL,
  `song_id` bigint DEFAULT NULL,
  PRIMARY KEY (`songID`,`play_count_id`),
  CONSTRAINT `playcount_songID` FOREIGN KEY (`songID`) REFERENCES `songs` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `play_count`
--

LOCK TABLES `play_count` WRITE;
/*!40000 ALTER TABLE `play_count` DISABLE KEYS */;
/*!40000 ALTER TABLE `play_count` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `playlist_folders`
--

DROP TABLE IF EXISTS `playlist_folders`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `playlist_folders` (
  `id` varchar(36) NOT NULL,
  `name` varchar(255) NOT NULL,
  `user_id` bigint NOT NULL,
  `created_at` datetime NOT NULL,
  `updated_at` datetime NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `playlist_folders`
--

LOCK TABLES `playlist_folders` WRITE;
/*!40000 ALTER TABLE `playlist_folders` DISABLE KEYS */;
/*!40000 ALTER TABLE `playlist_folders` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `playlist_song`
--

DROP TABLE IF EXISTS `playlist_song`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `playlist_song` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `playlist_id` int NOT NULL,
  `song_id` bigint NOT NULL,
  PRIMARY KEY (`id`),
  KEY `playlist_song_playlist_id_foreign` (`playlist_id`),
  KEY `playlist_song_song_id_foreign` (`song_id`),
  CONSTRAINT `playlist_song_playlist_id_foreign` FOREIGN KEY (`playlist_id`) REFERENCES `playlists` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `playlist_song`
--

LOCK TABLES `playlist_song` WRITE;
/*!40000 ALTER TABLE `playlist_song` DISABLE KEYS */;
/*!40000 ALTER TABLE `playlist_song` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `playlists`
--

DROP TABLE IF EXISTS `playlists`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `playlists` (
  `id` int NOT NULL,
  `user_id` bigint NOT NULL,
  `name` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL,
  `rules` text,
  `created_at` datetime NOT NULL,
  `updated_at` datetime NOT NULL,
  `folder_id` varchar(36) NOT NULL,
  `status` int NOT NULL,
  `share_token` varchar(36) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `playlists_folder_id_foreign` (`folder_id`),
  CONSTRAINT `playlists_folder_id_foreign` FOREIGN KEY (`folder_id`) REFERENCES `playlist_folders` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `playlists`
--

LOCK TABLES `playlists` WRITE;
/*!40000 ALTER TABLE `playlists` DISABLE KEYS */;
/*!40000 ALTER TABLE `playlists` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `roles`
--

DROP TABLE IF EXISTS `roles`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `roles` (
  `id` bigint unsigned NOT NULL AUTO_INCREMENT,
  `role_name` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `roles`
--

LOCK TABLES `roles` WRITE;
/*!40000 ALTER TABLE `roles` DISABLE KEYS */;
INSERT INTO `roles` VALUES (1,'ADMIN'),(2,'USER'),(3,'PREMIUM_USER'),(4,'GUEST');
/*!40000 ALTER TABLE `roles` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `settings`
--

DROP TABLE IF EXISTS `settings`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `settings` (
  `key` varchar(255) NOT NULL,
  `value` text NOT NULL,
  PRIMARY KEY (`key`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `settings`
--

LOCK TABLES `settings` WRITE;
/*!40000 ALTER TABLE `settings` DISABLE KEYS */;
/*!40000 ALTER TABLE `settings` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `social_accounts`
--

DROP TABLE IF EXISTS `social_accounts`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `social_accounts` (
  `id` bigint unsigned NOT NULL AUTO_INCREMENT,
  `provider` varchar(1000) NOT NULL,
  `provider_id` varchar(1000) NOT NULL,
  `email` varchar(1000) NOT NULL,
  `name` varchar(1000) NOT NULL,
  `user_id` bigint NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `social_accounts`
--

LOCK TABLES `social_accounts` WRITE;
/*!40000 ALTER TABLE `social_accounts` DISABLE KEYS */;
/*!40000 ALTER TABLE `social_accounts` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `song_category`
--

DROP TABLE IF EXISTS `song_category`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `song_category` (
  `id_song` int NOT NULL,
  `id_category` int NOT NULL,
  PRIMARY KEY (`id_song`,`id_category`),
  KEY `category_id_idx` (`id_category`),
  CONSTRAINT `category_id` FOREIGN KEY (`id_category`) REFERENCES `categories` (`cid`),
  CONSTRAINT `song_id` FOREIGN KEY (`id_song`) REFERENCES `songs` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='	';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `song_category`
--

LOCK TABLES `song_category` WRITE;
/*!40000 ALTER TABLE `song_category` DISABLE KEYS */;
INSERT INTO `song_category` VALUES (1,1),(1,2);
/*!40000 ALTER TABLE `song_category` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `songs`
--

DROP TABLE IF EXISTS `songs`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `songs` (
  `id` int NOT NULL AUTO_INCREMENT,
  `album_id` int DEFAULT NULL,
  `artist_id` bigint NOT NULL,
  `title` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL,
  `length` double DEFAULT NULL,
  `track` int DEFAULT NULL,
  `disc` int DEFAULT NULL,
  `lyrics` text,
  `path` text,
  `mtime` int DEFAULT NULL,
  `created_at` datetime DEFAULT NULL,
  `updated_at` datetime DEFAULT NULL,
  `audio_url` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `songs_track_disc_index` (`track`,`disc`),
  KEY `songs_title_index` (`title`),
  KEY `FKdjq2ujqovw5rc14q60f8p6b6e` (`artist_id`),
  CONSTRAINT `FKdjq2ujqovw5rc14q60f8p6b6e` FOREIGN KEY (`artist_id`) REFERENCES `artists` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `songs`
--

LOCK TABLES `songs` WRITE;
/*!40000 ALTER TABLE `songs` DISABLE KEYS */;
INSERT INTO `songs` VALUES (1,NULL,1,'1',NULL,NULL,NULL,NULL,NULL,NULL,'2024-10-20 16:01:09','2024-10-20 16:01:09','gs://musicstreaming/y2mate.com - Dß║¬U C├ô Lß╗ûI Lß║ªM  LI├èN MINH PH├üT T├ÇI  C├öNG DIß╗äN 2 ANH TRAI V╞»ß╗óT NG├ÇN CH├öNG GAI 2024.mp3'),(2,NULL,1,'11111',NULL,NULL,NULL,NULL,NULL,NULL,'2024-10-20 16:01:58','2024-10-20 16:01:58','gs://musicstreaming/y2mate.com - Mß╗╕ T├éM  V├î EM Tß║ñT Cß║ó  OFFICIAL MV.mp3'),(3,NULL,1,'1',NULL,NULL,NULL,NULL,NULL,NULL,'2024-10-25 23:00:58',NULL,'gs://musicstreaming/y2mate.com - Mß╗╕ T├éM  V├î EM Tß║ñT Cß║ó  OFFICIAL MV.mp3'),(4,NULL,1,'1',NULL,NULL,NULL,NULL,NULL,NULL,'2024-10-25 23:05:32',NULL,'https://storage.googleapis.com/musicstreaming/y2mate.com%20-%20Mß╗╕%20T├éM%20%20V├î%20EM%20Tß║ñT%20Cß║ó%20%20OFFICIAL%20MV.mp3');
/*!40000 ALTER TABLE `songs` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tokens`
--

DROP TABLE IF EXISTS `tokens`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tokens` (
  `id` bigint unsigned NOT NULL AUTO_INCREMENT,
  `token` varchar(1000) DEFAULT NULL,
  `refresh_token` varchar(1000) DEFAULT NULL,
  `expire_time` datetime DEFAULT NULL,
  `revoked` bit(1) NOT NULL,
  `token_type` varchar(1000) DEFAULT NULL,
  `user_id` bigint NOT NULL,
  `is_mobile` bit(1) NOT NULL,
  `refresh_expiration_time` datetime DEFAULT NULL,
  `device_id` varchar(255) DEFAULT NULL,
  `user_agent` varchar(255) DEFAULT NULL,
  `last_used` datetime DEFAULT NULL,
  `expired` tinyint(1) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tokens`
--

LOCK TABLES `tokens` WRITE;
/*!40000 ALTER TABLE `tokens` DISABLE KEYS */;
INSERT INTO `tokens` VALUES (7,'eyJhbGciOiJIUzI1NiJ9.eyJwaG9uZU51bWJlciI6IjA5ODc2NTQzMjEiLCJpYXQiOjE3Mjg2NjgwODUsImV4cCI6MTcyODY3MTY4NX0.J50-m-iMFbTn7G46Z1lAhi3l7h3DZ_RJ4MwpklBJ4Ig','4e5cffca-9944-4377-8a1c-41b3a46c0adf','2024-10-11 18:35:06',_binary '\0','Bearer',12,_binary '\0','2024-10-11 19:01:30',NULL,NULL,NULL,0);
/*!40000 ALTER TABLE `tokens` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_ads`
--

DROP TABLE IF EXISTS `user_ads`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user_ads` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `date_played` date NOT NULL,
  `time_played` bigint NOT NULL,
  `ads_id` bigint NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_ads`
--

LOCK TABLES `user_ads` WRITE;
/*!40000 ALTER TABLE `user_ads` DISABLE KEYS */;
/*!40000 ALTER TABLE `user_ads` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `userads`
--

DROP TABLE IF EXISTS `userads`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `userads` (
  `user_id` bigint unsigned NOT NULL AUTO_INCREMENT,
  `Ads_id` bigint NOT NULL,
  `DatePlayed` date NOT NULL,
  `TimePlayed` bigint NOT NULL,
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `userads`
--

LOCK TABLES `userads` WRITE;
/*!40000 ALTER TABLE `userads` DISABLE KEYS */;
/*!40000 ALTER TABLE `userads` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `users` (
  `id` bigint unsigned NOT NULL AUTO_INCREMENT,
  `fullname` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL,
  `phone_number` varchar(255) NOT NULL,
  `address` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL,
  `password` varchar(1000) NOT NULL,
  `created_at` datetime NOT NULL,
  `updated_at` datetime DEFAULT NULL,
  `is_active` tinyint(1) NOT NULL,
  `date_of_birth` date NOT NULL,
  `facebook_account_id` bigint DEFAULT NULL,
  `google_account_id` bigint DEFAULT NULL,
  `role_id` bigint NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES (11,'Nguyen Van A','0123456789','123 Main St','$2y$10$/yPvNsqcW1Et1ByzpAxGmOrr0IRqrYa3TlRKGUUzzkbR.Kp/0Ie3y','2024-10-10 14:55:10','2024-10-10 14:55:10',1,'1990-01-01',1001,2001,1),(12,'Le Thi B','0987654321','456 Elm St','$2y$10$/yPvNsqcW1Et1ByzpAxGmOrr0IRqrYa3TlRKGUUzzkbR.Kp/0Ie3y','2024-10-10 14:55:10','2024-10-10 14:55:10',1,'1992-05-15',1002,2002,2),(13,'Tran Van C','0912345678','789 Pine St','$2y$10$/yPvNsqcW1Et1ByzpAxGmOrr0IRqrYa3TlRKGUUzzkbR.Kp/0Ie3y','2024-10-10 14:55:10','2024-10-10 14:55:10',1,'1985-08-30',1003,2003,3),(14,'Pham Thi D','0976543210','101 Maple St','$2y$10$/yPvNsqcW1Et1ByzpAxGmOrr0IRqrYa3TlRKGUUzzkbR.Kp/0Ie3y','2024-10-10 14:55:10','2024-10-10 14:55:10',1,'1995-12-10',1004,2004,1),(15,'Do Van E','0901234567','202 Oak St','$2y$10$/yPvNsqcW1Et1ByzpAxGmOrr0IRqrYa3TlRKGUUzzkbR.Kp/0Ie3y','2024-10-10 14:55:10','2024-10-10 14:55:10',1,'1988-03-20',1005,2005,2),(16,'dsadasdas','string','string','$2a$10$530k9XKf75ToYxcMOKJhU.X/g1bAKOGjEaiXq5mYS2qE.f0z7Yjoi','2024-10-14 09:28:44','2024-10-14 09:28:44',1,'2024-10-14',0,0,2);
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

-- Dump completed on 2024-11-02 16:13:40
