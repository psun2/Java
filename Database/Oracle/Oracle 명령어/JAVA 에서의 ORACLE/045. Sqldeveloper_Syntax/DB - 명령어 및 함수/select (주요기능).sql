/* select - 사칙연산 */
select 5 + 3; -- Error 오류 발생

select 5 + 3 from dual; -- 8

select member.*, no + 10, no - 10, no * 10, no / 10 from member;
--1	tjddjs90	박성언	Tv봐요	175.5	19/10/13 00:00:00.000000000	90/05/12	11	-9	10	0.1
--2	kyj1211	김영재	영화		19/10/13 00:00:00.000000000	90/12/11	12	-8	20	0.2
--1	rmftpdy	호이호이	파리리	1	15/05/05 00:00:00.000000000	88/08/08	11	-9	10	0.1
--5	ghdrlfrhd	홍길동	영화		20/02/06 13:00:00.000000000	50/04/25	15	-5	50	0.5
--6	zmflrmaktm	크리스마스	영화		20/03/02 00:00:00.000000000	98/12/25	16	-4	60	0.6
--7	tlrahrdlf	식목일	잠자기		20/03/02 00:00:00.000000000	01/04/05	17	-3	70	0.7

/* as 문법 으로 새로운 이름 부여 */
select member.*, name as nickname, hobby as ect from member;
--1	tjddjs90	박성언	Tv봐요	175.5	19/10/13 00:00:00.000000000	90/05/12	박성언	Tv봐요
--2	kyj1211	김영재	영화		19/10/13 00:00:00.000000000	90/12/11	김영재	영화
--1	rmftpdy	호이호이	파리리	1	15/05/05 00:00:00.000000000	88/08/08	호이호이	파리리
--5	ghdrlfrhd	홍길동	영화		20/02/06 13:00:00.000000000	50/04/25	홍길동	영화
--6	zmflrmaktm	크리스마스	영화		20/03/02 00:00:00.000000000	98/12/25	크리스마스	영화
--7	tlrahrdlf	식목일	잠자기		20/03/02 00:00:00.000000000	01/04/05	식목일	잠자기

/* 문자열 더 하기 */
select member.*, hobby + ' 더해요' from member; -- Error
select member.*, hobby || ' 더해요' from member;
--1	tjddjs90	박성언	Tv봐요	175.5	19/10/13 00:00:00.000000000	90/05/12	Tv봐요 더해요
--2	kyj1211	김영재	영화		19/10/13 00:00:00.000000000	90/12/11	영화 더해요
--1	rmftpdy	호이호이	파리리	1	15/05/05 00:00:00.000000000	88/08/08	파리리 더해요
--5	ghdrlfrhd	홍길동	영화		20/02/06 13:00:00.000000000	50/04/25	영화 더해요
--6	zmflrmaktm	크리스마스	영화		20/03/02 00:00:00.000000000	98/12/25	영화 더해요
--7	tlrahrdlf	식목일	잠자기		20/03/02 00:00:00.000000000	01/04/05	잠자기 더해요

/* 필드명 띄어 쓰기 */
select member.*, hobby || ' 필드명 변경' as 띄 어 쓰 기 에 러 from member; -- Error
select member.*, hobby || ' 필드명 변경' as "띄 어 쓰 기" from member;
--1	tjddjs90	박성언	Tv봐요	175.5	19/10/13 00:00:00.000000000	90/05/12	Tv봐요 필드명 변경
--2	kyj1211	김영재	영화		19/10/13 00:00:00.000000000	90/12/11	영화 필드명 변경
--1	rmftpdy	호이호이	파리리	1	15/05/05 00:00:00.000000000	88/08/08	파리리 필드명 변경
--5	ghdrlfrhd	홍길동	영화		20/02/06 13:00:00.000000000	50/04/25	영화 필드명 변경
--6	zmflrmaktm	크리스마스	영화		20/03/02 00:00:00.000000000	98/12/25	영화 필드명 변경
--7	tlrahrdlf	식목일	잠자기		20/03/02 00:00:00.000000000	01/04/05	잠자기 필드명 변경

/* 중복 제거 */
select distinct hobby from member;
--잠자기
--영화
--파리리
--Tv봐요

select distinct hobby, name from member; -- 이름은 중복되지 않으므로 전체 값이 다 나옴
--영화	홍길동
--영화	크리스마스
--Tv봐요	박성언
--영화	김영재
--파리리	호이호이
--잠자기	식목일
select distinct name, hobby from member; -- 이름은 중복되지 않으므로 전체 값이 다 나옴
--박성언	Tv봐요
--호이호이	파리리
--홍길동	영화
--김영재	영화
--크리스마스	영화
--식목일	잠자기

/* like - 검색, 초성검색 */
select * from member where id like '%90';
-- tjddjs90	박성언	Tv봐요	175.5	19/10/13 00:00:00.000000000	90/05/12
select * from member where id like 'tjd%';
-- tjddjs90	박성언	Tv봐요	175.5	19/10/13 00:00:00.000000000	90/05/12
select * from member where id like '%dd%';
-- 1	tjddjs90	박성언	Tv봐요	175.5	19/10/13 00:00:00.000000000	90/05/12

/* or */
select * from member where id = 'tjddjs90' or id = 'kyj1211';
--1	tjddjs90	박성언	Tv봐요	175.5	19/10/13 00:00:00.000000000	90/05/12
--2	kyj1211	김영재	영화		19/10/13 00:00:00.000000000	90/12/11

/* in */
select * from member where id in('tjddjs90', 'kyj1211');
--1	tjddjs90	박성언	Tv봐요	175.5	19/10/13 00:00:00.000000000	90/05/12
--2	kyj1211	김영재	영화		19/10/13 00:00:00.000000000	90/12/11

/* and */
select * from member 
where birth >= '1990-05-12' and birth <= '1990-12-11';
--1	tjddjs90	박성언	Tv봐요	175.5	19/10/13 00:00:00.000000000	90/05/12
--2	kyj1211	김영재	영화		19/10/13 00:00:00.000000000	90/12/11

/* between */
select name from member
where birth between '1990-05-12' and '1990-12-11';
--박성언
--김영재

select name from member
where birth between '1990-12-11' and '1990-05-12';
-- 큰범주가 앞으로 가서 기준을 잡게 되면 출력 되지 않습니다.

/* in null */
update member set height = null where id = 'kyj1211';
update member set height = 159.9 where id = 'kyj1211';

select name, height from member
where height is null;
--홍길동	
--크리스마스	
--식목일	

/* in not null */
select name, height from member
where height is not null;
--박성언	175.5
--김영재	159.9
--호이호이	1

/* sort(정렬) */
select name from member order by height; -- 올림차순 (작은수 => 큰수)
--호이호이
--김영재
--박성언
--식목일
--홍길동
--크리스마스

select name from member order by height desc; -- 내림차순 (큰수 => 작은수)
--식목일
--홍길동
--크리스마스
--박성언
--김영재
--호이호이