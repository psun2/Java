-- �����ͺ��̽� ����(�޽��� ����ó�� ��� ������ ���� chat ���̺� ����)

-- ������ chat ���̺� ����
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

-- ������ ���̺� ��Ű�� Ȯ��
DESC CHAT;

-- chatID �� ������ sequence ����
CREATE SEQUENCE CHAT_chatID
START WITH 1
INCREMENT BY 1;

-- ����ó�� Ȯ��
SELECT * FROM CHAT;
