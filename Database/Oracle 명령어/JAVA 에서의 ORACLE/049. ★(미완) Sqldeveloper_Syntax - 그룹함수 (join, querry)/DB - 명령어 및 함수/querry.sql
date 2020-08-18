/* querry */

select rownum 로우넘, member.* from member;

--select : 선택하다
--rownum : 열번호 를 주는 Oracle의 내장 함수
--로우넘 : 컬럼명
--member.* : member 의 모든 컬럼명
--from : ~ 로부터
--member : member 테이블

select member.*, rownum 로우넘 from member;

select rownum 로우넘, member.* from member
order by name; 

--select : 선택하다
--rownum : 열번호 를 주는 Oracle의 내장 함수
--로우넘 : 컬럼명
--member.* : member 의 모든 컬럼명
--from : ~ 로부터
--member : member 테이블
--order by : 정렬하겟다
--name : 이름 순으로 (오름 차순)

select rownum 로우넘, member.* from member
order by name desc;

--select : 선택하다
--rownum : 열번호 를 주는 Oracle의 내장 함수
--로우넘 : 컬럼명
--member.* : member 의 모든 컬럼명
--from : ~ 로부터
--member : member 테이블
--order by : 정렬하겟다
--name : 이름 순으로 
-- desc : 내림 차순

/* 서브 쿼리 */
/* 정렬 되어 있는 테이블로 보겟다 */
select rownum 로우넘, querry.* from (select * from member order by name) querry;

-- querry 사용이유 : 정렬된 상태로 볼 것인데 위의 예제에서는 정렬과 동시에 rownum이 꼬임현상 발생
-- 쿼리 문으로 한번에 통채로 묶어 보겠다.

--select : 선택하다
--rownum : 열에 번호를 지정하는 Oracle 의 내장함수
--로우넘 : 컬럼명
--querry : 임시 변수 명
--querry.* : querry 의 모든 컬럼 
--from : ~ 로 부터
--(select * from member order by name) : 통채로 묶은 테이블
--즉 정렬 상태의 member 테이블
--querry : 쿼리를 지정하기 위한 임시 변수명

--1	2	칠가인	김영재	영화	159.9	19/10/13 00:00:00.000000000	90/12/11
--2	1	한가인	박성언	Tv봐요	175.5	19/10/13 00:00:00.000000000	90/05/12
--3	7	삼가인	식목일	잠자기		20/03/02 00:00:00.000000000	01/04/05
--4	6	십가인	크리스마스	영화		20/03/02 00:00:00.000000000	98/12/25
--5	1	육가인	호이호이	파리리	1	15/05/05 00:00:00.000000000	88/08/08
--6	5	한가인	홍길동	영화		20/02/06 13:00:00.000000000	50/04/25

 /* 서브쿼리 - 테이블로도 사용하고, 한 가지의 컬럼으로도 사용 가능 */
 /* join 과 유사하나 join 보다 더 많은 일을 할 수 있다 */
select avg(kor) from exam;

-- 63.30769230769230769230769230769230769231 

select kor from exam;
 
select exam.* from exam where kor >= (select avg(kor) from exam);
--select : 선택하다
--exam.* : exam 의 모든 컬럼
--* 말고 exam.* 을 사용하는이유 : where의 조건이 들어가기때문에 exam의 kor이라는 것을 알 아야 하기 때문
--from : ~ 로 부터
--exam : exam 테이블
--where : (조건문) 어디
--kor : kor 컬럼이
--(select avg(kor) from exam) : (쿼리는 항상 괄호 안에서 사용합니다.) exam의 kor컬럼의 평균점수
--보다 같거나 크다면 출력

--한가인	97	97	97			한가인
--두가인	72	37	74			bbb
--사가인	74	47	77			aaa
--육가인	78	56	35	1	기말	육가인
--칠가인	98	86	85	1	기말	bbb
--팔가인	78	76	75	1	기말	aaa
--십이가인	68	66	65	2	중간	fff
--십삼가인	100	97	13	2	중간	ccc

select max(no) + 1 as maxnum from exam;
select max(no) + 1 as "maxnum + 1" from exam;
-- 14

--select : 선택하다
--max(no) + 1 : max => 최대값을 반환하는 Oracle 의 내장함수 최대값 + 1
--as : 로서 컬럼명을 설정
--"maxnum + 1" :  따옴표 없이 사용 가능 하나 띄어쓰기등 이 있을씨 쌍따옴표로 묶어 사용 가능
--from : ~ 로부터
--exam : exam 테이블

insert into exam (no, pid, hakgi, title, kor, eng, mat) values (14, 'aaa', 2, '중간', 56, 63, 51);

--insert : 삽입
--into : ~ 로
--exam : exam 테이블
--(no, pid, hakgi, title, kor, eng, mat) : 값을 삽입할 컬럼명
--컬럼명이 없을시 null로 삽입
--values (14, 'aaa', 2, '중간', 56, 63, 51) : 값들 (값들)

insert into exam (no, pid, hakgi, title, kor, eng, mat) values
((select max(no) +1 from exam), 'bbb', 2, '중간', 76, 73, 71);
--insert : 삽입
--into : ~ 로
--exam : exam 테이블
--(no, pid, hakgi, title, kor, eng, mat) : 값을 삽입할 컬럼명
--컬럼명이 없을시 null로 삽입
--values : 값들
--((select max(no) +1 from exam), 'bbb', 2, '중간', 76, 73, 71) : (select max(no) +1 from exam) =>
--querry 사용 : 현재의 가장 큰번호에서 +1 한 값을 넣어 주기 위하여 쿼리문 사용

select pid, mat from exam where mat >= 80;
--select : 선택하다
--pid : 선택한 컬럼명
--from : ~ 로 부터
--exam : exam 테이블
--where : (조건문) 어디
--mat : mat 컬럼
--이 80 이상인 pid만...

--한가인	97
--칠가인	85
--구가인	85
--십일가인	95

select exam.* from exam where mat >= 80;
--한가인	97	97	97			한가인	1
--칠가인	98	86	85	1	기말	bbb	7
--구가인	18	36	85	2	기말	fff	9
--십일가인	58	96	95	2	중간	십일가인	11

select * from exam where pid in('bbb', 'ddd', 'eee');
--select : 선택하다
--* : all (모든요소)
--from : ~ 로 부터
--member : 테이블명
--where (조건문) 어디
--id : 컬럼명이 id 인 컬럼
--in : 앞으로 나올 모든 값들이 맞다면
-- 즉 id 가 bbb, ddd, eee 인 값들만 나옵니다

select * from member where id in(select pid from exam where mat >= 80);

--select : 선택하다
--* : all 컬럼 (모든요소)
--from ~ 로 부터
--member : member 테이블
--where : ~ 로 부터
--id : id 값
--in : 괄호 안의 모든 조건이...
--(select pid from exam where mat >= 80) : 쿼리문 사용
--쿼리문을 사용함으로써 하나의 조건으로 보겟다..
--즉 다른 테이블의 값을 가져와 사용하고 싶어 쿼리를 사용

--5	한가인	홍길동	영화		20/02/06 13:00:00.000000000	50/04/25
--1	한가인	박성언	Tv봐요	175.5	19/10/13 00:00:00.000000000	90/05/12
--2	칠가인	김영재	영화	159.9	19/10/13 00:00:00.000000000	90/12/11