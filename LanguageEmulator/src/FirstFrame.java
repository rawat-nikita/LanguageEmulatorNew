


import javax.swing.*;

//import Project.InterfaceNew;

import java.awt.*;
import java.awt.event.*;
class FirstFrame extends JFrame implements ActionListener
{
JButton b1;
JLabel l1,l2,l3;


    public FirstFrame()
    {
    	GraphicsEnvironment env = GraphicsEnvironment.getLocalGraphicsEnvironment();
		this.setMaximizedBounds(env.getMaximumWindowBounds());
		this.setExtendedState(this.getExtendedState()|this.MAXIMIZED_BOTH);
    	setLayout(null);
		
    setTitle("Tourist Language Emulator");
    setLocationRelativeTo(null);
    setDefaultCloseOperation(EXIT_ON_CLOSE);
    
   
    ImageIcon MyImage = new ImageIcon("F:\\COLLEGE\\5th SEM\\Java\\BtechCSB\\Mahima11\\src\\Internship\\Front.jpg");
   // ("F:\\COLLEGE\\5th SEM\\Java\\BtechCSB\\Mahima11\\src\\Internship\\Front.jpg")));
    Image img = MyImage.getImage();
    Image newImg = img.getScaledInstance(1370,720,Image.SCALE_SMOOTH);
    ImageIcon image = new ImageIcon(newImg);
    setContentPane(new JLabel( image));
   
   
   
    
    b1=new JButton("Go");
    b1.setForeground(Color.black);
   b1.setBackground(Color.gray);
    b1.setBounds(770,667,65,43);
    b1.addActionListener(this);
    add(b1,BorderLayout.SOUTH);
     
     
       l1 = new JLabel("Tourist");
       l1.setFont(new Font("Serif",Font.BOLD,54));
       l1.setForeground(Color.black);
     //  l1.setForeground(Color.getHSBColor(0.56f, 26.0f, 25.9f));
       l2 = new JLabel("Language");
       l2.setFont(new Font("Serif",Font.BOLD,54));
       l2.setForeground(Color.black);
       //l2.setForeground(Color.getHSBColor(45.56f, 36.0f, 809.9f));
       l1.setBounds(720, 360, 533, 154);
       l2.setBounds(710, 389, 617, 266);
       l3 = new JLabel("Emulator");
       l3.setBounds(710, 460, 630, 300);
       l3.setFont(new Font("Serif",Font.BOLD,54));
       l3.setForeground(Color.black);
     //  l3.setForeground(Color.getHSBColor(0.56f, 26.0f, 25.9f));
       
    /*   JTextArea editArea = new JTextArea(45,35);
       Font font = new Font(
               Font.MONOSPACED, 
               Font.PLAIN, 
               editArea.getFont().getSize());
       editArea.setFont(font);*/
       
       JLabel jlabel = new JLabel("This is a label");
       jlabel.setFont(new Font("Verdana",1,12));
      jlabel.setBounds(670,250,350, 140);
       
       
       add(l1);
       add(l2);
       add(l3);
       add(jlabel);
       setSize(768,359);
       setVisible(true);
    
   
    }
   
 public static void main(String args[])
    {
    new FirstFrame();
    }

@Override
public void actionPerformed(ActionEvent e) {
	if(e.getActionCommand()=="Go") {
		this.setVisible(false);
		new SecondFrame().setVisible(true);
	}
	
}
   
} 

