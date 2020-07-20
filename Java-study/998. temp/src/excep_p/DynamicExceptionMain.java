package excep_p;

class MyExcep extends Exception {

	String name;

	public MyExcep() {
		super("그만 하자");
	}

	public MyExcep(int i) {
		super(new String[] { "여긴", "참나무숲", "장수", "풍뎅이와", "사슴벌레가", "살지" }[i]);
		String[] arr = { "여긴", "참나무숲", "장수", "풍뎅이와", "사슴벌레가", "살지" };
		name = arr[i];
	}

	public MyExcep(int i, String str) {

		String[] arr = { " 값이 없습니다.", " 값이 다릅니다.", " 유효한 범위의 값이 아닙니다." };
		name = str + " " + arr[i];
	}

	public MyExcep(String msg) {
		super(msg);
		name = msg;
	}

	@Override
	public String getMessage() {
		// TODO Auto-generated method stub
		// return "조용히 하라";
		return name;
	}

	void printMsg() {
		System.out.println(super.getMessage());
	}

	String ttt() {
		return "아기상어 뚜루루 뚜루";
	}
}

public class DynamicExceptionMain {

	public static void main(String[] args) {

		try {
			// throw new MyExcep();
			// throw new MyExcep("여유롭게");
			// throw new MyExcep(1);
			// throw new MyExcep(0,"id");
			// throw new MyExcep(1,"pw");
			throw new MyExcep(2, "출생년도");
			// } catch (Exception e) {
		} catch (MyExcep e) {

			// System.out.println(e.ttt());
			System.out.println(e.getMessage());
			e.printMsg();
		}

	}

}
