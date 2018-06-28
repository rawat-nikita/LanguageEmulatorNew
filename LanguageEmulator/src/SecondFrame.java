import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
public class SecondFrame extends JFrame implements ActionListener{
	JPanel p1,p2,p3,p4;
	JButton b1,b2,b3,b4,b5,b6,b7,b8;
	JLabel heading1;
	JComboBox jl;
	String[] language= {"hindi","english","spanish"};
	JTextArea jt1,jt2;
	public SecondFrame()
	{   
		GraphicsEnvironment env = GraphicsEnvironment.getLocalGraphicsEnvironment();
		this.setMaximizedBounds(env.getMaximumWindowBounds());
		this.setExtendedState(this.getExtendedState()|this.MAXIMIZED_BOTH);
		setLayout(null);
		heading1=new JLabel("IMAGE",JLabel.LEFT);
		
		p1=new JPanel();
		p1.setLayout(null);
		p1.setBackground(Color.getHSBColor(9.56f, 2.6f, 49.9f));
		p1.setBounds(5,5,1356,100);
		p1.add(heading1);
		add(p1,BorderLayout.NORTH);
		JComboBox combo=new JComboBox(language);
		JComboBox combo1=new JComboBox(language);
		p2=new JPanel();
		p2.setLayout(null);
		p2.setBackground(Color.getHSBColor(9.00f, 2.0f, 40.9f));
		p2.setBounds(5,110,1356,80);
		p2.add(heading1);
		b1=new JButton("Camera");
		b2=new JButton("Browse");
		b5=new JButton("go");
		b1.setBackground(Color.white);
		b1.setBounds(50,135,160,30);
		b2.setBackground(Color.white);
		b2.setBounds(220,135,160,30);
		combo.setBackground(Color.white);
		combo.setBounds(700,135,160,30);
		combo1.setBackground(Color.white);
		combo1.setBounds(900,135,160,30);
		b5.setBackground(Color.white);
		b5.setBounds(1100,135,160,30);
		add(b1);
		add(b2);
		add(combo);
		add(combo1);
		add(b5);
		add(p2);
		
		b1.addActionListener(this);
		b2.addActionListener(this);
		//b3.addActionListener(this);
		//b4.addActionListener(this);
		b5.addActionListener(this);		
		
		p3=new JPanel();
		p3.setLayout(null);
		p3.setBackground(Color.getHSBColor(9.06f, 2.3f, 42.9f));
		p3.setBounds(5,195,655,542);
		p3.add(heading1);
		add(p3);
		
		p4=new JPanel();
		p4.setLayout(null);
		p4.setBackground(Color.getHSBColor(9.56f, 2.6f, 49.9f));
		p4.setBounds(665,195,695,542);
		p4.add(heading1);
		JTextArea jt1=new JTextArea(10,20);
		JTextArea jt2=new JTextArea(10,20);
		b3=new JButton("sound");
		b4=new JButton("sound");
		b8=new JButton("save");
		jt1.setBackground(Color.white);
		jt1.setBounds(730,230,570,130);
		jt2.setBackground(Color.white);
		jt2.setBounds(730,450,570,130);
		b3.setBackground(Color.white);
		b3.setBounds(950,380,160,30);
		b4.setBackground(Color.white);
		b4.setBounds(950,600,160,30);
		b8.setBackground(Color.white);
		b8.setBounds(950,670,160,30);
		
		
		add(jt1);
		add(jt2);
		add(b3);
		add(b4);
		add(b8);
		add(p4);
		
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
    new SecondFrame();
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		
	}
}
	