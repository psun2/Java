package ddong;

import java.io.Serializable;

public class DDongData implements Serializable {

	private static final long serialVersionUID = 1000L;

	/*
	 * src = �����»�� type = ����,�κ�,ä�� dst = "�ο���" ---> (Ÿ��) data (������Ʈ)
	 * 
	 */
	public String src, type;

	public String dst;

	public Object data;

	public boolean chk;

}