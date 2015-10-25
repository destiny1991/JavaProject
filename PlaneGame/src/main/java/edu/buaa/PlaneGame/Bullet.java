package edu.buaa.PlaneGame;

import java.awt.Color;
import java.awt.Graphics;
import edu.buaa.util.Constant;

public class Bullet extends GameObject {
	double degree;
		
	public void draw(Graphics g) {
		Color c = g.getColor();
		g.setColor(Color.YELLOW);
		g.fillOval((int)x, (int)y, width, height);
		
		x += speed*Math.cos(degree);
		y += speed*Math.sin(degree);
		
		if(y > Constant.GAME_HEIGHT - height || y < height) {
			degree = - degree;
		}
		if(x < 0 || x > Constant.GAME_WIDTH - width) {
			degree = Math.PI - degree;
		}
		
		g.setColor(c);
	}
	
	public Bullet() {
		super(null,
			  8,
			  8,
			  Constant.GAME_WIDTH / 2,
			  Constant.GAME_HEIGHT / 2,
			  Constant.BULLET_SPEED
				);
		
		this.degree = Math.random() * Math.PI * 2;
	}
}
