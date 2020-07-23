package hong.table;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.sun.security.ntlm.Client;

import hong.client.DefaultClient;
import hong.server.MessageObject;
import hwi.game.GameAcceptMain;

import java.awt.GridLayout;
import java.util.HashMap;
import java.util.Vector;

import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JScrollBar;
import java.awt.FlowLayout;
import javax.swing.BoxLayout;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import java.awt.Color;
import javax.swing.ScrollPaneConstants;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class TableFrame extends JFrame {

	TableMainController tableMainController;
	DefaultClient defaultClient;
	private JPanel contentPane;
	JPanel tableListPanel;
	
	////////////////////myTable 정보를 받아올 애들
	JLabel nickNameLab;//닉네임
	JLabel conceptLab;//컨셉
	JLabel manCntLab;//남자 수
	JLabel womanCntLab;//여자 수
	JLabel tableNumberLab;//테이블 번호
	////////////////////
	//이 녀석을 놓고, 초기화 시킬 수 있어야 한다!
	HashMap<String, TablePane> otherTablePanes=new HashMap<String, TableFrame.TablePane>();
	////////////////////
	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					TableFrame frame = new TableFrame();
//					frame.setVisible(true);
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		});
//	}

	/**
	 * Create the frame.
	 */
	public void refreshMyTableSetting() {
		TableInform my=tableMainController.getTableMainInform().getMyTableInform();
		nickNameLab.setText(my.getTableNickName());//닉네임
		conceptLab.setText(my.getTableConcept());;//컨셉
		manCntLab.setText(""+my.getManCnt());//남자 수
		womanCntLab.setText(""+my.getWomanCnt());//여자 수
		tableNumberLab.setText(""+my.tableName);//테이블 번호
	}
	public TableFrame(DefaultClient defaultClient) {
		this.defaultClient = defaultClient;
		init();		
	}
	TableFrame(TableMainController tableMainController){
		this.tableMainController=tableMainController;
		this.defaultClient = tableMainController.defaultClient;
		init();
		setVisible(true);
	}
	void init() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 950, 600);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(10, 10));
		setContentPane(contentPane);
		
		JPanel myTableInformPan = new JPanel();
		contentPane.add(myTableInformPan, BorderLayout.NORTH);
		myTableInformPan.setLayout(new GridLayout(1, 2, 0, 0));
		
		JLabel brandNameLab = new JLabel("저같드 포차");
		myTableInformPan.add(brandNameLab);
		
		nickNameLab = new JLabel("(닉네임)");
		myTableInformPan.add(nickNameLab);
		
		conceptLab = new JLabel("(컨셉)");
		myTableInformPan.add(conceptLab);
		
		JLabel manLab = new JLabel("남");
		myTableInformPan.add(manLab);
		manCntLab = new JLabel("(남인원)");
		myTableInformPan.add(manCntLab);
		JLabel womanLab = new JLabel("여");
		myTableInformPan.add(womanLab);
		womanCntLab = new JLabel("(여인원)");
		myTableInformPan.add(womanCntLab);
		
		JLabel noLab = new JLabel("no.");
		myTableInformPan.add(noLab);
		
		tableNumberLab = new JLabel("(테이블 번호)");
		myTableInformPan.add(tableNumberLab);
		
		JPanel panel_1 = new JPanel();
		contentPane.add(panel_1, BorderLayout.CENTER);
		panel_1.setLayout(new BorderLayout(10, 10));
		
		//직원호출부터 계산서 설정까지 모든 버튼이 들어감. (테블릿의 오른쪽)
		JPanel panel_2 = new JPanel();
		panel_1.add(panel_2, BorderLayout.EAST);
		panel_2.setLayout(new GridLayout(3, 1, 0, 0));
		
		JButton staffCallBtn = new JButton("직원 호출");
		staffCallBtn.addActionListener(new ActionListener() {		
			@Override
			public void actionPerformed(ActionEvent e) {
				tableMainController.callWaiter();
			}
		});
		panel_2.add(staffCallBtn);
		
		JButton menuPanBtn = new JButton("메뉴판");
		menuPanBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				tableMainController.orderMenu();
			}
		});
		panel_2.add(menuPanBtn);
		
		JPanel panel_4 = new JPanel();
		panel_2.add(panel_4);
		panel_4.setLayout(new BorderLayout(10, 10));
		
		JButton billBtn = new JButton("계산서");
		billBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				tableMainController.openBill();
			}
		});
		panel_4.add(billBtn, BorderLayout.WEST);
		
		JButton setUpBtn = new JButton("설정");
		setUpBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				tableMainController.tableSetting();
			}
		});
		panel_4.add(setUpBtn, BorderLayout.EAST);
		
		//(테블릿의 왼쪽)
		JPanel panel_3 = new JPanel();
		panel_1.add(panel_3, BorderLayout.CENTER);
		panel_3.setLayout(new BorderLayout(10, 10));
	
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		panel_3.add(scrollPane, BorderLayout.CENTER);
		
		tableListPanel = new JPanel();
		scrollPane.setViewportView(tableListPanel);
//		Container container=getContentPane();
		tableListPanel.setLayout(new FlowLayout(FlowLayout.LEFT,0,0));

		new TablePane();
	}		

	void mkTablePane(String nickName, String concept, int manCnt, int womanCnt) {
		new TablePane(nickName, concept, manCnt, womanCnt);
	}
//태블릿 메인의 고객 테이블 현황판의 상태테이블 정보 패널 객체
	class TablePane extends JPanel{
		int manCnt;
		int womanCnt;
		public TablePane() {
			init("존예","달리고 싶은 날",0,3);
		}
		public TablePane(String nickName, String concept, int manCnt, int womanCnt){
			init(nickName, concept, manCnt, womanCnt);
		}
		public void setBackColor() {
			int tmp=3;
			if(manCnt!=0&&womanCnt!=0)
				tmp=2;
			else if(manCnt!=0)
				tmp=1;
			else if(womanCnt!=0)
				tmp=0;
			Color color=new Color[]{Color.PINK,Color.BLUE,Color.GREEN,Color.GRAY}[tmp];
			setBackground(Color.PINK);
		}
		void init(String nickName, String concept, int manCnt, int womanCnt){
				this.manCnt=manCnt;
				this.womanCnt=womanCnt;
				setBackColor();
	//////////현황판_1 (핑크)
				setAlignmentY(Component.CENTER_ALIGNMENT);
				setPreferredSize(new Dimension(750,200));
				
				tableListPanel.add(this);
				setLayout(new BorderLayout(10, 10));
				
				JPanel urNGCPan = new JPanel();
				urNGCPan.setOpaque(false);
				add(urNGCPan, BorderLayout.WEST);
				urNGCPan.setLayout(new BorderLayout(0, 0));
				
				JPanel urGCPan = new JPanel();
				urGCPan.setOpaque(false);
				urNGCPan.add(urGCPan, BorderLayout.SOUTH);
				
				//현황판 남자 인원수 표시  
				JLabel urManLab = new JLabel("남");
				urGCPan.add(urManLab);
				
				JLabel urManCntLab = new JLabel(""+manCnt);
				urGCPan.add(urManCntLab);
				
				//현황판 여자 인원수 표시  
				JLabel urWomanLab = new JLabel("여");
				urGCPan.add(urWomanLab);
				
				JLabel urWomanCntLab = new JLabel(""+womanCnt);
				urGCPan.add(urWomanCntLab);
				
				//현황판 컨셉 표시  
				JLabel urConceptLab = new JLabel(concept);
				urGCPan.add(urConceptLab);
				
				JPanel urNickNamePan = new JPanel();
				urNickNamePan.setOpaque(false);
				urNGCPan.add(urNickNamePan, BorderLayout.CENTER);
				urNickNamePan.setLayout(new BorderLayout(0, 0));
				
				JLabel urNickNameLab = new JLabel(nickName);
				urNickNameLab.setOpaque(true);
				urNickNamePan.add(urNickNameLab);
				urNickNameLab.setHorizontalAlignment(SwingConstants.CENTER);
				
				JPanel panel_21 = new JPanel();
				panel_21.setOpaque(false);
				urNickNamePan.add(panel_21, BorderLayout.NORTH);
				
				JPanel panel_22 = new JPanel();
				panel_22.setOpaque(false);
				urNickNamePan.add(panel_22, BorderLayout.WEST);
				
				JPanel panel_23 = new JPanel();
				panel_23.setOpaque(false);
				urNickNamePan.add(panel_23, BorderLayout.EAST);
				
				JPanel urGTImgPan = new JPanel();
				urGTImgPan.setOpaque(false);
				add(urGTImgPan, BorderLayout.CENTER);
				urGTImgPan.setLayout(new BorderLayout(0, 0));
				
				JLabel urGTImgLab = new JLabel("(게임이미지)");
				urGTImgLab.setOpaque(true);
				urGTImgLab.setHorizontalAlignment(SwingConstants.CENTER);
				urGTImgPan.add(urGTImgLab);
				
				JPanel panel_18 = new JPanel();
				panel_18.setOpaque(false);
				urGTImgPan.add(panel_18, BorderLayout.NORTH);
				
				JPanel panel_19 = new JPanel();
				panel_19.setOpaque(false);
				urGTImgPan.add(panel_19, BorderLayout.SOUTH);
				
				JPanel urGameTalkPan = new JPanel();
				urGameTalkPan.setOpaque(false);
				add(urGameTalkPan, BorderLayout.EAST);
				urGameTalkPan.setLayout(new BorderLayout(0, 0));
				
				JButton urChatBnt = new JButton("채팅");
				urChatBnt.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {

					}
				});
				urGameTalkPan.add(urChatBnt, BorderLayout.WEST);
//			urTalkBnt.setAlignmentX(Component.CENTER_ALIGNMENT);
				
				JButton urGameBnt = new JButton("게임");
				urGameBnt.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						
						int num = (int)(Math.random()*20);
						String you = "테이블_2";
						/// 상대방 신청
						defaultClient.sendMessage(num, "게임신청", you);
						
						
						/// 수락확인은 아직 모름 ... 됐다 치고 게임 창 열기
						GameAcceptMain gm = new GameAcceptMain(
								defaultClient, tableMainController.clientName, 
								new MessageObject(num,"",you,you));
						
						
						
					}
				});
				urGameTalkPan.add(urGameBnt, BorderLayout.EAST);
				
				JPanel panel_15 = new JPanel();
				panel_15.setOpaque(false);
				urGameTalkPan.add(panel_15, BorderLayout.NORTH);
				
				JPanel panel_16 = new JPanel();
				panel_16.setOpaque(false);
				urGameTalkPan.add(panel_16, BorderLayout.SOUTH);
				
				JPanel panel_24 = new JPanel();
				panel_24.setOpaque(false);
				urGameTalkPan.add(panel_24, BorderLayout.CENTER);
		}
	}
}
