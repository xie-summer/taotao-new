package org.patchca.filter.library;

public abstract class AbstractTransformImageOp extends AbstractImageOp {
	private boolean initialized;

	protected abstract void transform(int paramInt1, int paramInt2, double[] paramArrayOfDouble);

	protected void init() {
	}

	public AbstractTransformImageOp() {
		setEdgeMode(2);
	}

	protected void filter(int[] inPixels, int[] outPixels, int width, int height) {
		if (!(this.initialized)) {
			init();
			this.initialized = true;
		}
		double[] t = new double[2];
		for (int y = 0; y < height; ++y) {
			for (int x = 0; x < width; ++x) {
				transform(x, y, t);
				int pixel = getPixelBilinear(inPixels, t[0], t[1], width, height);
				outPixels[(x + y * width)] = pixel;
			}
		}
	}
}
