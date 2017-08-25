package org.patchca.filter.library;

public class BlurImageOp extends AbstractConvolveImageOp {
	private static final float[][] matrix = { { 0.0625F, 0.125F, 0.0625F }, { 0.125F, 0.25F, 0.125F }, { 0.0625F, 0.125F, 0.0625F } };

	public BlurImageOp() {
		super(matrix);
	}
}
