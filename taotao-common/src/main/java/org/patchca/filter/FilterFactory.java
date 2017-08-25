package org.patchca.filter;

import java.awt.image.BufferedImage;

public abstract interface FilterFactory {
	public abstract BufferedImage applyFilters(BufferedImage paramBufferedImage);
}
