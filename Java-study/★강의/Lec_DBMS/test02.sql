SELECT * FROM t_emp;
SELECT * FROM t_dept;
-- 직원의 이름(ename)  과 근무부서명(dname) 이 출력되는 쿼리문 작성

SELECT e.ENAME, d.DNAME 
FROM t_emp e, t_dept d
WHERE e.DEPTNO = d.DEPTNO 
;

SELECT e.ENAME, d.DNAME 
FROM t_emp e JOIN t_dept d ON e.DEPTNO = d.DEPTNO 
;



