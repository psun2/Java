/* join */

insert all
into exam2(sid, hakgi, title, no, kor, eng, mat)
values ('한가인',1,'중간',1,71,17,100)
into exam2(sid, hakgi, title, no, kor, eng, mat)
values ('두가인',2,'중간',2,72,27,43)
into exam2(sid, hakgi, title, no, kor, eng, mat)
values ('삼가인',1,'기말',3,73,37,79)
into exam2(sid, hakgi, title, no, kor, eng, mat)
values ('사가인',1,'기말',4,74,47,78)
into exam2(sid, hakgi, title, no, kor, eng, mat)
values ('오가인',2,'중간',5,75,57,87)
into exam2(sid, hakgi, title, no, kor, eng, mat)
values ('육가인',2,'중간',6,76,67,47)
into exam2(sid, hakgi, title, no, kor, eng, mat)
values ('칠가인',1,'기말',7,77,77,17)
into exam2(sid, hakgi, title, no, kor, eng, mat)
values ('팔가인',1,'중간',8,78,87,73)
into exam2(sid, hakgi, title, no, kor, eng, mat)
values ('구가인',2,'기말',9,79,97,77)
into exam2(sid, hakgi, title, no, kor, eng, mat)
values ('십가인',1,'기말',10,70,7,57)
select * from dual;

select * from exam2, member;  -- 전부 곱하기가 되어 들어가고 있음

select * from exam2, member
where sid = id; -- pid 와 id 값이 같은 요소들만 출력
--한가인	1	중간	1	71	17	100	5	한가인	홍길동	영화		20/02/06 13:00:00.000000000	50/04/25
--한가인	1	중간	1	71	17	100	1	한가인	박성언	Tv봐요	175.5	19/10/13 00:00:00.000000000	90/05/12
--삼가인	1	기말	3	73	37	79	7	삼가인	식목일	잠자기		20/03/02 00:00:00.000000000	01/04/05
--육가인	2	중간	6	76	67	47	1	육가인	호이호이	파리리	1	15/05/05 00:00:00.000000000	88/08/08
--칠가인	1	기말	7	77	77	17	2	칠가인	김영재	영화	159.9	19/10/13 00:00:00.000000000	90/12/11
--십가인	1	기말	10	70	7	57	6	십가인	크리스마스	영화		20/03/02 00:00:00.000000000	98/12/25

select exam2.*, name, birth from exam2, member
where sid = id;
--한가인	1	중간	1	71	17	100	홍길동	50/04/25
--한가인	1	중간	1	71	17	100	박성언	90/05/12
--삼가인	1	기말	3	73	37	79	식목일	01/04/05
--육가인	2	중간	6	76	67	47	호이호이	88/08/08
--칠가인	1	기말	7	77	77	17	김영재	90/12/11
--십가인	1	기말	10	70	7	57	크리스마스	98/12/25

select exam.*, name, birth, no from exam, member
where pid = id;  -- error - 누구의 no 인지 햇깔림

select exam2.*, name, birth, member.no from exam2, member
where sid = id;  -- error - 누구의 no 인지 햇깔림
--한가인	1	중간	1	71	17	100	홍길동	50/04/25	5
--한가인	1	중간	1	71	17	100	박성언	90/05/12	1
--삼가인	1	기말	3	73	37	79	식목일	01/04/05	7
--육가인	2	중간	6	76	67	47	호이호이	88/08/08	1
--칠가인	1	기말	7	77	77	17	김영재	90/12/11	2
--십가인	1	기말	10	70	7	57	크리스마스	98/12/25	6

/* outter join */
select exam2.*, name, birth, member.no from exam2, member
where sid = id(+); -- + 의 의미 : sid는 전부 있어야 하고 id는 sid 와 일치 하는것만 출력

--한가인	1	중간	1	71	17	100	박성언	90/05/12	1
--칠가인	1	기말	7	77	77	17	김영재	90/12/11	2
--육가인	2	중간	6	76	67	47	호이호이	88/08/08	1
--한가인	1	중간	1	71	17	100	홍길동	50/04/25	5
--십가인	1	기말	10	70	7	57	크리스마스	98/12/25	6
--삼가인	1	기말	3	73	37	79	식목일	01/04/05	7
--팔가인	1	중간	8	78	87	73			
--두가인	2	중간	2	72	27	43			
--구가인	2	기말	9	79	97	77			
--사가인	1	기말	4	74	47	78			
--오가인	2	중간	5	75	57	87			