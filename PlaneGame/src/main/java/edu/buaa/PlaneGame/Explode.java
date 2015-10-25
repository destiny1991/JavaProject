package edu.buaa.PlaneGame;

import java.awt.Graphics;
import java.awt.Image;
import edu.buaa.util.Constant;
import edu.buaa.util.GameUtil;

public class Explode {
	double x,y;
	static Image[] imgs;  //静态属性用静态代码块初始化
	int count;
	
	static {
		imgs = new Image[Constant.EXPLODE_NUM];
		for(int i=0; i<imgs.length; i++) {
			System.out.println(i);
			imgs[i] =GameUtil.getImage("explodes/" + i + ".jpg");
		}
	}
	
	public void draw(Graphics g) {
		if(count < imgs.length)
			g.drawImage(imgs[count++], (int)x, (int)y, null);
	}
	
	public Explode(double x, double y) {
		this.x = x;
		this.y = y;
	}
}
