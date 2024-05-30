/*
SQLyog Community v13.1.5  (64 bit)
MySQL - 8.0.33 : Database - therapeuticchat
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`therapeuticchat` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;

USE `therapeuticchat`;

/*Data for the table `auth_group` */

/*Data for the table `auth_group_permissions` */

/*Data for the table `auth_permission` */

/*Data for the table `auth_user` */

insert  into `auth_user`(`id`,`password`,`last_login`,`is_superuser`,`username`,`first_name`,`last_name`,`email`,`is_staff`,`is_active`,`date_joined`) values 
(1,'pbkdf2_sha256$600000$gpcIorEN7dqwRAZuepIynk$gjLP9BywgqBqc20gxRONAKmGMRxtpi0HzMORtrX3ld4=','2023-11-02 11:09:18.450309',1,'admin','','','admin@gmail.com',1,1,'2023-11-02 03:18:47.884796');

/*Data for the table `auth_user_groups` */

/*Data for the table `auth_user_user_permissions` */

/*Data for the table `django_admin_log` */

/*Data for the table `django_content_type` */

/*Data for the table `django_migrations` */

/*Data for the table `django_session` */

insert  into `django_session`(`session_key`,`session_data`,`expire_date`) values 
('7sj4szzrby7ate4zihqpewgsmqkrism1','.eJxVjssOgjAURP-la9NwFftw6d5vILe9g6CkTWhZGf9dMGzYnpk5mY-aRlG380l1vNShWwrmbiOK1IEFjm-kLZAXp2fWMac6j0FvFb2nRT-yYLrv3YNg4DKsayC2ZHu-NATHBNgQghgyvfEQiq0VZgc4I56biObaw9toCS2JN26Vov7vfX-riD0-:1qyOsE:OCkO5lg2nLnGaitKz8xnh0NlIzjV12VWVIdw4GmYJ7A','2023-11-16 04:00:02.810429'),
('q4oevfa12g2s6gk1k9b89hsf6xwgt4l2','.eJxVjDsOwjAQBe_iGlleEfyhpOcM1tr7ggORI-VTRdwdLKVJOzPv7WocRN3poiJva4nbgjk2okidWOL8QW1C3lxfk85TXech6Zbowy76OQnGx9GeDgov5b8Gckeu56sheCbApZTEku1tgFDunDB7wFsJbDLMrUdw2RE6kmC9-v4AjSI7RQ:1qyTFx:3t5DUzhbGgd0VRE94sRD_LFf5wkBC95xbf0jjoC-N9c','2023-11-16 08:40:49.599096'),
('wv47gjdb4g1fnh3ua04ldcb3e0796z5p','.eJxVjDsOwjAQBe_iGllZMP5Q0nMGa-19IYHIluKkQtwdIqVJOzPvfdQ0irqdTyryugxxbZjjRhSpA0uc3yibkBeXZ9W5lmUek94SvdumH1Uw3ff2cDBwG_5rIBtyPV86gmcCXEpJLNneBghl44TZA95K4C6ju_YILjuCIQnWq-8Pjc47Rg:1qyVZe:pgH4TgbvbQ3H0M2y7HVj8XGNB4bMauzvw2Ubu-fevnw','2023-11-16 11:09:18.456422');

/*Data for the table `therapeticalchatbotapp_appointment` */

insert  into `therapeticalchatbotapp_appointment`(`id`,`status`,`date`,`SCHEDULE_id`,`USER_id`) values 
(2,'accepted','2023-11-02',3,2);

/*Data for the table `therapeticalchatbotapp_chat` */

insert  into `therapeticalchatbotapp_chat`(`id`,`msg`,`date`,`fromid_id`,`toid_id`) values 
(1,'hello','2023-11-02',4,2),
(2,'daa','2023-11-02',4,2);

/*Data for the table `therapeticalchatbotapp_chatbot` */

insert  into `therapeticalchatbotapp_chatbot`(`id`,`question`,`answer`,`USER_id`) values 
(2,'naja\n','predvoshiš\'enie\n\nPredvoshiš\'enie - eto predčuvstvie togo, čto proizojdet pozže. Ono možet byt\' odnovremenno i konstruktivnym, i negativnym. Naprimer, často ljudi mogut počuvstvovat\', čto proizojdet čto-to horošee ili plohoe, prežde čem eto na samom dele proishodit. Eto proishodit potomu, čto naši mozgi obrazujut mnogo logičeskih svjazej, i esli my čuvstvuem, čto my čuvstvuem, to často možem polučit\' predvoshiš\'enie.',2);

/*Data for the table `therapeticalchatbotapp_comment` */

insert  into `therapeticalchatbotapp_comment`(`id`,`comments`,`POST_id`,`USER_id`) values 
(1,'adipoli',1,2);

/*Data for the table `therapeticalchatbotapp_complaint` */

insert  into `therapeticalchatbotapp_complaint`(`id`,`complaint`,`date`,`reply`,`USER_id`) values 
(1,'complaints ','2023-11-02','reply',2);

/*Data for the table `therapeticalchatbotapp_dataset` */

insert  into `therapeticalchatbotapp_dataset`(`id`,`question`,`answer`) values 
(2,'Hello','Hi');

/*Data for the table `therapeticalchatbotapp_like` */

insert  into `therapeticalchatbotapp_like`(`id`,`date`,`POSTSS_id`,`USER_id`) values 
(1,'2023-11-02',1,2),
(2,'2023-11-02',2,2);

/*Data for the table `therapeticalchatbotapp_login` */

insert  into `therapeticalchatbotapp_login`(`id`,`username`,`password`,`type`) values 
(1,'admin','admin','admin'),
(2,'kalindhi','1234','psychartist'),
(3,'anu','123','user'),
(4,'naja','123','user');

/*Data for the table `therapeticalchatbotapp_postss` */

insert  into `therapeticalchatbotapp_postss`(`id`,`post`,`details`,`USER_id`) values 
(1,'ipbg-01.jpeg','vxgdgd hfhfjf jfjfjh',1),
(2,'a6172213-7cfc-4882-adfa-ee615871ede1.jpg','',1),
(3,'Screenshot_20230828-164051.png','bfnfbjfjh',2);

/*Data for the table `therapeticalchatbotapp_psychartist` */

insert  into `therapeticalchatbotapp_psychartist`(`id`,`fname`,`lname`,`age`,`gender`,`place`,`post`,`pin`,`phone`,`email`,`LOGIN_id`,`image`) values 
(1,'kalindhi','krishna','32','female','malappuram','malappuram','674532',7890789099,'kalindhi@gmail.com',2,'college.jpg');

/*Data for the table `therapeticalchatbotapp_rating` */

insert  into `therapeticalchatbotapp_rating`(`id`,`ratingg`,`date`,`LOGIN_id`) values 
(1,3,'2023-11-02',4),
(2,4,'2023-11-02',2),
(3,2.5,'2023-11-02',2),
(4,0,'2023-11-02',4);

/*Data for the table `therapeticalchatbotapp_schedule` */

insert  into `therapeticalchatbotapp_schedule`(`id`,`date`,`fromtime`,`totime`,`PSYCHARTIST_id`) values 
(2,'2023-11-14','11:33:00.000000','05:34:00.000000',1),
(3,'2023-11-13','02:29:00.000000','05:29:00.000000',1);

/*Data for the table `therapeticalchatbotapp_tips` */

insert  into `therapeticalchatbotapp_tips`(`id`,`tip`,`details`,`PSYCHARTIST_id`) values 
(1,'tipssssssspppp','smfdjfh sjfrhejfr jrfhejfre',1);

/*Data for the table `therapeticalchatbotapp_user` */

insert  into `therapeticalchatbotapp_user`(`id`,`fname`,`lname`,`age`,`gender`,`place`,`post`,`pin`,`phone`,`email`,`LOGIN_id`,`image`) values 
(1,'anupama ','p','19','Female','malappuram ','malappuram ','675432',8970653421,'anupama@gmail.com',3,'stock-vector-together-family-parent-and-children-logo-vector-icon-illustration-1708766572.jpg'),
(2,'naja','cp','21','Female','malappuram ','malappuram ','675432',8970654321,'naja@gmail.com',4,'lbg-01.jpeg');

/*Data for the table `therapeticalchatbotapp_vedio` */

insert  into `therapeticalchatbotapp_vedio`(`id`,`vedios`,`description`,`date`,`PSYCHARTIST_id`) values 
(1,'WIN_20231011_10_18_11_Pro_9cPXAYH.mp4','rdtfghjk gfhbjnkm','2023-11-02',1),
(2,'[AML] Madhura Manohara Moham 2023 Malayalam 540p WEB-DL_hfCav9R.mkv','dfghjkl','2023-11-02',1),
(4,'- Por Thozhil (2023) Tamil HQ HDRip - 400MB - x264 - AAC.mkv','oooo','2023-11-02',1);

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
