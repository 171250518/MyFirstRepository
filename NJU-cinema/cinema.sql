-- MySQL dump 10.13  Distrib 5.7.25, for Win64 (x86_64)
--
-- Host: localhost    Database: cinema
-- ------------------------------------------------------
-- Server version	5.7.25-log

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `activity`
--

DROP TABLE IF EXISTS `activity`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `activity` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `activity_name` varchar(45) NOT NULL,
  `a_description` varchar(255) NOT NULL,
  `end_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `coupon_id` int(11) DEFAULT NULL,
  `start_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `activity`
--

LOCK TABLES `activity` WRITE;
/*!40000 ALTER TABLE `activity` DISABLE KEYS */;
INSERT INTO `activity` VALUES (2,'春季外卖节','春季外卖节','2019-04-23 17:55:59',5,'2019-04-20 17:55:59'),(3,'春季外卖节','春季外卖节','2019-04-23 17:55:59',6,'2019-04-20 17:55:59'),(4,'测试活动','测试活动','2019-04-26 16:00:00',8,'2019-04-20 16:00:00');
/*!40000 ALTER TABLE `activity` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `activity_movie`
--

DROP TABLE IF EXISTS `activity_movie`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `activity_movie` (
  `activity_id` int(11) DEFAULT NULL,
  `movie_id` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `activity_movie`
--

LOCK TABLES `activity_movie` WRITE;
/*!40000 ALTER TABLE `activity_movie` DISABLE KEYS */;
INSERT INTO `activity_movie` VALUES (2,10),(2,11),(2,16),(4,10);
/*!40000 ALTER TABLE `activity_movie` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `charge_hist`
--

DROP TABLE IF EXISTS `charge_hist`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `charge_hist` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `charge` int(11) NOT NULL,
  `balance_befo` float NOT NULL,
  `balance_after` float NOT NULL,
  `date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `vip_card_id` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `charge_hist`
--

LOCK TABLES `charge_hist` WRITE;
/*!40000 ALTER TABLE `charge_hist` DISABLE KEYS */;
INSERT INTO `charge_hist` VALUES (2,100,0,115,'2019-06-07 05:45:24',2),(3,50,0,50,'2019-06-07 06:34:13',3),(4,100,50,165,'2019-06-07 07:01:44',3),(5,10,165,175,'2019-06-07 07:07:34',3),(6,120,175,335,'2019-06-07 07:57:27',3);
/*!40000 ALTER TABLE `charge_hist` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `consume_hist`
--

DROP TABLE IF EXISTS `consume_hist`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `consume_hist` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `consume` float NOT NULL,
  `balance_befo` float NOT NULL,
  `balance_after` float NOT NULL,
  `date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `vip_card_id` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `consume_hist`
--

LOCK TABLES `consume_hist` WRITE;
/*!40000 ALTER TABLE `consume_hist` DISABLE KEYS */;
/*!40000 ALTER TABLE `consume_hist` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `coupon`
--

DROP TABLE IF EXISTS `coupon`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `coupon` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `description` varchar(255) DEFAULT NULL,
  `name` varchar(45) DEFAULT NULL,
  `target_amount` float DEFAULT NULL,
  `discount_amount` float DEFAULT NULL,
  `start_time` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' ON UPDATE CURRENT_TIMESTAMP,
  `end_time` timestamp NULL DEFAULT NULL,
  `consume_req` float NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `coupon`
--

LOCK TABLES `coupon` WRITE;
/*!40000 ALTER TABLE `coupon` DISABLE KEYS */;
INSERT INTO `coupon` VALUES (1,'测试优惠券','春季电影节',20,5,'2019-04-20 17:47:54','2019-04-23 17:47:59',0),(5,'测试优惠券','品质联盟',30,4,'2019-04-20 21:14:46','2019-04-24 21:14:51',0),(6,'春节电影节优惠券','电影节优惠券',50,10,'2019-04-20 21:15:11','2019-04-21 21:14:56',0),(8,'测试优惠券','123',100,99,'2019-04-20 16:00:00','2019-04-26 16:00:00',0);
/*!40000 ALTER TABLE `coupon` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `coupon_user`
--

DROP TABLE IF EXISTS `coupon_user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `coupon_user` (
  `coupon_id` int(11) NOT NULL,
  `user_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `coupon_user`
--

LOCK TABLES `coupon_user` WRITE;
/*!40000 ALTER TABLE `coupon_user` DISABLE KEYS */;
/*!40000 ALTER TABLE `coupon_user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `hall`
--

DROP TABLE IF EXISTS `hall`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `hall` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `hallname` varchar(255) DEFAULT NULL,
  `hallrow` int(11) DEFAULT NULL,
  `hallcolumn` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `hall`
--

LOCK TABLES `hall` WRITE;
/*!40000 ALTER TABLE `hall` DISABLE KEYS */;
INSERT INTO `hall` VALUES (5,'1号厅',9,14);
/*!40000 ALTER TABLE `hall` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `movie`
--

DROP TABLE IF EXISTS `movie`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `movie` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `poster_url` varchar(255) DEFAULT NULL,
  `director` varchar(255) DEFAULT NULL,
  `screen_writer` varchar(255) DEFAULT NULL,
  `starring` varchar(255) DEFAULT NULL,
  `type` varchar(255) DEFAULT NULL,
  `country` varchar(255) DEFAULT NULL,
  `language` varchar(255) DEFAULT NULL,
  `length` int(11) NOT NULL,
  `start_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `name` varchar(255) NOT NULL,
  `description` text,
  `status` int(11) DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=30 DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `movie`
--

LOCK TABLES `movie` WRITE;
/*!40000 ALTER TABLE `movie` DISABLE KEYS */;
INSERT INTO `movie` VALUES (21,'http://img4.imgtn.bdimg.com/it/u=317831018,403768514&fm=11&gp=0.jpg','西蒙·金伯格','西蒙·金伯格','詹姆斯·麦卡沃伊，迈克尔·法斯宾德，詹妮弗·劳伦斯，尼古拉斯·霍尔特，索菲·特纳，杰西卡·查斯坦','动作、冒险、科幻','美国','英语',113,'2019-06-06 14:22:51','X战警：黑凤凰','在《X战警：黑凤凰》中，X战警将面临他们最强大又可怕的敌人：他们的一位成员——琴·葛蕾。在一次太空救援任务中，琴被一股神秘的宇宙力量击中险些死亡。返回家中后，这股力量令她变得无比强大的同时也极不稳定。琴在与体内能量搏斗期间释放了自己无法控制也无法领会的力量。随着不断失去控制，琴伤害了她的至亲之人。这一举动割裂了维系X战警的纽带。现在随着X战警家族渐渐分崩离析，他们必须找到办法重新团结一心——不仅为拯救琴的灵魂，更为地球的安全。因为外星势力正意图武器化琴体内的力量并以此统治银河系。',0),(22,'https://gss3.bdstatic.com/-Po3dSag_xI4khGkpoWK1HF6hhy/baike/w%3D268%3Bg%3D0/sign=f3cdfbb8387adab43dd01c45b3efd421/8d5494eef01f3a2926219d5e9725bc315d607cb0.jpg','王晶、关智耀','王晶、吕冠南','梁家辉，古天乐，林家栋，任达华，邱意浓，叶相明','剧情、动作、犯罪','中国内地','粤语、汉语普通话',103,'2019-06-05 16:00:00','追龙Ⅱ','悍匪龙志强，在香港回归前，乘香港英政府不作为，而屡犯巨案，先后绑架富豪利家及雷家之长子，勒索超过二十亿元，事主怕被报复, 交赎款后都不敢报警。中国公安部极为关注，与香港警方合力，派香港警员何天卧底潜入龙志强犯罪团伙，发现他正策骑绑架澳门富豪贺不凡，终勇擒龙志强，救出贺不凡。',0),(23,'https://gss1.bdstatic.com/-vo3dSag_xI4khGkpoWK1HF6hhy/baike/w%3D268%3Bg%3D0/sign=87e7f9876cd0f703e6b292da30c13600/a9d3fd1f4134970a3dbabfbf9bcad1c8a6865d50.jpg','迈克尔·道赫蒂','迈克尔·道赫蒂、麦克思·鲍伦斯坦、扎克·希尔兹','凯尔·钱德勒，维拉·法梅加，米莉·博比·布朗，渡边谦，章子怡，布莱德利·惠特福德，莎莉·霍金斯','动作、科幻、冒险','美国','英语',132,'2019-05-30 16:00:00','哥斯拉2：怪兽之王','《哥斯拉2：怪兽之王》承接《金刚：骷髅岛》，“帝王计划”神秘政府组织将继续研究接触更多怪兽，包括哥斯拉、拉顿、摩斯拉、以及王者基多拉（《金刚：骷髅岛》彩蛋出现的四大巨兽）！尤其哥斯拉将对阵他的终极敌人——三头巨兽王者基多拉！当这些被认为是神话中的史前巨兽再次崛起，它们将再度上演王者争霸，人类的命运掌握在它们手里。',0),(24,'','','','','','','',128,'2019-06-07 02:24:53','阿拉丁神灯','',1),(25,'https://gss3.bdstatic.com/-Po3dSag_xI4khGkpoWK1HF6hhy/baike/w%3D268%3Bg%3D0/sign=3758eeebaac27d1ea5263cc223eeca53/c8ea15ce36d3d5395567538c3487e950352ab023.jpg','F·加里·格雷','Lowell Cunningham、马特·霍洛威、亚瑟·马科姆','克里斯·海姆斯沃斯，泰莎·汤普森，连姆·尼森，拉菲·斯波','科幻、动作、喜剧','美国','英语',115,'2019-06-13 16:00:00','黑衣人：全球通缉','全球最具影响力的经典科幻IP《黑衣人》全面升级！科幻电影之父史蒂文·斯皮尔伯格联手《速度与激情8》导演F·加里·格雷，金牌班底鼎力巨献！英国黑衣人总部王牌探员H（锤哥）与新晋探员M（泰莎·汤普森）在阻止外星团伙入侵的过程中意外铲除了隐藏在黑衣人组织中的内奸，成功拯救世界 。 ',0),(26,'https://gss0.bdstatic.com/94o3dSag_xI4khGkpoWK1HF6hhy/baike/w%3D268%3Bg%3D0/sign=2d91026f1e3853438ccf8027ab28d743/0e2442a7d933c895c9f86978df1373f08202002a.jpg','乔什·库雷','约翰·拉塞特、安德鲁·斯坦顿 、彼特·道格特、李·昂克里奇、Stephany Folsom','汤姆·汉克斯（配音），蒂姆·艾伦（配音）','动画、爱情、冒险、喜剧、家庭','美国','英语',95,'2019-06-20 16:00:00','玩具总动员4','《玩具总动员4》将是皮克斯的动画系列电影的第四部，故事承接《玩具总动员3》，胡迪与巴斯光年等玩具们得到了新的朋友与主人邦妮。在这一季中，邦妮在学校制作了一位的新玩具—fokry，但是fokry坚持自己是个“叉子”并在半夜中逃跑，前去追逐的胡迪与fokry展开了一场公路之旅，在旅途中，胡迪与fokry遇到了新的对手，也重遇了曾经的朋友。与此同时，胡迪还将面临一个非常艰难的选择.....。在这一路上，两人有惊又有喜，胡迪警长到底会作出何种选择？是坚守初心还是放飞自我？而众玩具们又将获得怎样的结局呢？',0),(27,'https://gss0.bdstatic.com/-4o3dSag_xI4khGkpoWK1HF6hhy/baike/w%3D268%3Bg%3D0/sign=246c3a809bcad1c8d0bbfb2147050034/9922720e0cf3d7ca6fe915d9fc1fbe096b63a905.jpg','乔·沃茨','史蒂夫·迪特寇，斯坦·李','汤姆·赫兰德，赞达亚·科尔曼','动作，冒险，喜剧，科幻','美国','英语',135,'2019-06-27 16:00:00','蜘蛛侠：英雄远征','最受关注的漫威超级英雄片《蜘蛛侠:英雄远征》依旧由导演乔沃茨执导，汤姆:赫兰德继续饰演蜘蛛侠彼得帕克。故事承接《复仇者联盟4：终局之战》，此次蜘蛛侠将前往欧洲展开新的征程，并将对抗由杰克:吉伦哈尔加盟饰演的神秘客。赞达亚、雅各布巴特朗、托尼雷沃罗利等原班人马也将悉数回归。本片将于2019年6月28日在中国大陆上映，经历了第一部的成长经历后，小蜘蛛在失去钢铁侠之后又会面临怎样的危机?敬请期待。',0),(28,'https://gss1.bdstatic.com/9vo3dSag_xI4khGkpoWK1HF6hhy/baike/w%3D268%3Bg%3D0/sign=b372bd3285d4b31cf03c93bdbfed4042/2cf5e0fe9925bc31a5ffa7e950df8db1cb137025.jpg','安东尼·罗素、乔·罗素','克里斯托弗·马库斯，斯蒂芬·麦克菲利，斯坦·李，杰克·科比','小罗伯特·唐尼，克里斯·埃文斯，马克·鲁法洛，克里斯·海姆斯沃斯，斯嘉丽·约翰逊，杰瑞米·雷纳','动作，科幻','美国','英语',181,'2019-06-06 14:40:51','复仇者联盟4：终局之战','故事发生在《复仇者联盟3：无限战争》之后，灭霸使用无限手套的力量，造成全宇宙一半的生命随机消失，有的人永远失去了挚爱和家人，复仇者联盟部分成员也因此消失了。在泰坦星上和灭霸结束了战斗的钢铁侠托尼和星云，在返回地球的途中遭遇了缺水和没有食物的境地，幸得惊奇队长出手相救。回到地球的惊奇队长与其他英雄们，决定前去打倒灭霸并寻回宝石，却发现灭霸已经将宝石毁掉了。愤怒的雷神砍下了灭霸的脑袋，复仇者们从此不再勇猛如初。五年过去了，穿越到量子领域逃过一劫的斯科特·朗（蚁人）来到了复仇者总部，并告知众人，他们仍然有机会可以逆转灭霸的所作所为。',1),(29,'https://ss2.bdstatic.com/70cFvnSh_Q1YnxGkpoWK1HF6hhy/it/u=3234357809,1256841247&fm=11&gp=0.jpg','罗伯·莱特曼','德里克·康纳利，罗伯·莱特曼','瑞安·雷诺兹（配音），渡边谦，贾斯提斯·史密斯','家庭、喜剧、动作、冒险、悬疑、科幻','美国','英语',104,'2019-06-06 14:43:57','大侦探皮卡丘','电影《大侦探皮卡丘》改编自任天堂3DS同名游戏，蒂姆·古德曼为寻找下落不明的父亲来到莱姆市，意外与父亲的前宝可梦搭档大侦探皮卡丘相遇，并惊讶地发现自己是唯一能听懂皮卡丘说话的人类，他们决定组队踏上揭开真相的刺激冒险之路。探案过程中他们邂逅了各式各样的宝可梦，并意外发现了一个足以毁灭整个宝可梦宇宙的惊天阴谋。',1);
/*!40000 ALTER TABLE `movie` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `movie_like`
--

DROP TABLE IF EXISTS `movie_like`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `movie_like` (
  `movie_id` int(11) NOT NULL,
  `user_id` int(11) NOT NULL,
  `like_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`movie_id`,`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `movie_like`
--

LOCK TABLES `movie_like` WRITE;
/*!40000 ALTER TABLE `movie_like` DISABLE KEYS */;
INSERT INTO `movie_like` VALUES (23,16,'2019-06-07 02:25:37');
/*!40000 ALTER TABLE `movie_like` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `refund_promotion`
--

DROP TABLE IF EXISTS `refund_promotion`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `refund_promotion` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `Low` int(11) DEFAULT '0',
  `High` int(11) DEFAULT '0',
  `canOrnot` int(11) DEFAULT '0',
  `refundRate` double DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `refund_promotion`
--

LOCK TABLES `refund_promotion` WRITE;
/*!40000 ALTER TABLE `refund_promotion` DISABLE KEYS */;
INSERT INTO `refund_promotion` VALUES (11,12,26,0,0.1);
/*!40000 ALTER TABLE `refund_promotion` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `schedule`
--

DROP TABLE IF EXISTS `schedule`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `schedule` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `hall_id` int(11) NOT NULL,
  `movie_id` int(11) NOT NULL,
  `start_time` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' ON UPDATE CURRENT_TIMESTAMP,
  `end_time` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  `fare` double NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `schedule`
--

LOCK TABLES `schedule` WRITE;
/*!40000 ALTER TABLE `schedule` DISABLE KEYS */;
/*!40000 ALTER TABLE `schedule` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ticket`
--

DROP TABLE IF EXISTS `ticket`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ticket` (
  `user_id` int(11) DEFAULT NULL,
  `schedule_id` int(11) DEFAULT NULL,
  `column_index` int(11) DEFAULT NULL,
  `row_index` int(11) DEFAULT NULL,
  `state` tinyint(4) DEFAULT NULL,
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `time` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ticket`
--

LOCK TABLES `ticket` WRITE;
/*!40000 ALTER TABLE `ticket` DISABLE KEYS */;
/*!40000 ALTER TABLE `ticket` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(50) NOT NULL,
  `password` varchar(50) NOT NULL,
  `identity` int(1) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`),
  UNIQUE KEY `user_id_uindex` (`id`),
  UNIQUE KEY `user_username_uindex` (`username`)
) ENGINE=InnoDB AUTO_INCREMENT=24 DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (1,'testname','123456',0),(3,'test','123456',0),(5,'test1','123456',0),(7,'test121','123456',0),(8,'root','123456',2),(10,'roottt','123123',0),(12,'zhourui','123456',0),(13,'abc123','abc123',0),(15,'dd','123',0),(16,'1234','123456',0),(17,'12345','123456',0),(18,'123456','123456',0),(19,'1234567','123456',0),(20,'1111','123456',0),(21,'11111','123456',0),(22,'manager1','123456',2);
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `view`
--

DROP TABLE IF EXISTS `view`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `view` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `day` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `view`
--

LOCK TABLES `view` WRITE;
/*!40000 ALTER TABLE `view` DISABLE KEYS */;
INSERT INTO `view` VALUES (1,7);
/*!40000 ALTER TABLE `view` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `vip_card`
--

DROP TABLE IF EXISTS `vip_card`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `vip_card` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) DEFAULT NULL,
  `balance` float DEFAULT NULL,
  `join_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  UNIQUE KEY `vip_card_user_id_uindex` (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `vip_card`
--

LOCK TABLES `vip_card` WRITE;
/*!40000 ALTER TABLE `vip_card` DISABLE KEYS */;
INSERT INTO `vip_card` VALUES (2,17,115,'2019-06-07 05:45:24'),(3,16,335,'2019-06-07 07:57:27');
/*!40000 ALTER TABLE `vip_card` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `vip_card_set`
--

DROP TABLE IF EXISTS `vip_card_set`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `vip_card_set` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `price` float NOT NULL,
  `target` float NOT NULL,
  `discount` float NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `vip_card_set`
--

LOCK TABLES `vip_card_set` WRITE;
/*!40000 ALTER TABLE `vip_card_set` DISABLE KEYS */;
INSERT INTO `vip_card_set` VALUES (1,25,120,40);
/*!40000 ALTER TABLE `vip_card_set` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2019-06-07 16:04:29
