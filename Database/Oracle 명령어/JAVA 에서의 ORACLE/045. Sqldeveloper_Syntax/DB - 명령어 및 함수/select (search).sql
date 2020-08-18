/* select */
select id,name, birth from member where id = 'tjddjs90';
-- tjddjs90	박성언	90/05/12

select id,name, birth from member where id != 'tjddjs90';
--kyj1211	김영재	90/12/11
--rmftpdy	호이호이	88/08/08
--ghdrlfrhd	홍길동	50/04/25
--zmflrmaktm	크리스마스	98/12/25
--tlrahrdlf	식목일	01/04/05

select * from member where id != 'tjddjs90';
--2	kyj1211	김영재	영화		19/10/13 00:00:00.000000000	90/12/11
--1	rmftpdy	호이호이	파리리	1	15/05/05 00:00:00.000000000	88/08/08
--5	ghdrlfrhd	홍길동	영화		20/02/06 13:00:00.000000000	50/04/25
--6	zmflrmaktm	크리스마스	영화		20/03/02 00:00:00.000000000	98/12/25
--7	tlrahrdlf	식목일	잠자기		20/03/02 00:00:00.000000000	01/04/05