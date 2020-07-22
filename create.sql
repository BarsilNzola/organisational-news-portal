CREATE DATABASE organizational_news;
\c organizational_news
CREATE TABLE departments (id SERIAL PRIMARY KEY, name VARCHAR, description VARCHAR, noOfEmployees INTEGER);
CREATE TABLE news (id SERIAL PRIMARY KEY, post VARCHAR, postedBy VARCHAR);
CREATE TABLE users (id SERIAL PRIMARY KEY, name VARCHAR, position VARCHAR, role VARCHAR, departmentId INTEGER);
CREATE TABLE department_news (id SERIAL PRIMARY KEY, post VARCHAR, postedBy VARCHAR);
CREATE TABLE department_departmentnews (id SERIAL PRIMARY KEY, departmentId INTEGER, departmentNewsId INTEGER);
CREATE DATABASE organizational_news_test WITH TEMPLATE organizational_news;