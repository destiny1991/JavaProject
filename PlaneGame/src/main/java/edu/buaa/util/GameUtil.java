package edu.buaa.util;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;

/**
 * 游戏开发中常用的工具类（如图片加载）;
 * 工具类通常会将构造器私有，你只需调用static方法；
 * @author destiny
 *
 */
public class GameUtil {
	
	public static Image getImage(String path) {
		URL u = GameUtil.class.getClassLoader().getResource(path);
		BufferedImage img = null;
		try {
			img = ImageIO.read(u);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return img;
	}

}
