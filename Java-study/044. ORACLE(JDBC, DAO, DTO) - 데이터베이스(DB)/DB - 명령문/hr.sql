-- 실행 단축키 => ctrl + enter

insert into student -- 삽입 명령문
(id, name, no, height, regdate, birth, hobby) -- 삽입 할 column 목록 
-- ex) 만약 name 이 필요없으면 name만 빼주면 됩니다.
values -- 값을 입력하겠다는 명령문
('aaa', '한가인', 10, 182.7, '2015-05-12 13:22:54', '2020-7-13', 'Tv시청');
-- TIMESTAMP 는 DATE 와 차이점으로 시간까지 작성 가능 합니다.

/*
*/

insert into student
(id, name, no, height,  birth)  -- 부분적 삽입 가능
values
('bbb','두가인',17,'173.56','1996-7-13');

/* 다중 삽입 */
insert all
into student (id, name, no, birth) values ('ccc','삼가인',9,'1993-1-2')
into student (id, name, no, birth) values ('ddd','사가인',12,'1989-06-08')
into student (id, name, no, birth) values ('eee','오가인',21,'2001-07-23')
into student (id, name, no, birth) values ('fff','육가인',3,'1997-11-11')
select * from dual;  -- 다중 삽입 하겟다는 명령문

/*업데이트*/ -- 다중 업데이트 가능
update student set regdate ='2020-04-29', hobby = '코딩';

/* 조건적 업데이트 */
update student set hobby = '하아~' where id = 'aaa';  -- 같다를 표시할때 등호 == 가 아닌 = 로 사용 
update student set hobby = '잠자기' where no >= 10 and no <=20;
update student set hobby = '영화' where no < 10;

-- 주석
/* 
    주석 구간
            가입일
    80년대생 - 2019/05/08
    90 ~ 95   2019/10/13
    96 ~ 20   2020/03/02
*/

update student set regdate = '2019/05/08' where birth >= '1980-01-01' and birth < '1990-1-1';
update student set regdate = '2019/10/13' where birth >= '1990-01-01' and birth < '1996-01-01';
update student set regdate = '2020/03/02' where birth >= '1996-01-01' and birth < '2021-01-01';


/* 삭제 */
delete from student where id = 'ccc'; 


/* search(검색) */ 
select id,name, birth from student where id = 'ddd';
select id,name, birth from student where id != 'ddd';
select * from student where id != 'ddd';

/* 각 항목별 업데이트 */
update student set name = '호이호이', no = 1, height = 1.0, regdate = '2015-05-05', birth = '1988-08-08', hobby = '파리리'
where id = 'eee';

update member set name = '', no = 1, height = 1.0, regdate = '', birth = '', hobby = ''
where id = '';