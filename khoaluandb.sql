-- MySQL dump 10.13  Distrib 8.0.41, for Win64 (x86_64)
--
-- Host: localhost    Database: khoaluandb
-- ------------------------------------------------------
-- Server version	9.2.0

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
-- Table structure for table `bangdiems`
--

DROP TABLE IF EXISTS `bangdiems`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `bangdiems` (
  `id` int NOT NULL AUTO_INCREMENT,
  `deTaiKhoaLuan_id` int DEFAULT NULL,
  `giangVienPhanBien_id` int DEFAULT NULL,
  `tieuChi` text,
  `diem` float DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `deTaiKhoaLuan_id` (`deTaiKhoaLuan_id`),
  KEY `giangVienPhanBien_id` (`giangVienPhanBien_id`),
  CONSTRAINT `bangdiems_ibfk_1` FOREIGN KEY (`deTaiKhoaLuan_id`) REFERENCES `detaikhoaluans` (`id`),
  CONSTRAINT `bangdiems_ibfk_2` FOREIGN KEY (`giangVienPhanBien_id`) REFERENCES `nguoidungs` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `bangdiems`
--

LOCK TABLES `bangdiems` WRITE;
/*!40000 ALTER TABLE `bangdiems` DISABLE KEYS */;
/*!40000 ALTER TABLE `bangdiems` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `detaikhoaluan_giangvienhuongdan`
--

DROP TABLE IF EXISTS `detaikhoaluan_giangvienhuongdan`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `detaikhoaluan_giangvienhuongdan` (
  `id` int NOT NULL AUTO_INCREMENT,
  `deTaiKhoaLuan_id` int DEFAULT NULL,
  `giangVienHuongDan_id` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `deTaiKhoaLuan_id` (`deTaiKhoaLuan_id`),
  KEY `giangVienHuongDan_id` (`giangVienHuongDan_id`),
  CONSTRAINT `detaikhoaluan_giangvienhuongdan_ibfk_1` FOREIGN KEY (`deTaiKhoaLuan_id`) REFERENCES `detaikhoaluans` (`id`),
  CONSTRAINT `detaikhoaluan_giangvienhuongdan_ibfk_2` FOREIGN KEY (`giangVienHuongDan_id`) REFERENCES `nguoidungs` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `detaikhoaluan_giangvienhuongdan`
--

LOCK TABLES `detaikhoaluan_giangvienhuongdan` WRITE;
/*!40000 ALTER TABLE `detaikhoaluan_giangvienhuongdan` DISABLE KEYS */;
/*!40000 ALTER TABLE `detaikhoaluan_giangvienhuongdan` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `detaikhoaluan_hoidong`
--

DROP TABLE IF EXISTS `detaikhoaluan_hoidong`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `detaikhoaluan_hoidong` (
  `id` int NOT NULL AUTO_INCREMENT,
  `deTaiKhoaLuan_id` int DEFAULT NULL,
  `hoiDong_id` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `deTaiKhoaLuan_id` (`deTaiKhoaLuan_id`),
  KEY `hoiDong_id` (`hoiDong_id`),
  CONSTRAINT `detaikhoaluan_hoidong_ibfk_1` FOREIGN KEY (`deTaiKhoaLuan_id`) REFERENCES `detaikhoaluans` (`id`),
  CONSTRAINT `detaikhoaluan_hoidong_ibfk_2` FOREIGN KEY (`hoiDong_id`) REFERENCES `hoidongs` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `detaikhoaluan_hoidong`
--

LOCK TABLES `detaikhoaluan_hoidong` WRITE;
/*!40000 ALTER TABLE `detaikhoaluan_hoidong` DISABLE KEYS */;
/*!40000 ALTER TABLE `detaikhoaluan_hoidong` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `detaikhoaluan_sinhvien`
--

DROP TABLE IF EXISTS `detaikhoaluan_sinhvien`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `detaikhoaluan_sinhvien` (
  `id` int NOT NULL AUTO_INCREMENT,
  `deTaiKhoaLuan_id` int DEFAULT NULL,
  `sinhVien_id` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `deTaiKhoaLuan_id` (`deTaiKhoaLuan_id`),
  KEY `sinhVien_id` (`sinhVien_id`),
  CONSTRAINT `detaikhoaluan_sinhvien_ibfk_1` FOREIGN KEY (`deTaiKhoaLuan_id`) REFERENCES `detaikhoaluans` (`id`),
  CONSTRAINT `detaikhoaluan_sinhvien_ibfk_2` FOREIGN KEY (`sinhVien_id`) REFERENCES `nguoidungs` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `detaikhoaluan_sinhvien`
--

LOCK TABLES `detaikhoaluan_sinhvien` WRITE;
/*!40000 ALTER TABLE `detaikhoaluan_sinhvien` DISABLE KEYS */;
/*!40000 ALTER TABLE `detaikhoaluan_sinhvien` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `detaikhoaluans`
--

DROP TABLE IF EXISTS `detaikhoaluans`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `detaikhoaluans` (
  `id` int NOT NULL AUTO_INCREMENT,
  `title` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `detaikhoaluans`
--

LOCK TABLES `detaikhoaluans` WRITE;
/*!40000 ALTER TABLE `detaikhoaluans` DISABLE KEYS */;
/*!40000 ALTER TABLE `detaikhoaluans` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `hoidongs`
--

DROP TABLE IF EXISTS `hoidongs`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `hoidongs` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(100) DEFAULT NULL,
  `created_by` int DEFAULT NULL,
  `status` enum('active','closed') DEFAULT 'active',
  PRIMARY KEY (`id`),
  KEY `created_by` (`created_by`),
  CONSTRAINT `hoidongs_ibfk_1` FOREIGN KEY (`created_by`) REFERENCES `nguoidungs` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `hoidongs`
--

LOCK TABLES `hoidongs` WRITE;
/*!40000 ALTER TABLE `hoidongs` DISABLE KEYS */;
/*!40000 ALTER TABLE `hoidongs` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `nguoidungs`
--

DROP TABLE IF EXISTS `nguoidungs`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `nguoidungs` (
  `id` int NOT NULL AUTO_INCREMENT,
  `username` varchar(50) NOT NULL,
  `password` varchar(255) NOT NULL,
  `role` varchar(50) DEFAULT NULL,
  `avatar` varchar(255) DEFAULT NULL,
  `email` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `username` (`username`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `nguoidungs`
--

LOCK TABLES `nguoidungs` WRITE;
/*!40000 ALTER TABLE `nguoidungs` DISABLE KEYS */;
INSERT INTO `nguoidungs` VALUES (1,'giaovu1','$2a$10$ZULNKuUc6DWG1EIfU4gU9.5KLFKQW5mJ5l7zENa3s2zfqexjJxJgK','ROLE_GIAOVU','avatar.png','giaovu1@example.com'),(2,'admin','$2a$10$5X9k5N1sTc1/CjVH5XJoje3QMYijH3ETpgkox00R0MdPaJPPrf7wO','ROLE_ADMIN','admin.png','admin1@example.com'),(3,'phong','$2a$10$h0FlkZiCoMnnEn37UpTCr.ExAdXMch68NupLUpJl3Pv0WYSUzb8Ui','ROLE_GIANGVIEN','https://res.cloudinary.com/dp4fipzce/image/upload/v1746929245/af3rt3tonvfgtrwil2d7.jpg','tqphong2004@gmail.com');
/*!40000 ALTER TABLE `nguoidungs` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `phanconggiangvienphanbiens`
--

DROP TABLE IF EXISTS `phanconggiangvienphanbiens`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `phanconggiangvienphanbiens` (
  `id` int NOT NULL AUTO_INCREMENT,
  `deTaiKhoaLuan_id` int DEFAULT NULL,
  `giangVienPhanBien_id` int DEFAULT NULL,
  `thongBao_sent` tinyint(1) DEFAULT '0',
  PRIMARY KEY (`id`),
  KEY `deTaiKhoaLuan_id` (`deTaiKhoaLuan_id`),
  KEY `giangVienPhanBien_id` (`giangVienPhanBien_id`),
  CONSTRAINT `phanconggiangvienphanbiens_ibfk_1` FOREIGN KEY (`deTaiKhoaLuan_id`) REFERENCES `detaikhoaluans` (`id`),
  CONSTRAINT `phanconggiangvienphanbiens_ibfk_2` FOREIGN KEY (`giangVienPhanBien_id`) REFERENCES `nguoidungs` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `phanconggiangvienphanbiens`
--

LOCK TABLES `phanconggiangvienphanbiens` WRITE;
/*!40000 ALTER TABLE `phanconggiangvienphanbiens` DISABLE KEYS */;
/*!40000 ALTER TABLE `phanconggiangvienphanbiens` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `thanhvienhoidong`
--

DROP TABLE IF EXISTS `thanhvienhoidong`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `thanhvienhoidong` (
  `id` int NOT NULL AUTO_INCREMENT,
  `hoiDong_id` int DEFAULT NULL,
  `user_id` int DEFAULT NULL,
  `role` enum('chu_tich','thu_ky','phan_bien','thanh_vien') DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `hoiDong_id` (`hoiDong_id`),
  KEY `user_id` (`user_id`),
  CONSTRAINT `thanhvienhoidong_ibfk_1` FOREIGN KEY (`hoiDong_id`) REFERENCES `hoidongs` (`id`),
  CONSTRAINT `thanhvienhoidong_ibfk_2` FOREIGN KEY (`user_id`) REFERENCES `nguoidungs` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `thanhvienhoidong`
--

LOCK TABLES `thanhvienhoidong` WRITE;
/*!40000 ALTER TABLE `thanhvienhoidong` DISABLE KEYS */;
/*!40000 ALTER TABLE `thanhvienhoidong` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `thongbaos`
--

DROP TABLE IF EXISTS `thongbaos`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `thongbaos` (
  `id` int NOT NULL AUTO_INCREMENT,
  `nguoiDung_id` int DEFAULT NULL,
  `tinNhan` text,
  `thoiGianGui` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `nguoiDung_id` (`nguoiDung_id`),
  CONSTRAINT `thongbaos_ibfk_1` FOREIGN KEY (`nguoiDung_id`) REFERENCES `nguoidungs` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `thongbaos`
--

LOCK TABLES `thongbaos` WRITE;
/*!40000 ALTER TABLE `thongbaos` DISABLE KEYS */;
/*!40000 ALTER TABLE `thongbaos` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2025-05-11  9:10:34
