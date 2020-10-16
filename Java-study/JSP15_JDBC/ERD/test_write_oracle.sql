-- 오라클 에선 'uid' 는 키워드 --> 컬러명, 테이블명 사용 금지
/* Drop Tables */

DROP TABLE test_write CASCADE CONSTRAINTS;




/* Create Tables */

CREATE TABLE test_write
(
	wr_uid number NOT NULL,
	wr_subject varchar2(200) NOT NULL,
	wr_content clob,
	wr_name varchar2(40) NOT NULL,
	wr_viewconunt number DEFAULT 0,
	wr_regdate date DEFAULT SYSDATE,
	PRIMARY KEY (wr_uid)
);


/* Drop Tables */
DROP TABLE test_write CASCADE CONSTRAINTS;

/* Create Tables */
CREATE TABLE test_write(
	wr_uid number NOT NULL,  -- 글 고유 아이디
	wr_subject varchar2(200) NOT NULL, -- 게시글제목
	wr_content clob, -- 게시글
	wr_name varchar2(40) NOT NULL, -- 작성자
	wr_viewcnt number DEFAULT 0, -- 조회수
	wr_regdate date DEFAULT SYSDATE, -- 작성일
	PRIMARY KEY (wr_uid)
);

-- 시퀀스 작성
CREATE SEQUENCE test_write_seq;

-- 기본데이터 작성
INSERT INTO TEST_WRITE VALUES
(TEST_WRITE_SEQ.nextval, '첫째글:방가요', '안녕하세요', '김희철', 0, '2017-03-02');
INSERT INTO TEST_WRITE VALUES
(TEST_WRITE_SEQ.nextval, '둘째글:헤헤헤','1111', '김수길', 0, '2017-03-02');
INSERT INTO TEST_WRITE VALUES
(TEST_WRITE_SEQ.nextval, '세째글:힘내세요', '7394', '최진덕' , 0, '2017-08-12');
INSERT INTO TEST_WRITE VALUES
(TEST_WRITE_SEQ.nextval, '네째글: ... ', '9090', '이혜원', 0, '2018-02-09');
INSERT INTO TEST_WRITE VALUES
(TEST_WRITE_SEQ.nextval, '다섯째글: 게시판', '7531', '박수찬', 0, sysdate);

SET LINESIZE 120;
SET PAGESIZE 100;
col wr_uid for 999;
col wr_subject for a20;
col wr_content for a15;
col wr_name for a10;
col wr_viewcnt for 99;
col wr_regdate for a10;

SELECT * FROM test_write ORDER BY wr_uid DESC;


