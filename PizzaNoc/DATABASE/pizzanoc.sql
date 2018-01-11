-- --------------------------------------------------------
-- Host:                         127.0.0.1
-- Wersja serwera:               5.7.20-log - MySQL Community Server (GPL)
-- Serwer OS:                    Win64
-- HeidiSQL Wersja:              9.4.0.5125
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;


-- Zrzut struktury bazy danych pizzanoc
CREATE DATABASE IF NOT EXISTS `pizzanoc` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `pizzanoc`;

-- Zrzut struktury tabela pizzanoc.clients
CREATE TABLE IF NOT EXISTS `clients` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `city` varchar(100) NOT NULL,
  `street` varchar(100) NOT NULL,
  `house_number` varchar(10) NOT NULL,
  `apartment_number` varchar(10) NOT NULL,
  `phone_number` varchar(15) NOT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Zrzucanie danych dla tabeli pizzanoc.clients: ~0 rows (około)
/*!40000 ALTER TABLE `clients` DISABLE KEYS */;
/*!40000 ALTER TABLE `clients` ENABLE KEYS */;

-- Zrzut struktury tabela pizzanoc.employees
CREATE TABLE IF NOT EXISTS `employees` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `forename` varchar(50) NOT NULL,
  `surname` varchar(80) NOT NULL,
  `phone_number` varchar(15) NOT NULL,
  `day_salary` decimal(8,2) NOT NULL,
  `employment` datetime NOT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Zrzucanie danych dla tabeli pizzanoc.employees: ~0 rows (około)
/*!40000 ALTER TABLE `employees` DISABLE KEYS */;
/*!40000 ALTER TABLE `employees` ENABLE KEYS */;

-- Zrzut struktury tabela pizzanoc.ingredients
CREATE TABLE IF NOT EXISTS `ingredients` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(100) DEFAULT NULL,
  `quantity_in_stock` float DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Zrzucanie danych dla tabeli pizzanoc.ingredients: ~0 rows (około)
/*!40000 ALTER TABLE `ingredients` DISABLE KEYS */;
/*!40000 ALTER TABLE `ingredients` ENABLE KEYS */;

-- Zrzut struktury tabela pizzanoc.ordered_products
CREATE TABLE IF NOT EXISTS `ordered_products` (
  `order_ID` int(11) NOT NULL,
  `product_ID` int(11) NOT NULL,
  KEY `fk_order` (`order_ID`),
  KEY `fk_product` (`product_ID`),
  CONSTRAINT `fk_order` FOREIGN KEY (`order_ID`) REFERENCES `orders` (`ID`),
  CONSTRAINT `fk_product` FOREIGN KEY (`product_ID`) REFERENCES `products` (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Zrzucanie danych dla tabeli pizzanoc.ordered_products: ~0 rows (około)
/*!40000 ALTER TABLE `ordered_products` DISABLE KEYS */;
/*!40000 ALTER TABLE `ordered_products` ENABLE KEYS */;

-- Zrzut struktury tabela pizzanoc.orders
CREATE TABLE IF NOT EXISTS `orders` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `data` datetime NOT NULL,
  `status` enum('przyjęte','wysłane','odmówione','odebrane','nieodebrane') NOT NULL,
  `fullpirce` decimal(10,2) unsigned NOT NULL,
  `card_payment` enum('tak','nie') NOT NULL,
  `phone_number` varchar(15) NOT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

-- Zrzucanie danych dla tabeli pizzanoc.orders: ~5 rows (około)
/*!40000 ALTER TABLE `orders` DISABLE KEYS */;
INSERT INTO `orders` (`ID`, `data`, `status`, `fullpirce`, `card_payment`, `phone_number`) VALUES
	(1, '2003-02-07 00:00:00', 'przyjęte', 150.00, 'tak', '530134551'),
	(2, '2005-02-13 00:00:00', 'wysłane', 6346.00, 'tak', '531692381'),
	(3, '2011-11-05 00:00:00', 'odebrane', 25.00, 'nie', '140924133'),
	(4, '2016-10-05 00:00:00', 'przyjęte', 7246.00, 'tak', '530923851'),
	(5, '2003-02-02 00:00:00', 'przyjęte', 142.00, 'nie', '630241551');
/*!40000 ALTER TABLE `orders` ENABLE KEYS */;

-- Zrzut struktury tabela pizzanoc.products
CREATE TABLE IF NOT EXISTS `products` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(100) DEFAULT NULL,
  `price_with_vat` decimal(10,2) DEFAULT NULL,
  `price_without_vat` decimal(10,2) DEFAULT NULL,
  `weight` float DEFAULT NULL,
  `diameter` int(11) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Zrzucanie danych dla tabeli pizzanoc.products: ~0 rows (około)
/*!40000 ALTER TABLE `products` DISABLE KEYS */;
/*!40000 ALTER TABLE `products` ENABLE KEYS */;

-- Zrzut struktury tabela pizzanoc.product_ingredients
CREATE TABLE IF NOT EXISTS `product_ingredients` (
  `product_ID` int(11) NOT NULL,
  `ingredient_ID` int(11) NOT NULL,
  KEY `fk_secondProduct` (`product_ID`),
  KEY `fk_ingredients` (`ingredient_ID`),
  CONSTRAINT `fk_ingredients` FOREIGN KEY (`ingredient_ID`) REFERENCES `ingredients` (`ID`),
  CONSTRAINT `fk_secondProduct` FOREIGN KEY (`product_ID`) REFERENCES `products` (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Zrzucanie danych dla tabeli pizzanoc.product_ingredients: ~0 rows (około)
/*!40000 ALTER TABLE `product_ingredients` DISABLE KEYS */;
/*!40000 ALTER TABLE `product_ingredients` ENABLE KEYS */;

/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
