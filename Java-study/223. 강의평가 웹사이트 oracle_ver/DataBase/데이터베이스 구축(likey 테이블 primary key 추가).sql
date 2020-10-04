--primary key 추가
ALTER TABLE LIKEY ADD PRIMARY KEY (userID, evalueationID); 

select * from likey; 

delete from likey;

commit;