# primary key

## primary key 생성 1 : 테이블 생성시 생성

```
! 좋지 않은 예제
create table 테이블명 (
컬럼명 자료형 primary key
);
! primary key의 이름이 없어 가장 좋지 않은 방법 입니다.
```

```
create table stud (
id varchar2(20) primary key,
name varchar2(50) constraint stud_name_nn not null,
addr varchar2(300),
tel varchar2(50) constraint stud_tel_uni unique
);
```

## primary key 생성 2 : 생성된 테이블에 key 추가

```
alter table 테이블명 add constraint constraint명 키종류(컬럼명);
```

```
alter table exam add constraint exam_no_pk primary key(no);
```

## primary key 생성 3 : 생성된 테이블에 not null 추가 (! not null 은 add 가 아닌 수정 입니다.)

```
alter table 테이블명 modify 컬럼명 constraint constarint명 키종류;
```

```
alter table stud modify name constraint stud_name_nn not null;
```

---

# foreign key (외래키)

## add

```
alter table 테이블명 add constraint constraint명 foreign key(컬럼명) references 참조테이블명(참조컬럼명);
```

```
alter table exam add constraint exam_id_fk foreign key(id) references stud(sid);
```

---

# check key (check 제한 조건)

## add

```
alter table 테이블명 add constraint constraint명 check (조건);
```

exam)

```
age number constraint test2_age_chk check (age between 0 and 150);
alter table test3 add constraint t3_chk_kor check (kor between 0 and 150);
alter table test3 add constraint t3_chk_kor check (kor >= 0 and kor <= 150);
alter table test3 add constraint t3_chk_kor check (ban in(1, 3)); -- 반을 1반 또는 3반 만을 받겠다.
```

```
alter table exam add constraint exam_title_chk check (title in('중간', '기말'));
```

---

# key 삭제

```
alter table 테이블명 drop constraint constraint명;
```

```
alter table stud drop constraint stud_tel_uni;
```

---

# 생성된 키 확인

```
select * from user_constraints;
```
