# Index

## 주의사항

해당 컬럼에 제약조건을 가진 key 값을 걸게 되면 알아서 index가 잡혀 index 생성이 되지 않습니다.

---

## Index 조회

```
select * from user_indexes;
```

---

## index 생성

```
create index index_name on 테이블명(해당컬럼명);
```

```
create index car_name_idx on car(name);
```

---

## index rename(이름 변경)

```
alter index index_name rename to 변경할index_name;
```

```
alter index car_name_idx rename to idx_name_car;
```

---

## index 삭제

```
drop index index_name;
```

```
drop index idx_name_car;
```
