package evaluation;

import java.sql.Connection;
import java.sql.PreparedStatement;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class EvaluationDAO {

	DataSource dataSource = null;

	public EvaluationDAO() {

		try {
			InitialContext initContext = new InitialContext();

			Context context = (Context) initContext.lookup("java:/comp/env");

			dataSource = (DataSource) context.lookup("jdbc/223. 강의평가 웹사이트 oracle_ver");
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public int write(EvaluationDTO evaluationDTO) {

		Connection conn = null;
		PreparedStatement psmt = null;
		String sql = "INSERT INTO EVALUEATION (evaluationID, userID, lectureName, professorName, lectureYear, semesterDivide, lectureDivide, evalueationTitle, evalueationContent, totalScore, creditScore, comfortableScore, lectureScore, likeCount)"
				+ "VALUES (evaluationID_squence.nextval, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, 0)";

		try {

			conn = dataSource.getConnection();
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, evaluationDTO.getUserID());
			psmt.setString(2, evaluationDTO.getLectureName());
			psmt.setString(3, evaluationDTO.getProfessorName());
			psmt.setInt(4, evaluationDTO.getLectureYear());
			psmt.setString(5, evaluationDTO.getSemesterDevide());
			psmt.setString(6, evaluationDTO.getLectureDevide());
			psmt.setString(7, evaluationDTO.getEvaluationTitle());
			psmt.setString(8, evaluationDTO.getEvaluationContent());
			psmt.setString(9, evaluationDTO.getTotlaScore());
			psmt.setString(10, evaluationDTO.getCreditScore());
			psmt.setString(11, evaluationDTO.getComportableScore());
			psmt.setString(12, evaluationDTO.getLectureScore());
			
			return psmt.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if(psmt != null && !psmt.isClosed())
					psmt.close();
				if(conn != null && !conn.isClosed())
					conn.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		return -1; // 데이터 베이스 오류
	}

}
