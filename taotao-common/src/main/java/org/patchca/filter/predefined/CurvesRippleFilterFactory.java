package org.patchca.filter.predefined;

import org.patchca.color.ColorFactory;
import org.patchca.filter.library.CurvesImageOp;

import java.awt.image.BufferedImageOp;
import java.util.ArrayList;
import java.util.List;

public class CurvesRippleFilterFactory extends RippleFilterFactory {
	protected CurvesImageOp curves = new CurvesImageOp();

	public CurvesRippleFilterFactory() {
	}

	public CurvesRippleFilterFactory(ColorFactory colorFactory) {
		setColorFactory(colorFactory);
	}

	@Override
    protected List<BufferedImageOp> getPreRippleFilters() {
		List list = new ArrayList();
		list.add(this.curves);
		return list;
	}

	public void setStroke(float stroke) {
		this.curves.setStroke(stroke);
	}
	public void setColorFactory(ColorFactory colorFactory) {
		this.curves.setColorFactory(colorFactory);
	}
}