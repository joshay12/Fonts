package me.fonts.graphics;

import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;

/**
 * A class dedicated to retrieving the RGB values of images found in the Project's resources.
 * @author Joshua Diemer
 *
 */
public final class SpriteSheet {
	/**
	 * The width of the SpriteSheet.
	 */
	private int width;
	/**
	 * The height of the SpriteSheet.
	 */
	private int height;
	/**
	 * The pixels of the SpriteSheet.
	 */
	private int[] pixels;
	/**
	 * The location of the SpriteSheet's image.
	 */
	private String path;
	
	public static final SpriteSheet ARIAL_8PT = new SpriteSheet("/fonts/arial_8pt.png");
	public static final SpriteSheet ARIAL_9PT = new SpriteSheet("/fonts/arial_9pt.png");
	public static final SpriteSheet ARIAL_10PT = new SpriteSheet("/fonts/arial_10pt.png");
	public static final SpriteSheet ARIAL_11PT = new SpriteSheet("/fonts/arial_11pt.png");
	public static final SpriteSheet ARIAL_12PT = new SpriteSheet("/fonts/arial_12pt.png");
	public static final SpriteSheet ARIAL_14PT = new SpriteSheet("/fonts/arial_14pt.png");
	public static final SpriteSheet ARIAL_16PT = new SpriteSheet("/fonts/arial_16pt.png");
	public static final SpriteSheet ARIAL_18PT = new SpriteSheet("/fonts/arial_18pt.png");
	public static final SpriteSheet ARIAL_20PT = new SpriteSheet("/fonts/arial_20pt.png");
	public static final SpriteSheet ARIAL_22PT = new SpriteSheet("/fonts/arial_22pt.png");
	public static final SpriteSheet ARIAL_24PT = new SpriteSheet("/fonts/arial_24pt.png");
	public static final SpriteSheet ARIAL_26PT = new SpriteSheet("/fonts/arial_26pt.png");
	public static final SpriteSheet ARIAL_28PT = new SpriteSheet("/fonts/arial_28pt.png");
	public static final SpriteSheet ARIAL_32PT = new SpriteSheet("/fonts/arial_32pt.png");
	public static final SpriteSheet ARIAL_36PT = new SpriteSheet("/fonts/arial_36pt.png");
	public static final SpriteSheet ARIAL_40PT = new SpriteSheet("/fonts/arial_40pt.png");
	public static final SpriteSheet ARIAL_48PT = new SpriteSheet("/fonts/arial_48pt.png");
	public static final SpriteSheet ARIAL_72PT = new SpriteSheet("/fonts/arial_72pt.png");

	/**
	 * Creates an instance of a SpriteSheet and will attempt to lead it according to the provided path.  If no file is found in the provided path, the application will close with an Exception.
	 * @param path The path of the image resource.
	 */
	public SpriteSheet(String path) {
		this.path = path;
		
		System.out.print("Attempting to read " + path + ": ");
		
		try {
			BufferedImage image = ImageIO.read(SpriteSheet.class.getResource(path));
			
			System.out.println("Success!");
			
			width = image.getWidth();
			height = image.getHeight();
			pixels = new int[width * height];
			
			image.getRGB(0, 0, width, height, pixels, 0, width);
		} catch (Exception e) {
			System.err.println("Failed!");
			e.printStackTrace();
			System.exit(-1);
		}
	}
	
	/**
	 * Gets the width of the SpriteSheet.
	 * @return The width of the SpriteSheet.
	 */
	public int getWidth() {
		return width;
	}
	
	/**
	 * Gets the height of the SpriteSheet.
	 * @return The height of the SpriteSheet.
	 */
	public int getHeight() {
		return height;
	}
	
	/**
	 * Gets the pixels of the SpriteSheet.
	 * @return The pixels of the SpriteSheet.
	 */
	public int[] getPixels() {
		return pixels;
	}
	
	/**
	 * Gets the image resource location of the SpriteSheet.
	 * @return The image resource location of the SpriteSheet.
	 */
	public String getPath() {
		return path;
	}
}
