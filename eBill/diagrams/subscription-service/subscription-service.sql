-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema customer
-- -----------------------------------------------------
DROP SCHEMA IF EXISTS `customer` ;

-- -----------------------------------------------------
-- Schema customer
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `customer` DEFAULT CHARACTER SET utf8 ;
USE `customer` ;

-- -----------------------------------------------------
-- Table `customer`.`account`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `customer`.`account` (
  `account_id` VARCHAR(10) NOT NULL,
  `account_name` VARCHAR(255) NULL,
  PRIMARY KEY (`account_id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `customer`.`subscription_type`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `customer`.`subscription_type` (
  `type_code` CHAR(4) NOT NULL,
  `description` VARCHAR(255) NULL,
  PRIMARY KEY (`type_code`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `customer`.`account_subscription`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `customer`.`account_subscription` (
  `id` VARCHAR(45) NOT NULL,
  `account_id` VARCHAR(10) NOT NULL,
  `type_code` CHAR(4) NOT NULL,
  `subscribe` CHAR(1) NULL,
  INDEX `fk_subscription_subscription_type1_idx` (`type_code` ASC) VISIBLE,
  PRIMARY KEY (`id`),
  CONSTRAINT `fk_subscription_account`
    FOREIGN KEY (`account_id`)
    REFERENCES `customer`.`account` (`account_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_subscription_subscription_type1`
    FOREIGN KEY (`type_code`)
    REFERENCES `customer`.`subscription_type` (`type_code`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `customer`.`contact_type`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `customer`.`contact_type` (
  `type_code` CHAR(4) NOT NULL,
  `description` VARCHAR(255) NULL,
  PRIMARY KEY (`type_code`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `customer`.`account_contact`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `customer`.`account_contact` (
  `id` VARCHAR(45) NOT NULL,
  `account_id` VARCHAR(10) NOT NULL,
  `type_code` CHAR(4) NOT NULL,
  `value` VARCHAR(255) NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_account_contact_account1_idx` (`account_id` ASC) VISIBLE,
  CONSTRAINT `fk_account_type_contact_type1`
    FOREIGN KEY (`type_code`)
    REFERENCES `customer`.`contact_type` (`type_code`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_account_contact_account1`
    FOREIGN KEY (`account_id`)
    REFERENCES `customer`.`account` (`account_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;

-- -----------------------------------------------------
-- Data for table `customer`.`account`
-- -----------------------------------------------------
START TRANSACTION;
USE `customer`;
INSERT INTO `customer`.`account` (`account_id`, `account_name`) VALUES ('1000000000', 'Customer 1');
INSERT INTO `customer`.`account` (`account_id`, `account_name`) VALUES ('1000000001', 'Customer 2');

COMMIT;


-- -----------------------------------------------------
-- Data for table `customer`.`subscription_type`
-- -----------------------------------------------------
START TRANSACTION;
USE `customer`;
INSERT INTO `customer`.`subscription_type` (`type_code`, `description`) VALUES ('EBIL', 'Electronic sending of bill');
INSERT INTO `customer`.`subscription_type` (`type_code`, `description`) VALUES ('OUTN', 'Outage notification');
INSERT INTO `customer`.`subscription_type` (`type_code`, `description`) VALUES ('MOAP', 'MobileAP application');

COMMIT;


-- -----------------------------------------------------
-- Data for table `customer`.`account_subscription`
-- -----------------------------------------------------
START TRANSACTION;
USE `customer`;
INSERT INTO `customer`.`account_subscription` (`id`, `account_id`, `type_code`, `subscribe`) VALUES ('1', '1000000000', 'EBIL', 'Y');
INSERT INTO `customer`.`account_subscription` (`id`, `account_id`, `type_code`, `subscribe`) VALUES ('2', '1000000000', 'MOAP', 'Y');

COMMIT;


-- -----------------------------------------------------
-- Data for table `customer`.`contact_type`
-- -----------------------------------------------------
START TRANSACTION;
USE `customer`;
INSERT INTO `customer`.`contact_type` (`type_code`, `description`) VALUES ('EADD', 'Email Address');
INSERT INTO `customer`.`contact_type` (`type_code`, `description`) VALUES ('SMSN', 'SMS Number');
INSERT INTO `customer`.`contact_type` (`type_code`, `description`) VALUES ('PHON', 'Phone Number');

COMMIT;


-- -----------------------------------------------------
-- Data for table `customer`.`account_contact`
-- -----------------------------------------------------
START TRANSACTION;
USE `customer`;
INSERT INTO `customer`.`account_contact` (`id`, `account_id`, `type_code`, `value`) VALUES ('1', '1000000000', 'SMSN', '+639999069057');
INSERT INTO `customer`.`account_contact` (`id`, `account_id`, `type_code`, `value`) VALUES ('2', '1000000000', 'EADD', 'sherwin.amihan@aboitiz.com');
INSERT INTO `customer`.`account_contact` (`id`, `account_id`, `type_code`, `value`) VALUES ('3', '1000000000', 'EADD', 'sherwinamihan@gmail.com');

COMMIT;

