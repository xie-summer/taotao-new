package org.patchca.color;

import java.awt.*;
import java.util.Random;

public class RandomColorFactory implements ColorFactory {
	private Color min;
	private Color max;
	private Color color;

	public RandomColorFactory() {
		this.min = new Color(20, 40, 80);
		this.max = new Color(21, 50, 140);
	}

	public void setMin(Color min) {
		this.min = min;
	}

	public void setMax(Color max) {
		this.max = max;
	}

	@Override
    public Color getColor(int index) {
		if (this.color == null) {
			Random r = new Random();
			this.color = new Color(this.min.getRed() + r.nextInt(this.max.getRed() - this.min.getRed()), this.min.getGreen() + r.nextInt(this.max.getGreen() - this.min.getGreen()), this.min.getBlue()
					+ r.nextInt(this.max.getBlue() - this.min.getBlue()));
		}

		return this.color;
	}
}
