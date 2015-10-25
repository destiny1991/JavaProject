package edu.buaa.PlaneGame;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.util.Date;

import edu.buaa.util.Constant;
import edu.buaa.util.GameUtil;

public class Plane extends GameObject {	
	boolean left, up, right, down;
	boolean live = true;
	
	Date startTime;
	Date endTime;
	
	Explode bao;
	
	public void printInfo(Graphics g, String str, int size, int x, int y, Color color) {
		Color c = g.getColor();
		g.setColor(color);
		Font f = new Font("宋体", Font.BOLD, size);
		g.setFont(f);
		g.drawString(str, x, y);
		g.setColor(c);
	}
	
	public void draw(Graphics g) {
		if(live) {
			g.drawImage(img, (int)x, (int)y, null);
			move();
		} else {
			if(bao == null)
				bao = new Explode(x, y);
			bao.draw(g);
			
			int period = (int)(endTime.getTime() - startTime.getTime()) / 1000;
			
			switch (period/10) {
			case 0:
			case 1:
				printInfo(g, "学渣", 100, 
						Constant.GAME_WIDTH/3, Constant.GAME_HEIGHT/2, Color.white);
				break;
			case 2:
				printInfo(g, "学弱", 100, 
						Constant.GAME_WIDTH/3, Constant.GAME_HEIGHT/2, Color.white);
				break;
			case 3:
				printInfo(g, "学霸", 100, 
						Constant.GAME_WIDTH/3, Constant.GAME_HEIGHT/2, Color.white);
				break;
			case 4:
				printInfo(g, "学神", 100, 
						Constant.GAME_WIDTH/3, Constant.GAME_HEIGHT/2, Color.white);
				break;
			default:
				printInfo(g, "叫兽", 100, 
						Constant.GAME_WIDTH/3, Constant.GAME_HEIGHT/2, Color.white);
				break;
			}
			
			printInfo(g, "Game Over! Total Time: " + period + "(s)", 
					20, 30, 50, Color.white);
		}
	}
	
	public void move() {
		if(left && x >= 0) {
			x -= speed;
		}
		if(right && x < Constant.GAME_WIDTH - width) {
			x += speed;
		}
	
		if(up && y >= height) {
			y -= speed;
		}
		if(down && y <= Constant.GAME_HEIGHT - height) {
			y += speed;
		}
	}
	
	public void addDirection(KeyEvent e) {
		switch(e.getKeyCode()) {
		case KeyEvent.VK_LEFT:
			left = true;
			break;
		case KeyEvent.VK_UP:
			up = true;
			break;
		case KeyEvent.VK_RIGHT:
			right = true;
			break;
		case KeyEvent.VK_DOWN:
			down = true;
			break;
		}
	}
	
	public void minusDirection(KeyEvent e) {
		switch(e.getKeyCode()) {
		case KeyEvent.VK_LEFT:
			left = false;
			break;
		case KeyEvent.VK_UP:
			up = false;
			break;
		case KeyEvent.VK_RIGHT:
			right = false;
			break;
		case KeyEvent.VK_DOWN:
			down = false;
			break;
		}
	}
	
	public Plane(String imgpath, double x, double y) {
		super(null,
			  0,
			  0,	
			  x,
			  y,
			  Constant.PLANE_SPEED
				);
		this.img = GameUtil.getImage(imgpath);
		this.width = img.getWidth(null);
		this.height = img.getHeight(null);
	}
	
	public Plane() {
	}
}
