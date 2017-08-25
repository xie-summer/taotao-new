package org.patchca.service;

import org.patchca.background.SingleColorBackgroundFactory;
import org.patchca.color.SingleColorFactory;
import org.patchca.filter.predefined.CurvesRippleFilterFactory;
import org.patchca.font.RandomFontFactory;
import org.patchca.text.renderer.BestFitTextRenderer;
import org.patchca.word.AdaptiveRandomWordFactory;

public class ConfigurableCaptchaService extends AbstractCaptchaService {
	public ConfigurableCaptchaService() {
		this.backgroundFactory = new SingleColorBackgroundFactory();
		this.wordFactory = new AdaptiveRandomWordFactory();
		this.fontFactory = new RandomFontFactory();
		this.textRenderer = new BestFitTextRenderer();
		this.colorFactory = new SingleColorFactory();
		this.addFilterFactory(new CurvesRippleFilterFactory(this.colorFactory));
		this.textRenderer.setLeftMargin(10);
		this.textRenderer.setRightMargin(10);
		this.width = 160;
		this.height = 70;
	}
}