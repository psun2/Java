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

		// .replaceAll("<", "&lt;").replaceAll(">", "&gt;").replaceAll("\r\n", "<br>")
		
		// XXS = 크로스 사이트 스크립팅
		// XXS 공격이란 ? 게시판과 같이 글 작성하는 곳에서 해커들이 스크립트 구문을 사용할수 있으므로, 엔티티로 바꾸어 공격을 방어합니다.
		// .replaceAll("받은문자열", "치환할 문자열");
		
		try {
			this.con = DataUtil.getConnection();
			this.psmt = con.prepareStatement(sql);
			psmt.setString(1, evaluationDTO.getUserID().replaceAll("<", "&lt;").replaceAll(">", "&gt;").replaceAll("\r\n", "<br>"));
			psmt.setString(2, evaluationDTO.getLectureName().replaceAll("<", "&lt;").replaceAll(">", "&gt;").replaceAll("\r\n", "<br>"));
			psmt.setString(3, evaluationDTO.getProfessorName().replaceAll("<", "&lt;").replaceAll(">", "&gt;").replaceAll("\r\n", "<br>"));
			psmt.setInt(4, evaluationDTO.getLectureYear());
			psmt.setString(5, evaluationDTO.getSemesterDivide().replaceAll("<", "&lt;").replaceAll(">", "&gt;").replaceAll("\r\n", "<br>"));
			psmt.setString(6, evaluationDTO.getLectureDivide().replaceAll("<", "&lt;").replaceAll(">", "&gt;").replaceAll("\r\n", "<br>"));
			psmt.setString(7, evaluationDTO.getEvaluationTitle().replaceAll("<", "&lt;").replaceAll(">", "&gt;").replaceAll("\r\n", "<br>"));
			psmt.setString(8, evaluationDTO.getEvaluationContent().replaceAll("<", "&lt;").replaceAll(">", "&gt;").replaceAll("\r\n", "<br>"));
			psmt.setString(9, evaluationDTO.getTotalScore().replaceAll("<", "&lt;").replaceAll(">", "&gt;").replaceAll("\r\n", "<br>"));
			psmt.setString(10, evaluationDTO.getCreditScore().replaceAll("<", "&lt;").replaceAll(">", "&gt;").replaceAll("\r\n", "<br>"));
			psmt.setString(11, evaluationDTO.getComfortableScore().replaceAll("<", "&lt;").replaceAll(">", "&gt;").replaceAll("\r\n", "<br>"));
			psmt.setString(12, evaluationDTO.getLectureScore().replaceAll("<", "&lt;").replaceAll(">", "&gt;").replaceAll("\r\n", "<br>"));

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
		System.out.println("DAO lectureDivide : " + lectureDivide);
		System.out.println("DAO searchType : " + searchType);
		System.out.println("DAO search : " + search);
		System.out.println("DAO pageNumber : " + pageNumber);
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
			psmt.setString(1, "%" + lectureDivide + "%");
			psmt.setString(2, "%" + search + "%");
			System.out.println("like문 :  %" + lectureDivide + "%");
			System.out.println("like문 :  %" + search + "%");
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

	// 한 아이디당 한 강의 평가의 추천은 한번만 진행
	public int like(String evaluationID) {
		String sql = "update evaluation set likeCount = likeCount + 1 where evaluationID = ?";
		// 이메일 인증은 처음에 되지 않았으므로, false 값 입력

		try {
			this.con = DataUtil.getConnection();
			this.psmt = con.prepareStatement(sql);
			psmt.setInt(1, Integer.parseInt(evaluationID));
			return psmt.executeUpdate();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			close();
		} finally {
			close();
		}

		return -1; // 데이터베이스 오류
	}

	// 강의 평가 추천 삭제
	public int delete(String evaluationID) {
		String sql = "delete from evaluation where evaluationID = ?";
		// 이메일 인증은 처음에 되지 않았으므로, false 값 입력

		try {
			this.con = DataUtil.getConnection();
			this.psmt = con.prepareStatement(sql);
			psmt.setInt(1, Integer.parseInt(evaluationID));
			return psmt.executeUpdate();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			close();
		} finally {
			close();
		}

		return -1; // 데이터베이스 오류
	}

	// 특정한 강의 평가글을 작성한 유저의 아이디를 가져오는 함수
	public String getUserID(String evaluationID) {
		String sql = "select userID from likey where evaluationID = ?";
		// 이메일 인증은 처음에 되지 않았으므로, false 값 입력

		try {
			this.con = DataUtil.getConnection();
			this.psmt = con.prepareStatement(sql);
			psmt.setInt(1, Integer.parseInt(evaluationID));
			this.rs = psmt.executeQuery();

			if (rs.next()) {
				return rs.getString(1);
			}

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			close();
		} finally {
			close();
		}

		return null; // 존재하지 않는 아이디
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
