package me.fonts.src;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import me.fonts.graphics.IScreen;
import me.fonts.graphics.SpriteBase;
import me.fonts.graphics.SpriteSheet;

/**
 * A class that contains all of the currently completed custom fonts and font sizes.
 * @author Joshua Diemer
 *
 */
public final class Fonts {
	/**
	 * An array of all the fonts.
	 */
	private static Font[] FONTS;
	
	static {
		try {
			List<Font> fonts = new ArrayList<>();
			
			Class<SpriteSheet> clazz = SpriteSheet.class;
			
			Field[] fields = Arrays.stream(clazz.getFields()).toArray(Field[]::new);
			
			for (Field field : fields) {
				if (!Modifier.isStatic(field.getModifiers()) || !Modifier.isFinal(field.getModifiers()))
					continue;
				
				final SpriteSheet sheet = (SpriteSheet) field.get(null);
				final String fontFamily = field.getName().split("_")[0];
				final int fontSize = Integer.parseInt(field.getName().split("_")[1].replaceAll("PT", ""));
				final int size = sheet.getWidth() / 26;
				final SpriteBase[] sprites = SpriteBase.split(sheet, size, size);
				
				fonts.add(new Font(sprites, fontFamily, fontSize));
			}
			
			FONTS = fonts.stream().toArray(Font[]::new);
		} catch (Exception e) {
			e.printStackTrace();
			System.exit(-1);
		}
	}
	
	/**
	 * Gets the very first font in the FONTS array.
	 */
	public static final Font firstFont = getFont(0);
	/**
	 * Gets the very last font in the FONTS array.
	 */
	public static final Font lastFont = getFont(FONTS.length - 1);
	/**
	 * Retrieves the default font: <code>ARIAL 14PT</code> font.
	 */
	public static final Font defaultFont = getFont(FontFamily.ARIAL, 14);
	
	/**
	 * Get the font by the font family and the font size.
	 * @param family The font family of the font to print.
	 * @param size The size of the font to print.
	 * @return The font to print to the screen.
	 */
	public static final Font getFont(FontFamily family, int size) {
		Font font = null;
		
		for (Font item : FONTS)
			if (item.getName().equalsIgnoreCase(family.toString()) && item.getSize() == size)
				font = item;
		
		return font;
	}
	
	/**
	 * Get the font by the index of the FONTS array in the Fonts class.
	 * @param index The index to retrieve from the FONTS array.
	 * @return The font to print to the screen.
	 */
	public static final Font getFont(int index) {
		return FONTS[index];
	}
	
	/**
	 * Get the index of a specific Font according to the FontFamily and font size provided.
	 * @param family The font family to search for.
	 * @param size The font size to search for.
	 * @return The index of the Font in the FONTS array.  This will return -1 if one is not found.
	 */
	public static final int indexOf(FontFamily family, int size) {
		for (int i = 0; i < FONTS.length; i++)
			if (FONTS[i].getName().equalsIgnoreCase(family.toString()) && FONTS[i].getSize() == size)
				return i;
		
		return -1;
	}
	
	/**
	 * Get the amount of fonts the FONTS array contains.
	 * @return The amount of fonts that exist in the Fonts class.
	 */
	public static final int size() {
		return FONTS.length;
	}
	
	/**
	 * Rather than individually setting the IScreen of each Font, using this will set the IScreens for all Fonts.
	 * @param screen The screen to set each Font to render to.
	 */
	public static final void setAllRenderers(IScreen screen) {
		for (Font font : FONTS)
			font.setScreen(screen);
	}
	
	private Fonts() {}
}
