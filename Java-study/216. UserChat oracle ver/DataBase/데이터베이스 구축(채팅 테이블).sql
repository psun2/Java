-- 채팅 관련 테이블 생성
CREATE TABLE CHAT (
chatID INT CONSTRAINT chatID_PK PRIMARY KEY,
fromID VARCHAR2(20),
toID VARCHAR2(20),
chatContent VARCHAR2(100),
chatTime DATE,
chatTimeStamp TIMESTAMP
);

-- 테이블 삭제
DROP TABLE CHAT;

-- chatID 와 관련한 sequence 생성
CREATE SEQUENCE CHAT_chatID
START WITH 1
INCREMENT BY 1;

-- start with 오류로 인한 sequence 삭제
DROP SEQUENCE CHAT_chatID;

-- sequence 재생성 (START WITH 은 0 이 될 수 없음)
CREATE SEQUENCE chatID
START WITH 1
INCREMENT BY 1;

-- start with 오류로 인한 sequence 삭제
DROP SEQUENCE chatID;

-- 예제 데이터 연습
INSERT INTO CHAT (chatID, fromID, toID, chatContent, chatTime, chatTime2) values (chatID.nextval, '7777', '운영자', '안녕?', SYSDATE, SYSDATE);

-- 예제 데이터 확인
SELECT * FROM CHAT;

-- SQL Qurey 문 증명 (getChatListByID)
SELECT * FROM CHAT WHERE ((fromID  = '7777' AND toID = '운영자') OR (fromID = '운영자' AND toID = '7777')) AND chatID = 1 ORDER BY chatTime2;

-- SQL Qurey 문 증명 (getChatListByRecent)
SELECT * FROM CHAT WHERE ((fromID  = '7777' AND toID = '운영자') OR (fromID = '운영자' AND toID = '7777')) AND chatID = (SELECT MAX(chatID) - 1 FROM CHAT) ORDER BY chatTime2;

-- 확인작업후 column명 변경
ALTER TABLE CHAT RENAME COLUMN chatTime2 TO chatTimeStamp; 

-- 예제 데이터 연습
INSERT INTO CHAT (chatID, fromID, toID, chatContent, chatTime, chatTimeStamp) values (chatID.nextval, '7777', '운영자', '안녕2?', SYSDATE, SYSDATE);

-- SYSDATE 연습
SELECT TO_CHAR(SYSDATE, 'HH24:MI:SS') FROM DUAL;

SELECT TO_CHAR(SYSDATE, 'HH24:MI:SS') as CHATTIMESTAMP FROM DUAL;

SELECT SYSDATE-1 FROM DUAL; -- (1일전의 날짜/시간을 반환)

SELECT SYSDATE+1 FROM DUAL; -- (1일후의 날짜/시간을 반환)

SELECT TO_CHAR(SYSDATE, 'HH24:MI:SS') FROM DUAL; -- (시간만 반환 (24시간포멧), ex> 16:53:43);

SELECT TO_CHAR(SYSDATE, 'HH:MI:SS') FROM DUAL; -- (시간만 반환, ex> 04:53:43);

SELECT TO_CHAR(SYSDATE, 'YYYYMMDD') FROM DUAL; -- (날짜만 반환, ex> 20140416);

SELECT * FROM chat, (select TO_CHAR(chatTimeStamp, 'YYYY/MM/DD HH24:MI:SS') AS time FROM chat) t1;

SELECT * FROM CHAT;
SELECT * FROM CHAT, (SELECT TO_CHAR(chatTimeStamp, 'YYYY/MM/DD HH24:MI:SS') AS time FROM chat) t1 WHERE ((fromID = 'admin' AND toID = '777') OR (fromID = '777' AND toID = 'admin')) AND chatID > (SELECT MAX(chatID) - 10 FROM CHAT) ORDER BY time;
SELECT * FROM CHAT, (SELECT TO_CHAR(chatTimeStamp, 'YYYY/MM/DD HH24:MI:SS') AS time FROM chat) t1 WHERE ((fromID = '777' AND toID = 'admin') OR (fromID = 'admin' AND toID = '777')) AND chatID > 10 ORDER BY time;

SELECT * FROM CHAT, (SELECT TO_CHAR(chatTimeStamp, 'YYYY/MM/DD HH24:MI:SS') AS time FROM chat) t1 WHERE ((fromID = 'admin' AND toID = '777') OR (fromID = '777' AND toID = 'admin'));


select ROWNUM, chat.* from chat;

select ROWNUM, chat.*, TO_CHAR(chatTimeStamp, 'YYYY/MM/DD HH24:MI:SS') from chat;

SELECT * FROM CHAT, (select TO_CHAR(chatTimeStamp, 'YYYY/MM/DD HH24:MI:SS') AS time FROM CHAT)t1;
SELECT * FROM CHAT, (SELECT TO_CHAR(chatTimeStamp, 'YYYY/MM/DD HH24:MI:SS') AS time FROM chat) t1 WHERE chatID > (SELECT MAX(chatID) - 10 FROM CHAT ORDER BY time;
SELECT MAX(chatID) - 10 FROM CHAT;

create view CHAT_VIEW as
select CHAT.*, kor + eng as tot, (kor + eng) / 2 as avg from CHAT;

SELECT ROWNUM, chat.*, TO_CHAR(chatTimeStamp, 'YYYY/MM/DD HH24:MI:SS') AS TIME FROM CHAT WHERE ((fromID = 'admin' AND toID = '777') OR (fromID = '777' AND toID = 'admin')) AND chatID > (SELECT MAX(chatID) - 10 FROM CHAT) ORDER BY time;

SELECT ROWNUM, chat.*, TO_CHAR(chatTimeStamp, 'YYYY/MM/DD HH24:MI:SS') AS TIME FROM CHAT WHERE ((fromID = '777' AND toID = 'admin') OR (fromID = 'admin' AND toID = '777')) AND chatID > 10 ORDER BY time;