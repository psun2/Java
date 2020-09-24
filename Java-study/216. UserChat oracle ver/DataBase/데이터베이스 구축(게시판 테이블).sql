-- 게시판 테이블생성
CREATE TABLE BOARD (
userID VARCHAR2(20),
boardID INT CONSTRAINT boardID_pk PRIMARY KEY,
boardTitle VARCHAR2(50),
boardContent VARCHAR2(2048),
boardDate TIMESTAMP,
boardHit INT,
boardFile VARCHAR2(100), -- 사용자가 업로드 하는 파일명
boardRealFile VARCHAR2(100), -- 서버에 저장되는 실제 물리적 주소
boardGroup INT, 
boardSequence INT,
boardLevel INT
);

-- boardID를 위한 sequence 생성 (oracle 은 ifnull 이 없는듯)
-- IFNULL((SELECT max(boardID)+1 from BOARD ), 1) -- 작동안됨
CREATE SEQUENCE board_boardID
START WITH 1
INCREMENT BY 1;

-- boardGroup를 위한 sequence 생성
CREATE SEQUENCE board_boardGroup
START WITH 1
INCREMENT BY 1;

-- 예시 데이터 삽입
INSERT INTO BOARD
(userID, boardID, boardTitle, boardContent,boardDate, boardHit,  boardFile, boardRealFile, boardGroup, boardSequence, boardLevel)
VALUES ('asd', board_boardID.NEXTVAL, 'asdas', 'asdasdas', sysdate, 0, null, null, board_boardGroup.NEXTVAL,0,0);

-- 확인
select board.*, TO_CHAR(boardDate, 'YYYY/MM/DD HH24:MI:SS') as createDate from board;

-- 삭제
DELETE FROM BOARD;

-- 확인후 sequence 삭제
drop SEQUENCE board_boardID;
drop SEQUENCE board_boardGroup;

drop table board;