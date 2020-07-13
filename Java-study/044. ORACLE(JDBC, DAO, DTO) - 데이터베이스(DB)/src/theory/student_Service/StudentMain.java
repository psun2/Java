package theory.student_Service;

import theory.student.StudentDAO;
import theory.student.StudentDTO;

public class StudentMain {

	// Database List (목록)

	public static void main(String[] args) {

		for (StudentDTO student : new StudentDAO().list()) {
			System.out.println(student);
		}

//		StudentDTO [id=aaa, name=한가인, hobby=잠자기, no=10, height=182.7, regDate=2020-03-02 00:00:00.0, birth=2020-07-13]
//		StudentDTO [id=bbb, name=두가인, hobby=잠자기, no=17, height=173.56, regDate=2020-03-02 00:00:00.0, birth=1996-07-13]
//		StudentDTO [id=ddd, name=사가인, hobby=잠자기, no=12, height=0.0, regDate=2019-05-08 00:00:00.0, birth=1989-06-08]
//		StudentDTO [id=eee, name=호이호이, hobby=파리리, no=1, height=1.0, regDate=2015-05-05 00:00:00.0, birth=1988-08-08]
//		StudentDTO [id=fff, name=육가인, hobby=영화, no=3, height=0.0, regDate=2020-03-02 00:00:00.0, birth=1997-11-11]

/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

		// Database Create (추가)

		StudentDTO dto = new StudentDTO();
		dto.setId("tjddjs90");
		dto.setName("박성언");
		dto.setHobby("몰라요");
		dto.setHeight(175.5);
		dto.setRegDateStr("2020-05-12");
		dto.setBirthStr("2020-07-13");

		// insert into student (id, name, no, height, regdate, birth, hobby) values
		// ('tjddjs90', '박성언', null, 175.5, 'Tue May 12 00:00:00 KST 2020', 'Mon Jul 13
		// 00:00:00 KST 2020', '몰라요');

		// => DTO 을 만들어서 삽입할때 set 을 이용하여, String 형태를 Date로 바꾸어 주었고,
		// sql 문법으로 맴버 변수인 regdate 와 birth 를 삽입할때 다시 string 형태로 변환 해주어야 하기때문에 DTO으 get
		// 메소드를 커스터마이징 합니다.

		System.out.println(new StudentDAO().insert(dto)); // 0 => Error, 1 => 정상 처리

		for (StudentDTO student : new StudentDAO().list()) {
			System.out.println(student);
		}

//		StudentDTO [id=aaa, name=한가인, hobby=잠자기, no=10, height=182.7, regDate=2020-03-02 00:00:00.0, birth=2020-07-13]
//		StudentDTO [id=bbb, name=두가인, hobby=잠자기, no=17, height=173.56, regDate=2020-03-02 00:00:00.0, birth=1996-07-13]
//		StudentDTO [id=ddd, name=사가인, hobby=잠자기, no=12, height=0.0, regDate=2019-05-08 00:00:00.0, birth=1989-06-08]
//		StudentDTO [id=eee, name=호이호이, hobby=파리리, no=1, height=1.0, regDate=2015-05-05 00:00:00.0, birth=1988-08-08]
//		StudentDTO [id=fff, name=육가인, hobby=영화, no=3, height=0.0, regDate=2020-03-02 00:00:00.0, birth=1997-11-11]
//		StudentDTO [id=tjddjs90, name=박성언, hobby=몰라요, no=0, height=175.5, regDate=2020-05-12 00:00:00.0, birth=2020-07-13]
//		StudentDTO [id=tjddjs90, name=박성언, hobby=몰라요, no=0, height=175.5, regDate=2020-05-12 00:00:00.0, birth=2020-07-13]

/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

		// Database Delete (삭제)

		System.out.println(new StudentDAO().delete("eee")); // 1

		for (StudentDTO student : new StudentDAO().list()) {
			System.out.println(student);
		}

//		StudentDTO [id=aaa, name=한가인, hobby=잠자기, no=10, height=182.7, regDate=2020-03-02 00:00:00.0, birth=2020-07-13]
//		StudentDTO [id=bbb, name=두가인, hobby=잠자기, no=17, height=173.56, regDate=2020-03-02 00:00:00.0, birth=1996-07-13]
//		StudentDTO [id=ddd, name=사가인, hobby=잠자기, no=12, height=0.0, regDate=2019-05-08 00:00:00.0, birth=1989-06-08]
//		StudentDTO [id=fff, name=육가인, hobby=영화, no=3, height=0.0, regDate=2020-03-02 00:00:00.0, birth=1997-11-11]
//		StudentDTO [id=tjddjs90, name=박성언, hobby=몰라요, no=0, height=175.5, regDate=2020-05-12 00:00:00.0, birth=2020-07-13]
//		StudentDTO [id=tjddjs90, name=박성언, hobby=몰라요, no=0, height=175.5, regDate=2020-05-12 00:00:00.0, birth=2020-07-13]
//		StudentDTO [id=tjddjs90, name=박성언, hobby=몰라요, no=0, height=175.5, regDate=2020-05-12 00:00:00.0, birth=2020-07-13]
//		StudentDTO [id=tjddjs90, name=박성언, hobby=몰라요, no=0, height=175.5, regDate=2020-05-12 00:00:00.0, birth=2020-07-13]

/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

		// Database Modify (수정)

		dto.setName("박성언");

		System.out.println(new StudentDAO().modify(dto)); // 1

		for (StudentDTO student : new StudentDAO().list()) {
			System.out.println(student);
		}
//		update student set name = '박성언', no = null, height = 175.5, regdate = '2020-05-12', birth = '2020-07-13', hobby = '몰라요' where id = 'tjddjs90'
//		7
//		StudentDTO [id=aaa, name=김영재, hobby=몰라요, no=0, height=175.5, regDate=2020-05-12 00:00:00.0, birth=2020-07-13]
//		StudentDTO [id=bbb, name=김영재, hobby=몰라요, no=0, height=175.5, regDate=2020-05-12 00:00:00.0, birth=2020-07-13]
//		StudentDTO [id=ddd, name=김영재, hobby=몰라요, no=0, height=175.5, regDate=2020-05-12 00:00:00.0, birth=2020-07-13]
//		StudentDTO [id=fff, name=김영재, hobby=몰라요, no=0, height=175.5, regDate=2020-05-12 00:00:00.0, birth=2020-07-13]
//		StudentDTO [id=tjddjs90, name=박성언, hobby=몰라요, no=0, height=175.5, regDate=2020-05-12 00:00:00.0, birth=2020-07-13]
//		StudentDTO [id=tjddjs90, name=박성언, hobby=몰라요, no=0, height=175.5, regDate=2020-05-12 00:00:00.0, birth=2020-07-13]
//		StudentDTO [id=tjddjs90, name=박성언, hobby=몰라요, no=0, height=175.5, regDate=2020-05-12 00:00:00.0, birth=2020-07-13]
//		StudentDTO [id=tjddjs90, name=박성언, hobby=몰라요, no=0, height=175.5, regDate=2020-05-12 00:00:00.0, birth=2020-07-13]
//		StudentDTO [id=tjddjs90, name=박성언, hobby=몰라요, no=0, height=175.5, regDate=2020-05-12 00:00:00.0, birth=2020-07-13]
//		StudentDTO [id=tjddjs90, name=박성언, hobby=몰라요, no=0, height=175.5, regDate=2020-05-12 00:00:00.0, birth=2020-07-13]
//		StudentDTO [id=tjddjs90, name=박성언, hobby=몰라요, no=0, height=175.5, regDate=2020-05-12 00:00:00.0, birth=2020-07-13]

/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

		// Database Detail (검색)

		System.out.println(new StudentDAO().detail("tjddjs90"));

		// StudentDTO [id=tjddjs90, name=박성언, hobby=몰라요, no=0, height=175.5,
		// regDate=2020-05-12 00:00:00.0, birth=2020-07-13]
	}

}
