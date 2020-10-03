package evaluation;

public class EvaluationDTO {

	private int evaleationID, lectureYear, likeCount;
	private String userID, lectureName, professorName, semesterDevide, lectureDevide, evaluationTitle,
			evaluationContent, totlaScore, creditScore, comportableScore, lectureScore;

	public EvaluationDTO() {
		// TODO Auto-generated constructor stub
	}

	public EvaluationDTO(String userID, String lectureName, String professorName, int lectureYear,
			String semesterDevide, String lectureDevide, String evaluationTitle, String evaluationContent,
			String totlaScore, String creditScore, String comportableScore, String lectureScore) {
		super();
		this.userID = userID;
		this.lectureName = lectureName;
		this.professorName = professorName;
		this.lectureYear = lectureYear;
		this.semesterDevide = semesterDevide;
		this.lectureDevide = lectureDevide;
		this.evaluationTitle = evaluationTitle;
		this.evaluationContent = evaluationContent;
		this.totlaScore = totlaScore;
		this.creditScore = creditScore;
		this.comportableScore = comportableScore;
		this.lectureScore = lectureScore;
	}

	public EvaluationDTO(int evaleationID, String userID, String lectureName, String professorName, int lectureYear,
			String semesterDevide, String lectureDevide, String evaluationTitle, String evaluationContent,
			String totlaScore, String creditScore, String comportableScore, String lectureScore, int likeCount) {
		super();
		this.evaleationID = evaleationID;
		this.userID = userID;
		this.lectureName = lectureName;
		this.professorName = professorName;
		this.lectureYear = lectureYear;
		this.semesterDevide = semesterDevide;
		this.lectureDevide = lectureDevide;
		this.evaluationTitle = evaluationTitle;
		this.evaluationContent = evaluationContent;
		this.totlaScore = totlaScore;
		this.creditScore = creditScore;
		this.comportableScore = comportableScore;
		this.lectureScore = lectureScore;
		this.likeCount = likeCount;
	}

	public int getEvaleationID() {
		return evaleationID;
	}

	public void setEvaleationID(int evaleationID) {
		this.evaleationID = evaleationID;
	}

	public String getUserID() {
		return userID;
	}

	public void setUserID(String userID) {
		this.userID = userID;
	}

	public String getLectureName() {
		return lectureName;
	}

	public void setLectureName(String lectureName) {
		this.lectureName = lectureName;
	}

	public String getProfessorName() {
		return professorName;
	}

	public void setProfessorName(String professorName) {
		this.professorName = professorName;
	}

	public int getLectureYear() {
		return lectureYear;
	}

	public void setLectureYear(int lectureYear) {
		this.lectureYear = lectureYear;
	}

	public String getSemesterDevide() {
		return semesterDevide;
	}

	public void setSemesterDevide(String semesterDevide) {
		this.semesterDevide = semesterDevide;
	}

	public String getLectureDevide() {
		return lectureDevide;
	}

	public void setLectureDevide(String lectureDevide) {
		this.lectureDevide = lectureDevide;
	}

	public String getEvaluationTitle() {
		return evaluationTitle;
	}

	public void setEvaluationTitle(String evaluationTitle) {
		this.evaluationTitle = evaluationTitle;
	}

	public String getEvaluationContent() {
		return evaluationContent;
	}

	public void setEvaluationContent(String evaluationContent) {
		this.evaluationContent = evaluationContent;
	}

	public String getTotlaScore() {
		return totlaScore;
	}

	public void setTotlaScore(String totlaScore) {
		this.totlaScore = totlaScore;
	}

	public String getCreditScore() {
		return creditScore;
	}

	public void setCreditScore(String creditScore) {
		this.creditScore = creditScore;
	}

	public String getComportableScore() {
		return comportableScore;
	}

	public void setComportableScore(String comportableScore) {
		this.comportableScore = comportableScore;
	}

	public String getLectureScore() {
		return lectureScore;
	}

	public void setLectureScore(String lectureScore) {
		this.lectureScore = lectureScore;
	}

	public int getLikeCount() {
		return likeCount;
	}

	public void setLikeCount(int likeCount) {
		this.likeCount = likeCount;
	}

	@Override
	public String toString() {
		return "evaluationDTO [evaleationID=" + evaleationID + ", userID=" + userID + ", lectureName=" + lectureName
				+ ", professorName=" + professorName + ", lectureYear=" + lectureYear + ", semesterDevide="
				+ semesterDevide + ", lectureDevide=" + lectureDevide + ", evaluationTitle=" + evaluationTitle
				+ ", evaluationContent=" + evaluationContent + ", totlaScore=" + totlaScore + ", creditScore="
				+ creditScore + ", comportableScore=" + comportableScore + ", lectureScore=" + lectureScore
				+ ", likeCount=" + likeCount + "]";
	}

}
