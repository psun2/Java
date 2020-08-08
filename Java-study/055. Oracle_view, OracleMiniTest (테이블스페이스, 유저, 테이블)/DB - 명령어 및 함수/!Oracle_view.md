# view

두개의 테이블을 연결하여 같이 사용하여야 할때,  
서브querry 및 join 을 하지 않고 <<mark><b>가상</b></mark>> 의 테이블을 만들어 사용합니다.  
가상의 테이블은 insert 로 데이타의 조작이 불가능 하며, update도 불가능 하지만,  
제약 조건에 따라 될 수 도 있습니다.

---

## create (생성)

```
create view view_name as select 문;
```

```
create view stud_exam as
select name, exam.*, kor + eng + mat as tot, (kor + eng + mat) / 3 as avg
from stud, exam
where sid = id
order by title desc, avg desc;
```

---

## drop (삭제)

```
drop view view_name;
```

```
drop view stud_exam;
```

---

## 상태확인

```
select * from user_views;
```
