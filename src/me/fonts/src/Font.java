package me.fonts.src;

import java.awt.Rectangle;

import me.fonts.graphics.IScreen;
import me.fonts.graphics.SpriteBase;

/**
 * The main class for creating custom fonts.  This will render and retrieve the fonts from the SpriteBases, rendering them to the IScreen.
 * @author Joshua Diemer
 *
 */
public final class Font {
	/**
	 * These are the supported characters of all fonts.
	 */
	public static final String CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"
										   + "abcdefghijklmnopqrstuvwxyz"
										   + "0123456789!@#$%^&*(){}[]:;"
										   + ",.'\"-=_+<>?|/\\`~";
	
	/**
	 * The sprites of the font.
	 */
	private final SpriteBase[] SPRITES;
	/**
	 * The font size.
	 */
	private final int size;
	/**
	 * The font family (all-capitals).
	 */
	private final String name;
	/**
	 * The array of offsets on the y-axis which is manually entered.
	 */
	private final int[] yOffsets;
	/**
	 * The screen to render the font to.
	 */
	private IScreen screen;
	
	/**
	 * Creates a new Font.  This is handled through the Fonts class.
	 * @param sprites The sprites of the font.
	 * @param name The font family (all-capitals).
	 * @param size The font size.
	 */
	Font(SpriteBase[] sprites, String name, int size) {		
		SPRITES = createSpriteXLimitations(0xFF000000, sprites);
		
		this.name = name;
		this.size = size;
		this.yOffsets = createSpriteYLimitations();
	}
	
	/**
	 * Sets the screen to render the Font to.  This MUST be set BEFORE rendering any Fonts.
	 * @param screen The screen to render the Font to.
	 * @return Itself for compounding.
	 */
	public final Font setScreen(IScreen screen) {
		this.screen = screen;
		
		return this;
	}
	
	/**
	 * Render to the set screen the format provided, and located at the x and y provided.
	 * @param format The format to form the text and colors.
	 * @param x The x position to render on the screen.
	 * @param y The y position to render on the screen.
	 * @return A FontOutput to find the locations of the text, the font family, and the font size.
	 */
	public final FontOutput render(FontFormat format, int x, int y) {
		return render(format.getText(), x, y, 0, size / 16 + 1, (int) (size / 2.5), format.getColors());
	}
	
	/**
	 * Render to the set screen the text provided, and located at the x and y provided.
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
	public final FontOutput render(String text, int x, int y, int...colors) {
		return render(text, x, y, 0, size / 16 + 1, (int) (size / 2.5), colors);
	}
	
	/**
	 * Render to the set screen the format provided, located at the x and y provided, and with the hex color provided.
	 * @param format The format to form the text and colors.
	 * @param x The x position to render on the screen.
	 * @param y The y position to render on the screen.
	 * @param color The color of the text.
	 * @return A FontOutput to find the locations of the text, the font family, and the font size.
	 */
	public final FontOutput render(FontFormat format, int x, int y, int color) {
		return render(format.getText(), x, y, color, size / 16 + 1, (int) (size / 2.5), format.getColors());
	}
	
	/**
	 * Render to the set screen the text provided, located at the x and y provided, and with the hex color provided.
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
	public final FontOutput render(String text, int x, int y, int color, int...colors) {
		return render(text, x, y, color, size / 16 + 1, (int) (size / 2.5), colors);
	}
	
	/**
	 * Render to the set screen the format provided, located at the x and y provided, with the hex color provided, and the spacing provided.
	 * @param format The format to form the text and colors.
	 * @param x The x position to render on the screen.
	 * @param y The y position to render on the screen.
	 * @param color The color of the text.
	 * @param spacing The spacing between each letter (default is 1).
	 * @return A FontOutput to find the locations of the text, the font family, and the font size.
	 */
	public final FontOutput render(FontFormat format, int x, int y, int color, double spacing) {
		return render(format.getText(), x, y, color, spacing, (int) (size / 2.5), format.getColors());
	}
	
	/**
	 * Render to the set screen the text provided, located at the x and y provided, with the hex color provided, and the spacing provided.
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
	public final FontOutput render(String text, int x, int y, int color, double spacing, int...colors) {
		return render(text, x, y, color, spacing, (int) (size / 2.5), colors);
	}
	
	/**
	 * Render to the set screen the format provided, located at the x and y provided, with the hex color provided, and the spacing and lineHeight provided.
	 * @param format The format to form the text and colors.
	 * @param x The x position to render on the screen.
	 * @param y The y position to render on the screen.
	 * @param color The color of the text.
	 * @param spacing The spacing between each letter (default is 1).
	 * @param lineHeight The height between lines when using <code>\n</code> (default is 1).
	 * @return A FontOutput to find the locations of the text, the font family, and the font size.
	 */
	public final FontOutput render(FontFormat format, int x, int y, int color, double spacing, double lineHeight) {
		return render(format.getText(), x, y, color, spacing, lineHeight, format.getColors());
	}
	
	/**
	 * Render to the set screen the text provided, located at the x and y provided, with the hex color provided, and the spacing and lineHeight provided.
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
	public final FontOutput render(String text, int x, int y, int color, double spacing, double lineHeight, int...colors) {
		double xOffset = 0;
		double line = 0;
		int width = 0;
		int height = 0;
		int currentColor = 0;
		
		for (int i = 0; i < text.length(); i++) {
			char c = text.charAt(i);
			
			int index = CHARACTERS.indexOf(c);
			
			if (index == -1 && c != '\n' && c != '\r' && c != '\t' && c != '\b') {
				xOffset += size / 2 + size / 5;
				continue;
			} else if (c == '\n') {
				xOffset = 0;
				line++;
				continue;
			} else if (c == '\r') {
				xOffset += (size / 2 + size / 5) >> 1;
				continue;
			} else if (c == '\t') {
				xOffset += (size / 2 + size / 5) << 1;
				continue;
			} else if (c == '\b') {
				color = colors[currentColor++];
				continue;
			}
			
			SpriteBase sprite = new SpriteBase(SPRITES[index]) {};
			
			if (color != 0xFF000000)
				sprite.replaceColor(0xFF000000, color);
			
			int yOffset = yOffsets[index];
			
			if (screen == null) {
				System.err.println("Screen is not set in this font.  Please use .setScreen() to set it.");
				break;
			} else
				screen.render(sprite, x + (int) xOffset, y + yOffset + (int) (line * (size + lineHeight)), 0xFFFF00FF);
			
			xOffset += sprite.getWidth() + spacing;
			
			if (xOffset > width)
				width = (int) xOffset;
		}
		
		height = (int) ((line + 1) * (size + lineHeight));
		
		Rectangle bounds = new Rectangle(x, y, width, height);
		
		return new FontOutput(FontFamily.valueOf(name), size, bounds);
	}
	
	/**
	 * Measure the width of the current font with certain text.
	 * @param text The text the measure the width of.
	 * @return The width of the text with the provided font.
	 */
	public int measureText(String text) {
		return measureText(this, text);
	}
	
	/**
	 * Measure the width of the current font with certain text.
	 * @param text The text the measure the width of.
	 * @return The width of the text with the provided font.
	 */
	public int measureText(String text, double spacing) {
		return measureText(this, text, spacing);
	}
	
	/**
	 * Measure the width of a font with certain text.
	 * @param font The font to measure with.
	 * @param text The text the measure the width of.
	 * @return The width of the text with the provided font.
	 */
	public static int measureText(Font font, String text) {
		return measureText(font, text, font.getSize() / 16 + 1);
	}
	
	/**
	 * Measure the width of a font with certain text.
	 * @param font The font to measure with.
	 * @param text The text the measure the width of.
	 * @param spacing The spacing between each character.
	 * @return The width of the text with the provided font.
	 */
	public static int measureText(Font font, String text, double spacing) {
		int output = 0;
		
		for (int i = 0; i < text.length(); i++) {
			char c = text.charAt(i);
			int index = CHARACTERS.indexOf(c);
			
			if (index == -1 && c != '\n' && c != '\r' && c != '\t' && c != '\b') {
				output += font.getSize() / 2 + font.getSize() / 5;
				continue;
			} else if (c == '\n') {
				output = 0;
				continue;
			} else if (c == '\r') {
				output += (font.getSize() / 2 + font.getSize() / 5) >> 1;
				continue;
			} else if (c == '\t') {
				output += (font.getSize() / 2 + font.getSize() / 5) << 1;
				continue;
			} else if (c == '\b')
				continue;
			
			SpriteBase sprite = font.SPRITES[index];
			
			output += sprite.getWidth() + spacing;
		}
		
		return output;
	}
	
	/**
	 * Gets the index of the character located at the provided x position provided.<br>
	 * Easy examples for usages would be:<br>
	 * Creating a custom TextField, clicking in the textfield would get an x position relative to the screen.<br>
	 * Subtracting the textfield's location from the x position clicked would provide a good x position to retrieve where in the string you clicked.
	 * @param text The text to measure.
	 * @param trueX The location in the string to search the index of.
	 * @return The index of the character found at the trueX provided.
	 */
	public int xIndexOf(String text, int trueX) {
		return xIndexOf(this, text, trueX);
	}
	
	/**
	 * Gets the index of the character located at the provided x position provided.<br>
	 * Easy examples for usages would be:<br>
	 * Creating a custom TextField, clicking in the textfield would get an x position relative to the screen.<br>
	 * Subtracting the textfield's location from the x position clicked would provide a good x position to retrieve where in the string you clicked.
	 * @param text The text to measure.
	 * @param spacing The spacing between each letter.
	 * @param trueX The location in the string to search the index of.
	 * @return The index of the character found at the trueX provided.
	 */
	public int xIndexOf(String text, double spacing, int trueX) {
		return xIndexOf(this, text, spacing, trueX);
	}
	
	/**
	 * Gets the index of the character located at the provided x position provided.<br>
	 * Easy examples for usages would be:<br>
	 * Creating a custom TextField, clicking in the textfield would get an x position relative to the screen.<br>
	 * Subtracting the textfield's location from the x position clicked would provide a good x position to retrieve where in the string you clicked.
	 * @param font The font to measure and gather the character location.
	 * @param text The text to measure.
	 * @param trueX The location in the string to search the index of.
	 * @return The index of the character found at the trueX provided.
	 */
	public static int xIndexOf(Font font, String text, int trueX) {
		return xIndexOf(font, text, font.getSize() / 16 + 1, trueX);
	}
	
	/**
	 * Gets the index of the character located at the provided x position provided.<br>
	 * Easy examples for usages would be:<br>
	 * Creating a custom TextField, clicking in the textfield would get an x position relative to the screen.<br>
	 * Subtracting the textfield's location from the x position clicked would provide a good x position to retrieve where in the string you clicked.
	 * @param font The font to measure and gather the character location.
	 * @param text The text to measure.
	 * @param spacing The spacing between each letter.
	 * @param trueX The location in the string to search the index of.
	 * @return The index of the character found at the trueX provided.
	 */
	public static int xIndexOf(Font font, String text, double spacing, int trueX) {
		if (trueX <= 0 || text == null || text.length() == 0)
			return 0;
		
		int output = 0;
		int width = 0;
		
		for (int i = 0; i < text.length(); i++, output++) {
			char c = text.charAt(i);
			int index = CHARACTERS.indexOf(c);
			double current = 0;
			
			if (index == -1 && c != '\n' && c != '\r' && c != '\t' && c != '\b') {
				current = font.getSize() / 2 + font.getSize() / 5;
				width += current;
			} else if (c == '\n') {
				width = 0;
				continue;
			} else if (c == '\r') {
				current = (font.getSize() / 2 + font.getSize() / 5) >> 1;
				width += current;
			} else if (c == '\t') {
				current = (font.getSize() / 2 + font.getSize() / 5) << 1;
				width += current;
			} else if (c == '\b')
				continue;
			else {
				current = font.SPRITES[index].getWidth() + spacing;
				width += current;
			}
			
			if (width - current / 2 + font.getSize() / 4 >= trueX)
				return output;
		}
		
		return text.length() - 1;
	}
	
	private final SpriteBase[] createSpriteXLimitations(int color, SpriteBase[] sprites) {
		SpriteBase[] output = new SpriteBase[sprites.length];
		int current = 0;
		
		for (SpriteBase sprite : sprites) {
			int maxX = 0;
			
			for (int y = 0; y < sprite.getHeight(); y++)
				for (int x = 0; x < sprite.getWidth(); x++)
					if (x > maxX && sprite.getPixel(x, y) == color)
						maxX = x;
			
			maxX++;
			
			int[] pixels = new int[maxX * sprite.getHeight()];
			
			for (int y = 0; y < sprite.getHeight(); y++)
				for (int x = 0; x < maxX; x++)
					pixels[x + y * maxX] = sprite.getPixels()[x + y * sprite.getWidth()];
						
			output[current++] = new SpriteBase(pixels, maxX, sprite.getHeight()) {};
		}
		
		return output;
	}
	
	private final int[] createSpriteYLimitations() {
		switch (size) {
			case 8:
				return getYFor8();
			case 9:
				return getYFor9();
			case 10:
				return getYFor10();
			case 11:
				return getYFor11();
			case 12:
				return getYFor12();
			case 14:
				return getYFor14();
			case 16:
				return getYFor16();
			case 18:
				return getYFor18();
			case 20:
				return getYFor20();
			case 22:
				return getYFor22();
			case 24:
				return getYFor24();
			case 26:
				return getYFor26();
			case 28:
				return getYFor28();
			case 32:
				return getYFor32();
			case 36:
				return getYFor36();
			case 40:
				return getYFor40();
			case 48:
				return getYFor48();
			case 72:
				return getYFor72();
		}
		
		return new int[SPRITES.length];
	}
	
	private final int[] getYFor8() {
		int[] output = new int[SPRITES.length];
		
		for (int i = 0; i < CHARACTERS.length(); i++) {
			switch (CHARACTERS.charAt(i)) {
				case '\'':
				case '"':
					output[i] = 4;
					break;
				case 'g':
				case 'j':
				case 'p':
				case 'q':
				case 'y':
				case '@':
				case ':':
				case ',':
					output[i] = 2;
					break;
				case '$':
				case '(':
				case ')':
				case '{':
				case '}':
				case '[':
				case ']':
				case ';':
				case '_':
				case '|':
					output[i] = 1;
					break;
				case '+':
				case '<':
				case '>':
					output[i] = -1;
			}
		}
		
		return output;
	}
	
	private final int[] getYFor9() {
		int[] output = new int[SPRITES.length];
		
		for (int i = 0; i < CHARACTERS.length(); i++) {
			switch (CHARACTERS.charAt(i)) {
				case '\'':
				case '"':
					output[i] = 4;
					break;
				case '@':
					output[i] = 3;
					break;
				case 'g':
				case 'j':
				case 'p':
				case 'q':
				case 'y':
				case ',':
					output[i] = 2;
					break;
				case '$':
				case '(':
				case ')':
				case '{':
				case '}':
				case '[':
				case ']':
				case ':':
				case ';':
				case '_':
				case '|':
					output[i] = 1;
					break;
				case '+':
				case '<':
				case '>':
					output[i] = -2;
			}
		}
		
		return output;
	}
	
	private final int[] getYFor10() {
		int[] output = new int[SPRITES.length];
		
		for (int i = 0; i < CHARACTERS.length(); i++) {
			switch (CHARACTERS.charAt(i)) {
				case '\'':
				case '"':
					output[i] = 4;
					break;
				case 'g':
				case 'j':
				case 'p':
				case 'q':
				case 'y':
				case '@':
					output[i] = 3;
					break;
				case ':':
				case ';':
				case ',':
					output[i] = 2;
					break;
				case '$':
				case '(':
				case ')':
				case '{':
				case '}':
				case '[':
				case ']':
				case '_':
				case '|':
					output[i] = 1;
					break;
				case '+':
				case '<':
				case '>':
					output[i] = -1;
			}
		}
		
		return output;
	}
	
	private final int[] getYFor11() {
		int[] output = new int[SPRITES.length];
		
		for (int i = 0; i < CHARACTERS.length(); i++) {
			switch (CHARACTERS.charAt(i)) {
				case 'g':
				case 'j':
				case 'p':
				case 'q':
				case 'y':
				case '\'':
				case '"':
					output[i] = 3;
					break;
				case '@':
				case ',':
					output[i] = 2;
					break;
				case '(':
				case ')':
				case '{':
				case '}':
				case '[':
				case ']':
				case ':':
				case ';':
				case '_':
				case '|':
					output[i] = 1;
					break;
				case '+':
				case '<':
				case '>':
					output[i] = -1;
			}
		}
		
		return output;
	}
	
	private final int[] getYFor12() {
		int[] output = new int[SPRITES.length];
		
		for (int i = 0; i < CHARACTERS.length(); i++) {
			switch (CHARACTERS.charAt(i)) {
				case 'g':
				case 'p':
				case 'q':
				case 'y':
				case '@':
				case '\'':
				case '"':
					output[i] = 3;
					break;
				case 'j':
				case ',':
				case '*':
					output[i] = 2;
					break;
				case '(':
				case ')':
				case '{':
				case '}':
				case '[':
				case ']':
				case ':':
				case ';':
				case '_':
				case '|':
					output[i] = 1;
					break;
				case '+':
				case '-':
				case '=':
				case '<':
				case '>':
					output[i] = -2;
					break;
			}
		}
		
		return output;
	}
	
	private final int[] getYFor14() {
		int[] output = new int[SPRITES.length];
		
		for (int i = 0; i < CHARACTERS.length(); i++) {
			switch (CHARACTERS.charAt(i)) {
				case 'g':
				case 'p':
				case 'q':
				case 'y':
					output[i] = 4;
					break;
				case 'j':
				case '@':
				case '\'':
				case '"':
				case ',':
					output[i] = 3;
					break;
				case ':':
					output[i] = 2;
					break;
				case '$':
				case '*':
				case '(':
				case ')':
				case '{':
				case '}':
				case '[':
				case ']':
				case ';':
				case '_':
				case '|':
					output[i] = 1;
					break;
				case '+':
				case '-':
				case '=':
				case '<':
				case '>':
					output[i] = -2;
					break;
			}
		}
		
		for (int i = 0; i < output.length; i++)
			output[i]--;
		
		return output;
	}
	
	private final int[] getYFor16() {
		int[] output = new int[SPRITES.length];
		
		for (int i = 0; i < CHARACTERS.length(); i++) {
			switch (CHARACTERS.charAt(i)) {
				case '\'':
				case '"':
					output[i] = 6;
					break;
				case 'g':
				case 'j':
				case 'p':
				case 'q':
				case 'y':
				case '@':
					output[i] = 4;
					break;
				case ',':
					output[i] = 3;
					break;
				case ':':
					output[i] = 2;
					break;
				case '$':
				case '*':
				case '(':
				case ')':
				case '{':
				case '}':
				case '[':
				case ']':
				case ';':
				case '_':
				case '|':
					output[i] = 1;
					break;
				case '<':
				case '>':
					output[i] = -1;
				case '+':
					output[i] = -2;
					break;
			}
		}
		
		for (int i = 0; i < output.length; i++)
			output[i] -= 4;
		
		return output;
	}
	
	private final int[] getYFor18() {
		int[] output = new int[SPRITES.length];
		
		for (int i = 0; i < CHARACTERS.length(); i++) {
			switch (CHARACTERS.charAt(i)) {
				case '\'':
				case '"':
					output[i] = 6;
					break;
				case 'g':
				case 'j':
				case 'p':
				case 'q':
				case 'y':
				case '@':
					output[i] = 5;
					break;
				case ',':
					output[i] = 4;
					break;
				case '(':
				case ')':
				case '{':
				case '}':
				case '[':
				case ']':
				case ':':
				case ';':
					output[i] = 2;
					break;
				case '$':
				case '*':
				case '_':
				case '|':
					output[i] = 1;
					break;
				case '+':
				case '<':
				case '>':
					output[i] = -1;
					break;
			}
		}
		
		for (int i = 0; i < output.length; i++)
			output[i] -= 4;
		
		return output;
	}

	private final int[] getYFor20() {
		int[] output = new int[SPRITES.length];
		
		for (int i = 0; i < CHARACTERS.length(); i++) {
			switch (CHARACTERS.charAt(i)) {
				case '\'':
				case '"':
					output[i] = 7;
					break;
				case '@':
					output[i] = 6;
					break;
				case 'g':
				case 'j':
				case 'p':
				case 'q':
				case 'y':
					output[i] = 5;
					break;
				case ',':
					output[i] = 4;
					break;
				case ':':
					output[i] = 3;
					break;
				case '(':
				case ')':
				case '{':
				case '}':
				case '[':
				case ']':
				case ';':
					output[i] = 2;
					break;
				case 'K':
				case 'L':
				case '$':
				case '*':
				case '_':
				case '|':
					output[i] = 1;
					break;
				case '+':
				case '<':
				case '>':
					output[i] = -3;
					break;
			}
		}
		
		for (int i = 0; i < output.length; i++)
			output[i] -= 5;
		
		return output;
	}
	
	private final int[] getYFor22() {
		int[] output = new int[SPRITES.length];
		
		for (int i = 0; i < CHARACTERS.length(); i++) {
			switch (CHARACTERS.charAt(i)) {
				case 'g':
				case 'j':
				case 'p':
				case 'q':
				case 'y':
				case '\'':
				case '"':
					output[i] = 6;
					break;
				case '@':
					output[i] = 5;
					break;
				case ',':
					output[i] = 4;
					break;
				case ':':
					output[i] = 3;
					break;
				case '$':
				case '(':
				case ')':
				case '{':
				case '}':
				case '[':
				case ']':
				case ';':
					output[i] = 2;
					break;
				case '*':
				case '_':
				case '|':
					output[i] = 1;
					break;
				case '-':
				case '=':
					output[i] = -1;
					break;
				case '+':
				case '<':
				case '>':
					output[i] = -3;
					break;
			}
		}
		
		for (int i = 0; i < output.length; i++)
			output[i] -= 4;
		
		return output;
	}
	
	private final int[] getYFor24() {
		int[] output = new int[SPRITES.length];
		
		for (int i = 0; i < CHARACTERS.length(); i++) {
			switch (CHARACTERS.charAt(i)) {
				case '\'':
				case '"':
					output[i] = 10;
					break;
				case '@':
					output[i] = 7;
					break;
				case 'g':
				case 'j':
				case 'p':
				case 'q':
				case 'y':
					output[i] = 6;
					break;
				case ',':
					output[i] = 4;
					break;
				case ':':
					output[i] = 3;
					break;
				case '$':
				case '(':
				case ')':
				case '{':
				case '}':
				case '[':
				case ']':
				case ';':
					output[i] = 2;
					break;
				case '*':
				case '_':
				case '|':
					output[i] = 1;
					break;
				case '-':
				case '=':
					output[i] = -1;
					break;
				case '+':
				case '<':
				case '>':
					output[i] = -3;
					break;
			}
		}
		
		for (int i = 0; i < output.length; i++)
			output[i] -= 8;
		
		return output;
	}
	
	private final int[] getYFor26() {
		int[] output = new int[SPRITES.length];
		
		for (int i = 0; i < CHARACTERS.length(); i++) {
			switch (CHARACTERS.charAt(i)) {
				case '\'':
				case '"':
					output[i] = 8;
					break;
				case 'g':
				case 'j':
				case 'p':
				case 'q':
				case 'y':
					output[i] = 7;
					break;
				case '@':
					output[i] = 6;
					break;
				case ',':
					output[i] = 5;
					break;
				case ':':
					output[i] = 4;
					break;
				case '$':
				case '(':
				case ')':
				case '{':
				case '}':
				case '[':
				case ']':
				case ';':
				case '_':
				case '|':
					output[i] = 2;
					break;
				case '*':
					output[i] = 1;
					break;
				case '-':
				case '=':
					output[i] = -1;
					break;
				case '+':
				case '<':
				case '>':
					output[i] = -3;
					break;
			}
		}
		
		for (int i = 0; i < output.length; i++)
			output[i] -= 6;
		
		return output;
	}
	
	private final int[] getYFor28() {
		int[] output = new int[SPRITES.length];
		
		for (int i = 0; i < CHARACTERS.length(); i++) {
			switch (CHARACTERS.charAt(i)) {
				case 'g':
				case 'p':
				case 'q':
				case 'y':
				case '@':
					output[i] = 7;
					break;
				case 'j':
				case '\'':
				case '"':
					output[i] = 6;
					break;
				case ',':
					output[i] = 5;
					break;
				case '*':
				case ';':
					output[i] = 4;
					break;
				case '(':
				case ')':
				case '{':
				case '}':
				case '[':
				case ']':
				case ':':
					output[i] = 3;
					break;
				case '$':
				case '_':
				case '|':
					output[i] = 2;
					break;
				case 'Q':
					output[i] = 1;
					break;
				case '-':
				case '=':
					output[i] = -2;
					break;
				case '+':
				case '<':
				case '>':
					output[i] = -3;
					break;
			}
		}
		
		for (int i = 0; i < output.length; i++)
			output[i] -= 4;
		
		return output;
	}
	
	private final int[] getYFor32() {
		int[] output = new int[SPRITES.length];
		
		for (int i = 0; i < CHARACTERS.length(); i++) {
			switch (CHARACTERS.charAt(i)) {
				case 'g':
				case 'p':
				case 'q':
				case 'y':
					output[i] = 9;
					break;
				case 'j':
				case '@':
				case '\'':
				case '"':
					output[i] = 8;
					break;
				case ',':
					output[i] = 6;
					break;
				case '*':
				case ';':
					output[i] = 4;
					break;
				case '$':
				case '(':
				case ')':
				case '{':
				case '}':
				case '[':
				case ']':
				case ':':
				case '_':
				case '|':
					output[i] = 3;
					break;
				case 'Q':
					output[i] = 1;
					break;
				case '-':
				case '=':
					output[i] = -2;
					break;
				case '+':
				case '<':
				case '>':
					output[i] = -3;
					break;
			}
		}
		
		for (int i = 0; i < output.length; i++)
			output[i] -= 6;
		
		return output;
	}
	
	private final int[] getYFor36() {
		int[] output = new int[SPRITES.length];
		
		for (int i = 0; i < CHARACTERS.length(); i++) {
			switch (CHARACTERS.charAt(i)) {
				case 'g':
				case 'j':
				case 'p':
				case 'q':
				case 'y':
				case '\'':
				case '"':
					output[i] = 10;
					break;
				case '@':
					output[i] = 9;
					break;
				case ',':
					output[i] = 7;
					break;
				case '(':
				case ')':
				case '{':
				case '}':
				case '[':
				case ']':
				case ';':
					output[i] = 4;
					break;
				case '$':
				case ':':
				case '_':
				case '|':
					output[i] = 3;
					break;
				case 'Q':
					output[i] = 2;
					break;
				case 'C':
				case 'G':
				case 'J':
				case 'O':
				case 'S':
				case 'U':
				case '*':
					output[i] = 1;
					break;
				case '-':
				case '=':
					output[i] = -2;
					break;
				case '<':
				case '>':
					output[i] = -3;
					break;
				case '+':
					output[i] = -4;
					break;
			}
		}
		
		for (int i = 0; i < output.length; i++)
			output[i] -= 8;
		
		return output;
	}
	
	private final int[] getYFor40() {
		int[] output = new int[SPRITES.length];
		
		for (int i = 0; i < CHARACTERS.length(); i++) {
			switch (CHARACTERS.charAt(i)) {
				case '\'':
				case '"':
					output[i] = 13;
					break;
				case 'g':
				case 'j':
				case 'p':
				case 'q':
				case 'y':
					output[i] = 11;
					break;
				case '@':
					output[i] = 10;
					break;
				case ',':
					output[i] = 8;
					break;
				case '(':
				case ')':
				case '{':
				case '}':
				case '[':
				case ']':
				case ';':
					output[i] = 4;
					break;
				case '$':
				case ':':
				case '_':
				case '|':
					output[i] = 3;
					break;
				case 'Q':
					output[i] = 2;
					break;
				case 'C':
				case 'G':
				case 'J':
				case 'O':
				case 'S':
				case 'U':
				case '*':
					output[i] = 1;
					break;
				case '-':
				case '=':
					output[i] = -2;
					break;
				case '<':
				case '>':
					output[i] = -3;
					break;
				case '+':
					output[i] = -4;
					break;
			}
		}
		
		for (int i = 0; i < output.length; i++)
			output[i] -= 11;
		
		return output;
	}
	
	private final int[] getYFor48() {
		int[] output = new int[SPRITES.length];
		
		for (int i = 0; i < CHARACTERS.length(); i++) {
			switch (CHARACTERS.charAt(i)) {
				case '\'':
				case '"':
					output[i] = 15;
					break;
				case 'g':
				case 'j':
				case 'p':
				case 'q':
				case 'y':
					output[i] = 14;
					break;
				case '@':
					output[i] = 12;
					break;
				case ',':
					output[i] = 9;
					break;
				case '(':
				case ')':
				case '{':
				case '}':
				case '[':
				case ']':
					output[i] = 5;
					break;
				case '$':
				case ':':
				case ';':
				case '_':
					output[i] = 4;
					break;
				case 'Q':
				case '*':
				case '|':
					output[i] = 3;
					break;
				case 'C':
				case 'G':
				case 'J':
				case 'O':
				case 'S':
				case 'U':
					output[i] = 1;
					break;
				case '-':
				case '=':
					output[i] = -2;
					break;
				case '<':
				case '>':
					output[i] = -3;
					break;
				case '+':
					output[i] = -4;
					break;
			}
		}
		
		for (int i = 0; i < output.length; i++)
			output[i] -= 13;
		
		return output;
	}
	
	private final int[] getYFor72() {
		int[] output = new int[SPRITES.length];
		
		for (int i = 0; i < CHARACTERS.length(); i++) {
			switch (CHARACTERS.charAt(i)) {
				case '\'':
				case '"':
					output[i] = 21;
					break;
				case 'g':
				case 'j':
				case 'y':
					output[i] = 20;
					break;
				case 'p':
				case 'q':
					output[i] = 19;
					break;
				case '@':
					output[i] = 18;
					break;
				case ',':
					output[i] = 15;
					break;
				case '*':
				case '(':
				case ')':
				case '{':
				case '}':
				case '[':
				case ']':
				case ';':
					output[i] = 7;
					break;
				case '$':
				case ':':
				case '_':
					output[i] = 6;
					break;
				case '|':
					output[i] = 5;
					break;
				case 'Q':
					output[i] = 4;
					break;
				case 'C':
				case 'G':
				case 'O':
				case 'S':
				case 'U':
					output[i] = 2;
					break;
				case 'J':
					output[i] = 1;
					break;
				case '-':
				case '=':
					output[i] = -4;
					break;
				case '<':
				case '>':
					output[i] = -8;
					break;
				case '+':
					output[i] = -8;
					break;
			}
		}
		
		for (int i = 0; i < output.length; i++)
			output[i] -= 19;
		
		return output;
	}
	
	/**
	 * Get the font family of the Font (all-capitals).
	 * @return The font family of the Font (all-capitals).
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * Get the font size of the Font.
	 * @return The font size of the Font.
	 */
	public int getSize() {
		return size;
	}
	
	/**
	 * Get a clone of the SpriteBases the Font contains.
	 * @return A clone of the SpriteBases the Font contains.
	 */
	public SpriteBase[] getSprites() {
		return SPRITES.clone();
	}
	
	/**
	 * Get the IScreen that was provided to the Font.
	 * @return The IScreen that was provided to the Font.
	 */
	public IScreen getRenderer() {
		return screen;
	}
	
	/**
	 * This will return the font family (all-capitals) with the font size.<br>
	 * Format: <code>NAME_sizePT</code><br>
	 * Example: <code>ARIAL_12PT</code>
	 */
	public String toString() {
		return name + "_" + size + "PT";
	}
}