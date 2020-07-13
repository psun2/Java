-- select 문자열 함수

-- 문자열
select 
upper('aBcd Efg'), -- 대문자
lower('aBcd Efg'), -- 소문자
initcap('aBcd Efg'), -- 원본
length('aBcd Efg') -- 길이
from dual;

-- 음수는 뒤에서 부터 찾습니다. 뒤에 나오는 숫자는 2번째 포함 하는 숫자인지 찾습니다.
select 
instr('korea koo asdko qwekrk fewf kk', 'k') as in1, -- 1
instr('korea koo asdko qwekrk fewf kk', 'k', 4) as in2, -- 7
instr('korea koo asdko qwekrk fewf kk', 'k', 4,2) as in3, -- 14
instr('korea koo asdko qwekrk fewf kk', 'k', -1) as in4, -- 30
instr('korea koo asdko qwekrk fewf kk', 'k', -4) as in5, -- 22
instr('korea koo asdko qwekrk fewf kk', 'k', -4, 2) as in6 -- 20
from dual;

-- subString 과 같이 문자열을 자릅니다.
select
substr('korea koo asdko qwekrk fewf kk', 3) as in1, -- rea koo asdko qwekrk fewf kk
substr('korea koo asdko qwekrk fewf kk', 3,5) as in2, -- rea k
substr('korea koo asdko qwekrk fewf kk', -9) as in3, -- k fewf kk
substr('korea koo asdko qwekrk fewf kk', -9, 5) as in4 -- k few
from dual;