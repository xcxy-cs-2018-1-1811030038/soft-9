/*
SQLyog Ultimate v12.09 (64 bit)
MySQL - 8.0.23 : Database - scores
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`scores` /*!40100 DEFAULT CHARACTER SET utf8 */ /*!80016 DEFAULT ENCRYPTION='N' */;

USE `scores`;

/*Table structure for table `course` */

DROP TABLE IF EXISTS `course`;

CREATE TABLE `course` (
  `course_id` char(9) NOT NULL,
  `course_name` varchar(20) NOT NULL,
  `teacher_id` char(8) NOT NULL,
  `year` varchar(10) NOT NULL,
  `term` varchar(10) NOT NULL,
  PRIMARY KEY (`course_id`),
  KEY `course_fk_teacher` (`teacher_id`),
  CONSTRAINT `course_fk_teacher` FOREIGN KEY (`teacher_id`) REFERENCES `teacher` (`teacher_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `course` */

insert  into `course`(`course_id`,`course_name`,`teacher_id`,`year`,`term`) values ('500000001','英语','50000001','2018','1'),('500000002','数学','5000002','2018','1');

/*Table structure for table `manager` */

DROP TABLE IF EXISTS `manager`;

CREATE TABLE `manager` (
  `manager_id` char(9) NOT NULL,
  `manager_name` varchar(10) NOT NULL,
  `password` varchar(26) NOT NULL,
  PRIMARY KEY (`manager_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `manager` */

insert  into `manager`(`manager_id`,`manager_name`,`password`) values ('100000001','三胖','123456');

/*Table structure for table `score` */

DROP TABLE IF EXISTS `score`;

CREATE TABLE `score` (
  `score_id` int NOT NULL AUTO_INCREMENT,
  `student_id` char(8) NOT NULL,
  `course_id` char(9) DEFAULT NULL,
  `score` int DEFAULT '-1',
  PRIMARY KEY (`score_id`),
  KEY `score_fk_student` (`student_id`),
  KEY `score_fk_course` (`course_id`),
  CONSTRAINT `score_fk_course` FOREIGN KEY (`course_id`) REFERENCES `course` (`course_id`),
  CONSTRAINT `score_fk_student` FOREIGN KEY (`student_id`) REFERENCES `student` (`student_id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;

/*Data for the table `score` */

insert  into `score`(`score_id`,`student_id`,`course_id`,`score`) values (1,'10000001','500000001',80),(2,'10000002','500000001',80),(3,'10000003','500000001',80),(5,'10000101','500000002',-1),(6,'10000102','500000002',-1);

/*Table structure for table `student` */

DROP TABLE IF EXISTS `student`;

CREATE TABLE `student` (
  `student_id` char(8) NOT NULL,
  `student_name` varchar(10) NOT NULL,
  `grade` varchar(10) NOT NULL,
  `student_class` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `password` varchar(26) NOT NULL,
  PRIMARY KEY (`student_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `student` */

insert  into `student`(`student_id`,`student_name`,`grade`,`student_class`,`password`) values ('10000001','小金','2018','1','123456'),('10000002','小木','2018','1','123456'),('10000003','小火','2018','1','123456'),('10000101','小水','2018','2','123456'),('10000102','小土','2018','2','123456');

/*Table structure for table `teacher` */

DROP TABLE IF EXISTS `teacher`;

CREATE TABLE `teacher` (
  `teacher_id` char(8) NOT NULL,
  `teacher_name` varchar(10) NOT NULL,
  `password` varchar(26) NOT NULL,
  PRIMARY KEY (`teacher_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `teacher` */

insert  into `teacher`(`teacher_id`,`teacher_name`,`password`) values ('50000001','老赵','123456'),('50000003','老孙','123456'),('5000002','老钱','123456');

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
