-- select ���ڿ� �Լ�

-- ���ڿ�
select 
upper('aBcd Efg'), -- �빮��
lower('aBcd Efg'), -- �ҹ���
initcap('aBcd Efg'), -- ����
length('aBcd Efg') -- ����
from dual;

-- ������ �ڿ��� ���� ã���ϴ�. �ڿ� ������ ���ڴ� 2��° ���� �ϴ� �������� ã���ϴ�.
select 
instr('korea koo asdko qwekrk fewf kk', 'k') as in1, -- 1
instr('korea koo asdko qwekrk fewf kk', 'k', 4) as in2, -- 7
instr('korea koo asdko qwekrk fewf kk', 'k', 4,2) as in3, -- 14
instr('korea koo asdko qwekrk fewf kk', 'k', -1) as in4, -- 30
instr('korea koo asdko qwekrk fewf kk', 'k', -4) as in5, -- 22
instr('korea koo asdko qwekrk fewf kk', 'k', -4, 2) as in6 -- 20
from dual;

-- subString �� ���� ���ڿ��� �ڸ��ϴ�.
select
substr('korea koo asdko qwekrk fewf kk', 3) as in1, -- rea koo asdko qwekrk fewf kk
substr('korea koo asdko qwekrk fewf kk', 3,5) as in2, -- rea k
substr('korea koo asdko qwekrk fewf kk', -9) as in3, -- k fewf kk
substr('korea koo asdko qwekrk fewf kk', -9, 5) as in4 -- k few
from dual;