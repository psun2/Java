/* select - ���� */

/*
�� SELECT  (�ڷḦ �����ִ� ����)
�� �⺻��
 SELECT [DISTINCT] {*, column[Alias], ...} FROM ���̺��;
�� select * from employees;    -> �����Ͷ�  ��θ�   employees�� ���̺���
 select employee_id, last_name from employees;  -> �����Ͷ� id�� �̸��� 
 
�� ��� ������
 select first_name, salary + 100 from employees;
 select first_name, salary - 100 from employees;
 select first_name, salary * 100 from employees;
 select first_name, salary / 100 from employees;
 
�� null
 zero, �� ������ �ƴϴ�. 
 ��Ȯ�� �� �� �� ���� ���� �ǹ��Ѵ�.
 � ������ �� �� ������ ���� �����Ѵ�.
 ? Ȥ�� ���Ѵ��� �ǹ��̹Ƿ� ����, �Ҵ�, �񱳰� �Ұ����ϴ�.
 
 select last_name, salary*commission_pct from employees;
 null �� ��찡 �ֱ� ������ ����� null�� ���´�.
 
�� Alias
 select last_name, salary*12 as ���� from employees;
 as ���� ����
 select last_name, salary*12 as ���� ���� from employees;
 �����̳� $ _ # �� Ư�����ڸ� ���� ��� �� ����ǥ�� ����Ѵ�.
 
�� Concatenation
 select last_name, ' is a ', job_id from employees;
 select last_name || ' is a ' || job_id from employees;
 �޸� ��ſ� ����ϸ� ���ڿ��� ����Ǿ� ��µȴ�.
�� DISTINCT
 select job_id from employees;
 select distinct job_id from employees;
 �ߺ��Ǵ� �÷��� �� ������ �����ش�.
 */