package chat;

public class ChatDTO {

	private int chatID;
	private String fromID, toID, chatContent, chatTime, chatTimeStamp, time;

	public ChatDTO() {
		// TODO Auto-generated constructor stub
	}

	public ChatDTO(int chatID, String fromID, String toID, String chatContent, String chatTime, String chatTimeStamp,
			String time) {
		super();
		this.chatID = chatID;
		this.fromID = fromID;
		this.toID = toID;
		this.chatContent = chatContent;
		this.chatTime = chatTime;
		this.chatTimeStamp = chatTimeStamp;
		this.time = time;
	}

	public int getChatID() {
		return chatID;
	}

	public void setChatID(int chatID) {
		this.chatID = chatID;
	}

	public String getFromID() {
		return fromID;
	}

	public void setFromID(String fromID) {
		this.fromID = fromID;
	}

	public String getToID() {
		return toID;
	}

	public void setToID(String toID) {
		this.toID = toID;
	}

	public String getChatContent() {
		return chatContent;
	}

	public void setChatContent(String chatContent) {
		this.chatContent = chatContent;
	}

	public String getChatTime() {
		return chatTime;
	}

	public void setChatTime(String chatTime) {
		this.chatTime = chatTime;
	}

	public String getChatTimeStamp() {
		return chatTimeStamp;
	}

	public void setChatTimeStamp(String chatTimeStamp) {
		this.chatTimeStamp = chatTimeStamp;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	@Override
	public String toString() {
		return "ChatDTO [chatID=" + chatID + ", fromID=" + fromID + ", toID=" + toID + ", chatContent=" + chatContent
				+ ", chatTime=" + chatTime + ", chatTimeStamp=" + chatTimeStamp + ", time=" + time + "]";
	}

}
