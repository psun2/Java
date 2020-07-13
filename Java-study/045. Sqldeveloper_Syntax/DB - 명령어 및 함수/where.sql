/* update */

-- 다중업데이트
update member set hobby = '비와요', regdate = '2002-06-13';

-- 조건적 업데이트
update member set hobby = '잠자기' where no >= 3 and no <=7;
update member set hobby = '영화' where no < 7;
update member set hobby = 'Tv봐요' where id = 'tjddjs90';  -- 같다를 표시할때 등호 == 가 아닌 = 로 사용 