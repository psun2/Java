-- ���̺� �����̽� ����
CREATE TABLESPACE USERCHAT
DATAFILE 'D:\Study\Java\Java-study\216. UserChat oracle ver\DataBase\UserChat.dbf'
-- SIZE 50000000;
-- SIZE 100000000;

-- ������ ���̺� �����̽� Ȯ��
SELECT * FROM dba_tablespaces;

-- ������ ���̺� �����̽� �� Ȯ��
SELECT file_name, file_id, tablespace_name, status FROM dba_data_files WHERE tablespace_name = 'USERCHAT';

-- ���� ����
CREATE USER USERCHAT IDENTIFIED BY oracle
DEFAULT TABLESPACE USERCHAT
TEMPORARY TABLESPACE temp;

-- ������ ���� Ȯ��
SELECT * FROM all_users;

-- ������ ������ ���� �ο�
GRANT RESOURCE, CONNECT, CREATE TABLE, CREATE VIEW, CREATE SEQUENCE TO USERCHAT; 