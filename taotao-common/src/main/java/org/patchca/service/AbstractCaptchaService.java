package org.patchca.service;

import org.patchca.background.BackgroundFactory;
import org.patchca.color.ColorFactory;
import org.patchca.filter.FilterFactory;
import org.patchca.font.FontFactory;
import org.patchca.text.renderer.TextRenderer;
import org.patchca.word.WordFactory;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

public abstract class AbstractCaptchaService {
	protected FontFactory fontFactory;
	protected WordFactory wordFactory;
	protected ColorFactory colorFactory;
	protected BackgroundFactory backgroundFactory;
	protected TextRenderer textRenderer;
	protected List<FilterFactory> filterFactoryList = new ArrayList<FilterFactory>();
	protected int width;
	protected int height;

	public void setFontFactory(FontFactory fontFactory) {
		this.fontFactory = fontFactory;
	}

	public void setWordFactory(WordFactory wordFactory) {
		this.wordFactory = wordFactory;
	}

	public void setColorFactory(ColorFactory colorFactory) {
		this.colorFactory = colorFactory;
	}

	public void setBackgroundFactory(BackgroundFactory backgroundFactory) {
		this.backgroundFactory = backgroundFactory;
	}

	public void setTextRenderer(TextRenderer textRenderer) {
		this.textRenderer = textRenderer;
	}

	public void addFilterFactory(FilterFactory filterFactory) {
		this.filterFactoryList.add(filterFactory);
	}

	public FontFactory getFontFactory() {
		return this.fontFactory;
	}

	public WordFactory getWordFactory() {
		return this.wordFactory;
	}

	public ColorFactory getColorFactory() {
		return this.colorFactory;
	}

	public BackgroundFactory getBackgroundFactory() {
		return this.backgroundFactory;
	}

	public TextRenderer getTextRenderer() {
		return this.textRenderer;
	}

	public List<FilterFactory> getFilterFactory() {
		return this.filterFactoryList;
	}

	public int getWidth() {
		return this.width;
	}

	public int getHeight() {
		return this.height;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public Captcha getCaptcha() {
		BufferedImage bufImage = new BufferedImage(this.width, this.height, BufferedImage.TYPE_INT_RGB);//2
		this.backgroundFactory.fillBackground(bufImage);
		String word = this.wordFactory.getNextWord();
		this.textRenderer.draw(word, bufImage, this.fontFactory, this.colorFactory);
		for(FilterFactory factory: filterFactoryList){
			bufImage = factory.applyFilters(bufImage);
		}
		return new Captcha(word, bufImage);
	}
}
