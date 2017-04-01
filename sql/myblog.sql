CREATE SCHEMA `myblog` DEFAULT CHARACTER SET utf8 ;

CREATE TABLE `myblog`.`theme` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `title` VARCHAR(45) NOT NULL,
  `text` LONGTEXT NOT NULL,
  `date` DATETIME NOT NULL,
  PRIMARY KEY (`id`));

CREATE TABLE `myblog`.`user` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `login` VARCHAR(255) NOT NULL,
  `password` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id`));

CREATE TABLE `myblog`.`comments` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `test` VARCHAR(255) NOT NULL,
  `date` DATETIME NOT NULL,
  `iduser` INT NOT NULL,
  `idtheme` INT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `idtheme_idx` (`idtheme` ASC),
  INDEX `iduser_idx` (`iduser` ASC),
  CONSTRAINT `iduser`
  FOREIGN KEY (`iduser`)
  REFERENCES `myblog`.`user` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `idtheme`
  FOREIGN KEY (`idtheme`)
  REFERENCES `myblog`.`theme` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);
