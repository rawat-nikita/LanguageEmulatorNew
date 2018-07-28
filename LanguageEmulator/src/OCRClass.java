import java.awt.image.BufferedImage;
import java.io.File;
import javax.swing.ImageIcon;
//import com.asprise.ocr.Ocr;

import net.sourceforge.tess4j.ITesseract;
import net.sourceforge.tess4j.Tesseract;
/*
 * This class is for the purpose of performing OCR
 * i.e. extracting text from image
 * This is done with the help of Tesseract API
 */
public class OCRClass {
	String res;		//to store extracted text
	BufferedImage buff;
	public OCRClass(BufferedImage buff)
	{
		res = null;
		this.buff = buff;
		try
		{
			ITesseract instance = new Tesseract();
			res = instance.doOCR(buff);
		}
		catch(Exception e )
		{
			res = "Could not read text";
		}
	}
	public String getOCRText()
	{
		return res;
	}
}
/*				FOR ASPRISE
  		Ocr.setUp(); // one time setup
		Ocr ocr = new Ocr(); // create a new OCR engine
		ocr.startEngine("eng", Ocr.SPEED_FASTEST); // English
		String s = ocr.recognize(new File[] {new File("E:\\Language Emulator\\Preprocessed\\PreprocessedImage.png")},
		  Ocr.RECOGNIZE_TYPE_ALL, Ocr.OUTPUT_FORMAT_PLAINTEXT);
		System.out.println("Result: " + s);
		ocr.stopEngine();
*/
