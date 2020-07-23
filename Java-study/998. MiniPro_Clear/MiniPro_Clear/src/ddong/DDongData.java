package ddong;

import java.io.Serializable;
import java.util.ArrayList;

public class DDongData implements Serializable {
	
	
	/*
	 * src  = 보내는사람 
	   type = 게임,로비,채팅
	   dst = "인원수" ---> (타겟)
	   data (오브젝트)
	
	*/
	public String src, type;
	
	
	public ArrayList<String> dst;
	
	public Object data;

}
