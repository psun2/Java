package chat;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ChatDTO {

	private int chatID;
	private String chatName, chatContent, chatTime;
	// private Date chatTime;
	private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

	public ChatDTO() {
		// TODO Auto-generated constructor stub
	}

	// public ChatDTO(String chatName, String chatContent, Date chatTime) {
	public ChatDTO(String chatName, String chatContent, String chatTime) {
		super();
		this.chatName = chatName;
		this.chatContent = chatContent;
		this.chatTime = chatTime;
	}

	public ChatDTO(int chatID, String chatName, String chatContent, String chatTime) {
		super();
		this.chatID = chatID;
		this.chatName = chatName;
		this.chatContent = chatContent;
		this.chatTime = chatTime;
	}

	public int getChatID() {
		return chatID;
	}

	public void setChatID(int chatID) {
		this.chatID = chatID;
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

//	public Date getChatTime() {
//		return chatTime;
//	}
	public String getChatTime() {
		return chatTime;
	}

	public String getChatTimeStr() {
		return sdf.format(chatTime);
	}

//	public void setChatTime(Date chatTime) {
//		this.chatTime = chatTime;
//	}

	public void setChatTime(String chatTime) {
		this.chatTime = chatTime;
	}

//	public void setChatTimeStr(String chatTime) {
//		try {
//			this.chatTime = sdf.parse(chatTime);
//		} catch (ParseException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//	}

	@Override
	public String toString() {
		return "ChatDTO [chatName=" + chatName + ", chatContent=" + chatContent + ", chatTime=" + chatTime + "]";
	}

}
