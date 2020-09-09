-- query test
SELECT * FROM CHAT WHERE chatID IN (SELECT MAX(chatID) FROM CHAT WHERE fromID = 'admin' OR toID = 'admin' GROUP BY fromID, toID);
--28	���	admin	sdaasd	20/09/08	20/09/08 15:09:17.000000000	1
--35	admin	���	������������������	20/09/09	20/09/09 14:51:28.000000000	0

SELECT * FROM CHAT WHERE chatID IN (SELECT MAX(chatID) FROM CHAT WHERE fromID = 'admin' OR toID = 'admin');
--35	admin	���	������������������	20/09/09	20/09/09 14:51:28.000000000	0

SELECT * FROM CHAT WHERE chatID IN (SELECT MAX(chatID) FROM CHAT WHERE fromID = '���' OR toID = '���');
--35	admin	���	������������������	20/09/09	20/09/09 14:51:28.000000000	0


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

--���� ������ ����
--IN���� 'A'�� 'B' �� ���ԵǴ� �����͸� ����ǰ�,
--NOT IN ���� 'A'�� 'B' �� ���Ե��� �ʴ� �����͸� ������ �˴ϴ�.
--IN�� ��� OR�� NOT IN�� ��� AND ������ �ɸ��ٴ� ���̰� ����!

