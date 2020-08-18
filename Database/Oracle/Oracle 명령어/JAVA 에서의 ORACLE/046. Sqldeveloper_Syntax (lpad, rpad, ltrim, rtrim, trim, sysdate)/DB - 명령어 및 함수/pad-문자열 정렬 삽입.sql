/* pad */

select lpad('선생님은 귀여워요', 30, '@') from dual; -- @@@@@@@@@@@@@선생님은 귀여워요
-- @ => 13 byte
-- 공백 => 1 byte
-- 한글 8자 => 16 byte
-- 총 30 byte

select length('@@@@@@@@@@@@@선생님은 귀여워요')from dual; -- 22

-- 결론 : 글자 길이와 byte는 다릅니다.

select lpad('선생님은 귀여워요',10,'?') from dual; --  '선생님은 '
-- byte가 모자르면 글자를 잘라서 표시합니다.

select rpad('선생님은 귀여워요', 30 ,'?') as rpad from dual; -- 선생님은 귀여워요?????????????
select rpad('선생님은 귀여워요', 10 ,'?') as rpad짤림 from dual; -- '선생님은  '
