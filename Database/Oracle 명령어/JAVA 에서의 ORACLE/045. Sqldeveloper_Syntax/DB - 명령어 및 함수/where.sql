/* update */

-- ���߾�����Ʈ
update member set hobby = '��Ϳ�', regdate = '2002-06-13';

-- ������ ������Ʈ
update member set hobby = '���ڱ�' where no >= 3 and no <=7;
update member set hobby = '��ȭ' where no < 7;
update member set hobby = 'Tv����' where id = 'tjddjs90';  -- ���ٸ� ǥ���Ҷ� ��ȣ == �� �ƴ� = �� ��� 