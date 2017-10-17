package org.patchca.filter.predefined;

import org.apache.commons.lang.math.RandomUtils;
import org.patchca.filter.AbstractFilterFactory;
import org.patchca.filter.library.RippleImageOp;

import java.awt.image.BufferedImageOp;
import java.util.ArrayList;
import java.util.List;

public class RippleFilterFactory extends AbstractFilterFactory {
	protected RippleImageOp[] ripple;
	public RippleFilterFactory() {
		ripple = new RippleImageOp[100];
		for(int i=0;i<100;i++){
			this.ripple[i] = new RippleImageOp();
		}
	}

	protected List<BufferedImageOp> getPreRippleFilters() {
		return new ArrayList();
	}

	@Override
    public List<BufferedImageOp> getFilters() {
		List<BufferedImageOp> filters = new ArrayList<BufferedImageOp>();
		filters.addAll(getPreRippleFilters());
		filters.add(this.ripple[RandomUtils.nextInt(100)]);
		return filters;
	}
}
