package org.patchca.filter.predefined;

import org.patchca.filter.library.DiffuseImageOp;

import java.awt.image.BufferedImageOp;
import java.util.ArrayList;
import java.util.List;

public class DiffuseRippleFilterFactory extends RippleFilterFactory {
	protected DiffuseImageOp diffuse;

	public DiffuseRippleFilterFactory() {
		this.diffuse = new DiffuseImageOp();
	}

	@Override
    protected List<BufferedImageOp> getPreRippleFilters() {
		List list = new ArrayList();
		list.add(this.diffuse);
		return list;
	}
}
