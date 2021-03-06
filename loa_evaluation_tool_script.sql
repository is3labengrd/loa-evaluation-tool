-- MySQL Script generated by MySQL Workbench
-- 10/02/18 14:59:05
-- Model: New Model    Version: 1.0
-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

-- -----------------------------------------------------
-- Schema loa_evaluation_tool
-- -----------------------------------------------------
DROP SCHEMA IF EXISTS `loa_evaluation_tool` ;

-- -----------------------------------------------------
-- Schema loa_evaluation_tool
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `loa_evaluation_tool` DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci ;
USE `loa_evaluation_tool` ;

-- -----------------------------------------------------
-- Table `loa_evaluation_tool`.`TB_ACE_PRO_SEQ`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `loa_evaluation_tool`.`TB_ACE_PRO_SEQ` ;

CREATE TABLE IF NOT EXISTS `loa_evaluation_tool`.`TB_ACE_PRO_SEQ` (
  `PK_TB_ID` INT NOT NULL AUTO_INCREMENT,
  `NAME` VARCHAR(500) NOT NULL,
  `N_LOWER_LEVEL_SUB_PRO` INT NOT NULL,
  `VAR_PRO_SEQ_ID` VARCHAR(45) NOT NULL,
  `CREATE_DATE` DATETIME NOT NULL,
  `UPDATE_DATE` DATETIME NOT NULL,
  PRIMARY KEY (`PK_TB_ID`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `loa_evaluation_tool`.`TB_ACE_PHY_LOA`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `loa_evaluation_tool`.`TB_ACE_PHY_LOA` ;

CREATE TABLE IF NOT EXISTS `loa_evaluation_tool`.`TB_ACE_PHY_LOA` (
  `PK_TB_ID` INT NOT NULL AUTO_INCREMENT,
  `CODE` INT NOT NULL,
  `LOA` VARCHAR(500) NOT NULL,
  PRIMARY KEY (`PK_TB_ID`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `loa_evaluation_tool`.`TB_ACE_COG_LOA`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `loa_evaluation_tool`.`TB_ACE_COG_LOA` ;

CREATE TABLE IF NOT EXISTS `loa_evaluation_tool`.`TB_ACE_COG_LOA` (
  `PK_TB_ID` INT NOT NULL AUTO_INCREMENT,
  `CODE` INT NOT NULL,
  `LOA` VARCHAR(500) NOT NULL,
  PRIMARY KEY (`PK_TB_ID`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `loa_evaluation_tool`.`TB_ACE_PRO_LOA_INF`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `loa_evaluation_tool`.`TB_ACE_PRO_LOA_INF` ;

CREATE TABLE IF NOT EXISTS `loa_evaluation_tool`.`TB_ACE_PRO_LOA_INF` (
  `PK_TB_ID` INT NOT NULL AUTO_INCREMENT,
  `FK_TB_ACE_PHY_LOA` INT NULL,
  `FK_TB_ACE_COG_LOA` INT NULL,
  `POSSIBILITY` TINYINT(1) NOT NULL,
  `BEST_RANGE` VARCHAR(45) NOT NULL,
  `LOA_TYPE` ENUM('P','C') NOT NULL,
  `CREATE_DATE` DATETIME NOT NULL,
  `UPDATE_DATE` DATETIME NOT NULL,
  `FK_TB_ACE_SUB_PRO_LEV` INT(11) NOT NULL,
  PRIMARY KEY (`PK_TB_ID`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `loa_evaluation_tool`.`TB_ACE_SUB_PRO_LEV`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `loa_evaluation_tool`.`TB_ACE_SUB_PRO_LEV` ;

CREATE TABLE IF NOT EXISTS `loa_evaluation_tool`.`TB_ACE_SUB_PRO_LEV` (
  `PK_TB_ID` INT NOT NULL AUTO_INCREMENT,
  `FK_TB_ACE_PRO_SEQ` INT NULL,
  `NAME` VARCHAR(45) NOT NULL,
  `PRO_LEVEL` INT NULL,
  `VAR_PRO_SEQ_ID` VARCHAR(45) NOT NULL,
  `CREATE_DATE` DATETIME NOT NULL,
  `UDPATE_DATE` DATETIME NOT NULL,
  PRIMARY KEY (`PK_TB_ID`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `loa_evaluation_tool`.`TB_ACE_LOA_CRI_MAT`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `loa_evaluation_tool`.`TB_ACE_LOA_CRI_MAT` ;

CREATE TABLE IF NOT EXISTS `loa_evaluation_tool`.`TB_ACE_LOA_CRI_MAT` (
  `PK_TB_ID` INT NOT NULL AUTO_INCREMENT,
  `FK_TB_ACE_SUB_PRO_LEV` INT NOT NULL,
  `DS_STABLE` INT NULL,
  `DS_REDUCED_STABILITY` INT NULL,
  `DS_HANDLY_STABLE` INT NULL,
  `DS_UNSTABLE` INT NULL,
  `ST_INSENSITIVE` INT NULL,
  `ST_HARDLY_SENSITIVE` INT NULL,
  `ST_SENSITIVE` INT NULL,
  `ST_VERY_SENSITIVE` INT NULL,
  `GR_EX_GRIP_SURFACE` INT NULL,
  `GR_INT_GRIP_SURFACE` INT NULL,
  `GR_MAGNETIC_GRIPPER` INT NULL,
  `GR_FAB_CLOSURE_GRIPPER` INT NULL,
  `NV_NO_FURTHER_VARIANTS` INT NULL,
  `NV_ONE_FURTHER_VARIANTS` INT NULL,
  `NV_TWO_FURTHER_VARIANTS` INT NULL,
  `NV_MORE_FURTHER_VARIANTS` INT NULL,
  `SP_UP` INT NULL,
  `SP_MORE` INT NULL,
  `SP_STABLE_UNSTABLE` INT NULL,
  `SP_ONLY_UNSTABLE` INT NULL,
  `SM_ROT_SYMMETRICAL` INT NULL,
  `SM_AR_SYMMETRY` INT NULL,
  `SM_MA_ASYMMETRICAL` INT NULL,
  `SM_SE_SYMMETRICAL` INT NULL,
  `HA_NONE` INT NULL,
  `HA_STI_JAM_POSSIBLE` INT NULL,
  `HA_COM_PEN_POSSIBILE` INT NULL,
  `HA_SEE_SYMMETRICAL` INT NULL,
  `FJ_NEVER` INT NULL,
  `FJ_OCASIONALLY` INT NULL,
  `FJ_RARELY` INT NULL,
  `FJ_OFTEN` INT NULL,
  `AC_VERY_GOOD` INT NULL,
  `AC_GOOD` INT NULL,
  `AC_SATISFACTORY` INT NULL,
  `AC_SUFFICIENT` INT NULL,
  `OR_NO_AXES` INT NULL,
  `OR_ONE_AXIS` INT NULL,
  `OR_TWO_AXES` INT NULL,
  `OR_THREE_AXES` INT NULL,
  `JM_LINEAR` INT NULL,
  `JM_ROTATION` INT NULL,
  `JM_LINEAR_ROTATORY` INT NULL,
  `JM_PATH_MOVEMENT` INT NULL,
  `JF_NONE` INT NULL,
  `JF_LOW` INT NULL,
  `JF_MEDIUM` INT NULL,
  `JF_HIGH` INT NULL,
  `JA_JOIN_BASIC_COMP` INT NULL,
  `JA_JOIN_COMP` INT NULL,
  `JA_BASIC_COMP` INT NULL,
  `JA_MORE_COMP` INT NULL,
  `NC_ONE_BASIC_COMP` INT NULL,
  `NC_TWO_BASIC_COMP` INT NULL,
  `NC_THREE_BASIC_COMP` INT NULL,
  `NC_MORE_COMP` INT NULL,
  `JS_SEC_ALL_DIRECTION` INT NULL,
  `JS_GRAVITY_FIT` INT NULL,
  `JS_GRAVITY_RUBBING` INT NULL,
  `JS_ADDITIONAL_SEC` INT NULL,
  `SO_NONE` INT NULL,
  `SO_ONE` INT NULL,
  `SO_TWO` INT NULL,
  `SO_MORE` INT NULL,
  `EC_A_ELE_CONSUM_FUN` INT NULL,
  `CREATE_DATE` DATETIME NOT NULL,
  `UPDATE_DATE` DATETIME NOT NULL,
  PRIMARY KEY (`PK_TB_ID`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `loa_evaluation_tool`.`TB_ACE_RES`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `loa_evaluation_tool`.`TB_ACE_RES` ;

CREATE TABLE IF NOT EXISTS `loa_evaluation_tool`.`TB_ACE_RES` (
  `PK_TB_ID` INT NOT NULL AUTO_INCREMENT,
  `NAME` VARCHAR(500) NOT NULL,
  `LOA_PHYSICAL` INT NOT NULL,
  `LOA_COGNITIVE` INT NOT NULL,
  `LC_N_OPER_MACHINE` DOUBLE NOT NULL,
  `MC_A_MAINT_COSTS` DOUBLE NOT NULL,
  `MC_A_MAINT_COSTS_PERC` FLOAT NOT NULL,
  `RC_INST_SURFACE` DOUBLE NOT NULL,
  `RC_COSTS_M_MONTH` DOUBLE NOT NULL,
  `ID_MAC_PURH_VALUE` DOUBLE NOT NULL,
  `ID_MAC_SALES_VALUE` DOUBLE NOT NULL,
  `ID_ECO_USEFULL_LIFE` INT NOT NULL,
  `IC_INTER_RATE` FLOAT NOT NULL,
  `EC_A_ELE_CONSUM_FUN` INT NOT NULL,
  `EC_A_ELE_CONSUM_SB` INT NOT NULL,
  `EC_ELE_PRICE` DOUBLE NOT NULL,
  `VAR_RES` TINYINT(1) NULL,
  `VAR_CLASS` VARCHAR(500) NULL,
  `CREATE_DATE` DATETIME NOT NULL,
  `UPDATE_DATE` DATETIME NOT NULL,
  PRIMARY KEY (`PK_TB_ID`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `loa_evaluation_tool`.`TB_ACE_PRO_PLAN`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `loa_evaluation_tool`.`TB_ACE_PRO_PLAN` ;

CREATE TABLE IF NOT EXISTS `loa_evaluation_tool`.`TB_ACE_PRO_PLAN` (
  `PK_TB_ID` INT NOT NULL AUTO_INCREMENT,
  `FK_TB_ACE_PRO_SEQ` INT NOT NULL,
  `N_PROD_PIECE_PER_HOURS` DOUBLE NOT NULL,
  `CREATE_DATE` DATETIME NOT NULL,
  `UPDATE_DATE` DATETIME NOT NULL,
  PRIMARY KEY (`PK_TB_ID`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `loa_evaluation_tool`.`TB_ACE_PRO_SPEC`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `loa_evaluation_tool`.`TB_ACE_PRO_SPEC` ;

CREATE TABLE IF NOT EXISTS `loa_evaluation_tool`.`TB_ACE_PRO_SPEC` (
  `PK_TB_ID` INT NOT NULL AUTO_INCREMENT,
  `FK_TB_ACE_SUB_PRO_LEV` INT NOT NULL,
  `N_SHIPTS_DAY` FLOAT NOT NULL,
  `HOURS_SHIFT` FLOAT NOT NULL,
  `WORKING_DAYS_YEAR` FLOAT NOT NULL,
  `PROP_W_C_PER_HOURS` DOUBLE NOT NULL,
  `CREATE_DATE` DATETIME NOT NULL,
  `UPDATE_DATE` DATETIME NOT NULL,
  PRIMARY KEY (`PK_TB_ID`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `loa_evaluation_tool`.`TB_ACE_SUB_SCENARIOS`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `loa_evaluation_tool`.`TB_ACE_SUB_SCENARIOS` ;

CREATE TABLE IF NOT EXISTS `loa_evaluation_tool`.`TB_ACE_SUB_SCENARIOS` (
  `PK_TB_ID` INT NOT NULL AUTO_INCREMENT,
  `FK_TB_ACE_PRO_SEQ` INT NOT NULL,
  `FK_TB_ACE_SUB_PRO_LEV` INT NOT NULL,
  `FK_TB_ACE_RES` INT NOT NULL,
  `SCENARIO_NUMBER` INT NOT NULL,
  `PROCESS_TIME` INT NOT NULL,
  `ASSEMBLY_COST_PER_PIECE` DOUBLE NULL,
  `ASSEMBLY_COSTS` DOUBLE NULL,
  `US_PHYSICAL_LOA` INT NULL,
  `US_COGNITIVE_LOA` INT NULL,
  `HOURS_PER_YEARS` INT(11) NULL,
  `N_PROD_PIECES` DOUBLE NULL,
  `LABOUR_COST` DOUBLE NULL,
  `ENERGY_COST` DOUBLE NULL,
  `MAINT_COST` DOUBLE NULL,
  `ANNUAL_SPACE_COST` DOUBLE NULL,
  `INPUTED_DEPRECIATION` DOUBLE NULL,
  `RATE_OF_PART` DOUBLE NULL,
  `ACCRUED_INT_COSTS` DOUBLE NULL,
  `VAR_COST_TOTAL` DOUBLE NULL,
  `FIXED_COST_TOTAL` DOUBLE NULL,
  `RES_RECAL` BOOLEAN NULL,
  `RES_SORTING` TINYINT(1) NULL,
  `COGNITIVE_TIME` INT NOT NULL,
  `PHYSICAL_TIME` INT NOT NULL,
  `CREATE_DATE` DATETIME NOT NULL,
  `UPDATE_DATE` DATETIME NOT NULL,
  PRIMARY KEY (`PK_TB_ID`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `loa_evaluation_tool`.`TB_ACE_SCENARIOS`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `loa_evaluation_tool`.`TB_ACE_SCENARIOS` ;

CREATE TABLE IF NOT EXISTS `loa_evaluation_tool`.`TB_ACE_SCENARIOS` (
  `PK_TB_ID` INT NOT NULL AUTO_INCREMENT,
  `TOTAL_COST` DOUBLE NULL,
  `COST_PER_PIECE` DOUBLE NULL,
  `TOTAL_PROCESS_TIME` INT NULL,
  `HOURS_YEAR` INT NULL,
  `LABOUR_COST` DOUBLE NULL,
  `MAINT_COST` DOUBLE NULL,
  `ANNUAL_SPACE_COST` DOUBLE NULL,
  `INPUTED_DEPRECIATION` DOUBLE NULL,
  `ACCRUED_INTEREST_COST` DOUBLE NULL,
  `ENERGY_COST` DOUBLE NULL,
  `VAR_COSTS_PER_UNIT` DOUBLE NULL,
  `MAC_COST` DOUBLE NULL,
  `CREATE_DATE` DATETIME NOT NULL,
  `UDPATE_DATE` DATETIME NOT NULL,
  `FK_TB_ACE_PRO_SEQ` INT NOT NULL,
  `SCENARIO_NUMBER` INT NOT NULL,
  `TOT_WEIGHTED_PHYSICAL_LOA` DOUBLE NULL,
  `TOT_WEIGHTED_COGNITIVE_LOA` DOUBLE NULL,
  `PROD_UNITS_PER_YEARS` INT NULL,
  `ASS_COSTS_PER_UNITS` DOUBLE NULL,
  `TOTAL_ASS_COSTS` DOUBLE NULL,
  `RES_RECAL` TINYINT(1) NULL,
  PRIMARY KEY (`PK_TB_ID`))
ENGINE = InnoDB;

-- -----------------------------------------------------
-- Table `loa_evaluation_tool`.`TB_ACE_SCENARIO_RES`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `loa_evaluation_tool`.`TB_ACE_SCENARIO_RES` ;

CREATE TABLE IF NOT EXISTS `loa_evaluation_tool`.`TB_ACE_SCENARIO_RES` (
  `PK_TB_ID` INT NOT NULL AUTO_INCREMENT,
  `FK_TB_ACE_SUB_PRO_LEV` INT NOT NULL,
  `FK_TB_ACE_RES` INT NOT NULL,
  `CREATE_DATE` DATETIME NOT NULL,
  `UPDATE_DATE` DATETIME NOT NULL,
  `FK_TB_ACE_SCENARIOS` INT NOT NULL,
  `OPTIONAL_COST` DOUBLE NOT NULL,
  `WEIGHTED_PHYSICAL_LOA` DOUBLE NOT NULL,
  `WEIGHTED_COGNITIVE_LOA` DOUBLE NOT NULL,
  `NUMBER_SELECTED` INT NOT NULL,
  `FIXED_COST` TINYINT(1) NULL,
  PRIMARY KEY (`PK_TB_ID`))
ENGINE = InnoDB;

-- -----------------------------------------------------
-- Table `loa_evaluation_tool`.`TB_ACE_LOA_CRI_MAT_C`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `loa_evaluation_tool`.`TB_ACE_LOA_CRI_MAT_C` ;

CREATE TABLE IF NOT EXISTS `loa_evaluation_tool`.`TB_ACE_LOA_CRI_MAT_C` (
  `PK_TB_ID` INT NOT NULL AUTO_INCREMENT,
  `FK_TB_ACE_SUB_PRO_LEV` INT NOT NULL,
  `TC_H` INT NULL,
  `TC_M` INT NULL,
  `TC_L` INT NULL,
  `TC_N` INT NULL,
  `RE_N` INT NULL,
  `RE_L` INT NULL,
  `RE_M` INT NULL,
  `RE_H` INT NULL,
  `FC_H` INT NULL,
  `FC_M` INT NULL,
  `FC_L` INT NULL,
  `FC_N` INT NULL,
  `LV_H` INT NULL,
  `LV_M` INT NULL,
  `LV_L` INT NULL,
  `LV_N` INT NULL,
  `RD_H` INT NULL,
  `RD_M` INT NULL,
  `RD_L` INT NULL,
  `RD_N` INT NULL,
  `AD_L` INT NULL,
  `AD_A` INT NULL,
  `AD_S` INT NULL,
  `AD_N` INT NULL,
  `AS_L` INT NULL,
  `AS_A` INT NULL,
  `AS_S` INT NULL,
  `AS_N` INT NULL,
  `RM_N` INT NULL,
  `RM_S` INT NULL,
  `RM_M` INT NULL,
  `RM_L` INT NULL,
  `US_N` INT NULL,
  `US_OC` INT NULL,
  `US_R` INT NULL,
  `US_OF` INT NULL,
  `SC_H` INT NULL,
  `SC_M` INT NULL,
  `SC_L` INT NULL,
  `SC_N` INT NULL,
  `CREATE_DATE` DATETIME NOT NULL,
  `UPDATE_DATE` DATETIME NOT NULL,
  PRIMARY KEY (`PK_TB_ID`))
ENGINE = InnoDB;

-- -----------------------------------------------------
-- Table `loa_evaluation_tool`.`TB_ACE_SUB_PRO_LEV_RES`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `loa_evaluation_tool`.`TB_ACE_SUB_PRO_LEV_RES` ;

CREATE TABLE IF NOT EXISTS `loa_evaluation_tool`.`TB_ACE_SUB_PRO_LEV_RES` (
  `PK_TB_ID` INT NOT NULL AUTO_INCREMENT,
  `FK_TB_ACE_RES` INT NOT NULL,
  `FK_TB_ACE_SUB_PRO_LEV` INT NOT NULL,
  `CREATE_DATE` DATETIME NOT NULL,
  `UPDATE_DATE` DATETIME NULL,
  PRIMARY KEY (`PK_TB_ID`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `loa_evaluation_tool`.`TB_ACE_PRO_SEC_ORDERED`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `loa_evaluation_tool`.`TB_ACE_PRO_SEC_ORDERED` ;

CREATE TABLE IF NOT EXISTS `loa_evaluation_tool`.`TB_ACE_PRO_SEC_ORDERED` (
  `PK_TB_ID` INT(11) NOT NULL AUTO_INCREMENT,
  `FK_TB_ACE_PRO_SEQ` INT(11) NOT NULL,
  `FK_TB_ACE_SUB_PRO_LEV1` INT(11) NULL,
  `FK_TB_ACE_SUB_PRO_LEV2` INT(11) NULL,
  `FK_TB_ACE_SUB_PRO_LEV3` INT(11) NULL,
  `FK_TB_ACE_SUB_PRO_LEV4` INT(11) NULL,
  `FK_TB_ACE_SUB_PRO_LEV5` INT(11) NULL,
  `CREATE_DATE` DATETIME NOT NULL,
  `UPDATE_DATE` DATETIME NOT NULL,
  PRIMARY KEY (`PK_TB_ID`))
ENGINE = InnoDB;

-- -----------------------------------------------------
-- Table `loa_evaluation_tool`.`TB_ACE_MIN_SAT`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `loa_evaluation_tool`.`TB_ACE_MIN_SAT` ;

CREATE TABLE IF NOT EXISTS `loa_evaluation_tool`.`TB_ACE_MIN_SAT` (
  `PK_TB_ID` INT NOT NULL AUTO_INCREMENT,
  `FK_TB_ACE_PRO_SEQ` INT NOT NULL,
  `FK_TB_ACE_SUB_PRO_LEV` INT NOT NULL,
  `MIN_PHY_SAT` INT NOT NULL,
  `MIN_COG_SAT` INT NOT NULL,
  `CREATE_DATE` DATETIME NOT NULL,
  `UPDATE_DATE` DATETIME NOT NULL,
  PRIMARY KEY (`PK_TB_ID`))
ENGINE = InnoDB;

SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;

-- -----------------------------------------------------
-- INSERT STATEMENT `loa_evaluation_tool`.`TB_ACE_PHY_LOA` 
-- -----------------------------------------------------

INSERT INTO `loa_evaluation_tool`.`TB_ACE_PHY_LOA` (`PK_TB_ID`, `CODE`, `LOA`) VALUES ('1', '1001', 'Totally Manual – Totally manual work, no toll are used, only the users own muscle power');
INSERT INTO `loa_evaluation_tool`.`TB_ACE_PHY_LOA` (`PK_TB_ID`, `CODE`, `LOA`) VALUES ('2', '1002', 'Static Hand Tool – Manual work with support of static tool. e.g.  Screwdriver');
INSERT INTO `loa_evaluation_tool`.`TB_ACE_PHY_LOA` (`PK_TB_ID`, `CODE`, `LOA`) VALUES ('3', '1003', 'Flexible Hand Tool – Manual work with support of flexible tool. e.g. Adjustable spanner');
INSERT INTO `loa_evaluation_tool`.`TB_ACE_PHY_LOA` (`PK_TB_ID`, `CODE`, `LOA`) VALUES ('4', '1004', 'Automated Hand Tool – Manual work with support of automated tool. e.g. Hydraulic bolt driver');
INSERT INTO `loa_evaluation_tool`.`TB_ACE_PHY_LOA` (`PK_TB_ID`, `CODE`, `LOA`) VALUES ('5', '1005', 'Static Machine/Workstation – Automatic work by machine that is designed for a specific task. e.g. Lathe');
INSERT INTO `loa_evaluation_tool`.`TB_ACE_PHY_LOA` (`PK_TB_ID`, `CODE`, `LOA`) VALUES ('6', '1006', 'Flexible Machine/Workstation – Automatic work by machine that can be reconfigured for different tasks. e.g. CNC – Machine');
INSERT INTO `loa_evaluation_tool`.`TB_ACE_PHY_LOA` (`PK_TB_ID`, `CODE`, `LOA`) VALUES ('7', '1007', 'Totally Automatic – Totally automatic work, the machine solves all deviations or problems that occur by itself');

-- -----------------------------------------------------
-- INSERT STATEMENT `loa_evaluation_tool`.`TB_ACE_PHY_LOA` 
-- -----------------------------------------------------

INSERT INTO `loa_evaluation_tool`.`TB_ACE_COG_LOA` (`PK_TB_ID`, `CODE`, `LOA`) VALUES ('1', '2001', 'Operator knowledge and experience based information and control');
INSERT INTO `loa_evaluation_tool`.`TB_ACE_COG_LOA` (`PK_TB_ID`, `CODE`, `LOA`) VALUES ('2', '2002', 'Information is provided, but operator must analyze current step and decide what information to follow');
INSERT INTO `loa_evaluation_tool`.`TB_ACE_COG_LOA` (`PK_TB_ID`, `CODE`, `LOA`) VALUES ('3', '2003', 'Detail of guided information is provide step by step procedure, operator need to monitor current step and follow procedure');
INSERT INTO `loa_evaluation_tool`.`TB_ACE_COG_LOA` (`PK_TB_ID`, `CODE`, `LOA`) VALUES ('4', '2004', 'Technology starts monitoring information and process. Operator make decision and action');
INSERT INTO `loa_evaluation_tool`.`TB_ACE_COG_LOA` (`PK_TB_ID`, `CODE`, `LOA`) VALUES ('5', '2005', 'Technology monitors, analyze and provide decision, operator makes final decision and action');
INSERT INTO `loa_evaluation_tool`.`TB_ACE_COG_LOA` (`PK_TB_ID`, `CODE`, `LOA`) VALUES ('6', '2006', 'Technology responsible for majority of tasks, both technology and operator can set action but technology override');
INSERT INTO `loa_evaluation_tool`.`TB_ACE_COG_LOA` (`PK_TB_ID`, `CODE`, `LOA`) VALUES ('7', '2007', 'System handles all information and control, and communicates among another');
