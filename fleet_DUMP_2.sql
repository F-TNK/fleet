CREATE DATABASE  IF NOT EXISTS `fleet` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `fleet`;
-- MySQL dump 10.13  Distrib 8.0.46, for Win64 (x86_64)
--
-- Host: localhost    Database: fleet
-- ------------------------------------------------------
-- Server version	8.0.46

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
-- Table structure for table `equip`
--

DROP TABLE IF EXISTS `equip`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `equip` (
  `idequip` bigint NOT NULL AUTO_INCREMENT,
  `nome` varchar(100) NOT NULL,
  `modelo` varchar(100) NOT NULL,
  `data_aquisicao` varchar(10) NOT NULL,
  `horas_uso` double DEFAULT NULL,
  `vida_util` double NOT NULL,
  `nivel_combustivel` double NOT NULL,
  `status` varchar(20) NOT NULL,
  PRIMARY KEY (`idequip`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `equip`
--

LOCK TABLES `equip` WRITE;
/*!40000 ALTER TABLE `equip` DISABLE KEYS */;
INSERT INTO `equip` VALUES (1,'Compactador de Solo (Sapo)','Menegotti Rammer 75 - Gasolina','2022-01-15',485,2000,71,'Disponível'),(2,'Cortadora de Piso e Asfalto','Makita EK7651H - 4 Tempos','2021-05-10',1900,2000,15,'Em Manutenção'),(3,'Gerador de Energia Portátil','Branco B4000 - Gasolina','2023-02-20',100,3000,100,'Disponível'),(4,'Betoneira 400L','CSM 400L Motor Honda Gasolina','2020-08-05',1250,2500,50,'Em Uso'),(6,'Motobomba Autoescorvante','Toyama TWP80 - Gasolina','2022-10-22',1900,2500,40,'Disponível');
/*!40000 ALTER TABLE `equip` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `liberacao`
--

DROP TABLE IF EXISTS `liberacao`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `liberacao` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `data_hora_retirada` datetime NOT NULL,
  `data_hora_devolucao` datetime NOT NULL,
  `data_hora_retirada_real` datetime DEFAULT NULL,
  `data_hora_devolucao_real` datetime DEFAULT NULL,
  `horimetro_inicial` double DEFAULT NULL,
  `horimetro_final` double DEFAULT NULL,
  `combustivel_inicial` double DEFAULT NULL,
  `combustivel_final` double DEFAULT NULL,
  `local_uso` varchar(200) NOT NULL,
  `observacoes_retirada` varchar(600) DEFAULT NULL,
  `observacoes_devolucao` varchar(600) DEFAULT NULL,
  `alerta` tinyint(1) DEFAULT '0',
  `iduser` bigint NOT NULL,
  `idequip` bigint NOT NULL,
  PRIMARY KEY (`id`),
  KEY `iduser` (`iduser`),
  KEY `idequip` (`idequip`),
  CONSTRAINT `liberacao_ibfk_1` FOREIGN KEY (`iduser`) REFERENCES `users` (`iduser`),
  CONSTRAINT `liberacao_ibfk_2` FOREIGN KEY (`idequip`) REFERENCES `equip` (`idequip`)
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `liberacao`
--

LOCK TABLES `liberacao` WRITE;
/*!40000 ALTER TABLE `liberacao` DISABLE KEYS */;
INSERT INTO `liberacao` VALUES (1,'2026-07-01 08:00:00','2026-07-01 17:00:00','2026-07-01 07:55:00','2026-07-01 17:10:00',100,108.5,100,80,'Obra Centro - Lote 1','Equipamento limpo e revisado.','Devolvido sem avarias, funcionando 100%.',0,2,1),(2,'2026-07-02 09:00:00','2026-07-02 15:00:00','2026-07-02 09:10:00','2026-07-02 14:50:00',200,205,50,40,'Reforma Escola Municipal - Setor Norte',NULL,NULL,0,3,2),(3,'2026-07-03 07:30:00','2026-07-04 17:30:00','2026-07-03 07:35:00','2026-07-04 17:25:00',50,65.2,100,20,'Condomínio Vale Verde (Rua B)','Retirado com tanque cheio.','Tudo certo durante o uso.',0,4,3),(4,'2026-07-05 10:00:00','2026-07-05 16:00:00','2026-07-05 10:05:00','2026-07-05 15:55:00',1200,1206,40,30,'Construção da Praça (Centro)',NULL,NULL,0,5,4),(5,'2026-07-06 08:00:00','2026-07-08 18:00:00','2026-07-06 08:15:00','2026-07-08 17:50:00',1850,1870,80,20,'Drenagem Bairro Sul - Trecho 2','Bomba com leves riscos na carcaça.','Operação concluída com sucesso.',0,2,6),(6,'2026-07-10 07:00:00','2026-07-10 12:00:00','2026-07-10 07:00:00','2026-07-10 12:15:00',110,115,75,60,'Asfaltamento Av. Brasil',NULL,NULL,0,3,1),(7,'2026-07-12 13:00:00','2026-07-12 17:00:00','2026-07-12 12:50:00','2026-07-12 17:05:00',210,214,50,40,'Calçada do Shopping Novo','Acompanha disco de corte sobressalente.','Disco principal bastante gasto.',0,4,2),(8,'2026-07-14 08:00:00','2026-07-15 18:00:00','2026-07-14 08:05:00','2026-07-15 17:40:00',65,80,100,10,'Festa da Padroeira - Instalações',NULL,NULL,0,5,3),(9,'2026-07-18 07:00:00','2026-07-20 17:00:00','2026-07-18 07:10:00','2026-07-20 16:55:00',1210,1225.5,40,20,'Fundação Prédio A - Condomínio','Betoneira entregue limpa.','Devolvida com um pouco de resto de cimento seco.',0,2,4),(10,'2026-07-21 09:00:00','2026-07-21 16:00:00','2026-07-21 09:00:00','2026-07-21 16:10:00',1875,1880,45,35,'Limpeza de Cisterna Industrial',NULL,NULL,0,3,6),(11,'2026-07-24 08:00:00','2026-07-24 17:00:00','2026-07-24 07:58:00','2026-07-24 17:02:00',120,128,80,60,'Compactação Terreno Z - Quadra 4','Sapo recém revisado na oficina.','Sem novidades. Pronto para a próxima.',0,4,1),(12,'2026-07-27 02:09:00','2026-07-27 10:00:00',NULL,NULL,NULL,NULL,NULL,NULL,'Manutenção Noturna Via Expressa','Levar galão extra de gasolina.',NULL,0,5,2),(13,'2026-07-28 08:00:00','2026-07-28 17:00:00',NULL,NULL,NULL,NULL,NULL,NULL,'Obra Novo Hospital Regional',NULL,NULL,0,2,3),(14,'2026-07-28 14:00:00','2026-07-29 14:00:00',NULL,NULL,NULL,NULL,NULL,NULL,'Ponte Leste (Fase 1)','Urgência na entrega no canteiro.',NULL,0,3,4),(16,'2026-07-29 10:00:00','2026-07-30 18:00:00',NULL,NULL,NULL,NULL,NULL,NULL,'Reparo Estrutural Viaduto','Requer atenção na partida a frio.',NULL,0,5,1),(17,'2026-07-15 08:00:00','2026-07-15 17:00:00','2026-07-15 08:05:00','2026-07-15 14:30:00',215,218,40,25,'Corte Asfalto Centro','Tudo OK na saída.','ALERTA: Motor falhando muito e superaquecendo, fiz parada imediata.',0,2,2),(18,'2026-07-20 07:00:00','2026-07-22 17:00:00','2026-07-20 07:15:00','2026-07-22 16:45:00',85,100,100,10,'Evento Praça Central - Tenda',NULL,'ALERTA: Vazamento grave de óleo identificado no último dia de uso.',1,3,3),(19,'2026-07-25 08:00:00','2026-07-26 12:00:00','2026-07-25 08:00:00','2026-07-26 11:30:00',1230,1240,50,10,'Muro de Arrimo - Setor Sul','Equipamento ok.','ALERTA: Rolamento travando na cuba, fazendo barulho alto e metálico.',1,4,4);
/*!40000 ALTER TABLE `liberacao` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `users` (
  `iduser` bigint NOT NULL AUTO_INCREMENT,
  `email` varchar(100) NOT NULL,
  `senha` varchar(100) NOT NULL,
  `nome` varchar(100) NOT NULL,
  `cpf` varchar(14) NOT NULL,
  `telefone` varchar(15) NOT NULL,
  `endereco` varchar(200) NOT NULL,
  `datanascimento` varchar(10) NOT NULL,
  `cargo` enum('administrador','operador') NOT NULL,
  PRIMARY KEY (`iduser`),
  UNIQUE KEY `email` (`email`),
  UNIQUE KEY `cpf` (`cpf`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES (1,'fabin@teste.com','senha','Fabio Santos','04386495278','(43) 98877-6655','Rua da Minha Casa 666','2001-09-11','administrador'),(2,'carlos.almeida@frota.com','123456','Carlos Almeida','123.456.789-00','(11) 98765-4321','Rua das Flores, 123 - Centro','1985-04-12','operador'),(3,'marcos.souza@frota.com','123456','Marcos Souza','98765432111','(21) 8765-4321','Avenida Brasil, 45 - Zona Sul','1990-10-25','operador'),(4,'joao.pedro@frota.com','123456','João Pedro Silva','111.222.333-44','11999998888','Travessa C, 9 - Bairro Novo','1982-01-30','operador'),(5,'lucas.lima@frota.com','123456','Lucas Lima','55566677788','(43) 912345678','Rua D, 444 - Industrial','1995-07-14','operador');
/*!40000 ALTER TABLE `users` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping events for database 'fleet'
--

--
-- Dumping routines for database 'fleet'
--
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2026-07-27 12:59:31
