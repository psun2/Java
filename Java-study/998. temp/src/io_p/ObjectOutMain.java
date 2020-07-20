package io_p;

import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;

class ASDFG implements Serializable {

	private static final long serialVersionUID = 1234L;
	// 은닉화 static 상수화 의 자료형을 long 으로 ID 값을 부여 합니다.
	// serialVersionUID : 상수화, static 에 은닉화 까지 해야함 --> 현재 클래스의 아이디를 부여하여
	// 클래스의 정의부에 혼동을 회피한다

	String str;
	int aaa;

	public ASDFG(String str, int aaa) {
		super();
		this.str = str;
		this.aaa = aaa;
	}

	@Override
	public String toString() {
		return "ASDFG [str=" + str + ", aaa=" + aaa + "]";
	}

}

public class ObjectOutMain {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		try {

			FileOutputStream fos = new FileOutputStream("fff/qwe.zxc");
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			// ObjectOutputStream 은 DataOutputStream 과 똑같이 작동합니다.
			// 하지만 Object 를 읽고 쓸 수 있습니다.

			oos.writeBoolean(true);
			oos.writeChar('a');
			oos.writeDouble(123.456);
			oos.writeInt(123);
			oos.writeFloat(789.654f);
			oos.writeLong(123456745678l);
			oos.writeUTF("우리는 슈퍼슈퍼 슈퍼잭");
			oos.writeUTF("여기 참나무  숲 ");
			oos.writeObject(new int[] { 11, 22, 33, 44 });

			ArrayList arr = new ArrayList();
			arr.add(123);
			arr.add("asdasdas");
			arr.add(true);
			arr.add(123.456);
			arr.add(new String[] { "홍성혁", "김연지", "도망자" });

			oos.writeObject(arr);

			// oos.writeObject(new ASDFG("김연섭", 123)); // Error
			// java.io.NotSerializableException
			// => 직렬화 안됐어 너

			// implements Serializable 구현후 가능
			oos.writeObject(new ASDFG("김연섭", 123)); // Error

			oos.close();
			fos.close();

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
