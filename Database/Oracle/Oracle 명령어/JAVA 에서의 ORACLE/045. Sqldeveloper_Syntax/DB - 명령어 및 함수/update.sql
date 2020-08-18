/* update */

-- 다중업데이트
update member set hobby = '비와요', regdate = '2002-06-13';

-- 조건적 업데이트
update member set hobby = '잠자기' where no >= 3 and no <=7;
update member set hobby = '영화' where no < 7;
update member set hobby = 'Tv봐요' where id = 'tjddjs90';  -- 같다를 표시할때 등호 == 가 아닌 = 로 사용 

/* 각 항목별 업데이트 */
update member set name = '호이호이', no = 1, height = 1.0, regdate = '2015-05-05', birth = '1988-08-08', hobby = '파리리'
where id = 'rmftpdy';

update member set name = '', no = 1, height = 1.0, regdate = '', birth = '', hobby = ''
where id = '';