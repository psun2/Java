---2003Ҵ 10�� 23�� (��) 14:23:15

select
to_char(sysdate, 'yyyy') ||'Ҵ' || 
to_char(sysdate, ' mm') || '��' ||
to_char(sysdate, ' dd') ||'��' ||
to_char(sysdate, ' (dy) hh24:mi:ss') as ������� 
from dual;
--2020Ҵ 07�� 16�� (��) 00:26:09