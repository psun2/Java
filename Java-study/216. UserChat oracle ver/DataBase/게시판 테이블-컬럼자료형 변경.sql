-- NVARCHAR2 는 byte 가 아닌 글자수.... 컬럼 타입 변경
ALTER TABLE BOARD MODIFY boardTitle NVARCHAR2(50);
commit;

-- 복습(연습) - 컬럼 이름 변경
ALTER TABLE emp RENAME COLUMN editid TO edit_id