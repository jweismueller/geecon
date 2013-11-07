-- --------------------------------------------------------
-- Geecon Version:                 1.0.0
-- Author:                         Jürgen Weismüller
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;

-- Dumping database structure for geecon
CREATE DATABASE IF NOT EXISTS `geecon` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `geecon`;


-- Dumping structure for table geecon.conf_conference
CREATE TABLE IF NOT EXISTS `conf_conference` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `version` bigint(20) DEFAULT NULL,
  `date_from` date DEFAULT NULL,
  `date_till` date DEFAULT NULL,
  `description` longtext,
  `title` varchar(64) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;



-- Dumping structure for table geecon.conf_speakerassignment
CREATE TABLE IF NOT EXISTS `conf_speakerassignment` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `version` bigint(20) DEFAULT NULL,
  `speaker` bigint(20) DEFAULT NULL,
  `talk` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK2130CBB1A489AFF` (`speaker`),
  KEY `FK2130CBB1634555F7` (`talk`),
  CONSTRAINT `FK2130CBB1634555F7` FOREIGN KEY (`talk`) REFERENCES `conf_talk` (`id`),
  CONSTRAINT `FK2130CBB1A489AFF` FOREIGN KEY (`speaker`) REFERENCES `orga_speaker` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;



-- Dumping structure for table geecon.conf_talk
CREATE TABLE IF NOT EXISTS `conf_talk` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `version` bigint(20) DEFAULT NULL,
  `date_on` date DEFAULT NULL,
  `description` longtext,
  `time_from` varchar(5) DEFAULT NULL,
  `time_till` varchar(5) DEFAULT NULL,
  `title` varchar(64) DEFAULT NULL,
  `conference` bigint(20) DEFAULT NULL,
  `room` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKCF8614E751354397` (`conference`),
  KEY `FKCF8614E76343EE55` (`room`),
  CONSTRAINT `FKCF8614E76343EE55` FOREIGN KEY (`room`) REFERENCES `orga_room` (`id`),
  CONSTRAINT `FKCF8614E751354397` FOREIGN KEY (`conference`) REFERENCES `conf_conference` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;



-- Dumping structure for table geecon.orga_location
CREATE TABLE IF NOT EXISTS `orga_location` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `version` bigint(20) DEFAULT NULL,
  `city` varchar(255) DEFAULT NULL,
  `title` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;



-- Dumping structure for table geecon.orga_room
CREATE TABLE IF NOT EXISTS `orga_room` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `version` bigint(20) DEFAULT NULL,
  `capacity` int(11) DEFAULT NULL,
  `title` varchar(64) DEFAULT NULL,
  `location` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK7F8A41DD1FE2C489` (`location`),
  CONSTRAINT `FK7F8A41DD1FE2C489` FOREIGN KEY (`location`) REFERENCES `orga_location` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;



-- Dumping structure for table geecon.orga_speaker
CREATE TABLE IF NOT EXISTS `orga_speaker` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `version` bigint(20) DEFAULT NULL,
  `description` longtext,
  `name` varchar(64) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
