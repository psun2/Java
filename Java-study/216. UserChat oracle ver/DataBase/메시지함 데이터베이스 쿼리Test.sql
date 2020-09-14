-- query test
SELECT * FROM CHAT WHERE chatID IN (SELECT MAX(chatID) FROM CHAT WHERE fromID = 'admin' OR toID = 'admin' GROUP BY fromID, toID);
--28	운영자	admin	sdaasd	20/09/08	20/09/08 15:09:17.000000000	1
--35	admin	운영자	ㅁㄴㅇㅁㄴㅇㅁㄴㅇ	20/09/09	20/09/09 14:51:28.000000000	0

SELECT * FROM CHAT WHERE chatID IN (SELECT MAX(chatID) FROM CHAT WHERE fromID = 'admin' OR toID = 'admin');
--35	admin	운영자	ㅁㄴㅇㅁㄴㅇㅁㄴㅇ	20/09/09	20/09/09 14:51:28.000000000	0

SELECT * FROM CHAT WHERE chatID IN (SELECT MAX(chatID) FROM CHAT WHERE fromID = '운영자' OR toID = '운영자');
--35	admin	운영자	ㅁㄴㅇㅁㄴㅇㅁㄴㅇ	20/09/09	20/09/09 14:51:28.000000000	0


-- IN
SELECT *
FROM TABLE 
WHERE COLUMN  = 'A' 
OR COLUMN = 'B';

SELECT *
FROM TABLE 
WHERE COLUMN IN ('A' , 'B');

-- NOT IN
SELECT *
FROM TABLE 
WHERE COLUMN  <> 'A' 
AND COLUMN <> 'B';

SELECT *
FROM TABLE 
WHERE COLUMN NOT IN ('A' , 'B');

--위의 쿼리를 보면
--IN문은 'A'와 'B' 가 포함되는 데이터만 추출되고,
--NOT IN 문은 'A'와 'B' 가 포함되지 않는 데이터만 추출이 됩니다.
--IN의 경우 OR가 NOT IN의 경우 AND 조건이 걸린다는 차이가 있죠!

