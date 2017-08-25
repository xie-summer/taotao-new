package org.patchca.filter.predefined;

import org.apache.commons.lang.math.RandomUtils;
import org.patchca.filter.library.WobbleImageOp;

import java.awt.image.BufferedImageOp;
import java.util.ArrayList;
import java.util.List;

public class WobbleRippleFilterFactory extends RippleFilterFactory {
	protected WobbleImageOp[] wobble;

	public WobbleRippleFilterFactory() {
		this.wobble = new WobbleImageOp[100];
		for(int i=0;i<100;i++){
			wobble[i] = new WobbleImageOp();
		}
		
	}

	protected List<BufferedImageOp> getPreRippleFilters() {
		List list = new ArrayList();
		list.add(wobble[RandomUtils.nextInt(100)]);
		return list;
	}
}
