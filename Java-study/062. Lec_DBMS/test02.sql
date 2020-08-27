SELECT * FROM t_emp;
SELECT * FROM t_dept;

-- 직원의 이름(ename) 과 근무부서명(dname) 이 출력 되는 쿼리문 작성
SELECT e.ename, d.dname FROM t_emp e, t_dept d WHERE e.deptno = d.deptno;

SELECT e.ename AS 이름, d.dname AS 부서 FROM t_emp e JOIN t_dept d ON e.deptno = d.deptno;

-- 직원테이블의 부서번호와 부서테이블의 부서번호가 관계가 있음.