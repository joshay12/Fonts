package me.fonts.src;

import java.awt.Dimension;
import java.awt.Point;
import java.awt.Rectangle;

/**
 * A class dedicated to the aftermath of rendering a font.
 * @author Joshua Diemer
 *
 */
public final class FontOutput {
	/**
	 * The font family of what was just printed.
	 */
	private FontFamily family;
	/**
	 * The font size of what was just printed.
	 */
	private int size;
	/**
	 * The starting x position of the text.
	 */
	private int x;
	/**
	 * The starting y position of the text.
	 */
	private int y;
	/**
	 * The full width of the text.
	 */
	private int width;
	/**
	 * The full height of the text.
	 */
	private int height;
	/**
	 * The ending x position of the text.
	 */
	private int right;
	/**
	 * The ending y position of the text.
	 */
	private int bottom;
	
	/**
	 * Creates an instance of the FontOutput class; generally created after a Font has rendered to the screen.
	 * @param family The font family of the rendered text.
	 * @param size The font size of the rendered text.
	 * @param bounds The overall boundary of the text.
	 */
	FontOutput(FontFamily family, int size, Rectangle bounds) {
		this.family = family;
		this.size = size;
		
		x = bounds.x;
		y = bounds.y;
		width = bounds.width;
		height = bounds.height;
		right = x + width;
		bottom = y + height;
	}
	
	/**
	 * Ease-of-access to continue rendering after rendering initial text.
	 * @param format The format to form the text and colors.
	 * @param x The x position to render on the screen.
	 * @param y The y position to render on the screen.
	 * @return A FontOutput to find the locations of the text, the font family, and the font size.
	 */
	public FontOutput renderMore(FontFormat format, int x, int y) {
		return getFont().render(format, x, y);
	}
	
	/**
	 * Ease-of-access to continue rendering after rendering initial text.
	 * <br>In order to create a new line, use <code>\n</code>.
	 * <br>In order to create a tabbed space, use <code>\t</code>.
	 * <br>In order to create a short space (half the size of a normal NBSP), use <code>\r</code>.
	 * <br>To change the color mid-way, use <code>\b</code> and it will use the next color in your colors array.
	 * @param text The text to render to the screen.
	 * @param x The x position to render on the screen.
	 * @param y The y position to render on the screen.
	 * @param colors The ordered list of colors to iterate through when using <code>\b</code>.
	 * @return A FontOutput to find the locations of the text, the font family, and the font size.
	 */
	public FontOutput renderMore(String text, int x, int y, int...colors) {
		return getFont().render(text, x, y, colors);
	}
	
	/**
	 * Ease-of-access to continue rendering after rendering initial text.
	 * @param format The format to form the text and colors.
	 * @param x The x position to render on the screen.
	 * @param y The y position to render on the screen.
	 * @param color The color of the text.
	 * @return A FontOutput to find the locations of the text, the font family, and the font size.
	 */
	public FontOutput renderMore(FontFormat format, int x, int y, int color) {
		return getFont().render(format, x, y, color);
	}
	
	/**
	 * Ease-of-access to continue rendering after rendering initial text.
	 * <br>In order to create a new line, use <code>\n</code>.
	 * <br>In order to create a tabbed space, use <code>\t</code>.
	 * <br>In order to create a short space (half the size of a normal NBSP), use <code>\r</code>.
	 * <br>To change the color mid-way, use <code>\b</code> and it will use the next color in your colors array.
	 * @param text The text to render to the screen.
	 * @param x The x position to render on the screen.
	 * @param y The y position to render on the screen.
	 * @param color The color of the text.
	 * @param colors The ordered list of colors to iterate through when using <code>\b</code>.
	 * @return A FontOutput to find the locations of the text, the font family, and the font size.
	 */
	public FontOutput renderMore(String text, int x, int y, int color, int...colors) {
		return getFont().render(text, x, y, color, colors);
	}
	
	/**
	 * Ease-of-access to continue rendering after rendering initial text.
	 * @param format The format to form the text and colors.
	 * @param x The x position to render on the screen.
	 * @param y The y position to render on the screen.
	 * @param color The color of the text.
	 * @param spacing The spacing between each letter (default is 1).
	 * @return A FontOutput to find the locations of the text, the font family, and the font size.
	 */
	public FontOutput renderMore(FontFormat format, int x, int y, int color, double spacing) {
		return getFont().render(format, x, y, color, spacing);
	}
	
	/**
	 * Ease-of-access to continue rendering after rendering initial text.
	 * <br>In order to create a new line, use <code>\n</code>.
	 * <br>In order to create a tabbed space, use <code>\t</code>.
	 * <br>In order to create a short space (half the size of a normal NBSP), use <code>\r</code>.
	 * <br>To change the color mid-way, use <code>\b</code> and it will use the next color in your colors array.
	 * @param text The text to render to the screen.
	 * @param x The x position to render on the screen.
	 * @param y The y position to render on the screen.
	 * @param color The color of the text.
	 * @param spacing The spacing between each letter (default is 1).
	 * @param colors The ordered list of colors to iterate through when using <code>\b</code>.
	 * @return A FontOutput to find the locations of the text, the font family, and the font size.
	 */
	public FontOutput renderMore(String text, int x, int y, int color, double spacing, int...colors) {
		return getFont().render(text, x, y, color, spacing, colors);
	}
	
	/**
	 * Ease-of-access to continue rendering after rendering initial text.
	 * @param format The format to form the text and colors.
	 * @param x The x position to render on the screen.
	 * @param y The y position to render on the screen.
	 * @param color The color of the text.
	 * @param spacing The spacing between each letter (default is 1).
	 * @param lineHeight The height between lines when using <code>\n</code> (default is 1).
	 * @return A FontOutput to find the locations of the text, the font family, and the font size.
	 */
	public FontOutput renderMore(FontFormat format, int x, int y, int color, double spacing, double lineHeight) {
		return getFont().render(format, x, y, color, spacing, lineHeight);
	}
	
	/**
	 * Ease-of-access to continue rendering after rendering initial text.
	 * <br>In order to create a new line, use <code>\n</code>.
	 * <br>In order to create a tabbed space, use <code>\t</code>.
	 * <br>In order to create a short space (half the size of a normal NBSP), use <code>\r</code>.
	 * <br>To change the color mid-way, use <code>\b</code> and it will use the next color in your colors array.
	 * @param text The text to render to the screen.
	 * @param x The x position to render on the screen.
	 * @param y The y position to render on the screen.
	 * @param color The color of the text.
	 * @param spacing The spacing between each letter (default is 1).
	 * @param lineHeight The height between lines when using <code>\n</code> (default is 1).
	 * @param colors The ordered list of colors to iterate through when using <code>\b</code>.
	 * @return A FontOutput to find the locations of the text, the font family, and the font size.
	 */
	public FontOutput renderMore(String text, int x, int y, int color, double spacing, double lineHeight, int...colors) {
		return getFont().render(text, x, y, color, spacing, lineHeight, colors);
	}
	
	/**
	 * Gets the Font associated with the font family and font size.
	 * @return The Font that rendered to the screen.
	 */
	public Font getFont() {
		return Fonts.getFont(family, size);
	}
	
	/**
	 * Gets the FontFamily associated with the output.
	 * @return The FontFamily that rendered to the screen.
	 */
	public FontFamily getFamily() {
		return family;
	}
	
	/**
	 * Gets the Font Size associated with the output.
	 * @return The Font Size that rendered to the screen.
	 */
	public int getFontSize() {
		return size;
	}
	
	/**
	 * Gets the starting X position of the rendering.
	 * @return The starting X position of the text rendered to the screen.
	 */
	public int getX() {
		return x;
	}
	
	/**
	 * Gets the starting Y position of the rendering.
	 * @return The starting Y position of the text rendered to the screen.
	 */
	public int getY() {
		return y;
	}
	
	/**
	 * Gets the width of the entire rendering.
	 * @return The full width of all the text rendered to the screen.
	 */
	public int getWidth() {
		return width;
	}
	
	/**
	 * Gets the height of the entire rendering.
	 * @return The full height of all the text rendered to the screen.
	 */
	public int getHeight() {
		return height;
	}
	
	/**
	 * Gets the ending X position of the rendering.
	 * @return The ending X position of the text rendered to the screen.
	 */
	public int getRightX() {
		return right;
	}
	
	/**
	 * Gets the ending Y position of the rendering.
	 * @return The ending Y position of the text rendered to the screen.
	 */
	public int getBottomY() {
		return bottom;
	}
	
	/**
	 * Gets the starting position of the rendering.
	 * @return The starting position of the text rendered to the screen.
	 */
	public Point getLocation() {
		return new Point(x, y);
	}
	
	/**
	 * Gets the ending position of the rendering.
	 * @return The ending position of the text rendered to the screen.
	 */
	public Point getEndingLocation() {
		return new Point(right, bottom);
	}
	
	/**
	 * Gets the dimensions of the rendering.
	 * @return The dimensions of the text rendered to the screen.
	 */
	public Dimension getSize() {
		return new Dimension(width, height);
	}
	
	/**
	 * Get all the boundaries of the rendering.
	 * @return The boundaries of the text rendered to the screen.
	 */
	public Rectangle getBounds() {
		return new Rectangle(x, y, width, height);
	}
}
