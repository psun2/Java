# 데이터베이스 생성
CREATE DATABASE USERCHAT;

# 데이터베이스 접속
USE USERCHAT;

# 테이블 생성
CREATE TABLE USER (
usrID VARCHAR(20),
userPassword VARCHAR(20),
userName VARCHAR(20),
userAge INT,
userGender VARCHAR(20),
userEmail VARCHAR(50),
userProfile VARCHAR(50)
);

