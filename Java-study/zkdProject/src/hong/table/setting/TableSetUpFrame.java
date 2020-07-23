package hong.table.setting;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JToggleButton;
import javax.swing.border.EmptyBorder;

import hong.table.TableMainController;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class TableSetUpFrame extends JFrame {

	TableSettingController tableSettingController;
	ButtonGroup bg1;
	
	private JPanel contentPane;
	private JTextField nickNameTextField ;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TableSetUpFrame frame = new TableSetUpFrame();
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
	public TableSetUpFrame() {
		tableSettingController=new  TableSettingController(new TableMainController());
		init();
	}
	public TableSetUpFrame(TableSettingController tableSettingController) {
		this.tableSettingController=tableSettingController;
		init();
		setVisible(true);
	}
	void init() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 612, 210);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JPanel nickNameSetUpPanel = new JPanel();
		contentPane.add(nickNameSetUpPanel, BorderLayout.NORTH);
		nickNameSetUpPanel.setLayout(new BorderLayout(0, 0));
		
		JLabel youLab = new JLabel("����� ");
		nickNameSetUpPanel.add(youLab, BorderLayout.WEST);
		
		JLabel areLab = new JLabel(" �Դϴ�.");
		nickNameSetUpPanel.add(areLab, BorderLayout.EAST);
		
		JPanel panel_5 = new JPanel();
		nickNameSetUpPanel.add(panel_5, BorderLayout.CENTER);
		panel_5.setLayout(new BorderLayout(0, 0));
		
		JLabel nickNamelab = new JLabel("�г����� ");
		panel_5.add(nickNamelab, BorderLayout.WEST);
		
		nickNameTextField = new JTextField(tableSettingController.myTableInform.getTableNickName());
		panel_5.add(nickNameTextField, BorderLayout.CENTER);
		nickNameTextField.setColumns(10);
		
		JPanel cenceptSepUpPanel = new JPanel();
		contentPane.add(cenceptSepUpPanel, BorderLayout.CENTER);
		cenceptSepUpPanel.setLayout(new BorderLayout(0, 0));
		
		JPanel panel_3 = new JPanel();
		cenceptSepUpPanel.add(panel_3, BorderLayout.NORTH);
		
		JLabel concept = new JLabel("������ ���� ������ �������ּ��� !");
		panel_3.add(concept);
		
		JPanel conceptBntPanel = new JPanel();
		cenceptSepUpPanel.add(conceptBntPanel, BorderLayout.CENTER);
		
		JToggleButton conceptBnt1 = new JToggleButton("���عޱ� ����"); //�������ִ� ��ư �����ϱ�//true�� �����ִ�.
		conceptBnt1.setBounds(20, 210, 70, 30);
		conceptBntPanel.add(conceptBnt1);
		JToggleButton conceptBnt2 = new JToggleButton("������ ���ܸ�");
		conceptBnt2.setBounds(90, 210, 70, 30);
		conceptBntPanel.add(conceptBnt2);
		JToggleButton conceptBnt3 = new JToggleButton("�޸������ ��");
		conceptBnt3.setBounds(160, 210, 70, 30);
		conceptBntPanel.add(conceptBnt3);
		bg1 = new ButtonGroup(); //��ư�� �����ִ� �� - ���1�� �������� ���2�� Ǯ������ ���ھ
		bg1.add(conceptBnt1);
		bg1.add(conceptBnt2);
		bg1.add(conceptBnt3);
		
		
		JPanel panel_2 = new JPanel();
		contentPane.add(panel_2, BorderLayout.SOUTH);
		panel_2.setLayout(new BorderLayout(0, 0));
		
		//Ȯ�ι�ư ������ �г��Ӱ� ������ DB�� �ڷ� �ְ� ���̺��º��� �߰� �ϱ�
		JButton table_SetUp_Bnt = new JButton("Ȯ��");
		table_SetUp_Bnt.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				String concept="";
				if(conceptBnt1.isSelected())concept+=conceptBnt1.getText();
				else if(conceptBnt2.isSelected())concept+=conceptBnt2.getText();
				else if(conceptBnt3.isSelected())concept+=conceptBnt3.getText();
				tableSettingController.modifyMyTableInform(nickNameTextField.getText(), concept);

				dispose();//Ȯ�� ��ư ������ â �ݱ�
			}
		});
		panel_2.add(table_SetUp_Bnt);
		
		//�ǳʶٱ� ��ư ������ â�� ������ �����
		JButton pass = new JButton("�ǳʶٱ�");
		panel_2.add(pass, BorderLayout.EAST);
		
		pass.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				dispose();//�ǳʶٱ� ��ư ������ â �ݱ�
			}
		});
	}
}
