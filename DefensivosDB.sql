-- MySQL dump 10.13  Distrib 8.0.18, for Win64 (x86_64)
--
-- Host: localhost    Database: defensivosdb
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
-- Table structure for table `clientes`
--

DROP TABLE IF EXISTS `clientes`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `clientes` (
  `Cpf` varchar(15) NOT NULL,
  `Nome` varchar(50) NOT NULL,
  `Rg` varchar(15) NOT NULL,
  `Email` varchar(50) NOT NULL,
  `Telefone` varchar(15) NOT NULL,
  `Celular` varchar(20) NOT NULL,
  `NascimentoData` varchar(10) NOT NULL,
  `Sexo` varchar(2) NOT NULL,
  `cod_endereço` int(11) NOT NULL,
  PRIMARY KEY (`Cpf`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `clientes`
--

LOCK TABLES `clientes` WRITE;
/*!40000 ALTER TABLE `clientes` DISABLE KEYS */;
INSERT INTO `clientes` VALUES ('111.111.111-11','Paulo Henrique Nascimento de Souza','1718202','souza.phn.agro@gmail.com','(67)  3476-1121','(67) 9 9632-2470','04/11/1993','m',17),('222.222.222-22','Felipe Silva Nascimento','222222','felipenascimento@gmail.com','(67)  3321-2126','(67) 9 9632-2470','04/11/1993','m',18),('333.333.333-33','Angelica de Cassia','333333','angelica@gmail.com','(67)  3321-2126','(67) 9 9523-6986','01/05/2000','f',19),('444.444.444-44','Nivaldo Costa Carvalho','444444','nivaldo_costa@gmail.com','(67)  9656-2324','(67) 9 4466-6389','04/02/1996','m',20),('555.555.555-55','Mirian Mello de Oliveira','55555','miriam@gmail.com','(67)  3465-9326','(67) 9 6352-5689','04/02/1996','f',21),('666.666.666-66','Solange Mello Aparecida','666666','soh@hotmaill.com','(67)  3465-2693','(67) 9 9523-6689','12/05/1998','f',22),('777.777.777-77','Antonio Dionisio','77777','Antonio@hotmaill.com','(67)  3465-2693','(67) 9 9523-6689','20/06/1992','m',23),('888.888.888-88','Luciele Lopes de Camargo','888888','Luci@hotmaill.com','(67)  3465-2693','(67) 9 9523-6689','10/01/2019','f',24);
/*!40000 ALTER TABLE `clientes` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `endereço`
--

DROP TABLE IF EXISTS `endereço`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `endereço` (
  `id_endereço` int(11) NOT NULL AUTO_INCREMENT,
  `Rua` varchar(50) NOT NULL,
  `Numero` varchar(10) NOT NULL,
  `Complemento` varchar(50) NOT NULL,
  `Bairro` varchar(50) NOT NULL,
  `Cidade` varchar(50) NOT NULL,
  `Cep` varchar(20) NOT NULL,
  `Estado` varchar(10) NOT NULL,
  PRIMARY KEY (`id_endereço`)
) ENGINE=InnoDB AUTO_INCREMENT=27 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `endereço`
--

LOCK TABLES `endereço` WRITE;
/*!40000 ALTER TABLE `endereço` DISABLE KEYS */;
INSERT INTO `endereço` VALUES (10,'Rua Pastor Braff','150','Mercado Franscisco','Cohafaba III Plano','Dourados','79826 - 210','MS'),(12,'Rua Pastor Braff','150','Parque Industrial','Cohafaba III Plano','Dourados','79826 - 210','MS'),(13,'Rua Pastor Braff','140','Antiga Rua tramandai','Cohafaba III Plano','Dourados','79826 - 210','MS'),(14,'Rua Seiti Fukui','245','Antiga Analia Tenorio','Coohab','Dourados','79840 - 360','MS'),(15,'Rua Pastor Braff','150','Parque Industrial','Cohafaba III Plano','Dourados','79826 - 210','MS'),(16,'dedede','222','2222','fff','jesus','79450 - 989','AC'),(17,'Rua Pastor Braff','3685','Antiga Rua Tramandai','Jardim America','Dourados - MS','79826 - 210','MS'),(18,'Rua Oliveira Marques 3685','3685','Proximo ao Supermecado Extra','Jardim America','Dourados - MS','79830 - 040','MS'),(19,'Industrial','150','Br 163 km 18','Centro','Itaquirai ','79963 - 000','MS'),(20,'Rua Apolo Mello','15366','Antiga Rua Sao Jose','Centro','Caarapo','79826 - 365','MS'),(21,'Rua Major Capile','15366','Proximo ao Shopping Center','Jardim America','Douirados','79986 - 532','MS'),(22,'Rua Oliveira Marques','3025','Proximo ao Supermercado Extra','Jardim Paulista','Dourados','79826 - 210','MS'),(23,'Rua Melvin Jones','3659','Proximo ao Supermercado Extra','IV Plano','Dourados','79826 - 210','MS'),(24,'Rua Oliveira Marques','3025','Proximo ao Supermercado Extra','Jardim Paulista','Dourados','79826 - 210','MS');
/*!40000 ALTER TABLE `endereço` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `funcionario`
--

DROP TABLE IF EXISTS `funcionario`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `funcionario` (
  `login` varchar(30) NOT NULL,
  `nome` varchar(100) NOT NULL,
  `email` varchar(200) NOT NULL,
  `senha` varchar(50) NOT NULL,
  `cargo` varchar(100) NOT NULL,
  `nivel_acesso` varchar(50) NOT NULL,
  PRIMARY KEY (`login`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `funcionario`
--

LOCK TABLES `funcionario` WRITE;
/*!40000 ALTER TABLE `funcionario` DISABLE KEYS */;
INSERT INTO `funcionario` VALUES ('admin','Paulo Souza','souza.phn.agro@gmail.com','admin','Administrador','Administrador'),('Fernando','Fernando','fernmendes@gmail.com','13232','Administrador','Administrador'),('Joseylton','Joseylton','Joseylton@gmail.com','admin','vendedor','Caixa'),('Pedro','Pedro','joseamilton@gmail.com','123','Gerente','Caixa'),('phnsouza','Paulo','sds','asdas','Caixa','Caixa');
/*!40000 ALTER TABLE `funcionario` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `funcionarios`
--

DROP TABLE IF EXISTS `funcionarios`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `funcionarios` (
  `login` varchar(30) NOT NULL,
  `nome` varchar(100) NOT NULL,
  `email` varchar(200) NOT NULL,
  `senha` varchar(50) NOT NULL,
  `cargo` varchar(100) NOT NULL,
  `nivel_acesso` varchar(50) NOT NULL,
  PRIMARY KEY (`login`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `funcionarios`
--

LOCK TABLES `funcionarios` WRITE;
/*!40000 ALTER TABLE `funcionarios` DISABLE KEYS */;
INSERT INTO `funcionarios` VALUES ('1','paulo','souza@gmail.com','123','Vendas','3'),('2','joao','paulo','123','vendas','2'),('pauloHSouza','Paulo Souza','souza.phn.agro@gmail.com','1718202','Vendedor','Item 1');
/*!40000 ALTER TABLE `funcionarios` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `herbicida`
--

DROP TABLE IF EXISTS `herbicida`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `herbicida` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nome` varchar(50) NOT NULL,
  `codigo_barras` varchar(30) NOT NULL,
  `empresa` varchar(50) NOT NULL,
  `valor_unitario` double NOT NULL,
  `quantidade_estoque` int(11) NOT NULL,
  `formulacao` int(11) NOT NULL,
  `precisa_registro` tinyint(1) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=24 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `herbicida`
--

LOCK TABLES `herbicida` WRITE;
/*!40000 ALTER TABLE `herbicida` DISABLE KEYS */;
INSERT INTO `herbicida` VALUES (16,'Glifosato','78944313','Bayer',150.99,95,1,0),(17,'Folhagil','79865623','BASF',25.48,91,1,1),(18,'Maltrasil','7987989','Syngenta',32.45,95,0,0),(19,'Carpamil','265989','DuPont',18.68,82,2,1),(20,'Mesoxidil','7989656','Syngenta',25.36,197,0,1),(21,'Sulftatax_magno','7989','Ihara',75.96,194,2,0),(22,'Oleo Metilado de Alcaria','7986635','BUNG',69.54,197,1,1),(23,'Po de ferro','65689','BASF',36.54,195,0,1);
/*!40000 ALTER TABLE `herbicida` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tab_itemvenda`
--

DROP TABLE IF EXISTS `tab_itemvenda`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tab_itemvenda` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `venda_id` int(11) NOT NULL,
  `herbicida_id` int(11) NOT NULL,
  `valor_unitario` decimal(10,2) NOT NULL,
  `quantidade` int(11) NOT NULL,
  `subtotal` decimal(10,2) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=87 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tab_itemvenda`
--

LOCK TABLES `tab_itemvenda` WRITE;
/*!40000 ALTER TABLE `tab_itemvenda` DISABLE KEYS */;
INSERT INTO `tab_itemvenda` VALUES (45,24,16,150.99,1,150.99),(46,24,23,36.54,1,36.54),(47,25,19,18.68,3,56.04),(48,25,20,25.36,1,25.36),(49,26,16,150.99,1,150.99),(50,27,22,69.54,2,139.08),(51,27,23,36.54,2,73.08),(52,28,17,25.48,3,76.44),(53,29,17,25.48,1,25.48),(54,29,20,25.36,1,25.36),(55,29,16,150.99,1,150.99),(56,30,19,18.68,1,18.68),(57,30,21,75.96,1,75.96),(58,31,17,25.48,1,25.48),(59,31,19,18.68,1,18.68),(60,31,21,75.96,1,75.96),(61,31,22,69.54,1,69.54),(62,32,19,18.68,1,18.68),(63,33,19,18.68,1,18.68),(64,33,17,25.48,1,25.48),(65,33,21,75.96,1,75.96),(66,34,21,75.96,1,75.96),(67,34,18,32.45,1,32.45),(68,34,23,36.54,1,36.54),(69,35,21,75.96,1,75.96),(70,35,18,32.45,1,32.45),(71,35,23,36.54,1,36.54),(72,36,16,150.99,1,150.99),(73,36,19,18.68,1,18.68),(74,36,20,25.36,1,25.36),(75,37,16,150.99,1,150.99),(76,37,19,18.68,1,18.68),(77,38,17,25.48,1,25.48),(78,38,18,32.45,1,32.45),(79,38,18,32.45,1,32.45),(80,39,17,25.48,1,25.48),(81,39,18,32.45,1,32.45),(82,39,21,75.96,1,75.96),(83,40,17,25.48,1,25.48),(84,40,19,18.68,2,37.36),(85,40,19,18.68,3,56.04),(86,40,19,18.68,4,74.72);
/*!40000 ALTER TABLE `tab_itemvenda` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tab_sistema`
--

DROP TABLE IF EXISTS `tab_sistema`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tab_sistema` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `empresa` varchar(20) NOT NULL,
  `versao` varchar(20) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tab_sistema`
--

LOCK TABLES `tab_sistema` WRITE;
/*!40000 ALTER TABLE `tab_sistema` DISABLE KEYS */;
/*!40000 ALTER TABLE `tab_sistema` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tab_vendas`
--

DROP TABLE IF EXISTS `tab_vendas`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tab_vendas` (
  `id_venda` int(11) NOT NULL AUTO_INCREMENT,
  `cliente_id` varchar(30) NOT NULL,
  `data_venda` date NOT NULL,
  `forma_pagamento` varchar(30) NOT NULL,
  `valor_produtos` decimal(10,2) NOT NULL,
  `valor_desconto` decimal(10,2) NOT NULL,
  `valor_total` decimal(10,2) NOT NULL,
  `precisa_nfe` int(11) NOT NULL,
  PRIMARY KEY (`id_venda`)
) ENGINE=InnoDB AUTO_INCREMENT=41 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tab_vendas`
--

LOCK TABLES `tab_vendas` WRITE;
/*!40000 ALTER TABLE `tab_vendas` DISABLE KEYS */;
INSERT INTO `tab_vendas` VALUES (24,'111.111.111-11','2019-11-13','1',187.53,0.00,187.53,1),(25,'222.222.222-22','2019-11-13','2',81.40,0.00,81.40,1),(26,'111.111.111-11','2019-11-13','2',150.99,0.00,150.99,1),(27,'666.666.666-66','2019-11-13','2',212.16,0.00,212.16,1),(28,'888.888.888-88','2019-11-13','1',76.44,0.00,76.44,0),(29,'444.444.444-44','2019-11-13','2',201.83,0.00,201.83,1),(30,'333.333.333-33','2019-11-13','2',94.64,0.00,94.64,1),(31,'222.222.222-22','2019-11-13','1',189.66,0.00,189.66,0),(32,'111.111.111-11','2019-11-13','2',18.68,0.00,18.68,1),(33,'555.555.555-55','2019-11-13','2',120.12,0.00,120.12,1),(34,'555.555.555-55','2019-11-13','2',144.95,0.00,144.95,1),(35,'222.222.222-22','2019-11-13','1',144.95,0.00,144.95,1),(36,'111.111.111-11','2019-11-13','2',195.03,0.00,195.03,1),(37,'666.666.666-66','2019-11-13','2',169.67,0.00,169.67,0),(38,'333.333.333-33','2019-11-13','1',90.38,0.00,90.38,1),(39,'111.111.111-11','2019-11-14','1',133.89,0.00,133.89,1),(40,'555.555.555-55','2020-02-02','1',193.60,0.00,193.60,1);
/*!40000 ALTER TABLE `tab_vendas` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `usuarios`
--

DROP TABLE IF EXISTS `usuarios`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `usuarios` (
  `Login` varchar(30) NOT NULL,
  `Nome` varchar(100) NOT NULL,
  `Email` varchar(200) NOT NULL,
  `Senha` varchar(50) NOT NULL,
  `Cargo` varchar(100) NOT NULL,
  `Nivel_Acesso` varchar(50) NOT NULL,
  PRIMARY KEY (`Login`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `usuarios`
--

LOCK TABLES `usuarios` WRITE;
/*!40000 ALTER TABLE `usuarios` DISABLE KEYS */;
/*!40000 ALTER TABLE `usuarios` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `versao_sistema`
--

DROP TABLE IF EXISTS `versao_sistema`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `versao_sistema` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `empresa` varchar(20) NOT NULL,
  `versao` varchar(20) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `versao_sistema`
--

LOCK TABLES `versao_sistema` WRITE;
/*!40000 ALTER TABLE `versao_sistema` DISABLE KEYS */;
/*!40000 ALTER TABLE `versao_sistema` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2020-04-03 16:17:49
