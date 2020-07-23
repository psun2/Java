package an.tableBoard;

import java.awt.event.ActionListener;
import java.io.ObjectInputStream.GetField;

import javax.swing.JLabel;
import javax.swing.JSpinner;
import javax.swing.JTextField;

public class TableBoardSexRateControl {
	TableBoardSexRateView boardSexView;
	TableBoardView boardView;
	String tableNum;
	//JSpinner spinner;
	public TableBoardSexRateControl(TableBoardView boardView,String tableNum) {
		this.boardView = boardView;
		this.tableNum = tableNum;
		boardSexView = new TableBoardSexRateView(this, boardView);
		boardSexView.main();


	}

	public void register (String tt,String tt2) {
		
		
//		if(tableNum == "table_1") {		
//			boardView.lblNewLabel.setText(tt);
//			boardView.lblNewLabel_1.setText(tt2);
//		}
		
		
		
		switch (tableNum) {
		case "table_1":
			
			boardView.lblNewLabel.setText(tt);
			boardView.lblNewLabel_1.setText(tt2);
			break;
		
		case "table_2":
			System.out.println(boardView.lblNewLabel_4);
			boardView.lblNewLabel_4.setText(tt);
			boardView.lblNewLabel_5.setText(tt2);
			break;
			
		case "table_3":
			
			boardView.lblNewLabel_6.setText(tt);
			boardView.lblNewLabel_7.setText(tt2);
			break;

		case "table_4":
			
			boardView.lblNewLabel_8.setText(tt);
			boardView.lblNewLabel_9.setText(tt2);
			break;
		
		case "table_5":
			
			boardView.lblNewLabel_10.setText(tt);
			boardView.lblNewLabel_11.setText(tt2);
			break;
			
		case "table_6":
			
			boardView.lblNewLabel_12.setText(tt);
			boardView.lblNewLabel_13.setText(tt2);
			break;
			
		case "table_7":
			
			boardView.lblNewLabel_14.setText(tt);
			boardView.lblNewLabel_15.setText(tt2);
			break;
			
		case "table_8":
			
			boardView.lblNewLabel_16.setText(tt);
			boardView.lblNewLabel_17.setText(tt2);
			break;

		}
		
		
		

		
		
		
		
		
	}

}
