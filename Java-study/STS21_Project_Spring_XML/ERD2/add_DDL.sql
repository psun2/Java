
/* Drop Tables */

DROP TABLE schedule CASCADE CONSTRAINTS;



/* Create Tables */

--현준 추가 

CREATE SEQUENCE s_sid_seq
START WITH 1
INCREMENT BY 1;


CREATE TABLE schedule
(
	s_sid number NOT NULL,
	subject varchar2(20) NOT NULL,
	startdate varchar2(20) NOT NULL,
	enddate varchar2(20) NOT NULL,
	memo varchar2(100),
	PRIMARY KEY (s_sid)
);


select * from schedule;

insert into(s_sid, subject, startdate, enddate, memo)
values(s_sid_seq.nextval, 'todo', '2020-12-9', '2020-12-9', '싄나');




