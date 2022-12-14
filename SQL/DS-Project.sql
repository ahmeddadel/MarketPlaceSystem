-- --------------------------------------------------------
-- Host:                         127.0.0.1
-- Server version:               5.7.17-log - MySQL Community Server (GPL)
-- Server OS:                    Win64
-- HeidiSQL Version:             12.3.0.6589
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

-- Dumping structure for table marketplace.product
CREATE TABLE IF NOT EXISTS `product` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `userId` int(11) NOT NULL,
  `name` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `type` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `price` double NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_User` (`userId`),
  CONSTRAINT `FK_User` FOREIGN KEY (`userId`) REFERENCES `user` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- Dumping data for table marketplace.product: ~6 rows (approximately)
INSERT INTO `product` (`id`, `userId`, `name`, `type`, `price`) VALUES
	(1, 12, 'honda civic', 'vehicle', 100000),
	(2, 3, 'SAMSUNG 65 Inch TV', 'electronics', 1500),
	(4, 4, 'LAKESIDE LOUNGE SET', 'furniture', 2500),
	(5, 6, 'L-Shape Sofa', 'furniture', 3000),
	(6, 3, 'elliptical', 'training equipment', 600),
	(7, 3, 'Galaxy S-LOL', 'LOL-XD', 6500);

-- Dumping structure for table marketplace.transaction
CREATE TABLE IF NOT EXISTS `transaction` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `description` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `sellerId` int(11) NOT NULL,
  `buyerId` int(11) NOT NULL,
  `productId` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_Seller` (`sellerId`),
  KEY `FK_Buyer` (`buyerId`),
  KEY `FK_Product` (`productId`),
  CONSTRAINT `FK_Buyer` FOREIGN KEY (`buyerId`) REFERENCES `user` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `FK_Product` FOREIGN KEY (`productId`) REFERENCES `product` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `FK_Seller` FOREIGN KEY (`sellerId`) REFERENCES `user` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- Dumping data for table marketplace.transaction: ~4 rows (approximately)
INSERT INTO `transaction` (`id`, `description`, `sellerId`, `buyerId`, `productId`) VALUES
	(1, 'vehicle purchase', 2, 1, 1),
	(2, 'electronics purchase', 3, 2, 5),
	(3, 'furniture purchase', 6, 5, 4),
	(4, 'training equipmentPurchase', 5, 3, 6),
	(5, 'vehiclePurchase', 2, 12, 1);

-- Dumping structure for table marketplace.user
CREATE TABLE IF NOT EXISTS `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `email` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `password` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `balance` double NOT NULL DEFAULT '0',
  `location` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- Dumping data for table marketplace.user: ~10 rows (approximately)
INSERT INTO `user` (`id`, `name`, `email`, `password`, `balance`, `location`) VALUES
	(1, 'Vaso Kerenza', 'vasokerenza@hotmail.com', '123456', 600, 'USA'),
	(2, 'Mikey Brown', 'mikey_brown@gamil.com', '345657', 7000, 'UK'),
	(3, 'Brendon Castillo', 'brendon.castillo@gmail.com', '020298', 1900, 'Canada'),
	(4, 'Casper Kirk', 'casper_kirk1@outlook.com', '011254', 4500, 'USA'),
	(5, 'Isobella Little', 'isobella55little@hotmail.com', '924587', 5720, 'UK'),
	(6, 'Ellie-Mae Cross', 'crossmae2010@yahoo.com', '925484', 36000, 'UK'),
	(9, 'Ahmed Adel', 'ahmed@adel.com', '123654', 2500, 'EGP'),
	(10, 'Sakor', 'sakor@sakor.sakor', '654321', 2600, 'EGY'),
	(11, 'ahmed', 'ad@asd.com', '123654', 250, 'EGY'),
	(12, 'Lemon', 'lemon@gmail.com', '654aa32', 850100, 'EGY');

/*!40103 SET TIME_ZONE=IFNULL(@OLD_TIME_ZONE, 'system') */;
/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IFNULL(@OLD_FOREIGN_KEY_CHECKS, 1) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40111 SET SQL_NOTES=IFNULL(@OLD_SQL_NOTES, 1) */;
