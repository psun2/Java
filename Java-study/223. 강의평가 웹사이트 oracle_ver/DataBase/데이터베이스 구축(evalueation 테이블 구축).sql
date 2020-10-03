-- 강의 평가글 테이블
CREATE TABLE EVALUEATION (
evaluationID INT CONSTRAINT evaluationID_pk PRIMARY KEY,
userID NVARCHAR2(20),
lectureName VARCHAR2(50), -- 강의명
professorName VARCHAR2(20), -- 교수명
lectureYear INT, -- 강의를 들은 년도
semesterDivide VARCHAR2(20), -- 수강학기
lectureDivide VARCHAR2(10), -- 강의구분
evalueationTitle VARCHAR2(50),
evalueationContent VARCHAR2(2048),
totalScore VARCHAR2(5),
creditScore VARCHAR2(5),
comfortableScore VARCHAR2(5),
lectureScore VARCHAR2(5),
likeCount int
);



-- 생성확인
SELECT * FROM EVALUEATION;
DESC EVALUEATION;


-- evaluationID sequence 생성 (AUTO INCREMENT)
CREATE SEQUENCE evaluationID_squence
START WITH 1
INCREMENT BY 1;


-- byte 증가
ALTER TABLE EVALUEATION MODIFY professorName VARCHAR2(50);