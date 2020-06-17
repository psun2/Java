package theory;

//칵테일을 만들어 주세요
///  소맥 ::>  2 : 8  (변경불가)  
///  사콜 ::>  5 : 5  (변경가능)
///  총 소주, 맥주, 사이다, 콜라 사용량을 출력하세요  (단위 cc ) 
//총 사용 누적 합계량
//ex) 
class CocktailMenu {
	final String name;
	final String[] title = { "소주", "맥주", "사이다", "콜라" };
	final double[] recipe; // "소주","맥주","사이다","콜라"
	// TODO 배열은 final 키워드를 사용 하면 주소 변경은 불가능 하지만, 내부 데이터 변경은 가능하다.
	// TODO 위와 같은 이유로 배열은 final 보단 private 로 접근통제를 하는 부분이 더 좋은 방법
	final int size;

	public CocktailMenu(String name, int size, double... recipe) {
		super();
		this.name = name;
		this.recipe = recipe;
		this.size = size;
	}

	void serving(int cnt, double[] tot) {
		String ttt = name + " " + cnt + "잔(";

		for (int i = 0; i < recipe.length; i++) {
			if (recipe[i] != 0) {
				int rr = (int) (cnt * recipe[i] * size);
				ttt += title[i] + ":" + rr + ",";
				tot[i] += rr;
			}
		}

		ttt += ") " + cnt * size;

		System.out.println(ttt);
	}

}

class Cocktail {
	final private String[] title = { "소주", "맥주", "사이다", "콜라" };
	final private double[] tot = new double[title.length]; // 긱긱의 잔수 배열

	// : 5,(잔당:) : 2(잔당:), :3 (잔당:) :2 (잔당 ), :3 (잔당 )
	final private CocktailMenu[] menu = {

			// "소주","맥주","사이다","콜라"
			new CocktailMenu("소맥", 500, 0.2, 0.8, 0, 0), new CocktailMenu("사콜", 450, 0, 0, 0.5, 0.5),
			new CocktailMenu("소주", 50, 1, 0, 0, 0), new CocktailMenu("맥주", 500, 0, 1, 0, 0),
			new CocktailMenu("콜라", 350, 0, 0, 0, 1) };

	void setCC(double cider, double coke) {
		menu[1].recipe[2] = cider;
		menu[1].recipe[3] = coke;
		// 전 new 이용해 배열을 생성시켜서 바꿔주는 것은 주소를 바꾸어 주는 것이기 때문에
		// final 이 붙어 있으면 에러가 납니다. 하지만 내부의 index 값을 바꿔 주는 것은 가능합니다.
	}

	void order(String name, int cnt) { // name = kind (술종류), cnt = 잔 수
		int num = -1;

		for (int i = 0; i < menu.length; i++) {
			if (menu[i].name.equals(name)) {
				menu[i].serving(cnt, tot);
				num = i;
				break;
			}
		}

		if (num < 0) {
			System.out.println("없는 메뉴입니다.");
			return;
		}
	}

	void ppp() {
		System.out.println("전체>>>>>>>");

		for (int i = 0; i < tot.length; i++) {
			System.out.println(title[i] + ":" + tot[i]);
		}
	}

}

public class CocktailMain {

	public static void main(String[] args) {
		Cocktail cock = new Cocktail();

		cock.order("소맥", 3);
		cock.order("사콜", 4);
		cock.order("맥주", 3);
		cock.order("사콜", 5);
		cock.setCC(0.7, 0.3);
		cock.order("콜라", 1);
		cock.order("사콜", 3);
		cock.order("소맥", 3);
		cock.order("사콜", 4);
		cock.ppp();
	}

}