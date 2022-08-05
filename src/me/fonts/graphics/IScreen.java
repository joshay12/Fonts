package me.fonts.graphics;

/**
 * An interface for a rasterization screen.  The class that extends this will need to properly implement the SpriteBase into their pixels array; otherwise, the fonts will not appear properly.
 * @author Joshua Diemer
 *
 */
public interface IScreen {
	/**
	 * An abstract method to clear all of the pixels of the array.
	 * @param color The color to clear the pixels of the array to.
	 */
	void clear(int color);
	/**
	 * An abstract method to render a SpriteBase to the pixels array that you have set up.
	 * @param sprite The sprite to render to the pixels array.
	 * @param x The x position on the pixels array to render to.
	 * @param y The y position on the pixels array to render to.
	 * @param opaqueColors Which colors, if any, to avoid rendering (usually 0xFFFF00FF).
	 */
	void render(SpriteBase sprite, int x, int y, int...opaqueColors);
}
