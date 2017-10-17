package org.patchca.filter.library;

public class DoubleRippleImageOp extends RippleImageOp {
	@Override
    protected void transform(int x, int y, double[] t) {
		double tx = Math.sin(y / this.yWavelength + this.yRandom) + 1.3D * Math.sin(0.6D * y / this.yWavelength + this.yRandom);
		double ty = Math.cos(x / this.xWavelength + this.xRandom) + 1.3D * Math.cos(0.6D * x / this.xWavelength + this.xRandom);
		t[0] = (x + this.xAmplitude * tx);
		t[1] = (y + this.yAmplitude * ty);
	}
}
