-- 유저 테이블 생성
CREATE TABLE MEMBER (
userID NVARCHAR2(20),
userPassword VARCHAR2(64),
userEmaul VARCHAR2(50),
userEmailHash VARCHAR2(64), -- 사용자에게 메일을 보내기 위한 변수(이메일 인증)
userEmailChecked CHAR(1) --이메일 인증 확인
);

-- 강의 에선 변수 type boolean 인 반면... 
-- oracle 은 boolean type을 지원 하지 않음
-- userEmailChecked BOOLEAN

-- 생성확인
SELECT * FROM MEMBER;

-- 테이블 스키마 확인
DESC MEMBER;

-- 컬럼명 변경
ALTER TABLE MEMBER RENAME COLUMN userEmaul TO userEmail;

-- 컬럼 자료형 변경
ALTER TABLE MEMBER MODIFY userEmailChecked INT;
