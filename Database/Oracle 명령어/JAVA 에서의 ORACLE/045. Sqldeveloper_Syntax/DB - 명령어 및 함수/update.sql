/* update */

-- ���߾�����Ʈ
update member set hobby = '��Ϳ�', regdate = '2002-06-13';

-- ������ ������Ʈ
update member set hobby = '���ڱ�' where no >= 3 and no <=7;
update member set hobby = '��ȭ' where no < 7;
update member set hobby = 'Tv����' where id = 'tjddjs90';  -- ���ٸ� ǥ���Ҷ� ��ȣ == �� �ƴ� = �� ��� 

/* �� �׸� ������Ʈ */
update member set name = 'ȣ��ȣ��', no = 1, height = 1.0, regdate = '2015-05-05', birth = '1988-08-08', hobby = '�ĸ���'
where id = 'rmftpdy';

update member set name = '', no = 1, height = 1.0, regdate = '', birth = '', hobby = ''
where id = '';