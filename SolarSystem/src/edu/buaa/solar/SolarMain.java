package edu.buaa.solar;

import java.awt.Graphics;
import java.awt.Image;

import edu.buaa.util.Constant;
import edu.buaa.util.GameUtil;
import edu.buaa.util.MyFrame;

public class SolarMain extends MyFrame {
	private static final long serialVersionUID = 1L;
	
	private Image bg = GameUtil.getImage("images/bg.jpg");
	 private Star sun = new Star("images/sun.jpg", 
			 Constant.GAME_WIDTH/2, Constant.GAME_HEIGHT/2);
	 
	 private Planet earth = new Planet("images/earth.jpg", 
			  200, 100, 0.02, sun);
	 
	 private Planet moon = new Planet("images/moon.jpg", 
			  60, 30, 0.1, earth, true);
	 
	 private Planet mars = new Planet("images/mars.jpg", 
			  300, 150, 0.1, sun);
	 
	 public void paint(Graphics g) {
		g.drawImage(bg, 0,0, null); 
		sun.draw(g);
		earth.draw(g);
		mars.draw(g);
		moon.draw(g);
	 }
	 
	 public static void main(String[] args) {
		 new SolarMain().launchFrame();
	 }
}
