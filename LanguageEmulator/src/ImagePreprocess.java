import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.Size;
import org.opencv.imgproc.Imgproc;
import org.opencv.photo.Photo;
import org.opencv.imgcodecs.Imgcodecs;

import com.recognition.software.jdeskew.ImageDeskew;
import com.recognition.software.jdeskew.ImageUtil;

/*
 * This class is for the purpose of preprocessing the image which is to be processed for OCR.
 * This is done to increase the accuracy of Tesseract (API for OCR)
 * It includes :
 * 1. Reducing noise
 * 2. Increasing sharpness 
 * 3. Increasing contrast
 * 4. Binarizing image (Color to B&W )
 * 5. Deskewing image
 */

public class ImagePreprocess {
	BufferedImage image = null;
	BufferedImage newimage = null;
	Mat source,destination;
	String path = "E:\\Language Emulator\\Preprocessed\\PreprocessedImage.png";
	double minimumDeskewThreshold = 0.05d;
	public ImagePreprocess()
	{	
		System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
		try{
			//noise reduction
	         source = Imgcodecs.imread(path, Imgcodecs.CV_LOAD_IMAGE_COLOR);
	         destination = new Mat(source.rows(), source.cols(), source.type());
	         Photo.fastNlMeansDenoisingColored(source, destination, 10, 10, 1, 3);
	         Imgcodecs.imwrite(path, destination);
	        
	         //sharpness
	         source = Imgcodecs.imread(path, Imgcodecs.CV_LOAD_IMAGE_COLOR);
	         destination = new Mat(source.rows(), source.cols(), source.type());
	         Imgproc.GaussianBlur(source, destination, new Size(0, 0), 10);
	         Core.addWeighted(source, 1.5, destination, -0.5, 0, destination);
	         Imgcodecs.imwrite(path, destination);
	       
	         //contrast
	         source = Imgcodecs.imread(path, Imgcodecs.CV_LOAD_IMAGE_COLOR);
	         destination = new Mat(source.rows(), source.cols(), source.type());
	         source.convertTo(destination,-1,1,10);
	         Imgcodecs.imwrite(path, destination);
	       
	         //binarize
	         source = Imgcodecs.imread(path, Imgcodecs.CV_LOAD_IMAGE_GRAYSCALE);
	         destination = new Mat(source.rows(), source.cols(), source.type());
	         Imgproc.threshold(source, destination, 200, 500, Imgproc.THRESH_BINARY);
	         Imgcodecs.imwrite(path, destination);
	     
	         //deskew
	         image = ImageIO.read(new File(path));
	         ImageDeskew deskew = new ImageDeskew(image);
	         double imageSkewAngle = deskew.getSkewAngle();
	         if ((imageSkewAngle > minimumDeskewThreshold || imageSkewAngle < -(minimumDeskewThreshold))) 
	        	 newimage = ImageUtil.rotate(image, -imageSkewAngle, image.getWidth() / 2, image.getHeight() / 2);
	         ImageIO.write(newimage,"png",new File(path));    
	      }catch (Exception e) {
	      }
	}
	public BufferedImage getPreprocessedImage()
	{
		try {
			image = ImageIO.read(new File(path));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return image;
	}
}

/*			//	DPI
final String formatName = "png";
try {
	image = ImageIO.read(new File("E:\\sample3.png"));
	
} catch (IOException e) {
	// TODO Auto-generated catch block
	e.printStackTrace();
}
for (Iterator<ImageWriter> iw = ImageIO.getImageWritersByFormatName(formatName); iw.hasNext();) {
   ImageWriter writer = iw.next();
   ImageWriteParam writeParam = writer.getDefaultWriteParam();
   ImageTypeSpecifier typeSpecifier = ImageTypeSpecifier.createFromBufferedImageType(BufferedImage.TYPE_INT_RGB);
   IIOMetadata metadata = writer.getDefaultImageMetadata(typeSpecifier, writeParam);
   if (metadata.isReadOnly() || !metadata.isStandardMetadataFormatSupported()) {
      continue;
   }

   setDPI(metadata);

   ImageOutputStream stream = null;
try {
	stream = ImageIO.createImageOutputStream(new File("E:\\neww.png"));
} catch (IOException e1) {
	// TODO Auto-generated catch block
	e1.printStackTrace();
}
   try {
      writer.setOutput(stream);
      try {
		writer.write(metadata, new IIOImage(image, null, metadata), writeParam);
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
   } finally {
      try {
		stream.close();
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
   }
}*/


/*private static void setDPI(IIOMetadata metadata) {
	// TODO Auto-generated method stub
	// for PMG, it's dots per millimeter
   // double dotsPerMilli = 1.0 * DPI / 10 / INCH_2_CM;

    IIOMetadataNode horiz = new IIOMetadataNode("HorizontalPixelSize");
    horiz.setAttribute("value", Double.toString(25.4f / 300));

    IIOMetadataNode vert = new IIOMetadataNode("VerticalPixelSize");
    vert.setAttribute("value", Double.toString(25.4f / 300));

    IIOMetadataNode dim = new IIOMetadataNode("Dimension");
    dim.appendChild(horiz);
    dim.appendChild(vert);

    IIOMetadataNode root = new IIOMetadataNode("javax_imageio_1.0");
    root.appendChild(dim);

    try {
		metadata.mergeTree("javax_imageio_1.0", root);
	} catch (IIOInvalidTreeException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
}*/
