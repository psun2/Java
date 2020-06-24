package theory.Component;

import javax.swing.JFrame;
import javax.swing.JSlider;

public class Slider {

	public static void main(String[] args) {

		JFrame frame = new JFrame("JSlider");

		frame.setLayout(null); // 레이아웃

		frame.setBounds(30, 30, 400, 400);

		// JSlider(int orientation, int min, int max, int value)
//		JSlider slider = new JSlider(); // 기본
		JSlider slider = new JSlider(JSlider.HORIZONTAL, 0, 100, 50); // default (가로 bar)
//		JSlider slider = new JSlider(JSlider.WIDTH, 0, 100, 50); // (세로 bar)

		// 큰 눈금
		slider.setMajorTickSpacing(50); // 50단위
		// 작은 눈금
		slider.setMinorTickSpacing(10); // 10단위
		// 숫자 표시 - 눈금이 잇어야 표시 가능
		slider.setPaintLabels(true);
		// 선 표시
		slider.setPaintTicks(true);
		// 바 표시
		slider.setPaintTrack(true);

		slider.setBounds(30, 30, 300, 100);
		frame.add(slider);

		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	}

}
