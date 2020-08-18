/*
    exam 테이블을 생성하세요
    이름, 국어, 영어, 수학
    
    생성된 테이블을 이용하여 총점, 평균을 출력하세요
*/

insert all
into exam (name, kor, eng, mat) values ('한가인', 97,97,97)
into exam (name, kor, eng, mat) values ('두가인', 72,37,74)
into exam (name, kor, eng, mat) values ('삼가인', 17,27,77)
into exam (name, kor, eng, mat) values ('사가인', 74,47,77)
into exam (name, kor, eng, mat) values ('오가인', 17,65,43)
select * from dual;

delete from exam; -- 잘못 추가하여 전체 데이타 삭제

select exam.*, kor + eng + mat as tot, (kor + eng + mat) / 3 as avg from exam;
--한가인	97	97	97	291	97
--두가인	72	37	74	183	61
--삼가인	17	27	77	121	40.33333333333333333333333333333333333333
--사가인	74	47	77	198	66
--오가인	17	65	43	125	41.66666666666666666666666666666666666667