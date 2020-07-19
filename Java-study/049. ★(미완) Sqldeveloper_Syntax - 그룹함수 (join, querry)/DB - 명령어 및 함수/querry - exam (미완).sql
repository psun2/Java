/* querry - exam */

-- 생일파티 날짜를 구하고 생일파티가 가까운 3인을 출력하세요
-- 생일파티는 토, 일요일은 배재 한다.

select * from member;
select rownum 로우넘, member.* from member;

-- 4. rownum
select * from (select rownum rnum, t3.* from (select t2.*, case when to_char(nb,'dy') = '토' then nb - 1 when to_char(nb,'dy') = '일' then nb - 2 else nb end as pd from(select t1.*,case
when sysdate > nowBirth then add_months(nowBirth,12)else nowBirth end as nb from (select member.*, to_date(to_char(sysdate,'yyyy')||to_char(birth,'/mm/dd')) 
as nowBirth from member) t1) t2 order by pd) t3) t4
where rnum <=3;

-- 3. 요일 
select t2.*, 
case 
when to_char(nb,'dy') = '토' then nb - 1 
when to_char(nb,'dy') = '일' then nb - 2 
else nb 
end as pd 
from
(select t1.*,
case
when sysdate > nowBirth then add_months(nowBirth,12)
else nowBirth 
end as nb 
from 
(select member.*, to_date(to_char(sysdate,'yyyy')||to_char(birth,'/mm/dd')) as nowBirth from member) 
t1) 
t2 order by pd;


-- 2. 올해 생일이 지났다면 내년 생일을 표기
select t1.*,
case
when sysdate > nowBirth then add_months(nowBirth,12)
else nowBirth 
end as nb 
from 
(select member.*, to_date(to_char(sysdate,'yyyy')||to_char(birth,'/mm/dd')) as nowBirth from member) 
t1;

-- 1. 올해 생일
select member.*, to_date(to_char(sysdate,'yyyy')||to_char(birth,'/mm/dd')) as nowbirth from member;


-------------------------------------------------------------------------------------------------------

-- 1. 생일을 먼저 올해 생일로 업데이트
select member.*, to_date(to_char(sysdate, 'yy')|| to_char(birth, '/MM/dd')) as nowBirth from member;

-- 2. 올해 생일이 지났다면 내년생일로 셋팅
select t1.*, 
case
when sysdate > nowBirth then add_months(nowBirth, 12)
else
nowBirth
end as updateBirth
from (select member.*, to_date(to_char(sysdate, 'yy')|| to_char(birth, '/MM/dd')) as nowBirth from member) t1;

-- 3. 토 요일 일때 금요일로, 일요일일때 금요일로 셋팅
select t2.*,
case
when to_char(updateBirth, 'dy') = '토' then updateBirth -1
when to_char(updateBirth, 'dy') = '일' then updateBirth -2
else
updateBirth
end as PartyDay
from (select t1.*, 
case
when sysdate > nowBirth then add_months(nowBirth, 12)
else
nowBirth
end as updateBirth
from (select member.*, to_date(to_char(sysdate, 'yy')|| to_char(birth, '/MM/dd')) as nowBirth from member) t1) t2
order by PartyDay;

-- 4. rownum 달아주기
select rownum 순번, t3.*
from (select t2.*,
case
when to_char(updateBirth, 'dy') = '토' then updateBirth -1
when to_char(updateBirth, 'dy') = '일' then updateBirth -2
else
updateBirth
end as PartyDay
from (select t1.*, 
case
when sysdate > nowBirth then add_months(nowBirth, 12)
else
nowBirth
end as updateBirth
from (select member.*, to_date(to_char(sysdate, 'yy')|| to_char(birth, '/MM/dd')) as nowBirth from member) t1) t2
order by PartyDay) t3; 

-- 5. 3명만 뽑기
select * from (select rownum 순번, t3.*
from (select t2.*,
case
when to_char(updateBirth, 'dy') = '토' then updateBirth -1
when to_char(updateBirth, 'dy') = '일' then updateBirth -2
else
updateBirth
end as PartyDay
from (select t1.*, 
case
when sysdate > nowBirth then add_months(nowBirth, 12)
else
nowBirth
end as updateBirth
from (select member.*, to_date(to_char(sysdate, 'yy')|| to_char(birth, '/MM/dd')) as nowBirth from member) t1) t2
order by PartyDay) t3)
where 순번 <= 3;

-- tip : 가장 먼저 봐야 하는 큰 범주에서 점점 줄여 나갑니다.