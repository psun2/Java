
/* Drop Tables */

DROP TABLE tbl_student CASCADE CONSTRAINTS;
DROP TABLE tbl_department CASCADE CONSTRAINTS;




/* Create Tables */

CREATE TABLE tbl_department
(
	dept_uid number NOT NULL,
	dept_name varchar2(20) NOT NULL,
	PRIMARY KEY (dept_uid)
);


CREATE TABLE tbl_student
(
	stu_uid number NOT NULL,
	stu_name varchar2(20) NOT NULL,
	stu_grade number DEFAULT 1,
	dept_uid number NOT NULL,
	PRIMARY KEY (stu_uid)
);



/* Create Foreign Keys */

ALTER TABLE tbl_student
	ADD FOREIGN KEY (dept_uid)
	REFERENCES tbl_department (dept_uid)
;

SELECT * FROM tbl_student;
SELECT * FROM tbl_department;

