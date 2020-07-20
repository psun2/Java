package io_p;

import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.util.Arrays;

public class ObjectInMain {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		try {

			FileInputStream fos = new FileInputStream("fff/qwe.zxc");
			ObjectInputStream ois = new ObjectInputStream(fos);
			// ObjectInputStream 은 FileInputStream 과 똑같이 작동합니다.
			// 하지만 Object 를 읽고 쓸 수 있습니다.

			System.out.println(ois.readBoolean());
			System.out.println(ois.readChar());
			System.out.println(ois.readDouble());
			System.out.println(ois.readInt());
			System.out.println(ois.readFloat());
			System.out.println(ois.readLong());
			System.out.println(ois.readUTF());
			System.out.println(ois.readUTF());

			// System.out.println(ois.readObject()); // 주소만 넘어옴...
			// [I@7229724f
			// System.out.println(Arrays.toString(ois.readObject())); // error

			int[] arr = (int[]) ois.readObject(); // 형변환을 해주면 사용 가능
			System.out.println(Arrays.toString(arr));
			// [11, 22, 33, 44]

			System.out.println(ois.readObject());
			// [123, asdasdas, true, 123.456, [Ljava.lang.String;@7cca494b]

			// Object obj = ois.readObject();
			// System.out.println(((ASDFG) obj).str);
			 System.out.println(ois.readObject());
			// ASDFG [str=김연섭, aaa=123]

			// 덮여 씌웟을때.... package 명이 다르면 Error

			ois.close();
			fos.close();

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
