# Welcome to the Fonts API

## General Information

The first release of the Fonts API.  This version will only include 1 font (Arial), as well as the font sizes 8, 9, 10, 11, 12, 14, 16, 18, 20, 22, 24, 26, 28, 32, 36, 40, 48, and 72.  There is a Font Formatter included in the API to help format colors into your Fonts.

## Critical setup functions

In order to use the Fonts API, you must be making a project that uses rasterization.  In a class where you render things, be sure to implement "IScreen".  Then fill in what you need for rendering.  Usually, the appropriate code would be the following:

```Java
public void render(SpriteBase sprite, int x, int y, int...opaqueColors) {
	for (int yy = 0; yy < sprite.getHeight(); yy++) {
		int ya = yy + y;
			
		if (ya < 0 || ya >= height)
			continue;
			
		xSprite: for (int xx = 0; xx < sprite.getWidth(); xx++) {
			int xa = xx + x;
				
			if (xa < 0 || xa >= width)
				continue;
				
			int color = sprite.getPixels()[xx + yy * sprite.getWidth()];
				
			for (int col : opaqueColors)
				if (col == color)
					continue xSprite;
								
			pixels[xa + ya * width] = color;
		}
	}
}
```

After you set up your screen, before rendering any fonts, you must run "Fonts.setScreen()" at least one time.
Thank you, and happy rendering!

## Using the Fonts, Font, and FontOutput class

Utilize the following to render fonts to the screen:

```Java
FontOutput output = Fonts.getFont(FontFamily.ARIAL, 12).render("Hello World!", 10, 10, 0xFF11AAFF);
```

You can then use the output to retrieve important data, such as:

```Java
int x = output.getX(); // The initial x
int y = output.getY(); // The initial y
int width = output.getWidth(); // The ending width
int height = output.getHeight(); // The ending height
int right = output.getRight(); // The ending x position
int bottom = output.getBottom(); // The ending y position
FontFamily family = output.getFamily(); // The font family used to render
int size = output.getSize(); // The font size used to render
```

You can even use the output to continue rendering:

```Java
output = output.render("Another Hello World", 10, 30, 0xFFFFAA11);
```

## Using the FontFormat class

Font Formatting can be used to produce code or specific colored text.
Observe the following:

```Java
FontFormat.CustomBuilder builder = new FontFormat.CustomBuilder();

builder.addText("Hello ");
builder.addText("World", 0xFF11AAFF);
builder.addText("!", Colors.ORANGE);

Fonts.getFont(FontFamily.ARIAL, 16).render(builder.build(), 10, 10);
```

The above will make "Hello " the default text color, "World" a nice cyan color, and "!" an orange color.  All rendered in one neat little class.

Observe the following:

```Java
FontFormat format = new FontFormat.CodeBuilder().addKeyword("public static void ").addMethod("main").addText("(").addClass("String").addText("[] ").addVariable("args", true).addText(") {\n\n}").build();

Fonts.getFont(FontFamily.ARIAL, 16).render(format, 10, 10);
```

The above will print out:

```
public static void main(String[] args) {

}
```

However, it will print it with the colors of eclipse in dark mode.
