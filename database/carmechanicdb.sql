CREATE DATABASE IF NOT EXISTS car_mechanic CHARACTER SET utf8 COLLATE utf8_general_ci; 


USE car_mechanic;

CREATE TABLE IF NOT EXISTS roles
(
r_id int NOT NULL AUTO_INCREMENT,
name varchar(255),
PRIMARY KEY (r_id)
);

CREATE TABLE IF NOT EXISTS users 
(
u_id int NOT NULL AUTO_INCREMENT,
username varchar(255) NOT NULL,
password varchar(255),
account_type varchar(255),
role int,
first_name varchar(255),
last_name varchar(255),
email varchar(255),
notifications boolean,
misc varchar(255),
PRIMARY KEY (u_id),
FOREIGN KEY (role) REFERENCES roles(r_id)
);

CREATE TABLE IF NOT EXISTS car_maker
(
m_id int NOT NULL AUTO_INCREMENT,
maker varchar(255),
PRIMARY KEY (m_id)
);

CREATE TABLE IF NOT EXISTS car_model 
(
mod_id int NOT NULL AUTO_INCREMENT,
maker_id int,
model varchar(255),
model_year varchar(255),
engine varchar(255),
PRIMARY KEY (mod_id),
FOREIGN KEY (maker_id) REFERENCES car_maker(m_id)
);

CREATE TABLE IF NOT EXISTS problems 
(
p_id int NOT NULL AUTO_INCREMENT,
model_id int,
description TEXT,
solution TEXT,
photo_path varchar(255),
created_at DATETIME,
status varchar(255),
PRIMARY KEY (p_id),
FOREIGN KEY (model_id) REFERENCES car_model(mod_id)
);

CREATE TABLE IF NOT EXISTS comments 
(
c_id int NOT NULL AUTO_INCREMENT,
prob_id int,
author_id int,
create_at DATETIME,
PRIMARY KEY (c_id),
FOREIGN KEY (prob_id) REFERENCES problems(p_id)
);

