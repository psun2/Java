# 게시판 기능을 위한 데이터베이스 구축

# 기존 프로젝트 데이터베이스 접속
USE USERCHAT;

# 테이블 생성
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
boardLevel INT  # 댓글처리 를 위한 변수
);