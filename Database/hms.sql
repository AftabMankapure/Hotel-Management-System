-- phpMyAdmin SQL Dump
-- version 5.2.0
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Mar 01, 2023 at 04:52 PM
-- Server version: 10.4.27-MariaDB
-- PHP Version: 8.2.0

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `hms`
--

-- --------------------------------------------------------

--
-- Table structure for table `checkin`
--

CREATE TABLE `checkin` (
  `id` int(11) NOT NULL,
  `name` varchar(200) NOT NULL,
  `phoneno` int(11) NOT NULL,
  `idtype` varchar(200) NOT NULL,
  `idno` int(20) NOT NULL,
  `gender` varchar(200) NOT NULL,
  `adult` varchar(250) NOT NULL,
  `child` varchar(200) NOT NULL,
  `roomtype` varchar(200) NOT NULL,
  `roomno` int(20) NOT NULL,
  `rate` varchar(200) NOT NULL,
  `bed` int(200) NOT NULL,
  `checkin` varchar(200) NOT NULL,
  `checkintime` time NOT NULL DEFAULT current_timestamp(),
  `checkout` varchar(200) NOT NULL,
  `dtype` varchar(200) NOT NULL,
  `amount` varchar(200) NOT NULL,
  `total` int(100) NOT NULL,
  `advance` int(100) NOT NULL,
  `pending` varchar(200) NOT NULL,
  `balance` int(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `checkin`
--

INSERT INTO `checkin` (`id`, `name`, `phoneno`, `idtype`, `idno`, `gender`, `adult`, `child`, `roomtype`, `roomno`, `rate`, `bed`, `checkin`, `checkintime`, `checkout`, `dtype`, `amount`, `total`, `advance`, `pending`, `balance`) VALUES
(1, 'Aftab', 978645312, 'Aadhar Card', 987, 'Male', '1', '0', 'NON AC', 101, '500', 1, '02-03-2023', '19:42:53', '04-03-2023', 'Regular', '1000', 900, 500, '400', 0),
(2, 'Sagar', 7894521, 'Aadhar Card', 321, 'Male', '2', '0', 'NON AC', 102, '500', 1, '15-03-2023', '19:47:56', '18-03-2023', 'Regular', '1500', 1350, 1000, '350', 0);

-- --------------------------------------------------------

--
-- Table structure for table `checkout`
--

CREATE TABLE `checkout` (
  `id` int(11) NOT NULL,
  `name` varchar(200) NOT NULL,
  `phoneno` int(100) NOT NULL,
  `idtype` varchar(200) NOT NULL,
  `idno` int(100) NOT NULL,
  `gender` varchar(200) NOT NULL,
  `adult` int(200) NOT NULL,
  `child` int(100) NOT NULL,
  `roomno` int(100) NOT NULL,
  `rate` varchar(100) NOT NULL,
  `roomtype` varchar(200) NOT NULL,
  `bed` int(100) NOT NULL,
  `cindate` varchar(200) NOT NULL,
  `cintime` varchar(200) NOT NULL,
  `countdate` varchar(200) NOT NULL,
  `couttime` varchar(200) NOT NULL,
  `dtype` varchar(200) NOT NULL,
  `amount` int(100) NOT NULL,
  `total` int(100) NOT NULL,
  `advance` int(100) NOT NULL,
  `pending` int(100) NOT NULL,
  `balance` int(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `checkout`
--

INSERT INTO `checkout` (`id`, `name`, `phoneno`, `idtype`, `idno`, `gender`, `adult`, `child`, `roomno`, `rate`, `roomtype`, `bed`, `cindate`, `cintime`, `countdate`, `couttime`, `dtype`, `amount`, `total`, `advance`, `pending`, `balance`) VALUES
(1, 'Aftab', 978645312, 'Aadhar Card', 987, 'Male', 1, 0, 101, '500', 'NON AC', 1, '02-03-2023', '19:42:53', '04-03-2023', '19:44:01', 'Regular', 1000, 900, 500, 0, 0);

-- --------------------------------------------------------

--
-- Table structure for table `discount`
--

CREATE TABLE `discount` (
  `id` int(11) NOT NULL,
  `type` varchar(200) NOT NULL,
  `rate` varchar(250) NOT NULL,
  `status` varchar(250) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `discount`
--

INSERT INTO `discount` (`id`, `type`, `rate`, `status`) VALUES
(1, 'Regular', '10', 'ACTIVE'),
(2, 'VIP', '25', 'ACTIVE'),
(3, 'New Year', '20', 'INACTIVE');

-- --------------------------------------------------------

--
-- Table structure for table `reservation`
--

CREATE TABLE `reservation` (
  `id` int(11) NOT NULL,
  `name` varchar(200) NOT NULL,
  `phoneno` int(100) NOT NULL,
  `idtype` varchar(100) NOT NULL,
  `idno` int(100) NOT NULL,
  `gender` varchar(100) NOT NULL,
  `adult` int(100) NOT NULL,
  `child` int(100) NOT NULL,
  `roomtype` varchar(200) NOT NULL,
  `roomno` int(100) NOT NULL,
  `rate` int(100) NOT NULL,
  `Bed` int(100) NOT NULL,
  `from` varchar(200) NOT NULL,
  `to` varchar(200) NOT NULL,
  `dtype` varchar(200) NOT NULL,
  `amount` int(100) NOT NULL,
  `total` int(100) NOT NULL,
  `advance` int(100) NOT NULL,
  `pending` int(100) NOT NULL,
  `balance` int(100) NOT NULL,
  `Status` varchar(200) NOT NULL DEFAULT 'Till Not CheckIn'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `reservation`
--

INSERT INTO `reservation` (`id`, `name`, `phoneno`, `idtype`, `idno`, `gender`, `adult`, `child`, `roomtype`, `roomno`, `rate`, `Bed`, `from`, `to`, `dtype`, `amount`, `total`, `advance`, `pending`, `balance`, `Status`) VALUES
(1, 'Sagar', 7894521, 'Aadhar Card', 321, 'Male', 2, 0, 'NON AC', 102, 500, 1, '15-03-2023', '18-03-2023', 'Regular', 1500, 1350, 1000, 350, 0, 'CheckedIN');

-- --------------------------------------------------------

--
-- Table structure for table `room`
--

CREATE TABLE `room` (
  `id` int(200) NOT NULL,
  `R_NUMBER` varchar(250) NOT NULL,
  `R_TYPE` varchar(200) NOT NULL,
  `Rate` varchar(200) NOT NULL,
  `Bed` int(200) NOT NULL,
  `STATUS` varchar(200) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `room`
--

INSERT INTO `room` (`id`, `R_NUMBER`, `R_TYPE`, `Rate`, `Bed`, `STATUS`) VALUES
(1, '101', 'NON AC', '500', 1, 'Vacant'),
(2, '102', 'NON AC', '500', 1, 'Occupied'),
(3, '103', 'NON AC', '500', 1, 'Vacant'),
(6, '104', 'NON AC', '500', 1, 'Vacant'),
(7, '201', 'AC', '1000', 2, 'Vacant'),
(8, '202', 'AC', '1000', 2, 'Vacant'),
(9, '203', 'AC', '1000', 2, 'Vacant'),
(10, '204', 'AC', '1000', 2, 'Vacant'),
(11, '301', 'VIP', '2000', 2, 'Vacant'),
(12, '302', 'VIP', '2000', 2, 'Vacant'),
(13, '303', 'VIP', '2000', 2, 'Vacant'),
(14, '304', 'VIP', '3000', 3, 'Clean'),
(15, '401', 'DELUXE', '5000', 2, 'Vacant');

-- --------------------------------------------------------

--
-- Table structure for table `user`
--

CREATE TABLE `user` (
  `id` int(11) NOT NULL,
  `user` varchar(200) NOT NULL,
  `password` varchar(250) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `user`
--

INSERT INTO `user` (`id`, `user`, `password`) VALUES
(1, 'user', 'user');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `checkin`
--
ALTER TABLE `checkin`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `checkout`
--
ALTER TABLE `checkout`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `discount`
--
ALTER TABLE `discount`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `reservation`
--
ALTER TABLE `reservation`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `room`
--
ALTER TABLE `room`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `R_NUMBER` (`R_NUMBER`);

--
-- Indexes for table `user`
--
ALTER TABLE `user`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `checkin`
--
ALTER TABLE `checkin`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT for table `checkout`
--
ALTER TABLE `checkout`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT for table `discount`
--
ALTER TABLE `discount`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT for table `reservation`
--
ALTER TABLE `reservation`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT for table `room`
--
ALTER TABLE `room`
  MODIFY `id` int(200) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=16;

--
-- AUTO_INCREMENT for table `user`
--
ALTER TABLE `user`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
