-- select 문자열 함수 Exam

-- 확자자명을 출력하세요 : aaaa.bbbb.ccc.dddddd.ee.ddddd.xx.a.txt - teacher
select
instr('aaaa.bbbb.ccc.dddddd.ee.ddddd.xx.a.txt', '.', -1)
from dual;
-- 35

select
substr('aaaa.bbbb.ccc.dddddd.ee.ddddd.xx.a.txt', instr('aaaa.bbbb.ccc.dddddd.ee.ddddd.xx.a.txt', '.', -1) + 1)
from dual;
-- txt

