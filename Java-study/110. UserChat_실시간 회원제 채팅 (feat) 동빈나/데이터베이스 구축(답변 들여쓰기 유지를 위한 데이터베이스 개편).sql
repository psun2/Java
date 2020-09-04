# 답변 들여쓰기 유지를 위한 데이터베이스 개편

# 데이터베이스 진입
USE USERCHAT;

# Workbench 사용시 delete 안됨....
# Ddit => preferences => SQL Editor => Safe Updates 체크 해제후 mysql 서버 재가동 

# 기존 게시판 자료 삭제
DELETE FROM BOARD;

# 기존 게시판 테이블 삭제
DROP TABLE BOARD;

# 새로운 BOARD 테이블 생성
CREATE TABLE BOARD (
userID VARCHAR(20),
boardID INT PRIMARY KEY,
boardTitle VARCHAR(50),
boardContent VARCHAR(2048),
boardDate DATETIME,
boardHit INT, # 조회수
boardFile VARCHAR(100), # 첨부파일
boardRealFile VARCHAR(100), # 업로드 된 파일의 서버 실제 물리적 경로
boardGroup INT,
boardSequence INT, # 댓글처리 를 위한 변수
boardLevel INT,  # 댓글처리 를 위한 변수
boardAvailable INT # 댓글 삭제시 구조를 유지하기 위한 변수
);

# boardAvailable : 기존 게시글 작성시 1, 게시글 삭제시0 why? 답변 들여쓰기를 위한 변수 => 게시판에 삭제된 답변입니다 라고 화면 출력을 위한 변수