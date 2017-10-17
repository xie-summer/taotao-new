package org.patchca.filter.library;

public abstract class AbstractConvolveImageOp extends AbstractImageOp {
	private float[][] matrix;

	protected AbstractConvolveImageOp(float[][] matrix) {
		this.matrix = matrix;
	}

	@Override
    protected void filter(int[] inPixels, int[] outPixels, int width, int height) {
		int matrixWidth = this.matrix[0].length;
		int matrixHeight = this.matrix.length;
		int mattrixLeft = -matrixWidth / 2;
		int matrixTop = -matrixHeight / 2;
		for (int y = 0; y < height; ++y) {
			int ytop = y + matrixTop;
			int ybottom = y + matrixTop + matrixHeight;
			for (int x = 0; x < width; ++x) {
				float[] sum = { 0.5F, 0.5F, 0.5F, 0.5F };
				int xleft = x + mattrixLeft;
				int xright = x + mattrixLeft + matrixWidth;
				int matrixY = 0;
				for (int my = ytop; my < ybottom; ++matrixY) {
					int matrixX = 0;
					for (int mx = xleft; mx < xright; ++matrixX) {
						int pixel = getPixel(inPixels, mx, my, width, height);
						float m = this.matrix[matrixY][matrixX];
						sum[0] += m * (pixel >> 24 & 0xFF);
						sum[1] += m * (pixel >> 16 & 0xFF);
						sum[2] += m * (pixel >> 8 & 0xFF);
						sum[3] += m * (pixel & 0xFF);

						++mx;
					}
					++my;
				}

				outPixels[(x + y * width)] = (limitByte((int) sum[0]) << 24 | limitByte((int) sum[1]) << 16 | limitByte((int) sum[2]) << 8 | limitByte((int) sum[3]));
			}
		}
	}
}
