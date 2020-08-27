오라클 디비 접속 :  sqlplus system/oracle;

유저(계정)생성 : CREATE USER scott_b IDENTIFIED BY tiger_b;

권한 부여 : GRANT connect, resource, create view, create procedure TO scott_b;

유저 보기 :  show user;

유저 접속 : conn scott_b/tiger_b;

테이블 보기 : SELECT tname FROM tab;

스키마 확인 : DESC t_student;

DBeaver 설치

