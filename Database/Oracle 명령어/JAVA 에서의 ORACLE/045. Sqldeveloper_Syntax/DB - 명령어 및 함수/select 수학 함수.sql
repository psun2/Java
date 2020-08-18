-- select 수학 함수

-- 반올림
select 
round(123.456789, 2),
round(-123.456789, 3),
round(987.456789, 4),
round(-987.456789, 5)
from dual;
-- 123.46	-123.457	987.4568	-987.45679

-- 버림
select 
trunc(123.456789),
trunc(-123.456789),
trunc(987.456789),
trunc(-987.456789)
from dual;
-- 123	-123	987	-987

-- 나머지
select 
mod(15,7),
mod(4,2)
from dual;
-- 1	0

-- 절대 값
select
ABS(-15),
ABS(15),
ABS(-10010),
ABS(10010)
from dual;
-- 15	15	10010	10010