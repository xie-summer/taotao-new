package org.patchca.text.renderer;

import java.awt.*;
import java.awt.font.TextAttribute;
import java.text.AttributedCharacterIterator;
import java.text.AttributedString;

public class TextCharacter {
	private double x;
	private double y;
	private double width;
	private double height;
	private double ascent;
	private double descent;
	private char character;
	private Font font;
	private Color color;

	public double getX() {
		return this.x;
	}

	public void setX(double x) {
		this.x = x;
	}

	public double getY() {
		return this.y;
	}

	public void setY(double y) {
		this.y = y;
	}

	public double getWidth() {
		return this.width;
	}

	public void setWidth(double width) {
		this.width = width;
	}

	public double getHeight() {
		return this.height;
	}

	public void setHeight(double height) {
		this.height = height;
	}

	public char getCharacter() {
		return this.character;
	}

	public void setCharacter(char character) {
		this.character = character;
	}

	public Font getFont() {
		return this.font;
	}

	public void setFont(Font font) {
		this.font = font;
	}

	public Color getColor() {
		return this.color;
	}

	public void setColor(Color color) {
		this.color = color;
	}

	public double getAscent() {
		return this.ascent;
	}

	public void setAscent(double ascent) {
		this.ascent = ascent;
	}

	public double getDescent() {
		return this.descent;
	}

	public void setDescent(double descent) {
		this.descent = descent;
	}

	public AttributedCharacterIterator iterator() {
		AttributedString aString = new AttributedString(String.valueOf(this.character));

		aString.addAttribute(TextAttribute.FONT, this.font, 0, 1);
		return aString.getIterator();
	}
}
