SELECT s.studno, s.name, s.deptno1
FROM t_student s
;

SELECT d.DEPTNO , d.DNAME 
FROM T_DEPARTMENT d
;


SELECT name
FROM T_PROFESSOR
;


SELECT s.name, p.name
FROM t_student s, t_professor p
;

-- 카티션 곱

-- ORACLE 구문
SELECT s.studno, s.name, s.deptno1,
		d.deptno, d.dname
FROM t_student s, t_department d
WHERE
	s.deptno1 = d.deptno  -- JOIN 조건 
;

-- ANSI SQL구문
SELECT s.studno, s.name, s.deptno1,
		d.deptno, d.dname
FROM t_student s JOIN t_department d
		ON s.deptno1 = d.deptno  -- JOIN 조건 
;


SELECT s.studno, s.name, s.deptno2,
		d.deptno, d.dname
FROM t_student s JOIN t_department d
		ON s.deptno2 = d.deptno  -- JOIN 조건 
;

SELECT name, deptno1, deptno2 FROM t_student;


-- #6102
-- ORACLE 조인 구문
SELECT s.name, s.profno, p.na1`me
FROM 
	t_student s, t_professor p
WHERE
	s.profno = p.profno
;


-- ANSI 구문
SELECT s.name, s.profno, p.name
FROM 
	t_student s 
	JOIN t_professor p ON s.profno = p.profno
;

-- left outer
-- ANSI 구문
SELECT s.name, s.profno, p.name
FROM 
	t_student s LEFT OUTER JOIN t_professor p ON s.profno = p.profno
;


-- RIGHT OUTER JOIN
SELECT s.name, s.profno, p.name
FROM 
	t_student s RIGHT OUTER JOIN t_professor p ON s.profno = p.profno
;

-- Full Outer
SELECT s.name, s.profno, p.name
FROM 
	t_student s FULL OUTER JOIN t_professor p ON s.profno = p.profno
;

-- 3개 테이블 Join
-- ORACLE 구문
SELECT
	s.name, d.dname, p.name
FROM 
	t_student s, t_department d, t_professor p
WHERE
	s.deptno1 = d.deptno AND s.profno = p.profno;

-- ANSI 구문
SELECT
	s.name, d.dname, p.name
FROM 
	t_student s 
		JOIN t_department d ON  s.deptno1 = d.deptno
		JOIN t_professor p ON s.profno = p.profno
;


-- SELF JOIN
SELECT * FROM t_dept2;


SELECT d1.dname 부서명, d2.dname 상위부서명
FROM t_dept2 d1, t_dept2 d2
WHERE d1.pdept = d2.dcode;



SELECT * FROM t_student;









