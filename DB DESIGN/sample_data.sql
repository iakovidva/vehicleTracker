use vehicle_tracker;
INSERT INTO owner (owner_id, first_name, last_name, email, phone_number, password)
VALUES
(1, 'John', 'Doe', 'johndoe@example.com', '123-456-7890', 'password123'),
(2, 'Jane', 'Doe', 'janedoe@example.com', '987-654-3210', 'password456'),
(3, 'Bob', 'Smith', 'bobsmith@example.com', '555-555-5555', 'password789'),
(4, 'Alice', 'Johnson', 'alicejohnson@example.com', '111-111-1111', 'password101'),
(5, 'Mark', 'Williams', 'markwilliams@example.com', '222-222-2222', 'password202'),
(6, 'Sarah', 'Taylor', 'sarahtaylor@example.com', '333-333-3333', 'password303'),
(7, 'David', 'Lee', 'davidlee@example.com', '444-444-4444', 'password404'),
(8, 'Anna', 'Garcia', 'annagarcia@example.com', '555-555-5555', 'password505'),
(9, 'Michael', 'Brown', 'michaelbrown@example.com', '666-666-6666', 'password606'),
(10, 'Julia', 'Anderson', 'juliaanderson@example.com', '777-777-7777', 'password707');

INSERT INTO mechanic (mechanic_id, first_name, last_name, email, phone_number, password, workshop_name, specialisation)
VALUES
(1, 'Adam', 'Smith', 'adampsmith@example.com', '111-222-3333', 'password123', 'AutoWorks', 'Engine repair, Brake system'),
(2, 'Sarah', 'Lee', 'sarahlee@example.com', '444-555-6666', 'password456', 'CarTech', 'Transmission repair, Suspension system'),
(3, 'Brian', 'Johnson', 'brianjohnson@example.com', '777-888-9999', 'password789', 'Speedy Auto', 'AC repair, Electrical system'),
(4, 'Laura', 'Davis', 'lauradavis@example.com', '222-333-4444', 'password101', 'Top Gear', 'Oil change, Battery replacement'),
(5, 'Mike', 'Garcia', 'mikegarcia@example.com', '555-666-7777', 'password202', 'TuneUp', 'Tire repair, Wheel alignment');


INSERT INTO vehicle (vehicle_id, make, model, license_plate, production_date, owner_id, mechanic_id, dt_eff, dt_end)
VALUES
(1, 'Honda', 'Civic', 'ABC123', '2020-06', 1, NULL, '2023-04-05', '2099-12-31'),
(2, 'Toyota', 'Corolla', 'XYZ789', '2019-08', 2, 2, '2023-04-05', '2099-12-31'),
(3, 'Ford', 'Mustang', 'DEF456', '2017-05', 3, 3, '2023-04-05', '2099-12-31'),
(4, 'Chevrolet', 'Camaro', 'GHI789', '2022-01', 4, 4, '2023-04-05', '2099-12-31'),
(5, 'Jeep', 'Wrangler', 'JKL012', '2018-03', 5, 5, '2023-04-05', '2099-12-31'),
(6, 'Tesla', 'Model 3', 'MNO345', '2021-09', 6, NULL, '2023-04-05', '2099-12-31'),
(7, 'BMW', '3 Series', 'PQR678', '2020-02', 7, 1, '2023-04-05', '2099-12-31'),
(8, 'Audi', 'A4', 'STU901', '2019-11', 8, NULL, '2023-04-05', '2099-12-31'),
(9, 'Mercedes-Benz', 'C-Class', 'VWX234', '2016-12', 9, 2, '2023-04-05', '2099-12-31'),
(10, 'Nissan', 'Altima', 'YZA567', '2017-09', 10, NULL, '2023-04-05', '2099-12-31'),
(11, 'Mazda', 'CX-5', 'BCD890', '2022-02', 8, 3, '2023-04-05', '2099-12-31'),
(12, 'Subaru', 'Impreza', 'EFG123', '2018-11', 7, NULL, '2023-04-05', '2099-12-31'),
(13, 'Hyundai', 'Elantra', 'HIJ456', '2015-04', 7, NULL, '2023-04-05', '2099-12-31'),
(14, 'Kia', 'Soul', 'KLM789', '2019-06', 3, 4, '2023-04-05', '2099-12-31'),
(15, 'Volkswagen', 'Golf', 'NOP012', '2020-08', 1, 5, '2023-04-05', '2099-12-31');

INSERT INTO daily_activity (daily_activity_id, vehicle_id, activity, comments, cost, kilometers, dt_rec)
VALUES
(1, 1, 'Petrol Filling', 'Filled up the petrol tank', 40.00, 2000, '2023-03-02'),
(2, 2, 'Tire Pressure Check', 'Checked and adjusted tire pressure', 0.00, 5000, '2023-03-04'),
(3, 2, 'Oil Check', 'Checked and topped up oil level', 0.00, 10000, '2023-03-10'),
(4, 3, 'Tire Rotation', 'Rotated tires', 60.00, 8000, '2023-03-15'),
(5, 4, 'Battery Check', 'Checked battery voltage', 0.00, 6000, '2023-03-17'),
(6, 5, 'Oil Change', 'Performed oil change', 70.00, 12000, '2023-03-20'),
(7, 6, 'Brake Check', 'Checked brake pads and rotors', 0.00, 3000, '2023-03-21'),
(8, 6, 'Tire Replacement', 'Replaced one tire', 100.00, 7000, '2023-03-23'),
(9, 6, 'Oil Check', 'Checked and topped up oil level', 0.00, 4000, '2023-03-24'),
(10, 7, 'Petrol Filling', 'Filled up the petrol tank', 30.00, 8000, '2023-03-25'),
(11, 8, 'Oil Change', 'Performed oil change', 60.00, 9000, '2023-03-26'),
(12, 9, 'Tire Pressure Check', 'Checked and adjusted tire pressure', 0.00, 5000, '2023-03-27'),
(13, 10, 'Battery Replacement', 'Replaced battery', 150.00, 3000, '2023-03-28'),
(14, 11, 'Petrol Filling', 'Filled up the petrol tank', 40.00, 6000, '2023-03-29'),
(15, 11, 'Tire Check', 'Checked and adjusted tire pressure', 0.00, 10000, '2023-03-30'),
(16, 12, 'Oil Change', 'Performed oil change', 50.00, 5000, '2023-03-31'),
(17, 13, 'Brake Check', 'Checked brake pads and rotors', 0.00, 12000, '2023-04-01'),
(18, 14, 'Petrol Filling', 'Filled up the petrol tank', 30.00, 2000, '2023-04-02'),
(19, 15, 'Tire Rotation', 'Rotated tires', 50.00, 5000, '2023-04-03'),
(20, 15, 'Oil Check', 'Checked and topped up oil level', 0.00, 10000, '2023-04-04');


INSERT INTO service_book (vehicle_id, mechanic_id, activity, comments, cost, kilometers, dt_rec)
VALUES
(1, 1, 'Oil Change', 'Regular maintenance', 50.00, 2000, '2023-03-02'),
(2, 2, 'Brake Service', 'Replaced brake pads', 250.00, 5000, '2023-03-04'),
(2, 3, 'Transmission Fluid Change', 'Performed transmission fluid change', 150.00, 10000, '2023-03-10'),
(3, 4, 'AC Repair', 'Recharged AC system', 200.00, 8000, '2023-03-15'),
(4, 5, 'Wheel Alignment', 'Performed wheel alignment', 80.00, 6000, '2023-03-17'),
(5, 1, 'Tire Replacement', 'Replaced all four tires', 600.00, 12000, '2023-03-20'),
(6, 2, 'Battery Replacement', 'Replaced battery', 150.00, 3000, '2023-03-21'),
(6, 3, 'Brake Service', 'Replaced brake rotors', 400.00, 7000, '2023-03-23'),
(6, 4, 'Oil Change', 'Regular maintenance', 60.00, 4000, '2023-03-24'),
(7, 5, 'Tire Rotation', 'Rotated tires', 50.00, 8000, '2023-03-25'),
(8, 1, 'Transmission Service', 'Performed transmission fluid and filter change', 300.00, 9000, '2023-03-26'),
(9, 2, 'Brake Service', 'Replaced brake pads', 200.00, 5000, '2023-03-27'),
(10, 3, 'Oil Change', 'Regular maintenance', 50.00, 3000, '2023-03-28'),
(11, 4, 'Wheel Alignment', 'Performed wheel alignment', 80.00, 6000, '2023-03-29'),
(11, 5, 'Tire Replacement', 'Replaced two tires', 300.00, 10000, '2023-03-30'),
(12, 1, 'Battery Replacement', 'Replaced battery', 150.00, 5000, '2023-03-31'),
(13, 2, 'AC Repair', 'Repaired AC compressor', 500.00, 12000, '2023-04-01'),
(14, 3, 'Oil Change', 'Regular maintenance', 50.00, 2000, '2023-04-02'),
(15, 4, 'Brake Service', 'Replaced brake pads', 250.00, 5000, '2023-04-03'),
(15, 5, 'Transmission Fluid Change', 'Performed transmission fluid change', 150.00, 10000, '2023-04-04');
