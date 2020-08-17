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

#CHAT 테이블 수정
CREATE TABLE CHAT(
chatID INT PRIMARY KEY AUTO_INCREMENT, #페이지 별 내용 뽑기
chatName VARCHAR(20), #사용자 닉네임
chatContent VARCHAR(100), #내용
chatTime DATETIME #채팅이 입력된 날짜
);

----------------------------------------------------------------------

CREATE DATABASE anonymouschat DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci ;

CREATE TABLE CHAT(
chatID INT PRIMARY KEY AUTO_INCREMENT, #페이지 별 내용 뽑기
chatName VARCHAR(20), #사용자 닉네임
chatContent VARCHAR(100), #내용
chatTime DATETIME #채팅이 입력된 날짜
) DEFAULT CHARSET=utf8;