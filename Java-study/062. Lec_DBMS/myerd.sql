
/* Drop Tables */

DROP TABLE register CASCADE CONSTRAINTS;
DROP TABLE student CASCADE CONSTRAINTS;
DROP TABLE subject CASCADE CONSTRAINTS;
DROP TABLE professor CASCADE CONSTRAINTS;




/* Create Tables */

CREATE TABLE professor
(
	-- 교수님이 고유번호
	-- 
	profno number NOT NULL,
	-- 교수님 이름입니다
	name varchar2(10) NOT NULL,
	-- 소속 학과 번호
	deptno number,
	PRIMARY KEY (profno)
);


CREATE TABLE register
(
	-- 학생 고유번호
	studno number NOT NULL,
	-- 과목고유번호 입니다.
	subjno number NOT NULL
);


CREATE TABLE student
(
	-- 학생 고유번호
	studno number NOT NULL,
	-- 학생 이름입니다
	name varchar2(10) NOT NULL,
	-- 소속 학과번호
	deptno number,
	-- 교수님이 고유번호
	-- 
	profno number NOT NULL,
	PRIMARY KEY (studno)
);


CREATE TABLE subject
(
	-- 과목고유번호 입니다.
	subjno number NOT NULL,
	-- 과목명 입니다.
	name varchar2(10),
	-- 교수님이 고유번호
	-- 
	profno number NOT NULL,
	PRIMARY KEY (subjno)
);



/* Create Foreign Keys */

ALTER TABLE student
	ADD FOREIGN KEY (profno)
	REFERENCES professor (profno)
;


ALTER TABLE subject
	ADD FOREIGN KEY (profno)
	REFERENCES professor (profno)
;


ALTER TABLE register
	ADD FOREIGN KEY (studno)
	REFERENCES student (studno)
;


ALTER TABLE register
	ADD FOREIGN KEY (subjno)
	REFERENCES subject (subjno)
;



/* Comments */

COMMENT ON COLUMN professor.profno IS '교수님이 고유번호
';
COMMENT ON COLUMN professor.name IS '교수님 이름입니다';
COMMENT ON COLUMN professor.deptno IS '소속 학과 번호';
COMMENT ON COLUMN register.studno IS '학생 고유번호';
COMMENT ON COLUMN register.subjno IS '과목고유번호 입니다.';
COMMENT ON COLUMN student.studno IS '학생 고유번호';
COMMENT ON COLUMN student.name IS '학생 이름입니다';
COMMENT ON COLUMN student.deptno IS '소속 학과번호';
COMMENT ON COLUMN student.profno IS '교수님이 고유번호
';
COMMENT ON COLUMN subject.subjno IS '과목고유번호 입니다.';
COMMENT ON COLUMN subject.name IS '과목명 입니다.';
COMMENT ON COLUMN subject.profno IS '교수님이 고유번호
';



