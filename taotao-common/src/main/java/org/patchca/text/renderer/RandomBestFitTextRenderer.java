package org.patchca.text.renderer;

import java.util.Random;

public class RandomBestFitTextRenderer extends AbstractTextRenderer {
	protected void arrangeCharacters(int width, int height, TextString ts) {
		Random r = new Random();
		this.leftMargin = r.nextInt(10);
		this.topMargin = r.nextInt(3);
		double widthRemaining = (width - ts.getWidth() - this.leftMargin - this.rightMargin) / ts.getCharacters().size();
		widthRemaining = 0;
		double x = this.leftMargin + widthRemaining / 2.0D;
		height -= this.topMargin + this.bottomMargin;
		for (TextCharacter tc : ts.getCharacters()) {
			double y = this.topMargin + (height + tc.getAscent() * 0.7D) / 2.0D;
			tc.setX(x);
			tc.setY(y);
			x += tc.getWidth() + widthRemaining - 3;
		}
		
	}
}
