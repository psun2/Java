-- ▶rollback 
insert all
into department (id, name) values('com', '컴퓨터공학')
into department (id, name) values('eye_design', '시각디자인')
into department (id, name) values('phillos', '철학')
into department (id, name) values('chemisrty', '화공')
select * from dual;
select * from department;
--com	컴퓨터공학
--eye_design	시각디자인
--phillos	철학
--chemisrty	화공

update department set id = 'tb' where name = '화공';
select * from department;
--com	컴퓨터공학
--eye_design	시각디자인
--phillos	철학
--tb	화공

rollback;
select * from department;
-- 모두 지워 짐

-- ▶ commit & rollback : 커밋까지 롤백이 됩니다.
insert into department (id, name) values('com', '컴퓨터공학');
insert into department (id, name) values('eye_design', '시각디자인');
commit;
insert into department (id, name) values('phillos', '철학');
insert into department (id, name) values('chemisrty', '화공');
update department set id = 'tb' where name = '화공';
select * from department;
--com	컴퓨터공학
--eye_design	시각디자인
--phillos	철학
--tb	화공

rollback;
select * from department;
--com	컴퓨터공학
--eye_design	시각디자인

delete department; -- 다음 예제를 위한 삭제

-- ▶ savepoint & rollback to savepoint : savepoint 까지 롤백이 됩니다.
insert into department (id, name) values('com', '컴퓨터공학');
insert into department (id, name) values('eye_design', '시각디자인');
insert into department (id, name) values('phillos', '철학');
insert into department (id, name) values('chemisrty', '화공');
savepoint a;
update department set id = 'tb' where name = '화공';
select * from department;
--com	컴퓨터공학
--eye_design	시각디자인
--phillos	철학
--tb	화공

rollback to a;
select * from department;
--com	컴퓨터공학
--eye_design	시각디자인
--phillos	철학
--chemisrty	화공