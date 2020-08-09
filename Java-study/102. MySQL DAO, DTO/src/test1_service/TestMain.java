package test1_service;

import java.util.ArrayList;

import test_1.TestDAO;
import test_1.TestDTO;

public class TestMain {

	public static void main(String[] args) {

		System.out.println(new TestDAO().insert(new TestDTO("tjddjs90")));

		ArrayList<TestDTO> list = new ArrayList<TestDTO>();

		list.add(new TestDTO("zzang9"));
		list.add(new TestDTO("zzang10"));
		list.add(new TestDTO("zzang11"));
		list.add(new TestDTO("zzang12"));

		System.out.println(new TestDAO().listInsert(list));

		for (TestDTO dto : new TestDAO().list()) {
			System.out.println(dto);
		}

	}

}
