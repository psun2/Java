-- 실행 단축키 : ctrl + enter
-- 자동 정렬 : ctrl + F7

-- insert : 삽입
insert 
into member (no, id, name, hobby, height, regdate, birth)
values (1, 'tjddjs90', '박성언', '코딩', 175.5, '1990-05-12 12:00:00', '1990-05-12');

-- insert : 부분 삽입 가능
insert
into member (no, id, name, birth) values (2, 'kyj1211', '김영재', '1990-12-11');

-- insert : 다중 삽입 가능
insert all
into member (no, id, name, birth) values(3, 'dkfdkdy', '알아요', '2020-07-13')
into member (no, id, name, birth) values(4, 'rmftpdy', '글쎄요', '1980-02-24')
into member (no, id, name, birth) values(5, 'ghdrlfrhd', '홍길동', '1150-04-25')
into member (no, id, name, birth) values(6, 'zmflrmaktm', '크리스마스', '1998-12-25')
into member (no, id, name, birth) values(7, 'tlrahrdlf', '식목일', '2001-04-05')
select * from dual;