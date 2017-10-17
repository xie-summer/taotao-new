package org.patchca.filter.library;

public class MarbleImageOp extends AbstractTransformImageOp {
	double scale;
	double amount;
	double turbulence;
	double[] tx;
	double[] ty;
	double randomX;
	double randomY;

	public MarbleImageOp() {
		this.scale = 15.0D;
		this.amount = 1.1D;
		this.turbulence = 6.2D;
		this.randomX = (256.0D * Math.random());
		this.randomY = (256.0D * Math.random());
	}

	public double getScale() {
		return this.scale;
	}

	public void setScale(double scale) {
		this.scale = scale;
	}

	public double getAmount() {
		return this.amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public double getTurbulence() {
		return this.turbulence;
	}

	public void setTurbulence(double turbulence) {
		this.turbulence = turbulence;
	}

	@Override
    protected synchronized void init() {
		this.tx = new double[256];
		this.ty = new double[256];
		for (int i = 0; i < 256; ++i) {
			double angle = 6.283185307179586D * i * this.turbulence / 256.0D;
			this.tx[i] = (this.amount * Math.sin(angle));
			this.ty[i] = (this.amount * Math.cos(angle));
		}
	}

	@Override
    protected void transform(int x, int y, double[] t) {
		int d = limitByte((int) (127.0D * (1.0D + PerlinNoise.noise2D(x / this.scale + this.randomX, y / this.scale + this.randomY))));
		t[0] = (x + this.tx[d]);
		t[1] = (y + this.ty[d]);
	}

	protected void filter2(int[] outPixels, int width, int height) {
		for (int y = 0; y < height; ++y) {
            for (int x = 0; x < width; ++x) {
                int pixel = limitByte((int) (127.0D * (1.0D + PerlinNoise.noise2D(x / this.scale + this.randomX, y / this.scale + this.randomY))));
                outPixels[(x + y * width)] = (limitByte(255) << 24 | limitByte(pixel) << 16 | limitByte(pixel) << 8 | limitByte(pixel));
            }
        }
	}
}