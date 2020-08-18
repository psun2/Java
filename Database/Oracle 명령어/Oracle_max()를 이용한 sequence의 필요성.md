# max 를 이용한 sequence 대체와 sequence의 필요성

결론: 코드가 amx를 이용할때보다 sequence 를 이용하면 코드가 훨씬 간결하고,
깔끔해 집니다.

- <mark><b>주의 사항!</b></mark>  
  sequence와 같이 insert all 을 하게 되면,  
  max가 인지를 못하고 모두 같은 번호로 생성되게 됩니다.  
  insert all 사용 대신 DAO를 통해 List 형식으로 setAutoCommint(false) 와 commit rollback 을 사용 하여 insert 합니다.

## max를 이용한 insert

```
insert into 테이블명 (테이블컬럼, 테이블컬럼, 테이블컬럼, 테이블컬럼, 테이블컬럼, 테이블컬럼) values ((select max(컬럼명) + 증가량 from 테이블명), 값, 값, 값, 값, 값);
```

```
insert into exam (id, pid, title, regdate, kor, eng) values ((select max(id) + 1 from exam), 'aaa', '기말', '2019-05-05', 34, 56);
```

-- insert all
-- sequence 와 마찬가지로 id 값이 변경되지 않고 통일되어 하나로 들어 갑니다.

```
insert all
into exam (id, pid, title, regdate, kor, eng) values ((select max(id) + 1 from exam), 'ddd', '기말', '2014-05-05', 11, 11)
into exam (id, pid, title, regdate, kor, eng) values ((select max(id) + 1 from exam), 'ddd', '기말', '2014-05-05', 11, 11)
into exam (id, pid, title, regdate, kor, eng) values ((select max(id) + 1 from exam), 'ddd', '기말', '2014-05-05', 11, 11)
into exam (id, pid, title, regdate, kor, eng) values ((select max(id) + 1 from exam), 'ddd', '기말', '2014-05-05', 11, 11)
into exam (id, pid, title, regdate, kor, eng) values ((select max(id) + 1 from exam), 'ddd', '기말', '2014-05-05', 11, 11)
select * from dual;
```
