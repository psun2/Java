-- ä�� ���� ���̺� ����
CREATE TABLE CHAT (
chatID INT CONSTRAINT chatID_PK PRIMARY KEY,
fromID VARCHAR2(20),
toID VARCHAR2(20),
chatContent VARCHAR2(100),
chatTime DATE,
chatTimeStamp TIMESTAMP
);

-- ���̺� ����
DROP TABLE CHAT;

-- chatID �� ������ sequence ����
CREATE SEQUENCE CHAT_chatID
START WITH 1
INCREMENT BY 1;

-- start with ������ ���� sequence ����
DROP SEQUENCE CHAT_chatID;

-- sequence ����� (START WITH �� 0 �� �� �� ����)
CREATE SEQUENCE chatID
START WITH 1
INCREMENT BY 1;

-- start with ������ ���� sequence ����
DROP SEQUENCE chatID;

-- ���� ������ ����
INSERT INTO CHAT (chatID, fromID, toID, chatContent, chatTime, chatTime2) values (chatID.nextval, '7777', '���', '�ȳ�?', SYSDATE, SYSDATE);

-- ���� ������ Ȯ��
SELECT * FROM CHAT;

-- SQL Qurey �� ���� (getChatListByID)
SELECT * FROM CHAT WHERE ((fromID  = '7777' AND toID = '���') OR (fromID = '���' AND toID = '7777')) AND chatID = 1 ORDER BY chatTime2;

-- SQL Qurey �� ���� (getChatListByRecent)
SELECT * FROM CHAT WHERE ((fromID  = '7777' AND toID = '���') OR (fromID = '���' AND toID = '7777')) AND chatID = (SELECT MAX(chatID) - 1 FROM CHAT) ORDER BY chatTime2;

-- Ȯ���۾��� column�� ����
ALTER TABLE CHAT RENAME COLUMN chatTime2 TO chatTimeStamp; 

-- ���� ������ ����
INSERT INTO CHAT (chatID, fromID, toID, chatContent, chatTime, chatTimeStamp) values (chatID.nextval, '7777', '���', '�ȳ�2?', SYSDATE, SYSDATE);

-- SYSDATE ����
SELECT TO_CHAR(SYSDATE, 'HH24:MI:SS') FROM DUAL;

SELECT TO_CHAR(SYSDATE, 'HH24:MI:SS') as CHATTIMESTAMP FROM DUAL;

SELECT SYSDATE-1 FROM DUAL; -- (1������ ��¥/�ð��� ��ȯ)

SELECT SYSDATE+1 FROM DUAL; -- (1������ ��¥/�ð��� ��ȯ)

SELECT TO_CHAR(SYSDATE, 'HH24:MI:SS') FROM DUAL; -- (�ð��� ��ȯ (24�ð�����), ex> 16:53:43);

SELECT TO_CHAR(SYSDATE, 'HH:MI:SS') FROM DUAL; -- (�ð��� ��ȯ, ex> 04:53:43);

SELECT TO_CHAR(SYSDATE, 'YYYYMMDD') FROM DUAL; -- (��¥�� ��ȯ, ex> 20140416);

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