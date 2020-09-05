# 데이터베이스 생성
CREATE DATABASE FILE;

# 데이터베이스 접속
USE FILE;

# 테이블 생성
CREATE TABLE FILE (
fileName VARCHAR(200), # 파일명
fileRealName VARCHAR(200) # 사용자가 실제로 입력한 파일명
);

# 다운로드 횟수를 알기위한 새로운 필드 생성
ALTER TABLE FILE ADD(downloadCount INT);
