package file;

public class FileDTO {

	private String fileName, fileRealName;
	private int downloadCount;

	public FileDTO() {
		// TODO Auto-generated constructor stub
	}

	public FileDTO(String fileName, String fileRealName, int downloadCount) {
		super();
		this.fileName = fileName;
		this.fileRealName = fileRealName;
		this.downloadCount = downloadCount;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getFileRealName() {
		return fileRealName;
	}

	public void setFileRealName(String fileRealName) {
		this.fileRealName = fileRealName;
	}

	public int getdownloadCount() {
		return downloadCount;
	}

	public void setdownloadCount(int downloadCount) {
		this.downloadCount = downloadCount;
	}

	@Override
	public String toString() {
		return "fileDTO [fileName=" + fileName + ", fileRealName=" + fileRealName + "]";
	}

}
