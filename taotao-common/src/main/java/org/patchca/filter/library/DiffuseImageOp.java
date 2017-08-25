package org.patchca.filter.library;

import java.util.Random;

public class DiffuseImageOp extends AbstractTransformImageOp {
	double[] tx;
	double[] ty;
	double amount;

	public DiffuseImageOp() {
		this.amount = 1.6D;
	}

	public double getAmount() {
		return this.amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	protected synchronized void init() {
		this.tx = new double[256];
		this.ty = new double[256];
		for (int i = 0; i < 256; ++i) {
			double angle = 6.283185307179586D * i / 256.0D;
			this.tx[i] = (this.amount * Math.sin(angle));
			this.ty[i] = (this.amount * Math.cos(angle));
		}
	}

	protected void transform(int x, int y, double[] t) {
		Random r = new Random();
		int angle = (int) (r.nextFloat() * 255.0F);
		t[0] = (x + this.tx[angle]);
		t[1] = (y + this.ty[angle]);
	}
}

/*
 * Location: C:\Documents and Settings\Administrator\桌面\patchca-0.5.0.jar
 * Qualified Name: org.DiffuseImageOp JD-Core Version:
 * 0.5.3
 */