package edu.buaa.solar;

import java.awt.Color;
import java.awt.Graphics;

public class Planet extends Star {
	//行星沿着某个椭圆运行：长轴、短轴、速度；
	//绕着某个Star飞；
	private double longAxis;
	private double shortAxis;
	private double speed;
	private double degree;
	private Star center;
	private boolean satellite;
	
	public void draw(Graphics g) {
		super.draw(g);
		if(!satellite) drawTrace(g);
		move();
	}
	
	/**
	 * 画椭圆轨迹
	 * @param g
	 */
	public void drawTrace(Graphics g) {
		double ovalX, ovalY, ovalWidth, ovalHeight;
		
		ovalWidth = longAxis*2;
		ovalHeight = shortAxis*2;
		ovalX = (center.x + center.width/2) - longAxis;
		ovalY = (center.y + center.height/2) - shortAxis;
		
		Color c = g.getColor();
		g.setColor(Color.blue);
		g.drawOval((int)ovalX, (int)ovalY, (int)ovalWidth, (int)ovalHeight);
		g.setColor(c);
	}
	
	/**
	 * 控制行星的飞行
	 */
	public void move() {
		//沿着椭圆飞
		x = (center.x + center.width/2 - super.width/2) + longAxis* Math.cos(degree);
		y = (center.y + center.height/2 - super.height/2) + shortAxis*Math.sin(degree);
				
		degree += speed;
	}
	
	public Planet(String imgpath, double longAxis,
			double shortAxis, double speed, Star center) {
		super(imgpath, center.x + longAxis, center.y);
		
		this.longAxis = longAxis;
		this.shortAxis = shortAxis;
		this.speed = speed;
		this.center = center;
	}
	
	public Planet(String imgpath, double longAxis,
			double shortAxis, double speed, Star center, boolean satellite) {
		this(imgpath, longAxis, shortAxis, speed, center);
		this.satellite = satellite;
	}
}
