package org.patchca.text.renderer;


public class BestFitTextRenderer extends AbstractTextRenderer {
	protected void arrangeCharacters(int width, int height, TextString ts) {
		double widthRemaining = (width - ts.getWidth() - this.leftMargin - this.rightMargin) / ts.getCharacters().size();
		double x = this.leftMargin + widthRemaining / 2.0D;
		height -= this.topMargin + this.bottomMargin;
		for (TextCharacter tc : ts.getCharacters()) {
			double y = this.topMargin + (height + tc.getAscent() * 0.7D) / 2.0D;
			tc.setX(x);
			tc.setY(y);
			x += tc.getWidth() + widthRemaining;
		}
	}
}
