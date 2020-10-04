package evaluation;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

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
			psmt.setString(2, evaluationDTO.getLectureName().replaceAll("<", "&lt;").replaceAll(">", "&gt;").replaceAll(" ", "&nbsp;").replaceAll("\n", "<br />"));
			psmt.setString(3, evaluationDTO.getProfessorName().replaceAll("<", "&lt;").replaceAll(">", "&gt;").replaceAll(" ", "&nbsp;").replaceAll("\n", "<br />"));
			psmt.setInt(4, evaluationDTO.getLectureYear());
			psmt.setString(5, evaluationDTO.getSemesterDevide());
			psmt.setString(6, evaluationDTO.getLectureDevide());
			psmt.setString(7, evaluationDTO.getEvaluationTitle().replaceAll("<", "&lt;").replaceAll(">", "&gt;").replaceAll(" ", "&nbsp;").replaceAll("\n", "<br />"));
			psmt.setString(8, evaluationDTO.getEvaluationContent().replaceAll("<", "&lt;").replaceAll(">", "&gt;").replaceAll(" ", "&nbsp;").replaceAll("\n", "<br />"));
			psmt.setString(9, evaluationDTO.getTotlaScore());
			psmt.setString(10, evaluationDTO.getCreditScore());
			psmt.setString(11, evaluationDTO.getComportableScore());
			psmt.setString(12, evaluationDTO.getLectureScore());

			return psmt.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (psmt != null && !psmt.isClosed())
					psmt.close();
				if (conn != null && !conn.isClosed())
					conn.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		return -1; // 데이터 베이스 오류
	}

	public ArrayList<EvaluationDTO> getList(String lectureDivide, String searchType, String search, int pageNumber) {

		ArrayList<EvaluationDTO> evaluationList = null;

		if (lectureDivide.equals("전체"))
			lectureDivide = "";

		int startPage = (pageNumber - 1) * 5 + 1;
		int endPage = startPage + 5;

		System.out.println("startPage : " + startPage);
		System.out.println("endPage : " + endPage);

		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		String sql = "";

		try {

//			if (searchType.equals("최신순"))
//				sql = "SELECT * FROM (SELECT ROWNUM as NO, sub.* FROM (SELECT * FROM EVALUEATION WHERE lectureDivide Like ? AND lectureName || evalueationContent || professorName || evalueationTitle LIKE ?) sub ORDER BY sub.evaluationID DESC) WHERE NO >= "
//						+ startPage + " AND NO <= " + endPage;
//
//			else if (searchType.equals("추천순"))
//				sql = "SELECT * FROM (SELECT ROWNUM as NO, sub.* FROM (SELECT * FROM EVALUEATION WHERE lectureDivide Like ? AND lectureName || evalueationContent || professorName || evalueationTitle LIKE ?) sub ORDER BY sub.likeCount DESC) WHERE NO >= "
//						+ startPage + " AND NO <= " + endPage;
			if (searchType.equals("최신순"))
				sql = "SELECT * FROM (SELECT ROWNUM as NO, sub.* FROM (SELECT * FROM (SELECT * FROM (SELECT * FROM EVALUEATION  WHERE lectureDivide Like ? AND lectureName || evalueationContent || professorName || evalueationTitle LIKE ?) ORDER BY evaluationID DESC)) sub) WHERE NO >= "+startPage+" AND NO <= "+endPage;
			
			else if (searchType.equals("추천순"))
				sql = "SELECT * FROM (SELECT ROWNUM as NO, sub.* FROM (SELECT * FROM (SELECT * FROM (SELECT * FROM EVALUEATION  WHERE lectureDivide Like ? AND lectureName || evalueationContent || professorName || evalueationTitle LIKE ?) ORDER BY likeCount DESC)) sub) WHERE NO >= "+startPage+" AND NO <= "+endPage;

			System.out.println("sql 검증 : " + sql);

			conn = dataSource.getConnection();
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, "%" + lectureDivide + "%");
			psmt.setString(2, "%" + search + "%");
			rs = psmt.executeQuery();

			evaluationList = new ArrayList<EvaluationDTO>();

			while (rs.next()) {
				EvaluationDTO evaluation = new EvaluationDTO();
				evaluation.setEvaleationID(rs.getInt(2));
				evaluation.setUserID(rs.getString(3));
				evaluation.setLectureName(rs.getString(4).replaceAll("<", "&lt;").replaceAll(">", "&gt;").replaceAll(" ", "&nbsp;").replaceAll("\n", "<br />"));
				evaluation.setProfessorName(rs.getString(5).replaceAll("<", "&lt;").replaceAll(">", "&gt;").replaceAll(" ", "&nbsp;").replaceAll("\n", "<br />"));
				evaluation.setLectureYear(rs.getInt(6));
				evaluation.setSemesterDevide(rs.getString(7));
				evaluation.setLectureDevide(rs.getString(8));
				evaluation.setEvaluationTitle(rs.getString(9).replaceAll("<", "&lt;").replaceAll(">", "&gt;").replaceAll(" ", "&nbsp;").replaceAll("\n", "<br />"));
				evaluation.setEvaluationContent(rs.getString(10).replaceAll("<", "&lt;").replaceAll(">", "&gt;").replaceAll(" ", "&nbsp;").replaceAll("\n", "<br />"));
				evaluation.setTotlaScore(rs.getString(11));
				evaluation.setCreditScore(rs.getString(12));
				evaluation.setComportableScore(rs.getString(13));
				evaluation.setLectureScore(rs.getString(14));
				evaluation.setLikeCount(rs.getInt(15));

				evaluationList.add(evaluation);

				System.out.println(evaluation);
			}

			return evaluationList;

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null && !rs.isClosed())
					rs.close();
				if (psmt != null && !psmt.isClosed())
					psmt.close();
				if (conn != null && !conn.isClosed())
					conn.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}

		return evaluationList;

	}

	// 좋아요 기능
	public int like(String evaluationID) {
		Connection conn = null;
		PreparedStatement psmt = null;
		String sql = "UPDATE EVALUEATION SET likeCount = likeCount + 1 WHERE evaluationID = ?";

		try {

			conn = dataSource.getConnection();
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, evaluationID);

			return psmt.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (psmt != null && !psmt.isClosed())
					psmt.close();
				if (conn != null && !conn.isClosed())
					conn.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		return -1; // 데이터 베이스 오류
	}

	// 강의글 삭제
	public int delete(String evaluationID) {
		Connection conn = null;
		PreparedStatement psmt = null;
		String sql = "DELETE FROM EVALUEATION WHERE evaluationID = ?";

		try {

			conn = dataSource.getConnection();
			psmt = conn.prepareStatement(sql);
			psmt.setInt(1, Integer.parseInt(evaluationID));

			return psmt.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (psmt != null && !psmt.isClosed())
					psmt.close();
				if (conn != null && !conn.isClosed())
					conn.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		return -1; // 데이터 베이스 오류
	}

	// 강의글 작성자 아이디 가져오기
	public String getUserID(String evaluationID) {
		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		String sql = "SELECT userID FROM EVALUEATION WHERE evaluationID = ?";

		try {

			conn = dataSource.getConnection();
			psmt = conn.prepareStatement(sql);
			psmt.setInt(1, Integer.parseInt(evaluationID));
			rs = psmt.executeQuery();

			if (rs.next())
				return rs.getString(1);

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null && !rs.isClosed())
					rs.close();
				if (psmt != null && !psmt.isClosed())
					psmt.close();
				if (conn != null && !conn.isClosed())
					conn.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		return null; // 데이터 베이스 오류
	}

}
