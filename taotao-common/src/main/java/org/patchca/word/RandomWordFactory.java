package org.patchca.word;

import java.util.Random;

public class RandomWordFactory implements WordFactory {
	protected String characters;
	protected int minLength;
	protected int maxLength;

	public void setCharacters(String characters) {
		this.characters = characters;
	}

	public void setMinLength(int minLength) {
		this.minLength = minLength;
	}

	public void setMaxLength(int maxLength) {
		this.maxLength = maxLength;
	}

	public RandomWordFactory() {
		this.characters = "absdegkmnopwx23456789";
		this.minLength = 6;
		this.maxLength = 6;
	}

	public String getNextWord() {
		Random rnd = new Random();
		StringBuffer sb = new StringBuffer();
		int l = this.minLength + ((this.maxLength > this.minLength) ? rnd.nextInt(this.maxLength - this.minLength) : 0);
		for (int i = 0; i < l; ++i) {
			int j = rnd.nextInt(this.characters.length());
			sb.append(this.characters.charAt(j));
		}
		return sb.toString();
	}
}

/*
 * Location: C:\Documents and Settings\Administrator\桌面\patchca-0.5.0.jar
 * Qualified Name: org.RandomWordFactory JD-Core Version: 0.5.3
 */