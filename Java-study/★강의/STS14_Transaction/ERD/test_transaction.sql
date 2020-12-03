
/* Drop Tables */

DROP TABLE test_card CASCADE CONSTRAINTS;
DROP TABLE test_ticket CASCADE CONSTRAINTS;

/* Create Tables */

CREATE TABLE test_card
(
	user_id varchar2(20) NOT NULL,
	buy_amount number DEFAULT 1
);


CREATE TABLE test_ticket
(
	user_id varchar2(20) NOT NULL,
	ticket_count number NOT NULL,
	CONSTRAINT ticket_buy_limit
	CHECK (ticket_count BETWEEN 1 AND 5)
);

SET LINESIZE 120;
SET PAGESIZE 100;
col user_id for a8;
col ticket_count for 99;
col buy_amount for 99;

SELECT * FROM test_card;
SELECT * FROM test_ticket;

-- ticket2장을  카드로 결제하는 경우
INSERT INTO test_card VALUES('aaa', 2);
INSERT INTO test_ticket VALUES('aaa', 2);
-- ↑ 결과 확인해보자

-- ticket5장을  카드로 결제하는 경우
INSERT INTO test_card VALUES('aaa', 5);
INSERT INTO test_ticket VALUES('aaa', 5);

-- ticket6장을  카드로 결제하는 경우
INSERT INTO test_card VALUES('aaa', 6);
INSERT INTO test_ticket VALUES('aaa', 6);

DELETE FROM test_card;
DELETE FROM test_ticket;

