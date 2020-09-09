-- query test
SELECT * FROM CHAT WHERE chatID IN (SELECT MAX(chatID) FROM CHAT WHERE fromID = 'admin' OR toID = 'admin' GROUP BY fromID, toID);
--28	錘慎切	admin	sdaasd	20/09/08	20/09/08 15:09:17.000000000	1
--35	admin	錘慎切	けいしけいしけいし	20/09/09	20/09/09 14:51:28.000000000	0

SELECT * FROM CHAT WHERE chatID IN (SELECT MAX(chatID) FROM CHAT WHERE fromID = 'admin' OR toID = 'admin');
--35	admin	錘慎切	けいしけいしけいし	20/09/09	20/09/09 14:51:28.000000000	0

SELECT * FROM CHAT WHERE chatID IN (SELECT MAX(chatID) FROM CHAT WHERE fromID = '錘慎切' OR toID = '錘慎切');
--35	admin	錘慎切	けいしけいしけいし	20/09/09	20/09/09 14:51:28.000000000	0


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

--是税 汀軒研 左檎
--IN庚精 'A'人 'B' 亜 匂敗鞠澗 汽戚斗幻 蓄窒鞠壱,
--NOT IN 庚精 'A'人 'B' 亜 匂敗鞠走 省澗 汽戚斗幻 蓄窒戚 桔艦陥.
--IN税 井酔 OR亜 NOT IN税 井酔 AND 繕闇戚 杏鍵陥澗 託戚亜 赤倉!

