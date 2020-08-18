package jdbc_service_p;

import java.util.Date;

import jdbc_p.ResExamDAO;
import jdbc_p.ResExamDTO;

public class ResExamMain {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		// new ResExamDAO().insert(new ResExamDTO("aaa", "중간", new Date(), 78, 78));

		// 전체
//		for (ResExamDTO dto : new ResExamDAO().list()) {
//			System.out.println(dto);
//		}

		// 페이지별
//		for (ResExamDTO dto : new ResExamDAO().page(2)) {
//			System.out.println(dto);
//		}
		
		// 수정
		new ResExamDAO().modify(new ResExamDTO(400, "ccc", "기말", new Date(), 78, 78));
		
		// 아이디 값으로 조회
		System.out.println(new ResExamDAO().detail(400));

		System.out.println();
		
		// 삭제
		new ResExamDAO().delete(400);

		System.out.println(new ResExamDAO().detail(400));
		
		for (ResExamDTO dto : new ResExamDAO().list()) {
			System.out.println(dto);
		}
	}

}
