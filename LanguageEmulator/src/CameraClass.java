import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.videoio.VideoCapture;


public class CameraClass extends Thread{
	BufferedImage image;
	static VideoCapture capture;
	Mat webcamImage;
	static volatile boolean runnable = false;
	
	public CameraClass()
	{
		System.loadLibrary(Core.NATIVE_LIBRARY_NAME);	//loading opencv library
		
	}
	
	public void matToBufferedImage(Mat matBGR)
	{
		int width = matBGR.width(), height = matBGR.height(), channels = matBGR.channels();
		byte[] source = new byte[width * height * channels];
		matBGR.get(0, 0,source);
		
		image = new BufferedImage(width,height,BufferedImage.TYPE_3BYTE_BGR);
		
		final byte[] target = ((DataBufferByte) image.getRaster().getDataBuffer()).getData();
		System.arraycopy(source, 0, target, 00, source.length);
		
	}
	@Override
	public void run() {
		// TODO Auto-generated method stub
		while(runnable)
		{
			SecondFrame.imageLabel.setIcon(null);
			capture = new VideoCapture(0);	//accessing the default camera
			Mat webcamImage = new Mat();	//matrix to store image frames from camera
			
			if(capture.isOpened()) 	//if camera is opened
			{
				while(true)
				{
					capture.read(webcamImage);
					if(!webcamImage.empty())
					{
						matToBufferedImage(webcamImage);	//converting image in matrix form to buffered image form
						//drawing the image in panel using graphics obj
						Graphics g = SecondFrame.imageLabel.getGraphics();
						g.drawImage(image, 0, 0, SecondFrame.imageLabel.getWidth(), SecondFrame.imageLabel.getHeight(), null);
						
					}
				}
			}
			
		}
		
	}
	

}
