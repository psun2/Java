/* to_date */
select 
--'2020-07-15'+100, --이건 안됨 
to_date('2020-07-15', 'yyyy-mm-dd')+100, --100일 뒤 -- 20/10/23
to_date('2020-07-15 13:34:21', 'yyyy-mm-dd hh24:mi:ss')+100 -- 20/10/23
from dual;