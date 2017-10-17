package org.patchca.filter;

import java.awt.image.BufferedImageOp;
import java.util.List;

public class ConfigurableFilterFactory extends AbstractFilterFactory {
	private List<BufferedImageOp> filters;

	@Override
    public List<BufferedImageOp> getFilters() {
		return this.filters;
	}

	public void setFilters(List<BufferedImageOp> filters) {
		this.filters = filters;
	}
}
