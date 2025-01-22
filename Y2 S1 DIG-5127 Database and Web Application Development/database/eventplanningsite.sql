-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Jan 16, 2025 at 06:10 PM
-- Server version: 10.4.28-MariaDB
-- PHP Version: 8.2.4

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
  `name` int(11) NOT NULL,
  `phone` int(11) NOT NULL,
  `email` int(11) NOT NULL,
  `type` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_swedish_ci;

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
(1, 'steve', 'stev1ewonder', 'stevie.wonder@hotmail.com', '+447111111111', '96 wonder street', 'ST34IE', 'wonder', 'stevie', '2025-01-11 00:00:00');

-- --------------------------------------------------------

--
-- Table structure for table `eventdetails`
--

CREATE TABLE `eventdetails` (
  `eventID` int(11) NOT NULL,
  `locationID` int(11) NOT NULL,
  `service_id` int(11) NOT NULL,
  `customerID` int(11) NOT NULL,
  `eventType` text NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_swedish_ci;

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

-- --------------------------------------------------------

--
-- Table structure for table `servicedetails`
--

CREATE TABLE `servicedetails` (
  `service_id` int(11) NOT NULL,
  `username` varchar(25) DEFAULT NULL,
  `password` text NOT NULL,
  `serviceType` text NOT NULL,
  `serviceWebsite` text NOT NULL,
  `serviceHQ` text NOT NULL,
  `serviceEmail` text NOT NULL,
  `servicePhone` text NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_swedish_ci COMMENT='will need to create a location list for services as an inter';

-- --------------------------------------------------------

--
-- Table structure for table `servicelocations`
--

CREATE TABLE `servicelocations` (
  `servicelocationID` int(11) NOT NULL,
  `locationID` int(11) NOT NULL,
  `service_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_swedish_ci COMMENT='relationship between services and locations provided';

-- --------------------------------------------------------

--
-- Table structure for table `staffdetails`
--

CREATE TABLE `staffdetails` (
  `staffID` int(11) NOT NULL,
  `name` varchar(25) NOT NULL,
  `service_id` int(11) NOT NULL,
  `eventID` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_swedish_ci;

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
  ADD KEY `service_id` (`service_id`),
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
-- Indexes for table `servicedetails`
--
ALTER TABLE `servicedetails`
  ADD PRIMARY KEY (`service_id`),
  ADD UNIQUE KEY `username` (`username`);

--
-- Indexes for table `servicelocations`
--
ALTER TABLE `servicelocations`
  ADD PRIMARY KEY (`servicelocationID`),
  ADD KEY `locationID` (`locationID`),
  ADD KEY `service_id` (`service_id`);

--
-- Indexes for table `staffdetails`
--
ALTER TABLE `staffdetails`
  ADD PRIMARY KEY (`staffID`),
  ADD UNIQUE KEY `name` (`name`),
  ADD KEY `eventID` (`eventID`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `customerdetails`
--
ALTER TABLE `customerdetails`
  MODIFY `customerID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `eventdetails`
--
ALTER TABLE `eventdetails`
  ADD CONSTRAINT `eventdetails_ibfk_1` FOREIGN KEY (`locationID`) REFERENCES `locationdetails` (`locationID`),
  ADD CONSTRAINT `eventdetails_ibfk_2` FOREIGN KEY (`service_id`) REFERENCES `servicedetails` (`service_id`),
  ADD CONSTRAINT `eventdetails_ibfk_3` FOREIGN KEY (`customerID`) REFERENCES `customerdetails` (`customerID`);

--
-- Constraints for table `reviewdetails`
--
ALTER TABLE `reviewdetails`
  ADD CONSTRAINT `reviewdetails_ibfk_1` FOREIGN KEY (`service_id`) REFERENCES `servicedetails` (`service_id`),
  ADD CONSTRAINT `reviewdetails_ibfk_2` FOREIGN KEY (`customerID`) REFERENCES `customerdetails` (`customerID`);

--
-- Constraints for table `servicelocations`
--
ALTER TABLE `servicelocations`
  ADD CONSTRAINT `servicelocations_ibfk_1` FOREIGN KEY (`locationID`) REFERENCES `locationdetails` (`locationID`),
  ADD CONSTRAINT `servicelocations_ibfk_2` FOREIGN KEY (`service_id`) REFERENCES `servicedetails` (`service_id`);

--
-- Constraints for table `staffdetails`
--
ALTER TABLE `staffdetails`
  ADD CONSTRAINT `staffdetails_ibfk_1` FOREIGN KEY (`eventID`) REFERENCES `eventdetails` (`eventID`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
