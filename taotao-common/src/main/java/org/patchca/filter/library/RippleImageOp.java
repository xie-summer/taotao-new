package org.patchca.filter.library;

public class RippleImageOp extends AbstractTransformImageOp {
	protected double xWavelength;
	protected double yWavelength;
	protected double xAmplitude;
	protected double yAmplitude;
	protected double xRandom;
	protected double yRandom;

	public RippleImageOp() {
		this.xWavelength = 20.0D;
		this.yWavelength = 10.0D;
		this.xAmplitude = 5.0D;
		this.yAmplitude = 5.0D;
		this.xRandom = (5.0D * Math.random());
		this.yRandom = (5.0D * Math.random());
	}

	public double getxWavelength() {
		return this.xWavelength;
	}

	public void setxWavelength(double xWavelength) {
		this.xWavelength = xWavelength;
	}

	public double getyWavelength() {
		return this.yWavelength;
	}

	public void setyWavelength(double yWavelength) {
		this.yWavelength = yWavelength;
	}

	public double getxAmplitude() {
		return this.xAmplitude;
	}

	public void setxAmplitude(double xAmplitude) {
		this.xAmplitude = xAmplitude;
	}

	public double getyAmplitude() {
		return this.yAmplitude;
	}

	public void setyAmplitude(double yAmplitude) {
		this.yAmplitude = yAmplitude;
	}

	protected void transform(int x, int y, double[] t) {
		double tx = Math.sin(y / this.yWavelength + this.yRandom);
		double ty = Math.cos(x / this.xWavelength + this.xRandom);
		t[0] = (x + this.xAmplitude * tx);
		t[1] = (y + this.yAmplitude * ty);
	}
}
