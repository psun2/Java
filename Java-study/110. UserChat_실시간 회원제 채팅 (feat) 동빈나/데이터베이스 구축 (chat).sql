# 데이터베이스 접속
USE UserChat;

# 테이블 생성 (chat)
CREATE TABLE CHAT (
chatID INT PRIMARY KEY AUTO_INCREMENT,
fromID VARCHAR(20), # 채팅을 보낸사람
toID VARCHAR(20),
chatContent VARCHAR(100),
chatTime DATETIME
);