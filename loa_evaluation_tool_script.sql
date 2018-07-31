CREATE DATABASE  IF NOT EXISTS `loa_evaluation_tool` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `loa_evaluation_tool`;
-- MySQL dump 10.13  Distrib 5.6.17, for Win64 (x86_64)
--
-- Host: localhost    Database: loa_evaluation_tool
-- ------------------------------------------------------
-- Server version	5.5.40

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
-- Table structure for table `tb_ace_cog_loa`
--

DROP TABLE IF EXISTS `tb_ace_cog_loa`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tb_ace_cog_loa` (
  `PK_TB_ID` int(11) NOT NULL AUTO_INCREMENT,
  `LOA` varchar(500) NOT NULL,
  PRIMARY KEY (`PK_TB_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_ace_cog_loa`
--

LOCK TABLES `tb_ace_cog_loa` WRITE;
/*!40000 ALTER TABLE `tb_ace_cog_loa` DISABLE KEYS */;
/*!40000 ALTER TABLE `tb_ace_cog_loa` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tb_ace_loa_cri_mat`
--

DROP TABLE IF EXISTS `tb_ace_loa_cri_mat`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tb_ace_loa_cri_mat` (
  `PK_TB_ID` int(11) NOT NULL AUTO_INCREMENT,
  `FK_TB_ACE_PRO_SEQ` int(11) NOT NULL,
  `DS_STABLE` tinyint(1) DEFAULT NULL,
  `DS_REDUCED_STABILITY` tinyint(1) DEFAULT NULL,
  `DS_HANDLY_STABLE` tinyint(1) DEFAULT NULL,
  `DS_UNSTABLE` tinyint(1) DEFAULT NULL,
  `ST_INSENSITIVE` tinyint(1) DEFAULT NULL,
  `ST_HARDLY_SENSITIVE` tinyint(1) DEFAULT NULL,
  `ST_SENSITIVE` tinyint(1) DEFAULT NULL,
  `ST_VERY_SENSITIVE` tinyint(1) DEFAULT NULL,
  `GR_EX_GRIP_SURFACE` tinyint(1) DEFAULT NULL,
  `GR_INT_GRIP_SURFACE` tinyint(1) DEFAULT NULL,
  `GR_MAGNETIC_GRIPPER` tinyint(1) DEFAULT NULL,
  `GR_FAB_CLOSURE_GRIPPER` tinyint(1) DEFAULT NULL,
  `NV_NO_FURTHER_VARIANTS` tinyint(1) DEFAULT NULL,
  `NV_ONE_FURTHER_VARIANTS` tinyint(1) DEFAULT NULL,
  `NV_TWO_FURTHER_VARIANTS` tinyint(1) DEFAULT NULL,
  `NV_MORE_FURTHER_VARIANTS` tinyint(1) DEFAULT NULL,
  `SP_UP` tinyint(1) DEFAULT NULL,
  `SP_MORE` tinyint(1) DEFAULT NULL,
  `SP_STABLE_UNSTABLE` tinyint(1) DEFAULT NULL,
  `SP_ONLY_UNSTABLE` tinyint(1) DEFAULT NULL,
  `SM_ROT_SYMMETRICAL` tinyint(1) DEFAULT NULL,
  `SM_AR_SYMMETRY` tinyint(1) DEFAULT NULL,
  `SM_MA_ASYMMETRICAL` tinyint(1) DEFAULT NULL,
  `SM_SE_SYMMETRICAL` tinyint(1) DEFAULT NULL,
  `HA_NONE` tinyint(1) DEFAULT NULL,
  `HA_STI_JAM_POSSIBLE` tinyint(1) DEFAULT NULL,
  `HA_COM_PEN_POSSIBILE` tinyint(1) DEFAULT NULL,
  `HA_SEE_SYMMETRICAL` tinyint(1) DEFAULT NULL,
  `FJ_NEVER` tinyint(1) DEFAULT NULL,
  `FJ_OCASIONALLY` tinyint(1) DEFAULT NULL,
  `FJ_RARELY` tinyint(1) DEFAULT NULL,
  `FJ_OFTEN` tinyint(1) DEFAULT NULL,
  `AC_VERY_GOOD` tinyint(1) DEFAULT NULL,
  `AC_GOOD` tinyint(1) DEFAULT NULL,
  `AC_SATISFACTORY` tinyint(1) DEFAULT NULL,
  `AC_SUFFICIENT` tinyint(1) DEFAULT NULL,
  `OR_NO_AXES` tinyint(1) DEFAULT NULL,
  `OR_ONE_AXIS` tinyint(1) DEFAULT NULL,
  `OR_TWO_AXES` tinyint(1) DEFAULT NULL,
  `OR_THREE_AXES` tinyint(1) DEFAULT NULL,
  `JM_LINEAR` tinyint(1) DEFAULT NULL,
  `JM_ROTATION` tinyint(1) DEFAULT NULL,
  `JM_LINEAR_ROTATORY` tinyint(1) DEFAULT NULL,
  `JM_PATH_MOVEMENT` tinyint(1) DEFAULT NULL,
  `JF_NONE` tinyint(1) DEFAULT NULL,
  `JF_LOW` tinyint(1) DEFAULT NULL,
  `JF_MEDIUM` tinyint(1) DEFAULT NULL,
  `JF_HIGH` tinyint(1) DEFAULT NULL,
  `JA_JOIN_BASIC_COMP` tinyint(1) DEFAULT NULL,
  `JA_JOIN_COMP` tinyint(1) DEFAULT NULL,
  `JA_BASIC_COMP` tinyint(1) DEFAULT NULL,
  `JA_MORE_COMP` tinyint(1) DEFAULT NULL,
  `NC_ONE_BASIC_COMP` tinyint(1) DEFAULT NULL,
  `NC_TWO_BASIC_COMP` tinyint(1) DEFAULT NULL,
  `NC_THREE_BASIC_COMP` tinyint(1) DEFAULT NULL,
  `NC_MORE_COMP` tinyint(1) DEFAULT NULL,
  `JS_SEC_ALL_DIRECTION` tinyint(1) DEFAULT NULL,
  `JS_GRAVITY_FIT` tinyint(1) DEFAULT NULL,
  `JS_GRAVITY_RUBBING` tinyint(1) DEFAULT NULL,
  `JS_ADDITIONAL_SEC` tinyint(1) DEFAULT NULL,
  `SO_NONE` tinyint(1) DEFAULT NULL,
  `SO_ONE` tinyint(1) DEFAULT NULL,
  `SO_TWO` tinyint(1) DEFAULT NULL,
  `SO_MORE` tinyint(1) DEFAULT NULL,
  `CREATE_DATE` datetime NOT NULL,
  `UPDATE_DATE` datetime NOT NULL,
  PRIMARY KEY (`PK_TB_ID`),
  KEY `fk_TB_ACE_LOA_CRI_MAT_TB_ACE_PRO_SEQ1_idx` (`FK_TB_ACE_PRO_SEQ`),
  CONSTRAINT `fk_TB_ACE_LOA_CRI_MAT_TB_ACE_PRO_SEQ1` FOREIGN KEY (`FK_TB_ACE_PRO_SEQ`) REFERENCES `tb_ace_pro_seq` (`PK_TB_ID`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_ace_loa_cri_mat`
--

LOCK TABLES `tb_ace_loa_cri_mat` WRITE;
/*!40000 ALTER TABLE `tb_ace_loa_cri_mat` DISABLE KEYS */;
/*!40000 ALTER TABLE `tb_ace_loa_cri_mat` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tb_ace_phy_loa`
--

DROP TABLE IF EXISTS `tb_ace_phy_loa`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tb_ace_phy_loa` (
  `PK_TB_ID` int(11) NOT NULL AUTO_INCREMENT,
  `LOA` varchar(500) NOT NULL,
  PRIMARY KEY (`PK_TB_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_ace_phy_loa`
--

LOCK TABLES `tb_ace_phy_loa` WRITE;
/*!40000 ALTER TABLE `tb_ace_phy_loa` DISABLE KEYS */;
/*!40000 ALTER TABLE `tb_ace_phy_loa` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tb_ace_pro_loa_inf`
--

DROP TABLE IF EXISTS `tb_ace_pro_loa_inf`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tb_ace_pro_loa_inf` (
  `PK_TB_ID` int(11) NOT NULL AUTO_INCREMENT,
  `FK_TB_ACE_PRO_SEQ` int(11) NOT NULL,
  `FK_TB_ACE_PHY_LOA` int(11) NOT NULL,
  `FK_TB_ACE_COG_LOA` int(11) NOT NULL,
  `POSSIBILITY` tinyint(1) NOT NULL,
  `BEST_RANGE` varchar(45) NOT NULL,
  `CREATE_DATE` datetime NOT NULL,
  `UPDATE_DATE` datetime NOT NULL,
  PRIMARY KEY (`PK_TB_ID`),
  KEY `fk_TB_ACE_PRO_LOA_INF_TB_ACE_PRO_SEQ1_idx` (`FK_TB_ACE_PRO_SEQ`),
  KEY `fk_TB_ACE_PRO_LOA_INF_TB_ACE_PHY_LOA1_idx` (`FK_TB_ACE_PHY_LOA`),
  KEY `fk_TB_ACE_PRO_LOA_INF_TB_ACE_COG_LOA1_idx` (`FK_TB_ACE_COG_LOA`),
  CONSTRAINT `fk_TB_ACE_PRO_LOA_INF_TB_ACE_PRO_SEQ1` FOREIGN KEY (`FK_TB_ACE_PRO_SEQ`) REFERENCES `tb_ace_pro_seq` (`PK_TB_ID`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_TB_ACE_PRO_LOA_INF_TB_ACE_PHY_LOA1` FOREIGN KEY (`FK_TB_ACE_PHY_LOA`) REFERENCES `tb_ace_phy_loa` (`PK_TB_ID`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_TB_ACE_PRO_LOA_INF_TB_ACE_COG_LOA1` FOREIGN KEY (`FK_TB_ACE_COG_LOA`) REFERENCES `tb_ace_cog_loa` (`PK_TB_ID`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_ace_pro_loa_inf`
--

LOCK TABLES `tb_ace_pro_loa_inf` WRITE;
/*!40000 ALTER TABLE `tb_ace_pro_loa_inf` DISABLE KEYS */;
/*!40000 ALTER TABLE `tb_ace_pro_loa_inf` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tb_ace_pro_plan`
--

DROP TABLE IF EXISTS `tb_ace_pro_plan`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tb_ace_pro_plan` (
  `PK_TB_ID` int(11) NOT NULL AUTO_INCREMENT,
  `FK_TB_ACE_PRO_SEQ` int(11) NOT NULL,
  `N_PROD_PIECE_PER_HOURS` int(11) NOT NULL,
  `CREATE_DATE` datetime NOT NULL,
  `UPDATE_DATE` datetime NOT NULL,
  PRIMARY KEY (`PK_TB_ID`),
  KEY `fk_TB_ACE_PRO_PLAN_TB_ACE_PRO_SEQ1_idx` (`FK_TB_ACE_PRO_SEQ`),
  CONSTRAINT `fk_TB_ACE_PRO_PLAN_TB_ACE_PRO_SEQ1` FOREIGN KEY (`FK_TB_ACE_PRO_SEQ`) REFERENCES `tb_ace_pro_seq` (`PK_TB_ID`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_ace_pro_plan`
--

LOCK TABLES `tb_ace_pro_plan` WRITE;
/*!40000 ALTER TABLE `tb_ace_pro_plan` DISABLE KEYS */;
/*!40000 ALTER TABLE `tb_ace_pro_plan` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tb_ace_pro_seq`
--

DROP TABLE IF EXISTS `tb_ace_pro_seq`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tb_ace_pro_seq` (
  `PK_TB_ID` int(11) NOT NULL AUTO_INCREMENT,
  `NAME` varchar(500) NOT NULL,
  `SUB_PRO_LEV` int(11) NOT NULL,
  `FK_TB_ACE_STATUS` int(11) NOT NULL,
  `VAR_PRO_SEQ_ID` varchar(45) NOT NULL,
  `CREATE_DATE` datetime NOT NULL,
  `UPDATE_DATE` datetime NOT NULL,
  `OPERATOR` varchar(45) DEFAULT NULL,
  `N_LOWER_LEVEL_SUB_PRO` int(11) NOT NULL,
  PRIMARY KEY (`PK_TB_ID`),
  KEY `fk_TB_ACE_PRO_SEQ_TB_ACE_STATUS1_idx` (`FK_TB_ACE_STATUS`),
  CONSTRAINT `fk_TB_ACE_PRO_SEQ_TB_ACE_STATUS1` FOREIGN KEY (`FK_TB_ACE_STATUS`) REFERENCES `tb_ace_status` (`PK_ID`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_ace_pro_seq`
--

LOCK TABLES `tb_ace_pro_seq` WRITE;
/*!40000 ALTER TABLE `tb_ace_pro_seq` DISABLE KEYS */;
/*!40000 ALTER TABLE `tb_ace_pro_seq` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tb_ace_pro_spec`
--

DROP TABLE IF EXISTS `tb_ace_pro_spec`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tb_ace_pro_spec` (
  `PK_TB_ID` int(11) NOT NULL AUTO_INCREMENT,
  `FK_TB_ACE_PRO_SEQ` int(11) NOT NULL,
  `N_SHIPTS_DAY` float NOT NULL,
  `HOURS_SHIFT` float NOT NULL,
  `WORKING_DAYS_YEAR` float NOT NULL,
  `CREATE_DATE` datetime NOT NULL,
  `UPDATE_DATE` datetime NOT NULL,
  PRIMARY KEY (`PK_TB_ID`),
  KEY `fk_TB_ACE_PRO_SPEC_TB_ACE_PRO_SEQ1_idx` (`FK_TB_ACE_PRO_SEQ`),
  CONSTRAINT `fk_TB_ACE_PRO_SPEC_TB_ACE_PRO_SEQ1` FOREIGN KEY (`FK_TB_ACE_PRO_SEQ`) REFERENCES `tb_ace_pro_seq` (`PK_TB_ID`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_ace_pro_spec`
--

LOCK TABLES `tb_ace_pro_spec` WRITE;
/*!40000 ALTER TABLE `tb_ace_pro_spec` DISABLE KEYS */;
/*!40000 ALTER TABLE `tb_ace_pro_spec` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tb_ace_res`
--

DROP TABLE IF EXISTS `tb_ace_res`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tb_ace_res` (
  `PK_TB_ID` int(11) NOT NULL AUTO_INCREMENT,
  `FK_TB_ACE_PRO_SEQ` int(11) NOT NULL,
  `NAME` varchar(500) NOT NULL,
  `LOA_PHYSICAL` int(11) NOT NULL,
  `LOA_COGNITIVE` int(11) NOT NULL,
  `LC_N_OPER_MACHINE` double NOT NULL,
  `MC_A_MAINT_COSTS` double NOT NULL,
  `MC_A_MAINT_COSTS_PERC` float NOT NULL,
  `RC_INST_SURFACE` varchar(45) NOT NULL,
  `RC_COSTS_M_MONTH` double NOT NULL,
  `ID_MAC_PURH_VALUE` double NOT NULL,
  `ID_MAC_SALES_VALUE` double NOT NULL,
  `ID_ECO_USEFULL_LIFE` int(11) NOT NULL,
  `IC_INTER_RATE` float NOT NULL,
  `EC_A_ELE_CONSUM_FUN` int(11) NOT NULL,
  `EC_A_ELE_CONSUM_SB` int(11) NOT NULL,
  `CREATE_DATE` datetime NOT NULL,
  `EC_ELE_PRICE` double NOT NULL,
  `UDPATE_DATE` datetime NOT NULL,
  PRIMARY KEY (`PK_TB_ID`),
  KEY `fk_TB_ACE_RES_TB_ACE_PRO_SEQ1_idx` (`FK_TB_ACE_PRO_SEQ`),
  CONSTRAINT `fk_TB_ACE_RES_TB_ACE_PRO_SEQ1` FOREIGN KEY (`FK_TB_ACE_PRO_SEQ`) REFERENCES `tb_ace_pro_seq` (`PK_TB_ID`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_ace_res`
--

LOCK TABLES `tb_ace_res` WRITE;
/*!40000 ALTER TABLE `tb_ace_res` DISABLE KEYS */;
/*!40000 ALTER TABLE `tb_ace_res` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tb_ace_scen_sub_scen`
--

DROP TABLE IF EXISTS `tb_ace_scen_sub_scen`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tb_ace_scen_sub_scen` (
  `PK_TB_ID` int(11) NOT NULL AUTO_INCREMENT,
  `FK_TB_ACE_SCENARIOS` int(11) NOT NULL,
  `FK_TB_ACE_SUB_SCEN_RES` int(11) NOT NULL,
  `OPTION_COST` double DEFAULT NULL,
  `TOTAL_COST` double DEFAULT NULL,
  `COST_PER_PIECE` double DEFAULT NULL,
  `PHYSICAL_LOA` int(11) DEFAULT NULL,
  `COGNITIVE_LOA` int(11) DEFAULT NULL,
  `TOTAL_PROCESS_TIME` int(11) DEFAULT NULL,
  `HOURS_YEAR` int(11) DEFAULT NULL,
  `LABOUR_COST` double DEFAULT NULL,
  `MAINT_COST` double DEFAULT NULL,
  `ANNUAL_SPACE_COST` double DEFAULT NULL,
  `INPUTED_DEPRECIATION` double DEFAULT NULL,
  `ACCRUED_INTEREST_COST` double DEFAULT NULL,
  `ENERGY_COST` double DEFAULT NULL,
  `MAC_HOURLY_RATE` double DEFAULT NULL,
  `MAC_COST` double DEFAULT NULL,
  `CREATE_DATE` datetime NOT NULL,
  `UDPATE_DATE` datetime NOT NULL,
  PRIMARY KEY (`PK_TB_ID`),
  KEY `fk_TB_ACE_SCEN_SUB_SCEN_TB_ACE_SCENARIOS1_idx` (`FK_TB_ACE_SCENARIOS`),
  KEY `fk_TB_ACE_SCEN_SUB_SCEN_TB_ACE_SUB_SCEN_RES1_idx` (`FK_TB_ACE_SUB_SCEN_RES`),
  CONSTRAINT `fk_TB_ACE_SCEN_SUB_SCEN_TB_ACE_SCENARIOS1` FOREIGN KEY (`FK_TB_ACE_SCENARIOS`) REFERENCES `tb_ace_scenarios` (`PK_TB_ID`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_TB_ACE_SCEN_SUB_SCEN_TB_ACE_SUB_SCEN_RES1` FOREIGN KEY (`FK_TB_ACE_SUB_SCEN_RES`) REFERENCES `tb_ace_sub_scen_res` (`PK_TB_ID`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_ace_scen_sub_scen`
--

LOCK TABLES `tb_ace_scen_sub_scen` WRITE;
/*!40000 ALTER TABLE `tb_ace_scen_sub_scen` DISABLE KEYS */;
/*!40000 ALTER TABLE `tb_ace_scen_sub_scen` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tb_ace_scenarios`
--

DROP TABLE IF EXISTS `tb_ace_scenarios`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tb_ace_scenarios` (
  `PK_TB_ID` int(11) NOT NULL AUTO_INCREMENT,
  `FK_TB_ACE_PRO_SEQ` int(11) NOT NULL,
  `CREATE_DATE` datetime NOT NULL,
  `UDPATE_DATE` datetime NOT NULL,
  PRIMARY KEY (`PK_TB_ID`),
  KEY `fk_TB_ACE_SCENARIOS_TB_ACE_PRO_SEQ1_idx` (`FK_TB_ACE_PRO_SEQ`),
  CONSTRAINT `fk_TB_ACE_SCENARIOS_TB_ACE_PRO_SEQ1` FOREIGN KEY (`FK_TB_ACE_PRO_SEQ`) REFERENCES `tb_ace_pro_seq` (`PK_TB_ID`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_ace_scenarios`
--

LOCK TABLES `tb_ace_scenarios` WRITE;
/*!40000 ALTER TABLE `tb_ace_scenarios` DISABLE KEYS */;
/*!40000 ALTER TABLE `tb_ace_scenarios` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tb_ace_status`
--

DROP TABLE IF EXISTS `tb_ace_status`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tb_ace_status` (
  `PK_ID` int(11) NOT NULL AUTO_INCREMENT,
  `STATUS` varchar(45) NOT NULL,
  PRIMARY KEY (`PK_ID`),
  UNIQUE KEY `PK_ID_UNIQUE` (`PK_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_ace_status`
--

LOCK TABLES `tb_ace_status` WRITE;
/*!40000 ALTER TABLE `tb_ace_status` DISABLE KEYS */;
/*!40000 ALTER TABLE `tb_ace_status` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tb_ace_sub_pro_lev`
--

DROP TABLE IF EXISTS `tb_ace_sub_pro_lev`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tb_ace_sub_pro_lev` (
  `PK_TB_ID` int(11) NOT NULL AUTO_INCREMENT,
  `FK_TB_ACE_PRO_SEQ` int(11) NOT NULL,
  `NAME` varchar(45) NOT NULL,
  `VAR_PRO_SEQ_ID` varchar(45) NOT NULL,
  `PRO_LEVEL` int(11) NOT NULL,
  `SELECTED` tinyint(1) DEFAULT NULL,
  `CREATE_DATE` datetime NOT NULL,
  `UDPATE_DATE` datetime NOT NULL,
  PRIMARY KEY (`PK_TB_ID`),
  KEY `fk_TB_ACE_SUB_PRO_LEV_TB_ACE_PRO_SEQ1_idx` (`FK_TB_ACE_PRO_SEQ`),
  CONSTRAINT `fk_TB_ACE_SUB_PRO_LEV_TB_ACE_PRO_SEQ1` FOREIGN KEY (`FK_TB_ACE_PRO_SEQ`) REFERENCES `tb_ace_pro_seq` (`PK_TB_ID`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_ace_sub_pro_lev`
--

LOCK TABLES `tb_ace_sub_pro_lev` WRITE;
/*!40000 ALTER TABLE `tb_ace_sub_pro_lev` DISABLE KEYS */;
/*!40000 ALTER TABLE `tb_ace_sub_pro_lev` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tb_ace_sub_scen_res`
--

DROP TABLE IF EXISTS `tb_ace_sub_scen_res`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tb_ace_sub_scen_res` (
  `PK_TB_ID` int(11) NOT NULL AUTO_INCREMENT,
  `FK_TB_ACE_SUB_SCENARIOS` int(11) NOT NULL,
  `FK_TB_ACE_RES` int(11) NOT NULL,
  `SCENARIO_NUMBER` int(11) NOT NULL,
  `PROCESS_TIME` int(11) NOT NULL,
  `ASSEMBLY_COST_PER_PIECE` double DEFAULT NULL,
  `ASSEMBLY_COSTS` double DEFAULT NULL,
  `US_PHYSICAL_LOA` int(11) DEFAULT NULL,
  `US_COGNITIVE_LOA` int(11) DEFAULT NULL,
  `CREATE_DATE` datetime NOT NULL,
  `UPDATE_DATE` datetime NOT NULL,
  PRIMARY KEY (`PK_TB_ID`),
  KEY `fk_TB_ACE_SUB_SCEN_RES_TB_ACE_SUB_SCENARIOS1_idx` (`FK_TB_ACE_SUB_SCENARIOS`),
  KEY `fk_TB_ACE_SUB_SCEN_RES_TB_ACE_RES1_idx` (`FK_TB_ACE_RES`),
  CONSTRAINT `fk_TB_ACE_SUB_SCEN_RES_TB_ACE_SUB_SCENARIOS1` FOREIGN KEY (`FK_TB_ACE_SUB_SCENARIOS`) REFERENCES `tb_ace_sub_scenarios` (`PK_TB_ID`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_TB_ACE_SUB_SCEN_RES_TB_ACE_RES1` FOREIGN KEY (`FK_TB_ACE_RES`) REFERENCES `tb_ace_res` (`PK_TB_ID`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_ace_sub_scen_res`
--

LOCK TABLES `tb_ace_sub_scen_res` WRITE;
/*!40000 ALTER TABLE `tb_ace_sub_scen_res` DISABLE KEYS */;
/*!40000 ALTER TABLE `tb_ace_sub_scen_res` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tb_ace_sub_scenarios`
--

DROP TABLE IF EXISTS `tb_ace_sub_scenarios`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tb_ace_sub_scenarios` (
  `PK_TB_ID` int(11) NOT NULL AUTO_INCREMENT,
  `FK_TB_ACE_PRO_SEQ` int(11) NOT NULL,
  `BEST_LOA_RANGE` varchar(45) NOT NULL,
  `CREATE_DATE` datetime NOT NULL,
  `UPDATE_DATE` datetime NOT NULL,
  PRIMARY KEY (`PK_TB_ID`),
  KEY `fk_TB_ACE_SUB_SCENARIOS_TB_ACE_PRO_SEQ1_idx` (`FK_TB_ACE_PRO_SEQ`),
  CONSTRAINT `fk_TB_ACE_SUB_SCENARIOS_TB_ACE_PRO_SEQ1` FOREIGN KEY (`FK_TB_ACE_PRO_SEQ`) REFERENCES `tb_ace_pro_seq` (`PK_TB_ID`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_ace_sub_scenarios`
--

LOCK TABLES `tb_ace_sub_scenarios` WRITE;
/*!40000 ALTER TABLE `tb_ace_sub_scenarios` DISABLE KEYS */;
/*!40000 ALTER TABLE `tb_ace_sub_scenarios` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping events for database 'loa_evaluation_tool'
--

--
-- Dumping routines for database 'loa_evaluation_tool'
--
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2018-07-30 18:42:08
