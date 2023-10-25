create database vcakes_v1;
use vcakes_v1;
CREATE TABLE Users (
    user_id INT AUTO_INCREMENT PRIMARY KEY,
    first_name VARCHAR(50) NOT NULL,
    last_name VARCHAR(50) NOT NULL,
    email VARCHAR(100) NOT NULL,
    location VARCHAR(100),
    contact VARCHAR(20) NOT NULL,
    password varchar(50) not null
);
select * from Users;

create table Admins (
id int auto_increment primary key,
username varchar(50) not null,
password varchar(50) not null
);

INSERT INTO Admins (username, password) VALUES ('admin', 'vcakes@admin2023');
select * from Admins;

CREATE TABLE Products (
    id INT AUTO_INCREMENT PRIMARY KEY,
    cake_image VARCHAR(500),
    cake_name VARCHAR(50),
    cake_description VARCHAR(500),
    cake_category VARCHAR(50),
    cake_quantity INT CHECK (cake_quantity >= 0),
    cake_price INT CHECK (cake_price >= 1)
);
select * from Products;
select cake_price from Products where id=1;

CREATE TABLE orders (
    o_id INT PRIMARY KEY AUTO_INCREMENT,
    p_id INT NOT NULL,
    u_id INT NOT NULL,
    o_quantity INT NOT NULL,
    o_date VARCHAR(80),
    FOREIGN KEY (u_id) REFERENCES Users(user_id),
    FOREIGN KEY (p_id) REFERENCES Products(id)
);

SELECT
    u.first_name,
    u.last_name,
    u.location,
    u.contact,
    p.cake_name,
    p.cake_price,
    o.o_quantity AS quantity,
    (p.cake_price * o.o_quantity) AS total
FROM Users AS u
INNER JOIN orders AS o ON u.user_id = o.u_id
INNER JOIN Products AS p ON o.p_id = p.id;


select * from orders;

