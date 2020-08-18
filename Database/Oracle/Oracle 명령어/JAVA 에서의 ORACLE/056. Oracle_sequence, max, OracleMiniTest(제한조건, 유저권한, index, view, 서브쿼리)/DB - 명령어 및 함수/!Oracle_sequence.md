# SEQUENCE

ex) 겹치지 않아야 할 id값을 사용자가 원하는대로 값을 증가 시킬 수 있습니다.
연관되어, rownum 과 max 함수를 예로 들 수 있습니다.  
보통 view 의 가상테이블에 sequence를 생성하여 사용 합니다.

- <mark><b>주의 사항!</b></mark>  
  sequence 사용시 insert all 을 하게 되면,  
  sequence가 인지를 못하고 모두 같은 번호로 생성되게 됩니다.  
  insert all 사용 대신 DAO를 통해 List 형식으로 setAutoCommint(false) 와 commit rollback 을 사용 하여 insert 합니다.

## create (생성)

```
create sequence sequence_name
start with 시작번호
increment by 증가량;
```

```
create sequence exam_id
start with 100
increment by 1;
```

---

## sequence 활용

```
insert into 시퀀스를 사용할 테이블명 (테이블컬럼, 테이블컬럼, 테이블컬럼, 테이블컬럼, 테이블컬럼, 테이블컬럼) values (sequence_name.nextval, 값, 값, 값, 값, 값);
```

```
insert into exam (id, pid, title, regdate, kor, eng) values (exam_id.nextval, 'aaa', '기말', '2019-05-05', 34, 56);
```

---

## 현재 sequence value 조회

```
select sequence_name.currval from dual;
```

```
select exam_id.currval from dual;
```

---

## sequence 수정

sequence의 start with 는 교체가 불가능 하므로, seqeunce 를 삭제후
조건에 맞게 재 생성 하여 사용 합니다.

```
alter sequence sequence_name
increment by 수정된값;
```

```
alter sequence exam_id
increment by 100;
```

---

## drop (삭제)

```
drop sequence sequence_name;
```

```
drop sequence exam_id;
```

---

## sequence 조회

```
select * from user_sequences;
```

---

## insert all

-- insert all 사용시 sequence
-- id 값이 변경되지 않고 통일되어 하나로 들어 갑니다.

```
insert all
into exam (id, pid, title, regdate, kor, eng) values (exam_id.nextval, 'eee', '기말', '2019-05-05', 34, 56)
into exam (id, pid, title, regdate, kor, eng) values (exam_id.nextval, 'eee', '기말', '2019-05-05', 34, 56)
into exam (id, pid, title, regdate, kor, eng) values (exam_id.nextval, 'eee', '기말', '2019-05-05', 34, 56)
into exam (id, pid, title, regdate, kor, eng) values (exam_id.nextval, 'eee', '기말', '2019-05-05', 34, 56)
into exam (id, pid, title, regdate, kor, eng) values (exam_id.nextval, 'eee', '기말', '2019-05-05', 34, 56)
select * from dual;
```
