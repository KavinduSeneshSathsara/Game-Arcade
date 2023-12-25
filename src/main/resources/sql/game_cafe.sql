

CREATE DATABASE IF NOT EXISTS game_cafe;
USE game_cafe;

CREATE TABLE IF NOT EXISTS user (
                                    UserName VARCHAR(20) NOT NULL PRIMARY KEY,
                                    Password VARCHAR(30) NULL,
                                    Email VARCHAR(20) NULL
);

CREATE TABLE IF NOT EXISTS customer (
                                        cus_id VARCHAR(15) NOT NULL,
                                        contact_num VARCHAR(20) NOT NULL,
                                        email VARCHAR(20) NOT NULL,
                                        cus_name VARCHAR(20) NOT NULL,
                                        customer_address VARCHAR(30) NOT NULL,
                                        UNIQUE (cus_id)
);


CREATE TABLE IF NOT EXISTS employee (
                                        emp_id VARCHAR(15) NOT NULL PRIMARY KEY,
                                        emp_name VARCHAR(20) NOT NULL,
                                        emp_contact_num VARCHAR(20) NOT NULL,
                                        emp_salary VARCHAR(20) NOT NULL,
                                        emp_address VARCHAR(20) NOT NULL
);

CREATE TABLE IF NOT EXISTS play_station (
                                            play_station_id VARCHAR(15) PRIMARY KEY,
                                            play_station_number VARCHAR(20) NOT NULL
);

CREATE TABLE IF NOT EXISTS booking (
                                       booking_id INT AUTO_INCREMENT PRIMARY KEY,
                                       cus_id VARCHAR(15) NOT NULL,
                                       play_station_id VARCHAR(15) NOT NULL,
                                       booking_date DATE NOT NULL,
                                       booking_time TIME NOT NULL,
                                       duration_hours INT NOT NULL,
                                       FOREIGN KEY (cus_id) REFERENCES customer (cus_id) ON UPDATE CASCADE ON DELETE CASCADE,
                                       FOREIGN KEY (play_station_id) REFERENCES play_station(play_station_id) ON UPDATE CASCADE ON DELETE CASCADE,
                                       UNIQUE (booking_id)
);

CREATE TABLE IF NOT EXISTS booking_Details(
                                             booking_id INT PRIMARY KEY,
                                             total_amount DECIMAL(10, 2),
                                             booking_status VARCHAR(20) NOT NULL DEFAULT 'Pending',
                                             FOREIGN KEY (booking_id) REFERENCES booking(booking_id)
);

CREATE TABLE IF NOT EXISTS payment (
                                       payment_id VARCHAR(15) NOT NULL PRIMARY KEY,
                                       booking_id INT NOT NULL,
                                       payment_date DATE NULL,
                                       payment_time TIME NULL,
                                       amount VARCHAR(20) NOT NULL,
                                       FOREIGN KEY (booking_id) REFERENCES booking (booking_id) ON UPDATE CASCADE ON DELETE CASCADE
);



