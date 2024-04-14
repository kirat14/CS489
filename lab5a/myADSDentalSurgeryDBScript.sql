CREATE TABLE IF NOT EXISTS `addresses` (
  `ID` int NOT NULL,
  `state` varchar(45) NOT NULL,
  `city` varchar(45) NOT NULL,
  `zipcode` varchar(6) NOT NULL,
  PRIMARY KEY (`ID`),
  UNIQUE KEY `state_UNIQUE` (`state`)
);

INSERT INTO `addresses` (`ID`, `state`, `city`, `zipcode`) VALUES
(1, 'California', 'Los Angeles', '90001'),
(2, 'New York', 'New York City', '10001'),
(3, 'Texas', 'Houston', '77001');

-- --------------------------------------------------------

DROP TABLE IF EXISTS `appointments`;
CREATE TABLE IF NOT EXISTS `appointments` (
  `ID` int NOT NULL,
  `appointment_date` datetime NOT NULL,
  `status` tinyint(1) NOT NULL,
  `surgery_id` int NOT NULL,
  `patient_id` int NOT NULL,
  `dentist_id` int NOT NULL,
  `manager_id` int NOT NULL,
  PRIMARY KEY (`ID`),
  UNIQUE KEY `patient_dentist_date` (`patient_id`,`dentist_id`,`appointment_date`),
  KEY `appointment_surgery_idx` (`surgery_id`),
  KEY `appointment_patient` (`patient_id`),
  KEY `appointment_dentist_idx` (`dentist_id`),
  KEY `appointment_manager_idx` (`manager_id`)
);

INSERT INTO `appointments` (`ID`, `appointment_date`, `status`, `surgery_id`, `patient_id`, `dentist_id`, `manager_id`) VALUES
(1, '2024-04-15 10:00:00', 1, 1, 3, 1, 2),
(2, '2024-04-20 14:30:00', 1, 2, 3, 1, 2),
(3, '2024-04-22 09:00:00', 1, 3, 3, 1, 2);

-- --------------------------------------------------------

CREATE TABLE IF NOT EXISTS `surgeries` (
  `ID` int NOT NULL,
  `name` varchar(45) NOT NULL,
  `address_id` int DEFAULT NULL,
  `phone_number` varchar(10) DEFAULT NULL,
  PRIMARY KEY (`ID`),
  UNIQUE KEY `address_id_UNIQUE` (`address_id`),
  KEY `surgery_address_idx` (`address_id`)
);

INSERT INTO `surgeries` (`ID`, `name`, `address_id`, `phone_number`) VALUES
(1, 'Advantis Dental Clinic - Los Angeles', 1, '1234567890'),
(2, 'Advantis Dental Clinic - New York', 2, '9876543210'),
(3, 'Advantis Dental Clinic - Houston', 3, '5551234567');

-- --------------------------------------------------------

CREATE TABLE IF NOT EXISTS `users` (
  `ID` int NOT NULL AUTO_INCREMENT,
  `username` varchar(45) NOT NULL,
  `password` varchar(45) NOT NULL,
  `user_type` varchar(45) NOT NULL,
  `firstname` varchar(45) DEFAULT NULL,
  `lastname` varchar(45) DEFAULT NULL,
  `email` varchar(45) DEFAULT NULL,
  `DOB` date DEFAULT NULL,
  `specialization` varchar(45) DEFAULT NULL,
  `address_id` int DEFAULT NULL,
  PRIMARY KEY (`ID`),
  UNIQUE KEY `username_UNIQUE` (`username`)
);

INSERT INTO `users` (`ID`, `username`, `password`, `user_type`, `firstname`, `lastname`, `email`, `DOB`, `specialization`, `address_id`) VALUES
(1, 'john_doe', 'password1', 'dentist', 'John', 'Doe', 'johndoe@example.com', '1980-05-15', 'General Dentist', 1),
(2, 'susan_smith', 'password2', 'manager', 'Susan', 'Smith', 'susansmith@example.com', '1975-10-20', NULL, 2),
(3, 'mike_jones', 'password3', 'patient', 'Mike', 'Jones', 'mikejones@example.com', '1990-03-25', NULL, 3);

--
-- Constraints for table `appointments`
--
ALTER TABLE `appointments`
  ADD CONSTRAINT `appointment_dentist` FOREIGN KEY (`dentist_id`) REFERENCES `users` (`ID`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  ADD CONSTRAINT `appointment_manager` FOREIGN KEY (`manager_id`) REFERENCES `users` (`ID`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  ADD CONSTRAINT `appointment_patient` FOREIGN KEY (`patient_id`) REFERENCES `users` (`ID`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  ADD CONSTRAINT `appointment_surgery` FOREIGN KEY (`surgery_id`) REFERENCES `surgeries` (`ID`) ON DELETE RESTRICT ON UPDATE RESTRICT;

--
-- Constraints for table `surgeries`
--
ALTER TABLE `surgeries`
  ADD CONSTRAINT `surgery_address` FOREIGN KEY (`address_id`) REFERENCES `addresses` (`ID`);

--
-- Constraints for table `users`
--
ALTER TABLE `users`
  ADD CONSTRAINT `user_address` FOREIGN KEY (`address_id`) REFERENCES `addresses` (`ID`);


-- --------------------------------------------------------

-- Query 1
SELECT * FROM users
WHERE user_type = 'dentist'
ORDER BY lastname ASC;

-- Query 2
SELECT * FROM appointments ap
INNER JOIN users u ON u.ID = ap.dentist_id;

-- Query 3
SELECT * FROM appointments
WHERE surgery_id = 1;

-- Query 4
SELECT * FROM appointments
WHERE patient_id = 3
AND DATE(appointment_date) LIKE '2024-04-15';
