package org.patchca.text.renderer;

public class SimpleTextRenderer extends AbstractTextRenderer {
	protected void arrangeCharacters(int width, int height, TextString ts) {
		double x = this.leftMargin;
		for (TextCharacter tc : ts.getCharacters()) {
			double y = this.topMargin + (height + tc.getAscent() * 0.7D) / 2.0D;
			tc.setX(x);
			tc.setY(y);
			x += tc.getWidth();
		}
	}
}
