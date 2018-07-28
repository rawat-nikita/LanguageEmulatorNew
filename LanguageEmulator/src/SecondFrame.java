import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class SecondFrame extends JFrame implements ActionListener{
	JPanel p1,p2,p3,p4;
	JButton b1,b2,b3,b4,b5,b6,b7,b8,b9,b10;
	static JLabel imageLabel;	//static, so that it can directly be accessed from CameraClass
	JLabel l1,l2,l3,l4,l5;
	JComboBox combo1,combo2;
	String[] language= {"hindi","english","spanish"};
	JTextArea jt1,jt2;
	Thread t;
	CameraClass c;
	File f;
	String tempImagePath = "E:\\Language Emulator\\Captured\\CapturedImage.jpg";
	BufferedImage buff=null;
	JScrollPane sp1,sp2;
	
	public SecondFrame()
	{   
		GraphicsEnvironment env = GraphicsEnvironment.getLocalGraphicsEnvironment();
		this.setMaximizedBounds(env.getMaximumWindowBounds());
		this.setExtendedState(this.getExtendedState()|this.MAXIMIZED_BOTH);
		setLayout(null);
		c = new CameraClass();
		
		//making a directory
		new File("E:\\Language Emulator").mkdir();
		new File("E:\\Language Emulator\\Captured").mkdir();
		new File("E:\\Language Emulator\\Saved").mkdir();
		new File("E:\\Language Emulator\\Preprocessed").mkdir();
		
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
		p2.setBounds(5,110,150,590);
		
		l1 = new JLabel("OR");
		l2 = new JLabel("Select input language");
		l3 = new JLabel("Select output language");
		b1=new JButton("Open Camera");
		b2=new JButton("Browse Image");
		b5=new JButton("Go");
		
		b1.setBackground(Color.white);
		b1.setBounds(20,150,120,25);
		l1.setBounds(70,180,30,20);
		b2.setBackground(Color.white);
		b2.setBounds(20,210,120,25);
		
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
		p3.setBounds(160,110,655,590);
		
		b9 = new JButton("Capture");
		b9.setBounds(275,10,80,25);
		b9.setEnabled(false);
		b9.addActionListener(this);
		p3.add(b9);
		imageLabel = new JLabel();
		imageLabel.setBounds(10,45,635,540);
		p3.add(imageLabel);
		add(p3);
		
		//fourth panel
		p4=new JPanel();
		p4.setLayout(null);
		p4.setBackground(Color.getHSBColor(9.56f, 2.6f, 49.9f));
		p4.setBounds(820,110,540,590);
		
		jt1=new JTextArea(10,20);
		jt2=new JTextArea(10,20);
		sp1 = new JScrollPane(jt1);		//adding JTextArea to scrollable JScrollPane
		sp2 = new JScrollPane(jt2);
		b10 = new JButton("Read text from image");
		l4 = new JLabel("Input text");
		l5 = new JLabel("Output text");
		b3=new JButton("Sound");
		b4=new JButton("Sound");
		b8=new JButton("Save");
		l4.setBounds(855,165,100,25);
		sp1.setBackground(Color.white);
		sp1.setBounds(835,200,510,130);
		b3.setBackground(Color.white);
		b3.setBounds(1250,160,80,25);
		l5.setBounds(855,415,100,25);
		sp2.setBackground(Color.white);
		sp2.setBounds(835,450,510,130);
		b4.setBackground(Color.white);
		b4.setBounds(1250,410,80,25);
		b8.setBackground(Color.white);
		b8.setBounds(1040,650,80,25);
		b10.setBackground(Color.white);
		b10.setBounds(1000,130,160,25);
		b10.addActionListener(this);
		b10.setEnabled(false);
		
		add(b10);
		add(l4);
		add(sp1);
		add(b3);
		add(l5);
		add(sp2);
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
	        file.addChoosableFileFilter(new FileNameExtensionFilter("*.Images","jpeg", "jpg","gif","png"));
	        int result = file.showOpenDialog(null);
	        if(result == JFileChooser.APPROVE_OPTION)	//"open" option selected
	        {
	        	File selectedFile = file.getSelectedFile();
		        String path = selectedFile.getAbsolutePath();
		        imageLabel.setIcon(ResizeImage(path));	//resizing image according to panel size
		        try {
					buff = ImageIO.read(selectedFile);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		        b9.setEnabled(false);
		        b10.setEnabled(true);
		        if(CameraClass.runnable==true)
		        {
		        	CameraClass.runnable = false;
		        	CameraClass.capture.release();
		        }
		    }
	        else if(result == JFileChooser.CANCEL_OPTION)	//"cancel" option selected
	        {
	        	System.out.println("No File Selected");
	        }
		}
		
		if(arg.getActionCommand()=="Open Camera")
		{
			t = new Thread(new CameraClass());	//thread for accessing webcam
			CameraClass.runnable = true;
			if(CameraClass.image==null)
				t.start();
			b9.setEnabled(true);
			b1.setEnabled(false);
		}
		if(arg.getActionCommand()=="Capture")
		{
			//creating a temp captured image file in 'Language Emulator\Captured' folder
			f = new File(tempImagePath);
			if(f.exists())	//deleting temp file if it already exists
			{
				f.delete();
				f = new File(tempImagePath);
			}
			buff = CameraClass.image;
			try {
				ImageIO.write(buff, "jpg", f);	//creating new temp image file
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			imageLabel.setIcon(ResizeImage(buff));	//setting the new file in imageLabel
			if(CameraClass.runnable==true)
	        {
	        	CameraClass.runnable = false;	//closing camera
	        	CameraClass.capture.release();
	        }
			b1.setEnabled(true);
			b9.setEnabled(false);
			b10.setEnabled(true);	//enabling 'read text from image' button
		}
		
		if(arg.getActionCommand()=="Read text from image")
		{
			f = new File("E:\\Language Emulator\\Preprocessed\\PreprocessedImage.png");
			try {
				ImageIO.write(buff, "png", f);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			buff = new ImagePreprocess().getPreprocessedImage();
			String text1 = new OCRClass(buff).getOCRText();	//for performing OCR
			System.out.println(text1);
			jt1.setText(text1);
		}
		
	}
	
	public ImageIcon ResizeImage(String ImagePath)
    {
		ImageIcon MyImage = new ImageIcon(ImagePath);
        Image img = MyImage.getImage();
        Image newImg = img.getScaledInstance(imageLabel.getWidth(), imageLabel.getHeight(), Image.SCALE_SMOOTH);
        ImageIcon image = new ImageIcon(newImg);
        return image;
    }
	
	public ImageIcon ResizeImage(BufferedImage bimg)
    {
        ImageIcon MyImage = new ImageIcon(bimg);
        Image img = MyImage.getImage();
        Image newImg = img.getScaledInstance(imageLabel.getWidth(), imageLabel.getHeight(), Image.SCALE_SMOOTH);
        ImageIcon image = new ImageIcon(newImg);
        return image;
    }
	
}
	