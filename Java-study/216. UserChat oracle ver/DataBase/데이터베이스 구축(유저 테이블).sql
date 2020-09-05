-- 유저 테이블 생성
CREATE TABLE MEMBER (
userID  VARCHAR2(20),
userPassword VARCHAR2(20),
userName VARCHAR2(20),
userAge INT,
userGender VARCHAR2(20),
userEmail VARCHAR2(50),
userProfile VARCHAR2(50)
);

-- 테이블 스키마 확인
DESC MEMBER;

-- 오타로 인한 테이블 컬럼 수정
ALTER TABLE MEMBER RENAME COLUMN userPasswrd to userPassword;

-- 테이블 삭제
DROP TABLE MEMBER;
