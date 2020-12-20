
/* Drop Triggers */

DROP TRIGGER TRI_mybatis_data1;



/* Drop Tables */

DROP TABLE mybatis CASCADE CONSTRAINTS;



/* Drop Sequences */

DROP SEQUENCE SEQ_mybatis_data1;




/* Create Sequences */

CREATE SEQUENCE SEQ_mybatis_data1 INCREMENT BY 1 START WITH 1;



/* Create Tables */

CREATE TABLE mybatis
(
	data1 number NOT NULL,
	data2 varchar2(30) NOT NULL,
	PRIMARY KEY (data1)
);



/* Create Triggers */

CREATE OR REPLACE TRIGGER TRI_mybatis_data1 BEFORE INSERT ON mybatis
FOR EACH ROW
BEGIN
	SELECT SEQ_mybatis_data1.nextval
	INTO :new.data1
	FROM dual;
END;

/


INSERT INTO mybatis(data2) VALUES ('데이타1 입니다.');
INSERT INTO mybatis(data2) VALUES ('데이타2 입니다.');
INSERT INTO mybatis(data2) VALUES ('데이타3 입니다.');
INSERT INTO mybatis(data2) VALUES ('데이타4 입니다.');
INSERT INTO mybatis(data2) VALUES ('데이타5 입니다.');
commit;
SELECT * FROM mybatis;