# 프로젝트 데이터베이스 접속
USE BBS;

# 게시판 양식 테이블 생성
CREATE TABLE BBS (
bbsID INT, # 게시판의 번호
bbsTitle VARCHAR(50), # 게시글의 제목
userID VARCHAR(20), # 작성자
bbsDate DATETIME, # 작성날짜
bbsContent VARCHAR(2048), # 게시글의 내용
bbsAvailable INT, # 현재 글이 삭제 되었나 안되었나를 알려줍니다.
# available 이 1인경우 삭제가 안된경우 0인경우는 삭제가 된경우
PRIMARY KEY (bbsID)
);