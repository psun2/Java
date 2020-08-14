package chat;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ChatDTO {

	private String chatName, chatContent;
	private Date chatTime;
	private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

	public ChatDTO() {
		// TODO Auto-generated constructor stub
	}

	public ChatDTO(String chatName, String chatContent, Date chatTime) {
		super();
		this.chatName = chatName;
		this.chatContent = chatContent;
		this.chatTime = chatTime;
	}

	public String getChatName() {
		return chatName;
	}

	public void setChatName(String chatName) {
		this.chatName = chatName;
	}

	public String getChatContent() {
		return chatContent;
	}

	public void setChatContent(String chatContent) {
		this.chatContent = chatContent;
	}

	public Date getChatTime() {
		return chatTime;
	}

	public String getChatTimeStr() {
		return sdf.format(chatTime);
	}

	public void setChatTime(Date chatTime) {
		this.chatTime = chatTime;
	}

	public void setChatTimeStr(String chatTime) {
		try {
			this.chatTime = sdf.parse(chatTime);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
