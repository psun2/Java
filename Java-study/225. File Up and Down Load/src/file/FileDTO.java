package file;

public class FileDTO {

	private String fileName;
	private String fileRealName;
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

	public int getDownloadCount() {
		return downloadCount;
	}

	public void setDownloadCount(int downloadCount) {
		this.downloadCount = downloadCount;
	}

	@Override
	public String toString() {
		return "FileDAO [fileName=" + fileName + ", fileRealName=" + fileRealName + ", downloadCount=" + downloadCount
				+ "]";
	}

}
