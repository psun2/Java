-- 주석
/* 
    주석 구간
            가입일
    80년대생 - 2019/05/08
    90 ~ 95   2019/10/13
    96 ~ 20   2020/03/02
*/

-- update where Exam

update member set regdate = '2019/05/08' where birth >= '1980-01-01' and birth < '1990-1-1';
update member set regdate = '2019/10/13' where birth >= '1990-01-01' and birth < '1996-01-01';
update member set regdate = '2020/03/02' where birth >= '1996-01-01' and birth < '2021-01-01';
