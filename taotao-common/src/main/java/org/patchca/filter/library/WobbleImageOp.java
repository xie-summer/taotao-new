package org.patchca.filter.library;

public class WobbleImageOp extends AbstractTransformImageOp {
	private double xWavelength;
	private double yWavelength;
	private double xAmplitude;
	private double yAmplitude;
	private double xRandom;
	private double yRandom;
	private double xScale;
	private double yScale;

	public WobbleImageOp() {
		this.xWavelength = 15.0D;
		this.yWavelength = 15.0D;
		this.xAmplitude = 4.0D;
		this.yAmplitude = 3.0D;
		this.xScale = 1.0D;
		this.yScale = 1.0D;
		this.xRandom = (3.0D * Math.random());
		this.yRandom = (10.0D * Math.random());
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

	public double getxScale() {
		return this.xScale;
	}

	public void setxScale(double xScale) {
		this.xScale = xScale;
	}

	public double getyScale() {
		return this.yScale;
	}

	public void setyScale(double yScale) {
		this.yScale = yScale;
	}

	@Override
    protected void transform(int x, int y, double[] t) {
		double tx = Math.cos((this.xScale * x + y) / this.xWavelength + this.xRandom);
		double ty = Math.sin((this.yScale * y + x) / this.yWavelength + this.yRandom);
		t[0] = (x + this.xAmplitude * tx);
		t[1] = (y + this.yAmplitude * ty);
	}
}