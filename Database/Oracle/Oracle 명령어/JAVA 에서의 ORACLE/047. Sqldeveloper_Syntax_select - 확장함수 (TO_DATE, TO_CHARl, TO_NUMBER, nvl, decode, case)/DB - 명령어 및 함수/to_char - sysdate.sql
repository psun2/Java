/* to_char(sysdate 'format') */
select
sysdate,
to_char(sysdate, 'yyyy'), -- 2020
to_char(sysdate, 'yy'), -- 20
to_char(sysdate, 'mon'), -- 7월 
to_char(sysdate, 'mm'), -- 07
to_char(sysdate, 'dd'), -- 16
to_char(sysdate, 'day'), -- 목요일
to_char(sysdate, 'dy'), -- 목
to_char(sysdate, 'hh'), -- 12
to_char(sysdate, 'hh12'), -- 12
to_char(sysdate, 'hh24'), -- 00
to_char(sysdate, 'mi'), -- 29
to_char(sysdate, 'ss') -- 33
from dual;