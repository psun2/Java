package exam;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.util.Arrays;
 
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
 
/*
 * 
 * 패널을 나눠서 패널에만 그려지도록
 * 
 * 브러시 사이즈 조절 가능하게
 * 
 * 네모 , 별까지 
 * 
 * 색깔 조합까지
 * 
 */
public class Main_Stude extends JFrame implements MouseMotionListener {
 
    int num=0;
    int [] make_cc;
    //= new int[4];
    
    @Override
    public void mouseDragged(MouseEvent e) {
        
        System.out.println(e.getModifiers()+","+e.getModifiersEx());
        
        if(e.getModifiers()==MouseEvent.BUTTON1_MASK) {
            cc = front;
        }else if(e.getModifiers()==MouseEvent.BUTTON3_MASK) {
            cc = Color.white;
        }
        if(e.getY()>670) {
            x = e.getX()-10;
            y = 670;
        }
        else {
            x = e.getX()-10;
            y = e.getY()-10;
        }
        
        
        repaint();//paint() 호출
    }
 
    @Override
    public void mouseMoved(MouseEvent e) {
        System.out.println(Arrays.toString(make_cc));
    }
    
    public Main_Stude() {
        
 
        
        Color [] cc = {
                new Color(255, 0, 0),
                new Color(255, 100, 0),
                new Color(255, 255, 0),
                new Color(0, 255, 0),
                new Color(0, 0, 255),
                new Color(0, 50, 150),
                new Color(200, 0, 255),
                new Color(0, 0, 0)
        };
        
        setBounds(0,50,620,800);
        setTitle("CharPaint");
        setLayout(new BoxLayout(getContentPane(),BoxLayout.Y_AXIS));
        
        JPanel jj = new JPanel();
        jj.setSize(600,600);
        jj.setLayout(null);
        add(jj);
        
        JPanel jp = new JPanel();
        jp.setBounds(0,0,600,600);
        jp.setBackground(Color.yellow);
        jp.setLayout(null);
        
        jj.add(jp);
        
        JPanel jp1 = new JPanel();
        jp1.setBounds(0,650,600,50);
        jp1.setLayout(new GridLayout());
 
        JButton jb = new JButton("지우기");
        jp1.add(jb);
        
        jb.addActionListener(new ActionListener() {
            
            @Override
            public void actionPerformed(ActionEvent e) {
                first=true;
                x=-100;
                y=-100;
                repaint();
            }
        });
        
        
        
        JButton jb0 = new JButton("색조합");
        jp1.add(jb0);
        
        jb0.addActionListener(new ActionListener() {
            
            @Override
            public void actionPerformed(ActionEvent e) {
                    JFrame jf = new JFrame();
                    make_cc = new int[4];
                    jf.setBounds(200,600,600,400);
                    jf.setLayout(new GridLayout(5,1));
                    
//                    JLabel jll = new JLabel("원하는 색을 입력하세요");
//                    jf.add(jll);
                    
                    JTextField tf = new JTextField("Red의 값을 입력하세요.(최소 0, 최대 255)");
                    tf.setBounds(50, 50, 200, 100);
                    jf.add(tf);
                    
                    JTextField tf2 = new JTextField("Green의 값을 입력하세요.(최소 0, 최대 255)");
                    tf.setBounds(50, 50, 200, 100);
                    jf.add(tf2);
                    
                    JTextField tf3 = new JTextField("Blue의 값을 입력하세요.(최소 0, 최대 255)");
                    tf.setBounds(50, 50, 200, 100);
                    jf.add(tf3);
                    
                    JTextField tf4 = new JTextField("명도값을 입력하세요.(최소 0, 최대 255)");
                    tf.setBounds(50, 50, 200, 100);
                    jf.add(tf4);
                    
                    JButton btn = new JButton("완료");
                    tf.setBounds(50, 50, 200, 100);
                    jf.add(btn);
                    
                    
                    btn.addActionListener(new ActionListener() {
                        
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            make_cc[0]=Integer.parseInt(tf.getText());
                            make_cc[1]=Integer.parseInt(tf2.getText());
                            make_cc[2]=Integer.parseInt(tf3.getText());
                            make_cc[3]=Integer.parseInt(tf4.getText());
                            
                            jf.setVisible(false);
                            for (int i = 0; i < make_cc.length; i++) {
                                if(make_cc[i]<0) {
                                    make_cc[i]=0;
                                }
                                else if(make_cc[i]>255) {
                                    make_cc[i]=255;
                                }
                            }
                            
                            front = new Color(make_cc[0],make_cc[1],make_cc[2],make_cc[3]);
                        }
                    });
                    
                    jf.setVisible(true);
                    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                
            }
        });
        
        
        JButton jb1 = new JButton("키우기");
        jp1.add(jb1);
        
        jb1.addActionListener(new ActionListener() {
            
            @Override
            public void actionPerformed(ActionEvent e) {
                size+=5;
            }
        });
        
        JButton jb2 = new JButton("줄이기");
        jp1.add(jb2);
        
        jb2.addActionListener(new ActionListener() {
            
            @Override
            public void actionPerformed(ActionEvent e) {
                size-=5;
                if(size==0) //최소 사이즈 지정;    
                    size=5;
            }
        });
        
        
        JButton jb3 = new JButton("■");
        jp1.add(jb3);
        
        jb3.addActionListener(new ActionListener() {
            
            @Override
            public void actionPerformed(ActionEvent e) {
                choi=1;
                
            }
        });
        
        JButton jb4 = new JButton("▲");
        jp1.add(jb4);
        
        jb4.addActionListener(new ActionListener() {
            
            @Override
            public void actionPerformed(ActionEvent e) {
                choi=2;
                
            }
        });
        
        JButton jb5 = new JButton("★");
        jp1.add(jb5);
        
        jb5.addActionListener(new ActionListener() {
            
            @Override
            public void actionPerformed(ActionEvent e) {
                choi=3;
                
            }
        });
        
        JButton jb6 = new JButton("●");
        jp1.add(jb6);
        
        jb6.addActionListener(new ActionListener() {
            
            @Override
            public void actionPerformed(ActionEvent e) {
                choi =0;
            }
        });
        
        
        jj.add(jp1);
        
        JPanel jp2 = new JPanel();
        jp2.setBounds(0,700,600,100);
//        jp3.setLayout(new FlowLayout());
        jp2.setLayout(new GridLayout(1,7));
        
        jj.add(jp2);
        
        int i=0;
        for (Color color : cc) {
            JButton btn = new JButton();
            btn.setBackground(color);
            btn.setBounds(i*50+10, 500, 20, 300);
            
            jp2.add(btn);
            i++;
            btn.addActionListener(new ActionListener() {
                
                @Override
                public void actionPerformed(ActionEvent e) {
                    // TODO Auto-generated method stub
                    front = ((JButton)e.getSource()).getBackground();
                }
            });
        }
        
        
        
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        addMouseMotionListener(this);
    }
    
    int x = -100,y = -100;
    int size =20;
    int choi = 0;
    Color front=Color.black,cc;
    
    boolean first = true;
    @Override
    public void paint(Graphics g) {
        System.out.println("paint() 실행");
        //super.paint(g); 화면 초기화
        
        if(first) {
            g.setColor(Color.white);
            g.fillRect(0, 0, 600, 687);
            first = false;
        }
        
        g.setColor(cc);
        
        switch(choi) {
        
        case 0:
            g.fillOval(x, y, size, size);
            break;
        case 1:
            g.fillRect(x,y,size,size);
            break;
            
        case 2:
            g.fillPolygon(
                    new int[] {x,x+size,x-size},
                    new int[] {y,y+size,y+size},
                    3
                );
            break;    
            
        case 3:
            g.fillPolygon(
                    new int[] {x,x+size,x+size*2,x+size,x+size*2,x,x-size*2,x-size,x-size*2,x-size},
                    new int[] {y,y+size,y+size,y+size*2,y+size*4,y+size*3,y+size*4,y+size*2,y+size,y+size},
                    10
                );
            break;    
            
        }
        
    }
    
 
    public static void main(String[] args) {
        new Main_Stude();
    }
 
}