package data;

import java.io.Serializable;

public class Data implements Serializable {

	private static final long serialVersionUID = 1L;

	public String type;

	public Object data;

	@Override
	public String toString() {
		return "Data [type=" + type + ", data=" + data + "]";
	}

}
