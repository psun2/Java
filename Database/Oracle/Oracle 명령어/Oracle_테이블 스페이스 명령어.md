# Oracle_tablespace

## 테이블 스페이스 생성

```
create tablespace 공간이름
datafile 파일 경로
size 사이즈;
```

```
create tablespace korea_2
datafile 'D:\Oracle\korea_temp.dbf'
size 100000000;
```

## 생성된 테이블 스페이스 확인

```
select * from dba_tablespaces;
```

---

## 테이블 스페이스 정보

### 생성된 테이블 스페이스 확인

```
select * from dba_tablespaces;
```

### 테이블 스페이스 상세정보 확인

```
select file_name, file_id, tablespace_name, status from dba_data_files where tablespace_name = '테이블 스페이스 명';
```

```
select file_name, file_id, tablespace_name, status from dba_data_files where tablespace_name = 'TP_2';
```

---

## 테이블스페이스 사이즈 변경

```
alter database datafile '파일경로' resize 변경용량;
```

```
ALTER DATABASE DATAFILE 'C:\ORACLEXE\APP\ORACLE\ORADATA\KOREA\TP_BBB.DBF' RESIZE 100000000;
```

---

## user 생성

```
reate user 계정명 identified by 비밀번호
default tablespace 사용 할 공간 파일명
temporary tablespace temp;
```

```
create user korea_b identified by 123456
default tablespace korea_2
temporary tablespace temp;
```

```
사용자 생성_b 123456으로 식별됨
기본 테이블스페이스 korea_2
임시 테이블스페이스 온도;
```

## 생성한 유저 확인

```
select * from all_users;
```

---

## 정보확인

### 테이블스페이스 목록

```
select * from dba_tablespaces;
```

### 데이터 파일

```
select * from dba_data_files;
```

### 컨트롤 파일

```
select * from v$controlfile;
```

### 로그파일

```
select * from v$log;
```

```
select * from v$logfile;
```

```
select * from v$log t1, v$logfile t2
where t1.group# = t2.group#;
```

---

## 권한 부여 (grant : 승인하다)

모든 권한 목록  
connect  
resource  
create table  
create view  
create sequence

```
grant 권한명 to user
```

```
grant connect, resource, create table to korea_b;
```

## 권한 확인

```
select * from dba_sys_privs where grantee = '유저명(!!대문자로!!)';
```

```
select * from dba_sys_privs where grantee = 'KOREA_B';
```

---

## 권한 삭제 (revoke : 폐지하다)

모든 권한 목록  
connect  
resource  
create table  
create view  
create sequence

```
revoke 권한명 from 유저명
```

```
revoke resource, connect, create table from korea_b;
```

## 권한 확인

```
select * from dba_sys_privs where grantee = '유저명(!!대문자로!!)';
```

```
select * from dba_sys_privs where grantee = 'KOREA_B';
```

---

## 계정 수정 (alter : 변질시키다)

```
alter user 유저명 identified by 수정된 비밀번호;
```

```
alter user korea_b identified by 987654;
```

---

## 계정 삭제 (drop : 떨어뜨리다)

접속시 삭제가 안되므로, 접속 해제수 삭제 진행 합니다.

```
drop user 유저명 cascade;
```

```
drop user korea_b cascade;
```

---

## 테이블스페이스 삭제 (drop : 떨어뜨리다)

```
drop tablespace 공간파일 명;
```

```
drop tablespace korea_2;
```

---

## 삭제 확인

```
select * from all_users;
```

```
select * from dba_tablespaces;
```

---

남은 폴더와 dbf 파일은 껍데기 뿐이니 그냥 delete 시켜도 됩니다.
