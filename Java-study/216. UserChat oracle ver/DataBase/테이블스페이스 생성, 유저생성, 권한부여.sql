-- 테이블 스페이스 생성
CREATE TABLESPACE USERCHAT
DATAFILE 'D:\Study\Java\Java-study\216. UserChat oracle ver\DataBase\UserChat.dbf'
-- SIZE 50000000;
-- SIZE 100000000;

-- 생성된 테이블 스페이스 확인
SELECT * FROM dba_tablespaces;

-- 생성된 테이블 스페이스 상세 확인
SELECT file_name, file_id, tablespace_name, status FROM dba_data_files WHERE tablespace_name = 'USERCHAT';

-- 계정 생성
CREATE USER USERCHAT IDENTIFIED BY oracle
DEFAULT TABLESPACE USERCHAT
TEMPORARY TABLESPACE temp;

-- 생성한 유저 확인
SELECT * FROM all_users;

-- 생성한 유저의 권한 부여
GRANT RESOURCE, CONNECT, CREATE TABLE, CREATE VIEW, CREATE SEQUENCE TO USERCHAT; 