-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema conference
-- -----------------------------------------------------
DROP SCHEMA IF EXISTS `conference` ;

-- -----------------------------------------------------
-- Schema conference
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `conference` DEFAULT CHARACTER SET utf8 ;
USE `conference` ;

-- -----------------------------------------------------
-- Table `conference`.`user_roles`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `conference`.`user_roles` ;

CREATE TABLE IF NOT EXISTS `conference`.`user_roles` (
  `role_id` INT NOT NULL,
  `role_description` VARCHAR(24) NOT NULL,
  PRIMARY KEY (`role_id`),
  UNIQUE INDEX `role_description_UNIQUE` (`role_description` ASC) VISIBLE);


-- -----------------------------------------------------
-- Table `conference`.`users`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `conference`.`users` ;

CREATE TABLE IF NOT EXISTS `conference`.`users` (
  `user_id` INT NOT NULL AUTO_INCREMENT,
  `role_id` INT NOT NULL,
  `user_name` VARCHAR(32) NOT NULL,
  `user_surname` VARCHAR(32) NOT NULL,
  `user_password` VARCHAR(32) NOT NULL,
  `user_phone` VARCHAR(32) NOT NULL,
  `user_email` VARCHAR(32) NOT NULL,
  `user_photo_url` VARCHAR(255) NOT NULL,
  `user_address` VARCHAR(128) NOT NULL,
  PRIMARY KEY (`user_id`),
  UNIQUE INDEX `user_email_UNIQUE` (`user_email` ASC) VISIBLE,
  UNIQUE INDEX `user_phone_UNIQUE` (`user_phone` ASC) VISIBLE,
    FOREIGN KEY (`role_id`)
    REFERENCES `conference`.`user_roles` (`role_id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE);


-- -----------------------------------------------------
-- Table `conference`.`events`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `conference`.`events` ;

CREATE TABLE IF NOT EXISTS `conference`.`events` (
  `event_id` INT NOT NULL AUTO_INCREMENT,
  `event_name_ua` VARCHAR(128) NOT NULL,
  `event_name_en` VARCHAR(128) NOT NULL,
  `event_place_ua` VARCHAR(128) NOT NULL,
  `event_place_en` VARCHAR(128) NOT NULL,
  `event_description_ua` VARCHAR(255) NOT NULL,
  `event_description_en` VARCHAR(255) NOT NULL,
  `event_date` DATETIME NOT NULL,
  `event_photo_url` VARCHAR(255) NOT NULL,
  PRIMARY KEY (`event_id`));


-- -----------------------------------------------------
-- Table `conference`.`reports`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `conference`.`reports` ;

CREATE TABLE IF NOT EXISTS `conference`.`reports` (
  `report_id` INT NOT NULL AUTO_INCREMENT,
  `event_id` INT NOT NULL,
  `report_name_ua` VARCHAR(45) NOT NULL,
  `report_name_en` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`report_id`),
    FOREIGN KEY (`event_id`)
    REFERENCES `conference`.`events` (`event_id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE);


-- -----------------------------------------------------
-- Table `conference`.`event_users`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `conference`.`event_users` ;

CREATE TABLE IF NOT EXISTS `conference`.`event_users` (
  `id` INT NOT NULL,
  `user_id` INT NOT NULL,
  `event_id` INT NOT NULL,
  `present` TINYINT NOT NULL DEFAULT 0,
  PRIMARY KEY (`id`),
    FOREIGN KEY (`user_id`)
    REFERENCES `conference`.`users` (`user_id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
    FOREIGN KEY (`event_id`)
    REFERENCES `conference`.`events` (`event_id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE);


-- -----------------------------------------------------
-- Table `conference`.`category`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `conference`.`category` ;

CREATE TABLE IF NOT EXISTS `conference`.`category` (
  `category_id` INT NOT NULL,
  `name` VARCHAR(255) NOT NULL,
  PRIMARY KEY (`category_id`));


-- -----------------------------------------------------
-- Table `conference`.`reports_speakers`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `conference`.`reports_speakers` ;

CREATE TABLE IF NOT EXISTS `conference`.`reports_speakers` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `report_id` INT NOT NULL,
  `speaker_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `report_id_UNIQUE` (`report_id` ASC) VISIBLE,
    FOREIGN KEY (`report_id`)
    REFERENCES `conference`.`reports` (`report_id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
    FOREIGN KEY (`speaker_id`)
    REFERENCES `conference`.`users` (`user_id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE);


-- -----------------------------------------------------
-- Table `conference`.`speaker_preposition`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `conference`.`speaker_preposition` ;

CREATE TABLE IF NOT EXISTS `conference`.`speaker_preposition` (
  `id` INT NOT NULL,
  `report_id` INT NOT NULL,
  `speaker_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `speaker_id_UNIQUE` (`speaker_id` ASC) VISIBLE,
  UNIQUE INDEX `report_id_UNIQUE` (`report_id` ASC) VISIBLE,
    FOREIGN KEY (`speaker_id`)
    REFERENCES `conference`.`users` (`user_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
    FOREIGN KEY (`report_id`)
    REFERENCES `conference`.`reports` (`report_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);


-- -----------------------------------------------------
-- Table `conference`.`report_preposition`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `conference`.`report_preposition` ;

CREATE TABLE IF NOT EXISTS `conference`.`report_preposition` (
  `id` INT NOT NULL,
  `event_id` INT NOT NULL,
  `speaker_id` INT NOT NULL,
  `report_name_ua` VARCHAR(45) NOT NULL,
  `report_name_en` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id`),
    FOREIGN KEY (`speaker_id`)
    REFERENCES `conference`.`users` (`user_id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
    FOREIGN KEY (`event_id`)
    REFERENCES `conference`.`events` (`event_id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE);


-- -----------------------------------------------------
-- Table `conference`.`moderator_preposition`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `conference`.`moderator_preposition` ;

CREATE TABLE IF NOT EXISTS `conference`.`moderator_preposition` (
  `id` INT NOT NULL,
  `report_id` INT NOT NULL,
  `speaker_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `speaker_id_UNIQUE` (`speaker_id` ASC) VISIBLE,
  UNIQUE INDEX `report_id_UNIQUE` (`report_id` ASC) VISIBLE,
    FOREIGN KEY (`speaker_id`)
    REFERENCES `conference`.`users` (`user_id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
    FOREIGN KEY (`report_id`)
    REFERENCES `conference`.`reports` (`report_id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE);


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;

INSERT INTO conference.user_roles VALUES(1, "moderator");
INSERT INTO conference.user_roles VALUES(2, "speaker");
INSERT INTO conference.user_roles VALUES(3, "user");

INSERT INTO conference.users VALUES(1, 1, "moderator", "moderator", "moderator", "+123456789", "moderator@gmail.com", "moderator.jpg", "Lviv");
INSERT INTO conference.users VALUES(2, 2, "Joe", "Needham", "speaker", "+234567890", "joeneedham@gmail.com", "speaker.jpg", "Lviv");
INSERT INTO conference.users VALUES(3, 2, "Horace", "Daniel", "speaker", "+234567891", "horacedaniel@gmail.com", "speaker.jpg", "Lviv");
INSERT INTO conference.users VALUES(4, 2, "Paula", "Whyte", "speaker", "+234567892", "paulawhyte@gmail.com", "speaker.jpg", "Lviv");
INSERT INTO conference.users VALUES(5, 2, "Amira", "Robins", "speaker", "+234567893", "amirarobins@gmail.com", "speaker.jpg", "Lviv");
INSERT INTO conference.users VALUES(6, 2, "Stephen", "Regan", "speaker", "+234567894", "stehpenregan@gmail.com", "speaker.jpg", "Lviv");
INSERT INTO conference.users VALUES(7, 2, "Mai", "Arias", "speaker", "+234567895", "maiarias@gmail.com", "speaker.jpg", "Lviv");
INSERT INTO conference.users VALUES(8, 3, "Maurice", "Knight", "user", "+345678901", "mauriceknight@gmail.com", "user.jpg", "Lviv");
INSERT INTO conference.users VALUES(9, 3, "Anna", "Johnson", "user", "+345678902", "annajohnson@gmail.com", "user.jpg", "Lviv");
INSERT INTO conference.users VALUES(10, 3, "Mark", "Reyes", "user", "+345678903", "markreyes@gmail.com", "user.jpg", "Lviv");
INSERT INTO conference.users VALUES(11, 3, "Timothy", "Baker", "user", "+345678904", "timothynaker@gmail.com", "user.jpg", "Lviv");
INSERT INTO conference.users VALUES(12, 3, "Katie", "Palmer", "user", "+345678905", "katiepalmer@gmail.com", "user.jpg", "Lviv");
INSERT INTO conference.users VALUES(13, 3, "Christine", "Garcia", "user", "+345678906", "christinegarcia@gmail.com", "user.jpg", "Lviv");
INSERT INTO conference.users VALUES(14, 3, "Dalton", "Armstrong", "user", "+345678907", "deltonarmstrong@gmail.com", "user.jpg", "Lviv");
INSERT INTO conference.users VALUES(15, 3, "Jennifer", "Caldwell", "user", "+345678908", "jennifercaldwell@gmail.com", "user.jpg", "Lviv");
INSERT INTO conference.users VALUES(16, 3, "Tammy", "Flores", "user", "+345678909", "tammyflores@gmail.com", "user.jpg", "Lviv");
INSERT INTO conference.users VALUES(17, 3, "Suzanne", "Kennedy", "user", "+345678910", "suzannekennedy@gmail.com", "user.jpg", "Lviv");
INSERT INTO conference.users VALUES(18, 3, "Tracy", "Stewart", "user", "+345678911", "tracystewart@gmail.com", "user.jpg", "Lviv");
INSERT INTO conference.users VALUES(19, 3, "Rebecca", "Schmidt", "user", "+345678912", "rebeccaschmidt@gmail.com", "user.jpg", "Lviv");

INSERT INTO conference.events VALUES(1, "Коллекції", "Collections", "Еколенд", "Ekoland", "Освіжаєм знання про коллекції в Java та закріплуєм їх на практиці", "Refresh your knowledge of collections in Java and put them into practice", "2022-05-10 18:00:00", "collections.jpg");
INSERT INTO conference.events VALUES(2, "Стріми І/О", "Streams I/O", "Малевич", "Malevich", "Глибоке занурення в Streams I/O", "Deep dive into Streams I/O", "2022-05-15 18:00:00", "streamsio.jpg");
INSERT INTO conference.events VALUES(3, "Багатопоточність", "Multithreading", "Віртуал Сіті", "Virtual City", "Що таке багатопоточність: майстер-класс по багатопоточним програмам", "What is multithreading: a master class on multithreading programs", "2022-05-20 18:00:00", "multithreading.jpg");
INSERT INTO conference.events VALUES(4, "Джава 8", "Java 8", "Арт Готель", "Art Hotel", "Нововведення джави 8. Що нового", "Innovations of Java 8. What's new", "2022-05-25 18:00:00", "java8.jpg");
INSERT INTO conference.events VALUES(5, "Паттерни проектування", "Design Patterns", "Еколенд", "Ekoland", "Ефектні способи вирішення задач проєктування програмного забезпечення", "Effective ways to solve software design problems", "2022-05-30 18:00:00", "designpatterns.jpg");
INSERT INTO conference.events VALUES(6, "Тестування й Логування", "Testing and Logging", "Малевич", "Malevich", "Дзен логування. Як полюбити свої логи та почати жити", "Zen logging. How to love your logs and start living", "2022-02-10 18:00:00", "testing.jpg");
INSERT INTO conference.events VALUES(7, "Введення в Бази даних", "Introduction to Database", "Віртуал Сіті", "Virtual City", "Дізнайтеся, що таке бази даних, які вони бувають", "Learn what databases are, what they are", "2022-02-20 18:00:00", "database.jpg");
INSERT INTO conference.events VALUES(8, "Фільтри й сессії", "Filters and Sessions", "Арт Готель", "Art Hotel", "Фільтри й сессії - що це таке і з чим це їдять", "Filters and sessions - what it is and what it is eaten with", "2022-02-28 18:00:00", "filters.jpg");
INSERT INTO conference.reports VALUES(1, 1, "Трісет", "TreeSet");
INSERT INTO conference.reports VALUES(2, 1, "Хешмапа", "HashMap");
INSERT INTO conference.reports VALUES(3, 1, "Масиви проти Колекцій", "Arrays vs Collections");
INSERT INTO conference.reports VALUES(4, 2, "Клас Файл", "Class File");
INSERT INTO conference.reports VALUES(5, 2, "ІО проти НІО", "IO VS NIO");
INSERT INTO conference.reports VALUES(6, 2, "Серіалізація", "Serializable");
INSERT INTO conference.reports VALUES(7, 3, "Процеси і потоки", "Processes and Threads");
INSERT INTO conference.reports VALUES(8, 3, "Синхронізація", "Synchronization");
INSERT INTO conference.reports VALUES(9, 3, "Атомарність", "Atomocity");
INSERT INTO conference.reports VALUES(10, 4, "Лямбда Вирази", "Lambdas");
INSERT INTO conference.reports VALUES(11, 4, "Стрім Апі", "Stream API");
INSERT INTO conference.reports VALUES(12, 5, "Солід Принципи", "SOLID principles");
INSERT INTO conference.reports VALUES(13, 6, "Життєвий цикл П.З.", "SDLC");
INSERT INTO conference.reports VALUES(14, 6, "Мавен проти Градлу", "Maven vs Gradle");
INSERT INTO conference.reports VALUES(15, 6, "Аннотації", "Annotations");
INSERT INTO conference.reports VALUES(16, 6, "Інтеграційні тести", "Integration testing");
INSERT INTO conference.reports VALUES(17, 7, "Моделі баз даних", "Database models");
INSERT INTO conference.reports VALUES(18, 7, "Основні команди", "Main commands");
INSERT INTO conference.reports VALUES(19, 8, "Концепт Фільтрів", "Concept of filter");

INSERT INTO conference.report_preposition VALUES(1, 1, 2, "Ітератор", "Iterator");
INSERT INTO conference.report_preposition VALUES(2, 4, 3, "Функціональні інтерфейси", "Functional Inferface");
INSERT INTO conference.report_preposition VALUES(3, 8, 6, "Основні принципи логування", "General principles of logging");

INSERT INTO conference.event_users VALUES(1, 8, 6, true);
INSERT INTO conference.event_users VALUES(2, 9, 7, true);
INSERT INTO conference.event_users VALUES(3, 10, 8, false);
INSERT INTO conference.event_users VALUES(4, 11, 6, true);
INSERT INTO conference.event_users VALUES(5, 12, 7, true);
INSERT INTO conference.event_users VALUES(6, 14, 8, false);
INSERT INTO conference.event_users VALUES(7, 15, 6, true);
INSERT INTO conference.event_users VALUES(8, 16, 7, true);
INSERT INTO conference.event_users VALUES(9, 17, 8, false);
INSERT INTO conference.event_users VALUES(10, 18, 6, true);
INSERT INTO conference.event_users VALUES(11, 19, 7, true);
INSERT INTO conference.event_users VALUES(12, 8, 7, false);
INSERT INTO conference.event_users VALUES(13, 9, 8, true);
INSERT INTO conference.event_users VALUES(14, 10, 6, false);
INSERT INTO conference.event_users VALUES(15, 11, 7, true);
INSERT INTO conference.event_users VALUES(16, 12, 8, true);
INSERT INTO conference.event_users VALUES(17, 14, 6, false);
INSERT INTO conference.event_users VALUES(18, 15, 7, true);
INSERT INTO conference.event_users VALUES(19, 16, 8, true);
INSERT INTO conference.event_users VALUES(20, 17, 6, true);
INSERT INTO conference.event_users VALUES(21, 18, 7, false);
INSERT INTO conference.event_users VALUES(22, 19, 8, true);
INSERT INTO conference.event_users VALUES(23, 8, 8, true);
INSERT INTO conference.event_users VALUES(24, 9, 6, true);
INSERT INTO conference.event_users VALUES(25, 10, 7, true);
INSERT INTO conference.event_users VALUES(26, 11, 8, false);
INSERT INTO conference.event_users VALUES(27, 12, 6, false);
INSERT INTO conference.event_users VALUES(28, 14, 7, true);
INSERT INTO conference.event_users VALUES(29, 15, 8, true);
INSERT INTO conference.event_users VALUES(30, 16, 6, false);
INSERT INTO conference.event_users VALUES(31, 17, 7, true);
INSERT INTO conference.event_users VALUES(32, 18, 8, false);
INSERT INTO conference.event_users VALUES(33, 19, 6, false);
INSERT INTO conference.event_users VALUES(34, 8, 1, false);
INSERT INTO conference.event_users VALUES(35, 9, 1, false);
INSERT INTO conference.event_users VALUES(36, 10, 1, false);
INSERT INTO conference.event_users VALUES(37, 11, 1, false);
INSERT INTO conference.event_users VALUES(38, 12, 1, false);
INSERT INTO conference.event_users VALUES(39, 14, 1, false);
INSERT INTO conference.event_users VALUES(40, 15, 1, false);
INSERT INTO conference.event_users VALUES(41, 16, 1, false);
INSERT INTO conference.event_users VALUES(42, 17, 1, false);
INSERT INTO conference.event_users VALUES(43, 18, 1, false);
INSERT INTO conference.event_users VALUES(44, 19, 1, false);
INSERT INTO conference.event_users VALUES(45, 8, 3, false);
INSERT INTO conference.event_users VALUES(46, 9, 3, false);
INSERT INTO conference.event_users VALUES(47, 10, 3, false);
INSERT INTO conference.event_users VALUES(48, 11, 3, false);
INSERT INTO conference.event_users VALUES(49, 12, 3, false);
INSERT INTO conference.event_users VALUES(50, 14, 3, false);
INSERT INTO conference.event_users VALUES(51, 15, 3, false);
INSERT INTO conference.event_users VALUES(52, 16, 3, false);
INSERT INTO conference.event_users VALUES(53, 17, 3, false);
INSERT INTO conference.event_users VALUES(54, 18, 3, false);
INSERT INTO conference.event_users VALUES(55, 19, 3, false);
INSERT INTO conference.event_users VALUES(56, 8, 4, false);
INSERT INTO conference.event_users VALUES(57, 9, 4, false);
INSERT INTO conference.event_users VALUES(58, 10, 4, false);
INSERT INTO conference.event_users VALUES(59, 11, 4, false);
INSERT INTO conference.event_users VALUES(60, 12, 4, false);
INSERT INTO conference.event_users VALUES(61, 14, 4, false);
INSERT INTO conference.event_users VALUES(62, 15, 5, false);
INSERT INTO conference.event_users VALUES(64, 16, 5, false);
INSERT INTO conference.event_users VALUES(65, 17, 5, false);
INSERT INTO conference.event_users VALUES(66, 18, 5, false);
INSERT INTO conference.event_users VALUES(67, 19, 5, false);
INSERT INTO conference.event_users VALUES(68, 11, 4, false);
INSERT INTO conference.event_users VALUES(69, 12, 4, false);
INSERT INTO conference.event_users VALUES(70, 14, 4, false);
INSERT INTO conference.event_users VALUES(71, 15, 2, false);
INSERT INTO conference.event_users VALUES(72, 16, 2, false);
INSERT INTO conference.event_users VALUES(73, 17, 2, false);
INSERT INTO conference.event_users VALUES(74, 18, 2, false);
INSERT INTO conference.event_users VALUES(75, 19, 2, false);

INSERT INTO conference.speaker_preposition VALUES(1, 1, 2);
INSERT INTO conference.speaker_preposition VALUES(2, 2, 3);
INSERT INTO conference.speaker_preposition VALUES(3, 3, 4);
INSERT INTO conference.speaker_preposition VALUES(4, 4, 5);
INSERT INTO conference.speaker_preposition VALUES(5, 5, 6);
INSERT INTO conference.speaker_preposition VALUES(6, 6, 7);

INSERT INTO conference.moderator_preposition VALUES(1, 7, 2);
INSERT INTO conference.moderator_preposition VALUES(2, 8, 3);
INSERT INTO conference.moderator_preposition VALUES(3, 9, 4);
INSERT INTO conference.moderator_preposition VALUES(4, 10, 5);
INSERT INTO conference.moderator_preposition VALUES(5, 11, 6);
INSERT INTO conference.moderator_preposition VALUES(6, 12, 7);

INSERT INTO conference.reports_speakers VALUES(1, 1, 3);
INSERT INTO conference.reports_speakers VALUES(2, 2, 4);
INSERT INTO conference.reports_speakers VALUES(3, 3, 5);
INSERT INTO conference.reports_speakers VALUES(4, 4, 6);
INSERT INTO conference.reports_speakers VALUES(5, 5, 7);
INSERT INTO conference.reports_speakers VALUES(6, 6, 2);
INSERT INTO conference.reports_speakers VALUES(7, 7, 2);
INSERT INTO conference.reports_speakers VALUES(8, 8, 3);
INSERT INTO conference.reports_speakers VALUES(9, 9, 4);
INSERT INTO conference.reports_speakers VALUES(10, 10, 5);
INSERT INTO conference.reports_speakers VALUES(11, 11, 6);
INSERT INTO conference.reports_speakers VALUES(12, 12, 7);
INSERT INTO conference.reports_speakers VALUES(13, 13, 5);
INSERT INTO conference.reports_speakers VALUES(14, 14, 7);
INSERT INTO conference.reports_speakers VALUES(15, 15, 6);
INSERT INTO conference.reports_speakers VALUES(16, 16, 5);
INSERT INTO conference.reports_speakers VALUES(17, 17, 4);
INSERT INTO conference.reports_speakers VALUES(18, 18, 3);
INSERT INTO conference.reports_speakers VALUES(19, 19, 2);