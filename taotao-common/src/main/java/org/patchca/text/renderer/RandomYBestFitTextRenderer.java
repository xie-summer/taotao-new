package org.patchca.text.renderer;

import java.util.Random;

public class RandomYBestFitTextRenderer extends AbstractTextRenderer {
	private double minY = 20.0;
	private double maxY =25.0;
	public void setMinY(double minY) {
		this.minY = minY;
	}
	public void setMaxY(double maxY) {
		this.maxY = maxY;
	}
	@Override
    protected void arrangeCharacters(int width, int height, TextString ts) {
		double widthRemaining = (width - ts.getWidth() - this.leftMargin - this.rightMargin) / ts.getCharacters().size();
		double vmiddle = height / 2;
		double x = this.leftMargin + widthRemaining / 2.0D;
		Random r = new Random();
		height -= this.topMargin + this.bottomMargin;
		for (TextCharacter tc : ts.getCharacters()) {
			double heightRemaining = height - tc.getHeight();
			double y = vmiddle + 0.35D * tc.getAscent() + (1.0D - (2.0D * r.nextDouble())) * heightRemaining;
			tc.setX(x);
			y = Math.min(Math.max(minY, y), maxY);
			tc.setY(y);
			x += tc.getWidth() + widthRemaining - 5;
		}
	}
}
