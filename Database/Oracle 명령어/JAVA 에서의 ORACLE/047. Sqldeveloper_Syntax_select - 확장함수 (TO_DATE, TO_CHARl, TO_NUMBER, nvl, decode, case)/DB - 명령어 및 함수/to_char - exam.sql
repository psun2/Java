---2003Ò´ 10êÅ 23ìí (¿ù) 14:23:15

select
to_char(sysdate, 'yyyy') ||'Ò´' || 
to_char(sysdate, ' mm') || 'êÅ' ||
to_char(sysdate, ' dd') ||'ìí' ||
to_char(sysdate, ' (dy) hh24:mi:ss') as »ı³â¿ùÀÏ 
from dual;
--2020Ò´ 07êÅ 16ìí (¸ñ) 00:26:09