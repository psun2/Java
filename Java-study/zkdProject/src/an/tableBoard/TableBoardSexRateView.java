package an.tableBoard;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.GridLayout;
import javax.swing.JLabel;
import javax.swing.JSpinner;
import javax.swing.SpinnerModel;
import javax.swing.SpinnerNumberModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class TableBoardSexRateView extends JFrame {
	
	private JPanel contentPane;
	static TableBoardSexRateControl boardSexControl;
	static TableBoardView boardView;
	JSpinner spinner,spinner_1;
	/**
	 * Launch the application.
	 */
	public static void main() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TableBoardSexRateView frame = new TableBoardSexRateView(boardSexControl, boardView);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public TableBoardSexRateView(TableBoardSexRateControl boardSexControl,TableBoardView boardView) {
		
		this.boardSexControl = boardSexControl;
		this.boardView = boardView;
		
		//setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 413, 446);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new GridLayout(5, 1, 0, 0));
		
		JLabel lblNewLabel = new JLabel("              ＊남");
		contentPane.add(lblNewLabel);
		
		SpinnerModel model1 = new SpinnerNumberModel(0,0,10,1);
		spinner = new JSpinner(model1);//-----------------------
		contentPane.add(spinner);
		
		JLabel lblNewLabel_1 = new JLabel("             ＊여");
		contentPane.add(lblNewLabel_1);
		
		SpinnerModel model2 = new SpinnerNumberModel (0,0,10,1);
		spinner_1 = new JSpinner(model2);//-------------------
		contentPane.add(spinner_1); // = SpinnerModel을 위로 뺴기.
		
		JButton btnNewButton = new JButton("등록");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				boardSexControl.register(spinner.getModel().getValue().toString(),
						spinner_1.getModel().getValue().toString());
				
				
				dispose();
				
			}
		});
		contentPane.add(btnNewButton);
		
	}


}
