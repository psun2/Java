# Oracle_table

## create (생성)

```
create table 테이블명(
column명 자료형
);
```

```
create table test1 (
id number(4),
name char(10),
email varchar2(50),
height number(6, 2), -- 6자리 수에 소수점이 두자리라는 의미
birth date,
regdate timestamp,
hobby nvarchar2(10)
);
```

---

## 현재 모든 테이블 확인

### 테이블 상세정보

```
select * from dba_tables;
```

```
select * from all_all_tables;
```

### 테이블 이름

```
select * from tab;
```

---

## 사용자가 만든 탭의 모든 컬럼의 정보 확인

```
select * from user_tab_columns;
```

```
select * from user_tab_columns where table_name = '테이블명(대문자 작성)'
```

```
select * from user_tab_columns where table_name = 'TEST1'
```

```
select * from 테이블명;
```

```
select * from test1;
```

---

## 테이블 삭제

```
drop table 테이블명;
```

```
drop table test1;
```
