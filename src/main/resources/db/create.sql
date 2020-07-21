SET MODE PostgreSQL;


--CREATE DATABASE organisation;

CREATE TABLE IF NOT EXISTS departments(
id int PRIMARY KEY auto_increment,
name VARCHAR,
description VARCHAR,
noOfEmployees INTEGER,
);

CREATE TABLE IF NOT EXISTS news(
id int PRIMARY KEY auto_increment,
post VARCHAR,
postedBy VARCHAR
);

CREATE TABLE IF NOT EXISTS users(
id int PRIMARY KEY auto_increment,
name VARCHAR,
position VARCHAR,
role VARCHAR,
department_id INTEGER,
);

CREATE TABLE IF NOT EXISTS department_news(
id int PRIMARY KEY auto_increment,
post VARCHAR,
postedBy VARCHAR,
);

CREATE TABLE IF NOT EXISTS departments_departmentNews(
id int PRIMARY KEY auto_increment,
department_id INTEGER,
DepartmentNews_id INTEGER,
);
