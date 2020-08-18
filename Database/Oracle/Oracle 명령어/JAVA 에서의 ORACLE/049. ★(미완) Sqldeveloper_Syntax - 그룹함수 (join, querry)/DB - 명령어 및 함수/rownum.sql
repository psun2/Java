/* rownum */

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