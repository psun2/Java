package board;

public class BoardDTO {

	private String userID, boardTitle, boardContent, boardDate, boardFile, boardRealFile;
	private int boardID, boardHit, boardGroup, boardSequence, boardLevel, boardAvailable;
	// boardAvailable : 기존 게시글 작성시 1, 게시글 삭제시0 why? 답변 들여쓰기를 위한 변수 => 게시판에 삭제된 답변입니다 라고 화면 출력을 위한 변수

	public BoardDTO() {
		// TODO Auto-generated constructor stub
	}

	public BoardDTO(String userID, int boardID, String boardTitle, String boardContent, String boardDate, int boardHit,
			String boardFile, String boardRealFile, int boardGroup, int boardSequence, int boardLevel,
			int boardAvailable) {
		super();
		this.userID = userID;
		this.boardID = boardID;
		this.boardTitle = boardTitle;
		this.boardContent = boardContent;
		this.boardDate = boardDate;
		this.boardHit = boardHit;
		this.boardFile = boardFile;
		this.boardRealFile = boardRealFile;
		this.boardGroup = boardGroup;
		this.boardSequence = boardSequence;
		this.boardLevel = boardLevel;
		this.boardAvailable = boardAvailable;
	}

	public String getUserID() {
		return userID;
	}

	public void setUserID(String userID) {
		this.userID = userID;
	}

	public int getBoardID() {
		return boardID;
	}

	public void setBoardID(int boardID) {
		this.boardID = boardID;
	}

	public String getBoardTitle() {
		return boardTitle;
	}

	public void setBoardTitle(String boardTitle) {
		this.boardTitle = boardTitle;
	}

	public String getBoardContent() {
		return boardContent;
	}

	public void setBoardContent(String boardContent) {
		this.boardContent = boardContent;
	}

	public String getBoardDate() {
		return boardDate;
	}

	public void setBoardDate(String boardDate) {
		this.boardDate = boardDate;
	}

	public int getBoardHit() {
		return boardHit;
	}

	public void setBoardHit(int boardHit) {
		this.boardHit = boardHit;
	}

	public String getBoardFile() {
		return boardFile;
	}

	public void setBoardFile(String boardFile) {
		this.boardFile = boardFile;
	}

	public String getBoardRealFile() {
		return boardRealFile;
	}

	public void setBoardRealFile(String boardRealFile) {
		this.boardRealFile = boardRealFile;
	}

	public int getBoardGroup() {
		return boardGroup;
	}

	public void setBoardGroup(int boardGroup) {
		this.boardGroup = boardGroup;
	}

	public int getBoardSequence() {
		return boardSequence;
	}

	public void setBoardSequence(int boardSequence) {
		this.boardSequence = boardSequence;
	}

	public int getBoardLevel() {
		return boardLevel;
	}

	public void setBoardLevel(int boardLevel) {
		this.boardLevel = boardLevel;
	}

	public int getBoardAvailable() {
		return boardAvailable;
	}

	public void setBoardAvailable(int boardAvailable) {
		this.boardAvailable = boardAvailable;
	}

	@Override
	public String toString() {
		return "BoardDTO [userID=" + userID + ", boardID=" + boardID + ", boardTitle=" + boardTitle + ", boardContent="
				+ boardContent + ", boardDate=" + boardDate + ", boardHit=" + boardHit + ", boardFile=" + boardFile
				+ ", boardRealFile=" + boardRealFile + ", boardGroup=" + boardGroup + ", boardSequence=" + boardSequence
				+ ", boardLevel=" + boardLevel + ", boardAvailable=" + boardAvailable + "]";
	}

}
