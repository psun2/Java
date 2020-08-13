package evaluation;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import util.DataUtil;

public class EvaluationDAO {

	Connection con;
	PreparedStatement psmt;
	ResultSet rs;

	// 사용자가 한개의 강의 평가를 등록 할 수 있게함
	// 글쓰기 함수
	public int write(EvaluationDTO evaluationDTO) {

		System.out.println(evaluationDTO);

		// 기본적으로 evaluation 이 evaluationID 는auto_increment 가 적용 되어 있어 null 값을 부여 해주면
		// 자동적으로 값이 들어 갑니다.
		// 0 : 초기 등록시 좋아요는 0번
		String sql = "INSERT INTO EVALUATION VALUES (NULL, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, 0)";

		try {
			this.con = DataUtil.getConnection();
			this.psmt = con.prepareStatement(sql);
			psmt.setString(1, evaluationDTO.getUserID());
			psmt.setString(2, evaluationDTO.getLectureName());
			psmt.setString(3, evaluationDTO.getProfessorName());
			psmt.setInt(4, evaluationDTO.getLectureYear());
			psmt.setString(5, evaluationDTO.getSemesterDivide());
			psmt.setString(6, evaluationDTO.getLectureDivide());
			psmt.setString(7, evaluationDTO.getEvaluationTitle());
			psmt.setString(8, evaluationDTO.getEvaluationContent());
			psmt.setString(9, evaluationDTO.getTotalScore());
			psmt.setString(10, evaluationDTO.getCreditScore());
			psmt.setString(11, evaluationDTO.getComfortableScore());
			psmt.setString(12, evaluationDTO.getLectureScore());

			return psmt.executeUpdate();

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			close();
		} finally {
			close();
		}

		return -1; // 데이터 베이스 오류

	}

	public ArrayList<EvaluationDTO> getList(String lectureDivide, String searchType, String search, int pageNumber) {
		if (lectureDivide.equals("전체")) {
			lectureDivide = "";
		}
		ArrayList<EvaluationDTO> evaluationList = null;

		String sql = "";

		try {
			if (searchType.equals("최신순")) {
				sql = "select * from evaluation where lectureDivide like ? and concat(lectureName, professorName, evaluationTitle, evaluationContent) like ? order by evaluationId desc limit "
						+ pageNumber * 5 + ", " + pageNumber * 5 + 6;
				// like => 특정문자열을 포함하는지 물어볼때 사용 하는문법
			} else if (searchType.equals("추천순")) {
				sql = "select * from evaluation where lectureDivide like ? and concat(lectureName, professorName, evaluationTitle, evaluationContent) like ? order by likeCount desc limit "
						+ pageNumber * 5 + ", " + pageNumber * 5 + 6;
			}
			this.con = DataUtil.getConnection();
			this.psmt = con.prepareStatement(sql);
			System.out.println(sql);
			psmt.setString(1, "%" + lectureDivide + "%");
			psmt.setString(2, "%" + search + "%");
			System.out.println(sql);
			this.rs = psmt.executeQuery();
			evaluationList = new ArrayList<EvaluationDTO>();

			while (rs.next()) {
				EvaluationDTO evaluation = new EvaluationDTO(rs.getInt(1), rs.getString(2), rs.getString(3),
						rs.getString(4), rs.getInt(5), rs.getString(6), rs.getString(7), rs.getString(8),
						rs.getString(9), rs.getString(10), rs.getString(11), rs.getString(12), rs.getString(13),
						rs.getInt(14));
				evaluationList.add(evaluation);
			}

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			close();
		} finally {
			close();
		}

		System.out.println(evaluationList);
		return evaluationList;
	}

	void close() {

		try {
			if (rs != null && !rs.isClosed())
				rs.close();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		try {
			if (psmt != null && !psmt.isClosed())
				psmt.close();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		try {
			if (con != null && !con.isClosed())
				con.close();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

	}

}
