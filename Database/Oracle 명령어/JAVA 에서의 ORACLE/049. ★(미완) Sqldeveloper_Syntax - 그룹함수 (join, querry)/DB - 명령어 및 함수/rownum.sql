/* rownum */

select rownum �ο��, member.* from member;

--select : �����ϴ�
--rownum : ����ȣ �� �ִ� Oracle�� ���� �Լ�
--�ο�� : �÷���
--member.* : member �� ��� �÷���
--from : ~ �κ���
--member : member ���̺�

select member.*, rownum �ο�� from member;

select rownum �ο��, member.* from member
order by name; 

--select : �����ϴ�
--rownum : ����ȣ �� �ִ� Oracle�� ���� �Լ�
--�ο�� : �÷���
--member.* : member �� ��� �÷���
--from : ~ �κ���
--member : member ���̺�
--order by : �����ϰٴ�
--name : �̸� ������ (���� ����)

select rownum �ο��, member.* from member
order by name desc;

--select : �����ϴ�
--rownum : ����ȣ �� �ִ� Oracle�� ���� �Լ�
--�ο�� : �÷���
--member.* : member �� ��� �÷���
--from : ~ �κ���
--member : member ���̺�
--order by : �����ϰٴ�
--name : �̸� ������ 
-- desc : ���� ����