package an.tableBoard;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;

import an.adminMain.AdminMainView;
import hong.server.MessageObject;

public class TableBoardControl {
	TableBoardView	boardView;
	ArrayList<JButton> orderBtn;
	public TableBoardControl(AdminMainView mainView) {
		boardView = new TableBoardView(this, mainView);
		boardView.main();
		addOrderBtn();
	}
	public void callStaff (MessageObject msgObject) {
		   if(msgObject.getMessageMain().equals("테이블_1")) {
		         boardView.table_1.setBackground(Color.orange);
		      }
		      if(msgObject.getMessageMain().equals("테이블_2")) {
		         boardView.table_2.setBackground(Color.orange);
		      }
		      if(msgObject.getMessageMain().equals("테이블_3")) {  
		         boardView.table_3.setBackground(Color.orange);
		      }
		      if(msgObject.getMessageMain().equals("테이블_4")) {
		         boardView.table_4.setBackground(Color.orange);
		      }
		      if(msgObject.getMessageMain().equals("테이블_5")) {
		         boardView.table_5.setBackground(Color.orange);
		      }
		      if(msgObject.getMessageMain().equals("테이블_6")) {
		         boardView.table_6.setBackground(Color.orange);
		      }
		      if(msgObject.getMessageMain().equals("테이블_7")) {
		         boardView.table_7.setBackground(Color.orange);
		      }
		      if(msgObject.getMessageMain().equals("테이블_8")) {
		         boardView.table_8.setBackground(Color.orange);
		      }
	}
	public void addOrderBtn() {
		orderBtn = new ArrayList();
		for (int i = 0; i < 8; i++) {
			JButton btn = new JButton("table_"+(i+1));
			btn.setBackground(new Color(206,251,201));
			boardView.order_p.add(btn);
			orderBtn.add(btn);
			btn.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					btn.setBackground(new Color(206,251,201));
				}
			});
		}
		
	}
	public void completeOrder(MessageObject msgObject) {
		if(msgObject.getMessageMain().equals("테이블_1")) {
			orderBtn.get(0).setBackground(Color.red);
		}
		if(msgObject.getMessageMain().equals("테이블_2")) {
			orderBtn.get(1).setBackground(Color.red);
		}
		if(msgObject.getMessageMain().equals("테이블_3")) {
			orderBtn.get(2).setBackground(Color.red);
		}
		if(msgObject.getMessageMain().equals("테이블_4")) {
			orderBtn.get(3).setBackground(Color.red);
		}
		if(msgObject.getMessageMain().equals("테이블_5")) {
			orderBtn.get(4).setBackground(Color.red);
		}
		if(msgObject.getMessageMain().equals("테이블_6")) {
			orderBtn.get(5).setBackground(Color.red);
		}
		if(msgObject.getMessageMain().equals("테이블_7")) {
			orderBtn.get(6).setBackground(Color.red);
		}
		if(msgObject.getMessageMain().equals("테이블_8")) {
			orderBtn.get(7).setBackground(Color.red);;
		}
		boardView.order_p.setVisible(false);
		boardView.order_p.setVisible(true);
	}
}
