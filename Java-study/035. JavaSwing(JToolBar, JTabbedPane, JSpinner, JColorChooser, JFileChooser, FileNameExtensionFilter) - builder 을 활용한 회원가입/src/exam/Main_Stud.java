package exam;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
 
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.awt.SystemColor;
import java.util.Vector;
 
import javax.swing.UIManager;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JRadioButton;
import java.awt.Button;
import javax.swing.SwingConstants;
 
public class Main_Stud extends JFrame {
 
    private JPanel contentPane;
    private JTextField textField;
    private JTextField textField_2;
    private JTextField textField_3;
    private JTextField textField_4;
    private JTextField textField_1;
    private JTextField textField_5;
    private JTextField textField_6;
    private JTextField textField_7;
    private JTextField textField_8;
    private JTextField textField_9;
    private JTextField textField_10;
    private JTextField textField_11;
    private JTextField textField_12;
 
    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                	Main_Stud frame = new Main_Stud();
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
    public Main_Stud() {
        setTitle("MOCOBLING");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(500, 30, 900, 1000);
        contentPane = new JPanel();
        contentPane.setLayout(null);
        contentPane.setBackground(Color.WHITE);
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        
        JLabel lblNewLabel = new JLabel("JOIN US");
        lblNewLabel.setBounds(387, 22, 107, 30);
        lblNewLabel.setFont(new Font("나눔스퀘어 ExtraBold", Font.BOLD, 26));
        lblNewLabel.setForeground(new Color(51, 51, 51));
        lblNewLabel.setBackground(Color.WHITE);
        contentPane.add(lblNewLabel);
        
        JLabel lblNewLabel_1 = new JLabel("\uBAA8\uCF54\uBE14\uB9C1 "
                + "\uD68C\uC6D0\uAC00\uC785\uC73C\uB85C \uB2E4\uC591"
                + "\uD55C \uD574\uD0DD\uC744 \uB204\uB9AC\uC138\uC694.");
        lblNewLabel_1.setBounds(313, 63, 256, 18);
        lblNewLabel_1.setFont(new Font("나눔바른고딕", Font.PLAIN, 13));
        lblNewLabel_1.setForeground(Color.GRAY);
        contentPane.add(lblNewLabel_1);
        
        JPanel panel_1 = new JPanel();
        panel_1.setBounds(42, 107, 800, 100);
        panel_1.setLayout(null);
        panel_1.setBorder(new LineBorder(Color.LIGHT_GRAY, 1, true));
        panel_1.setBackground(Color.WHITE);
        contentPane.add(panel_1);
        
        JLabel lblNewLabel_4 
        = new JLabel("\uD734\uB300\uD3F0 \uC778\uC99D\uC774\uB780?");
        lblNewLabel_4.setBounds(352, 60, 96, 20);
        lblNewLabel_4.setForeground(Color.RED);
        lblNewLabel_4.setFont(new Font("나눔바른고딕", Font.PLAIN, 13));
        panel_1.add(lblNewLabel_4);
        
        JPanel panel_3 = new JPanel();
        panel_3.setBounds(1, 17, 798, 36);
        panel_3.setLayout(null);
        panel_3.setBackground(Color.WHITE);
        panel_1.add(panel_3);
        
        JLabel lblNewLabel_2 = new JLabel("\uBCF8\uC778 \uD655\uC778");
        lblNewLabel_2.setBounds(61, 10, 52, 17);
        lblNewLabel_2.setBackground(Color.WHITE);
        lblNewLabel_2.setForeground(UIManager.getColor("CheckBox.darkShadow"));
        lblNewLabel_2.setFont(new Font("나눔바른고딕", Font.BOLD, 13));
        panel_3.add(lblNewLabel_2);
        
        JLabel lblNewLabel_3 = new JLabel("\uD734\uB300\uD3F0 \uC778\uC99D"
                + "\uC744 \uD1B5\uD558\uC5EC \uBCF8\uC778\uC778\uC99D\uC744"
                + " \uD558\uC154\uC57C \uD569\uB2C8\uB2E4. \uC778\uC99D "
                + "\uBC84\uD2BC\uC744 \uB20C\uB7EC\uBCF8\uC778\uC778\uC99D"
                + "\uC744 \uC9C4\uD589\uD574 \uC8FC\uC138\uC694.");
        lblNewLabel_3.setBounds(132, 10, 506, 17);
        lblNewLabel_3.setForeground(Color.GRAY);
        lblNewLabel_3.setFont(new Font("나눔바른고딕", Font.PLAIN, 13));
        panel_3.add(lblNewLabel_3);
        
        Button button_1_1_1 = new Button("\uD734\uB300\uD3F0 \uC778\uC99D");
        button_1_1_1.setBounds(643, 0, 94, 36);
        panel_3.add(button_1_1_1);
        button_1_1_1.setForeground(Color.DARK_GRAY);
        button_1_1_1.setFont(new Font("나눔바른고딕", Font.PLAIN, 14));
        button_1_1_1.setBackground(Color.WHITE);
        
        JPanel panel_2 = new JPanel();
        panel_2.setBounds(42, 242, 800, 610);
        panel_2.setLayout(null);
        panel_2.setBackground(Color.WHITE);
        panel_2.setBorder(new LineBorder(Color.LIGHT_GRAY, 1, true));
        contentPane.add(panel_2);
        
        JPanel panel = new JPanel();
        panel.setBackground(Color.WHITE);
        panel.setBounds(35, 35, 730, 540);
        panel_2.add(panel);
        panel.setLayout(null);
        
        JLabel lblNewLabel_5 = new JLabel("\uC774\uB984");
        lblNewLabel_5.setBounds(0, 7, 26, 18);
        panel.add(lblNewLabel_5);
        lblNewLabel_5.setForeground(Color.DARK_GRAY);
        lblNewLabel_5.setBackground(Color.WHITE);
        lblNewLabel_5.setFont(new Font("나눔바른고딕", Font.BOLD, 15));
        
        textField = new JTextField();
        textField.setBounds(140, 0, 260, 30);
        panel.add(textField);
        textField.setColumns(10);
        
        JPanel panel_4 = new JPanel();
        panel_4.setBackground(Color.LIGHT_GRAY);
        panel_4.setBounds(0, 38, 730, 1);
        panel.add(panel_4);
        panel_4.setLayout(null);
        
        JPanel panel_4_1 = new JPanel();
        panel_4_1.setLayout(null);
        panel_4_1.setBackground(Color.LIGHT_GRAY);
        panel_4_1.setBounds(0, 85, 730, 1);
        panel.add(panel_4_1);
        
        JLabel lblNewLabel_5_1 = new JLabel("\uC0DD\uB144\uC6D4\uC77C");
        lblNewLabel_5_1.setForeground(Color.DARK_GRAY);
        lblNewLabel_5_1.setFont(new Font("나눔바른고딕", Font.BOLD, 15));
        lblNewLabel_5_1.setBackground(Color.WHITE);
        lblNewLabel_5_1.setBounds(0, 54, 55, 18);
        panel.add(lblNewLabel_5_1);
        
        JPanel panel_4_1_1 = new JPanel();
        panel_4_1_1.setLayout(null);
        panel_4_1_1.setBackground(Color.LIGHT_GRAY);
        panel_4_1_1.setBounds(0, 133, 730, 1);
        panel.add(panel_4_1_1);
        
        textField_2 = new JTextField();
        textField_2.setColumns(10);
        textField_2.setBounds(140, 95, 260, 30);
        panel.add(textField_2);
        
        JLabel lblNewLabel_5_1_1 = new JLabel("\uC544\uC774\uB514");
        lblNewLabel_5_1_1.setForeground(Color.DARK_GRAY);
        lblNewLabel_5_1_1.setFont(new Font("나눔바른고딕", Font.BOLD, 15));
        lblNewLabel_5_1_1.setBackground(Color.WHITE);
        lblNewLabel_5_1_1.setBounds(0, 102, 50, 18);
        panel.add(lblNewLabel_5_1_1);
        
        JPanel panel_4_1_1_1 = new JPanel();
        panel_4_1_1_1.setLayout(null);
        panel_4_1_1_1.setBackground(Color.LIGHT_GRAY);
        panel_4_1_1_1.setBounds(0, 181, 730, 1);
        panel.add(panel_4_1_1_1);
        
        textField_3 = new JTextField();
        textField_3.setColumns(10);
        textField_3.setBounds(140, 143, 260, 30);
        panel.add(textField_3);
        
        JLabel lblNewLabel_5_1_1_1 = new JLabel("\uBE44\uBC00\uBC88\uD638");
        lblNewLabel_5_1_1_1.setForeground(Color.DARK_GRAY);
        lblNewLabel_5_1_1_1.setFont(new Font("나눔바른고딕", Font.BOLD, 15));
        lblNewLabel_5_1_1_1.setBackground(Color.WHITE);
        lblNewLabel_5_1_1_1.setBounds(0, 149, 55, 18);
        panel.add(lblNewLabel_5_1_1_1);
        
        JPanel panel_4_1_1_1_1 = new JPanel();
        panel_4_1_1_1_1.setLayout(null);
        panel_4_1_1_1_1.setBackground(Color.LIGHT_GRAY);
        panel_4_1_1_1_1.setBounds(0, 229, 730, 1);
        panel.add(panel_4_1_1_1_1);
        
        textField_4 = new JTextField();
        textField_4.setColumns(10);
        textField_4.setBounds(140, 190, 260, 30);
        panel.add(textField_4);
        
        JLabel lblNewLabel_5_1_1_1_1 
        = new JLabel("\uBE44\uBC00\uBC88\uD638\uD655\uC778");
        lblNewLabel_5_1_1_1_1.setForeground(Color.DARK_GRAY);
        lblNewLabel_5_1_1_1_1.setFont(new Font("나눔바른고딕", Font.BOLD, 15));
        lblNewLabel_5_1_1_1_1.setBackground(Color.WHITE);
        lblNewLabel_5_1_1_1_1.setBounds(0, 196, 80, 18);
        panel.add(lblNewLabel_5_1_1_1_1);
        
        
        Vector<String> year = new Vector<String>();
        year.add("선택");
        for (int i = 1920; i < 2021; i++) {
            year.add(Integer.toString(i));
        }
        
        JComboBox comboBox = new JComboBox();
        comboBox.setForeground(Color.DARK_GRAY);
        comboBox.setFont(new Font("나눔고딕", Font.PLAIN, 15));
        comboBox.setModel(new DefaultComboBoxModel(year));
        comboBox.setSelectedItem("선택");
        comboBox.setEditable(true);
        comboBox.setBackground(Color.WHITE);
        comboBox.setBounds(140, 47, 65, 30);
        panel.add(comboBox);
        
        JLabel lblNewLabel_5_1_2 = new JLabel("\uB144");
        lblNewLabel_5_1_2.setForeground(Color.GRAY);
        lblNewLabel_5_1_2.setFont(new Font("나눔바른고딕", Font.PLAIN, 15));
        lblNewLabel_5_1_2.setBackground(Color.WHITE);
        lblNewLabel_5_1_2.setBounds(209, 54, 15, 18);
        panel.add(lblNewLabel_5_1_2);
        
        JLabel lblNewLabel_5_1_2_1 = new JLabel("\uC6D4");
        lblNewLabel_5_1_2_1.setForeground(Color.GRAY);
        lblNewLabel_5_1_2_1.setFont(new Font("나눔바른고딕", Font.PLAIN, 15));
        lblNewLabel_5_1_2_1.setBackground(Color.WHITE);
        lblNewLabel_5_1_2_1.setBounds(295, 54, 15, 18);
        panel.add(lblNewLabel_5_1_2_1);
        
        Vector<String> month = new Vector<String>();
        month.add("선택");
        for (int i = 1; i < 13; i++) {
            month.add(Integer.toString(i));
        }
        
        JComboBox comboBox_1 = new JComboBox();
        comboBox_1.setModel(new DefaultComboBoxModel(month));
        comboBox_1.setSelectedItem("선택");
        comboBox_1.setForeground(Color.DARK_GRAY);
        comboBox_1.setFont(new Font("나눔고딕", Font.PLAIN, 15));
        comboBox_1.setEditable(true);
        comboBox_1.setBackground(Color.WHITE);
        comboBox_1.setBounds(226, 47, 65, 30);
        panel.add(comboBox_1);
        
        Vector<String> day = new Vector<String>();
        day.add("선택");
        for (int i = 1; i < 32; i++) {
            day.add(Integer.toString(i));
        }
        
        JComboBox comboBox_1_1 = new JComboBox();
        comboBox_1_1.setModel(new DefaultComboBoxModel(day));
        comboBox_1_1.setSelectedItem("선택");
        comboBox_1_1.setForeground(Color.DARK_GRAY);
        comboBox_1_1.setFont(new Font("나눔고딕", Font.PLAIN, 15));
        comboBox_1_1.setEditable(true);
        comboBox_1_1.setBackground(Color.WHITE);
        comboBox_1_1.setBounds(313, 47, 65, 30);
        panel.add(comboBox_1_1);
        
        JLabel lblNewLabel_5_1_2_1_1 = new JLabel("\uC77C");
        lblNewLabel_5_1_2_1_1.setForeground(Color.GRAY);
        lblNewLabel_5_1_2_1_1.setFont(new Font("나눔바른고딕", Font.PLAIN, 15));
        lblNewLabel_5_1_2_1_1.setBackground(Color.WHITE);
        lblNewLabel_5_1_2_1_1.setBounds(383, 54, 15, 18);
        panel.add(lblNewLabel_5_1_2_1_1);
        
        ButtonGroup bg1 = new ButtonGroup();
        JRadioButton rdbtnNewRadioButton = new JRadioButton("\uB0A8");
        rdbtnNewRadioButton.setForeground(Color.GRAY);
        rdbtnNewRadioButton.setFont(new Font("나눔바른고딕", Font.PLAIN, 15));
        rdbtnNewRadioButton.setBackground(Color.WHITE);
        rdbtnNewRadioButton.setBounds(431, 49, 60, 27);
        panel.add(rdbtnNewRadioButton);
        
        JRadioButton rdbtnNewRadioButton_1 = new JRadioButton("\uC5EC");
        rdbtnNewRadioButton_1.setSelected(true);
        rdbtnNewRadioButton_1.setForeground(Color.GRAY);
        rdbtnNewRadioButton_1.setFont(new Font("나눔바른고딕", Font.PLAIN, 15));
        rdbtnNewRadioButton_1.setBackground(Color.WHITE);
        rdbtnNewRadioButton_1.setBounds(490, 49, 60, 27);
        panel.add(rdbtnNewRadioButton_1);
        bg1.add(rdbtnNewRadioButton);
        bg1.add(rdbtnNewRadioButton_1);
        
        Button button = new Button("\uC911\uBCF5\uD655\uC778");
        button.setForeground(Color.WHITE);
        button.setBackground(Color.DARK_GRAY);
        button.setFont(new Font("나눔바른고딕", Font.PLAIN, 14));
        button.setBounds(410, 95, 75, 30);
        panel.add(button);
        
        JLabel lblNewLabel_3_1 = new JLabel("4~20\uC790\uC758 \uC601, "
                + "\uC22B\uC790\uB9CC \uC0AC\uC6A9\uD560 \uC218 \uC788"
                + "\uC2B5\uB2C8\uB2E4.");
        lblNewLabel_3_1.setBackground(Color.WHITE);
        lblNewLabel_3_1.setForeground(Color.GRAY);
        lblNewLabel_3_1.setFont(new Font("나눔바른고딕", Font.PLAIN, 12));
        lblNewLabel_3_1.setBounds(495, 102, 208, 17);
        panel.add(lblNewLabel_3_1);
        
        JLabel lblNewLabel_5_1_3 = new JLabel("\uD734\uB300\uC804\uD654");
        lblNewLabel_5_1_3.setForeground(Color.DARK_GRAY);
        lblNewLabel_5_1_3.setFont(new Font("나눔바른고딕", Font.BOLD, 15));
        lblNewLabel_5_1_3.setBackground(Color.WHITE);
        lblNewLabel_5_1_3.setBounds(0, 245, 55, 18);
        panel.add(lblNewLabel_5_1_3);
        
        JComboBox comboBox_2 = new JComboBox();
        comboBox_2.setModel(new DefaultComboBoxModel
                (new String[] {"\uC120\uD0DD",
                "010", "011", "016", "017", "018", "019"}));
        comboBox_2.setForeground(Color.DARK_GRAY);
        comboBox_2.setFont(new Font("나눔고딕", Font.PLAIN, 15));
        comboBox_2.setEditable(true);
        comboBox_2.setBackground(Color.WHITE);
        comboBox_2.setBounds(140, 238, 60, 30);
        panel.add(comboBox_2);
        
        JLabel lblNewLabel_5_1_2_2 = new JLabel("-");
        lblNewLabel_5_1_2_2.setForeground(Color.GRAY);
        lblNewLabel_5_1_2_2.setFont(new Font("나눔바른고딕", Font.PLAIN, 15));
        lblNewLabel_5_1_2_2.setBackground(Color.WHITE);
        lblNewLabel_5_1_2_2.setBounds(203, 245, 11, 18);
        panel.add(lblNewLabel_5_1_2_2);
        
        JPanel panel_4_1_1_1_1_1 = new JPanel();
        panel_4_1_1_1_1_1.setLayout(null);
        panel_4_1_1_1_1_1.setBackground(Color.LIGHT_GRAY);
        panel_4_1_1_1_1_1.setBounds(0, 300, 730, 1);
        panel.add(panel_4_1_1_1_1_1);
        
        textField_1 = new JTextField();
        textField_1.setColumns(10);
        textField_1.setBounds(215, 238, 80, 30);
        panel.add(textField_1);
        
        JLabel lblNewLabel_5_1_2_2_1 = new JLabel("-");
        lblNewLabel_5_1_2_2_1.setForeground(Color.GRAY);
        lblNewLabel_5_1_2_2_1.setFont(new Font("나눔바른고딕", Font.PLAIN, 15));
        lblNewLabel_5_1_2_2_1.setBackground(Color.WHITE);
        lblNewLabel_5_1_2_2_1.setBounds(298, 245, 11, 18);
        panel.add(lblNewLabel_5_1_2_2_1);
        
        textField_5 = new JTextField();
        textField_5.setColumns(10);
        textField_5.setBounds(310, 238, 80, 30);
        panel.add(textField_5);
        
        JRadioButton rdbtnNewRadioButton_1_1 
        = new JRadioButton("\uBC1B\uC9C0 \uC54A\uC2B5\uB2C8\uB2E4.");
        rdbtnNewRadioButton_1_1.setSelected(true);
        rdbtnNewRadioButton_1_1.setForeground(Color.GRAY);
        rdbtnNewRadioButton_1_1.setFont(new Font("나눔바른고딕", Font.PLAIN, 15));
        rdbtnNewRadioButton_1_1.setBackground(Color.WHITE);
        rdbtnNewRadioButton_1_1.setBounds(246, 275, 114, 20);
        panel.add(rdbtnNewRadioButton_1_1);
        
        JRadioButton rdbtnNewRadioButton_2 
        = new JRadioButton("\uBC1B\uC2B5\uB2C8\uB2E4.");
        rdbtnNewRadioButton_2.setForeground(Color.GRAY);
        rdbtnNewRadioButton_2.setFont(new Font("나눔바른고딕", Font.PLAIN, 15));
        rdbtnNewRadioButton_2.setBackground(Color.WHITE);
        rdbtnNewRadioButton_2.setBounds(136, 275, 100, 20);
        panel.add(rdbtnNewRadioButton_2);
        
        JLabel lblNewLabel_3_1_1 = new JLabel("*sms\uC218\uC2E0\uC744"
                + " \uBC1B\uC9C0 \uC54A\uC744 \uC2DC '\uC785\uACE0"
                + "\uC9C0\uC5F0/\uD488\uC808 \uC548\uB0B4 \uBD88\uAC00'");
        lblNewLabel_3_1_1.setForeground(Color.GRAY);
        lblNewLabel_3_1_1.setFont(new Font("나눔바른고딕", Font.PLAIN, 13));
        lblNewLabel_3_1_1.setBackground(Color.WHITE);
        lblNewLabel_3_1_1.setBounds(366, 277, 285, 17);
        panel.add(lblNewLabel_3_1_1);
        
        JLabel lblNewLabel_5_1_3_1 = new JLabel("SMS\uC218\uC2E0");
        lblNewLabel_5_1_3_1.setForeground(Color.DARK_GRAY);
        lblNewLabel_5_1_3_1.setFont(new Font("나눔바른고딕", Font.BOLD, 15));
        lblNewLabel_5_1_3_1.setBackground(Color.WHITE);
        lblNewLabel_5_1_3_1.setBounds(0, 276, 65, 18);
        panel.add(lblNewLabel_5_1_3_1);
        
        JPanel panel_4_1_1_1_1_1_1 = new JPanel();
        panel_4_1_1_1_1_1_1.setLayout(null);
        panel_4_1_1_1_1_1_1.setBackground(Color.LIGHT_GRAY);
        panel_4_1_1_1_1_1_1.setBounds(0, 375, 730, 1);
        panel.add(panel_4_1_1_1_1_1_1);
        
        JLabel lblNewLabel_5_1_3_1_1 
        = new JLabel("\uC774\uBA54\uC77C \uC218\uC2E0\uC5EC\uBD80");
        lblNewLabel_5_1_3_1_1.setForeground(Color.DARK_GRAY);
        lblNewLabel_5_1_3_1_1.setFont(new Font("나눔바른고딕", Font.BOLD, 15));
        lblNewLabel_5_1_3_1_1.setBackground(Color.WHITE);
        lblNewLabel_5_1_3_1_1.setBounds(0, 348, 100, 18);
        panel.add(lblNewLabel_5_1_3_1_1);
        
        JLabel lblNewLabel_5_1_3_2
        = new JLabel("\uC774\uBA54\uC77C \uC8FC\uC18C");
        lblNewLabel_5_1_3_2.setForeground(Color.DARK_GRAY);
        lblNewLabel_5_1_3_2.setFont(new Font("나눔바른고딕", Font.BOLD, 15));
        lblNewLabel_5_1_3_2.setBackground(Color.WHITE);
        lblNewLabel_5_1_3_2.setBounds(0, 316, 75, 18);
        panel.add(lblNewLabel_5_1_3_2);
        
        textField_6 = new JTextField();
        textField_6.setColumns(10);
        textField_6.setBounds(140, 309, 185, 30);
        panel.add(textField_6);
        
        JLabel lblNewLabel_5_1_2_2_1_1 = new JLabel("@");
        lblNewLabel_5_1_2_2_1_1.setForeground(Color.GRAY);
        lblNewLabel_5_1_2_2_1_1.setFont(new Font("나눔바른고딕", Font.PLAIN, 15));
        lblNewLabel_5_1_2_2_1_1.setBackground(Color.WHITE);
        lblNewLabel_5_1_2_2_1_1.setBounds(327, 316, 17, 18);
        panel.add(lblNewLabel_5_1_2_2_1_1);
        
        textField_7 = new JTextField();
        textField_7.setColumns(10);
        textField_7.setBounds(346, 309, 185, 30);
        panel.add(textField_7);
        
        JRadioButton rdbtnNewRadioButton_2_1 
        = new JRadioButton("\uBC1B\uC2B5\uB2C8\uB2E4.");
        rdbtnNewRadioButton_2_1.setForeground(Color.GRAY);
        rdbtnNewRadioButton_2_1.setFont(new Font("나눔바른고딕", Font.PLAIN, 15));
        rdbtnNewRadioButton_2_1.setBackground(Color.WHITE);
        rdbtnNewRadioButton_2_1.setBounds(136, 346, 100, 20);
        panel.add(rdbtnNewRadioButton_2_1);
        
        JRadioButton rdbtnNewRadioButton_1_1_1 
        = new JRadioButton("\uBC1B\uC9C0 \uC54A\uC2B5\uB2C8\uB2E4.");
        rdbtnNewRadioButton_1_1_1.setSelected(true);
        rdbtnNewRadioButton_1_1_1.setForeground(Color.GRAY);
        rdbtnNewRadioButton_1_1_1.setFont(new Font("나눔바른고딕", Font.PLAIN, 15));
        rdbtnNewRadioButton_1_1_1.setBackground(Color.WHITE);
        rdbtnNewRadioButton_1_1_1.setBounds(246, 346, 114, 20);
        panel.add(rdbtnNewRadioButton_1_1_1);
        
        JLabel lblNewLabel_3_1_1_1 
        = new JLabel("\uBC1B\uAE30\uB97C \uC120\uD0DD\uD558\uC2DC\uBA74 "
                + "\uBC30\uC1A1 \uC815\uBCF4, \uC774\uBCA4\uD2B8 \uBA54"
                + "\uC77C\uC744 \uC218\uC2E0\uD569\uB2C8\uB2E4.");
        lblNewLabel_3_1_1_1.setForeground(Color.GRAY);
        lblNewLabel_3_1_1_1.setFont(new Font("나눔바른고딕", Font.PLAIN, 13));
        lblNewLabel_3_1_1_1.setBackground(Color.WHITE);
        lblNewLabel_3_1_1_1.setBounds(366, 348, 310, 17);
        panel.add(lblNewLabel_3_1_1_1);
        
        Button button_1 = new Button("\uC911\uBCF5\uD655\uC778");
        button_1.setForeground(Color.WHITE);
        button_1.setFont(new Font("나눔바른고딕", Font.PLAIN, 14));
        button_1.setBackground(Color.DARK_GRAY);
        button_1.setBounds(655, 309, 75, 30);
        panel.add(button_1);
        
        JComboBox comboBox_3 = new JComboBox();
        comboBox_3.setModel(new DefaultComboBoxModel
                (new String[] {"\uC9C1\uC811\uC785\uB825", "naver.com",
                        "hotmail.com", "hanmail.net", "yahoo.com", "nate.com",
                        "korea.com", "chol.com", "gmail.com", "netian.com"}));
        comboBox_3.setForeground(Color.DARK_GRAY);
        comboBox_3.setFont(new Font("나눔고딕", Font.PLAIN, 15));
        comboBox_3.setEditable(true);
        comboBox_3.setBackground(Color.WHITE);
        comboBox_3.setBounds(535, 309, 115, 30);
        panel.add(comboBox_3);
        
        JLabel lblNewLabel_5_1_3_3 = new JLabel("\uC8FC\uC18C");
        lblNewLabel_5_1_3_3.setForeground(Color.DARK_GRAY);
        lblNewLabel_5_1_3_3.setFont(new Font("나눔바른고딕", Font.BOLD, 15));
        lblNewLabel_5_1_3_3.setBackground(Color.WHITE);
        lblNewLabel_5_1_3_3.setBounds(0, 391, 55, 18);
        panel.add(lblNewLabel_5_1_3_3);
        
        textField_8 = new JTextField();
        textField_8.setColumns(10);
        textField_8.setBounds(140, 384, 90, 30);
        panel.add(textField_8);
        
        JLabel lblNewLabel_5_1_2_2_1_2 = new JLabel("-");
        lblNewLabel_5_1_2_2_1_2.setForeground(Color.GRAY);
        lblNewLabel_5_1_2_2_1_2.setFont(new Font("나눔바른고딕", Font.PLAIN, 15));
        lblNewLabel_5_1_2_2_1_2.setBackground(Color.WHITE);
        lblNewLabel_5_1_2_2_1_2.setBounds(232, 391, 11, 18);
        panel.add(lblNewLabel_5_1_2_2_1_2);
        
        textField_9 = new JTextField();
        textField_9.setColumns(10);
        textField_9.setBounds(244, 384, 90, 30);
        panel.add(textField_9);
        
        Button button_1_1 = new Button("\uC6B0\uD3B8\uBC88\uD638\uAC80\uC0C9");
        button_1_1.setForeground(Color.DARK_GRAY);
        button_1_1.setFont(new Font("나눔바른고딕", Font.PLAIN, 14));
        button_1_1.setBackground(Color.WHITE);
        button_1_1.setBounds(342, 384, 100, 30);
        panel.add(button_1_1);
        
        JLabel lblNewLabel_5_1_1_1_2 = new JLabel("\uBE44\uBC00\uBC88\uD638");
        lblNewLabel_5_1_1_1_2.setForeground(Color.DARK_GRAY);
        lblNewLabel_5_1_1_1_2.setFont(new Font("나눔바른고딕", Font.BOLD, 15));
        lblNewLabel_5_1_1_1_2.setBackground(Color.WHITE);
        lblNewLabel_5_1_1_1_2.setBounds(0, 427, 55, 18);
        panel.add(lblNewLabel_5_1_1_1_2);
        
        textField_10 = new JTextField();
        textField_10.setColumns(10);
        textField_10.setBounds(140, 421, 330, 30);
        panel.add(textField_10);
        
        textField_11 = new JTextField();
        textField_11.setColumns(10);
        textField_11.setBounds(140, 458, 330, 30);
        panel.add(textField_11);
        
        JLabel lblNewLabel_3_1_1_1_1 = new JLabel("[\uAE30\uBCF8\uC8FC\uC18C]");
        lblNewLabel_3_1_1_1_1.setHorizontalAlignment(SwingConstants.LEFT);
        lblNewLabel_3_1_1_1_1.setForeground(Color.GRAY);
        lblNewLabel_3_1_1_1_1.setFont(new Font("나눔바른고딕", Font.BOLD, 14));
        lblNewLabel_3_1_1_1_1.setBackground(Color.WHITE);
        lblNewLabel_3_1_1_1_1.setBounds(476, 428, 61, 17);
        panel.add(lblNewLabel_3_1_1_1_1);
        
        JLabel lblNewLabel_3_1_1_1_1_1 = new JLabel("[\uC0C1\uC138\uC8FC\uC18C]");
        lblNewLabel_3_1_1_1_1_1.setHorizontalAlignment(SwingConstants.LEFT);
        lblNewLabel_3_1_1_1_1_1.setForeground(Color.GRAY);
        lblNewLabel_3_1_1_1_1_1.setFont(new Font("나눔바른고딕", Font.BOLD, 14));
        lblNewLabel_3_1_1_1_1_1.setBackground(Color.WHITE);
        lblNewLabel_3_1_1_1_1_1.setBounds(476, 465, 61, 17);
        panel.add(lblNewLabel_3_1_1_1_1_1);
        
        JLabel lblNewLabel_5_1_1_2 = new JLabel("\uCD94\uCC9C \uC544\uC774\uB514");
        lblNewLabel_5_1_1_2.setForeground(Color.DARK_GRAY);
        lblNewLabel_5_1_1_2.setFont(new Font("나눔바른고딕", Font.BOLD, 15));
        lblNewLabel_5_1_1_2.setBackground(Color.WHITE);
        lblNewLabel_5_1_1_2.setBounds(0, 517, 70, 18);
        panel.add(lblNewLabel_5_1_1_2);
        
        textField_12 = new JTextField();
        textField_12.setColumns(10);
        textField_12.setBounds(140, 510, 240, 30);
        panel.add(textField_12);
        
        Button button_2 
        = new Button("\uCD94\uCC9C\uC778 \uC544\uC774\uB514 \uAC80\uC0C9");
        button_2.setForeground(Color.DARK_GRAY);
        button_2.setFont(new Font("나눔바른고딕", Font.PLAIN, 14));
        button_2.setBackground(Color.WHITE);
        button_2.setBounds(387, 510, 140, 30);
        panel.add(button_2);
        
        JLabel lblNewLabel_3_1_2 
        = new JLabel("\uBCF8\uC778\uC744 \uCD94\uCC9C\uC778\uC73C\uB85C "
                + "\uD560 \uC218 \uC5C6\uC2B5\uB2C8\uB2E4.");
        lblNewLabel_3_1_2.setForeground(Color.GRAY);
        lblNewLabel_3_1_2.setFont(new Font("나눔바른고딕", Font.PLAIN, 12));
        lblNewLabel_3_1_2.setBackground(Color.WHITE);
        lblNewLabel_3_1_2.setBounds(535, 517, 173, 17);
        panel.add(lblNewLabel_3_1_2);
        
        JPanel panel_4_1_1_1_1_1_1_1 = new JPanel();
        panel_4_1_1_1_1_1_1_1.setLayout(null);
        panel_4_1_1_1_1_1_1_1.setBackground(Color.LIGHT_GRAY);
        panel_4_1_1_1_1_1_1_1.setBounds(0, 498, 730, 1);
        panel.add(panel_4_1_1_1_1_1_1_1);
        
        JPanel panel_5 = new JPanel();
        panel_5.setBackground(Color.WHITE);
        panel_5.setBounds(323, 872, 235, 60);
        contentPane.add(panel_5);
        panel_5.setLayout(null);
        
        Button button_1_2 = new Button("\uD68C\uC6D0\uAC00\uC785");
        button_1_2.setForeground(Color.WHITE);
        button_1_2.setFont(new Font("나눔바른고딕", Font.PLAIN, 17));
        button_1_2.setBackground(Color.DARK_GRAY);
        button_1_2.setBounds(0, 0, 115, 60);
        panel_5.add(button_1_2);
        
        Button button_1_2_1 = new Button("\uAC00\uC785\uCDE8\uC18C");
        button_1_2_1.setForeground(Color.DARK_GRAY);
        button_1_2_1.setFont(new Font("나눔바른고딕", Font.PLAIN, 17));
        button_1_2_1.setBackground(Color.WHITE);
        button_1_2_1.setBounds(121, 0, 115, 60);
        panel_5.add(button_1_2_1);
    }
}