-- Inserting address records into the addresses table
INSERT INTO addresses (id, city, state, street, zip_code)
VALUES
(1, 'New York City', 'NY', '123 Main St', '10001'),
(2, 'Los Angeles', 'CA', '456 Oak St', '90001'),
(3, 'Chicago', 'IL', '789 Elm St', '60601');


-- Inserting surgery records into the surgery table
INSERT INTO surgery (name, phone_number, address_id)
VALUES
('Orthopedic Surgery', '123-456-7890', 1),
('Dental Surgery', '987-654-3210', 2),
('Cardiac Surgery', '555-123-4567', 3);


-- Inserting user records into the users table
INSERT INTO users (user_type, id, email, firstname, lastname, password, username, specialization, dob, address_id)
VALUES
('Dentist', 1, 'john.doe@example.com', 'John', 'Doe', 'password123', 'johndoe', 'Orthodontics', '1980-05-15', 1),
('Patient', 2, 'jane.smith@example.com', 'Jane', 'Smith', 'password456', 'janesmith', NULL, '1990-09-20', 2),
('Manager', 3, 'admin@example.com', 'Admin', 'User', 'admin123', 'adminuser', NULL, '1975-03-10', 3);

-- Inserting appointment records into the appointments table
INSERT INTO appointments (id, appointment_date, status, surgery_id, dentist_id, manager_id, patient_id)
VALUES
(1, '2024-04-15 10:00:00', 1, 1, 1, 1, 1),
(2, '2024-04-16 14:30:00', 1, 2, 2, 1, 2),
(3, '2024-04-17 09:15:00', 1, 3, 3, 1, 3);
