/* querry - exam */

-- ������Ƽ ��¥�� ���ϰ� ������Ƽ�� ����� 3���� ����ϼ���
-- ������Ƽ�� ��, �Ͽ����� ���� �Ѵ�.

select * from member;
select rownum �ο��, member.* from member;

-- 4. rownum
select * from (select rownum rnum, t3.* from (select t2.*, case when to_char(nb,'dy') = '��' then nb - 1 when to_char(nb,'dy') = '��' then nb - 2 else nb end as pd from(select t1.*,case
when sysdate > nowBirth then add_months(nowBirth,12)else nowBirth end as nb from (select member.*, to_date(to_char(sysdate,'yyyy')||to_char(birth,'/mm/dd')) 
as nowBirth from member) t1) t2 order by pd) t3) t4
where rnum <=3;

-- 3. ���� 
select t2.*, 
case 
when to_char(nb,'dy') = '��' then nb - 1 
when to_char(nb,'dy') = '��' then nb - 2 
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


-- 2. ���� ������ �����ٸ� ���� ������ ǥ��
select t1.*,
case
when sysdate > nowBirth then add_months(nowBirth,12)
else nowBirth 
end as nb 
from 
(select member.*, to_date(to_char(sysdate,'yyyy')||to_char(birth,'/mm/dd')) as nowBirth from member) 
t1;

-- 1. ���� ����
select member.*, to_date(to_char(sysdate,'yyyy')||to_char(birth,'/mm/dd')) as nowbirth from member;


-------------------------------------------------------------------------------------------------------

-- 1. ������ ���� ���� ���Ϸ� ������Ʈ
select member.*, to_date(to_char(sysdate, 'yy')|| to_char(birth, '/MM/dd')) as nowBirth from member;

-- 2. ���� ������ �����ٸ� ������Ϸ� ����
select t1.*, 
case
when sysdate > nowBirth then add_months(nowBirth, 12)
else
nowBirth
end as updateBirth
from (select member.*, to_date(to_char(sysdate, 'yy')|| to_char(birth, '/MM/dd')) as nowBirth from member) t1;

-- 3. �� ���� �϶� �ݿ��Ϸ�, �Ͽ����϶� �ݿ��Ϸ� ����
select t2.*,
case
when to_char(updateBirth, 'dy') = '��' then updateBirth -1
when to_char(updateBirth, 'dy') = '��' then updateBirth -2
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

-- 4. rownum �޾��ֱ�
select rownum ����, t3.*
from (select t2.*,
case
when to_char(updateBirth, 'dy') = '��' then updateBirth -1
when to_char(updateBirth, 'dy') = '��' then updateBirth -2
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

-- 5. 3�� �̱�
select * from (select rownum ����, t3.*
from (select t2.*,
case
when to_char(updateBirth, 'dy') = '��' then updateBirth -1
when to_char(updateBirth, 'dy') = '��' then updateBirth -2
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
where ���� <= 3;

-- tip : ���� ���� ���� �ϴ� ū ���ֿ��� ���� �ٿ� �����ϴ�.