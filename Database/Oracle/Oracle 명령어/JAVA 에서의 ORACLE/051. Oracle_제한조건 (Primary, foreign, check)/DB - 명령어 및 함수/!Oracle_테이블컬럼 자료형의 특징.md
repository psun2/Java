# column

## column 추가

```
alter table 테이블명 add(컬럼명 자료형);
```

```
alter table test1 add(spec varchar2(20));
```

---

## column 모든 데이터 delete

```
delete from 테이블명
```

```
delete from test1;
```

---

## column자료형 변경

```
alter table 테이블명 modify(변경할 컬럼명 변경할 자료형);
```

```
alter table test1 modify(id varchar2(20));
```

---

## column명 변경

```
alter table test1 rename column 현재컬럼명 to 변경할 컬럼명;
```

```
alter table test1 rename column id to pid;
```

---

## column 삭제

```
alter table 테이블명 drop column 삭제할 컬럼명;
```

```
alter table test1 drop column spec;
```

---

## 테이블 삭제

```
drop table 테이블명;
```

```
drop table test1;
```
