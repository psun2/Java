# `MSSQL`

docker 로 mssql 설치하는 방법은 oracle을 참고하여 설치할수 있도록 합니다.

docker로 mssql를 이용하면 SSMS(SQL Server Management Studio) 을 사용할 수 없습니다. (ms에서 mac용 배포를 안하기 때문)

이로인해서 데이터베이스, 로그인, 권한 등을 쿼리로 직접 해야 합니다.

[Azure Data Dtudio DownLoad](https://docs.microsoft.com/ko-kr/sql/azure-data-studio/download-azure-data-studio?view=sql-server-ver15)  
쿼리를 작성하기 위한 툴로는 통용되는 디비버도 있지만,  
ms에서 나온 Azure Data Dtudio 툴을 이용 할 수 도 있습니다.

## `데이터 베이스 생성`

```
-- 데이터베이스 생성
CREATE DATABASE SCOTT;
```

---

## `로그인 생성`

```
-- LOGIN 생성 (유저의 개념과 비슷)
CREATE LOGIN SCOTT WITH PASSWORD='tiger12345678!',
DEFAULT_DATABASE = SCOTT;
```

---

## `유저 생성`

```
-- SCOTT 데이터베이스를 사용할 수 있는 USER 생성
USE SCOTT
CREATE USER SCOTT FOR LOGIN SCOTT;
```

---

## `권한부여`

테이블등을 생성하기 위하여 권한을 부여 받아야합니다.

```
-- database 전체권한 : db_owner
USE SCOTT
ALTER ROLE db_owner
ADD MEMBER SCOTT;
```

---

## `유저 삭제`

```
DROP USER SCOTT;
```

---

## `LOGIN 삭제`

로그인이 되어있다는 에러메시지가 나온다면,  
새쿼리문.sql 중에 해당 LOGIN이 사용 되고 있으면 X를 눌러 닫아주시면 되겠습니다.

```
DROP LOGIN SCOTT;
```

---

## `DATABASE 삭제`

[참고](http://blog.naver.com/PostView.nhn?blogId=checkmate12&logNo=221344263842&parentCategoryNo=11&categoryNo=&viewDate=&isShowPopularPosts=true&from=search)

```
-- 데이터 베이스 삭제 시 다음과 같은 오류 메시지가 나오며 삭제되지 않을 경우 - "데이터베이스는 현재 사용 중이므로 삭제할 수 없습니다."
-- DB에 대한 모든 연결 종료
ALTER DATABASE SCOTT
SET OFFLINE WITH ROLLBACK IMMEDIATE;

DROP DATABASE SCOTT;
```
