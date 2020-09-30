package evalueation;

public class EvalueationDTO {

	private int evaluationID, lectureYear, likeCount;
	private String userID, lectureName, professorName, semesterDivide, lectureDivide, evalueationTitle,
			evalueationContent, totalScore, creditScore, comfortableScore, lectureScore;

	public EvalueationDTO() {
		// TODO Auto-generated constructor stub
	}

	public EvalueationDTO(int evaluationID, String userID, String lectureName, String professorName, int lectureYear,
			String semesterDivide, String lectureDivide, String evalueationTitle, String evalueationContent,
			String totalScore, String creditScore, String comfortableScore, String lectureScore, int likeCount) {
		super();
		this.evaluationID = evaluationID;
		this.userID = userID;
		this.lectureName = lectureName;
		this.professorName = professorName;
		this.lectureYear = lectureYear;
		this.semesterDivide = semesterDivide;
		this.lectureDivide = lectureDivide;
		this.evalueationTitle = evalueationTitle;
		this.evalueationContent = evalueationContent;
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

	public String getUserID() {
		return userID;
	}

	public void setUserID(String userID) {
		this.userID = userID;
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

	public String getEvalueationTitle() {
		return evalueationTitle;
	}

	public void setEvalueationTitle(String evalueationTitle) {
		this.evalueationTitle = evalueationTitle;
	}

	public String getEvalueationContent() {
		return evalueationContent;
	}

	public void setEvalueationContent(String evalueationContent) {
		this.evalueationContent = evalueationContent;
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

	public int getLikeCount() {
		return likeCount;
	}

	public void setLikeCount(int likeCount) {
		this.likeCount = likeCount;
	}

	@Override
	public String toString() {
		return "EvalueationDTO [evaluationID=" + evaluationID + ", userID=" + userID + ", lectureName=" + lectureName
				+ ", professorName=" + professorName + ", lectureYear=" + lectureYear + ", semesterDivide="
				+ semesterDivide + ", lectureDivide=" + lectureDivide + ", evalueationTitle=" + evalueationTitle
				+ ", evalueationContent=" + evalueationContent + ", totalScore=" + totalScore + ", creditScore="
				+ creditScore + ", comfortableScore=" + comfortableScore + ", lectureScore=" + lectureScore
				+ ", likeCount=" + likeCount + "]";
	}

}
