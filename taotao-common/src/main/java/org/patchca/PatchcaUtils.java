package org.patchca;

import org.patchca.background.ComplexBackgroundFactory;
import org.patchca.color.SingleColorFactory;
import org.patchca.filter.predefined.CurvesRippleFilterFactory;
import org.patchca.filter.predefined.WobbleRippleFilterFactory;
import org.patchca.font.RandomFontFactory;
import org.patchca.service.Captcha;
import org.patchca.service.ConfigurableCaptchaService;
import org.patchca.text.renderer.RandomBestFitTextRenderer;
import org.patchca.word.RandomWordFactory;

import javax.imageio.IIOImage;
import javax.imageio.ImageIO;
import javax.imageio.ImageWriteParam;
import javax.imageio.ImageWriter;
import javax.imageio.plugins.jpeg.JPEGImageWriteParam;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.ColorModel;
import java.io.IOException;
import java.io.OutputStream;

public class PatchcaUtils implements CaptchaUtil{
	private ConfigurableCaptchaService cs;
	private static final String defaultCapChar = "ABCDEFGHKLMNPRSTUVXYZacdefhkmnprstuwvxz345789";
	public static PatchcaUtils getSimpleUtils(){
		return new PatchcaUtils(80, 40, 4, 4);
	}
	public static PatchcaUtils getComplexUtils(){
		PatchcaUtils util = new PatchcaUtils(80, 40, 4, 4);
		CurvesRippleFilterFactory crf = new CurvesRippleFilterFactory();
		crf.setColorFactory(new SingleColorFactory(new Color(25, 60, 170)));
		util.cs.addFilterFactory(crf);
		util.cs.setBackgroundFactory(new ComplexBackgroundFactory());
		util.cs.setColorFactory(new SingleColorFactory(new Color(25, 60, 170)));
		
		return util;
	}
	public PatchcaUtils(int width, int height, int minLen, int maxLen){
		this(width, height, minLen, maxLen, defaultCapChar);
	}
	public PatchcaUtils(int width, int height, int minLen, int maxLen, String capchar){
		RandomFontFactory ff = new RandomFontFactory();
		init(width, height, minLen, maxLen, capchar, ff);
	}
	public PatchcaUtils(int width, int height, int minLen, int maxLen, String capchar, RandomFontFactory ff){
		init(width, height, minLen, maxLen, capchar, ff);
	}
	private void init(int width, int height, int minLen, int maxLen, String capchar, RandomFontFactory ff){
		cs = new ConfigurableCaptchaService();
		cs.setColorFactory(new SingleColorFactory(new Color(25, 60, 170)));
		RandomWordFactory rwf = new RandomWordFactory();
		rwf.setCharacters(capchar);
		rwf.setMinLength(minLen);
		rwf.setMaxLength(maxLen);
		cs.setWordFactory(rwf);
		cs.setWidth(width);
		cs.setHeight(height);
		ff.setMinSize(30);
		ff.setMaxSize(30);
		cs.setFontFactory(ff);
		cs.setTextRenderer(new RandomBestFitTextRenderer());
		WobbleRippleFilterFactory filter = new WobbleRippleFilterFactory();
		cs.addFilterFactory(filter);
	}
	@Override
	public Captcha createCaptcha() {
		Captcha captcha = cs.getCaptcha();
		return captcha;
	}
	public static void printImage(OutputStream os, BufferedImage bi) throws IOException {
		ImageWriteParam  imgWriteParams = new JPEGImageWriteParam(null);
		imgWriteParams.setCompressionMode(JPEGImageWriteParam.MODE_EXPLICIT);
		imgWriteParams.setCompressionQuality(0.6f);
		imgWriteParams.setProgressiveMode(JPEGImageWriteParam.MODE_DISABLED);
		
		ColorModel colorModel = ColorModel.getRGBdefault();
		// 指定压缩时使用的色彩模式
		imgWriteParams.setDestinationType(new javax.imageio.ImageTypeSpecifier(colorModel, colorModel.createCompatibleSampleModel(16, 16)));
		ImageWriter imgWrier = ImageIO.getImageWritersByFormatName("png").next();
		imgWrier.setOutput(ImageIO.createImageOutputStream(os));
		imgWrier.write(null, new IIOImage(bi, null, null), imgWriteParams);
		// 创建可用来将图像数据编码为JPEG数据流的编码器
		// 强行将缓冲区的内容输入到页面
		os.flush();
		// 关闭输出流
		os.close();
	}
}