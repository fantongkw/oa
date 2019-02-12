-- MySQL Script generated by MySQL Workbench
-- Wed May 16 16:20:07 2018
-- Model: New Model    Version: 1.0
-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

-- -----------------------------------------------------
-- Schema oa
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema oa
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `oa` DEFAULT CHARACTER SET utf8 ;
USE `oa` ;

-- -----------------------------------------------------
-- Table `oa`.`Department`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `oa`.`Department` (
  `id` BIGINT(20) NOT NULL,
  `parent_id` BIGINT(20) NOT NULL,
  `name` BIGINT(20) NOT NULL,
  `description` BIGINT(20) NOT NULL,
  PRIMARY KEY (`id`, `parent_id`),
  INDEX `fk_Parent_Department_idx` (`parent_id` ASC),
  CONSTRAINT `fk_Parent_Department`
    FOREIGN KEY (`parent_id`)
    REFERENCES `oa`.`Department` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `oa`.`Member`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `oa`.`User` (
  `id` BIGINT(20) NOT NULL,
  `loginName` VARCHAR(255) NULL,
  `password` VARCHAR(255) NULL,
  `name` VARCHAR(255) NULL,
  `gender` VARCHAR(255) NULL,
  `phoneNumber` VARCHAR(255) NULL,
  `email` VARCHAR(255) NULL,
  `description` VARCHAR(255) NULL,
  `Department_id` BIGINT(20) NOT NULL,
  PRIMARY KEY (`id`, `Department_id`),
  INDEX `fk_User_Department1_idx` (`Department_id` ASC),
  CONSTRAINT `fk_User_Department1`
    FOREIGN KEY (`Department_id`)
    REFERENCES `oa`.`Department` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `oa`.`Role`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `oa`.`Role` (
  `id` BIGINT(20) NOT NULL,
  `name` VARCHAR(255) NULL,
  `description` VARCHAR(255) NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `oa`.`UserHasRole`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `oa`.`User_has_Role` (
  `User_id` BIGINT(20) NOT NULL,
  `Role_id` BIGINT(20) NOT NULL,
  PRIMARY KEY (`User_id`, `Role_id`),
  INDEX `fk_User_has_Role_Role1_idx` (`Role_id` ASC),
  INDEX `fk_User_has_Role_User_idx` (`User_id` ASC),
  CONSTRAINT `fk_User_has_Role_User`
    FOREIGN KEY (`User_id`)
    REFERENCES `oa`.`User` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_User_has_Role_Role1`
    FOREIGN KEY (`Role_id`)
    REFERENCES `oa`.`Role` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;