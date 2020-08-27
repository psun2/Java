# 메시지 읽음 처리 기능 구현을 위한 데이터베이스 개편

# 데이터베이스 접속
USE USERCHAT;

# 기존의 CHAT 테이블 삭제
DROP TABLE CHAT;

CREATE TABLE CHAT (
chatID INT PRIMARY KEY AUTO_INCREMENT,
fromID VARCHAR(20),
toID VARCHAR(20),
chatContent VARCHAR(100),
chatTime DATETIME,
chatRead INT # 읽음 처리 기능 구현을 위한 플래그 값의 컬럼
);

