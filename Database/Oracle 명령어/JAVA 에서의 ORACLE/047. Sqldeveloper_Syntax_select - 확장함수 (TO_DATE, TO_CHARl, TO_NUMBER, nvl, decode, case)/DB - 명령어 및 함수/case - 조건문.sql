/* case */

select kor,
case
when kor >= 90 then 'A'
when kor >= 80 then 'B'
when kor >= 70 then 'C'
when kor >= 60 then 'D'
when kor >= 50 then 'E'
else 'F'
end
as kor_gread
from exam;
