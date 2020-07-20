package dynamic_P;

abstract class Car {

	private String name;
	private double score;

	public Car(String name) {
		this.name = name;
	}

	abstract void calc(); // 계산 메소드

	void show() {

		calc();

		String print = name + " : " + score;
		System.out.println(print);
	}

	public double getTotal() {
		return score;
	}

	public void setTotal(double score) {
		this.score = score;
	}

}

class Sports extends Car {

	private int speed, fuel, design;

	public Sports(int speed, int fuel, int design) {
		super("스포츠카");
		this.speed = speed;
		this.fuel = fuel;
		this.design = design;
	}

	void calc() {
		double score = (speed * 0.5) + (fuel * 0.2) + (design * 0.3);
		setTotal(score);
	}

}

class Truck extends Car {

	private int fuel, loadage;

	public Truck(int fuel, int loadage) {
		super("트럭");
		this.fuel = fuel;
		this.loadage = loadage;
	}

	void calc() {
		double score = (fuel * 0.6) + (loadage * 0.4);
		setTotal(score);
	}

}

class Van extends Car {

	private int speed, fuel, person;

	public Van(int speed, int fuel, int person) {
		super("승합차");
		this.speed = speed;
		this.fuel = fuel;
		this.person = person;
	}

	void calc() {
		double score = (speed * 0.15) + (fuel * 0.35) + (person * 0.5);
		setTotal(score);
	}

}

public class Exam_car {

	public static void main(String[] args) {

		Car[] arr = { new Sports(50, 20, 30), new Truck(60, 40), new Van(15, 35, 50) };

		for (Car car : arr) {
			System.out.println(car instanceof Car);
			System.out.println(car instanceof Sports);
			System.out.println(car instanceof Truck);
			System.out.println(car instanceof Van);
			car.show();
		}

	}

}
