package exam;

class innerStart extends Thread {

	public innerStart(String name) {
		super(name);
	}

	void sta() {
		start();
	}

	@Override
	public void run() {
		System.out.println(getName());
	}

}

public class test2 {

	public static void main(String[] args) {

		innerStart i1 = new innerStart("됩니까?");
		innerStart i2 = new innerStart("됩니까?2");

		i1.sta();
		i2.sta();

	}

}
