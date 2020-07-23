package an.tableBoard;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPanel;

public class yeonji_CallControl {
	//an_tableBoardVieW
	
	yeonji_CallView callview;
	TableBoardView changeTableColor;
	Color cc = new Color(255,0,0);


	public yeonji_CallControl(TableBoardView changeTableColor) {
		
		new yeonji_CallView(this);
//		callview.main();
		this.changeTableColor = changeTableColor;
		
	}

	
	public void changePannel1 () {
		changeTableColor.panel_16.setBackground(Color.orange);
		// boardView 쪽에 있는 panel의 색을 오랜지색으 바꿔야함.
	}
	
	public static void main(String[] args) {
		
	}
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					yeonji_CallView frame = new yeonji_CallView(this);
//					frame.setVisible(true);
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		});
//	}
}