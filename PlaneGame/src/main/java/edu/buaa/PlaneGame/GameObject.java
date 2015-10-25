package edu.buaa.PlaneGame;

import java.awt.Image;
import java.awt.Rectangle;

public class GameObject {
	Image img;
	int width, height;
	double x, y;
	int speed;
	
	public Rectangle getRect() {
		return new Rectangle((int)x, (int)y, 
				width, height);
	}

	public GameObject(Image img, int width, int height, 
			double x, double y, int speed) {
		super();
		this.img = img;
		this.width = width;
		this.height = height;
		this.x = x;
		this.y = y;
		this.speed = speed;
	}
	
	public GameObject() {}
}
