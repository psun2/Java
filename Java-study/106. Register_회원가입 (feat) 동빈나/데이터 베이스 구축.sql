# 데이터 베이스 구축
CREATE DATABASE REGISTER;

# 데이터베이스 접속
USE REGISTER;

# 테이블 생성
CREATE TABLE USER ( 
userID VARCHAR(20) PRIMARY KEY,
userPassword VARCHAR(20),
userName VARCHAR(20),
userAge INT,
userGender VARCHAR(20),
userEmail VARCHAR(20)
);
