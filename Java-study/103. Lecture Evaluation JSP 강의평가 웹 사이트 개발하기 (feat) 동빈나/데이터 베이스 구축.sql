##########################################################################################

# 데이터베이스 생성
create database LectureEvaluation;

# 데이터베이스 접속
use LectureEvaluation;

# 계정 권한 허가
ALTER USER 'root'@'localhost' IDENTIFIED WITH mysql_native_password BY 'root'; 

# 테이블 생성
create table user(
userId varchar(20) not null unique,
userPassword varchar(64) not null,
userEmail varchar(50) not null,
userEmailHash varchar(64) not null, # 이메일 확인(이메일 인증)
userEmailChecked boolean  # 이메일 인증 여부
);

# 테이블 요소 확인
desc user;

# primary key 추가
alter table user add primary key(userID);

####################################################################################
create table evaluation( # 하나의 평가 글을 담는 테이블
evaluationID int primary key auto_increment, # 게시글의 번호
userID varchar(20), # 유저 아이디
lectureName varchar(50), # 강의명
professorName varchar(20), # 교수명 
lectureYear int, # 수강연도
semesterDivied varchar(20), #수강학기 ex) 1학기, 2학기
lectureDivied varchar(10), # 강의 구분 전체 or 교양 or 전공
evaluationTitle varchar(50), # 평가 제목
evaluationContent varchar(2048), # 평가 내용
totalScore varchar(5), # A,B,C,D
creditScore varchar(5),
comfortableScore varchar(5),
lectureScore varchar(5),
likeCount int # 추천 개수
);

# 테이블 요소 확인
desc evaluation;

########################################################################################

# 좋아요 정보를 담고 있는 테이블 생성
create table likey( # 좋아요를 누른 사용자 의 정보를 담는 테이블
userID varchar(20),
evaluationID int,
userIP varchar(50),
primary key(userID, evaluationID)
);

# 테이블 요소 확인
desc likey;

# 생성된 테이블 확인
show tables;

select * from user;