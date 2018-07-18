import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

public class SecondFrame extends JFrame implements ActionListener{
	JPanel p1,p2,p3,p4;
	JButton b1,b2,b3,b4,b5,b6,b7,b8,b9;
	static JLabel imageLabel;	//static, so that it can directly be accessed from CameraClass
	JLabel l1,l2,l3,l4,l5;
	JComboBox combo1,combo2;
	String[] language= {"hindi","english","spanish"};
	JTextArea jt1,jt2;
	Thread t;
	CameraClass c;
	
	public SecondFrame()
	{   
		GraphicsEnvironment env = GraphicsEnvironment.getLocalGraphicsEnvironment();
		this.setMaximizedBounds(env.getMaximumWindowBounds());
		this.setExtendedState(this.getExtendedState()|this.MAXIMIZED_BOTH);
		setLayout(null);
		c = new CameraClass();
		
		//making a directory
		new File("E:\\Language Emulator").mkdir();
		new File("E:\\Language Emulator\\Temp").mkdir();
		new File("E:\\Language Emulator\\Saved").mkdir();
		
		//first panel
		p1=new JPanel();
		p1.setLayout(null);
		p1.setBackground(Color.getHSBColor(9.56f, 2.6f, 49.9f));
		p1.setBounds(5,5,1356,100);
		add(p1,BorderLayout.NORTH);
		
		//second panel
		p2=new JPanel();
		p2.setLayout(null);
		p2.setBackground(Color.getHSBColor(9.00f, 2.0f, 40.9f));
		//p2.setBounds(5,110,1356,80);
		p2.setBounds(5,110,150,590);
		
		l1 = new JLabel("OR");
		l2 = new JLabel("Select input language");
		l3 = new JLabel("Select output language");
		b1=new JButton("Open Camera");
		b2=new JButton("Browse Image");
		b5=new JButton("Go");
		
		b1.setBackground(Color.white);
		//b1.setBounds(160,135,130,25);
		b1.setBounds(20,150,120,25);
		//l1.setBounds(305,140,30,20);
		l1.setBounds(70,180,30,20);
		b2.setBackground(Color.white);
		//b2.setBounds(340,135,130,25);
		b2.setBounds(20,210,120,25);
		
		//l2.setBounds(800,120,150,20);
		//l3.setBounds(955,120,150,20);
		combo1=new JComboBox(language);
		combo2=new JComboBox(language);
		l2.setBounds(20,440,150,20);
		combo1.setBackground(Color.white);
		combo1.setBounds(25,470,110,25);
		l3.setBounds(15,515,150,20);
		combo2.setBackground(Color.white);
		combo2.setBounds(25,545,110,25);
		b5.setBackground(Color.white);
		b5.setBounds(50,600,60,25);
		
		add(b1);
		add(l1);
		add(b2);
		add(l2);
		add(l3);
		add(combo1);
		add(combo2);
		add(b5);
		add(p2);
		
		b1.addActionListener(this);
		b2.addActionListener(this);
		b5.addActionListener(this);		
		
		//third panel
		p3=new JPanel();
		p3.setLayout(null);
		p3.setBackground(Color.getHSBColor(9.06f, 2.3f, 42.9f));
		//p3.setBounds(5,195,655,505);
		p3.setBounds(160,110,655,590);
		
		b9 = new JButton("Capture");
		b9.setBounds(275,10,80,25);
		b9.setEnabled(false);
		b9.addActionListener(this);
		p3.add(b9);
		imageLabel = new JLabel();
		imageLabel.setBounds(10,40,635,590);
		p3.add(imageLabel);
		
		
		add(p3);
		
		//fourth panel
		p4=new JPanel();
		p4.setLayout(null);
		p4.setBackground(Color.getHSBColor(9.56f, 2.6f, 49.9f));
		//p4.setBounds(665,195,695,505);
		p4.setBounds(820,110,540,590);
		
		JTextArea jt1=new JTextArea(10,20);
		JTextArea jt2=new JTextArea(10,20);
		l4 = new JLabel("Input text");
		l5 = new JLabel("Output text");
		b3=new JButton("Sound");
		b4=new JButton("Sound");
		b8=new JButton("Save");
		l4.setBounds(855,165,100,25);
		jt1.setBackground(Color.white);
		jt1.setBounds(835,200,510,130);
		b3.setBackground(Color.white);
		b3.setBounds(1250,160,80,25);
		l5.setBounds(855,415,100,25);
		jt2.setBackground(Color.white);
		jt2.setBounds(835,450,510,130);
		b4.setBackground(Color.white);
		b4.setBounds(1250,410,80,25);
		b8.setBackground(Color.white);
		b8.setBounds(1040,650,80,25);
		
		add(l4);
		add(jt1);
		add(b3);
		add(l5);
		add(jt2);
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
	public void actionPerformed(ActionEvent arg) {
		// TODO Auto-generated method stub
		if(arg.getActionCommand()=="Browse Image")
		{
			JFileChooser file = new JFileChooser();
	        FileNameExtensionFilter filter = new FileNameExtensionFilter("*.Images","jpeg", "jpg","gif","png");
	        file.addChoosableFileFilter(filter);
	        int result = file.showOpenDialog(null);
	        if(result == JFileChooser.APPROVE_OPTION)	//"open" option selected
	        {
	        	File selectedFile = file.getSelectedFile();
		        String path = selectedFile.getAbsolutePath();
		        imageLabel.setIcon(ResizeImage(path));	//resizing image according to panel size
		        b9.setEnabled(false);
		        if(CameraClass.runnable==true)
		        {
		        	CameraClass.runnable = false;
		        	CameraClass.capture.release();
		        		        	
		        }
		        
	        }
	        else if(result == JFileChooser.CANCEL_OPTION)	//"cancel" option selected
	        {
	        	System.out.println("No File Select");
	        }
		}
		
		if(arg.getActionCommand()=="Open Camera")
		{
			CameraClass.runnable = true;
			t = new Thread(new CameraClass());	//thread for accessing webcam
			t.start();
			b9.setEnabled(true);			
		}
		if(arg.getActionCommand()=="Capture")
		{
			
		}
		
	}
	
	public ImageIcon ResizeImage(String ImagePath)
    {
		ImageIcon MyImage = new ImageIcon(ImagePath);
        Image img = MyImage.getImage();
        //Image newImg = img.getScaledInstance(400, 400, Image.SCALE_SMOOTH);
        Image newImg = img.getScaledInstance(imageLabel.getWidth(), imageLabel.getHeight(), Image.SCALE_SMOOTH);
        ImageIcon image = new ImageIcon(newImg);
        return image;
    }
	
}
	