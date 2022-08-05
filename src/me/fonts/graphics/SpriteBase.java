package me.fonts.graphics;

/**
 * An abstract class dedicated to holding the pixels of a SpriteSheet, or duplicating certain pixels.
 * @author Joshua Diemer
 *
 */
public abstract class SpriteBase {
	/**
	 * The width of the SpriteBase.
	 */
	protected int width;
	/**
	 * The height of the SpriteBase.
	 */
	protected int height;
	/**
	 * The array of pixels, which contains all of the colors and their positions, for the SpriteBase.
	 */
	protected int[] pixels;
	
	/**
	 * A default constructor, which will not normally be able to be called unless if you have a custom constructor in the subclass.
	 */
	protected SpriteBase() {}
	
	/**
	 * Clones a SpriteBase's width, height, and array.  Uses value type copying of an array rather than the reference, so the array is a copy.
	 * @param sprite The SpriteBase to clone.
	 */
	public SpriteBase(SpriteBase sprite) {
		width = sprite.width;
		height = sprite.height;
		pixels = new int[width * height];
		
		for (int i = 0; i < pixels.length; i++)
			pixels[i] = sprite.pixels[i];
	}
	
	/**
	 * Clones the pixels, width, and height provided.  Uses value type copying of an array rather than the reference, so the array is a copy.
	 * @param pixels The pixels to copy to the new SpriteBase's pixels.
	 * @param width The width of the new SpriteBase.
	 * @param height The height of the new SpriteBase.
	 */
	public SpriteBase(int[] pixels, int width, int height) {
		this.width = width;
		this.height = height;
		this.pixels = new int[width * height];
		
		for (int i = 0; i < pixels.length; i++)
			this.pixels[i] = pixels[i];
	}
	
	/**
	 * Split an entire SpriteSheet into an array of SpriteBases, each the width and height of the provided.
	 * @param sheet The SpriteSheet to split into an array.
	 * @param width The width of each SpriteBase.
	 * @param height The height of each SpriteBase.
	 * @return The result of splitting each item in the SpriteSheet.
	 */
	public static final SpriteBase[] split(SpriteSheet sheet, int width, int height) {
		int amount = (sheet.getWidth() / width) * (sheet.getHeight() / height);
		int current = 0;
		
		SpriteBase[] output = new SpriteBase[amount];
		
		for (int y = 0; y < sheet.getHeight() / height; y++) {
			for (int x = 0; x < sheet.getWidth() / width; x++) {
				int[] pixels = new int[width * height];
				
				for (int yy = 0; yy < height; yy++) {
					for (int xx = 0; xx < width; xx++) {
						int ya = yy + y * height;
						int xa = xx + x * width;
						
						pixels[xx + yy * width] = sheet.getPixels()[xa + ya * sheet.getWidth()];
					}
				}
				
				output[current++] = new SpriteBase(pixels, width, height) {};
			}
		}
				
		return output;
	}
	
	/**
	 * Replaces a color in the SpriteBase to another color.
	 * @param from The color to replace.
	 * @param to The color to replace the old color with.
	 */
	public void replaceColor(int from, int to) {
		for (int i = 0; i < pixels.length; i++)
			if (pixels[i] == from)
				pixels[i] = to;
	}
	
	/**
	 * Sets a pixel's color at the specified location in the SpriteBase.
	 * @param x The x position in the SpriteBase to set the color of.
	 * @param y The y position in the SpriteBase to set the color of.
	 * @param color The color to set the specified pixel to.
	 */
	public final void setPixel(int x, int y, int color) {
		pixels[x + y * width] = color;
	}
	
	/**
	 * Gets a pixel's color at the specified location in the SpriteBase.
	 * @param x The x position in the SpriteBase to get the pixel.
	 * @param y The y position in the SpriteBase to get the pixel.
	 * @return The color found at the specified location.
	 */
	public final int getPixel(int x, int y) {
		return pixels[x + y * width];
	}
	
	/**
	 * Gets the width of the SpriteBase.
	 * @return The width of the SpriteBase.
	 */
	public final int getWidth() {
		return width;
	}
	
	/**
	 * Gets the height of the SpriteBase.
	 * @return The height of the SpriteBase.
	 */
	public final int getHeight() {
		return height;
	}
	
	/**
	 * Gets the pixels of the SpriteBase.
	 * @return The pixels of the SpriteBase.
	 */
	public final int[] getPixels() {
		return pixels;
	}
}
