-- select ���ڿ� �Լ� Exam

-- Ȯ���ڸ��� ����ϼ��� : aaaa.bbbb.ccc.dddddd.ee.ddddd.xx.a.txt - teacher
select
instr('aaaa.bbbb.ccc.dddddd.ee.ddddd.xx.a.txt', '.', -1)
from dual;
-- 35

select
substr('aaaa.bbbb.ccc.dddddd.ee.ddddd.xx.a.txt', instr('aaaa.bbbb.ccc.dddddd.ee.ddddd.xx.a.txt', '.', -1) + 1)
from dual;
-- txt

