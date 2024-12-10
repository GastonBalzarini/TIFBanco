-- MySQL dump 10.13  Distrib 5.7.17, for Win64 (x86_64)
--
-- Host: localhost    Database: bdbanco
-- ------------------------------------------------------
-- Server version	5.7.18-log

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
-- Table structure for table `altacuentas`
--

DROP TABLE IF EXISTS `altacuentas`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `altacuentas` (
  `IDAltaCuenta` int(11) NOT NULL AUTO_INCREMENT,
  `DNI` int(11) NOT NULL,
  `Fecha` date NOT NULL,
  `SaldoInicial` decimal(10,2) DEFAULT '10000.00',
  PRIMARY KEY (`IDAltaCuenta`,`DNI`),
  UNIQUE KEY `Cliente` (`DNI`,`Fecha`),
  CONSTRAINT `altacuentas_ibfk_1` FOREIGN KEY (`DNI`) REFERENCES `clientes` (`DNI`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `altacuentas`
--

LOCK TABLES `altacuentas` WRITE;
/*!40000 ALTER TABLE `altacuentas` DISABLE KEYS */;
INSERT INTO `altacuentas` VALUES (1,12345678,'2023-04-01',15000.00),(2,87654321,'2023-04-02',20000.00);
/*!40000 ALTER TABLE `altacuentas` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `altaprestamos`
--

DROP TABLE IF EXISTS `altaprestamos`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `altaprestamos` (
  `IDAltaPrestamo` int(11) NOT NULL AUTO_INCREMENT,
  `Cliente` int(11) NOT NULL,
  `Fecha` date NOT NULL,
  `Importe` decimal(10,2) NOT NULL,
  PRIMARY KEY (`IDAltaPrestamo`,`Cliente`),
  UNIQUE KEY `Cliente` (`Cliente`,`Fecha`),
  CONSTRAINT `altaprestamos_ibfk_1` FOREIGN KEY (`Cliente`) REFERENCES `clientes` (`DNI`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `altaprestamos`
--

LOCK TABLES `altaprestamos` WRITE;
/*!40000 ALTER TABLE `altaprestamos` DISABLE KEYS */;
INSERT INTO `altaprestamos` VALUES (1,12345678,'2023-03-01',50000.00),(2,87654321,'2023-03-15',30000.00);
/*!40000 ALTER TABLE `altaprestamos` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `clientes`
--

DROP TABLE IF EXISTS `clientes`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `clientes` (
  `DNI` int(11) NOT NULL,
  `CUIL` varchar(11) NOT NULL,
  `Nombre` varchar(50) NOT NULL,
  `Apellido` varchar(50) NOT NULL,
  `Sexo` int(11) NOT NULL,
  `FechaNacimiento` date DEFAULT NULL,
  `Direccion` varchar(100) DEFAULT NULL,
  `IDLocalidad` int(11) DEFAULT NULL,
  `CorreoElectronico` varchar(100) DEFAULT NULL,
  `Telefono` varchar(20) DEFAULT NULL,
  `Usuario` varchar(50) DEFAULT NULL,
  `IDNacionalidad` int(11) DEFAULT NULL,
  `estado` tinyint(1) DEFAULT '1',
  `IDProvincia` int(11) DEFAULT NULL,
  PRIMARY KEY (`DNI`),
  KEY `Usuario` (`Usuario`),
  KEY `IDLocalidad` (`IDLocalidad`),
  KEY `FK_Clientes_Nacionalidad` (`IDNacionalidad`),
  KEY `clientes_ibfk_3_idx` (`Sexo`),
  KEY `clientes_ibfk_4_idx` (`IDProvincia`),
  CONSTRAINT `FK_Clientes_Nacionalidad` FOREIGN KEY (`IDNacionalidad`) REFERENCES `nacionalidad` (`IDNacionalidad`),
  CONSTRAINT `clientes_ibfk_1` FOREIGN KEY (`Usuario`) REFERENCES `usuarios` (`Usuario`),
  CONSTRAINT `clientes_ibfk_2` FOREIGN KEY (`IDLocalidad`) REFERENCES `localidades` (`IDLocalidad`),
  CONSTRAINT `clientes_ibfk_3` FOREIGN KEY (`Sexo`) REFERENCES `sexo` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `clientes_ibfk_4` FOREIGN KEY (`IDProvincia`) REFERENCES `provincias` (`IDProvincia`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `clientes`
--

LOCK TABLES `clientes` WRITE;
/*!40000 ALTER TABLE `clientes` DISABLE KEYS */;
INSERT INTO `clientes` VALUES (3030,'1000','tania','tapia',2,'1968-05-19','el barrio',4,'prince@gmail.com','321312321312','tito',1,1,3),(4321,'4321','dylan','carpio',1,'2004-05-07','la calle pou',1,'carp@gmail.com','321312321312','newtnate',1,1,1),(4365,'4365','tomas','salas',1,'2024-11-02','rio cuarto',7,'abcd@gmail.com','11987654','newpapi',4,1,6),(5050,'5050','elsa','rojas',2,'1970-12-08','calle 12',8,'amy@gmail.com','321312321312','amyrojas',1,0,7),(7777,'7777','cristobal','colon',1,'2004-12-05','anashei',10,'virrey@gmail.com','12313','elnascar',2,1,9),(8765,'3030','julio','cesar',1,'2001-06-16','la calle pou',5,'juliocesar@gmail.com','12313','nuevousuario',1,1,4),(9876,'9876','zoe','rouco',2,'2003-07-19','torcuato',2,'zoe@gmail.com','12313','zoee',1,1,1),(154264,'152412','Gaston','bnaslk',1,'2001-02-08','estevez',3,'dasdad@gmail.com','341241241','balzarini',1,1,2),(1597546,'1234567898','Gaston','Balzarini',2,'2024-11-29','Estevez 2322',2,'gaston@gmail.com','121212121','Gaston',1,1,1),(12345678,'20123456789','Juan','Pérez',1,'1980-01-01','Calle Falsa 123',1,'juan.perez@example.com','1234567890','jperez',1,1,1),(13535212,'53135121351','134124','21124124',2,'2024-12-03','Estevez 3371',1,'gaston@gmail.com','153312135','asdqwe',1,1,1),(15648954,'1215432115','234513213','13121234',1,'2024-12-03','Estevez 3371',2,'gaston.bnalzarini@gmail.com','1138778874','kasjdkadi',1,1,1),(21312312,'21313131231','3421124','123421',2,'2024-12-01','Estevez 3371',2,'gaston@gmail.com','215123132132','wqeqoekwoq',1,1,1),(21312423,'12313211331','123123','231233',1,'2024-12-03','Estevez 3371',1,'g@gmail.com','1231312313','Mauricio2',1,1,1),(23545468,'42315135132','1231231','3123213',1,'2024-12-03','Estevez 371',3,'gaston@gmail.com','53211231','adasdasd',1,1,2),(42313213,'21315123115','12313','123123123',1,'2024-12-02','Estevez 3371',1,'gaston@gmail.com','1138788770','jking',1,1,1),(45678912,'12365789455','Gaston','Balzarini',1,'2024-12-02','Estevez 2214',3,'gaston@gmail.com','12151515412','gastaeraw',1,1,2),(87654321,'20987654321','María','García',2,'1990-02-02','Av. Siempre Viva 456',2,'maria.garcia@example.com','0987654321','mgarcia',2,1,2),(98745612,'1235132132','2313123','12313212',1,'2024-12-02','Estevez 3381',1,'gaston@gmail.com','123121312','asdadawwd',1,1,1),(98745621,'1535421323','123132','12312312',1,'2024-12-03','Estevez 3371',1,'gaston@gmail.com','11354121512','dasjdlka',1,1,1),(123456789,'210212105','123123','12313',1,'2002-03-07','4142414',1,'gastonasdad@gmail.com','12313123','148384415331987',1,1,1),(213151321,'13515343813','Gaston','Balzarini',1,'2024-12-03','Estevez 3371',3,'gaston@gmail.com','1138788770','Estevez',1,1,2);
/*!40000 ALTER TABLE `clientes` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `condicionprestamos`
--

DROP TABLE IF EXISTS `condicionprestamos`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `condicionprestamos` (
  `idCondicion` int(11) NOT NULL AUTO_INCREMENT,
  `descripcion` varchar(50) NOT NULL,
  PRIMARY KEY (`idCondicion`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `condicionprestamos`
--

LOCK TABLES `condicionprestamos` WRITE;
/*!40000 ALTER TABLE `condicionprestamos` DISABLE KEYS */;
INSERT INTO `condicionprestamos` VALUES (1,'Aceptado'),(2,'Rechazado'),(3,'Pendiente'),(4,'En Proceso de Pago'),(5,'Pagado');
/*!40000 ALTER TABLE `condicionprestamos` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `cuentas`
--

DROP TABLE IF EXISTS `cuentas`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `cuentas` (
  `NumeroCuenta` int(11) NOT NULL AUTO_INCREMENT,
  `CBU` varchar(22) NOT NULL,
  `TipoCuenta` varchar(20) NOT NULL,
  `Saldo` decimal(10,2) DEFAULT '10000.00',
  `DNI` int(11) NOT NULL,
  `FechaCreacion` date NOT NULL,
  `estado` tinyint(1) DEFAULT '1',
  PRIMARY KEY (`NumeroCuenta`,`DNI`),
  KEY `Cliente` (`DNI`),
  CONSTRAINT `cuentas_ibfk_1` FOREIGN KEY (`DNI`) REFERENCES `clientes` (`DNI`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cuentas`
--

LOCK TABLES `cuentas` WRITE;
/*!40000 ALTER TABLE `cuentas` DISABLE KEYS */;
INSERT INTO `cuentas` VALUES (1,'1234567890123456789012','Caja de Ahorro',50151.45,12345678,'2023-04-01',1),(2,'0987654321098765432109','Caja de ahorro',44846.00,87654321,'2023-04-02',0),(3,'0987654321098765432109','Caja de ahorro',10317366.00,87654321,'2024-11-26',1),(4,'0987654321098765432132','Cuenta corriente',525000.00,87654321,'2024-11-26',1);
/*!40000 ALTER TABLE `cuentas` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `cuotas`
--

DROP TABLE IF EXISTS `cuotas`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `cuotas` (
  `IDCuota` int(11) NOT NULL AUTO_INCREMENT,
  `IDPrestamo` int(11) NOT NULL,
  `FechaPago` date DEFAULT NULL,
  `Importe` decimal(10,2) NOT NULL,
  `Pagado` tinyint(1) DEFAULT '0',
  PRIMARY KEY (`IDCuota`,`IDPrestamo`),
  KEY `cuotas_ibfk_1_idx` (`IDPrestamo`)
) ENGINE=InnoDB AUTO_INCREMENT=39 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cuotas`
--

LOCK TABLES `cuotas` WRITE;
/*!40000 ALTER TABLE `cuotas` DISABLE KEYS */;
INSERT INTO `cuotas` VALUES (1,16,'2024-12-04',25038.50,1),(1,17,NULL,1683501.63,0),(2,2,'2024-12-04',3000.00,1),(2,16,'2024-12-04',25038.50,1),(2,17,NULL,1683501.63,0),(3,16,'2024-12-04',25038.50,1),(3,17,'2024-12-04',1683501.63,1),(4,16,'2024-12-04',25038.50,1),(4,17,'2024-12-04',1683501.63,1),(5,16,NULL,25038.50,0),(5,17,NULL,1683501.63,0),(6,16,NULL,25038.50,0),(6,17,NULL,1683501.63,0),(8,1,'2000-01-01',4166.67,0),(12,7,'2000-01-01',12500.00,0),(14,8,'2000-01-01',2057613.00,0),(17,9,'2000-01-01',20576.00,0),(19,10,'2000-01-01',1010101.00,0),(21,11,'2024-12-04',720.17,0),(23,12,'2024-12-04',9053.50,0),(25,13,'2024-12-01',4060.83,0),(27,14,'2024-12-01',561.83,0),(28,14,'2024-12-02',561.83,0),(29,14,'2024-12-03',561.83,0),(30,14,'2024-12-04',561.83,0),(31,14,'2024-12-05',561.83,0),(32,14,'2024-12-06',561.83,0),(33,15,'2024-12-01',220.67,0),(34,15,'2024-12-02',220.67,0),(35,15,'2024-12-03',220.67,0),(36,15,'2024-12-04',220.67,0),(37,15,'2024-12-05',220.67,0),(38,15,'2024-12-06',220.67,0);
/*!40000 ALTER TABLE `cuotas` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `localidades`
--

DROP TABLE IF EXISTS `localidades`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `localidades` (
  `IDLocalidad` int(11) NOT NULL AUTO_INCREMENT,
  `Nombre` varchar(50) NOT NULL,
  `IDProvincia` int(11) NOT NULL,
  PRIMARY KEY (`IDLocalidad`,`IDProvincia`),
  KEY `localidades_ibfk_1` (`IDProvincia`),
  CONSTRAINT `localidades_ibfk_1` FOREIGN KEY (`IDProvincia`) REFERENCES `provincias` (`IDProvincia`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `localidades`
--

LOCK TABLES `localidades` WRITE;
/*!40000 ALTER TABLE `localidades` DISABLE KEYS */;
INSERT INTO `localidades` VALUES (1,'La Plata',1),(2,'Mar del Plata',1),(3,'Córdoba Capital',2),(4,'Rosario',3),(5,'Mendoza Capital',4),(6,'San Miguel de Tucumán',5),(7,'Salta Capital',6),(8,'Resistencia',7),(9,'Posadas',8),(10,'Formosa Capital',9);
/*!40000 ALTER TABLE `localidades` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `movimientos`
--

DROP TABLE IF EXISTS `movimientos`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `movimientos` (
  `IDMovimiento` int(11) NOT NULL AUTO_INCREMENT,
  `Fecha` date NOT NULL,
  `Detalle` varchar(255) DEFAULT NULL,
  `Importe` decimal(10,2) NOT NULL,
  `IdTipoMovimiento` int(11) NOT NULL,
  `CuentaOrigen` int(11) DEFAULT NULL,
  `CuentaDestino` int(11) DEFAULT NULL,
  PRIMARY KEY (`IDMovimiento`),
  KEY `CuentaOrigen` (`CuentaOrigen`),
  KEY `CuentaDestino` (`CuentaDestino`),
  KEY `movimientos_ibfk_3_idx` (`IdTipoMovimiento`),
  CONSTRAINT `movimientos_ibfk_1` FOREIGN KEY (`CuentaOrigen`) REFERENCES `cuentas` (`NumeroCuenta`),
  CONSTRAINT `movimientos_ibfk_2` FOREIGN KEY (`CuentaDestino`) REFERENCES `cuentas` (`NumeroCuenta`),
  CONSTRAINT `movimientos_ibfk_3` FOREIGN KEY (`IdTipoMovimiento`) REFERENCES `tipomovimiento` (`idTipoMovimiento`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `movimientos`
--

LOCK TABLES `movimientos` WRITE;
/*!40000 ALTER TABLE `movimientos` DISABLE KEYS */;
/*!40000 ALTER TABLE `movimientos` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `nacionalidad`
--

DROP TABLE IF EXISTS `nacionalidad`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `nacionalidad` (
  `IDNacionalidad` int(11) NOT NULL AUTO_INCREMENT,
  `Descripcion` varchar(50) NOT NULL,
  PRIMARY KEY (`IDNacionalidad`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `nacionalidad`
--

LOCK TABLES `nacionalidad` WRITE;
/*!40000 ALTER TABLE `nacionalidad` DISABLE KEYS */;
INSERT INTO `nacionalidad` VALUES (1,'Argentina'),(2,'Brasil'),(3,'Uruguay'),(4,'Chile');
/*!40000 ALTER TABLE `nacionalidad` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `pagoprestamos`
--

DROP TABLE IF EXISTS `pagoprestamos`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `pagoprestamos` (
  `IDPagoPrestamo` int(11) NOT NULL AUTO_INCREMENT,
  `IDCuota` int(11) NOT NULL,
  `FechaPago` date NOT NULL,
  `Importe` decimal(10,2) NOT NULL,
  `IDPrestamo` int(11) NOT NULL,
  PRIMARY KEY (`IDPagoPrestamo`,`IDCuota`,`IDPrestamo`),
  UNIQUE KEY `IDCuota` (`IDCuota`,`FechaPago`),
  CONSTRAINT `pagoprestamos_ibfk_1` FOREIGN KEY (`IDCuota`) REFERENCES `cuotas` (`IDCuota`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `pagoprestamos`
--

LOCK TABLES `pagoprestamos` WRITE;
/*!40000 ALTER TABLE `pagoprestamos` DISABLE KEYS */;
/*!40000 ALTER TABLE `pagoprestamos` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `prestamos`
--

DROP TABLE IF EXISTS `prestamos`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `prestamos` (
  `IDPrestamo` int(11) NOT NULL,
  `Cliente` int(11) NOT NULL,
  `Fecha` date NOT NULL,
  `ImportePedido` decimal(10,2) NOT NULL,
  `ImporteAPagar` decimal(10,2) NOT NULL,
  `PlazoDePago` int(11) NOT NULL,
  `MontoPorMes` decimal(10,2) NOT NULL,
  `Cuotas` int(11) NOT NULL,
  `Condicion` int(11) NOT NULL,
  `Estado` tinyint(4) NOT NULL DEFAULT '1',
  `NumCuenta` int(11) NOT NULL,
  PRIMARY KEY (`IDPrestamo`,`Cliente`),
  KEY `Cliente` (`Cliente`),
  KEY `prestamos_ibfk_2_idx` (`Condicion`),
  KEY `prestamos_ibfk_3_idx` (`NumCuenta`),
  CONSTRAINT `prestamos_ibfk_1` FOREIGN KEY (`Cliente`) REFERENCES `clientes` (`DNI`),
  CONSTRAINT `prestamos_ibfk_2` FOREIGN KEY (`Condicion`) REFERENCES `condicionprestamos` (`idCondicion`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `prestamos_ibfk_3` FOREIGN KEY (`NumCuenta`) REFERENCES `cuentas` (`NumeroCuenta`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `prestamos`
--

LOCK TABLES `prestamos` WRITE;
/*!40000 ALTER TABLE `prestamos` DISABLE KEYS */;
INSERT INTO `prestamos` VALUES (1,12345678,'2023-03-01',50000.00,60000.00,12,5000.00,12,3,1,1),(2,87654321,'2023-03-15',30000.00,36000.00,10,3000.00,10,3,1,2),(3,7777,'2024-11-24',150.00,165.00,6,27.50,6,3,1,1),(4,7777,'2024-11-24',1524.52,1676.97,12,139.75,12,2,1,1),(5,7777,'2024-11-24',15151.45,16666.60,12,1388.88,12,1,1,1),(6,7777,'2024-11-24',150.45,165.50,24,6.90,24,3,1,1),(7,87654321,'2024-12-04',150000.00,165000.00,12,13750.00,12,3,1,3),(8,87654321,'2024-12-04',12345678.00,13580246.00,6,2263374.20,6,3,1,3),(9,87654321,'2024-12-04',123456.00,135801.61,6,22633.60,6,3,1,4),(10,87654321,'2024-12-04',12121212.00,13333333.00,12,1111111.10,12,3,1,3),(11,87654321,'2024-12-04',4321.00,4753.10,6,792.18,6,3,1,3),(12,87654321,'2024-12-04',54321.00,59753.10,6,9958.85,6,3,1,3),(13,87654321,'2024-12-04',24365.00,26801.50,6,4466.92,6,3,1,3),(14,87654321,'2024-12-04',3371.00,3708.10,6,618.02,6,3,1,3),(15,87654321,'2024-12-04',1324.00,1456.40,6,242.73,6,3,1,3),(16,87654321,'2024-12-04',150231.00,165254.11,6,27542.35,6,1,1,3),(17,87654321,'2024-12-04',10101010.00,11111111.00,6,1851851.90,6,1,1,3),(18,87654321,'2024-12-04',12345.00,13579.50,6,2263.25,6,1,1,3),(19,87654321,'2024-12-04',45.12,49.63,6,8.27,6,3,1,3);
/*!40000 ALTER TABLE `prestamos` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `provincias`
--

DROP TABLE IF EXISTS `provincias`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `provincias` (
  `IDProvincia` int(11) NOT NULL AUTO_INCREMENT,
  `Nombre` varchar(50) NOT NULL,
  PRIMARY KEY (`IDProvincia`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `provincias`
--

LOCK TABLES `provincias` WRITE;
/*!40000 ALTER TABLE `provincias` DISABLE KEYS */;
INSERT INTO `provincias` VALUES (1,'Buenos Aires'),(2,'Córdoba'),(3,'Santa Fe'),(4,'Mendoza'),(5,'Tucumán'),(6,'Salta'),(7,'Chaco'),(8,'Misiones'),(9,'Formosa'),(10,'Corrientes');
/*!40000 ALTER TABLE `provincias` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sexo`
--

DROP TABLE IF EXISTS `sexo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sexo` (
  `id` int(11) NOT NULL,
  `descripcion` varchar(45) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sexo`
--

LOCK TABLES `sexo` WRITE;
/*!40000 ALTER TABLE `sexo` DISABLE KEYS */;
INSERT INTO `sexo` VALUES (1,'Masculino'),(2,'Femenino'),(3,'Otro');
/*!40000 ALTER TABLE `sexo` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tipodeusuario`
--

DROP TABLE IF EXISTS `tipodeusuario`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tipodeusuario` (
  `IDTipoDeUsuario` int(11) NOT NULL AUTO_INCREMENT,
  `Descripcion` varchar(50) NOT NULL,
  PRIMARY KEY (`IDTipoDeUsuario`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tipodeusuario`
--

LOCK TABLES `tipodeusuario` WRITE;
/*!40000 ALTER TABLE `tipodeusuario` DISABLE KEYS */;
INSERT INTO `tipodeusuario` VALUES (1,'administrador'),(2,'cliente');
/*!40000 ALTER TABLE `tipodeusuario` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tipomovimiento`
--

DROP TABLE IF EXISTS `tipomovimiento`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tipomovimiento` (
  `idTipoMovimiento` int(11) NOT NULL,
  `Descripcion` varchar(45) NOT NULL,
  PRIMARY KEY (`idTipoMovimiento`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tipomovimiento`
--

LOCK TABLES `tipomovimiento` WRITE;
/*!40000 ALTER TABLE `tipomovimiento` DISABLE KEYS */;
INSERT INTO `tipomovimiento` VALUES (1,'Depósito'),(2,'Transferencia'),(3,'Pago de préstamo');
/*!40000 ALTER TABLE `tipomovimiento` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `transferencias`
--

DROP TABLE IF EXISTS `transferencias`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `transferencias` (
  `IDTransferencia` int(11) NOT NULL AUTO_INCREMENT,
  `Fecha` date NOT NULL,
  `Importe` decimal(10,2) NOT NULL,
  `CuentaOrigen` int(11) NOT NULL,
  `CuentaDestino` int(11) NOT NULL,
  PRIMARY KEY (`IDTransferencia`),
  KEY `transferencias_ibfk_1` (`CuentaOrigen`),
  KEY `transferencias_ibfk_2` (`CuentaDestino`),
  CONSTRAINT `transferencias_ibfk_1` FOREIGN KEY (`CuentaOrigen`) REFERENCES `cuentas` (`NumeroCuenta`),
  CONSTRAINT `transferencias_ibfk_2` FOREIGN KEY (`CuentaDestino`) REFERENCES `cuentas` (`NumeroCuenta`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `transferencias`
--

LOCK TABLES `transferencias` WRITE;
/*!40000 ALTER TABLE `transferencias` DISABLE KEYS */;
INSERT INTO `transferencias` VALUES (1,'2022-05-04',1500.00,1,2),(2,'2024-11-27',15000.00,2,1),(3,'2024-12-04',5000.00,3,1);
/*!40000 ALTER TABLE `transferencias` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `usuarios`
--

DROP TABLE IF EXISTS `usuarios`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `usuarios` (
  `Usuario` varchar(50) NOT NULL,
  `Contraseña` varchar(50) NOT NULL,
  `IDTipoDeUsuario` int(11) DEFAULT NULL,
  `Estado` tinyint(4) NOT NULL DEFAULT '1',
  PRIMARY KEY (`Usuario`),
  KEY `IDTipoDeUsuario` (`IDTipoDeUsuario`),
  CONSTRAINT `usuarios_ibfk_1` FOREIGN KEY (`IDTipoDeUsuario`) REFERENCES `tipodeusuario` (`IDTipoDeUsuario`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `usuarios`
--

LOCK TABLES `usuarios` WRITE;
/*!40000 ALTER TABLE `usuarios` DISABLE KEYS */;
INSERT INTO `usuarios` VALUES ('148384415331','123',2,1),('148384415331987','123',2,1),('a','1',1,1),('adasdasd','123',2,1),('amyrojas','1111',2,1),('arodriguez','contrasena789',2,1),('asdadawwd','123',2,1),('asdqwe','123',2,1),('balzarini','456',2,1),('csuarez','contrasena102',2,1),('dasjdlka','123',2,1),('elnascar','1212',2,1),('Estevez','123',2,1),('gastaeraw','123',2,1),('Gaston','123',2,1),('gastonbalza','123',2,1),('Gastonbalzs','123',2,1),('gastonbasd','123',2,1),('GastonBlas','123',2,1),('gastonhjkhhkj','123',2,1),('jking','123',2,1),('jperez','contrasena123',1,1),('kasjdkadi','123',2,1),('lmendez','contrasena101',1,1),('Mauricio2','123',2,1),('mgarcia','contrasena456',2,1),('newpapi','pepe',2,1),('newtnate','1111',2,1),('nuevousuario','1111',2,1),('tito','9999',2,1),('wqeqoekwoq','123456',2,1),('zoee','1234',2,1);
/*!40000 ALTER TABLE `usuarios` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2024-12-04 14:57:37
