/* concat(||) */

-- concat �� �̿��� ���ڿ� ���ϱ�
SELECT CONCAT(COUNTRY_ID, COUNTRY_NAME) FROM COUNTRIES;

-- concat �� �̿��� ���ڿ� ���ϱ� (���� ����)
SELECT CONCAT(CONCAT(COUNTRY_ID, ' '), COUNTRY_NAME) FROM COUNTRIES;


/* || */
-- || �� �̿��� ���ڿ� ���ϱ�
SELECT COUNTRY_ID||COUNTRY_NAME FROM COUNTRIES;

-- || �� �̿��� ���ڿ� ���ϱ� (���� ����)
SELECT COUNTRY_ID||' '||COUNTRY_NAME FROM COUNTRIES;