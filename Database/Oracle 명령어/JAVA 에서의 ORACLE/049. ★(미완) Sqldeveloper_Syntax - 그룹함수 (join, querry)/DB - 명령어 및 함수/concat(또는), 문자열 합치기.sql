/* concat(||) */

-- concat 을 이용한 문자열 더하기
SELECT CONCAT(COUNTRY_ID, COUNTRY_NAME) FROM COUNTRIES;

-- concat 을 이용한 문자열 더하기 (공백 삽입)
SELECT CONCAT(CONCAT(COUNTRY_ID, ' '), COUNTRY_NAME) FROM COUNTRIES;


/* || */
-- || 을 이용한 문자열 더하기
SELECT COUNTRY_ID||COUNTRY_NAME FROM COUNTRIES;

-- || 을 이용한 문자열 더하기 (공백 삽입)
SELECT COUNTRY_ID||' '||COUNTRY_NAME FROM COUNTRIES;