-- 답변 들여쓰기 유지를 위한 데이터베이스 개편

-- 기존 자료 삭제
DELETE FROM BOARD;

-- 기존 테이블 삭제
DROP TABLE BOARD;

-- 기존 시퀀스 삭제
DROP SEQUENCE board_boardID;
DROP SEQUENCE board_boardGroup;

-- 새로운 테이블 생성
CREATE TABLE BOARD (
userID NVARCHAR2(20),
boardID INT CONSTRAINT boardID_pk PRIMARY KEY,
boardTitle NVARCHAR2(50),
boardContetn VARCHAR2(2048),
boardDate TIMESTAMP,
boardHit INT,
boardFile VARCHAR2(100),
boardRealFile VARCHAR2(100),
boardGroup INT,
boardSequence INT,
boardLevel INT,
boardAvailable INT
);

-- 새로운 시퀀스 생성
CREATE SEQUENCE board_boardID
START WITH 1
INCREMENT BY 1;

CREATE SEQUENCE board_boardGroup
START WITH 1
INCREMENT BY 1;

-- 생성시 컬럼명 오류 수정
ALTER TABLE BOARD RENAME COLUMN boardContetn to boardContent;

-- 페이지 네이션을 위한 예시데이터 삽입
INSERT ALL
INTO BOARD -- boardID 가 Primary Key 이므로 all 이 안됩니다.
(userID, boardID, boardTitle, boardContent,boardDate, boardHit, boardFile, boardRealFile, boardGroup, boardSequence, boardLevel, boardAvailable)
VALUES('운영자', board_boardID.NEXTVAL, '예시데이터', '예시데이터', sysdate, 0, '', '', board_boardGroup.NEXTVAL, 0, 0, 1)
SELECT * FROM DUAL;

INSERT
INTO BOARD 
(userID, boardID, boardTitle, boardContent,boardDate, boardHit, boardFile, boardRealFile, boardGroup, boardSequence, boardLevel, boardAvailable)
VALUES('운영자', board_boardID.NEXTVAL, '예시데이터', '예시데이터', sysdate, 0, '', '', board_boardGroup.NEXTVAL, 0, 0, 1);

-- 데이터 확인
SELECT ROWNUM as 로우넘, BOARD.* FROM BOARD;

SELECT COUNT(sub.로우넘) AS "데이터의 길이" FROM (SELECT ROWNUM as 로우넘, BOARD.* FROM BOARD) sub;

SELECT sub."데이터의 길이", BOARD.* FROM BOARD, (SELECT COUNT(sub.로우넘) AS "데이터의 길이" FROM (SELECT ROWNUM as 로우넘, BOARD.* FROM BOARD) sub) sub; 

commit;