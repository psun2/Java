package evaluation;

public class EvaluationDTO {

	private int evaluationID, lectureYear, likeCount;
	private String userID, lectureName, professorName, semesterDivide, lectureDivide, evaluationTitle,
			evaluationContent, totalScore, creditScore, comfortableScore, lectureScore;

	public EvaluationDTO() {
		// TODO Auto-generated constructor stub
	}

	public EvaluationDTO(int evaluationID, String userID, String lectureName, String professorName, int lectureYear,
			String semesterDivide, String lectureDivide, String evaluationTitle, String evaluationContent,
			String totalScore, String creditScore, String comfortableScore, String lectureScore, int likeCount) {
		super();
		this.evaluationID = evaluationID;
		this.userID = userID;
		this.lectureName = lectureName;
		this.professorName = professorName;
		this.lectureYear = lectureYear;
		this.semesterDivide = semesterDivide;
		this.lectureDivide = lectureDivide;
		this.evaluationTitle = evaluationTitle;
		this.evaluationContent = evaluationContent;
		this.totalScore = totalScore;
		this.creditScore = creditScore;
		this.comfortableScore = comfortableScore;
		this.lectureScore = lectureScore;
		this.likeCount = likeCount;
	}

	public int getEvaluationID() {
		return evaluationID;
	}

	public void setEvaluationID(int evaluationID) {
		this.evaluationID = evaluationID;
	}

	public int getLectureYear() {
		return lectureYear;
	}

	public void setLectureYear(int lectureYear) {
		this.lectureYear = lectureYear;
	}

	public int getLikeCount() {
		return likeCount;
	}

	public void setLikeCount(int likeCount) {
		this.likeCount = likeCount;
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

	public String getSemesterDivide() {
		return semesterDivide;
	}

	public void setSemesterDivide(String semesterDivide) {
		this.semesterDivide = semesterDivide;
	}

	public String getLectureDivide() {
		return lectureDivide;
	}

	public void setLectureDivide(String lectureDivide) {
		this.lectureDivide = lectureDivide;
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

	public String getTotalScore() {
		return totalScore;
	}

	public void setTotalScore(String totalScore) {
		this.totalScore = totalScore;
	}

	public String getCreditScore() {
		return creditScore;
	}

	public void setCreditScore(String creditScore) {
		this.creditScore = creditScore;
	}

	public String getComfortableScore() {
		return comfortableScore;
	}

	public void setComfortableScore(String comfortableScore) {
		this.comfortableScore = comfortableScore;
	}

	public String getLectureScore() {
		return lectureScore;
	}

	public void setLectureScore(String lectureScore) {
		this.lectureScore = lectureScore;
	}

	@Override
	public String toString() {
		return "EvaluationDTO [evaluationID=" + evaluationID + ", userID=" + userID + ", lectureName=" + lectureName
				+ ", professorName=" + professorName + ", lectureYear=" + lectureYear + ", semesterDivied="
				+ semesterDivide + ", lectureDivied=" + lectureDivide + ", evaluationTitle=" + evaluationTitle
				+ ", evaluationContent=" + evaluationContent + ", totalScore=" + totalScore + ", creditScore="
				+ creditScore + ", comfortableScore=" + comfortableScore + ", lectureScore=" + lectureScore
				+ ", likeCount=" + likeCount + "]";
	}

}
