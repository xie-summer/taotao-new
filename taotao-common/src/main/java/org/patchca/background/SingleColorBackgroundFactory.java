package org.patchca.background;

import org.patchca.color.ColorFactory;
import org.patchca.color.SingleColorFactory;

import java.awt.*;
import java.awt.image.BufferedImage;

public class SingleColorBackgroundFactory implements BackgroundFactory {
	private ColorFactory colorFactory;

	public SingleColorBackgroundFactory() {
		SingleColorFactory scf = new SingleColorFactory(Color.WHITE);
		this.colorFactory = scf;
	}

	public SingleColorBackgroundFactory(Color color) {
		this.colorFactory = new SingleColorFactory(color);
	}

	public void setColorFactory(ColorFactory colorFactory) {
		this.colorFactory = colorFactory;
	}

	public void fillBackground(BufferedImage dest) {
		Graphics2D g = dest.createGraphics();
		g.setBackground(this.colorFactory.getColor(0));
		// 填充背景色
		g.clearRect(0, 0, dest.getWidth(), dest.getHeight());
	}
}
