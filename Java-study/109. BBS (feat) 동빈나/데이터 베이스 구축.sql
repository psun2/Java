# 데이터베이스 구축
CREATE DATABASE BBS;

# 데이터베이스 접속
USE BBS;

# 테이블 생성
CREATE TABLE USER (
userID VARCHAR(20),
userPassword VARCHAR(20),
userName VARCHAR(20),
userGender VARCHAR(20),
userEmail VARCHAR(50),
PRIMARY KEY (userID)
);

# 해당 데이터베이스의 테이블 확인
SHOW TABLES;

# 테이블 컬럼 속성 확인
DESC USER;

# 테이블 INSERT 테스트
INSERT INTO USER VALUES ('gildong', '123456', '홍길동', '남자', 'gildong@naver.com');

# 해당 테이블의 값 확인 
SELECT * FROM USER;

# 저장
commit;