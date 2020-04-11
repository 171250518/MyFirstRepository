/*
 Navicat Premium Data Transfer

 Source Server         : 127.0.0.1
 Source Server Type    : MySQL
 Source Server Version : 80018
 Source Host           : localhost:3306
 Source Schema         : oasis

 Target Server Type    : MySQL
 Target Server Version : 80018
 File Encoding         : 65001

 Date: 07/03/2020 11:18:39
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for 11
-- ----------------------------
DROP TABLE IF EXISTS ase;
DROP Table IF EXISTS user;

CREATE TABLE `ase` (
  `Document_Title` varchar(255) DEFAULT NULL,
  `Authors` varchar(255) DEFAULT NULL,
  `Author_Affiliations` mediumtext,
  `Publication_Title` varchar(255) DEFAULT NULL,
  `Date_Added_To_Xplore` varchar(255) DEFAULT NULL,
  `Publication_Year` varchar(255) DEFAULT NULL,
  `Volume` varchar(255) DEFAULT NULL,
  `Issue` varchar(255) DEFAULT NULL,
  `Start_Page` varchar(255) DEFAULT NULL,
  `End_Page` varchar(255) DEFAULT NULL,
  `Abstract` mediumtext,
  `ISSN` varchar(255) DEFAULT NULL,
  `ISBNs` varchar(255) DEFAULT NULL,
  `DOI` varchar(255) DEFAULT NULL,
  `Funding_Information` varchar(255) DEFAULT NULL,
  `PDF_Link` varchar(255) DEFAULT NULL,
  `Author_Keywords` mediumtext DEFAULT NULL,
  `IEEE_Terms` varchar(255) DEFAULT NULL,
  `INSPEC_Controlled_Terms` mediumtext DEFAULT NULL,
  `INSPEC_Non-Controlled_Terms` mediumtext,
  `Mesh_Terms` varchar(255) DEFAULT NULL,
  `Article_Citation_Count` varchar(255) DEFAULT NULL,
  `Reference_Count` varchar(255) DEFAULT NULL,
  `License` varchar(255) DEFAULT NULL,
  `Online_Date` varchar(255) DEFAULT NULL,
  `Issue_Date` varchar(255) DEFAULT NULL,
  `Meeting_Date` varchar(255) DEFAULT NULL,
  `Publisher` varchar(255) DEFAULT NULL,
  `Document_Identifier` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE table `author`(

    `Author_id`int default NULL,
    `Author_name` varchar(255) default NULL
)ENGINE=InnoDB DEFAULT CHARSET=utf8;


CREATE table `author_direction`(

    `Author_id`int default NULL,
    `Direction_id`int default  null
)ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE table `paper_direction`(

    `Paper_id`int default NULL,
    `Direction_id`int default  null
)ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE table `paper_affiliation`(

    `Paper_id`int default NULL,
    `Affiliation_id`int default  null
)ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE table `direction`(
    `Direction_id`int default  null,
    `Direction_name`varchar(255) default NULL
)ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE table `author_paper`(
    `Author_id`int default null,
    `Paper_id`int default null
    )ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE table `paper`(
    `Paper_id`int default null,
    `Conference_id`int default null,
    `Start_page`mediumtext default null,
    `End_page`mediumtext default null,
    `Title`mediumtext default null,
    `Abstract`mediumtext default null,
    `Citation_count`int default null,
    `Publication_year`int default null,
    `PDF_linking`mediumtext default null
)ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE table `author_affiliation`(
    `Author_id`int default  null,
    `Affiliation_id`int default null,
    `Year`int default null
)ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE table `affiliation`(

    `Affiliation_id`int default null,
    `Affiliation_name`mediumtext default null
    )ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE table `conference`(
    `Conference_id`int default null,
    `Conference_name`mediumtext default null
)ENGINE=InnoDB default charset=utf8;
SET FOREIGN_KEY_CHECKS = 1;
