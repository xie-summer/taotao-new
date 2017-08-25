package org.patchca.filter.library;

public class SoftenImageOp extends AbstractConvolveImageOp {
	private static final float[][] matrix = { { 0.0F, 0.0625F, 0.0F }, { 0.0625F, 0.75F, 0.0625F }, { 0.0F, 0.0625F, 0.0F } };

	public SoftenImageOp() {
		super(matrix);
	}
}
