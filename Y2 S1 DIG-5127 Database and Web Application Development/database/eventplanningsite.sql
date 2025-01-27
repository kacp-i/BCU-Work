-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Jan 27, 2025 at 04:56 AM
-- Server version: 10.4.32-MariaDB
-- PHP Version: 8.2.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `eventplanningsite`
--

-- --------------------------------------------------------

--
-- Table structure for table `businesses`
--

CREATE TABLE `businesses` (
  `businessID` int(11) NOT NULL,
  `username` varchar(25) NOT NULL,
  `password` char(255) NOT NULL,
  `email` char(255) NOT NULL,
  `phoneNum` varchar(15) NOT NULL,
  `addressLineOne` char(255) NOT NULL,
  `postCode` char(25) NOT NULL,
  `firstName` char(50) NOT NULL,
  `lastName` char(50) NOT NULL,
  `regDate` datetime NOT NULL DEFAULT current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `businesses`
--

INSERT INTO `businesses` (`businessID`, `username`, `password`, `email`, `phoneNum`, `addressLineOne`, `postCode`, `firstName`, `lastName`, `regDate`) VALUES
(1, 'buss', 'buss', 'buss@gmail.com', '+441210121111', '91 buss lane', 'BU33SS', 'buss', 'ssub', '2025-01-24 16:50:32');

-- --------------------------------------------------------

--
-- Table structure for table `customerdetails`
--

CREATE TABLE `customerdetails` (
  `customerID` int(11) NOT NULL,
  `username` varchar(25) NOT NULL,
  `password` char(255) NOT NULL,
  `email` char(255) NOT NULL,
  `phoneNum` varchar(15) NOT NULL,
  `addressLineOne` char(255) NOT NULL,
  `postCode` char(25) NOT NULL,
  `firstName` char(50) NOT NULL,
  `lastName` char(50) NOT NULL,
  `regDate` datetime NOT NULL DEFAULT current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `customerdetails`
--

INSERT INTO `customerdetails` (`customerID`, `username`, `password`, `email`, `phoneNum`, `addressLineOne`, `postCode`, `firstName`, `lastName`, `regDate`) VALUES
(1, 'steve', 'stev1ewonder', 'stevie.wonder@hotmail.com', '+447111111111', '96 wonder street', 'ST34IE', 'wonder', 'stevie', '2025-01-11 00:00:00'),
(2, 'Pdog648', 'bbdpopcorn54', 'pdog648@gmail.com', '07353265678', '566', 'B69 7HU', 'Paul', 'Douglas', '2025-01-26 14:17:40'),
(3, 'ILoveAVFC12', 'skibiditoilet2', 'ILoveAVFC12@gmail.com', '07353965090', '65 Digne Lane', 'B18 2XU', 'Elliot', 'Haaland', '2025-01-26 14:21:24');

-- --------------------------------------------------------

--
-- Table structure for table `eventdetails`
--

CREATE TABLE `eventdetails` (
  `eventID` int(11) NOT NULL,
  `locationID` int(11) NOT NULL,
  `serviceID` int(11) NOT NULL,
  `customerID` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_swedish_ci;

--
-- Dumping data for table `eventdetails`
--

INSERT INTO `eventdetails` (`eventID`, `locationID`, `serviceID`, `customerID`) VALUES
(3, 2, 6, 1),
(4, 2, 6, 1);

-- --------------------------------------------------------

--
-- Table structure for table `locationdetails`
--

CREATE TABLE `locationdetails` (
  `locationID` int(11) NOT NULL,
  `name` text NOT NULL,
  `address` text NOT NULL,
  `size` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_swedish_ci;

--
-- Dumping data for table `locationdetails`
--

INSERT INTO `locationdetails` (`locationID`, `name`, `address`, `size`) VALUES
(1, 'Merrydale Manor', 'Knutsford, Chesire', 2000),
(2, 'Farnham Castle', 'Farnham, Surrey', 875),
(3, 'Bredenbury Court Barns', 'Hereford, Herefordshire', 1200);

-- --------------------------------------------------------

--
-- Table structure for table `reviewdetails`
--

CREATE TABLE `reviewdetails` (
  `reviewID` int(11) NOT NULL,
  `customerID` int(11) NOT NULL,
  `service_id` int(11) NOT NULL,
  `reviewScore` int(11) NOT NULL,
  `reviewDescription` text NOT NULL,
  `reviewHelpfulCount` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_swedish_ci;

--
-- Dumping data for table `reviewdetails`
--

INSERT INTO `reviewdetails` (`reviewID`, `customerID`, `service_id`, `reviewScore`, `reviewDescription`, `reviewHelpfulCount`) VALUES
(1, 1, 6, 5, 'Fantastic service for my event, I am so happy. Would definitely recommend to anyone. Gave the kids a proper scare!', 2),
(2, 2, 5, 4, 'Could have improved on our waiting times but the service provided was excellent on the day and left all guests happy. Thank you for great service!', 1),
(3, 3, 4, 5, 'Good stuff init.', 0),
(4, 2, 5, 5, 'AMAZING FOOD!! BLEW THE JOCKS OFF ME! Definitely using at my next wedding, see you in 2 years time', 3),
(5, 1, 1, 4, 'Fun for all the family, very catered to requests, my child was so happy!', 1);

-- --------------------------------------------------------

--
-- Table structure for table `services`
--

CREATE TABLE `services` (
  `serviceID` int(11) NOT NULL,
  `serviceName` char(50) NOT NULL,
  `serviceType` char(50) NOT NULL,
  `serviceProvider` char(50) NOT NULL,
  `serviceDescription` text NOT NULL,
  `servicePPD` decimal(11,2) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `services`
--

INSERT INTO `services` (`serviceID`, `serviceName`, `serviceType`, `serviceProvider`, `serviceDescription`, `servicePPD`) VALUES
(1, 'Child Entertainment', 'Entertainment', 'Kids R Us', 'Kids R Us specializes in providing engaging and interactive experiences for children aged 2 to 12 years. Our mission is to create magical moments that inspire creativity, joy, and learning through various fun-filled activities.', 299.99),
(2, 'Hamburger Cheeseburger BigMac Whopper', 'Catering', 'McDonalds', 'McDonald\'s is a globally recognized fast-food chain that serves a diverse range of affordable and delicious meals, making it a popular choice for families, individuals, and children. Known for its iconic golden arches, McDonald\'s aims to provide an enjoyable dining experience with a focus on quality, convenience, and customer satisfaction.', 799.99),
(3, 'Photobomb', 'Photography', 'Intel', 'Photobomb is a professional photography company dedicated to preserving life’s most cherished memories through stunning imagery. Specializing in a wide range of photography services, we aim to provide clients with high-quality photos that tell their unique stories.', 1250.99),
(4, 'Feastingables', 'Catering', 'Mr Burger', 'This innovative fast-food concept leverages delivery-only services to reach fans and customers nationwide. The brand is known for its delicious burgers, fries, and unique menu items, all created with a focus on quality and customer satisfaction.', 19.00),
(5, 'Elegant Events Catering', 'Catering', 'EEC', 'Elegant Events Catering is a premier wedding catering company dedicated to creating unforgettable culinary experiences for couples on their special day. With a focus on high-quality ingredients, exceptional service, and customized menus, we aim to elevate wedding celebrations with delicious food that reflects the couple’s unique tastes and style.', 2000.00),
(6, 'Freddy Fez', 'Entertainment', 'FF Inc', 'Freddy Fez is an enchanting traveling circus that brings joy and wonder to audiences of all ages. With a rich tradition of performing arts, our circus combines breathtaking acrobatics, whimsical clowns, and mesmerizing animal acts to create a magical experience that captivates and entertains.', 840.75);

-- --------------------------------------------------------

--
-- Table structure for table `supporttickets`
--

CREATE TABLE `supporttickets` (
  `ticketID` int(11) NOT NULL,
  `firstName` char(50) NOT NULL,
  `lastName` char(50) NOT NULL,
  `email` varchar(50) NOT NULL,
  `phoneNum` varchar(15) NOT NULL,
  `reason` text NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `supporttickets`
--

INSERT INTO `supporttickets` (`ticketID`, `firstName`, `lastName`, `email`, `phoneNum`, `reason`) VALUES
(1, 'ben', 'ben', 'ben@benmail.com', '+440111222333', 'i was hoping to change some details on my bookings but. please get back to me ASAP');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `businesses`
--
ALTER TABLE `businesses`
  ADD PRIMARY KEY (`businessID`);

--
-- Indexes for table `customerdetails`
--
ALTER TABLE `customerdetails`
  ADD PRIMARY KEY (`customerID`),
  ADD UNIQUE KEY `username` (`username`);

--
-- Indexes for table `eventdetails`
--
ALTER TABLE `eventdetails`
  ADD PRIMARY KEY (`eventID`),
  ADD KEY `locationID` (`locationID`),
  ADD KEY `service_id` (`serviceID`),
  ADD KEY `customerID` (`customerID`);

--
-- Indexes for table `locationdetails`
--
ALTER TABLE `locationdetails`
  ADD PRIMARY KEY (`locationID`);

--
-- Indexes for table `reviewdetails`
--
ALTER TABLE `reviewdetails`
  ADD PRIMARY KEY (`reviewID`),
  ADD KEY `service_id` (`service_id`),
  ADD KEY `customerID` (`customerID`);

--
-- Indexes for table `services`
--
ALTER TABLE `services`
  ADD PRIMARY KEY (`serviceID`);

--
-- Indexes for table `supporttickets`
--
ALTER TABLE `supporttickets`
  ADD PRIMARY KEY (`ticketID`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `businesses`
--
ALTER TABLE `businesses`
  MODIFY `businessID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT for table `customerdetails`
--
ALTER TABLE `customerdetails`
  MODIFY `customerID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT for table `eventdetails`
--
ALTER TABLE `eventdetails`
  MODIFY `eventID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT for table `locationdetails`
--
ALTER TABLE `locationdetails`
  MODIFY `locationID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT for table `services`
--
ALTER TABLE `services`
  MODIFY `serviceID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- AUTO_INCREMENT for table `supporttickets`
--
ALTER TABLE `supporttickets`
  MODIFY `ticketID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `eventdetails`
--
ALTER TABLE `eventdetails`
  ADD CONSTRAINT `eventdetails_ibfk_1` FOREIGN KEY (`locationID`) REFERENCES `locationdetails` (`locationID`),
  ADD CONSTRAINT `eventdetails_ibfk_2` FOREIGN KEY (`serviceID`) REFERENCES `services` (`serviceID`),
  ADD CONSTRAINT `eventdetails_ibfk_3` FOREIGN KEY (`customerID`) REFERENCES `customerdetails` (`customerID`);

--
-- Constraints for table `reviewdetails`
--
ALTER TABLE `reviewdetails`
  ADD CONSTRAINT `reviewdetails_ibfk_1` FOREIGN KEY (`service_id`) REFERENCES `services` (`serviceID`),
  ADD CONSTRAINT `reviewdetails_ibfk_2` FOREIGN KEY (`customerID`) REFERENCES `customerdetails` (`customerID`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
