-- 데이터베이스 구축(메시지 읽음처리 기능 구현을 위한 chat 테이블 개편)

-- 기존의 chat 테이블 삭제
DROP TABLE CHAT;

CREATE TABLE CHAT (
chatID INT CONSTRAINT chatID_PK PRIMARY KEY,
fromID VARCHAR2(20),
toID VARCHAR2(20),
chatContent VARCHAR2(50),
chatTime DATE,
chatTimeStamp TIMESTAMP,
chatRead INT
);

-- 생성된 테이블 스키마 확인
DESC CHAT;

-- chatID 와 관련한 sequence 생성
CREATE SEQUENCE CHAT_chatID
START WITH 1
INCREMENT BY 1;

-- 읽음처리 확인
SELECT * FROM CHAT;
