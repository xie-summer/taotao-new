package org.patchca.word;

import java.util.Random;

public class AdaptiveRandomWordFactory extends RandomWordFactory {
	protected String wideCharacters;

	public void setWideCharacters(String wideCharacters) {
		this.wideCharacters = wideCharacters;
	}

	public AdaptiveRandomWordFactory() {
		this.characters = "absdegkmnopwx23456789";
		this.wideCharacters = "mw";
	}

	public String getNextWord() {
		Random rnd = new Random();
		StringBuffer sb = new StringBuffer();
		StringBuffer chars = new StringBuffer(this.characters);
		int l = this.minLength + ((this.maxLength > this.minLength) ? rnd.nextInt(this.maxLength - this.minLength) : 0);
		for (int i = 0; i < l; ++i) {
			int j = rnd.nextInt(chars.length());
			char c = chars.charAt(j);
			if (this.wideCharacters.indexOf(c) != -1) {
				for (int k = 0; k < this.wideCharacters.length(); ++k) {
					int idx = chars.indexOf(String.valueOf(this.wideCharacters.charAt(k)));
					if (idx != -1) {
						chars.deleteCharAt(idx);
					}
				}
			}
			sb.append(c);
		}
		return sb.toString();
	}
}
