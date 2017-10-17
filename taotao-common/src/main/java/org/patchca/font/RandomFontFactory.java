package org.patchca.font;

import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class RandomFontFactory implements FontFactory {
	protected List<String> families;
	protected int minSize;
	protected int maxSize;
	protected boolean randomStyle;

	public RandomFontFactory() {
		this.families = new ArrayList<>();
		this.families.add("Verdana");
		this.families.add("Tahoma");
		this.minSize = 45;
		this.maxSize = 45;
	}

	public RandomFontFactory(List<String> families) {
		this.families = families;
	}

	public RandomFontFactory(String[] families) {
		this.families = Arrays.asList(families);
	}

	public RandomFontFactory(int size, List<String> families) {
		this(families);
		this.minSize = (this.maxSize = size);
	}

	public RandomFontFactory(int size, String[] families) {
		this(families);
		this.minSize = (this.maxSize = size);
	}

	public void setFamilies(List<String> families) {
		this.families = families;
	}

	public void setMinSize(int minSize) {
		this.minSize = minSize;
	}

	public void setMaxSize(int maxSize) {
		this.maxSize = maxSize;
	}

	public void setRandomStyle(boolean randomStyle) {
		this.randomStyle = randomStyle;
	}

	@Override
    public Font getFont(int index) {
		Random r = new Random();
		String family = this.families.get(r.nextInt(this.families.size()));
		boolean bold = (r.nextBoolean()) && (this.randomStyle);
		int size = this.minSize;
		if (this.maxSize - this.minSize > 0) {
			size += r.nextInt(this.maxSize - this.minSize);
		}
		return new Font(family, (bold) ? 1 : 0, size);
	}
}
