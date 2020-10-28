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

-- 실행할때마다 게시판 글 X 2
INSERT INTO test_write (wr_uid, wr_subject, wr_content, wr_name, wr_viewcnt)
SELECT test_write_seq.nextval, wr_subject, wr_content, wr_name, wr_viewcnt FROM test_write;

-- 전체 글의 개수
SELECT COUNT(*) FROM test_write;

-- SELECT 결과물중 맨 위의 5개만 출력해보고 싶으면 어케 해야 하나?
-- SELECT 결과물중 n 번째 부터 m 개를 출력하고 싶다면?
--   ex) 게시판.. 페이징

-- 	DBMS 마다 구현 방법 다름
--	MYSQL : LIMIT
-- 	MS SQL server : TOP
-- 	ORACLE : ROWNUM 

-- ROWNUM
SELECT ROWNUM, wr_name FROM test_write;

-- 1 페이지 분량
SELECT ROWNUM, wr_name FROM test_write
WHERE ROWNUM >=1 AND ROWNUM < 1 + 5;

-- 2페이지 분량은 ?
SELECT ROWNUM, wr_name FROM test_write
WHERE ROWNUM >=6 AND ROWNUM < 6 + 5;

SELECT * FROM
(SELECT ROWNUM AS RNUM, T.*
FROM (SELECT * FROM test_write ORDER BY wr_uid DESC) T) WHERE RNUM >=6 AND RNUM < 6 + 5;

DELETE FROM test_write 
WHERE ROWID IN (
	SELECT rwid FROM (
		SELECT ROWNUM AS rnum, rwid FROM
			(SELECT ROWID AS rwid FROM test_write)
        ORDER BY rwid
    )
WHERE rnum > 3);
