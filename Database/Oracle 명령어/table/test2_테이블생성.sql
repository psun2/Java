-- 메뉴 테이블 생성 id, name, price
create table menu(
id varchar2(20),
name varchar2(1000),
price int
);

-- 주문테이블 생성 id, mid, regdate, cnt
create table order_mm(
id int,
mid varchar2(20),
regdate date,
cnt int
);
