package newasd;

public class Element {

	int x, y;
	String color;
	boolean stop;

	public Element(int x, int y) {
		// TODO Auto-generated constructor stub

		this.x = x;
		this.y = y;

		String[] colors = { "blue", "green", "red", "yellow", "ninja" };
		int i = (int) (Math.random() * 5);

		if (i == 4) {

			for (int j = 0; j < 3; j++) {
				i = (int) (Math.random() * 5);
			}

		}

		this.color = colors[i];
		this.stop = false;
	}

}
