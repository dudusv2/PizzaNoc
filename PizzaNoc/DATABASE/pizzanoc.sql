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
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8;

-- Zrzucanie danych dla tabeli pizzanoc.ingredients: ~18 rows (około)
/*!40000 ALTER TABLE `ingredients` DISABLE KEYS */;
INSERT IGNORE INTO `ingredients` (`ID`, `name`, `quantity_in_stock`) VALUES
	(1, 'ser', 10),
	(2, 'szynka', 6),
	(3, 'pieczarki', 5),
	(4, 'salami', 2),
	(5, 'ananas', 2),
	(6, 'kurczak', 3),
	(7, 'kukurydza', 1.5),
	(8, 'papryka', 1),
	(9, 'pomidor', 2),
	(10, 'chorizo', 1.5),
	(11, 'chilli', 0.3),
	(12, 'oliwki', 0.5),
	(13, 'ser pleśniowy', 1),
	(14, 'camembert', 0.6),
	(15, 'ser feta', 1),
	(16, 'cebula', 2),
	(17, 'boczek', 2),
	(18, 'jajko', 1.5);
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

-- Zrzucanie danych dla tabeli pizzanoc.ordered_products: ~18 rows (około)
/*!40000 ALTER TABLE `ordered_products` DISABLE KEYS */;
INSERT IGNORE INTO `ordered_products` (`order_ID`, `product_ID`) VALUES
	(1, 1),
	(1, 3),
	(1, 8),
	(1, 4),
	(2, 52),
	(2, 1),
	(2, 4),
	(3, 15),
	(3, 56),
	(3, 60),
	(3, 3),
	(4, 13),
	(4, 14),
	(4, 1),
	(5, 2),
	(5, 3),
	(5, 4),
	(5, 55);
/*!40000 ALTER TABLE `ordered_products` ENABLE KEYS */;

-- Zrzut struktury tabela pizzanoc.orders
CREATE TABLE IF NOT EXISTS `orders` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `data` datetime NOT NULL,
  `status` enum('przyjęte','wysłane','odmówione','odebrane','nieodebrane') NOT NULL,
  `fullpirce` decimal(10,2) unsigned NOT NULL,
  `card_payment` enum('tak','nie') NOT NULL,
  `phone_number` varchar(15) NOT NULL,
  `adres` varchar(250) NOT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

-- Zrzucanie danych dla tabeli pizzanoc.orders: ~5 rows (około)
/*!40000 ALTER TABLE `orders` DISABLE KEYS */;
INSERT IGNORE INTO `orders` (`ID`, `data`, `status`, `fullpirce`, `card_payment`, `phone_number`, `adres`) VALUES
	(1, '2003-02-07 00:00:00', 'nieodebrane', 150.00, 'tak', '530134551', 'Wyczółkowskiego/15/3/Jelenia Góra'),
	(2, '2005-02-13 00:00:00', 'wysłane', 6346.00, 'tak', '531692381', 'Pijarska/148/17/Jelenia Góra'),
	(3, '2011-11-05 00:00:00', 'wysłane', 25.00, 'nie', '140924133', 'Osiedle Robotnicze/2/3/Jelenia Góra'),
	(4, '2016-10-05 00:00:00', 'odebrane', 7246.00, 'tak', '530923851', 'I Maja/12/45/Jelenia Góra'),
	(5, '2003-02-02 00:00:00', 'przyjęte', 142.00, 'nie', '630241551', 'Szybka/12/1/Jelenia Góra');
/*!40000 ALTER TABLE `orders` ENABLE KEYS */;

-- Zrzut struktury tabela pizzanoc.products
CREATE TABLE IF NOT EXISTS `products` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(100) DEFAULT NULL,
  `price` decimal(10,2) DEFAULT NULL,
  `diameter` int(11) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=66 DEFAULT CHARSET=utf8;

-- Zrzucanie danych dla tabeli pizzanoc.products: ~30 rows (około)
/*!40000 ALTER TABLE `products` DISABLE KEYS */;
INSERT IGNORE INTO `products` (`ID`, `name`, `price`, `diameter`) VALUES
	(1, 'Margherita', 12.00, 32),
	(2, 'Funghi', 13.00, 32),
	(3, 'Prosciutto', 13.00, 32),
	(4, 'Salami', 14.00, 32),
	(5, 'Capriciosa', 14.00, 32),
	(6, 'Hawajska', 14.00, 32),
	(7, 'Gambino', 15.00, 32),
	(8, 'Nocny Marek', 15.00, 32),
	(9, 'Vegetariana', 15.00, 32),
	(10, 'Kolorowa', 15.00, 32),
	(11, 'Chilli', 15.00, 32),
	(12, 'Droga Mleczna', 16.00, 32),
	(13, 'Księżycowa', 16.00, 32),
	(14, 'Cztery Sery', 16.00, 32),
	(15, 'Mamma Mia!', 16.00, 32),
	(51, 'Margherita', 25.00, 45),
	(52, 'Funghi', 27.00, 45),
	(53, 'Prosciutto', 27.00, 45),
	(54, 'Salami', 28.00, 45),
	(55, 'Capriciosa', 28.00, 45),
	(56, 'Hawajska', 28.00, 45),
	(57, 'Gambino', 30.00, 45),
	(58, 'Nocny Marek', 32.00, 45),
	(59, 'Vegetariana', 32.00, 45),
	(60, 'Kolorowa', 32.00, 45),
	(61, 'Chilli', 32.00, 45),
	(62, 'Droga Mleczna', 34.00, 45),
	(63, 'Księżycowa', 34.00, 45),
	(64, 'Cztery Sery', 34.00, 45),
	(65, 'Mamma Mia!', 34.00, 45);
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

-- Zrzucanie danych dla tabeli pizzanoc.product_ingredients: ~51 rows (około)
/*!40000 ALTER TABLE `product_ingredients` DISABLE KEYS */;
INSERT IGNORE INTO `product_ingredients` (`product_ID`, `ingredient_ID`) VALUES
	(1, 1),
	(2, 1),
	(2, 3),
	(3, 1),
	(3, 2),
	(4, 1),
	(4, 3),
	(4, 4),
	(5, 1),
	(5, 2),
	(5, 3),
	(6, 1),
	(6, 2),
	(6, 5),
	(7, 1),
	(7, 2),
	(7, 4),
	(8, 1),
	(8, 6),
	(8, 7),
	(8, 16),
	(9, 1),
	(9, 9),
	(9, 3),
	(9, 8),
	(9, 16),
	(10, 1),
	(10, 7),
	(10, 3),
	(10, 8),
	(11, 1),
	(11, 4),
	(11, 10),
	(12, 1),
	(12, 17),
	(12, 18),
	(12, 16),
	(13, 1),
	(13, 17),
	(13, 12),
	(13, 16),
	(14, 1),
	(14, 13),
	(14, 14),
	(14, 15),
	(15, 1),
	(15, 3),
	(15, 2),
	(15, 8),
	(15, 16),
	(15, 7);
/*!40000 ALTER TABLE `product_ingredients` ENABLE KEYS */;

/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
