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