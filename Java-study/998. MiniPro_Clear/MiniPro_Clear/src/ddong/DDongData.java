package ddong;

import java.io.Serializable;
import java.util.ArrayList;

public class DDongData implements Serializable {
	
	
	/*
	 * src  = �����»�� 
	   type = ����,�κ�,ä��
	   dst = "�ο���" ---> (Ÿ��)
	   data (������Ʈ)
	
	*/
	public String src, type;
	
	
	public ArrayList<String> dst;
	
	public Object data;

}
