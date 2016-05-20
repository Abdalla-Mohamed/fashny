-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

-- -----------------------------------------------------
-- Schema fashny
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema fashny
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `fashny` DEFAULT CHARACTER SET utf8 ;
USE `fashny` ;

-- -----------------------------------------------------
-- Table `fashny`.`resouce`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `fashny`.`resouce` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `path` VARCHAR(100) NOT NULL,
  `description` VARCHAR(500) NULL,
  `type` INT NULL DEFAULT 0,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `path_UNIQUE` (`path` ASC))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `fashny`.`admin`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `fashny`.`admin` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `password` VARCHAR(45) NOT NULL,
  `name` VARCHAR(45) NOT NULL,
  `super` TINYINT(1) NOT NULL DEFAULT 0,
  `activated` TINYINT(1) NOT NULL DEFAULT 1,
  `last_seen` TIMESTAMP(5) NOT NULL,
  `email` VARCHAR(30) NOT NULL,
  `phone` VARCHAR(20) NOT NULL,
  `profile_Pic` INT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `name_UNIQUE` (`name` ASC),
  UNIQUE INDEX `email_UNIQUE` (`email` ASC),
  INDEX `fk_admin_resouce1_idx` (`profile_Pic` ASC),
  CONSTRAINT `fk_admin_resouce1`
    FOREIGN KEY (`profile_Pic`)
    REFERENCES `fashny`.`resouce` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `fashny`.`client`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `fashny`.`client` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL,
  `email` VARCHAR(30) NOT NULL,
  `contact_email` VARCHAR(45) NULL,
  `password` VARCHAR(45) NOT NULL,
  `address` VARCHAR(45) NOT NULL,
  `birth_date` DATE NOT NULL,
  `gender` SMALLINT(2) NOT NULL,
  `marital_status` SMALLINT(2) NOT NULL,
  `last_seen` TIMESTAMP(5) NOT NULL,
  `mobile1` VARCHAR(20) NOT NULL,
  `mobile2` VARCHAR(20) NULL,
  `phone` VARCHAR(20) NULL,
  `profile_Pic` INT NULL,
  `active` TINYINT(1) NULL DEFAULT 1,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `email_id_UNIQUE` (`email` ASC),
  INDEX `fk_client_resouce1_idx` (`profile_Pic` ASC),
  CONSTRAINT `fk_client_resouce1`
    FOREIGN KEY (`profile_Pic`)
    REFERENCES `fashny`.`resouce` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `fashny`.`tag`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `fashny`.`tag` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL,
  `vaidated` TINYINT(1) NOT NULL DEFAULT 1,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `name_UNIQUE` (`name` ASC))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `fashny`.`place`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `fashny`.`place` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL,
  `address` VARCHAR(100) NOT NULL,
  `description` TEXT NULL,
  `work_hours` VARCHAR(100) NOT NULL,
  `lang` DOUBLE NOT NULL,
  `attd` DOUBLE NOT NULL,
  `validated` TINYINT(1) NOT NULL DEFAULT 0,
  `active` TINYINT(1) NULL DEFAULT 1,
  UNIQUE INDEX `name_UNIQUE` (`name` ASC),
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `fashny`.`company`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `fashny`.`company` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL,
  `password` VARCHAR(45) NOT NULL,
  `website` VARCHAR(100) NULL,
  `address` VARCHAR(100) NOT NULL,
  `description` TEXT NULL,
  `validated` TINYINT(1) NOT NULL DEFAULT 0,
  `last_seen` TIMESTAMP(5) NOT NULL,
  `work_houres` VARCHAR(45) NULL,
  `email` VARCHAR(30) NOT NULL,
  `contact_email` VARCHAR(30) NULL,
  `mobile1` VARCHAR(20) NOT NULL,
  `mobile2` VARCHAR(20) NULL,
  `phone` VARCHAR(20) NULL,
  `active` TINYINT(1) NULL DEFAULT 1,
  `profile_Pic` INT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `name_UNIQUE` (`name` ASC),
  UNIQUE INDEX `email_id_UNIQUE` (`email` ASC),
  INDEX `fk_company_resouce1_idx` (`profile_Pic` ASC),
  CONSTRAINT `fk_company_resouce1`
    FOREIGN KEY (`profile_Pic`)
    REFERENCES `fashny`.`resouce` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `fashny`.`trip`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `fashny`.`trip` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL,
  `cost` INT NOT NULL,
  `count_booking` INT NOT NULL,
  `program` TEXT NOT NULL,
  `need_tools` TEXT NULL,
  `date` DATETIME NOT NULL,
  `join_deadline` DATETIME NOT NULL,
  `Max_booking` INT NOT NULL,
  `hint` TEXT NULL,
  `validated` TINYINT(1) NOT NULL DEFAULT 0,
  `company_id` INT NOT NULL,
  `offer` TINYINT(1) NULL DEFAULT 0,
  PRIMARY KEY (`id`),
  INDEX `fk_trip_company1_idx` (`company_id` ASC),
  CONSTRAINT `fk_trip_company1`
    FOREIGN KEY (`company_id`)
    REFERENCES `fashny`.`company` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `fashny`.`Partn_type`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `fashny`.`Partn_type` (
  `id` INT NOT NULL,
  `name` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `name_UNIQUE` (`name` ASC))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `fashny`.`partener`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `fashny`.`partener` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL,
  `password` VARCHAR(45) NOT NULL,
  `website` VARCHAR(100) NULL,
  `address` VARCHAR(100) NULL,
  `description` TEXT NULL,
  `type` INT NOT NULL,
  `work_hours` VARCHAR(45) NULL,
  `validated` TINYINT(1) NULL DEFAULT 0,
  `email` VARCHAR(30) NOT NULL,
  `contact_email` VARCHAR(30) NULL,
  `mobile1` VARCHAR(20) NOT NULL,
  `mobile2` VARCHAR(20) NULL,
  `phone` VARCHAR(20) NULL,
  `profile_Pic` INT NULL,
  `active` TINYINT(1) NULL DEFAULT 1,
  PRIMARY KEY (`id`),
  INDEX `fk_parteners_Partn_type1_idx` (`type` ASC),
  UNIQUE INDEX `email_id_UNIQUE` (`email` ASC),
  INDEX `fk_partener_resouce1_idx` (`profile_Pic` ASC),
  CONSTRAINT `fk_parteners_Partn_type1`
    FOREIGN KEY (`type`)
    REFERENCES `fashny`.`Partn_type` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_partener_resouce1`
    FOREIGN KEY (`profile_Pic`)
    REFERENCES `fashny`.`resouce` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `fashny`.`Service_Categorey`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `fashny`.`Service_Categorey` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL,
  `active` TINYINT(1) NULL DEFAULT 1,
  `parteners_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_CategoreyService_parteners1_idx` (`parteners_id` ASC),
  CONSTRAINT `fk_CategoreyService_parteners1`
    FOREIGN KEY (`parteners_id`)
    REFERENCES `fashny`.`partener` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `fashny`.`service`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `fashny`.`service` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL,
  `description` TEXT NOT NULL,
  `cost` FLOAT NOT NULL,
  `active` TINYINT(1) NOT NULL DEFAULT 1,
  `CategoreyService_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_service_CategoreyService1_idx` (`CategoreyService_id` ASC),
  CONSTRAINT `fk_service_CategoreyService1`
    FOREIGN KEY (`CategoreyService_id`)
    REFERENCES `fashny`.`Service_Categorey` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `fashny`.`interset`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `fashny`.`interset` (
  `client_id` INT NOT NULL,
  `tag_id` INT NOT NULL,
  PRIMARY KEY (`client_id`, `tag_id`),
  INDEX `fk_clients_has_tags_tags1_idx` (`tag_id` ASC),
  INDEX `fk_clients_has_tags_clients1_idx` (`client_id` ASC),
  CONSTRAINT `fk_clients_has_tags_clients1`
    FOREIGN KEY (`client_id`)
    REFERENCES `fashny`.`client` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_clients_has_tags_tags1`
    FOREIGN KEY (`tag_id`)
    REFERENCES `fashny`.`tag` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `fashny`.`wish`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `fashny`.`wish` (
  `clients_id` INT NOT NULL,
  `places_id` INT NOT NULL,
  PRIMARY KEY (`clients_id`, `places_id`),
  INDEX `fk_clients_has_places_places1_idx` (`places_id` ASC),
  INDEX `fk_clients_has_places_clients1_idx` (`clients_id` ASC),
  CONSTRAINT `fk_clients_has_places_clients1`
    FOREIGN KEY (`clients_id`)
    REFERENCES `fashny`.`client` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_clients_has_places_places1`
    FOREIGN KEY (`places_id`)
    REFERENCES `fashny`.`place` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `fashny`.`join_trip`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `fashny`.`join_trip` (
  `client_id` INT NOT NULL,
  `Trip_id` INT NOT NULL,
  `count` INT NOT NULL,
  `rate` INT NOT NULL,
  PRIMARY KEY (`client_id`, `Trip_id`),
  INDEX `fk_clients_has_Trips_Trips1_idx` (`Trip_id` ASC),
  INDEX `fk_clients_has_Trips_clients1_idx` (`client_id` ASC),
  CONSTRAINT `fk_clients_has_Trips_clients1`
    FOREIGN KEY (`client_id`)
    REFERENCES `fashny`.`client` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_clients_has_Trips_Trips1`
    FOREIGN KEY (`Trip_id`)
    REFERENCES `fashny`.`trip` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `fashny`.`Trip_has_place`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `fashny`.`Trip_has_place` (
  `Trip_id` INT NOT NULL,
  `place_id` INT NOT NULL,
  PRIMARY KEY (`Trip_id`, `place_id`),
  INDEX `fk_Trips_has_places_places1_idx` (`place_id` ASC),
  INDEX `fk_Trips_has_places_Trips1_idx` (`Trip_id` ASC),
  CONSTRAINT `fk_Trips_has_places_Trips1`
    FOREIGN KEY (`Trip_id`)
    REFERENCES `fashny`.`trip` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Trips_has_places_places1`
    FOREIGN KEY (`place_id`)
    REFERENCES `fashny`.`place` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `fashny`.`trip_has_tag`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `fashny`.`trip_has_tag` (
  `trip_id` INT NOT NULL,
  `tag_id` INT NOT NULL,
  PRIMARY KEY (`trip_id`, `tag_id`),
  INDEX `fk_Trips_has_tags_tags1_idx` (`tag_id` ASC),
  INDEX `fk_Trips_has_tags_Trips1_idx` (`trip_id` ASC),
  CONSTRAINT `fk_Trips_has_tags_Trips1`
    FOREIGN KEY (`trip_id`)
    REFERENCES `fashny`.`trip` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Trips_has_tags_tags1`
    FOREIGN KEY (`tag_id`)
    REFERENCES `fashny`.`tag` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `fashny`.`place_has_tag`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `fashny`.`place_has_tag` (
  `place_id` INT NOT NULL,
  `tag_id` INT NOT NULL,
  PRIMARY KEY (`place_id`, `tag_id`),
  INDEX `fk_places_has_tags_tags1_idx` (`tag_id` ASC),
  INDEX `fk_places_has_tags_places1_idx` (`place_id` ASC),
  CONSTRAINT `fk_places_has_tags_places1`
    FOREIGN KEY (`place_id`)
    REFERENCES `fashny`.`place` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_places_has_tags_tags1`
    FOREIGN KEY (`tag_id`)
    REFERENCES `fashny`.`tag` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `fashny`.`client_review_place`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `fashny`.`client_review_place` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `client_id` INT NOT NULL,
  `comment` VARCHAR(45) NOT NULL,
  `place_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_clients_has_reviews_clients1_idx` (`client_id` ASC),
  INDEX `fk_clients_has_reviews_places1_idx` (`place_id` ASC),
  CONSTRAINT `fk_clients_has_reviews_clients1`
    FOREIGN KEY (`client_id`)
    REFERENCES `fashny`.`client` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_clients_has_reviews_places1`
    FOREIGN KEY (`place_id`)
    REFERENCES `fashny`.`place` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `fashny`.`place_has_resouce`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `fashny`.`place_has_resouce` (
  `place_id` INT NOT NULL,
  `resouce_id` INT NOT NULL,
  PRIMARY KEY (`place_id`, `resouce_id`),
  INDEX `fk_places_has_images_images1_idx` (`resouce_id` ASC),
  INDEX `fk_places_has_images_places1_idx` (`place_id` ASC),
  CONSTRAINT `fk_places_has_images_places1`
    FOREIGN KEY (`place_id`)
    REFERENCES `fashny`.`place` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_places_has_images_images1`
    FOREIGN KEY (`resouce_id`)
    REFERENCES `fashny`.`resouce` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `fashny`.`trip_has_resouce`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `fashny`.`trip_has_resouce` (
  `trip_id` INT NOT NULL,
  `resouce_id` INT NOT NULL,
  PRIMARY KEY (`trip_id`, `resouce_id`),
  INDEX `fk_trip_has_resouce_resouce1_idx` (`resouce_id` ASC),
  INDEX `fk_trip_has_resouce_trip1_idx` (`trip_id` ASC),
  CONSTRAINT `fk_trip_has_resouce_trip1`
    FOREIGN KEY (`trip_id`)
    REFERENCES `fashny`.`trip` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_trip_has_resouce_resouce1`
    FOREIGN KEY (`resouce_id`)
    REFERENCES `fashny`.`resouce` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `fashny`.`service_has_resouce`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `fashny`.`service_has_resouce` (
  `service_id` INT NOT NULL,
  `resouce_id` INT NOT NULL,
  PRIMARY KEY (`service_id`, `resouce_id`),
  INDEX `fk_service_has_resouce_resouce1_idx` (`resouce_id` ASC),
  INDEX `fk_service_has_resouce_service1_idx` (`service_id` ASC),
  CONSTRAINT `fk_service_has_resouce_service1`
    FOREIGN KEY (`service_id`)
    REFERENCES `fashny`.`service` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_service_has_resouce_resouce1`
    FOREIGN KEY (`resouce_id`)
    REFERENCES `fashny`.`resouce` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `fashny`.`partener_has_tag`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `fashny`.`partener_has_tag` (
  `partener_id` INT NOT NULL,
  `tag_id` INT NOT NULL,
  PRIMARY KEY (`partener_id`, `tag_id`),
  INDEX `fk_partener_has_tag_tag1_idx` (`tag_id` ASC),
  INDEX `fk_partener_has_tag_partener1_idx` (`partener_id` ASC),
  CONSTRAINT `fk_partener_has_tag_partener1`
    FOREIGN KEY (`partener_id`)
    REFERENCES `fashny`.`partener` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_partener_has_tag_tag1`
    FOREIGN KEY (`tag_id`)
    REFERENCES `fashny`.`tag` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
