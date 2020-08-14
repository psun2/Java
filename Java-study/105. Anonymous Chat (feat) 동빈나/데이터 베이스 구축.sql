# 데이터베이스 생성
CREATE DATABASE ANONYMOUSCHAT;

# 데이터베이스 접속
USE ANONYMOUSCHAT;

#CHAT 테이블 생성
CREATE TABLE CHAT(
chatName VARCHAR(20), #사용자 닉네임
chatContent VARCHAR(100), #내용
chatTime DATETIME #채팅이 입력된 날짜
);