package me.fonts.src;

import java.util.ArrayList;
import java.util.List;

/**
 * This class is dedicated to building certain formats to render to the font.<br>
 * Use <code>FontFormat.CodeBuilder</code> to make a Code Formatted font.<br>
 * Use <code>FontFormat.CustomBuilder</code> to make a Custom Formatted font.<br>
 * @author Joshua Diemer
 *
 */
public final class FontFormat {
	private final String text;
	private final int[] colors;
	
	private FontFormat(CodeBuilder builder) {
		colors = new int[builder.result.size()];
		
		StringBuilder output = new StringBuilder();
		
		int current = 0;
		
		for (CodeBuilder.Dual dual : builder.result) {
			output.append("\b" + dual.getText());
			
			switch (dual.type) {
				case "TEXT":
					colors[current++] = CodeBuilder.TEXT_COLOR;
					break;
				case "KEYWORD":
					colors[current++] = CodeBuilder.KEYWORD_COLOR;
					break;
				case "VARIABLE":
					colors[current++] = CodeBuilder.VARIABLE_COLOR;
					break;
				case "LOCAL_VARIABLE":
					colors[current++] = CodeBuilder.LOCAL_VARIABLE_COLOR;
					break;
				case "CONSTANT":
					colors[current++] = CodeBuilder.CONSTANT_COLOR;
					break;
				case "CLASS":
					colors[current++] = CodeBuilder.CLASS_COLOR;
					break;
				case "INTERFACE":
					colors[current++] = CodeBuilder.INTERFACE_COLOR;
					break;
				case "NUMBER":
					colors[current++] = CodeBuilder.NUMBER_COLOR;
					break;
				case "STRING":
					colors[current++] = CodeBuilder.STRING_COLOR;
					break;
				case "METHOD":
					colors[current++] = CodeBuilder.METHOD_COLOR;
					break;
				case "COMMENT":
					colors[current++] = CodeBuilder.COMMENT_COLOR;
					break;
				default:
					colors[current++] = CodeBuilder.TEXT_COLOR;
					break;
			}
		}
		
		text = output.toString();
	}
	
	private FontFormat(CustomBuilder builder) {
		colors = new int[builder.result.size()];
		
		StringBuilder output = new StringBuilder();
		
		int current = 0;
		
		for (CustomBuilder.Dual dual : builder.result) {
			output.append("\b" + dual.getText());
			colors[current++] = dual.getColor();
		}
		
		text = output.toString();
	}
	
	/**
	 * Gets the text produced by the builders.
	 * @return The text produced by the builders.
	 */
	public String getText() {
		return text;
	}
	
	/**
	 * Gets the colors for each text produced by the builders.
	 * @return The colors for each text produced by the builders.
	 */
	public int[] getColors() {
		return colors;
	}
	
	/**
	 * This class will create code according to Eclipse's "dark mode" text colors.
	 * @author Joshua Diemer
	 *
	 */
	public static final class CodeBuilder {
		public static int TEXT_COLOR 	= 0xFFFFFFFF,
				  KEYWORD_COLOR 	    = 0xFFBF7232,
				  VARIABLE_COLOR 	    = 0xFF87DEF5,
				  LOCAL_VARIABLE_COLOR  = 0xFF84A9F9,
				  CONSTANT_COLOR 		= 0xFF9ED8F5,
				  CLASS_COLOR 			= 0xFF428EBF,
				  INTERFACE_COLOR 		= 0xFF9CEFF4,
				  NUMBER_COLOR 			= 0xFF7295B8,
				  STRING_COLOR 			= 0xFF5ABDA0,
				  METHOD_COLOR 			= 0xFF56B251,
				  COMMENT_COLOR 		= 0xFF7B7B7B;
		
		private final List<Dual> result;
		
		public CodeBuilder() {
			result = new ArrayList<>();
		}

		public CodeBuilder addText(String text) {
			result.add(new Dual(text, "TEXT"));
			
			return this;
		}
		
		public CodeBuilder addKeyword(String text) {
			result.add(new Dual(text, "KEYWORD"));
			
			return this;
		}
		
		public CodeBuilder addVariable(String text, boolean isLocalVariable) {
			result.add(new Dual(text, isLocalVariable ? "LOCAL_VARIABLE" : "VARIABLE"));
			
			return this;
		}
		
		public CodeBuilder addConstant(String text) {
			result.add(new Dual(text, "CONSTANT"));
			
			return this;
		}
		
		public CodeBuilder addClass(String text) {
			result.add(new Dual(text, "CLASS"));
			
			return this;
		}
		
		public CodeBuilder addInterface(String text) {
			result.add(new Dual(text, "INTERFACE"));
			
			return this;
		}
		
		public CodeBuilder addNumber(String text) {
			result.add(new Dual(text, "NUMBER"));
			
			return this;
		}
		
		public CodeBuilder addString(String text) {
			result.add(new Dual(text, "STRING"));
			
			return this;
		}
		
		public CodeBuilder addMethod(String text) {
			result.add(new Dual(text, "METHOD"));
			
			return this;
		}
		
		public CodeBuilder addComment(String text) {
			result.add(new Dual(text, "COMMENT"));
			
			return this;
		}
		
		public FontFormat build() {
			return new FontFormat(this);
		}
		
		class Dual {
			private String text;
			private String type;
			
			private Dual(String text, String type) {
				this.text = text;
				this.type = type;
			}
			
			String getText() {
				return text;
			}
			
			String getType() {
				return type;
			}
		}
	}

	/**
	 * This class will create code according to the user's specified colors.
	 * @author Joshua Diemer
	 *
	 */
	public static final class CustomBuilder {
		/**
		 * The default text color.
		 */
		public static int TEXT_COLOR = 0xFF000000;
		
		private final List<Dual> result;
		
		public CustomBuilder() {
			result = new ArrayList<>();
		}
		
		/**
		 * Add text that is associated with the default text color.
		 * @param text The text to add to the builder.
		 * @return Itself for compound actions.
		 */
		public CustomBuilder addText(String text) {
			return addText(text, TEXT_COLOR);
		}
		
		/**
		 * Add text that is associated with the color provided.
		 * @param text The text to add to the builder.
		 * @param color The custom color to make the text (hexa-decimal).
		 * @return Itself for compound actions.
		 */
		public CustomBuilder addText(String text, int color) {
			result.add(new Dual(text, color));
			
			return this;
		}
		
		/**
		 * Add text that is associated with the color provided.
		 * @param text The text to add to the builder.
		 * @param color The premade color to make the text.
		 * @return Itself for compound actions.
		 */
		public CustomBuilder addText(String text, Colors color) {
			result.add(new Dual(text, color));
			
			return this;
		}
		
		public FontFormat build() {
			return new FontFormat(this);
		}
		
		/**
		 * An enumeration to give a premade color pallate.
		 * @author Joshua Diemer
		 *
		 */
		public enum Colors {
			WHITE(0xFFFFFFFF),
			YELLOW(0xFFFFFF00),
			ORANGE(0xFFFF8800),
			RED(0xFFFF0000),
			PINK(0xFFFF8888),
			MAGENTA(0xFFFF00FF),
			PURPLE(0xFF8800FF),
			BLUE(0xFF0000FF),
			CYAN(0xFF0088FF),
			AQUA(0xFF00FFFF),
			GREEN(0xFF00FF00),
			CHARTREUSE(0xFF88FF00),
			LIGHT_GRAY(0xFF333333),
			GRAY(0xFF7C7C7C),
			DARK_DRAY(0xFFBBBBBB),
			BLACK(0);
			
			private final int color;

			private Colors(int color) {
				this.color = color;
			}
			
			public int getColor() {
				return color;
			}
		}
		
		class Dual {
			private String text;
			private int color;
			
			private Dual(String text, int color) {
				this.text = text;
				this.color = color;
			}
			
			private Dual(String text, Colors color) {
				this.text = text;
				this.color = color.getColor();
			}
			
			String getText() {
				return text;
			}
			
			int getColor() {
				return color;
			}
		}
	}
}
