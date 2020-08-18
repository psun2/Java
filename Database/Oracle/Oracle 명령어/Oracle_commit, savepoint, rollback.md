# commit (저장)

```
insert 후
commit;
```

```
insert into department (id, name) values ('com', '컴퓨터공학');
commit;
```

---

# savepoint (저장위치 : ex> 윈도우 복원지점?)

```
savepoint 변수명;
```

```
savepoint a;
```

---

# rollback (commit 전까지 되돌립니다.)

## 커밋 전까지 롤백

```
rollback;
```

## savepoint 전 까지 롤백

```
rollback to savepoint변수명
```

```
rollback to a;
```
