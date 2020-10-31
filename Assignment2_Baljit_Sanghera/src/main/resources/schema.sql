CREATE TABLE cars (id INT NOT NULL Primary key AUTO_INCREMENT, make VARCHAR(20), model VARCHAR(20), colour VARCHAR(20), price DOUBLE, vin INT, dealership VARCHAR(30));

INSERT INTO cars VALUES
(1, 'Ferrari', 'F8 Tributo', 'Red', 450000, 123, 'Dealership One'),
(2, 'Ferrari', 'SP90 Stradale', 'Red', 500000, 122, 'Dealership Two'),
(3, 'Ferrari', '812 GTS', 'Red', 700000, 124, 'Dealership Three'),
(4, 'Lamborghini', 'Aventador', 'Green', 200000, 12354, 'Dealership One'),
(5, 'Lamborghini', 'Huracan', 'Red', 350000, 1227, 'Dealership Three'),
(6, 'Lamborghini', 'Sián FKP 37', 'Red', 1200000, 1239, 'Dealership Three'),
(7, 'McLaren', '540C', 'Orange', 500000, 12309, 'Dealership Three'),
(8, 'McLaren', '570S Coupe', 'Red', 400000, 12378, 'Dealership Three'),
(9, 'Pagani', 'Huayra', 'Grey', 900000, 12973, 'Dealership Three'),
(10, 'Pagani', 'Zonda', 'Grey', 800000, 123789, 'Dealership Three');


Create TABLE dealerships (id INT NOT NULL Primary key AUTO_INCREMENT, dealershipName VARCHAR(30));

INSERT INTO dealerships VALUES
(1, 'Dealership One'),
(2, 'Dealership Two'),
(3, 'Dealership Three');