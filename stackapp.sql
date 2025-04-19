-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Aug 23, 2024 at 06:35 PM
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
-- Database: `stackapp`
--

-- --------------------------------------------------------

--
-- Table structure for table `stack_operations`
--

CREATE TABLE `stack_operations` (
  `id` int(11) NOT NULL,
  `user_id` int(11) DEFAULT NULL,
  `operation` varchar(50) DEFAULT NULL,
  `value` varchar(50) DEFAULT NULL,
  `time` timestamp NOT NULL DEFAULT current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `stack_operations`
--

INSERT INTO `stack_operations` (`id`, `user_id`, `operation`, `value`, `time`) VALUES
(29, 7, 'push', 'd', '2024-08-22 06:26:18'),
(30, 7, 'push', 'e', '2024-08-22 06:26:20'),
(31, 7, 'pop', 'e', '2024-08-22 06:26:22'),
(32, 7, 'Infix to Postfix', 'abc*d/+', '2024-08-22 06:29:56'),
(33, 7, 'Infix to Postfix', 'hb*k+', '2024-08-22 06:32:42'),
(34, 7, 'Infix to Prefix', '-f+hb', '2024-08-22 06:38:18'),
(35, 7, 'Postfix to Prefix', '+-ghh', '2024-08-22 06:39:17'),
(36, 7, 'push', '1', '2024-08-22 06:41:22'),
(37, 7, 'push', '2', '2024-08-22 06:41:39'),
(38, 7, 'push', '+', '2024-08-22 06:41:46'),
(39, 7, 'Postfix to Infix', '(1+2)', '2024-08-22 06:42:04'),
(40, 7, 'Infix to Postfix', 'dg-v+', '2024-08-22 06:57:13'),
(41, 7, 'Prefix to Infix', '(f+((b*h)-k))', '2024-08-22 07:19:34');

-- --------------------------------------------------------

--
-- Table structure for table `users`
--

CREATE TABLE `users` (
  `id` int(11) NOT NULL,
  `username` varchar(50) NOT NULL,
  `password` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `users`
--

INSERT INTO `users` (`id`, `username`, `password`) VALUES
(1, 'asd', 'qw'),
(6, 'qwer', '123'),
(7, 'Dhruvi', '123456');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `stack_operations`
--
ALTER TABLE `stack_operations`
  ADD PRIMARY KEY (`id`),
  ADD KEY `user_id` (`user_id`);

--
-- Indexes for table `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `username` (`username`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `stack_operations`
--
ALTER TABLE `stack_operations`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=42;

--
-- AUTO_INCREMENT for table `users`
--
ALTER TABLE `users`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `stack_operations`
--
ALTER TABLE `stack_operations`
  ADD CONSTRAINT `stack_operations_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
