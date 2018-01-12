-- phpMyAdmin SQL Dump
-- version 4.7.4
-- https://www.phpmyadmin.net/
--
-- Host: localhost
-- Czas generowania: 12 Sty 2018, 23:40
-- Wersja serwera: 10.1.28-MariaDB
-- Wersja PHP: 7.1.10

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Baza danych: `pizzanoc`
--
CREATE DATABASE IF NOT EXISTS `pizzanoc` DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci;
USE `pizzanoc`;

DELIMITER $$
--
-- Procedury
--
DROP PROCEDURE IF EXISTS `constOrder`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `constOrder` (IN `n` INT)  NO SQL
BEGIN
UPDATE ingredients SET ingredients.quantity_in_stock= ingredients.quantity_in_stock+10
Where ingredients.name="ser";
UPDATE ingredients SET ingredients.quantity_in_stock= ingredients.quantity_in_stock+5
Where ingredients.name="szynka";
UPDATE ingredients SET ingredients.quantity_in_stock= ingredients.quantity_in_stock+10
Where ingredients.name="pieczarki";
UPDATE ingredients SET ingredients.quantity_in_stock= ingredients.quantity_in_stock+0.2
Where ingredients.name="oliwki";
UPDATE ingredients SET ingredients.quantity_in_stock= ingredients.quantity_in_stock+2
Where ingredients.name="papryka";
UPDATE ingredients SET ingredients.quantity_in_stock= ingredients.quantity_in_stock+4
Where ingredients.name="salami";
END$$

DELIMITER ;

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `clients`
--

DROP TABLE IF EXISTS `clients`;
CREATE TABLE IF NOT EXISTS `clients` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `city` varchar(100) NOT NULL,
  `street` varchar(100) NOT NULL,
  `house_number` varchar(10) NOT NULL,
  `apartment_number` varchar(10) NOT NULL,
  `phone_number` varchar(15) NOT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `employees`
--

DROP TABLE IF EXISTS `employees`;
CREATE TABLE IF NOT EXISTS `employees` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `forename` varchar(50) NOT NULL,
  `surname` varchar(80) NOT NULL,
  `phone_number` varchar(15) NOT NULL,
  `day_salary` decimal(8,2) NOT NULL,
  `employment` datetime NOT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `ingredients`
--

DROP TABLE IF EXISTS `ingredients`;
CREATE TABLE IF NOT EXISTS `ingredients` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(100) DEFAULT NULL,
  `quantity_in_stock` float DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8;

--
-- Zrzut danych tabeli `ingredients`
--

INSERT INTO `ingredients` (`ID`, `name`, `quantity_in_stock`) VALUES
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

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `ordered_products`
--

DROP TABLE IF EXISTS `ordered_products`;
CREATE TABLE IF NOT EXISTS `ordered_products` (
  `order_ID` int(11) NOT NULL,
  `product_ID` int(11) NOT NULL,
  KEY `fk_order` (`order_ID`),
  KEY `fk_product` (`product_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Zrzut danych tabeli `ordered_products`
--

INSERT INTO `ordered_products` (`order_ID`, `product_ID`) VALUES
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

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `orders`
--

DROP TABLE IF EXISTS `orders`;
CREATE TABLE IF NOT EXISTS `orders` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `data` datetime NOT NULL,
  `status` enum('przyjęte','wysłane','odmówione','odebrane','nieodebrane') NOT NULL,
  `fullpirce` decimal(10,2) UNSIGNED NOT NULL,
  `card_payment` enum('tak','nie') NOT NULL,
  `phone_number` varchar(15) NOT NULL,
  `adres` varchar(250) NOT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

--
-- Zrzut danych tabeli `orders`
--

INSERT INTO `orders` (`ID`, `data`, `status`, `fullpirce`, `card_payment`, `phone_number`, `adres`) VALUES
(1, '2003-02-07 00:00:00', 'nieodebrane', '150.00', 'tak', '530134551', 'Wyczółkowskiego/15/3/Jelenia Góra'),
(2, '2005-02-13 00:00:00', 'wysłane', '6346.00', 'tak', '531692381', 'Pijarska/148/17/Jelenia Góra'),
(3, '2011-11-05 00:00:00', 'odmówione', '25.00', 'nie', '140924133', 'Osiedle Robotnicze/2/3/Jelenia Góra'),
(4, '2016-10-05 00:00:00', 'odebrane', '7246.00', 'tak', '530923851', 'I Maja/12/45/Jelenia Góra'),
(5, '2003-02-02 00:00:00', 'nieodebrane', '142.00', 'nie', '630241551', 'Szybka/12/1/Jelenia Góra');

--
-- Wyzwalacze `orders`
--
DROP TRIGGER IF EXISTS `zamowienie`;
DELIMITER $$
CREATE TRIGGER `zamowienie` BEFORE INSERT ON `orders` FOR EACH ROW BEGIN
UPDATE ingredients
SET ingredients.quantity_in_stock=ingredients.quantity_in_stock-1
Where ingredients.ID=product_ingredients.ingredient_ID and product_ingredients.product_ID=ordered_products.product_ID;
END
$$
DELIMITER ;

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `pizza_ingridient`
--

DROP TABLE IF EXISTS `pizza_ingridient`;
CREATE TABLE IF NOT EXISTS `pizza_ingridient` (
  `ID_pizza` int(11) NOT NULL,
  `ID_ingridient` int(11) NOT NULL,
  KEY `ID_pizza` (`ID_pizza`),
  KEY `ID_ingridient` (`ID_ingridient`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Zrzut danych tabeli `pizza_ingridient`
--

INSERT INTO `pizza_ingridient` (`ID_pizza`, `ID_ingridient`) VALUES
(1, 1),
(2, 1),
(2, 2),
(2, 3),
(3, 2),
(3, 15),
(3, 1),
(4, 4),
(4, 1),
(4, 3),
(5, 1),
(5, 8),
(5, 3),
(6, 5),
(7, 10),
(7, 11),
(8, 6),
(8, 7),
(8, 8),
(9, 2),
(9, 4),
(9, 6),
(10, 8),
(10, 7),
(10, 12),
(11, 17),
(11, 18),
(11, 15),
(12, 18),
(12, 11),
(12, 12),
(13, 8),
(13, 7),
(13, 12),
(14, 13),
(14, 7),
(14, 2),
(15, 4),
(15, 15),
(15, 17),
(51, 8),
(51, 7),
(51, 18),
(52, 8),
(52, 1),
(52, 2),
(53, 8),
(53, 3),
(53, 5),
(54, 8),
(54, 3),
(54, 5),
(54, 6),
(55, 12),
(55, 14),
(55, 16),
(55, 17),
(56, 8),
(56, 3),
(56, 5),
(56, 14),
(57, 9),
(57, 11),
(57, 13),
(5, 15),
(58, 2),
(58, 1),
(58, 12),
(58, 6),
(59, 8),
(59, 3),
(59, 1),
(59, 9),
(60, 1),
(60, 4),
(60, 8),
(60, 10),
(61, 5),
(61, 3),
(61, 5),
(61, 12),
(62, 1),
(62, 3),
(62, 5),
(62, 10),
(63, 2),
(63, 7),
(63, 9),
(63, 14),
(64, 1),
(64, 7),
(64, 9),
(64, 14),
(64, 18),
(65, 1),
(65, 2),
(65, 6),
(65, 12),
(65, 18);

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `products`
--

DROP TABLE IF EXISTS `products`;
CREATE TABLE IF NOT EXISTS `products` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(100) DEFAULT NULL,
  `price` decimal(10,2) DEFAULT NULL,
  `diameter` int(11) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=66 DEFAULT CHARSET=utf8;

--
-- Zrzut danych tabeli `products`
--

INSERT INTO `products` (`ID`, `name`, `price`, `diameter`) VALUES
(1, 'Margherita', '12.00', 32),
(2, 'Funghi', '13.00', 32),
(3, 'Prosciutto', '13.00', 32),
(4, 'Salami', '14.00', 32),
(5, 'Capriciosa', '14.00', 32),
(6, 'Hawajska', '14.00', 32),
(7, 'Gambino', '15.00', 32),
(8, 'Nocny Marek', '15.00', 32),
(9, 'Vegetariana', '15.00', 32),
(10, 'Kolorowa', '15.00', 32),
(11, 'Chilli', '15.00', 32),
(12, 'Droga Mleczna', '16.00', 32),
(13, 'Księżycowa', '16.00', 32),
(14, 'Cztery Sery', '16.00', 32),
(15, 'Mamma Mia!', '16.00', 32),
(51, 'Margherita', '25.00', 45),
(52, 'Funghi', '27.00', 45),
(53, 'Prosciutto', '27.00', 45),
(54, 'Salami', '28.00', 45),
(55, 'Capriciosa', '28.00', 45),
(56, 'Hawajska', '28.00', 45),
(57, 'Gambino', '30.00', 45),
(58, 'Nocny Marek', '32.00', 45),
(59, 'Vegetariana', '32.00', 45),
(60, 'Kolorowa', '32.00', 45),
(61, 'Chilli', '32.00', 45),
(62, 'Droga Mleczna', '34.00', 45),
(63, 'Księżycowa', '34.00', 45),
(64, 'Cztery Sery', '34.00', 45),
(65, 'Mamma Mia!', '34.00', 45);

-- --------------------------------------------------------

--
-- Zastąpiona struktura widoku `Products_for_Clients`
-- (See below for the actual view)
--
DROP VIEW IF EXISTS `Products_for_Clients`;
CREATE TABLE IF NOT EXISTS `Products_for_Clients` (
`name` varchar(100)
,`price` decimal(10,2)
,`diameter` int(11)
);

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `product_ingredients`
--

DROP TABLE IF EXISTS `product_ingredients`;
CREATE TABLE IF NOT EXISTS `product_ingredients` (
  `product_ID` int(11) NOT NULL,
  `ingredient_ID` int(11) NOT NULL,
  KEY `fk_secondProduct` (`product_ID`),
  KEY `fk_ingredients` (`ingredient_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Zrzut danych tabeli `product_ingredients`
--

INSERT INTO `product_ingredients` (`product_ID`, `ingredient_ID`) VALUES
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

-- --------------------------------------------------------

--
-- Structure for view `Products_for_Clients` exported as a table
--
DROP TABLE IF EXISTS `Products_for_Clients`;
CREATE TABLE IF NOT EXISTS `Products_for_Clients`(
    `name` varchar(100) COLLATE utf8_general_ci DEFAULT NULL,
    `price` decimal(10,2) DEFAULT NULL,
    `diameter` int(11) DEFAULT NULL
);

--
-- Ograniczenia dla zrzutów tabel
--

--
-- Ograniczenia dla tabeli `ordered_products`
--
ALTER TABLE `ordered_products`
  ADD CONSTRAINT `fk_order` FOREIGN KEY (`order_ID`) REFERENCES `orders` (`ID`),
  ADD CONSTRAINT `fk_product` FOREIGN KEY (`product_ID`) REFERENCES `products` (`ID`);

--
-- Ograniczenia dla tabeli `pizza_ingridient`
--
ALTER TABLE `pizza_ingridient`
  ADD CONSTRAINT `pizza_ingridient_ibfk_1` FOREIGN KEY (`ID_pizza`) REFERENCES `products` (`ID`),
  ADD CONSTRAINT `pizza_ingridient_ibfk_2` FOREIGN KEY (`ID_ingridient`) REFERENCES `ingredients` (`ID`);

--
-- Ograniczenia dla tabeli `product_ingredients`
--
ALTER TABLE `product_ingredients`
  ADD CONSTRAINT `fk_ingredients` FOREIGN KEY (`ingredient_ID`) REFERENCES `ingredients` (`ID`),
  ADD CONSTRAINT `fk_secondProduct` FOREIGN KEY (`product_ID`) REFERENCES `products` (`ID`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
