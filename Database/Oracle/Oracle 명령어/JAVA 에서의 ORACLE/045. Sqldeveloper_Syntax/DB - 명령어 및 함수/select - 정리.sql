/* select - 정리 */

/*
▶ SELECT  (자료를 보여주는 구문)
▷ 기본형
 SELECT [DISTINCT] {*, column[Alias], ...} FROM 테이블명;
▷ select * from employees;    -> 가져와라  모두를   employees의 테이블에서
 select employee_id, last_name from employees;  -> 가져와라 id나 이름만 
 
▷ 산술 연산자
 select first_name, salary + 100 from employees;
 select first_name, salary - 100 from employees;
 select first_name, salary * 100 from employees;
 select first_name, salary / 100 from employees;
 
▷ null
 zero, 빈 공간은 아니다. 
 미확정 값 알 수 없는 값을 의미한다.
 어떤 값인지 알 수 없지만 값이 존재한다.
 ? 혹은 무한대의 의미이므로 연산, 할당, 비교가 불가능하다.
 
 select last_name, salary*commission_pct from employees;
 null 인 경우가 있기 때문에 결과는 null이 나온다.
 
▷ Alias
 select last_name, salary*12 as 연봉 from employees;
 as 생략 가능
 select last_name, salary*12 as “연 봉” from employees;
 공백이나 $ _ # 등 특수문자를 넣을 경우 쌍 따옴표를 사용한다.
 
▷ Concatenation
 select last_name, ' is a ', job_id from employees;
 select last_name || ' is a ' || job_id from employees;
 콤마 대신에 사용하면 문자열이 연결되어 출력된다.
▷ DISTINCT
 select job_id from employees;
 select distinct job_id from employees;
 중복되는 컬럼을 한 번씩만 보여준다.
 */