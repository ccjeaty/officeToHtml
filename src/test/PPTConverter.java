package test;

import java.awt.Dimension;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import org.apache.poi.hslf.model.TextRun;
import org.apache.poi.hslf.record.Slide;
import org.apache.poi.hslf.usermodel.RichTextRun;
import org.apache.poi.hslf.usermodel.SlideShow;

public class PPTConverter {
	public static void main(String[] args) {
		
//		String filePath = "XXXX内部文件监控保密系统";
		String filePath = "demo";
		
//		System.out.println(getFileName("c:\\poi\\" + filePath + ".ppt"));
		
		convert("c:/poi/" + filePath + ".ppt","c:\\imgs\\");
	}
	
	/**
	 * convert .ppt file with a .html file.
	 * @param filePath
	 * @param output
	 */
	public static void  convert(String filePath, String output) {
		
		File file = new File(filePath);
		if(!file.canRead())
			return;
		else if(!isPPT(file)){
			return;
		}
		
		try {
			FileInputStream is = new FileInputStream(file);
			SlideShow ppt = new SlideShow(is);
			is.close();
			Dimension pgsize = ppt.getPageSize();
			org.apache.poi.hslf.model.Slide[] slide = ppt.getSlides();
			
			for (int i = 0; i < slide.length; i++) {
				
				TextRun[] truns = slide[i].getTextRuns();
				for (int k = 0; k < truns.length; k++) {
					
					RichTextRun[] richTexts = truns[k].getRichTextRuns();
					for (int l = 0; l < richTexts.length; l++) {
						
						String fontName = richTexts[l].getFontName();
//						rtruns[l].setFontIndex(1);
						System.out.println(fontName);
						// POI bug？？？
						if ( isTrueType(fontName) )
							richTexts[l].setFontName("宋体");
						else
							richTexts[l].setFontName(fontName);
					}
				}
				BufferedImage img = new BufferedImage(pgsize.width, pgsize.height, BufferedImage.TYPE_INT_RGB);
				Graphics2D graphics = img.createGraphics();
				
				graphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
	            graphics.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
	            graphics.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BICUBIC);
	            graphics.setRenderingHint(RenderingHints.KEY_FRACTIONALMETRICS, RenderingHints.VALUE_FRACTIONALMETRICS_ON);
	            
	            graphics.setColor(Color.white);
	            graphics.clearRect(0, 0, pgsize.width, pgsize.height);
	            
//				graphics.setPaint(Color.white);
//				graphics.fill(new Rectangle2D.Float(0, 0, pgsize.width, pgsize.height));
				
				slide[i].draw(graphics);
//				String slideName = slide[i].getTitle() == null || "".equals(slide[i].getTitle().trim()) ?  ""
				// generate image.
				FileOutputStream out = new FileOutputStream(output + getFileName(filePath) + "_" + (i + 1) + ".png");
				javax.imageio.ImageIO.write(img, "png", out);
				out.close();
			}
			System.out.println("success!!");
		} catch (FileNotFoundException e) {
			System.out.println(e);
		} catch (IOException e) {
		}
	}
	
	private static boolean isPPT(File file){
		
		String filePath = file.getPath();
		int sep = filePath.lastIndexOf(".");
		if("ppt".equals(filePath.substring(sep + 1, filePath.length())))
			return true;
		
		return false;
	}
	
	private static String getFileName(String filePath){
		int sep = filePath.lastIndexOf("\\") == -1 ? filePath.lastIndexOf("/") : filePath.lastIndexOf("\\");
		return filePath.substring( sep + 1  , filePath.lastIndexOf("."));
	}

	
	public static boolean isTrueType(String fontName){
		String[] trueType = new String[]{
			"Tahoma","Times New Roman","Calibri","Arial"};
		for(String type : trueType){
			if(type.equals(fontName))
				return true;
		}
		return false;
	}
}


