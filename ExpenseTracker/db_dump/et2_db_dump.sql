-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema et2_db
-- -----------------------------------------------------
-- Expense Tracker DB
DROP SCHEMA IF EXISTS `et2_db` ;

-- -----------------------------------------------------
-- Schema et2_db
--
-- Expense Tracker DB
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `et2_db` DEFAULT CHARACTER SET utf8mb4 ;
USE `et2_db` ;

-- -----------------------------------------------------
-- Table `et2_db`.`user`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `et2_db`.`user` ;

CREATE TABLE IF NOT EXISTS `et2_db`.`user` (
  `user_id` INT NOT NULL AUTO_INCREMENT,
  `username` VARCHAR(16) NOT NULL,
  `password` VARCHAR(32) NOT NULL,
  `create_dttm` DATETIME NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`user_id`),
  UNIQUE INDEX `username_UNIQUE` (`username` ASC) VISIBLE,
  UNIQUE INDEX `user_id_UNIQUE` (`user_id` ASC) VISIBLE);


-- -----------------------------------------------------
-- Table `et2_db`.`expense_category`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `et2_db`.`expense_category` ;

CREATE TABLE IF NOT EXISTS `et2_db`.`expense_category` (
  `category_id` VARCHAR(4) NOT NULL,
  `name` VARCHAR(32) NOT NULL,
  PRIMARY KEY (`category_id`),
  UNIQUE INDEX `category_id_UNIQUE` (`category_id` ASC) VISIBLE);


-- -----------------------------------------------------
-- Table `et2_db`.`expense`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `et2_db`.`expense` ;

CREATE TABLE IF NOT EXISTS `et2_db`.`expense` (
  `expense_id` VARCHAR(36) NOT NULL,
  `user_id` INT NOT NULL,
  `category_id` VARCHAR(4) NOT NULL,
  `description` VARCHAR(64) NOT NULL,
  `amount` DECIMAL(13,2) NOT NULL,
  `expense_dt` DATE NOT NULL,
  `create_dttm` DATETIME NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`expense_id`),
  UNIQUE INDEX `expense_id_UNIQUE` (`expense_id` ASC) VISIBLE,
  INDEX `user_id_fk_idx` (`user_id` ASC) VISIBLE,
  INDEX `category_id_expense_fk_idx` (`category_id` ASC) VISIBLE,
  CONSTRAINT `user_id_expense_fk`
    FOREIGN KEY (`user_id`)
    REFERENCES `et2_db`.`user` (`user_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `category_id_expense_fk`
    FOREIGN KEY (`category_id`)
    REFERENCES `et2_db`.`expense_category` (`category_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;

-- -----------------------------------------------------
-- Data for table `et2_db`.`user`
-- -----------------------------------------------------
START TRANSACTION;
USE `et2_db`;
INSERT INTO `et2_db`.`user` (`user_id`, `username`, `password`, `create_dttm`) VALUES (DEFAULT, 'user0', 'user@123', DEFAULT);

COMMIT;


-- -----------------------------------------------------
-- Data for table `et2_db`.`expense_category`
-- -----------------------------------------------------
START TRANSACTION;
USE `et2_db`;
INSERT INTO `et2_db`.`expense_category` (`category_id`, `name`) VALUES ('FOOD', 'Food');
INSERT INTO `et2_db`.`expense_category` (`category_id`, `name`) VALUES ('BILL', 'Bills');
INSERT INTO `et2_db`.`expense_category` (`category_id`, `name`) VALUES ('TRPO', 'Transportation');
INSERT INTO `et2_db`.`expense_category` (`category_id`, `name`) VALUES ('MISC', 'Miscellaneous');

COMMIT;

