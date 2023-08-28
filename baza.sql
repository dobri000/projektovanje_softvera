/*
SQLyog Community v13.1.9 (64 bit)
MySQL - 10.4.25-MariaDB : Database - volleybox
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`volleybox` /*!40100 DEFAULT CHARACTER SET utf8mb4 */;

USE `volleybox`;

/*Table structure for table `admin` */

DROP TABLE IF EXISTS `admin`;

CREATE TABLE `admin` (
  `username` varchar(100) NOT NULL,
  `password` varchar(100) NOT NULL,
  PRIMARY KEY (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

/*Data for the table `admin` */

insert  into `admin`(`username`,`password`) values 
('admin','admin');

/*Table structure for table `country` */

DROP TABLE IF EXISTS `country`;

CREATE TABLE `country` (
  `countryId` int(11) NOT NULL AUTO_INCREMENT,
  `countryName` varchar(100) NOT NULL,
  PRIMARY KEY (`countryId`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4;

/*Data for the table `country` */

insert  into `country`(`countryId`,`countryName`) values 
(1,'Serbia'),
(2,'Bosnia and Hercegovina'),
(3,'Croatia'),
(4,'Montenegro');

/*Table structure for table `hall` */

DROP TABLE IF EXISTS `hall`;

CREATE TABLE `hall` (
  `hallId` int(11) NOT NULL AUTO_INCREMENT,
  `hallName` varchar(200) NOT NULL,
  `address` varchar(200) NOT NULL,
  PRIMARY KEY (`hallId`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8mb4;

/*Data for the table `hall` */

insert  into `hall`(`hallId`,`hallName`,`address`) values 
(1,'USC Vozdovac','Crnotravska 4, Beograd'),
(2,'SC Pozarevac','Partizanska 1, Pozarevac'),
(3,'SC Master Zemun','Cvetna 4, Beograd'),
(4,'SPC Vojvodina','Sutjeska 2, Novi Sad'),
(5,'HS Kraljevo','Kraljevo'),
(6,'SH Breza','Korcaginova bb, Gornji Milanovac'),
(7,'HS Dudova Suma','Ferenca Sepa 3, Subotica'),
(8,'SH Jezero','Grada sirena 15, Kragujevac'),
(9,'OS Sava Kerkovic','Svetog Save bb, Ljig'),
(10,'HS Stara Pazova','Branka Radicevica 6, Stara Pazova'),
(11,'O. S. Karadjordje','Mije Todorovic br 8, Topola'),
(12,'SD Slobodan Piva Ivkovic','Vojvode Supljikca 31, Beograd'),
(13,'Sportski centar \"Petrovac na Mlavi\"','Jovana Jovanovica Zmaja 3, Petrovac');

/*Table structure for table `player` */

DROP TABLE IF EXISTS `player`;

CREATE TABLE `player` (
  `playerId` int(11) NOT NULL AUTO_INCREMENT,
  `firstname` varchar(100) NOT NULL,
  `lastname` varchar(100) NOT NULL,
  `birthdate` date NOT NULL,
  `height` int(11) NOT NULL,
  `weight` int(11) NOT NULL,
  `spike` int(11) NOT NULL,
  `block` int(11) NOT NULL,
  `dominantHand` varchar(20) NOT NULL,
  `nationality` int(11) NOT NULL,
  PRIMARY KEY (`playerId`),
  KEY `fk_country` (`nationality`),
  CONSTRAINT `fk_country` FOREIGN KEY (`nationality`) REFERENCES `country` (`countryId`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8mb4;

/*Data for the table `player` */

insert  into `player`(`playerId`,`firstname`,`lastname`,`birthdate`,`height`,`weight`,`spike`,`block`,`dominantHand`,`nationality`) values 
(2,'Filip','Trifunovic','1999-07-11',186,69,305,300,'RIGHT',1),
(3,'Aleksandar','Gmitrovic','1994-11-23',200,96,340,320,'RIGHT',1),
(4,'Luka','Tadic','2000-10-10',203,100,340,320,'RIGHT',1),
(5,'Aleksandar','Bosnjak','2000-06-20',207,91,350,330,'RIGHT',1),
(6,'Maksim','Buculjevic','1991-01-20',191,92,340,315,'RIGHT',1),
(7,'Aleksandar','Okolic','1993-06-26',205,90,347,320,'RIGHT',1),
(8,'Stefan','Skakic','1999-10-19',195,89,330,320,'LEFT',1),
(9,'Milenko','Kozic','2002-02-07',197,90,325,320,'LEFT',2),
(12,'Bogdan','Vujic','1999-07-06',181,87,300,280,'RIGHT',1),
(16,'Dimitrije','Dobrijevic','2000-01-28',193,88,330,310,'RIGHT',1);

/*Table structure for table `playerengagement` */

DROP TABLE IF EXISTS `playerengagement`;

CREATE TABLE `playerengagement` (
  `playerId` int(11) NOT NULL,
  `teamId` int(11) NOT NULL,
  `rosterId` int(11) unsigned NOT NULL,
  `position` varchar(20) NOT NULL,
  `number` int(11) NOT NULL,
  PRIMARY KEY (`playerId`,`teamId`,`rosterId`),
  KEY `fk_pe_season` (`teamId`,`rosterId`),
  CONSTRAINT `fk_pe_player` FOREIGN KEY (`playerId`) REFERENCES `player` (`playerId`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fk_pe_roster` FOREIGN KEY (`teamId`, `rosterId`) REFERENCES `roster` (`teamId`, `rosterId`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

/*Data for the table `playerengagement` */

insert  into `playerengagement`(`playerId`,`teamId`,`rosterId`,`position`,`number`) values 
(2,1,23,'LIBERO',16),
(2,1,24,'LIBERO',6),
(2,1,25,'LIBERO',6),
(2,2,26,'LIBERO',6),
(4,1,23,'OUTSIDE_HITTER',5),
(4,11,37,'OUTSIDE_HITTER',5),
(5,10,34,'MIDDLE_BLOCKER',45),
(5,12,35,'MIDDLE_BLOCKER',9),
(12,3,38,'LIBERO',4),
(12,3,39,'LIBERO',4),
(12,3,42,'LIBERO',4);

/*Table structure for table `roster` */

DROP TABLE IF EXISTS `roster`;

CREATE TABLE `roster` (
  `teamId` int(11) NOT NULL,
  `rosterId` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `seasonId` int(10) NOT NULL,
  PRIMARY KEY (`teamId`,`rosterId`),
  KEY `rosterId` (`rosterId`),
  KEY `fk_roster_season` (`seasonId`),
  CONSTRAINT `fk_roster_season` FOREIGN KEY (`seasonId`) REFERENCES `season` (`seasonId`),
  CONSTRAINT `fk_roster_team` FOREIGN KEY (`teamId`) REFERENCES `team` (`teamId`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=43 DEFAULT CHARSET=utf8mb4;

/*Data for the table `roster` */

insert  into `roster`(`teamId`,`rosterId`,`seasonId`) values 
(3,39,1),
(3,40,2),
(1,23,3),
(1,24,5),
(1,25,6),
(5,30,7),
(12,35,7),
(1,27,8),
(3,42,8),
(5,29,8),
(10,34,8),
(11,37,8),
(1,28,9),
(2,26,9),
(3,38,9),
(11,36,9);

/*Table structure for table `season` */

DROP TABLE IF EXISTS `season`;

CREATE TABLE `season` (
  `seasonId` int(11) NOT NULL AUTO_INCREMENT,
  `startYear` int(4) NOT NULL,
  `endYear` int(4) NOT NULL,
  PRIMARY KEY (`seasonId`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb4;

/*Data for the table `season` */

insert  into `season`(`seasonId`,`startYear`,`endYear`) values 
(1,2015,2016),
(2,2016,2017),
(3,2017,2018),
(4,2018,2019),
(5,2019,2020),
(6,2020,2021),
(7,2021,2022),
(8,2022,2023),
(9,2023,2024);

/*Table structure for table `staffmember` */

DROP TABLE IF EXISTS `staffmember`;

CREATE TABLE `staffmember` (
  `staffMemberId` int(11) NOT NULL AUTO_INCREMENT,
  `firstname` varchar(100) DEFAULT NULL,
  `lastname` varchar(100) DEFAULT NULL,
  `birthdate` date DEFAULT NULL,
  `nationality` int(11) DEFAULT NULL,
  PRIMARY KEY (`staffMemberId`),
  KEY `fk_staff_country` (`nationality`),
  CONSTRAINT `fk_staff_country` FOREIGN KEY (`nationality`) REFERENCES `country` (`countryId`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4;

/*Data for the table `staffmember` */

insert  into `staffmember`(`staffMemberId`,`firstname`,`lastname`,`birthdate`,`nationality`) values 
(1,'Luka','Ratkovic','2003-03-31',1),
(2,'Ivica','Jevtic','1983-07-26',1),
(3,'Vladimir','Kozic','1994-07-14',2),
(4,'Bojan','Janic','1982-03-11',1);

/*Table structure for table `staffmemberengagement` */

DROP TABLE IF EXISTS `staffmemberengagement`;

CREATE TABLE `staffmemberengagement` (
  `staffMemberId` int(11) NOT NULL,
  `teamId` int(11) NOT NULL,
  `rosterId` int(11) unsigned NOT NULL,
  `position` varchar(30) NOT NULL,
  PRIMARY KEY (`staffMemberId`,`teamId`,`rosterId`),
  KEY `fk_se_season` (`teamId`,`rosterId`),
  CONSTRAINT `fk_se_roster` FOREIGN KEY (`teamId`, `rosterId`) REFERENCES `roster` (`teamId`, `rosterId`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fk_se_staff` FOREIGN KEY (`staffMemberId`) REFERENCES `staffmember` (`staffMemberId`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

/*Data for the table `staffmemberengagement` */

insert  into `staffmemberengagement`(`staffMemberId`,`teamId`,`rosterId`,`position`) values 
(1,1,27,'STATISTICIAN'),
(1,1,28,'STATISTICIAN'),
(1,3,40,'STATISTICIAN'),
(2,1,28,'HEAD_COACH'),
(2,5,29,'HEAD_COACH'),
(2,5,30,'HEAD_COACH');

/*Table structure for table `team` */

DROP TABLE IF EXISTS `team`;

CREATE TABLE `team` (
  `teamId` int(11) NOT NULL AUTO_INCREMENT,
  `teamName` varchar(100) NOT NULL,
  `founded` int(11) NOT NULL,
  `country` int(11) NOT NULL,
  `hall` int(11) NOT NULL,
  PRIMARY KEY (`teamId`),
  KEY `fk_team_country` (`country`),
  KEY `fk_team_hall` (`hall`),
  CONSTRAINT `fk_team_country` FOREIGN KEY (`country`) REFERENCES `country` (`countryId`),
  CONSTRAINT `fk_team_hall` FOREIGN KEY (`hall`) REFERENCES `hall` (`hallId`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8mb4;

/*Data for the table `team` */

insert  into `team`(`teamId`,`teamName`,`founded`,`country`,`hall`) values 
(1,'Crvena Zvezda Belgrade',1945,1,1),
(2,'Mladi Radnik Pozarevac',1946,1,2),
(3,'Ribnica Kraljevo',1954,1,5),
(4,'OK Metalac Takovo',1976,1,6),
(5,'Spartak Subotica',1981,1,7),
(6,'Radnicki Kragujevac',1945,1,8),
(7,'Spartak Ljig',1975,1,9),
(8,'Jedinstvo Stara Pazova',1931,1,10),
(9,'OK Karadjordje Topola',1964,1,11),
(10,'Novi Sad',2004,1,4),
(11,'Partizan Beograd',1945,1,3),
(12,'Vojvodina Novi Sad',1946,1,4);

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
