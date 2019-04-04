-- --------------------------------------------------------
-- Host:                         localhost
-- Server version:               10.3.9-MariaDB - mariadb.org binary distribution
-- Server OS:                    Win64
-- HeidiSQL Version:             9.5.0.5293
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;

-- Dumping structure for table notes_app.role_permission
CREATE TABLE IF NOT EXISTS `role_permission` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `role` varchar(50) NOT NULL,
  `create` bit(1) NOT NULL,
  `read` bit(1) NOT NULL,
  `update` bit(1) NOT NULL,
  `delete` bit(1) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;

-- Dumping data for table notes_app.role_permission: ~0 rows (approximately)
/*!40000 ALTER TABLE `role_permission` DISABLE KEYS */;
INSERT INTO `role_permission` (`id`, `role`, `create`, `read`, `update`, `delete`) VALUES
	(1, 'Admin', b'1', b'1', b'1', b'1'),
	(2, 'Developer', b'1', b'1', b'1', b'0'),
	(3, 'Reporter', b'0', b'1', b'0', b'0');
/*!40000 ALTER TABLE `role_permission` ENABLE KEYS */;

-- Dumping structure for table notes_app.user
CREATE TABLE IF NOT EXISTS `user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `user_name` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  `role_permission_id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_user_role_permission` (`role_permission_id`),
  CONSTRAINT `FK_user_role_permission` FOREIGN KEY (`role_permission_id`) REFERENCES `role_permission` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;

-- Dumping data for table notes_app.user: ~0 rows (approximately)
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` (`id`, `user_name`, `password`, `role_permission_id`) VALUES
	(1, 'Rahul', '123', 1),
	(2, 'Rakesh', '123', 2),
	(3, 'Ankur', '123', 3);
/*!40000 ALTER TABLE `user` ENABLE KEYS */;

/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
