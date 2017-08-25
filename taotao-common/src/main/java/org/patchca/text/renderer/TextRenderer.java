package org.patchca.text.renderer;

import org.patchca.color.ColorFactory;
import org.patchca.font.FontFactory;

import java.awt.image.BufferedImage;

public abstract interface TextRenderer {
	public abstract void setLeftMargin(int paramInt);

	public abstract void setRightMargin(int paramInt);

	public abstract void setTopMargin(int paramInt);

	public abstract void setBottomMargin(int paramInt);

	public abstract void draw(String paramString, BufferedImage paramBufferedImage, FontFactory paramFontFactory, ColorFactory paramColorFactory);
}
